package com.ty.chat.client;

import android.os.SystemClock;

import com.google.gson.Gson;
import com.ty.chat.config.ChatConfig;
import com.ty.chat.protocol.Command;
import com.ty.chat.protocol.KfMessage;
import com.ty.chat.protocol.KfOnline;
import com.ty.utils.LogUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

public class ChatClient implements IChatClient, ChannelFutureListener {
    protected ChatConfig config;
    private EventLoopGroup group;
    private Bootstrap bootstrap;
    private SocketChannel channel;
    private int connectState;
    private String appSig;
    /**
     * 重试次数
     */
    private AtomicInteger errorCount = new AtomicInteger(0);


    public ChatClient(ChatConfig config){
        this.config = config;
        connectState = DISCONNECTION;
        bootstrap = new Bootstrap();
        group = new NioEventLoopGroup();
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) {
                ChannelPipeline pipeline = socketChannel.pipeline();
                pipeline.addLast(new IdleStateHandler(0, 5, 0))
                        //拆包解码
                        .addLast(new ProtobufVarint32FrameDecoder())
                        .addLast(new ProtobufDecoder(KfMessage.getDefaultInstance()))
                        //拆包编码
                        .addLast(new ProtobufVarint32LengthFieldPrepender())
                        .addLast(new ProtobufEncoder())
                        .addLast(new ChatClientHandle());

            }
        });
    }


    @Override
    public void connect() {
        if (getConnectState() != CONNECTED) {
            try {
                ChannelFuture channelFuture = bootstrap.connect(config.host, config.port).sync();
                channelFuture.addListener(this);
            } catch (Exception e) {
                errorCount.incrementAndGet();
                if (errorCount.get() >= config.count) {
                    LogUtils.d("连接失败次数达到上限次" +  errorCount);
                    disconnect();
                }
                LogUtils.d("连接失败");
            }

        }
    }

    @Override
    public void register(String appSig) {
        this.appSig = appSig;
        KfOnline kfOnline = KfOnline.newBuilder()
                .setFromId(config.userId)
                .setAppkey(config.appKey)
                .setAppRequestSource(config.source)
                .setAppSig(appSig)
                .build();
        KfMessage login = KfMessage.newBuilder()
                .setCmd(Command.COMMAND_ONLINE_REQ)
                .setKfOnline(kfOnline)
                .build();
        LogUtils.d("register:" + login.toString());
        if(channel != null){
            channel.writeAndFlush(login).addListener(future -> LogUtils.d(future.isSuccess() ? "注册成功" : "注册失败"));
        }
    }

    @Override
    public void send(Object message) {
        LogUtils.d("发送数据:" + new Gson().toJson(message));
        if(channel != null){
            channel.writeAndFlush(message).addListener(future -> LogUtils.d(future.isSuccess() ? "发送成功" : "发送失败"));
        }
    }

    @Override
    public void reconnect() {
        if (channel != null && channel.isActive()) {
            return;
        }
        SystemClock.sleep(3000);
        LogUtils.d("发起重连");
        connect();
        register(appSig);
    }

    @Override
    public void disconnect() {
        if (channel != null){
            channel.close();
            channel = null;
        }
        connectState = DISCONNECTION;
    }

    @Override
    public int getConnectState() {
        return connectState;
    }

    /**
     * 监听连接状态
     * @param future
     */
    @Override
    public void operationComplete(ChannelFuture future) {
        if (future.isSuccess()) {
            connectState = CONNECTING;
            LogUtils.d("连接成功");
            channel = (SocketChannel) future.channel();
            errorCount.set(0);
        }else {
            int count = errorCount.get();
            errorCount.set(count++);
            connectState = DISCONNECTION;
            future.channel().eventLoop().schedule(() -> connect(), 3L, TimeUnit.SECONDS);
        }
    }
}
