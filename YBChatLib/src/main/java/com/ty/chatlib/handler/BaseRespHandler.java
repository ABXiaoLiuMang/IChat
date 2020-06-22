package com.ty.chatlib.handler;

import com.ty.chatlib.netty.NettyTcpClient;
import com.ty.chatlib.protocol.Command;
import com.ty.chatlib.protocol.KfMessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

/**
 * 消息处理基类
 */
public abstract class BaseRespHandler extends SimpleChannelInboundHandler<KfMessage> {

    protected AttributeKey<Integer[]> lastResponse = AttributeKey.valueOf("last_response");
    protected NettyTcpClient imsClient;

    public BaseRespHandler(NettyTcpClient imsClient){
        this.imsClient = imsClient;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, KfMessage msg) throws Exception {
        if(msg == null){
            return;
        }
        // 消息处理
        Command cmd = msg.getCmd();
        if(isCommandMatching(cmd)){
            handleMessage(ctx,msg,cmd);
        }else {
            // 消息透传
            ctx.fireChannelRead(msg);
        }

    }

    /**
     * 返回自己处理的cmd
     * @return
     */
    protected boolean isCommandMatching(Command cmd){
        return true;
    }

    /**
     * 消息处理
     * @param ctx
     * @param msg
     */
    protected abstract void handleMessage(ChannelHandlerContext ctx, KfMessage msg,Command cmd);
}
