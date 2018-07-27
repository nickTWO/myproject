package com.hyt.myproject.common.dto;

import java.io.Serializable;
import java.util.Date;

public class HandleLogsDto implements Serializable {

    private static final long serialVersionUID = -5698259218744964128L;
    private Integer hlid;

    private String puserid;

    private String partnerId;

    private String handleType;

    private String handleCode;

    private String orderSn;

    private String handleEvents;

    private Integer handleStatus;

    private String handleIp;

    private Date dateline;

    private String handleParams;


    public Integer getHlid() {
        return hlid;
    }

    public void setHlid(Integer hlid) {
        this.hlid = hlid;
    }

    public String getPuserid() {
        return puserid;
    }

    public void setPuserid(String puserid) {
        this.puserid = puserid == null ? null : puserid.trim();
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId == null ? null : partnerId.trim();
    }

    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType == null ? null : handleType.trim();
    }

    public String getHandleCode() {
        return handleCode;
    }

    public void setHandleCode(String handleCode) {
        this.handleCode = handleCode == null ? null : handleCode.trim();
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public String getHandleEvents() {
        return handleEvents;
    }

    public void setHandleEvents(String handleEvents) {
        this.handleEvents = handleEvents == null ? null : handleEvents.trim();
    }

    public Integer getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(Integer handleStatus) {
        this.handleStatus = handleStatus;
    }

    public String getHandleIp() {
        return handleIp;
    }

    public void setHandleIp(String handleIp) {
        this.handleIp = handleIp == null ? null : handleIp.trim();
    }

    public Date getDateline() {
        return dateline;
    }

    public void setDateline(Date dateline) {
        this.dateline = dateline;
    }

    public String getHandleParams() {
        return handleParams;
    }

    public void setHandleParams(String handleParams) {
        this.handleParams = handleParams == null ? null : handleParams.trim();
    }

    @Override
    public String toString() {
        return "HandleLogsDto{" +
                "hlid=" + hlid +
                ", puserid='" + puserid + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", handleType='" + handleType + '\'' +
                ", handleCode='" + handleCode + '\'' +
                ", orderSn='" + orderSn + '\'' +
                ", handleEvents='" + handleEvents + '\'' +
                ", handleStatus=" + handleStatus +
                ", handleIp='" + handleIp + '\'' +
                ", dateline=" + dateline +
                ", handleParams='" + handleParams + '\'' +
                '}';
    }
}