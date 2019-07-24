package com.jokermk.basemvvm.lifecycle;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author: Joker
 * @Date: 2019/7/24 14:31
 * @Description:
 */
public class ButterKnifeLifecycleManager {
    public static void bindButterKnife(LifecycleOwner lifecycleOwner, Activity activity) {
        new ButterKnifeLifecycle(lifecycleOwner,activity);
    }

    static class ButterKnifeLifecycle implements LifecycleObserver {
        private Activity activity;
        private Unbinder unbinder;

        public ButterKnifeLifecycle(LifecycleOwner lifecycleOwner, Activity activity) {
            this.activity = activity;
            lifecycleOwner.getLifecycle().addObserver(this);
        }
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        void bindButterKnife() {
            Log.e("==============","bind");
            unbinder = ButterKnife.bind(activity);
        }
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        void unBindButterKnife() {
            if (unbinder!=null) {
                unbinder.unbind();
            }
        }
    }

}
