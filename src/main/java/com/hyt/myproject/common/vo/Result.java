package com.hyt.myproject.common.vo;

import java.io.Serializable;

/**
 * @author Echo_ayalamih
 * @description desc.
 * @packagename com.paycloudx.common.vo
 * @date 16:25, 22/11/2017
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 4520520901674193502L;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 错误代码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 返回码
     */
    private String returnCode;

    /**
     * 返回信息
     */
    private String returnMessage;

    private T entry;

    public void setEntry(T entry) {
        this.entry = entry;
    }

    public T getEntry() {
        return entry;
    }

    public Result() {
        this.success = false;
    }

    public Result(boolean isSuccess) {
        this.success = isSuccess;
    }

    public Result(boolean isSuccess, String errorCode, String errorMessage) {
        this.success = isSuccess;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
}
