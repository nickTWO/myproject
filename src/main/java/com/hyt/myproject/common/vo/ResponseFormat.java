
package com.hyt.myproject.common.vo;

import com.google.common.collect.Maps;
import com.hyt.myproject.common.constant.Constants;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;

/**
 * @author Echo_ayalamih
 * @description desc.
 * @packagename com.paycloudx.common.vo
 * @date 21:28, 22/11/2017
 */
public class ResponseFormat {
    /**
     * HTTP EXCEPTION CODE
     */

    public static final String HTTP_SUCCESS = "200";

    /** （错误请求） 服务器不理解请求的语法 */
    public static final String HTTP_BAD_REQUEST = "400";

    /** （未授权） 请求要求身份验证。 对于需要登录的网页，服务器可能返回此响应. */
    public static final String HTTP_NOT_AUTHORIZATION = "401";

    /** （禁止） 服务器拒绝请求 */
    public static final String HTTP_SERVER_REFUSE = "403";

    /** （方法禁用） 禁用请求中指定的方法 */
    public static final String HTTP_METHOD_NOT_ALLOWED = "405";

    public static final String HTTP_NOT_ACCEPTABLE = "406";

    public static final String HTTP_INTERNAL_SERVER_ERROR = "500";

    /**
     * SERVER EXCEPTION CODE
     */

    public static final String SERVER_SUCCESS = "200";

    public static final String SERVER_ERROR = "500";

    public static final String SERVER_RUNTIME_EXCEPTION = "1000";

    public static final String SERVER_NULL_POINT_EXCEPTION = "1001";

    public static final String SERVER_CLASS_CAST_EXCEPTION = "1002";

    public static final String SERVER_IO_EXCEPTION = "1003";

    public static final String SERVER_NO_SUCH_METHOD_EXCEPTION = "1004";

    public static final String SERVER_INDEX_OUT_BOUNDS_EXCEPTION = "1005";

    public static final String SERVER_SOCKET_EXCEPTION = "1006";

    public static final String API_EXCEPTION = "1007";

    private static Map<String, String> messageMap = Maps.newHashMap();

    static {
        //==============================================================================
        messageMap.put(HTTP_SUCCESS, "success");

        messageMap.put(HTTP_BAD_REQUEST, "Bad Request");
        messageMap.put(HTTP_NOT_AUTHORIZATION, "NotAuthorization");
        messageMap.put(HTTP_SERVER_REFUSE, "ServerRefuse");
        messageMap.put(HTTP_METHOD_NOT_ALLOWED, "Method Not Allowed");
        messageMap.put(HTTP_NOT_ACCEPTABLE, "Not Acceptable");
        messageMap.put(HTTP_INTERNAL_SERVER_ERROR, "Internal Server Error");

        //==============================================================================
        messageMap.put(SERVER_SUCCESS, "success");

        messageMap.put(SERVER_ERROR, "[服务器]异常");
        messageMap.put(SERVER_RUNTIME_EXCEPTION, "[服务器]运行时异常");
        messageMap.put(SERVER_NULL_POINT_EXCEPTION, "[服务器]空值异常");
        messageMap.put(SERVER_CLASS_CAST_EXCEPTION, "[服务器]数据类型转换异常");
        messageMap.put(SERVER_IO_EXCEPTION, "[服务器]IO异常");
        messageMap.put(SERVER_NO_SUCH_METHOD_EXCEPTION, "[服务器]未知方法异常");
        messageMap.put(SERVER_INDEX_OUT_BOUNDS_EXCEPTION, "[服务器]数组越界异常");
        messageMap.put(SERVER_SOCKET_EXCEPTION, "[服务器]网络异常");
        messageMap.put(API_EXCEPTION, "[服务器]接口请求异常");
    }

    public static <T extends Object> ResponseEntry<T> retParam(String code, T data) {
        ResponseEntry.Builder builder = new ResponseEntry.Builder();
        builder.setEntry(data);
        builder.setCode(code);
        builder.setMessage(messageMap.get(code));
        builder.setSign(DigestUtils.sha256Hex(builder.toString() + "&key=" + Constants.SECRET_KEY));
        return builder.build();
    }

    public static <T> ResponseEntry<T> retMessage(String code, String message) {
        return new ResponseEntry.Builder().setCode(code).setMessage(message).build();
    }
}
