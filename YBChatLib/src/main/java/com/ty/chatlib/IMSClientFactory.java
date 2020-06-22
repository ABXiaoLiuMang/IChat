package com.ty.chatlib;


import com.ty.chatlib.interf.IMSClientInterface;
import com.ty.chatlib.netty.NettyTcpClient;

/**
 * Sdk入口
 */
public class IMSClientFactory {

    public static IMSClientInterface getIMSClient() {
        return NettyTcpClient.getInstance();
    }
}
