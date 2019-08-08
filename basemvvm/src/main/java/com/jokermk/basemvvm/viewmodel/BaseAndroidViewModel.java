package com.jokermk.basemvvm.viewmodel;

import android.app.Application;

import com.jokermk.basemvvm.event.BaseActionEvent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/**
 * @Author: Joker
 * @Date: 2019/7/23 9:51
 * @Description:
 */
public class BaseAndroidViewModel extends AndroidViewModel implements IBaseViewModelAction {

    private MutableLiveData<BaseActionEvent> actionLiveData;
    protected Application application;

    public BaseAndroidViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        this.actionLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<BaseActionEvent> getActionLiveData() {
        return actionLiveData;
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
    public void showError(int errorcode,String message) {
        BaseActionEvent baseActionEvent = new BaseActionEvent(BaseActionEvent.SHOW_ERROR);
        baseActionEvent.setCode(errorcode);
        baseActionEvent.setMessage(message);
        actionLiveData.setValue(baseActionEvent);
    }


}
