package com.ty.chat.client;

import android.os.SystemClock;
import android.util.Log;

import com.ty.chat.ChatSdk;
import com.ty.chat.config.ChatConfig;
import com.ty.chat.protocol.Command;
import com.ty.chat.protocol.KfMessage;
import com.ty.chat.protocol.KfOnline;
import com.ty.utils.LogUtils;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WebChatClient extends  WebSocketListener implements IChatClient {
    protected ChatConfig config;
    private WebSocket mWebSocket;
    private OkHttpClient mOkHttpClient;
    private Lock mLock;
    private Request mRequest;
    private int connectState;
    private String appSig;
    /**
     * 重试次数
     */
    private AtomicInteger errorCount = new AtomicInteger(0);


    public WebChatClient(ChatConfig config) {
        this.config = config;
        connectState = DISCONNECTION;
        mOkHttpClient = new OkHttpClient.Builder()
                .pingInterval(5, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        this.mLock = new ReentrantLock();
    }


    @Override
    public void connect() {
        if (getConnectState() != CONNECTED) {
            if (mRequest == null) {
                LogUtils.d("链接地址：" + ChatSdk.url);
                mRequest = new Request.Builder()
                        .url(ChatSdk.url)
                        .build();
            }
            mOkHttpClient.dispatcher().cancelAll();
            try {
                mLock.lockInterruptibly();
                try {
                    mOkHttpClient.newWebSocket(mRequest, this);
                } finally {
                    mLock.unlock();
                }
            } catch (InterruptedException e) {
            }

        }
    }

    @Override
    public void register(String appSig) {
        this.appSig = appSig;

        try {
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
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            DataOutputStream dos = new DataOutputStream(outputStream);
//            dos.write(login.toByteArray());
            mWebSocket.send(ByteString.of(login.toByteArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void send(Object message) {
        KfMessage kfMessage = (KfMessage) message;
        LogUtils.d("发送数据:" + kfMessage.toString());
        mWebSocket.send(ByteString.of(kfMessage.toByteArray()));
    }

    @Override
    public void reconnect() {
        if (mWebSocket != null) {
            return;
        }
        SystemClock.sleep(3000);
        LogUtils.d("发起重连");
        connect();
        register(appSig);
    }

    @Override
    public void disconnect() {
        if (mWebSocket != null){
            mWebSocket.cancel();
            mWebSocket = null;
        }
        connectState = DISCONNECTION;
    }

    @Override
    public int getConnectState() {
        return connectState;
    }

    @Override
    public void onOpen(WebSocket webSocket, final Response response) {
        //连接成功
        LogUtils.d("onOpen 53:" + webSocket);
        mWebSocket = webSocket;
    }

    @Override
    public void onMessage(WebSocket webSocket, final ByteString bytes) {
        //接收ByteString类型数据
        LogUtils.d("onMessage 73:" + bytes.string(Charset.forName("UTF-8")));
    }

    @Override
    public void onMessage(WebSocket webSocket, final String text) {
        //接收string类型数据
        LogUtils.d("onMessage 90:" + text);
    }

    @Override
    public void onClosing(WebSocket webSocket, final int code, final String reason) {
        //连接关闭中
        //远程端暗示没有数据交互时回调（即此时准备关闭，但连接还没有关闭）
        LogUtils.d("连接关闭中 code:" + code +"  reason:" + reason );
    }

    @Override
    public void onClosed(WebSocket webSocket, final int code, final String reason) {
        //连接已关闭
        LogUtils.d("连接已关闭 code:" + code +"  reason:" + reason );
    }

    @Override
    public void onFailure(WebSocket webSocket, final Throwable t, final Response response) {
        //包括连接失败，发送失败等
        LogUtils.d("连接失败 t:" + t.getMessage());
    }

}
