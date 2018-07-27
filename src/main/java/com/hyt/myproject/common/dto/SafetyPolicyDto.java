package com.hyt.myproject.common.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lhx
 * @date 2017/12/8
 */
public class SafetyPolicyDto implements Serializable{

    private static final long serialVersionUID = -4309527307470045844L;

    private Integer spid;

    private Integer puid;

    private String policyModule;

    private Integer policyValue;

    private Date dateline;

    public Integer getSpid() {
        return spid;
    }

    public void setSpid(Integer spid) {
        this.spid = spid;
    }

    public Integer getPuid() {
        return puid;
    }

    public void setPuid(Integer puid) {
        this.puid = puid;
    }

    public String getPolicyModule() {
        return policyModule;
    }

    public void setPolicyModule(String policyModule) {
        this.policyModule = policyModule == null ? null : policyModule.trim();
    }

    public Integer getPolicyValue() {
        return policyValue;
    }

    public void setPolicyValue(Integer policyValue) {
        this.policyValue = policyValue;
    }

    public Date getDateline() {
        return dateline;
    }

    public void setDateline(Date dateline) {
        this.dateline = dateline;
    }

    @Override
    public String toString() {
        return "SafetyPolicyDto{" +
                "spid=" + spid +
                ", puid=" + puid +
                ", policyModule='" + policyModule + '\'' +
                ", policyValue=" + policyValue +
                ", dateline=" + dateline +
                '}';
    }
}
