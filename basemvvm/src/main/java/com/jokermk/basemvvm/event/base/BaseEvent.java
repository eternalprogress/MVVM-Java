package com.jokermk.basemvvm.event.base;

/**
 * @Author: Joker
 * @Date: 2019/7/23 9:47
 * @Description:
 */
public class BaseEvent {
    private int action;

    public BaseEvent(int action) {
        this.action = action;
    }

    public int getAction() {
        return action;
    }
}
