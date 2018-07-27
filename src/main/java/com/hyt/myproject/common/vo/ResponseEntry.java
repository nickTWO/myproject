package com.hyt.myproject.common.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author Echo_ayalamih
 * @description desc.
 * @packagename com.paycloudx.common.vo
 * @date 21:14, 22/11/2017
 */
public class ResponseEntry<T> implements Serializable {
    private static final long serialVersionUID = -3563304186099988196L;

    /**
     * 错误码
     */
    @JSONField(name = "code")
    private String code;

    /**
     * 错误信息
     */
    @JSONField(name = "message")
    private String message;

    /**
     * 错误码签名值
     */
    @JSONField(name = "sign")
    private String sign;

    /**
     * 返回数据
     */
    @JSONField(name = "resp_data")
    private T entry;

    @JSONField(name = "token")
    private String token;

    public ResponseEntry() {
    }

    private ResponseEntry(String code, String message, String sign, T entry) {
        this.code = code;
        this.message = message;
        this.sign = sign;
        this.entry = entry;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getEntry() {
        return entry;
    }

    public void setEntry(T entry) {
        this.entry = entry;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public static class Builder<T> {
        private String code;
        private String sign;
        private String message;
        private T entry;

        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public Builder setSign(String sign) {
            this.sign = sign;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setEntry(T entry) {
            this.entry = entry;
            return this;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder(16);
            result.append("code=");
            result.append(code);
            result.append("&message=");
            result.append(message);
            if (this.entry != null) {
                result.append("&resp_data=" + JSON.toJSONString(entry));
            }
            return result.toString();

        }

        public ResponseEntry build() {
            return new ResponseEntry(this.code, this.message, this.sign, this.entry);
        }
    }
}
