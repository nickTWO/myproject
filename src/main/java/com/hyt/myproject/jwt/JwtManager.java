package com.hyt.myproject.jwt;


import com.hyt.myproject.exception.ApiException;

/**
 * Created by pc on 2018/1/12.
 */
public interface JwtManager {
    String createJwtToken(String uid, String subject, String issuer, String secretKey);

    JwtToken verifyJwtToken(String secretKey, String token) throws ApiException;
}
