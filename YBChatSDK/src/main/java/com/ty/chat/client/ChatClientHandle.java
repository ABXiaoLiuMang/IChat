package com.ty.chat.client;


import com.ty.chat.protocol.Command;
import com.ty.chat.protocol.KfHeartbeat;
import com.ty.chat.protocol.KfMessage;
import com.ty.chat.protocol.KfSend;
import com.ty.chat.protocol.KfSession;
import com.ty.utils.LogUtils;
import com.ty.utils.StringUtils;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;

public class ChatClientHandle extends SimpleChannelInboundHandler<KfMessage> {
    public AttributeKey<Integer[]> lastResponse = AttributeKey.valueOf("last_response");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, KfMessage msg) throws Exception {
        // 消息处理
        Command cmd = msg.getCmd();
        switch (cmd){
            // 机器人上线
            case COMMAND_ONLINE_RESP:
                LogUtils.d("注册成功！");
                break;
            // 收到新会话 提示 推送回复配置第一层内容
            case COMMAND_PUSH_NEW_SESSION:
                KfSession kfSession = msg.getKfSession();
                sendPz(kfSession, ctx);
                break;
            // 收到服务器的心跳响应
            case COMMAND_HEARTBEAT_REQ:
                KfHeartbeat kfHeartbeat = msg.getKfHeartbeat();
                Integer[] lastResp = new Integer[2];
                lastResp[0] = kfHeartbeat.getId();
                lastResp[1] = kfHeartbeat.getAck();
                ctx.channel().attr(lastResponse).set(lastResp);
                LogUtils.d("收到心跳消息id:" + kfHeartbeat.getId() + "ack:"+ kfHeartbeat.getAck());
                break;
            //11 收到访客消息 返回配置答案
            case COMMAND_CHAT_REQ:
                KfSend kfSend = msg.getKfSend();
                LogUtils.d("收到正常消息"+kfSend);
                sendMsg(kfSend, ctx);
                break;
            default:
                LogUtils.d("机器人收到消息类型异常:" + cmd);
                break;
        }
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
                LogUtils.d("机器人回复" + kfSend.getFromId() + "的问题"+ kfSend.getContent());
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
            LogUtils.d("缺少appkey");
            return;
        }
        KfSend.Builder jqhf = KfSend.newBuilder();
//        jqhf.setFromId(imClient.getId());
        jqhf.setSessionId(kfSend.getSessionId());
        jqhf.setContent("机器人回复");
        KfMessage msg = KfMessage.newBuilder()
                .setCmd(Command.COMMAND_CHAT_REQ)
                .setKfSend(jqhf.build()).build();
        ctx.writeAndFlush(msg).addListener(future -> {
            if(future.isSuccess()) {
                LogUtils.d("机器人回复" + kfSend.getFromId() +"的问题"+kfSend.getContent());
            }
        });
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt ;
            if (idleStateEvent.state() == IdleState.WRITER_IDLE){
                Integer[] lastResp = getLastResponse(ctx);
                Integer ack = 0;
                Integer id = 0;
                if(lastResp != null && lastResp.length > 0){
                    ack = lastResp[1];
                    id = lastResp[0];
                }
                KfHeartbeat kfHeartbeat = KfHeartbeat.newBuilder().setId(id+1).setAck(ack).build();
                KfMessage msg = KfMessage.newBuilder().setCmd(Command.COMMAND_HEARTBEAT_REQ).setKfHeartbeat(kfHeartbeat).build();
                LogUtils.d("发送心跳消息id:" + kfHeartbeat.getId() + "ack:"+ kfHeartbeat.getAck());
                ctx.writeAndFlush(msg).addListeners((ChannelFutureListener) future -> {
                    if (!future.isSuccess()) {
                        LogUtils.d("IO error,close Channel");
                        future.channel().close();
                    }
                }) ;
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //客户端和服务端建立连接时调用
        LogUtils.d("cim server connect success!");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        LogUtils.d("客户端断开了，重新连接！");
        try {
//            imClient.reconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //异常时断开连接
        cause.printStackTrace() ;
        ctx.close() ;
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
