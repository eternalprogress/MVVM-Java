package com.jokermk.basemvvm.net.exception;

/**
 * @Author: Joker
 * @Date: 2019/7/23 16:13
 * @Description:
 */
public class ServerResultException extends BaseException {
    public ServerResultException(int code, String message) {
        super(code, message);
    }

}
