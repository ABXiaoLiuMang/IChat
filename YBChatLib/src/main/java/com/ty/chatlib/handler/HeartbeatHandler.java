package com.ty.chatlib.handler;


import com.ty.chatlib.netty.NettyTcpClient;
import com.ty.chatlib.protocol.Command;
import com.ty.chatlib.protocol.KfHeartbeat;
import com.ty.chatlib.protocol.KfMessage;
import com.ty.utils.LogUtils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import static com.ty.chatlib.protocol.Command.COMMAND_HEARTBEAT_REQ;

/**
 * 心跳任务管理器
 * 1.
 */
public class HeartbeatHandler extends BaseRespHandler {

    public HeartbeatHandler(NettyTcpClient imsClient) {
        super(imsClient);
    }

    @Override
    protected boolean isCommandMatching(Command cmd){
        return cmd == COMMAND_HEARTBEAT_REQ;
    }

    @Override
    protected void handleMessage(ChannelHandlerContext ctx, KfMessage msg, Command cmd) {
        KfHeartbeat kfHeartbeat = msg.getKfHeartbeat();
        Integer[] lastResp = new Integer[2];
        lastResp[0] = kfHeartbeat.getId();
        lastResp[1] = kfHeartbeat.getAck();
        ctx.channel().attr(lastResponse).set(lastResp);
        LogUtils.d("收到心跳消息id:" + kfHeartbeat.getId() + "ack:"+ kfHeartbeat.getAck());
    }





    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            switch (state) {
                case READER_IDLE: {
                    // 规定时间内没收到服务端心跳包响应，进行重连操作
                    imsClient.resetConnect(false);
                    break;
                }

                case WRITER_IDLE: {
                    // 规定时间内没向服务端发送心跳包，即发送一个心跳包
                    if (heartbeatTask == null) {
                        heartbeatTask = new HeartbeatTask(ctx);
                    }

                    imsClient.getLoopGroup().execWorkTask(heartbeatTask);
                    break;
                }
            }
        }
    }

    private HeartbeatTask heartbeatTask;
    private class HeartbeatTask implements Runnable {

        private ChannelHandlerContext ctx;

        public HeartbeatTask(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            if (ctx.channel().isActive()) {
                KfMessage heartbeatMsg = imsClient.getHeartbeatMsg();
                if (heartbeatMsg == null) {
                    return;
                }
                LogUtils.d("发送心跳: \n  message：" + heartbeatMsg.toString() + "当前心跳间隔为：" + imsClient.getHeartbeatInterval() + "ms");
                imsClient.sendMsg(heartbeatMsg, false);
            }
        }
    }
}
