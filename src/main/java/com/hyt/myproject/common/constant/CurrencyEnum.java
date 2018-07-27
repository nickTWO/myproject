package com.hyt.myproject.common.constant;

/**
 * Created by pc on 2018/1/9.
 */
public enum CurrencyEnum {

    CNY("人民币"), BTC("比特币"), ETH("以太币"), USDT("泰达币");

    private String chineseDesc;

    CurrencyEnum(String chineseDesc) {
        this.chineseDesc = chineseDesc;
    }

    public String getChineseDesc() {
        return this.chineseDesc;
    }
}
