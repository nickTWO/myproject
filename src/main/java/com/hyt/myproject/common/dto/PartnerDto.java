package com.hyt.myproject.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Echo_ayalamih
 * @description desc.
 * @packagename com.paycloudx.common.interfaces.dto
 * @date 16:15, 22/11/2017
 */
public class PartnerDto implements Serializable {
    private static final long serialVersionUID = -5935561343298632161L;

    // wp_partners 表字段
    private Integer ptid;

    private String partnerType;

    private String operatePartnerId;

    private String topPartnerId;

    private Integer partnerLevel;

    private String partnerId;

    private String partnerName;

    private String partnerNameAlias;

    private String tradeNotifyUrl;

    private String transferNotifyUrl;

    private String partnerInfo;

    private String paycloudPrivateKey;

    private String paycloudPublicKey;

    private String partnerPublicKey;

    private String apiDomainList;

    private String apiWhiteIp;

    private String partnerMd5Key;

    private String partnerAesKey;

    private Integer singleDayLimit;

    private BigDecimal singleDayQuota;

    private Integer tradeDailyTotal;

    private BigDecimal tradeDailyQuota;

    private String tradeDailyDate;

    private Date createTime;

    private Date updateTime;

    private Integer apiStatus;

    private String appDomain;

    private String appSkin;

    private Integer status;

    private Integer isTemplate;

    private String qpayDomain;

    private String isKyc;

    public String getQpayDomain() {
        return qpayDomain;
    }

    public void setQpayDomain(String qpayDomain) {
        this.qpayDomain = qpayDomain;
    }

    public PartnerDto(){}

    public PartnerDto(String partnerId) {
        this.partnerId = partnerId;
    }

    public Integer getPtid() {
        return ptid;
    }

    public void setPtid(Integer ptid) {
        this.ptid = ptid;
    }

    public String getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(String partnerType) {
        this.partnerType = partnerType;
    }

    public String getOperatePartnerId() {
        return operatePartnerId;
    }

    public void setOperatePartnerId(String operatePartnerId) {
        this.operatePartnerId = operatePartnerId;
    }

    public String getTopPartnerId() {
        return topPartnerId;
    }

    public void setTopPartnerId(String topPartnerId) {
        this.topPartnerId = topPartnerId;
    }

    public Integer getPartnerLevel() {
        return partnerLevel;
    }

    public void setPartnerLevel(Integer partnerLevel) {
        this.partnerLevel = partnerLevel;
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

    public String getPartnerNameAlias() {
        return partnerNameAlias;
    }

    public void setPartnerNameAlias(String partnerNameAlias) {
        this.partnerNameAlias = partnerNameAlias;
    }

    public String getTradeNotifyUrl() {
        return tradeNotifyUrl;
    }

    public void setTradeNotifyUrl(String tradeNotifyUrl) {
        this.tradeNotifyUrl = tradeNotifyUrl;
    }

    public String getTransferNotifyUrl() {
        return transferNotifyUrl;
    }

    public void setTransferNotifyUrl(String transferNotifyUrl) {
        this.transferNotifyUrl = transferNotifyUrl;
    }

    public String getPartnerInfo() {
        return partnerInfo;
    }

    public void setPartnerInfo(String partnerInfo) {
        this.partnerInfo = partnerInfo;
    }

    public String getPaycloudPrivateKey() {
        return paycloudPrivateKey;
    }

    public void setPaycloudPrivateKey(String paycloudPrivateKey) {
        this.paycloudPrivateKey = paycloudPrivateKey;
    }

    public String getPaycloudPublicKey() {
        return paycloudPublicKey;
    }

    public void setPaycloudPublicKey(String paycloudPublicKey) {
        this.paycloudPublicKey = paycloudPublicKey;
    }

    public String getPartnerPublicKey() {
        return partnerPublicKey;
    }

    public void setPartnerPublicKey(String partnerPublicKey) {
        this.partnerPublicKey = partnerPublicKey;
    }

    public String getApiDomainList() {
        return apiDomainList;
    }

    public void setApiDomainList(String apiDomainList) {
        this.apiDomainList = apiDomainList;
    }

    public String getApiWhiteIp() {
        return apiWhiteIp;
    }

    public void setApiWhiteIp(String apiWhiteIp) {
        this.apiWhiteIp = apiWhiteIp;
    }

    public String getPartnerMd5Key() {
        return partnerMd5Key;
    }

    public void setPartnerMd5Key(String partnerMd5Key) {
        this.partnerMd5Key = partnerMd5Key;
    }

    public String getPartnerAesKey() {
        return partnerAesKey;
    }

    public void setPartnerAesKey(String partnerAesKey) {
        this.partnerAesKey = partnerAesKey;
    }

    public Integer getSingleDayLimit() {
        return singleDayLimit;
    }

    public void setSingleDayLimit(Integer singleDayLimit) {
        this.singleDayLimit = singleDayLimit;
    }

    public BigDecimal getSingleDayQuota() {
        return singleDayQuota;
    }

    public void setSingleDayQuota(BigDecimal singleDayQuota) {
        this.singleDayQuota = singleDayQuota;
    }

    public Integer getTradeDailyTotal() {
        return tradeDailyTotal;
    }

    public void setTradeDailyTotal(Integer tradeDailyTotal) {
        this.tradeDailyTotal = tradeDailyTotal;
    }

    public BigDecimal getTradeDailyQuota() {
        return tradeDailyQuota;
    }

    public void setTradeDailyQuota(BigDecimal tradeDailyQuota) {
        this.tradeDailyQuota = tradeDailyQuota;
    }

    public String getTradeDailyDate() {
        return tradeDailyDate;
    }

    public void setTradeDailyDate(String tradeDailyDate) {
        this.tradeDailyDate = tradeDailyDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(Integer apiStatus) {
        this.apiStatus = apiStatus;
    }

    public String getAppDomain() {
        return appDomain;
    }

    public void setAppDomain(String appDomain) {
        this.appDomain = appDomain;
    }

    public String getAppSkin() {
        return appSkin;
    }

    public void setAppSkin(String appSkin) {
        this.appSkin = appSkin;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsTemplate() {
        return isTemplate;
    }

    public void setIsTemplate(Integer isTemplate) {
        this.isTemplate = isTemplate;
    }

    public String getIsKyc() {
        return isKyc;
    }

    public void setIsKyc(String isKyc) {
        this.isKyc = isKyc;
    }
}
