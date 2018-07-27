package com.hyt.myproject.common.constant;

import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

public class PayCloudConstants {

    /** 当前版本号 **/
    public static String API_VERSION = "V4.0.1";

    /** 老版本号 **/
    public static String API_VERSION_V35 = "V3.5.0";

    /** 项目秘钥 **/
    public static String SECRETKEY = "DB92F06194F8196F3D60849E165ED2B5";

    /** UTF-8字符集 **/
    public static final String CHARSET_UTF8 = "UTF-8";

    /** HTTP请求相关 **/
    public static final String SIGN_METHOD_MD5 = "MD5";
    public static final String SIGN_METHOD_AES = "AES";
    public static final String SIGN_METHOD_RSA = "RSA";
    public static final String SIGN_SHA256RSA_ALGORITHMS      = "SHA256WithRSA";

    ////////////////////////down////////////////
    public static boolean ISDEBUG = true;

    /** 判断是否为历史表 **/
    public static final String HISTORY = "history";

    //JEDIS IP
    public static String JEDISIP = "127.0.0.1";//"192.168.1.6";
    public static int JEDISPORT = 6379;//端口
    public static String CONFIGSET = "system";
    public static String AUTHCODE = "authcode_";

    //初始化数据库连接用
    public static WebApplicationContext applicationContext;
    public static ServletContext sc;
    public static ServletContext sqeEvent;

    //前台用户session
    public static String PARTNER_SESSION = "partnerSessionInfo";
    public static String PARTNER_SESSION_MENUS = "partnerSessionMenus";
    public static String PARTNER_REDIS_CACHE = "partnerCacheUUID_";
    public static String SECRET_SETTING_STATUS = "secret_setting_status";

    //后台用户session
    public static String SYSADMIN_SESSION = "sessionAdminUserInfo";
    public static String SYSADMIN_REDIS_CACHE = "adminCacheUUID_";


//    日志类型[TYPE_PARTNER_HANDLE, TYPE_SYSADMIN_HANDLE, TYPE_GATEWAY_HANDLE, TYPE_TASK_HANDLE]
    public static String TYPE_PARTNER_HANDLE = "TYPE_PARTNER_HANDLE";
    public static String TYPE_SYSADMIN_HANDLE = "TYPE_SYSADMIN_HANDLE";
    public static String TYPE_GATEWAY_HANDLE = "TYPE_GATEWAY_HANDLE";
    public static String TYPE_TASK_HANDLE = "TYPE_TASK_HANDLE";

    //=================安全策略常量=================
    public static String SAFETY_POLICY = "SAFETY_POLICY";
    public static String SAFETY_POLICY_LOGIN = "SAFETY_POLICY_LOGIN";
    public static String SAFETY_POLICY_SET = "SAFETY_POLICY_SET";
    public static String SAFETY_POLICY_MENTION = "SAFETY_POLICY_MENTION";
    //=================安全策略常量=================

    public static String API_TEST_PARAMS = "api_test_params"; // - 接口测试参数
    public static String API_SMS_PARAMS = "api_sms_params"; // - 短信接口参数
    public static String API_SMS_PARAMS_ZH = "api_sms_params_zh"; // - 短信接口参数
    public static String API_EMAIL_PARAMS = "api_email_params"; //- 邮箱接口参数

    public static String TRANSFER_ROUTE_RULE_SERVICE = "transferRouteRuleService";

    public static int PAY_OUT = 1;    // 下发网关
    public static int PAY_IN = 0;     // 支付网关

    public static String[] HOST_NAME = {"H59", "H51", "H77"}; //主机

    /** 支持交易接口 */
    public static String API_NAME                                  =  "payment|transfer|query|notify|billing|gateway";
    public static String SERVICE_NAME_LIST                         =  "PTY_ONLINE_PAY|PTY_ONLINE_RECHARGE|PTY_TRADE_QUERY|PTY_TRANSFER_PAY_CARD|PTY_TRANSFER_PAY_QUERY|PTY_ACCOUNT_BALANCE|PTY_PAYMENT_TO_CARD|PTY_PAYMENT_QUERY";
    public static String PTY_ONLINE_PAY                            =  "PTY_ONLINE_PAY"; //在线支付
    public static String PTY_ONLINE_RECHARGE                       =  "PTY_ONLINE_RECHARGE"; //在线充值
    public static String PTY_TRADE_QUERY                           =  "PTY_TRADE_QUERY"; //交易查询
    public static String PTY_TRANSFER_PAY_CARD                     =  "PTY_TRANSFER_PAY_CARD"; //转账到卡
    public static String PTY_TRANSFER_PAY_QUERY                    =  "PTY_TRANSFER_PAY_QUERY"; //代付查询
    public static String PTY_ACCOUNT_BALANCE                       =  "PTY_ACCOUNT_BALANCE"; //账户余额查询
    public static String PTY_TRANS_TO_ACCOUNT                      =  "PTY_TRANS_TO_ACCOUNT"; //转账到户

    public static String PTY_PAYMENT_TO_CARD                       =  "PTY_PAYMENT_TO_CARD"; //转账到卡 兼容V3.5版本
    public static String PTY_PAYMENT_QUERY                         =  "PTY_PAYMENT_QUERY"; //转账到卡 兼容V3.5版本


    /** 支持消息队列网关 */
    public static String GATEWAY_LIST_FOR_AYSNC                    = "JYTPAY|JYTPAYB2C|WORTPAY|CHANPAY|GOPAY|YEMADAIPAY";


    //================================================================================================================//
    //================================================== 产品业务类型 ==================================================//
    //================================================================================================================//
    /** 交易: 1000 在线支付 */
    public static int TYPE_ONLINE_PAY                           = 1000;

    /** 交易: 1001 在线充值 */
    public static int TYPE_ONLINE_RECHARGE                      = 1001;

    /** 提现: 2000 转账到户 - 转出方 */
    public static int TYPE_TRANS_FROM_ACCOUNT                   = 2000;

    /** 提现: 2001 转账到户 - 转入方 */
    public static int TYPE_TRANS_TO_ACCOUNT                     = 2001;

    /** 提现: 2002 转账到卡 */
    public static int TYPE_TRANS_TO_CARD                        = 2002;

    /** 提现: 2005 分润结算 加款 */
    public static int TYPE_PROFIT_TO_ACCOUNT                    = 2005;

    /** 提现: 2003 跨境提现 */
    public static int TYPE_TRANS_TO_OUTSIDE                     = 2003;

    /** 退款: 3000-交易退款 */
    public static int TYPE_REFUND_FROM_TRADE                    = 3000;

    /** 退款: 4000-代付退款 */
    public static int TYPE_REFUND_FROM_MENTION                  = 4000;

    /** 调账: 4001-调账加款 {内部专用 对外全部过滤} */
    public static int TYPE_ADJUST_ADD_TO_MERCHANT               = 4001;

    /** 调账: 4002-调账扣款 {内部专用 对外全部过滤} */
    public static int TYPE_ADJUST_SUB_FROM_MERCHANT             = 4002;

    /** 冲正: 5001-冲正加款 */
    public static int TYPE_CORRECT_ADD_TO_MERCHANT              = 5001;

    /** 冲正: 5002-冲正扣款 */
    public static int TYPE_CORRECT_SUB_FROM_MERCHANT            = 5002;

    //================================================================================================================//
    //================================================= 消息队列TOPIC =================================================//
    //================================================================================================================//
    /** 1、交易 - 加款消息 **/
    public static String TOPIC_TRADE_PAY_WAIT_SETTLE           = "TOPIC_TRADE_PAY_WAIT_SETTLE";

    /** 2、交易 - 通知消息 **/
    public static String TOPIC_TRADE_PAY_WAIT_NOTIFY           = "TOPIC_TRADE_PAY_WAIT_NOTIFY";

    /** 0、代付 - 等待退款 ， 代付退款消息**/
    public static String TOPIC_TRANSFER_PAY_WAIT_REFUND        = "TOPIC_TRANSFER_PAY_WAIT_REFUND";

    /** 1、代付 - 等待扣款 **/
    public static String TOPIC_TRANSFER_PAY_WAIT_DEBIT         = "TOPIC_TRANSFER_PAY_WAIT_DEBIT";

    /** 2、代付 - 提交至银行 , 代付上送消息**/
    public static String TOPIC_TRANSFER_PAY_WAIT_TO_BANK       = "TOPIC_TRANSFER_PAY_WAIT_TO_BANK";

    /** 3、代付 - 通知类消息 **/
    public static String TOPIC_TRANSFER_PAY_WAIT_NOTIFY        = "TOPIC_TRANSFER_PAY_WAIT_NOTIFY";

}
