package com.jokermk.mvvm_java.bravhtest;

import com.jokermk.basemvvm.view.activity.DataBindingBaseActivity;
import com.jokermk.mvvm_java.R;
import com.jokermk.mvvm_java.databinding.ActivityRvDatabindingTestBinding;

import androidx.lifecycle.ViewModel;

public class RvDatabindingTestActivity extends DataBindingBaseActivity<ActivityRvDatabindingTestBinding> {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_rv_databinding_test;
    }

    @Override
    protected ViewModel initViewModel() {
        RvDataBindViewModel viewModel = getViewModel(RvDataBindViewModel.class);
        viewDataBinding.setRvData(viewModel);
        return viewModel;
    }
}
