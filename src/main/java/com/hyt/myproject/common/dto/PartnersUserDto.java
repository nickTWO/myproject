package com.hyt.myproject.common.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description: 操作员实体
 * @author: geek
 * @datetime: 2017/12/4 下午1:25
 * @returns:
 */
public class PartnersUserDto implements Serializable {


    private static final long serialVersionUID = 2071074997473458800L;

    private Long puid;
    private Long topPuserId;
    private Long roleId;
    private String partnerType;
    private String partnerId;
    private String partnerName;
    private String email;
    private String username;
    private String areacode;
    private String mobile;
    private String loginpwd;
    private String tradepwd;
    private Timestamp lastlogin;
    private Long loginFailNum;
    private String lastip;
    private String salts;
    private Timestamp createTime;
    private String gaSecret;
    private String loginWhiteIp;
    private Long status;
    private Timestamp resetLoginPwdTime;
    private Timestamp resetTradePwdTime;

    /**wp_partner_role字段*/
    private String roleName;

    public Long getPuid() {
        return puid;
    }

    public void setPuid(Long puid) {
        this.puid = puid;
    }

    public Long getTopPuserId() {
        return topPuserId;
    }

    public void setTopPuserId(Long topPuserId) {
        this.topPuserId = topPuserId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(String partnerType) {
        this.partnerType = partnerType;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLoginpwd() {
        return loginpwd;
    }

    public void setLoginpwd(String loginpwd) {
        this.loginpwd = loginpwd;
    }

    public String getTradepwd() {
        return tradepwd;
    }

    public void setTradepwd(String tradepwd) {
        this.tradepwd = tradepwd;
    }

    public Timestamp getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Timestamp lastlogin) {
        this.lastlogin = lastlogin;
    }

    public Long getLoginFailNum() {
        return loginFailNum;
    }

    public void setLoginFailNum(Long loginFailNum) {
        this.loginFailNum = loginFailNum;
    }

    public String getLastip() {
        return lastip;
    }

    public void setLastip(String lastip) {
        this.lastip = lastip;
    }

    public String getSalts() {
        return salts;
    }

    public void setSalts(String salts) {
        this.salts = salts;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getGaSecret() {
        return gaSecret;
    }

    public void setGaSecret(String gaSecret) {
        this.gaSecret = gaSecret;
    }

    public String getLoginWhiteIp() {
        return loginWhiteIp;
    }

    public void setLoginWhiteIp(String loginWhiteIp) {
        this.loginWhiteIp = loginWhiteIp;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Timestamp getResetLoginPwdTime() {
        return resetLoginPwdTime;
    }

    public void setResetLoginPwdTime(Timestamp resetLoginPwdTime) {
        this.resetLoginPwdTime = resetLoginPwdTime;
    }

    public Timestamp getResetTradePwdTime() {
        return resetTradePwdTime;
    }

    public void setResetTradePwdTime(Timestamp resetTradePwdTime) {
        this.resetTradePwdTime = resetTradePwdTime;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    @Override
    public String toString() {
        return "PartnersUserDto{" +
                "puid=" + puid +
                ", topPuserId=" + topPuserId +
                ", roleId=" + roleId +
                ", partnerType='" + partnerType + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", partnerName='" + partnerName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", areacode='" + areacode + '\'' +
                ", mobile='" + mobile + '\'' +
                ", loginpwd='" + loginpwd + '\'' +
                ", tradepwd='" + tradepwd + '\'' +
                ", lastlogin=" + lastlogin +
                ", loginFailNum=" + loginFailNum +
                ", lastip='" + lastip + '\'' +
                ", salts='" + salts + '\'' +
                ", createTime=" + createTime +
                ", gaSecret='" + gaSecret + '\'' +
                ", loginWhiteIp='" + loginWhiteIp + '\'' +
                ", status=" + status +
                ", resetLoginPwdTime=" + resetLoginPwdTime +
                ", resetTradePwdTime=" + resetTradePwdTime +
                '}';
    }
}
