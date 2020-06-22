package com.ty.chatlib;


import com.google.protobuf.GeneratedMessageV3;
import com.ty.chatlib.interf.IMSClientInterface;
import com.ty.chatlib.protocol.KfMessage;
import com.ty.utils.LogUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 消息发送超时定时器，每一条消息对应一个定时器
 * 1.发送超时，自动重新发送
 */
public class MsgTimeoutTimer extends Timer {

    private IMSClientInterface imsClient;// ims客户端
    private GeneratedMessageV3 msg;// 发送的消息
    private int currentResendCount;// 当前重发次数
    private MsgTimeoutTask task;// 消息发送超时任务

    public MsgTimeoutTimer(IMSClientInterface imsClient, GeneratedMessageV3 msg) {
        this.imsClient = imsClient;
        this.msg = msg;
        task = new MsgTimeoutTask();
        this.schedule(task, imsClient.getResendInterval(), imsClient.getResendInterval());
    }

    /**
     * 消息发送超时任务
     */
    private class MsgTimeoutTask extends TimerTask {

        @Override
        public void run() {
            if (imsClient.isClosed()) {
                if (imsClient.getMsgTimeoutTimerManager() != null) {
                    imsClient.getMsgTimeoutTimerManager().remove(msg);
                }

                return;
            }

            currentResendCount++;
            if (currentResendCount > imsClient.getResendCount()) {
                // 重发次数大于可重发次数，直接标识为发送失败，并通过消息转发器通知应用层
                try {
                    // 通知应用层消息发送失败
                    imsClient.getMsgDispatcher().receivedMsg((KfMessage) msg);
                } finally {
                    // 从消息发送超时管理器移除该消息
                    imsClient.getMsgTimeoutTimerManager().remove(msg);
                    // 执行到这里，认为连接已断开或不稳定，触发重连
                    imsClient.resetConnect();
                    currentResendCount = 0;
                }
            } else {
                // 发送消息，但不再加入超时管理器，达到最大发送失败次数就算了
                sendMsg();
            }
        }
    }

    public void sendMsg() {
        LogUtils.d("正在重发消息，message=" + msg);
        imsClient.sendMsg(msg, false);
    }

    public GeneratedMessageV3 getMsg() {
        return msg;
    }

    @Override
    public void cancel() {
        if (task != null) {
            task.cancel();
            task = null;
        }

        super.cancel();
    }
}
