package com.ty.chatdemo.util;


import com.ty.chatlib.listener.IMSConnectStatusCallback;
import com.ty.utils.LogUtils;

/**
 * <p>@ProjectName:     NettyChat</p>
 * <p>@ClassName:       IMSConnectStatusListener.java</p>
 * <p>@PackageName:     com.freddy.chat.im</p>
 * <b>
 * <p>@Description:     类描述</p>
 * </b>
 * <p>@author:          FreddyChen</p>
 * <p>@date:            2019/04/08 00:31</p>
 * <p>@email:           chenshichao@outlook.com</p>
 */
public class IMSConnectStatusListener implements IMSConnectStatusCallback {

    @Override
    public void onConnecting() {
        LogUtils.d("Tcp连接中");

    }

    @Override
    public void onConnected() {
        LogUtils.d("Tcp连接成功");
    }

    @Override
    public void onConnectFailed() {
        LogUtils.d("Tcp连接失败");
    }
}
