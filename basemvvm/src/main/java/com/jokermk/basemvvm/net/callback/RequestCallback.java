package com.jokermk.basemvvm.net.callback;

/**
 * @Author: Joker
 * @Date: 2019/7/23 15:03
 * @Description:
 */
public interface RequestCallback<T> {
    void onSuccess(T t);
}
