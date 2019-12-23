package com.jokermk.basemvvm.bravhdatabbinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @Author: Joker
 * @Date: 2019/7/26 9:58
 * @Description:
 */
public abstract class MVVMBaseQAdapter<T,D extends ViewDataBinding,K extends BaseViewHolder> extends BaseQuickAdapter<T,K> {
    public MVVMBaseQAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public MVVMBaseQAdapter(@Nullable List<T> data) {
        super(data);
    }

    public MVVMBaseQAdapter(int layoutResId) {
        super(layoutResId);
    }

    @NonNull
    @Override
    public K onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType != LOADING_VIEW && viewType != HEADER_VIEW && viewType != EMPTY_VIEW && viewType != FOOTER_VIEW) {
            D d = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), this.mLayoutResId, null, false);
            d.executePendingBindings();
            MVVMViewHolder mvViewHolder = new MVVMViewHolder(d);
            bindViewClickListener(mvViewHolder);
            mvViewHolder.setMVVMAdapter(this);
            return (K) mvViewHolder;
        } else {
            return super.onCreateViewHolder(parent, viewType);
        }
    }


    private void bindViewClickListener(final BaseViewHolder baseViewHolder) {
        if (baseViewHolder == null) {
            return;
        }
        final View view = baseViewHolder.itemView;
        if (view == null) {
            return;
        }
        if (getOnItemClickListener() != null) {
            view.setOnClickListener(v -> getOnItemClickListener().onItemClick(MVVMBaseQAdapter.this, v, baseViewHolder.getLayoutPosition() - getHeaderLayoutCount()));
        }
        if (getOnItemLongClickListener() != null) {
            view.setOnLongClickListener(v -> getOnItemLongClickListener().onItemLongClick(MVVMBaseQAdapter.this, v, baseViewHolder.getLayoutPosition() - getHeaderLayoutCount()));
        }
    }





}
