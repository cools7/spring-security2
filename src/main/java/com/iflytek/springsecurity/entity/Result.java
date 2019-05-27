package com.iflytek.springsecurity.entity;

/**
 * Created by zjingchuan on 2016/5/19.
 */
public class Result<T> {

    int code;
    String message;
    T data;

    public static <O> Result<O> sucessOf(O object) {
        Result<O> result = new Result<O>();
        result.code = 0;
        result.message = "成功";
        result.data = object;
        return result;
    }

    public static Result errorOf(int errorCode, String message) {
        Result result = new Result();
        result.code = errorCode;
        result.message = message;
        return result;
    }

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

    public boolean successful() {
        return code == 0;
    }

    @Override
    public String toString() {
        return "{\"code\":\"" + code + "\"" + ", " +
                "\"message\":" + (message == null ? "null" : "\"" + message + "\"") + ", " +
                "\"data\":" + (data == null ? "null" : "\"" + data + "\"") +
                "}";
    }
}
