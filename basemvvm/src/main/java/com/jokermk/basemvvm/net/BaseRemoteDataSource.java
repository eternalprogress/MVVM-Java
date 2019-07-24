package com.jokermk.basemvvm.net;


import com.jokermk.basemvvm.net.callback.RequestCallback;
import com.jokermk.basemvvm.net.exception.ServerResultException;
import com.jokermk.basemvvm.net.model.BaseResponse;
import com.jokermk.basemvvm.viewmodel.IBaseViewModelAction;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author: Joker
 * @Date: 2019/7/23 15:34
 * @Description:
 */
public class BaseRemoteDataSource {
    private IBaseViewModelAction baseViewModelAction;
    public BaseRemoteDataSource(IBaseViewModelAction baseViewModelAction) {
        this.baseViewModelAction = baseViewModelAction;
    }

    protected <T> T getService(Class<T> clz) {
        return getService(clz, HttpConfig.BASE_URL);
    }
    protected <T> T getService(Class<T> clz, String host) {
        return RetrofitManager.getInstance().getService(clz, host);
    }

    protected <T> void execute(Observable observable, RequestCallback<T> callback) {
        execute(observable, new BaseSubscriber<>(callback,baseViewModelAction), true);
    }


    protected <T> void executeQuietly(Observable observable, RequestCallback<T> callback) {
        execute(observable, new BaseSubscriber<>(callback,baseViewModelAction), false);
    }

    private <T> void execute(Observable<BaseResponse<T>> observable, Observer<T> observer, final boolean isShow) {
        observable.subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (isShow) {
                            showLoading();
                        }
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (isShow) {
                            dismissLoading();
                        }
                    }
                }).flatMap(new Function<BaseResponse<T>, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(BaseResponse<T> t) throws Exception {
                if (t.getCode() == HttpConfig.RESULT_OK) {
                    return createData(t.getData());
                }else {
                    throw new ServerResultException(t.getCode(),t.getMsg());
                }
            }
        }).subscribe(observer);
    }


    private <T> Observable<T> createData(final T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
    }

    private void dismissLoading() {
        baseViewModelAction.dismissLoading();
    }

    private void showLoading() {
        baseViewModelAction.startLoading();
    }

}
