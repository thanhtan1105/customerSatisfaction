package com.manhnv.indoordirection.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ManhNV on 10/29/16.
 */

public class ResponseModel<T> {
    @SerializedName("success")
    private boolean succeed;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private T data;

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
