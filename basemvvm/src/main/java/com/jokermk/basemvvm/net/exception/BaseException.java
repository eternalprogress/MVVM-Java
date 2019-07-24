package com.jokermk.basemvvm.net.exception;


import com.jokermk.basemvvm.net.HttpConfig;

/**
 * @Author: Joker
 * @Date: 2019/7/23 15:05
 * @Description:
 */
public class BaseException extends RuntimeException {
    private int errorCode = HttpConfig.CODE_UNKNOWN;

    public BaseException() {
    }

    public BaseException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
