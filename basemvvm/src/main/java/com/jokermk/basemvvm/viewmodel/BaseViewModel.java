package com.jokermk.basemvvm.viewmodel;


import com.jokermk.basemvvm.event.BaseActionEvent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @Author: Joker
 * @Date: 2019/7/23 9:51
 * @Description:
 */
public class BaseViewModel extends ViewModel implements IBaseViewModelAction {

    private MutableLiveData<BaseActionEvent> actionLiveData;

    public MutableLiveData<BaseActionEvent> getActionLiveData() {
        return actionLiveData;
    }

    public BaseViewModel() {
        this.actionLiveData = new MutableLiveData<>();
    }

    @Override
    public void startLoading() {
        startLoading("加载中");
    }

    @Override
    public void startLoading(String message) {
        BaseActionEvent baseActionEvent = new BaseActionEvent(BaseActionEvent.SHOW_LOADING_DIALOG);
        baseActionEvent.setMessage(message);
        actionLiveData.setValue(baseActionEvent);
    }

    @Override
    public void dismissLoading() {
        actionLiveData.setValue(new BaseActionEvent(BaseActionEvent.DISMISS_LOADING_DIALOG));
    }

    @Override
    public void showToast(String message) {
        BaseActionEvent baseActionEvent = new BaseActionEvent(BaseActionEvent.SHOW_TOAST);
        baseActionEvent.setMessage(message);
        actionLiveData.setValue(baseActionEvent);
    }

    @Override
    public void finish() {
        actionLiveData.setValue(new BaseActionEvent(BaseActionEvent.FINISH));
    }

    @Override
    public void finishWithResultOk() {
        actionLiveData.setValue(new BaseActionEvent(BaseActionEvent.FINISH_WITH_RESULT_OK));
    }

    @Override
    public void showError(int errorCode,String message) {
        BaseActionEvent baseActionEvent = new BaseActionEvent(BaseActionEvent.SHOW_ERROR);
        baseActionEvent.setCode(errorCode);
        baseActionEvent.setMessage(message);
        actionLiveData.setValue(baseActionEvent);
    }


}
