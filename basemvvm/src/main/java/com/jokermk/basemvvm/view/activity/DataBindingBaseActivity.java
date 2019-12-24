package com.jokermk.basemvvm.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.jokermk.basemvvm.event.BaseActionEvent;
import com.jokermk.basemvvm.view.AppManager;
import com.jokermk.basemvvm.view.ScreenUtils;
import com.jokermk.basemvvm.viewmodel.BaseAndroidViewModel;
import com.jokermk.basemvvm.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

/**
 * @Author: Joker
 * @Date: 2019/7/23 16:57
 * @Description:
 */
public abstract class DataBindingBaseActivity<T extends ViewDataBinding> extends AppCompatActivity {
    private boolean isNormal;
    private ProgressDialog loadingDialog;
    protected T viewDataBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在界面未初始化之前调用的初始化窗口
        initWindows();
        isNormal =  initArgs(getIntent().getExtras());
        if (isNormal) {
            AppManager.getAppManager().addActivity(this);
            //得到界面id并设置上去
         viewDataBinding = DataBindingUtil.setContentView(this, getContentLayoutId());
         viewDataBinding.setLifecycleOwner(this);
//           StatusBarUtil.immersive(this);
//           StatusBarUtil.darkMode(this);
            initViewModelEvent();
            initWidget();
            initData();
        }else {
            finish();
        }
    }

    /**
     * 初始化窗口
     */
    protected void initWindows(){

    }
    /**
     * 初始化相关参数
     * @param bundle  参数Bundle
     * @return 如果参数正确返回true,错误返回false
     */
    protected boolean initArgs(Bundle bundle) {
        return true;
    }
    /**
     *  得到当前界面的资源文件的id
     * @return 资源文件id
     */
    protected abstract int getContentLayoutId();
    /**
     * 初始化控件
     */
    protected void initWidget() {

    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (ScreenUtils.isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    private void initViewModelEvent() {
        List<ViewModel> viewModelList = initViewModelList();
        if (viewModelList != null && viewModelList.size() > 0) {
            observeEvent(viewModelList);
        } else {
            ViewModel viewModel = initViewModel();
            if (viewModel != null) {
                List<ViewModel> modelList = new ArrayList<>();
                modelList.add(viewModel);
                observeEvent(modelList);
            }
        }
    }
    protected ViewModel initViewModel() {
        return null;
    }

    protected List<ViewModel> initViewModelList() {
        return null;
    }

    private void observeEvent(List<ViewModel> viewModelList) {
        for (ViewModel viewModel : viewModelList) {
            if (viewModel instanceof BaseViewModel) {
                BaseViewModel viewModelAction = (BaseViewModel) viewModel;
                viewModelAction.getActionLiveData().observe(this, new Observer<BaseActionEvent>() {
                    @Override
                    public void onChanged(BaseActionEvent baseActionEvent) {
                        if (baseActionEvent != null) {
                            switch (baseActionEvent.getAction()) {
                                case BaseActionEvent.SHOW_LOADING_DIALOG: {
                                    startLoading(baseActionEvent.getMessage());
                                    break;
                                }
                                case BaseActionEvent.DISMISS_LOADING_DIALOG: {
                                    dismissLoading();
                                    break;
                                }
                                case BaseActionEvent.SHOW_TOAST: {
                                    showToast(baseActionEvent.getMessage());
                                    break;
                                }
                                case BaseActionEvent.FINISH: {
                                    finish();
                                    break;
                                }
                                case BaseActionEvent.FINISH_WITH_RESULT_OK: {
                                    setResult(RESULT_OK);
                                    finish();
                                    break;
                                }
                                case BaseActionEvent.SHOW_ERROR:{
                                    showError(baseActionEvent.getCode(),baseActionEvent.getMessage());
                                }
                            }
                        }
                    }
                });
            }


            if (viewModel instanceof BaseAndroidViewModel) {
                BaseAndroidViewModel viewModelAction = (BaseAndroidViewModel) viewModel;
                viewModelAction.getActionLiveData().observe(this, new Observer<BaseActionEvent>() {
                    @Override
                    public void onChanged(BaseActionEvent baseActionEvent) {
                        if (baseActionEvent != null) {
                            switch (baseActionEvent.getAction()) {
                                case BaseActionEvent.SHOW_LOADING_DIALOG: {
                                    startLoading(baseActionEvent.getMessage());
                                    break;
                                }
                                case BaseActionEvent.DISMISS_LOADING_DIALOG: {
                                    dismissLoading();
                                    break;
                                }
                                case BaseActionEvent.SHOW_TOAST: {
                                    showToast(baseActionEvent.getMessage());
                                    break;
                                }
                                case BaseActionEvent.FINISH: {
                                    finish();
                                    break;
                                }
                                case BaseActionEvent.FINISH_WITH_RESULT_OK: {
                                    setResult(RESULT_OK);
                                    finish();
                                    break;
                                }
                                case BaseActionEvent.SHOW_ERROR:{
                                    showError(baseActionEvent.getCode(),baseActionEvent.getMessage());
                                }
                            }
                        }
                    }
                });
            }



        }
    }
    protected  void showError(int code, String message) {
        Toast.makeText(this,"error:" +code+ message, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    protected void startLoading(String message) {
        if (loadingDialog == null) {
            loadingDialog = new ProgressDialog(this);
            loadingDialog.setCancelable(false);
            loadingDialog.setCanceledOnTouchOutside(false);
        }
        loadingDialog.setTitle(message);
        loadingDialog.show();
    }

    protected void dismissLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    public <T extends ViewModel> T  getViewModel(Class<T> modelClass) {
        return ViewModelProviders.of(this).get(modelClass);
    }


}
