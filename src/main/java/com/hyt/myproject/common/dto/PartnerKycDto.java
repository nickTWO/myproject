package com.hyt.myproject.common.dto;

import java.util.Date;

public class PartnerKycDto {
    private Integer aid;

    private String adomain;

    private String userName;

    private String useePwd;

    private String keyword;

    private Integer status;

    private String remark;

    private Date dateline;

    private Date updateTime;

    private String partnerId;

    private String partnerName;

    private String examineBak;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAdomain() {
        return adomain;
    }

    public void setAdomain(String adomain) {
        this.adomain = adomain == null ? null : adomain.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUseePwd() {
        return useePwd;
    }

    public void setUseePwd(String useePwd) {
        this.useePwd = useePwd;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getDateline() {
        return dateline;
    }

    public void setDateline(Date dateline) {
        this.dateline = dateline;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId == null ? null : partnerId.trim();
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName == null ? null : partnerName.trim();
    }

    public String getExamineBak() {
        return examineBak;
    }

    public void setExamineBak(String examineBak) {
        this.examineBak = examineBak;
    }
}