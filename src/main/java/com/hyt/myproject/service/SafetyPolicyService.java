package com.hyt.myproject.service;

import com.hyt.myproject.common.constant.PayCloudConstants;
import com.hyt.myproject.common.dao.PartnersMessageDao;
import com.hyt.myproject.common.dao.PuserDao;
import com.hyt.myproject.common.dao.SafetyPolicyDao;
import com.hyt.myproject.common.dto.InParamterDto;
import com.hyt.myproject.common.dto.PartnersMessageDto;
import com.hyt.myproject.common.dto.PartnersUserDto;
import com.hyt.myproject.common.dto.SafetyPolicyDto;
import com.hyt.myproject.common.utils.DateUtils;
import com.hyt.myproject.common.utils.GoogleManger;
import com.hyt.myproject.common.utils.PayCloudUtils;
import com.hyt.myproject.common.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

/**
 * Created by lhx on 2017/12/08.
 */
@Service("safetyPolicyService")
public class SafetyPolicyService {

    private static final Logger logger = LoggerFactory.getLogger(SafetyPolicyService.class);


    @Autowired
    private PartnersMessageDao partnersMessageDao;

    @Autowired
    private SafetyPolicyDao safetyPolicyDao;

    @Autowired
    private PuserDao partnerUserDao;



    /**
     * @param safetyPolicyDto : policy_module "SAFETY_POLICY";  "SAFETY_POLICY_LOGIN"; "SAFETY_POLICY_SET"; "SAFETY_POLICY_MENTION";
     * @Description: 验证策略
     * @author: geek
     * @datetime: 2017/6/8 上午10:21
     * @returns:
     */
    public Result<String> verificationPolicy(SafetyPolicyDto safetyPolicyDto, InParamterDto inParamterDto) {
        Result<String> result = new Result();

        Integer puid = safetyPolicyDto.getPuid();
        String policy_module = safetyPolicyDto.getPolicyModule();

        String policy_reason = "安全验证未通过";
        String logmsg = "[安全策略验证] 当前登录者 puid="+puid + ", policy_module=" + policy_module +", 事件描述={}";
        try {
            String input_userpwd = inParamterDto.getInputUserpwd();
            String input_tradepswd = inParamterDto.getInputTradepswd();
            String input_mail_verify_code = inParamterDto.getInputMailVerifyCode();
            String input_googlecode = inParamterDto.getInputGooglecode();
            String input_vcode = inParamterDto.getInputVcode();

            SafetyPolicyDto safetyPolicy = safetyPolicyDao.getSafetyPolicyByPuidPmodule(safetyPolicyDto);
            int policy_value = 0;
            if (safetyPolicy == null) {
                policy_reason = "策略表无信息";
                logger.info(logmsg, "policy_value="+policy_value+", 用户未配置策略，将使用默认策略[登录密码]or[支付密码]or[邮件密码], policy_reason=" + policy_reason);
                if(PayCloudConstants.SAFETY_POLICY_MENTION.equals(policy_module)){
                    policy_value = 1;
                }else{
                    policy_value = 0;
                }
            } else {
                policy_value = safetyPolicy.getPolicyValue();
                policy_reason = "策略表查询到信息";
                logger.info(logmsg, "policy_value="+policy_value+", 将使用默认策略, policy_reason=" + policy_reason);
            }

            PartnersUserDto partnerUserDto = new PartnersUserDto();
            partnerUserDto.setPuid(Long.valueOf(puid));
            partnerUserDto = partnerUserDao.getPartnerUserInfo(partnerUserDto);

//            策略组合：
//            0=支付密码 or 登录密码 or 邮件密码 （这个不存入表中）
//            1=支付密码+短信验证码
//            2=支付密码+GA验证码
//            3=支付密码+短信验证码+GA验证码
            if (policy_value == 0 || policy_value == 1 || policy_value == 2 || policy_value == 3) { // 默认
                if (PayCloudConstants.SAFETY_POLICY_LOGIN.equals(policy_module)) {
                    String loginpwd = partnerUserDto.getLoginpwd();
                    if (PayCloudUtils.isEmpty(loginpwd)) {
                        policy_reason = "用户未设置登录密码";
                        logger.info(logmsg, "policy_value="+policy_value+", 用户使用默认策略验证, 验证不通过, policy_reason=" + policy_reason);
//                        result.setResult(policy_reason);
                        result.setErrorMessage(policy_reason);
                        return result;
                    }
                    if (PayCloudUtils.isEmpty(input_userpwd)) {
                        policy_reason = "未输入登录密码";
                        logger.info(logmsg, "policy_value="+policy_value+", 用户使用默认策略验证, 验证不通过, policy_reason=" + policy_reason);
//                        result.setResult(policy_reason);
                        result.setErrorMessage(policy_reason);
                        return result;
                    }
                    if (!PayCloudUtils.getCertifiedSigned(input_userpwd, partnerUserDto.getSalts()).equals(loginpwd)) {
                        policy_reason = "登录密码错误";
                        logger.info(logmsg, "policy_value="+policy_value+", 用户使用默认策略验证, 验证不通过, policy_reason=" + policy_reason);
//                        result.setResult(policy_reason);
                        result.setErrorMessage(policy_reason);
                        return result;
                    }
                } else if(PayCloudConstants.SAFETY_POLICY_MENTION.equals(policy_module)) {
                    String tradepwd = partnerUserDto.getTradepwd();
                    if (PayCloudUtils.isEmpty(tradepwd)) {
                        policy_reason = "必须要输入交易密码！您还未设置交易密码请先设置交易密码,再来操作";
                        logger.info(logmsg, "policy_value="+policy_value+", 用户使用默认策略验证, 验证不通过, policy_reason=" + policy_reason);
//                        result.setResult(policy_reason);
                        result.setErrorMessage(policy_reason);
                        return result;
                    }
                    if (PayCloudUtils.isEmpty(input_tradepswd)) {
                        policy_reason = "未输入交易密码";
                        logger.info(logmsg, "policy_value="+policy_value+", 用户使用默认策略验证, 验证不通过, policy_reason=" + policy_reason);
//                        result.setResult(policy_reason);
                        result.setErrorMessage(policy_reason);
                        return result;
                    }
                    if (!PayCloudUtils.getCertifiedSigned(input_tradepswd, partnerUserDto.getSalts()).equals(tradepwd)) {
                        policy_reason = "交易密码错误";
                        logger.info(logmsg, "policy_value="+policy_value+", 用户使用默认策略验证, 验证不通过, policy_reason=" + policy_reason);
//                        result.setResult(policy_reason);
                        result.setErrorMessage(policy_reason);
                        return result;
                    }
                }else if(PayCloudConstants.SAFETY_POLICY_SET.equals(policy_module)){
                    String email = partnerUserDto.getEmail();
                    if (PayCloudUtils.isEmpty(email)) {
                        policy_reason = "用户未绑定邮箱";
                        logger.info(logmsg, "policy_value="+policy_value+", email="+partnerUserDto.getEmail()+", 用户使用默认邮箱验证, 验证不通过, policy_reason=" + policy_reason);
//                        result.setResult(policy_reason);
                        result.setErrorMessage(policy_reason);
                        return result;
                    }
                    if (PayCloudUtils.isEmpty(input_mail_verify_code)) {
                        policy_reason = "未输入邮箱验证码";
                        logger.info(logmsg, "policy_value="+policy_value+", email="+partnerUserDto.getEmail()+", 用户使用默认邮箱验证, 验证不通过, policy_reason=" + policy_reason);
//                        result.setResult(policy_reason);
                        result.setErrorMessage(policy_reason);
                        return result;
                    }

                    PartnersMessageDto parMessageDto = new PartnersMessageDto();
                    parMessageDto.setAccount(email);
                    parMessageDto.setPuid(puid.toString());
                    parMessageDto.setMtype("email");
                    parMessageDto.setIsused(0); //0、未使用 1、已使用
                    parMessageDto.setStatus(1); //状态  0:发送中 1:发送成功 -1:发送失败
                    PartnersMessageDto partnersMessageDto = partnersMessageDao.getPartnersMessageInfo(parMessageDto);
                    if(partnersMessageDto == null){
                        policy_reason = "邮箱验证码无效";
                        logger.info(logmsg, "policy_value="+policy_value+", email="+partnerUserDto.getEmail()+", 用户使用默认邮箱验证, 邮箱对应的验证码不存在, 验证不通过, policy_reason=" + policy_reason);
                        result.setErrorMessage(policy_reason);
                        return result;
                    }
                    if(partnersMessageDto.getIsused() == 1){
                        policy_reason = "邮箱验证码失效或者已被使用";
                        logger.info(logmsg, "policy_value="+policy_value+", email="+partnerUserDto.getEmail()+", 用户使用默认邮箱验证, 邮箱对应的验证码不存在, 验证不通过, policy_reason=" + policy_reason);
//                        result.setResult(policy_reason);
                        result.setErrorMessage(policy_reason);
                        return result;
                    } else {
                        Date dateline = partnersMessageDto.getDateline();
                        long seconds = DateUtils.getNumberOfSecondsBetween(dateline, new Date());
                        if (seconds > 15*60) {
                            policy_reason = "邮箱验证码失效";
                            logger.info(logmsg, "policy_value="+policy_value+", email="+partnerUserDto.getEmail()+", 用户验证验证码超过15分钟, 验证不通过, seconds="+seconds+", policy_reason=" + policy_reason);
//                            result.setResult(policy_reason);
                            result.setErrorMessage(policy_reason);
                            return result;
                        }
                    }
                    String vcode = partnersMessageDto.getVcode();
                    if (!vcode.equals(input_mail_verify_code)) {
                        policy_reason = "邮箱验证码无效";
                        logger.info(logmsg, "policy_value="+policy_value+", email="+partnerUserDto.getEmail()+", 用户使用默认邮箱验证, 验证码比对不相等, 验证不通过, policy_reason=" + policy_reason);
//                        result.setResult(policy_reason);
                        result.setErrorMessage(policy_reason);
                        return result;
                    }
                    //更新验证码表状态
                    PartnersMessageDto updatePartnersMessageDto = new PartnersMessageDto();
                    updatePartnersMessageDto.setMid(partnersMessageDto.getMid());
                    updatePartnersMessageDto.setVcode(input_mail_verify_code);
                    updatePartnersMessageDto.setPuid(partnersMessageDto.getPuid());
                    updatePartnersMessageDto.setMtype(partnersMessageDto.getMtype());
                    updatePartnersMessageDto.setIsused(1);
                    updatePartnersMessageDto.setFinishtime(new Date());
                    partnersMessageDao.updatePartnerMessage(updatePartnersMessageDto);
                }else{
                    policy_reason = "无法通过验证";
                    logger.info(logmsg, "policy_value="+policy_value+", 未知安全策略模式, 验证不通过, policy_module="+policy_module+", policy_reason=" + policy_reason);
//                    result.setResult(policy_reason);
                    result.setErrorMessage(policy_reason);
                    return result;
                }
                policy_reason = "OK";
            }

            if (policy_value == 2 || policy_value == 3) { // 2=默认+GA验证码
                if (PayCloudUtils.isEmpty(input_googlecode)) {
                    policy_reason = "未输入GA验证码";
                    logger.info(logmsg, "policy_value="+policy_value+", 用户使用默认+GA验证码策略验证, 验证不通过, policy_reason=" + policy_reason);
//                    result.setResult(policy_reason);
                    result.setErrorMessage(policy_reason);
                    return result;
                }
                String GA_SECRET = partnerUserDto.getGaSecret();
                if (PayCloudUtils.isEmpty(GA_SECRET)) {
                    policy_reason = "用户未绑定Google身份验证器";
                    logger.info(logmsg, "policy_value="+policy_value+", 用户使用默认+GA验证码策略验证, 验证不通过, policy_reason=" + policy_reason);
//                    result.setResult(policy_reason);
                    result.setErrorMessage(policy_reason);
                    return result;
                }

                if (!GoogleManger.checkGoogleCode(GA_SECRET, input_googlecode, puid+"")) {
                    policy_reason = "Google验证码已失效或已被使用过";
                    logger.info(logmsg, "policy_value="+policy_value+", 用户使用默认+GA验证码策略验证, 验证不通过, policy_reason=" + policy_reason);
//                    result.setResult(policy_reason);
                    result.setErrorMessage(policy_reason);
                    return result;
                }
                policy_reason = "OK";
            }

            if (policy_value == 1 || policy_value == 3) { // 默认+短信
                String areacode = partnerUserDto.getAreacode();
                String mobile = partnerUserDto.getMobile();
                if (PayCloudUtils.isEmpty(areacode) || PayCloudUtils.isEmpty(mobile)) {
                    policy_reason = "用户未绑定手机";
                    logger.info(logmsg, "policy_value="+policy_value+", 用户使用默认+短信策略验证, 验证不通过, policy_reason=" + policy_reason);
//                    result.setResult(policy_reason);
                    result.setErrorMessage(policy_reason);
                    return result;
                }
                if (PayCloudUtils.isEmpty(input_vcode)) {
                    policy_reason = "未输入短信验证码";
                    logger.info(logmsg, "policy_value="+policy_value+", 用户使用默认+短信策略验证, 验证不通过, policy_reason=" + policy_reason);
//                    result.setResult(policy_reason);
                    result.setErrorMessage(policy_reason);
                    return result;
                }
                PartnersMessageDto parMessageDto = new PartnersMessageDto();
                parMessageDto.setAreacode(areacode);
                parMessageDto.setAccount(mobile);
                parMessageDto.setPuid(puid+"");
                parMessageDto.setMtype("mobile");
                parMessageDto.setIsused(0); //0、未使用 1、已使用
                parMessageDto.setStatus(1); //状态  0:发送中 1:发送成功 -1:发送失败
                PartnersMessageDto partnersMessageDto = partnersMessageDao.getPartnersMessageInfo(parMessageDto);
                if(partnersMessageDto.getIsused() == 1){
                    policy_reason = "短信验证码无效或已被使用";
                    logger.info(logmsg, "policy_value="+policy_value+", 用户使用默认+短信策略验证, 短信不存在, 验证不通过, policy_reason=" + policy_reason);
//                    result.setResult(policy_reason);
                    result.setErrorMessage(policy_reason);
                    return result;
                }
                else{
                    Date dateline = partnersMessageDto.getDateline();
                    long seconds = DateUtils.getNumberOfSecondsBetween(dateline, new Date());
                    if (seconds > 15*60) {
                        policy_reason = "短信验证码失效";
                        logger.info(logmsg, "policy_value="+policy_value+", 用户验证验证码超过15分钟, 验证不通过, seconds="+seconds+", policy_reason=" + policy_reason);
//                        result.setResult(policy_reason);
                        result.setErrorMessage(policy_reason);
                        return result;
                    }
                }
                String vcode = partnersMessageDto.getVcode();
                if (!vcode.equals(input_vcode)) {
                    policy_reason = "短信验证码失效";
                    logger.info(logmsg, "policy_value="+policy_value+", 用户使用默认+短信策略验证, 验证不通过, policy_reason=" + policy_reason);
//                    result.setResult(policy_reason);
                    result.setErrorMessage(policy_reason);
                    return result;
                }
                //更新短信验证码表状态
                PartnersMessageDto updatePartnersMessageDto = new PartnersMessageDto();
                updatePartnersMessageDto.setMid(partnersMessageDto.getMid());
                updatePartnersMessageDto.setVcode(input_mail_verify_code);
                updatePartnersMessageDto.setPuid(partnersMessageDto.getPuid());
                updatePartnersMessageDto.setMtype(partnersMessageDto.getMtype());
                updatePartnersMessageDto.setIsused(1);
                updatePartnersMessageDto.setFinishtime(new Date());
                partnersMessageDao.updatePartnerMessage(updatePartnersMessageDto);

                policy_reason = "OK";
            }
            logger.info(logmsg, "policy_value="+policy_value+", 用户安全策略验证通过, policy_reason=" + policy_reason);
            result.setSuccess(true);
            result.setEntry(policy_reason);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            policy_reason = "策略验证异常";
            logger.info(logmsg, "用户安全策略验证异常, 验证不通过, policy_reason=" + policy_reason +", "+ e.getMessage());
        }
        result.setErrorMessage(policy_reason);
//        result.setResult(policy_reason);
        return result;
    }



}
