package com.jokermk.basemvvm.viewmodel;

/**
 * @Author: Joker
 * @Date: 2019/7/23 9:50
 * @Description:
 */
public interface IBaseViewModelAction {
    void startLoading();

    void startLoading(String message);

    void dismissLoading();

    void showToast(String message);

    void finish();

    void finishWithResultOk();

    void showError(int errorCode,String message);

}
