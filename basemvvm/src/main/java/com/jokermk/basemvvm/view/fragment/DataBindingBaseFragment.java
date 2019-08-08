package com.jokermk.basemvvm.view.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jokermk.basemvvm.event.BaseActionEvent;
import com.jokermk.basemvvm.viewmodel.BaseAndroidViewModel;
import com.jokermk.basemvvm.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * Created by @ZhangBo
 * on @2017/12/6.
 */

public abstract class DataBindingBaseFragment<T extends ViewDataBinding> extends Fragment {
    protected View mRoot;
    protected Unbinder mRootUnBinder;
    private ProgressDialog loadingDialog;
    protected T viewDataBinding;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //初始化参数
        initArgs(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        if (mRoot == null) {
            int layId = getContentLayoutId();
            //初始化当前的根布局,但是不在创建时就添加到container里面去
//            View root = inflater.inflate(layId,container,false);
            viewDataBinding =  DataBindingUtil.inflate(inflater,layId,container,false);
            viewDataBinding.setLifecycleOwner(this);
            mRoot = viewDataBinding.getRoot();
            initViewModelEvent();
            initWidget();
        } else {
            if (mRoot.getParent() != null) {
                //把当前Root从其父控件中移除
                ((ViewGroup)mRoot.getParent()).removeView(mRoot);
            }
        }

        return mRoot;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //当View创建完成之后初始化数据
        initData();
    }
    /**
     * 初始化相关参数
     * @param bundle  参数Bundle
     *
     */
    protected void initArgs(Bundle bundle) {

    }

    /**
     * 得到去当前的资源文件
     * @return 资源文件Id
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化控件
     *
     */
    protected void initWidget() {

    }

    /**
     * 初始化数据
     */
    protected void initData() {

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
                                    getActivity().finish();
                                    break;
                                }
                                case BaseActionEvent.FINISH_WITH_RESULT_OK: {
                                    getActivity().setResult(RESULT_OK);
                                    getActivity().finish();
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
                                    getActivity().finish();
                                    break;
                                }
                                case BaseActionEvent.FINISH_WITH_RESULT_OK: {
                                    getActivity().setResult(RESULT_OK);
                                    getActivity().finish();
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
        Toast.makeText(getActivity(),"error:" +code+ message, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }


    protected void startLoading(String message) {
        if (loadingDialog == null) {
            loadingDialog = new ProgressDialog(getActivity());
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


    /**
     * 返回按钮触发时
     * @return  返回true代表我已经处理返回逻辑,Activity不用自己finish.
     * 返回false代表我没有处理,activity自己走自己的逻辑
     */
     public boolean onBackPressed() {
         return false;
     }


    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mRootUnBinder!=null) {
            mRootUnBinder.unbind();
        }

    }
}
