package com.hyt.myproject.common.dto;

import java.io.Serializable;
import java.util.Date;

public class PartnersMessageDto implements Serializable {
    private static final long serialVersionUID = 5563746678563987646L;
    private Integer mid;

    private String mtype;

    private String partnerId;

    private String puid;

    private String content;

    private String vcode;

    private String areacode;

    private String account;

    private String results;

    private Date sendtime;

    private Integer isused;

    private Date finishtime;

    private Date dateline;

    private String ip;

    private Integer status;

    private String channelCode;

    private Integer isTxi;

    //==========以下3个参数为分页必须===============
    private Integer pageSize;

    private Integer pageIndex;

    private String sortString;
    //==========以上3个参数为分页必须===============

    private String isResetPwd;

    private String emailSign;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMtype() {
        return mtype;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPuid() {
        return puid;
    }

    public void setPuid(String puid) {
        this.puid = puid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Integer getIsused() {
        return isused;
    }

    public void setIsused(Integer isused) {
        this.isused = isused;
    }

    public Date getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Date finishtime) {
        this.finishtime = finishtime;
    }

    public Date getDateline() {
        return dateline;
    }

    public void setDateline(Date dateline) {
        this.dateline = dateline;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public Integer getIsTxi() {
        return isTxi;
    }

    public void setIsTxi(Integer isTxi) {
        this.isTxi = isTxi;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getSortString() {
        return sortString;
    }

    public void setSortString(String sortString) {
        this.sortString = sortString;
    }

    public String getIsResetPwd() {
        return isResetPwd;
    }

    public void setIsResetPwd(String isResetPwd) {
        this.isResetPwd = isResetPwd;
    }

    public String getEmailSign() {
        return emailSign;
    }

    public void setEmailSign(String emailSign) {
        this.emailSign = emailSign;
    }

    @Override
    public String toString() {
        return "PartnersMessageDto{" +
                "mid=" + mid +
                ", mtype='" + mtype + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", puid='" + puid + '\'' +
                ", content='" + content + '\'' +
                ", vcode='" + vcode + '\'' +
                ", areacode='" + areacode + '\'' +
                ", account='" + account + '\'' +
                ", results='" + results + '\'' +
                ", sendtime=" + sendtime +
                ", isused=" + isused +
                ", finishtime=" + finishtime +
                ", dateline=" + dateline +
                ", ip='" + ip + '\'' +
                ", status=" + status +
                ", channelCode='" + channelCode + '\'' +
                ", isTxi=" + isTxi +
                ", pageSize=" + pageSize +
                ", pageIndex=" + pageIndex +
                ", sortString='" + sortString + '\'' +
                '}';
    }
}