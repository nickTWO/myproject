package com.hyt.myproject.controller;

import com.hyt.myproject.common.utils.PayCloudUtils;
import com.hyt.myproject.common.vo.ResponseEntry;
import com.hyt.myproject.common.vo.ResponseFormat;
import org.apache.commons.lang3.StringUtils;


import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * BaseController
 *
 * @author Echo_ayalamih
 * @description desc.
 * @packagename com.paycloudx.backend
 * @date 10:34, 23/11/2017
 */
public class BaseController {

    /**
     * 公共参数验证
     *
     * @param request
     * @return
     */
    public ResponseEntry verifyParam(HttpServletRequest request) {
        try {
            //前端提交的
            String sign = request.getParameter("sign");
            String url = request.getRequestURI();

            String token = "";
            if(!(url.indexOf("isVerifyCode")>0) && !(url.indexOf("userLogin") > 0) && !(url.indexOf("forgotPwd") > 0)
                    && !(url.indexOf("doForgotPwdView") > 0) && !(url.indexOf("doForgotPwd") > 0)
                    && !(url.indexOf("sendMailCodeNoSign") > 0) && !(url.indexOf("register") > 0)){
                String authorization = request.getHeader("Authorization");

                if (StringUtils.isEmpty(authorization) || !authorization.startsWith("Bearer") || "Bearer".equals(authorization.trim())) {
                    return ResponseFormat.retParam(ResponseFormat.HTTP_NOT_AUTHORIZATION, "身份认证失败");
                }
                token = authorization.substring(authorization.indexOf(" ") + 1, authorization.length());
            }

            Map<String, String> parameterMap = new HashMap(8);
            Iterator<Map.Entry<String, String[]>> it = request.getParameterMap().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = it.next();
                StringBuilder value = new StringBuilder(4);
                if (entry.getValue() != null && entry.getValue() instanceof String[]) {
                    String[] values = (String[]) entry.getValue();
                    for (int i = 0; i < values.length; i++) {
                        if ("signType".equals(entry.getKey()) || "sign".equals(entry.getKey()) || "ssoToken".equals(entry.getKey())) {
                            continue;
                        }
                        if (i == values.length - 1) {
                            value.append(values[i]);
                        } else {
                            value.append(values[i] + ",");
                        }
                    }
                    parameterMap.put(entry.getKey().toString(), value.toString());
                }
            }


            String paramString = createSignString(parameterMap);
            if (PayCloudUtils.MD5(paramString + token).equals(sign)) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseFormat.retParam(ResponseFormat.HTTP_BAD_REQUEST, null);
    }

    private static String createSignString(Map params) {

        List keys = new ArrayList(params.keySet());
        Collections.sort(keys);

        StringBuilder prestr = new StringBuilder();
        String key = "";
        String value = "";
        for (int i = 0; i < keys.size(); i++) {
            key = (String) keys.get(i);
            value = (String) params.get(key);
            if ("".equals(value) || "null".equals(value) || value == null) {
                continue;
            } else {
                if (key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")) {
                    continue;
                }
                prestr.append(key).append("=").append(value).append("&");
            }
        }
        return prestr.deleteCharAt(prestr.length() - 1).toString();
    }

}
