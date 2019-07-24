package com.jokermk.basemvvm.net;


import com.jokermk.basemvvm.net.callback.RequestCallback;
import com.jokermk.basemvvm.net.callback.RequestMultiplyCallback;
import com.jokermk.basemvvm.net.exception.BaseException;
import com.jokermk.basemvvm.viewmodel.IBaseViewModelAction;

import io.reactivex.observers.DisposableObserver;

/**
 * @Author: Joker
 * @Date: 2019/7/23 15:00
 * @Description:
 */
public class BaseSubscriber<T> extends DisposableObserver<T> {
    private RequestCallback<T> requestCallback;
    private IBaseViewModelAction iBaseViewModelAction;
    BaseSubscriber(RequestCallback<T> requestCallback,IBaseViewModelAction iBaseViewModelAction) {
        this.requestCallback = requestCallback;
        this.iBaseViewModelAction = iBaseViewModelAction;
    }
    @Override
    public void onNext(T t) {
        if (requestCallback != null) {
            requestCallback.onSuccess(t);
        }
    }

    @Override
    public void onError(Throwable e) {

        if (requestCallback instanceof RequestMultiplyCallback) {
            RequestMultiplyCallback callback = (RequestMultiplyCallback) requestCallback;
            if (e instanceof BaseException) {
                callback.onFail((BaseException) e);
            } else {
                callback.onFail(new BaseException(HttpConfig.CODE_UNKNOWN, e.getMessage()));
            }
        }else {
            if (e instanceof BaseException) {
                iBaseViewModelAction.showError(((BaseException) e).getErrorCode(),e.getMessage());
            }else {
                iBaseViewModelAction.showError(HttpConfig.CODE_UNKNOWN,e.getMessage());
            }

        }
    }

    @Override
    public void onComplete() {

    }
}
