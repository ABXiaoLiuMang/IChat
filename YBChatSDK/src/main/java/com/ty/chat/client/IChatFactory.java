package com.ty.chat.client;

import com.ty.chat.protocol.KfReq;

public interface IChatFactory {

    /**
     * 创建一个机器人回话
     *
     * @param machineId 机器人id
     * @return
     */
    KfReq createMachineChat(String machineId);


    /**
     * 加入一个回话排队
     *
     * @return
     */
    KfReq joinChat();

    /**
     * 创建一个客服会话
     *
     * @param serviceId 客服id
     * @return
     */
    KfReq createChat(String serviceId);

}
