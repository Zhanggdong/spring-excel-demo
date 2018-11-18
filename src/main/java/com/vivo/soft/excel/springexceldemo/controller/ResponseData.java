package com.vivo.soft.excel.springexceldemo.controller;

/**
 * @author 张贵东
 * @Company TODO
 * @date 2018-11-17.
 * @Time 17:27
 * @Description TODO
 * @Version 2.0.0
 */
public class ResponseData<T> {
    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
