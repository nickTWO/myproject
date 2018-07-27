package com.hyt.myproject.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PartnerAccountDto implements Serializable {
    private static final long serialVersionUID = -200929888124957669L;
    private String partnerId;

    private BigDecimal balance;

    private BigDecimal freezeBalance;

    private String hashCode;

    private Date lastTrade;

    private String lastSign;

    private String currency;

    private String balanceString;

    private String freezeBalanceString;

    public PartnerAccountDto () {

    }

    public PartnerAccountDto(String partnerId){
        this.partnerId = partnerId;
    }

    //以下为非数据表字段

    private String tableName;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId == null ? null : partnerId.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFreezeBalance() {
        return freezeBalance;
    }

    public void setFreezeBalance(BigDecimal freezeBalance) {
        this.freezeBalance = freezeBalance;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode == null ? null : hashCode.trim();
    }

    public Date getLastTrade() {
        return lastTrade;
    }

    public void setLastTrade(Date lastTrade) {
        this.lastTrade = lastTrade;
    }

    public String getLastSign() {
        return lastSign;
    }

    public void setLastSign(String lastSign) {
        this.lastSign = lastSign == null ? null : lastSign.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBalanceString() {
        return balanceString;
    }

    public void setBalanceString(String balanceString) {
        this.balanceString = balanceString;
    }

    public String getFreezeBalanceString() {
        return freezeBalanceString;
    }

    public void setFreezeBalanceString(String freezeBalanceString) {
        this.freezeBalanceString = freezeBalanceString;
    }
}