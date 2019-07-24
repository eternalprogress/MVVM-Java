package com.jokermk.mvvm_java;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jokermk.basemvvm.view.BaseActivity;
import com.jokermk.mvvm_java.test.TestDataViewModel;

import androidx.lifecycle.ViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private TestDataViewModel dataViewModel;
    @BindView(R.id.et_cityName)
    EditText et_cityName;
    @BindView(R.id.tv_test)
    TextView tv_weather;
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected ViewModel initViewModel() {
        dataViewModel = getViewModel(TestDataViewModel.class);
        dataViewModel.getLiveData().observe(this, s -> tv_weather.setText(s));
        return dataViewModel;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.queryWeather})
    void onClick(View view) {
        tv_weather.setText(null);
        dataViewModel.test(et_cityName.getText().toString());
    }

}
