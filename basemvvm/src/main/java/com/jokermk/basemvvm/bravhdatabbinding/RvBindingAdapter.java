package com.jokermk.basemvvm.bravhdatabbinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author: Joker
 * @Date: 2019/7/26 16:12
 * @Description:
 */
public class RvBindingAdapter {
    @BindingAdapter("android:bindLayoutManager")
    public static void bindLayoutManager(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }


    @BindingAdapter(value = {"android:reAdapter","android:onChildItemClick"})
    public static void setChildItemClick(RecyclerView recyclerView, MVVMBaseQAdapter adapter, ItemClickListsener testListsener) {
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter1, view, position) -> testListsener.OnItemChildClick(position));
    }
    @BindingAdapter(value = {"android:bindLayoutManagerType","android:gridLayoutCount"},requireAll = false)
    public static void bindLayoutManager(RecyclerView recyclerView, int type,int count) {
        RecyclerView.LayoutManager layoutManager = null;
        switch (type) {
            case 1:
                layoutManager = new LinearLayoutManager(recyclerView.getContext());
                break;
            case 2:
                if (count == 0) {
                    count = 3;
                }
                layoutManager = new GridLayoutManager(recyclerView.getContext(),count);
                break;
        }
        recyclerView.setLayoutManager(layoutManager);
    }


}


