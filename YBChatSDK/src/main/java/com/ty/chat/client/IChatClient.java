package com.ty.chat.client;


public interface IChatClient {

    int DISCONNECTION = 0;
    int CONNECTING = 1;
    int CONNECTED = 2;

    /**
     * 启动客户端
     * @return
     */
    void connect();

    /**
     * 向服务器注册
     */
    void register(String appSig);

    /**
     * 发送消息
     * @param message
     */
    void send(Object message);

    /**
     * 连接
     */
    void reconnect();

    /**
     * 断开连接
     */
    void disconnect();

    /**
     * 获取连接状态
     * @return
     */
    int getConnectState();



}
