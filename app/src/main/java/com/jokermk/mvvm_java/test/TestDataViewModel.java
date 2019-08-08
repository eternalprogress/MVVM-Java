package com.jokermk.mvvm_java.test;


import com.jokermk.basemvvm.viewmodel.BaseViewModel;

import androidx.lifecycle.MutableLiveData;

/**
 * @Author: Joker
 * @Date: 2019/7/23 17:19
 * @Description:
 */
public class TestDataViewModel extends BaseViewModel {
    private TestDataSource testDataSource = new TestDataSource(this);
    private MutableLiveData<String> liveData = new MutableLiveData<>();

    public MutableLiveData<String> getLiveData() {
        return liveData;
    }

    public void test(String name) {
        testDataSource.queryWeather(name, s -> liveData.setValue(s.getName()));
    }


}
