package com.ty.chatdemo;

import android.app.Application;


import com.ty.chatdemo.util.HttpSignUtils;
import com.ty.constant.LibApplication;
import com.ty.net.NetSdk;
import com.ty.utils.LogUtils;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LibApplication.init(this,"chat");
        LogUtils.isDebug(true);
        initNetSdk();

//        ChatSdk.config(this)
//                .port(Api.port)
//                .host(Api.host)
//                .setUserId(Api.userId)
//                .setAppKey(Api.appKey)
//                .setSource(Api.source)
//                .initSDK();
    }

    private void initNetSdk() {
        String baseUrl = "http://172.16.172.60:8083/";
        //必须先初始化
        NetSdk.config(this)
                .baseUrl(baseUrl)
                .connectTimeout(30_1000)
                .readTimeout(10_000)
                .writeTimeout(10_000)
                .addHeaders(HttpSignUtils::getBaseHeader)
                .needLog(true);
    }
}
