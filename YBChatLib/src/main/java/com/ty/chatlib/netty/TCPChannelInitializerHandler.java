package com.ty.chatlib.netty;

import com.ty.chatlib.handler.HeartbeatHandler;
import com.ty.chatlib.handler.RegisterRespHandler;
import com.ty.chatlib.handler.TCPReadHandler;
import com.ty.chatlib.protocol.KfMessage;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * Channel初始化配置
 */
public class TCPChannelInitializerHandler extends ChannelInitializer<Channel> {

    private NettyTcpClient imsClient;

    public TCPChannelInitializerHandler(NettyTcpClient imsClient) {
        this.imsClient = imsClient;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();


        pipeline.addLast(new IdleStateHandler(0, 4, 0))
                //拆包解码
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufDecoder(KfMessage.getDefaultInstance()))
                //拆包编码
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder());


        // 握手认证消息响应处理handler
        pipeline.addLast(RegisterRespHandler.class.getSimpleName(), new RegisterRespHandler(imsClient));
        // 心跳消息响应处理handler
        pipeline.addLast(HeartbeatHandler.class.getSimpleName(), new HeartbeatHandler(imsClient));
//        pipeline.addLast(HeartbeatHandler.class.getSimpleName(), new HeartbeatRespHandler(imsClient)); dale
        // 接收消息处理handler
        pipeline.addLast(TCPReadHandler.class.getSimpleName(), new TCPReadHandler(imsClient));
    }
}
