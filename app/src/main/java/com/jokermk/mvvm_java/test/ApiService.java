package com.jokermk.mvvm_java.test;


import com.jokermk.basemvvm.net.model.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 作者：leavesC
 * 时间：2018/10/28 13:13
 * 描述：
 * GitHub：https://github.com/leavesC
 * Blog：https://www.jianshu.com/u/9df45b87cfdf
 */
public interface ApiService {

    @GET("login/{name}")
    Observable<BaseResponse<LoginBean>> login(@Path("name") String name);

}