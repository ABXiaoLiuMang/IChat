package com.ty.chat.config;

import android.app.Application;

import com.ty.chat.client.ChatFactory;
import com.ty.chat.client.IChatClient;
import com.ty.chat.client.IChatFactory;

public interface IChatConfig {

    Application getApp();

    void initSDK();

    /**
     * 用户id
     * @param userId
     * @return
     */
    IChatConfig setUserId(String userId);

    /**
     * appkey 平台获取
     * @param appKey
     * @return
     */
    IChatConfig setAppKey(String appKey);

    /**
     * 获取 sig 的参数appRequestSource
     * @param source
     * @return
     */
    IChatConfig setSource(String source);

    /**
     * 重试次数
     * @param count
     * @return
     */
    IChatConfig retryCount(int count);

    /**
     * 端口
     * @param port
     * @return
     */
    IChatConfig port(int port);

    /**
     * 域名
     * @param host
     * @return
     */
    IChatConfig host(String host);

    IChatClient getClient();

    /**
     * 创建回话
     * @return
     */
    IChatFactory getChatFactory();

}
