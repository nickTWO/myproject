package com.hyt.myproject.common.constant;

/**
 * Created by mark on 05/06/2017.
 */
public enum GatewayEnum {

    /** 未知定义 **/
    UNKNOW_ERROR("UNKNOW_ERROR", "未知错误"),
    UNKNOW_EXCEPTION_ERROR("UNKNOW_EXCEPTION_ERROR", "未知异常错误"),
    UNKNOWN_GATEWAY_INTERFACE("UNKNOWN_GATEWAY_INTERFACE", "未知的网关接口地址"),
    UNKNOWN_GATEWAY_SERVICE_NAME("UNKNOWN_GATEWAY_SERVICE_NAME", "未知的网关接口服务名"),

    /** 业务定义 **/
    PARTNER_IS_NOT_EXISTS("PARTNER_IS_NOT_EXISTS", "商户账号不存在或未开通，请联系业务人员"),
    PARTNER_HAS_BEEN_BLOCKED("PARTNER_HAS_BEEN_BLOCKED", "商户账号被冻结，请联系业务人员"),
    PARTNER_NOT_HAVE_PRODUCT("PARTNER_NOT_HAVE_PRODUCT", "商户产品权限未开通或被关闭，请联系业务人员"),
    PARTNER_NOT_HAVE_PRODUCT_AUTH("PARTNER_NOT_HAVE_PRODUCT_AUTH", "产品权限未开启或被关闭，请联系业务人员"),
    PARTNER_TRADE_QUOTA_HAS_LIMIT("PARTNER_TRADE_QUOTA_HAS_LIMIT", "商户日交易总额超限，请联系业务人员"),
    PARTNER_NOT_HAVE_API_PRIVILEGE("PARTNER_NOT_HAVE_API_PRIVILEGE", "商户API调用权限未开启或被关闭，请联系业务人员"),
    PARTNER_ACCOUNT_BALANCE_PRIVILEGE("PARTNER_ACCOUNT_BALANCE_PRIVILEGE", "商户账户余额不足"),

    /** 渠道定义 **/
    GATEWAY_CHANNEL_RESPONSE_FAILURE("GATEWAY_CHANNEL_RESPONSE_FAILURE", "网关渠道请求响应失败"),
    GATEWAY_CHANNEL_RESPONSE_SUCCESS("GATEWAY_CHANNEL_RESPONSE_SUCCESS", "网关渠道请求响应成功"),
    GATEWAY_CHANNEL_RESPONSE_EXCEPTION("GATEWAY_CHANNEL_RESPONSE_EXCEPTION", "网关渠道请求响应异常"),
    GATEWAY_CHANNEL_LIST_NOT_FOUND("GATEWAY_CHANNEL_LIST_NOT_FOUND", "网关渠道列表信息不存在"),
    GATEWAY_CHANNEL_ROUTE_RULE_NOT_FOUND("GATEWAY_CHANNEL_ROUTE_RULE_NOT_FOUND", "网关渠道路由规则未找到"),
    GATEWAY_CHANNEL_NOT_FOUND("GATEWAY_CHANNEL_NOT_FOUND", "未找到可用网关渠道"),

    TRADE_ORDER_RESPONE_ERROR("TRADE_ORDER_RESPONE_ERROR", "订单请求响应失败，请联系业务人员协助检查"),

    /** 参数定义 **/
    ILLEGAL_SIGN_VALUE("ILLEGAL_SIGN_VALUE", "签名值不匹配"),
    ILLEGAL_IP_ACCESS_FORBIDEN("ILLEGAL_IP_ACCESS_FORBIDEN", "请求访问受限，IP未在白名单内"),
    ILLEGAL_ORDER_PARAMS_NULL("ILLEGAL_ORDER_PARAMS_NULL", "订单参数不能为空，请参阅接口文档"),
    ILLEGAL_OUT_TRADE_NO_IS_EXISTS("OUT_TRADE_NO_IS_EXISTS", "商户订单号已存在，请勿重复提交"),
    ILLEGAL_OUT_TRADE_NOT_EXISTS("ILLEGAL_OUT_TRADE_NOT_EXISTS", "商户订单号不存在"),
    ILLEGAL_ORDER_PARAMS_LENGTH("ILLEGAL_ORDER_FIELD_LENGTH", "订单参数长度不合法，请参阅接口文档"),
    ILLEGAL_ORDER_PARAMS_FORMAT("ILLEGAL_ORDER_PARAMS_FORMAT", "订单参数格式不合法，请参阅接口文档"),
    ILLEGAL_BIZ_CONTENT_ERROR("ILLEGAL_BIZ_CONTENT_ERROR", "代付主体参数错误"),
    ILLEGAL_CARD_BIN_ERROR("ILLEGAL_CARD_BIN_ERROR", "所提交的银行卡BIN异常"),
    INVALID_TOTAL_FEE_LIMIT("INVALID_TOTAL_FEE_LIMIT", "单笔提现金额超限"),
    INVALID_IP_ACCESS_FORBIDEN("INVALID_IP_ACCESS_FORBIDEN", "用户IP访问受限制，未在白名单内"),
    INVALID_BANK_CODE_UNAVAILABLE("INVALID_BANK_CODE_UNAVAILABLE", "渠道银行维护中, 暂停出款服务, 恢复时间等待官方通知"),

    /** 风控定义 **/
    VIOLATE_DAY_MAXCOUNT("VIOLATE_DAY_MAXCOUNT", "商家每日最大交易笔数"),
    VIOLATE_MONTH_MAXCOUNT("VIOLATE_MONTH_MAXCOUNT", "商家每月最大交易金额"),
    VIOLATE_SING_MAXAMOUNT("VIOLATE_SING_MAXAMOUNT", "违反风控规则：商家单笔最大金额"),

    /** 成功定义 **/
    RESPONSE_FAILURE("RESPONSE_FAILURE", "请求响应失败"),
    RESPONSE_SUCCESS("RESPONSE_SUCCESS", "请求响应成功");

    private String respCode;
    private String respMsg;

    GatewayEnum(String respCode, String respMsg) {
        this.respCode = respCode;
        this.respMsg = respMsg;
    }

    public static String getGatewayEnumValue(String respCode) {
        for (GatewayEnum GatewayEnum : GatewayEnum.values()) {
            if (GatewayEnum.getRespCode().equals(respCode)) {
                return GatewayEnum.getRespMsg();
            }
        }
        return null;
    }

    public static GatewayEnum getGatewayEnum(String respCode) {
        for (GatewayEnum GatewayEnum : GatewayEnum.values()) {
            if (respCode.equals(GatewayEnum.getRespCode())) {
                return GatewayEnum;
            }
        }
        return null;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }
}
