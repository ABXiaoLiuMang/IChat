package com.ty.chatlib;


import com.ty.chatlib.listener.OnEventListener;
import com.ty.chatlib.protocol.KfMessage;

/**
 *  消息转发器，负责将接收到的消息转发到应用层
 */
public class MsgDispatcher {

    private OnEventListener mOnEventListener;

    public MsgDispatcher() {

    }

    public void setOnEventListener(OnEventListener listener) {
        this.mOnEventListener = listener;
    }

    /**
     * 接收消息，并通过OnEventListener转发消息到应用层
     * @param msg
     */
    public void receivedMsg(KfMessage msg) {
        if(mOnEventListener == null) {
            return;
        }

        mOnEventListener.dispatchMsg(msg);
    }
}
