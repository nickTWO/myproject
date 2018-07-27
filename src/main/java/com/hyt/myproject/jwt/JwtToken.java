package com.hyt.myproject.jwt;

/**
 * Created by pc on 2018/1/12.
 */
public class JwtToken {
    private String puid;

    private String loginIp;

    private Long expiredTime;

    public JwtToken() {
    }

    public JwtToken(String puid) {
        this.puid = puid;
    }

    public String getPuid() {
        return puid;
    }

    public void setPuid(String puid) {
        this.puid = puid;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setExpiredTime(Long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Long getExpiredTime() {
        return expiredTime;
    }
}
