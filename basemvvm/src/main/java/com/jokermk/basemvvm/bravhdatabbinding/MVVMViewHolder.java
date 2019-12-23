package com.jokermk.basemvvm.bravhdatabbinding;

import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import androidx.databinding.ViewDataBinding;

/**
 * @Author: Joker
 * @Date: 2019/7/26 9:54
 * @Description:
 */
public class MVVMViewHolder<T extends ViewDataBinding> extends BaseViewHolder {
    T binding = null;

    public MVVMViewHolder(T binding) {
        super(binding.getRoot());
        binding.getRoot().setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        this.binding = binding;
    }

    public BaseViewHolder setMVVMAdapter(BaseQuickAdapter adapter) {
        super.setAdapter(adapter);
        return this;
    }

    public T getDataViewBinding() {
        return this.binding;
    }

}
