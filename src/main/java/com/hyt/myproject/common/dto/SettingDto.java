package com.hyt.myproject.common.dto;

import java.io.Serializable;

public class SettingDto implements Serializable{

    private static final long serialVersionUID = 7737584098943478637L;

    private String key;

    private String value;

    public SettingDto(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    @Override
    public String toString() {
        return "SettingDto{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}