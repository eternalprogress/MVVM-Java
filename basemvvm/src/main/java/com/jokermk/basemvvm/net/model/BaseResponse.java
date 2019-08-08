package com.jokermk.basemvvm.net.model;

/**
 * @Author: Joker
 * @Date: 2019/7/23 15:49
 * @Description:
 */
public class BaseResponse<T> {
    public static final int SUCCEED = 200;

//    @SerializedName("error_code")
    private int code;

//    @SerializedName("reason")
    private String msg;

//    @SerializedName("result")
    private T data;

    public boolean success() {
        return code == SUCCEED;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
