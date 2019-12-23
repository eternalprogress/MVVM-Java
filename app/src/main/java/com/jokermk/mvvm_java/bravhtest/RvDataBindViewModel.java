package com.jokermk.mvvm_java.bravhtest;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @Author: Joker
 * @Date: 2019/7/26 10:14
 * @Description:
 */
public class RvDataBindViewModel extends ViewModel {
    public MutableLiveData<List<String>> rvList = new MutableLiveData<>();
    public TestAdapter testAdapter;


    public RvDataBindViewModel() {
        List<String> strings = new ArrayList<>();
        strings.add("sadadadsa");
        strings.add("sadadadsaaasd");
        strings.add("sadadadsaaasdsdsad");
        strings.add("sadadadsaaasdsdsadwdadsa");
        strings.add("sadadadsaaasdsdsadwdadsadas");
        strings.add("sadadadsaaasdsdsadwdadsadasdsasa");
        strings.add("sadadadsaaasdsdsadwdadsadasdsasaad");
        rvList.setValue(strings);
        testAdapter = new TestAdapter(rvList.getValue());
    }

    public void textchange(int s) {
        Log.e("============",rvList.getValue().get(s));
    }



}
