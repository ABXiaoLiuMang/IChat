package com.ty.chat.config;

import android.app.Application;

import com.ty.chat.ChatSdk;
import com.ty.chat.client.ChatClient;
import com.ty.chat.client.IChatClient;
import com.ty.chat.client.IChatFactory;
import com.ty.chat.client.WebChatClient;

public final class ChatConfig implements IChatConfig{

    private static IChatConfig sInstance;
    public Application app;
    public IChatClient client;
    public int count = 5;
    public int port;
    public String host;
    public String userId;
    public String source;
    public String appKey;

    public static IChatConfig init(Application application) {
        if (sInstance == null) {
            synchronized (ChatSdk.class) {
                if (sInstance == null) {
                    sInstance = new ChatConfig(application);
                }
            }
        }
        return sInstance;
    }

    private ChatConfig(Application application){
        this.app = application;
    }


    @Override
    public void initSDK() {
//        client = new ChatClient(this);
        client = new WebChatClient(this);
    }

    @Override
    public IChatConfig setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public IChatConfig setAppKey(String appKey) {
        this.appKey = appKey;
        return this;
    }

    @Override
    public IChatConfig setSource(String source) {
        this.source = source;
        return this;
    }


    @Override
    public IChatClient getClient() {
        if (client == null) {
            throw new RuntimeException("请先在Application中调用ChatSdk.config().initSDK()初始化");
        }
        return client;
    }

    @Override
    public IChatFactory getChatFactory() {
        return null;
    }

    @Override
    public Application getApp() {
        return app;
    }


    @Override
    public IChatConfig retryCount(int count) {
        this.count = count;
        return this;
    }

    @Override
    public IChatConfig port(int port) {
        this.port = port;
        return this;
    }

    @Override
    public IChatConfig host(String host) {
        this.host = host;
        return this;
    }
}
