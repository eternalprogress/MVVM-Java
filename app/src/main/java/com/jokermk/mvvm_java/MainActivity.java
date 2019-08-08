package com.jokermk.mvvm_java;


import com.jokermk.basemvvm.view.activity.DataBindingBaseActivity;
import com.jokermk.mvvm_java.databinding.ActivityMainBinding;
import com.jokermk.mvvm_java.test.TestDataViewModel;

import androidx.lifecycle.ViewModel;


public class MainActivity extends DataBindingBaseActivity<ActivityMainBinding> {


    private TestDataViewModel viewModel;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected ViewModel initViewModel() {
        viewModel =  getViewModel(TestDataViewModel.class);
        viewDataBinding.setLogin(viewModel);
        return viewModel;
    }
}
