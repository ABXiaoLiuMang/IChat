package com.ty.chat;

import android.app.Application;

import com.ty.chat.config.ChatConfig;
import com.ty.chat.config.IChatConfig;
import com.ty.chat.client.IChatClient;

public class ChatSdk {

    private static IChatConfig config;

    public static IChatConfig config(Application application) {
        if (config == null) {
            config = ChatConfig.init(application);
        }

        return config;
    }

    /**
     * 获取对象调用 SDK 相关 API
     *
     * @return
     */
    public static IChatClient getClient() {
        return config.getClient();
    }

    public static Application getApp() {
        return config.getApp();
    }
}
