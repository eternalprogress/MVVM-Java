package com.jokermk.mvvm_java.bravhtest;


import com.jokermk.basemvvm.bravhdatabbinding.MVVMBaseQAdapter;
import com.jokermk.basemvvm.bravhdatabbinding.MVVMViewHolder;
import com.jokermk.mvvm_java.R;
import com.jokermk.mvvm_java.databinding.ItemTestBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Author: Joker
 * @Date: 2019/7/26 17:01
 * @Description:
 */
public class TestAdapter extends MVVMBaseQAdapter<String, ItemTestBinding, MVVMViewHolder<ItemTestBinding>> {

    public TestAdapter(@Nullable List data) {
        super(R.layout.item_test, data);
    }


    @Override
    protected void convert(@NonNull MVVMViewHolder<ItemTestBinding> helper, String item) {
        helper.getDataViewBinding().setName(item);
        helper.addOnClickListener(R.id.name);
    }
}
