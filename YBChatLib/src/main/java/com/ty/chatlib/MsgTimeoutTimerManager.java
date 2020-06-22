package com.ty.chatlib;


import com.google.protobuf.GeneratedMessageV3;
import com.ty.chatlib.interf.IMSClientInterface;
import com.ty.chatlib.protocol.KfHeartbeat;
import com.ty.chatlib.protocol.KfMessage;
import com.ty.utils.LogUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息发送超时管理器，用于管理消息定时器的新增、移除等
 * 1.重新发送失败后，添加到管理器中
 * 2.再次连接上后，自动重新发送
 */
public class MsgTimeoutTimerManager {

    private Map<GeneratedMessageV3, MsgTimeoutTimer> mMsgTimeoutMap = new ConcurrentHashMap<>();
    private IMSClientInterface imsClient;// ims客户端

    public MsgTimeoutTimerManager(IMSClientInterface imsClient) {
        this.imsClient = imsClient;
    }

    /**
     * 添加消息到发送超时管理器
     *
     * @param msg
     */
    public void add(GeneratedMessageV3 msg) {
        if (msg == null) {
            return;
        }


        int clientReceivedReportMsgType = imsClient.getClientReceivedReportMsgType();
        KfMessage handshakeMsg = imsClient.getHandshakeMsg();
        KfMessage heartbeatMsg = imsClient.getHeartbeatMsg();
        // 握手消息、心跳消息、客户端返回的状态报告消息，不用重发。
        if (msg instanceof KfMessage || msg instanceof KfHeartbeat) {
//        if (msgType == handshakeMsgType || msgType == heartbeatMsgType || msgType == clientReceivedReportMsgType) {
            return;
        }

        if (!mMsgTimeoutMap.containsKey(msg)) {
            MsgTimeoutTimer timer = new MsgTimeoutTimer(imsClient, msg);
            mMsgTimeoutMap.put(msg, timer);
        }

        LogUtils.d("添加消息超发送超时管理器，message=" + msg + "\t当前管理器消息数：" + mMsgTimeoutMap.size());
    }

    /**
     * 从发送超时管理器中移除消息，并停止定时器
     *
     * @param msg
     */
    public void remove(GeneratedMessageV3 msg) {
        if (msg == null) {
            return;
        }

        MsgTimeoutTimer timer = mMsgTimeoutMap.remove(msg);
        if (timer != null) {
            timer.cancel();
        }

        LogUtils.d("从发送消息管理器移除消息，message=" + msg.toString());
    }

    /**
     * 重连成功回调，重连并握手成功时，重发消息发送超时管理器中所有的消息
     */
    public synchronized void onResetConnected() {
        for(Iterator<Map.Entry<GeneratedMessageV3, MsgTimeoutTimer>> it = mMsgTimeoutMap.entrySet().iterator(); it.hasNext();) {
            it.next().getValue().sendMsg();
        }
    }
}
