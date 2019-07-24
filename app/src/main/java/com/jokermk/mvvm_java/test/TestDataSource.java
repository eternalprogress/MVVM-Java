package com.jokermk.mvvm_java.test;


import com.jokermk.basemvvm.net.BaseRemoteDataSource;
import com.jokermk.basemvvm.net.callback.RequestCallback;
import com.jokermk.basemvvm.viewmodel.IBaseViewModelAction;

/**
 * @Author: Joker
 * @Date: 2019/7/23 16:53
 * @Description:
 */
public class TestDataSource extends BaseRemoteDataSource {
    public TestDataSource(IBaseViewModelAction baseViewModelAction) {
        super(baseViewModelAction);
    }


    public void queryWeather(String city, RequestCallback<String> callback) {
        execute(getService(ApiService.class).queryWeather(city),callback);
    }


}
