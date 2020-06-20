package com.ty.chatdemo.api;

import com.ty.net.callback.XEntity;

public class BaseEntity<T> implements XEntity {

    private int code;
    private T data;
    private String message;

    public int getStatus() {
        return code;
    }

    public T getData() {
        return data;
    }

    @Override
    public boolean isOk() {
        return code == 0;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getErrMsg() {
        return message;
    }

    @Override
    public void setResponse(String result) {
    }

    @Override
    public void error() {
        switch (code) {

        }
    }
}
