//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hyt.myproject.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PcdToken implements Serializable {
    private static final long serialVersionUID = 7247834865453847855L;
    private Integer id;
    private String uid;
    private String secretKey;
    private String subject;
    private String loginIp;
    private Long startTime;
    private Long expiredTime;
    private String issuer;

    public PcdToken id(Integer id) {
        this.id = id;
        return this;
    }

    public PcdToken uid(String uid) {
        this.uid = uid;
        return this;
    }

    public PcdToken secretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public PcdToken subject(String subject) {
        this.subject = subject;
        return this;
    }

    public PcdToken loginIp(String loginIp) {
        this.loginIp = loginIp;
        return this;
    }

    public PcdToken startTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    public PcdToken expiredTime(Long expiredTime) {
        this.expiredTime = expiredTime;
        return this;
    }

    public PcdToken issuer(String issuer) {
        this.issuer = issuer;
        return this;
    }




}
