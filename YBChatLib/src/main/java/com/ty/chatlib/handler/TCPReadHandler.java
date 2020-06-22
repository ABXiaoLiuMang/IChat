package com.ty.chatlib.handler;


import com.ty.chatlib.netty.AA;
import com.ty.chatlib.netty.NettyTcpClient;
import com.ty.chatlib.protocol.Command;
import com.ty.chatlib.protocol.KfMessage;
import com.ty.chatlib.protocol.KfSend;
import com.ty.chatlib.protocol.KfSession;
import com.ty.utils.LogUtils;
import com.ty.utils.StringUtils;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * 消息接收处理
 * 1.心跳,注册登录等消息已经分离
 * 2.这里只是处理业务接收消息
 */
public class TCPReadHandler extends BaseRespHandler {


    public TCPReadHandler(NettyTcpClient imsClient) {
        super(imsClient);
    }

    @Override
    protected void handleMessage(ChannelHandlerContext ctx, KfMessage msg, Command cmd) {

        switch (cmd){
            // 收到新会话 提示 推送回复配置第一层内容
            case COMMAND_PUSH_NEW_SESSION:
                KfSession kfSession = msg.getKfSession();
                sendPz(kfSession, ctx);
                break;
            //11 收到访客消息 返回配置答案
            case COMMAND_CHAT_REQ:
                KfSend kfSend = msg.getKfSend();
                LogUtils.d("收到正常消息"+kfSend);
                sendMsg(kfSend, ctx);
                break;
            default:
                LogUtils.d("机器人收到消息类型{}异常");
                break;
        }


        // 接收消息，由消息转发器转发到应用层
        imsClient.getMsgDispatcher().receivedMsg(msg);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //客户端和服务端建立连接时调用
        LogUtils.d("客户端和服务端Tcp建立成功");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        //客户端断开了，重新连接
        LogUtils.d("客户端和服务端断开，开启重连");
        Channel channel = ctx.channel();
        if (channel != null) {
            channel.close();
            ctx.close();
        }

        // 触发重连
        imsClient.resetConnect(false);
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        LogUtils.d("客户端和服务端 异常断开连接，开启重连");
        //异常时断开连接
        Channel channel = ctx.channel();
        if (channel != null) {
            channel.close();
            ctx.close();
        }

        // 触发重连
        imsClient.resetConnect(false);
    }


    /**
     * 收到新会，固定回复机器人配置的第一层内容
     * @param ctx
     */
    private void sendPz(KfSession kfSession, ChannelHandlerContext ctx){

        // 获取站点配置的机器人回复缓存
//        List<>(extras[1]+ RedisKey.SEP + extras[0]);
        KfSend kfSend = KfSend.newBuilder()
                .setContent("json数据")
                .setFromId(kfSession.getIntelligentId())
                .setSessionId(kfSession.getSessionId())
                .build();
        KfMessage msg = KfMessage.newBuilder()
                .setCmd(Command.COMMAND_CHAT_REQ)
                .setKfSend(kfSend).build();
        ctx.writeAndFlush(msg).addListener(future -> {
            if(future.isSuccess()) {
                LogUtils.d("机器人回复的问题");
            }
        });
    }

    /**
     * 回复消息
     * @param kfSend
     * @param ctx
     */
    private void sendMsg(KfSend kfSend, ChannelHandlerContext ctx){
        //接收： 站点、问题id
        //发送： 问题id\问题内容\答案
        //配置存储： key=站点+parentId  list: 配置内容 第一层parentId固定
        String appKey = kfSend.getExtras();
        if(StringUtils.isEmpty(appKey)){
            LogUtils.e("缺少appkey");
            return;
        }
        KfSend.Builder jqhf = KfSend.newBuilder();
        jqhf.setFromId(AA.userId);
        jqhf.setSessionId(kfSend.getSessionId());
        jqhf.setContent("机器人回复");
        KfMessage msg = KfMessage.newBuilder()
                .setCmd(Command.COMMAND_CHAT_REQ)
                .setKfSend(jqhf.build()).build();
        ctx.writeAndFlush(msg).addListener(future -> {
            if(future.isSuccess()) {
                LogUtils.d("机器人回复的问题");
            }
        });
    }

    /**
     * 获取客户端响应id
     * @param ctx
     * @return
     */
    protected Integer[] getLastResponse(ChannelHandlerContext ctx){
        return ctx.channel().attr(lastResponse).get();
    }
}
