package com.hyt.myproject.exception;

/**
 * @author Echo_ayalamih
 * @description desc.
 * @packagename com.paycloudx.backend.exception
 * @date 15:20, 18/12/2017
 */
public class ApiException extends Exception {
    private String code;

    private String message;

    private Throwable cause;

    public ApiException() {
        super();
    }

    public ApiException(String code, String message) {
        this(message);
        this.code = code;
    }

    public ApiException(String code, String message, Throwable cause) {
        this(message, cause);
        this.code = code;
    }

    public ApiException(String message) {
        super(message);
        this.message = message;
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
        this. message = message;
        this.cause = cause;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }
}
