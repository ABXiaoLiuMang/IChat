package com.ty.chatlib.listener;

/**
 * Tcp连接状态回调
 * 1.业务注册此接口监听连接状态
 */
public interface IMSConnectStatusCallback {

    /**
     * ims连接中
     */
    void onConnecting();

    /**
     * ims连接成功
     */
    void onConnected();

    /**
     * ims连接失败
     */
    void onConnectFailed();
}
