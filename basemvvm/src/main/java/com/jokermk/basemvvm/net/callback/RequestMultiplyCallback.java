package com.jokermk.basemvvm.net.callback;


import com.jokermk.basemvvm.net.exception.BaseException;

/**
 * @Author: Joker
 * @Date: 2019/7/23 15:04
 * @Description:
 */
public interface RequestMultiplyCallback<T> extends RequestCallback<T> {

    void onFail(BaseException e);
}
