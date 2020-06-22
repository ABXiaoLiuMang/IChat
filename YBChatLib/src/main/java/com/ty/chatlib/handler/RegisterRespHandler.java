package com.ty.chatlib.handler;


import com.ty.chatlib.netty.NettyTcpClient;
import com.ty.chatlib.protocol.Command;
import com.ty.chatlib.protocol.KfMessage;
import com.ty.utils.LogUtils;

import io.netty.channel.ChannelHandlerContext;

import static com.ty.chatlib.protocol.Command.COMMAND_ONLINE_RESP;

/**
 * Tcp连接成功后注册登录响应处理
 * 1.tcp连接成功后，自动发送第一条心跳包，只是连接成功后发送一次
 * 2.后期心跳包交给心跳维护处理器处理
 */
public class RegisterRespHandler extends BaseRespHandler {


    public RegisterRespHandler(NettyTcpClient imsClient) {
        super(imsClient);
    }


    @Override
    protected boolean isCommandMatching(Command cmd){
        return cmd == COMMAND_ONLINE_RESP;
    }

    @Override
    protected void handleMessage(ChannelHandlerContext ctx, KfMessage msg, Command cmd) {
        LogUtils.d("成功收到登录注册消息 \n message：" + msg.toString());
        // 握手成功，检查消息发送超时管理器里是否有发送超时的消息，如果有，则全部重发
        imsClient.getMsgTimeoutTimerManager().onResetConnected();
        //认证后发送第一条心跳消息
        KfMessage kfMessage = imsClient.getHeartbeatMsg();
        imsClient.sendMsg(kfMessage);
        LogUtils.d("登录注册成功发送第一条心跳消息 \n message：" + kfMessage.toString());
        // 添加心跳消息管理handler
        imsClient.addHeartbeatHandler();
    }

}
