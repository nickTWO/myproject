package com.hyt.myproject.common.utils;

import com.rogoman.easyauth.Authenticator;
import com.rogoman.easyauth.TimeAuthenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by geek on 2017/6/15.
 */
public class GoogleManger {

    private static final Logger logger = LoggerFactory.getLogger(GoogleManger.class);

    /**
     * @Description: 获取GoogleSecret
     * @author: geek
     * @datetime: 2017/6/6 上午9:33
     * @returns:
     */
    public static String getGoogleSecret() {
        try {
//            Authenticator auth = new TimeAuthenticator();
            String secret = Authenticator.generateKey();
            return secret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 验证Google身份
     * 2014-1-17 下午4:18:12
     * @param secret
     * @param googlecode
     * @return
     */
    public static boolean checkGoogleCode(String secret, String googlecode, String userid) {
        String googleCodeRedis = CacheManger.get("GA_" + userid + "_" + googlecode);
        if (googleCodeRedis != null && googleCodeRedis.equals("Y")) {
            logger.info("[GA验证器校验] userid=" + userid + "，googleCode=" + googlecode + ", googleCodeRedis=" + "GA_" + userid + "_" + googlecode + ", 已经使用过，缓存中有值，进行过滤!");
            return false;
        }

        Authenticator auth = new TimeAuthenticator();
        boolean isCodeValid = auth.checkCode(secret, googlecode, "");
        logger.info("[GA验证器校验] userid=" + userid + "，googleCode=" + googlecode + ", isCodeValid=" + isCodeValid);
        CacheManger.set("GA_" + userid + "_" + googlecode, "Y", 150); //写入缓存
        return isCodeValid;
    }
}
