package com.hyt.myproject.common.dto;

import java.io.Serializable;

/**
 * 登录输入参数
 * Created by geek on 2017/6/19.
 */
public class InParamterDto implements Serializable {

    private static final long serialVersionUID = -6393281197363625672L;

    private String inputGooglecode;
    private String inputUserpwd;
    private String inputTradepswd;
    private String inputMailVerifyCode;
    private String inputVcode;
    private String requestIp;

    public String getInputGooglecode() {
        return inputGooglecode;
    }

    public void setInputGooglecode(String inputGooglecode) {
        this.inputGooglecode = inputGooglecode;
    }

    public String getInputUserpwd() {
        return inputUserpwd;
    }

    public void setInputUserpwd(String inputUserpwd) {
        this.inputUserpwd = inputUserpwd;
    }

    public String getInputTradepswd() {
        return inputTradepswd;
    }

    public void setInputTradepswd(String inputTradepswd) {
        this.inputTradepswd = inputTradepswd;
    }

    public String getInputMailVerifyCode() {
        return inputMailVerifyCode;
    }

    public void setInputMailVerifyCode(String inputMailVerifyCode) {
        this.inputMailVerifyCode = inputMailVerifyCode;
    }

    public String getInputVcode() {
        return inputVcode;
    }

    public void setInputVcode(String inputVcode) {
        this.inputVcode = inputVcode;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    @Override
    public String toString() {
        return "InParamterDto{" +
                "inputGooglecode='" + inputGooglecode + '\'' +
                ", inputUserpwd='" + inputUserpwd + '\'' +
                ", inputTradepswd='" + inputTradepswd + '\'' +
                ", inputMailVerifyCode='" + inputMailVerifyCode + '\'' +
                ", inputVcode='" + inputVcode + '\'' +
                ", requestIp='" + requestIp + '\'' +
                '}';
    }
}
