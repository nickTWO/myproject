package com.hyt.myproject.common.constant;

/**
 * Created by pc on 2018/1/12.
 */
public class JwtConstants {
    public static final String ISSUSR = "payadmin";

    public static final String JWT_SECRET_KEY = "4606A998B443848FB88EF66931FE1BD2";

    //后台用户状态，0为锁定，1为正常
    public static final String USER_STATUS = "1";

    //timestamp访问限制，只允许差当前时间3分钟的时间戳才有资格访问接口
    public static final Long COUNT_DOWN_TIME = 180000L;
}
