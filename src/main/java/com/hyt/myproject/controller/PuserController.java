package com.hyt.myproject.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.myproject.common.constant.JwtConstants;
import com.hyt.myproject.common.constant.PayCloudConstants;
import com.hyt.myproject.common.dto.*;
import com.hyt.myproject.common.utils.PayCloudUtils;
import com.hyt.myproject.common.vo.ResponseEntry;
import com.hyt.myproject.common.vo.ResponseFormat;
import com.hyt.myproject.common.vo.Result;
import com.hyt.myproject.jwt.JwtManager;
import com.hyt.myproject.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 操作员类
 * @author: geek
 * @datetime: 2017/12/4 下午1:27
 * @returns:
 */
@RestController
@RequestMapping(value = "/api/v1/puser")
public class PuserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(PuserController.class);


    @Autowired
    private PartnerService partnerService;

    @Autowired
    private PuserService puserService;

    @Autowired
    private PartnersPrivilegeService partnersPrivilegeService;


    @Autowired
    private SafetyPolicyService safetyPolicyService;


    @Autowired
    private HandleLogsService handleLogsService;

    @Autowired
    private JwtManager jwtManager;



    /**
     * @Description: 登陆实现
     * @author: geek
     * @datetime: 2017/12/4 上午11:58
     * @returns:
     */
    @RequestMapping(value = "/userLogin")
    public ResponseEntry userLogin(HttpServletRequest request){
        try {
            //账户
            String appid = request.getParameter("appid");
            //密码
            String secret = request.getParameter("secret");
            //GAcode
            String inputGooglecode = request.getParameter("gacode");

            String ipAddr = PayCloudUtils.getIpAddr(request);
            String logmsg = "[用户登录事件] 当前登录email=" + appid + " 事件描述={}, 请求参数=" + PayCloudUtils.formRequestMap(request.getParameterMap()) + ", 请求头=" + PayCloudUtils.getHeadersInfo(request);

            //根据邮箱查询操作员用户
            Result<PartnersUserDto> partnersUserResult = puserService.getPuserInfoByEmail(appid);
            if(!partnersUserResult.isSuccess()){
                return ResponseFormat.retParam(ResponseFormat.SERVER_ERROR, "用户不存在");
            }

            //获取到用户实例
            PartnersUserDto partnersUserDto = partnersUserResult.getEntry();


            /** 验证用户是否被冻结：0=待开通 1=正常 -2=登陆试错临时冻结 -1=冻结 **/
            if (partnersUserDto.getStatus() == -1 || partnersUserDto.getStatus() == 0) {
                return ResponseFormat.retParam(ResponseFormat.SERVER_ERROR, "用户冻结或未开通，请联系管理员!");
            }
            if (partnersUserDto.getRoleId() == 0 && partnersUserDto.getTopPuserId() != 0){
                return ResponseFormat.retParam(ResponseFormat.SERVER_ERROR, "无权限登录系统,请联系您的主账号进行赋权限!");
            }
            //临时冻结解冻  开始
            if (partnersUserDto.getStatus() == -2) {
                long zerobound = 120; // 2; //
                // 这里有2种可能
                // 最后一次登录时间据当前时间是否小于120分钟
                Date dateline = partnersUserDto.getLastlogin();
                Date now = new Date();
                long minute = (now.getTime() - dateline.getTime()) / (60 * 1000);  //现在和最后一次登录时间比较
                // 1、<120 继续不能登录
                if (minute < zerobound) {
                    logger.info(logmsg, "用户临时冻结, 离上次错误登录时间小于"+zerobound+"分钟，禁止登录!");
                    return ResponseFormat.retParam(ResponseFormat.SERVER_ERROR, "用户暂时冻结，请" + (zerobound - minute) + "分钟后登录！");
                }
                // 2、>120 允许下面操作
                if (minute >= zerobound) {
                    logger.info(logmsg, "用户临时冻结, 离上次错误登录时间大于"+zerobound+"分钟，放行继续验证其他!");
                    //更新 错误登陆次数=0  用户状态=1（正常）
                    PartnersUserDto updateParnerUserLgginFailNmuDto = new PartnersUserDto();
                    updateParnerUserLgginFailNmuDto.setPuid(partnersUserDto.getPuid());
                    updateParnerUserLgginFailNmuDto.setLoginFailNum(Long.valueOf(0));
                    updateParnerUserLgginFailNmuDto.setStatus(Long.valueOf(1));
                    puserService.updatePartnerUserByPuid(updateParnerUserLgginFailNmuDto);
                    //更新账户信息后，partnerUser重新获取
                    partnersUserDto = puserService.getPartnerUserInfo(partnersUserDto).getEntry();
                }
            }
            ////临时冻结解冻  结束

            // ====================================查询商户信息 ====================================
            PartnerDto paramPartnerDto = new PartnerDto();
            paramPartnerDto.setPartnerId(partnersUserDto.getPartnerId());
            Result<PartnerDto> partnerSingleResult = partnerService.getPartnerInfo(paramPartnerDto);
            if (!partnerSingleResult.isSuccess()) {
                logger.info(logmsg, "商户不存在！");
                return ResponseFormat.retParam(ResponseFormat.SERVER_ERROR, "商户不存在");
            }
            PartnerDto PartnerDto = partnerSingleResult.getEntry();
            if (PartnerDto == null) {
                logger.info(logmsg, "无法获取商户或操作员!");
                return ResponseFormat.retParam(ResponseFormat.SERVER_ERROR, "此商户或操作员不存在!");
            }
            if (PartnerDto.getStatus() != 1) {
                logger.info(logmsg, "商户已关闭, 无法登录!");
                return ResponseFormat.retParam(ResponseFormat.SERVER_ERROR, "商户已关闭, 无法登录!");
            }

            if("0".equals(PartnerDto.getOperatePartnerId()) || "PTYPE_OPERATE".equals(PartnerDto.getPartnerType())){
                //==0代表自己就是运营商
                logger.info(logmsg, "此账户为运营商，不能登录商户系统！");
                return ResponseFormat.retParam(ResponseFormat.SERVER_ERROR, "运营商无法登录商户系统！");
            }

            if("0".equals(PartnerDto.getTopPartnerId()) || "PTYPE_AGENCY".equals(PartnerDto.getPartnerType())){
                //==0代表自己就是运营商
                logger.info(logmsg, "此账户为代理商，不能登录商户系统！");
                return ResponseFormat.retParam(ResponseFormat.SERVER_ERROR, "代理商无法登录商户系统！");
            }

            //====================用域名来判断和运营商绑定的域名是否一致====================
            java.net.URL url = new java.net.URL(request.getHeader("Referer"));
            String[] serverNameArr = url.getHost().split("\\.");
            String appDomain = serverNameArr[1]+"."+serverNameArr[2];

            PartnerDto partnerDto = new PartnerDto();
            partnerDto.setAppDomain(appDomain);
            partnerDto.setPartnerId(PartnerDto.getOperatePartnerId());
            Result<PartnerDto> result = partnerService.getPartnerInfo(partnerDto);
            if(!result.isSuccess()){
                logger.info(logmsg, "绑定域名下商户不存在, 当前登录 appid="+appid+", 请求域名 appDomaim'pn="+appDomain);
                return ResponseFormat.retParam(ResponseFormat.SERVER_ERROR, "此商户不存在!");
            }
            //====================用域名来判断和运营商绑定的域名是否一致====================

            // ====================================记录日志====================================
            HandleLogsDto handleLogsDto = new HandleLogsDto();
            handleLogsDto.setPuserid(partnersUserDto.getPuid().toString());
            handleLogsDto.setPartnerId(partnersUserDto.getPartnerId());
            handleLogsDto.setHandleType(PayCloudConstants.TYPE_PARTNER_HANDLE);
            handleLogsDto.setHandleCode("PARTNER_LOGIN");
            handleLogsDto.setHandleIp(ipAddr);
            //默认为失败
            handleLogsDto.setHandleStatus(0);
            handleLogsDto.setDateline(new Date());

//             ----------------------------------------------验证安全策略start----------------------------------------------------------------
            SafetyPolicyDto safetyPolicyDto = new SafetyPolicyDto();
            safetyPolicyDto.setPuid(partnersUserDto.getPuid().intValue());
            safetyPolicyDto.setPolicyModule(PayCloudConstants.SAFETY_POLICY_LOGIN);
            // 需要判断的字段
            InParamterDto inParamterDto = new InParamterDto();
            inParamterDto.setInputGooglecode(inputGooglecode);
            inParamterDto.setInputUserpwd(secret);
            inParamterDto.setRequestIp(ipAddr);
            Result<String> singleResultPolicy = safetyPolicyService.verificationPolicy(safetyPolicyDto, inParamterDto);
            if (!singleResultPolicy.isSuccess()) {
                //======密码错误5次，冻结账户===
                Long login_fail_num = partnersUserDto.getLoginFailNum();

                PartnersUserDto lastParnerUserDto = new PartnersUserDto();
                lastParnerUserDto.setPuid(partnersUserDto.getPuid());
                lastParnerUserDto.setLoginFailNum(login_fail_num + 1);
                lastParnerUserDto.setLastip(ipAddr);
                lastParnerUserDto.setLastlogin(new Timestamp(System.currentTimeMillis()));

                if (login_fail_num + 1 >= 5) {
                    //错误次数5次，临时冻结
                    lastParnerUserDto.setStatus(-2L);
                    logger.info(logmsg, singleResultPolicy.getErrorMessage() + "，安全验证失败" + login_fail_num + "次，账户临时冻结!");
                }
                puserService.updatePartnerUserByPuid(lastParnerUserDto);
                logger.info(logmsg, singleResultPolicy.getErrorMessage() + "，账户临时冻结!");

                handleLogsDto.setHandleParams(logmsg.replace("事件描述={}", "事件描述={" + singleResultPolicy.getErrorMessage() + "!}"));
                handleLogsDto.setHandleEvents(singleResultPolicy.getErrorMessage());
                handleLogsService.insertHandleLogs(handleLogsDto);

                return ResponseFormat.retParam(ResponseFormat.SERVER_ERROR, singleResultPolicy.getErrorMessage());
            }
            String policy_reason = singleResultPolicy.getEntry();
            if (!"OK".equals(policy_reason)) {
                String msg = policy_reason;

                //======密码错误5次，冻结账户===
                Long login_fail_num = partnersUserDto.getLoginFailNum();
                PartnersUserDto lastParnerUserDto = new PartnersUserDto();
                lastParnerUserDto.setPuid(partnersUserDto.getPuid());
                lastParnerUserDto.setLoginFailNum(login_fail_num + 1);
                lastParnerUserDto.setLastip(ipAddr);
                lastParnerUserDto.setLastlogin(new Timestamp(System.currentTimeMillis()));

                if (login_fail_num + 1 >= 5) {
                    lastParnerUserDto.setStatus(-2L); //错误次数5次，临时冻结
                    msg ="安全验证失败5次，账户临时冻结!" ;
                }
                puserService.updatePartnerUserByPuid(lastParnerUserDto);
                logger.info(logmsg, policy_reason + "，账户临时冻结!");

                handleLogsDto.setHandleParams(logmsg.replace("事件描述={}", "事件描述={安全验证失败3次，账户临时冻结!}"));
                handleLogsDto.setHandleEvents("安全验证失败5次，账户临时冻结!");
                handleLogsService.insertHandleLogs(handleLogsDto);

                return ResponseFormat.retParam(ResponseFormat.SERVER_ERROR, msg);
            }

            if (!PayCloudUtils.isEmpty(partnersUserDto.getLoginWhiteIp())) {
                if (partnersUserDto.getLoginWhiteIp().indexOf(ipAddr) <= -1) {
                    logger.info(logmsg, "登陆IP不是白名单中IP");

                    //======IP不在白名单中，冻结账户===
                    Long login_fail_num = partnersUserDto.getLoginFailNum();
                    PartnersUserDto lastParnerUserDto = new PartnersUserDto();
                    lastParnerUserDto.setPuid(partnersUserDto.getPuid());
                    lastParnerUserDto.setLoginFailNum(login_fail_num + 1);
                    lastParnerUserDto.setLastip(ipAddr);
                    lastParnerUserDto.setLastlogin(new Timestamp(System.currentTimeMillis()));
                    if (login_fail_num + 1 >= 5) {
                        lastParnerUserDto.setStatus(-2L);
                        logger.info(logmsg, "登录域名IP验证已启用，当前登录域名IP未添加，无法登陆，账户临时冻结!");
                    }
                    puserService.updatePartnerUserByPuid(lastParnerUserDto);

                    handleLogsDto.setHandleParams(logmsg.replace("事件描述={}", "事件描述={登录域名IP验证已启用，并且当前登录域名IP未添加到IP白名单，禁止登录!}"));
                    handleLogsDto.setHandleEvents("登录域名IP验证已启用，当前登录域名IP未添加，无法登陆!");
                    handleLogsService.insertHandleLogs(handleLogsDto);

                    return ResponseFormat.retParam(ResponseFormat.SERVER_ERROR, "当前登录域名IP未添加，无法登陆，请联系管理员！");
                }
            }

            //登录成功密码错误次数清零
            PartnersUserDto lastParnerUserDto = new PartnersUserDto();
            lastParnerUserDto.setPuid(partnersUserDto.getPuid());
            lastParnerUserDto.setLoginFailNum(Long.valueOf(0));
            lastParnerUserDto.setLastip(PayCloudUtils.getIpAddr(request));
            lastParnerUserDto.setLastlogin(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
            puserService.updatePartnerUserByPuid(lastParnerUserDto);

            // 获取权限菜单 权限
            PartnersPrivilegeDto partnersPrivilegeDto = new PartnersPrivilegeDto();
            partnersPrivilegeDto.setRoleId(partnersUserDto.getRoleId());
            Result<List<PartnersPrivilegeDto>> singleResult = partnersPrivilegeService.getPartnersPrivilegeByRoleId(partnersPrivilegeDto);
            if (!singleResult.isSuccess()) {
                return ResponseFormat.retParam(ResponseFormat.SERVER_ERROR, "系统错误,请联系客服");
            }

            List<PartnersPrivilegeDto> partnersPrivilegeList = singleResult.getEntry();
            if (partnersPrivilegeList == null || partnersPrivilegeList.size() == 0) {
                // '父级管理员ID', 默认0
                Long top_puser_id = partnersUserDto.getTopPuserId();
                if (top_puser_id == 0) {
                    logger.info(logmsg, "无权限登录系统,请联系客服!");

                    handleLogsDto.setHandleEvents("无权限登录系统！");
                    handleLogsDto.setHandleParams(logmsg.replace("事件描述={}", "事件描述={无权限登录系统,请联系客服!}"));
                    handleLogsService.insertHandleLogs(handleLogsDto);

                    return ResponseFormat.retParam(ResponseFormat.SERVER_ERROR, "无权限登录系统,请联系客服!");
                } else {
                    logger.info(logmsg, "无权限登录系统,请联系您的主账号进行赋权限!");

                    handleLogsDto.setHandleEvents("无权限登录系统！");
                    handleLogsDto.setHandleParams(logmsg.replace("事件描述={}", "事件描述={无权限登录系统,请联系您的主账号进行赋权限!}"));
                    handleLogsService.insertHandleLogs(handleLogsDto);

                    return ResponseFormat.retParam(ResponseFormat.SERVER_ERROR, "无权限登录系统,请联系您的主账号进行赋权限!");
                }
            }
            List<PartnersPrivilegeDto> jsonArray = JSONArray.parseArray(JSON.toJSONString(partnersPrivilegeList), PartnersPrivilegeDto.class);

            //--------------------分离各个集合--------------------
            //主菜单
            List<PartnersPrivilegeDto> menusList = new ArrayList();
            //子菜单
            List<PartnersPrivilegeDto> charMenusList = new ArrayList();
            //action
            List<PartnersPrivilegeDto> actionMenusList = new ArrayList();
            for (PartnersPrivilegeDto partnersPrivilege : jsonArray) {
                //菜单类型 0：主菜单 1：子菜单 2：操作Action
                Long menuType = partnersPrivilege.getPartnersMenusDto().getMenuType();
                if (menuType.equals(0L)) {
//                    System.out.println(partnersPrivilege.toString());
                    menusList.add(partnersPrivilege);
                } else if (menuType.equals(1L)) {
                    charMenusList.add(partnersPrivilege);
                } else if (menuType.equals(2L)) {
                    actionMenusList.add(partnersPrivilege);
                }
            }
            for (PartnersPrivilegeDto partnersPrivilege : menusList) {
                Long mmid = partnersPrivilege.getPartnersMenusDto().getMmid();
                List<PartnersPrivilegeDto> charmenusList = new ArrayList();
                for (PartnersPrivilegeDto charPartnersPrivilege : charMenusList) {
                    if (mmid.equals(charPartnersPrivilege.getPartnersMenusDto().getParentMid())) {
                        charmenusList.add(charPartnersPrivilege);
                    }
                    Long mmid_ = charPartnersPrivilege.getPartnersMenusDto().getMmid();
                    List<PartnersPrivilegeDto> actionmenusList = new ArrayList();
                    for (PartnersPrivilegeDto actionPartnersPrivilege : actionMenusList) {
                        if (mmid_.equals(actionPartnersPrivilege.getPartnersMenusDto().getParentMid())) {
                            actionmenusList.add(actionPartnersPrivilege);
                        }
                    }
                    charPartnersPrivilege.setActionmenusList(actionmenusList);
                }
                partnersPrivilege.setChildren(charmenusList);
            }

            // 更新会员最后登陆信息
            PartnersUserDto lastPartnerUserDto = new PartnersUserDto();
            lastPartnerUserDto.setPuid(partnersUserDto.getPuid());
            lastPartnerUserDto.setLastip(ipAddr);
            lastPartnerUserDto.setLastlogin(new Timestamp(System.currentTimeMillis()));
            lastPartnerUserDto.setStatus(1L);
            lastPartnerUserDto.setLoginFailNum(0L);
            puserService.updatePartnerUserByPuid(lastPartnerUserDto);

            //添加登陆日志
            handleLogsDto.setDateline(lastPartnerUserDto.getLastlogin());
            handleLogsDto.setHandleParams(logmsg.replace("事件描述={}", "事件描述={登录成功!}"));
            handleLogsDto.setHandleEvents("登录成功！");
            handleLogsDto.setHandleStatus(1);
            handleLogsService.insertHandleLogs(handleLogsDto);

            //获取jwt，返回给客户端
            String subject = "{\"puid\":\""+partnersUserDto.getPuid()+"\", \"loginIp\":\""+ipAddr+"\"}";
            String token = jwtManager.createJwtToken(partnersUserDto.getPuid().toString(), subject, JwtConstants.ISSUSR, JwtConstants.JWT_SECRET_KEY);

            //接口返回
            JSONObject json = new JSONObject();
            json.put("partnerId", partnersUserDto.getPartnerId());
            json.put("userName", partnersUserDto.getUsername());
            json.put("puid", partnersUserDto.getPuid());
            json.put("email", partnersUserDto.getEmail());
            json.put("partnerType", partnersUserDto.getPartnerType());
            json.put("isAdmin", partnersUserDto.getTopPuserId() == 0 ? 1:0);

            json.put("menusList", menusList);
            json.put("token", token);
            return ResponseFormat.retParam(ResponseFormat.SERVER_SUCCESS, json);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseFormat.retParam(ResponseFormat.SERVER_ERROR, "系统异常，请联系客服！");
        }
    }

}
