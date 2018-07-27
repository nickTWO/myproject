package com.hyt.myproject.jwt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.hyt.myproject.common.vo.ResponseFormat;
import com.hyt.myproject.exception.ApiException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc on 2018/1/12.
 */
@Component
public class JwtManagerImpl implements JwtManager {
    // 15min
    private static final Long expire = 900000L;

    //测试
//    private static final Long expire = 180000L;

    private static final Logger logger = LoggerFactory.getLogger(JwtManagerImpl.class);

    @Override
    public String createJwtToken(String uid, String subject, String issuer, String secretKey) {
        Map<String, Object> header = new HashMap(2);
        header.put("typ", "JWT");
        header.put("alg", "HS512");

        long start = System.currentTimeMillis();
        return Jwts.builder()
                .setAudience(uid)
                .setExpiration(new Date(start + expire))
                .setSubject(subject)
                .setIssuedAt(new Date(start))
                .setHeader(header)
                .setIssuer(issuer)
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }

    @Override
    public JwtToken verifyJwtToken(String secretKey, String token) throws ApiException {
        String logmsg = "[用户jwt验证事件] 事件描述={}";
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            String subject = claims.getBody().getSubject();
            JSONObject subjectJson = JSON.parseObject(subject);
            JwtToken jwtToken = new JwtToken(claims.getBody().getAudience());
            jwtToken.setPuid(claims.getBody().getAudience());
            jwtToken.setLoginIp(subjectJson.getString("loginIp"));
            jwtToken.setExpiredTime(claims.getBody().getExpiration().getTime());
            return jwtToken;
        } catch (ExpiredJwtException ex) {
            logger.error(logmsg, "[EX]Token失效");
            throw new ApiException(ResponseFormat.HTTP_NOT_AUTHORIZATION, "身份认证失效，请重新登录");
        } catch (UnsupportedJwtException ex) {
            logger.error(logmsg, "Token格式错误");
            throw new ApiException(ResponseFormat.HTTP_NOT_AUTHORIZATION, "身份认证失效，请重新登录");
        } catch (MalformedJwtException ex) {
            logger.error(logmsg, "token构建异常或已被废弃" + ex.getMessage());
            throw new ApiException(ResponseFormat.HTTP_NOT_AUTHORIZATION, "身份认证失效，请重新登录");
        } catch (SignatureException ex) {
            logger.error(logmsg, "Token签名异常");
            throw new ApiException(ResponseFormat.HTTP_NOT_AUTHORIZATION, "身份认证失效，请重新登录");
        } catch (IllegalArgumentException ex) {
            logger.error(logmsg, "Token参数异常");
            throw new ApiException(ResponseFormat.HTTP_NOT_AUTHORIZATION, "身份认证失效，请重新登录");
        } catch (Exception e) {
            logger.error(logmsg, "token验证发生未知异常");
            throw new ApiException(ResponseFormat.HTTP_NOT_AUTHORIZATION, "身份认证失效，请重新登录");
        }
    }
}
