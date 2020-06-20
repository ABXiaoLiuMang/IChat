package com.ty.chat.client;

import com.ty.chat.config.ChatConfig;
import com.ty.chat.protocol.KfReq;

public class ChatFactory implements IChatFactory{

    ChatConfig chatConfig;

    public ChatFactory(ChatConfig chatConfig){
        this.chatConfig = chatConfig;
    }

    @Override
    public KfReq createMachineChat(String machineId) {
        return null;
    }

    @Override
    public KfReq joinChat() {
        return null;
    }

    @Override
    public KfReq createChat(String serviceId) {
        return null;
    }
}
