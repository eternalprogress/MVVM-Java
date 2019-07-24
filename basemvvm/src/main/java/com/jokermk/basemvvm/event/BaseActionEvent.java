package com.jokermk.basemvvm.event;


import com.jokermk.basemvvm.event.base.BaseEvent;

/**
 * @Author: Joker
 * @Date: 2019/7/23 9:47
 * @Description: 用于向 View 层传递 Action 的 Model
 */
public class BaseActionEvent extends BaseEvent {
    public static final int SHOW_LOADING_DIALOG = 1;

    public static final int DISMISS_LOADING_DIALOG = 2;

    public static final int SHOW_TOAST = 3;

    public static final int FINISH = 4;

    public static final int FINISH_WITH_RESULT_OK = 5;
    public static final int SHOW_ERROR = 6;


    private int code;
    private String message;
    public BaseActionEvent(int action) {
        super(action);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
