package com.ty.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.protobuf.InvalidProtocolBufferException;
import com.rabtman.wsmanager.WsManager;
import com.rabtman.wsmanager.listener.WsStatusListener;
import com.ty.chatlib.protocol.Command;
import com.ty.chatlib.protocol.KfHeartbeat;
import com.ty.chatlib.protocol.KfMessage;
import com.ty.myapplication.api.Api;
import com.ty.myapplication.api.BaseEntity;
import com.ty.myapplication.viewmodel.MainViewModel;
import com.ty.net.callback.NetObserver;
import com.ty.utils.LogUtils;
import com.ty.utils.StringUtils;
import com.ty.utils.ToastUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okio.ByteString;

public class WebMainActivity extends AppCompatActivity {

    private WsManager wsManager;
    MainViewModel mainViewModel;
    Button connect;
    Button btn_send;
    String url;
    int id,ack;
    private WsStatusListener wsStatusListener = new WsStatusListener() {
        @Override
        public void onOpen(Response response) {
            LogUtils.d( "服务器连接成功");
        }

        @Override
        public void onMessage(String text) {
            LogUtils.d( "收到消息：" + text);
        }

        @Override
        public void onMessage(ByteString bytes) {
            try {
                LogUtils.d( "收到消息：" + KfMessage.parseFrom(bytes.toByteArray()).toString());
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onReconnect() {
            LogUtils.d( "服务器重连接中");
    }

        @Override
        public void onClosing(int code, String reason) {
            LogUtils.d( "服务器连接关闭中");
        }

        @Override
        public void onClosed(int code, String reason) {
            LogUtils.d( "服务器连接已关闭");
        }

        @Override
        public void onFailure(Throwable t, Response response) {
            LogUtils.d( "W服务器连接失败");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.signLiveData.observe(this,new NetObserver<BaseEntity<String>>(){

            @Override
            protected void onSuccess(BaseEntity<String> baseEntity) {
                url = StringUtils.getFormatString(Api.url,baseEntity.getData());
                connect();
            }

            @Override
            protected void onError(int code, String errMsg) {

            }
        });



        connect = findViewById(R.id.connect);
        btn_send = findViewById(R.id.btn_send);
        connect.setOnClickListener(v -> mainViewModel.getSig());



        btn_send.setOnClickListener(v -> {
            ToastUtils.showLong("send");
            KfHeartbeat kfHeartbeat = KfHeartbeat.newBuilder().setId(id++).setAck(ack++).build();
            KfMessage kfMessage = KfMessage.newBuilder().setCmd(Command.COMMAND_HEARTBEAT_REQ).setKfHeartbeat(kfHeartbeat).build();
            if (wsManager != null && wsManager.isWsConnected()) {
                boolean isSend = wsManager.sendMessage(ByteString.of(kfMessage.toByteArray()));
                if (isSend) {
                    LogUtils.d("发送成功：" + kfMessage.toString());
                } else {
                    LogUtils.d("发送失败：" + kfMessage.toString());
                }
            } else {
                Toast.makeText(getBaseContext(), "请先连接服务器", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        if (wsManager != null) {
            wsManager.stopConnect();
            wsManager = null;
        }
        super.onDestroy();
    }

    private void connect() {
        if (!TextUtils.isEmpty(url) && url.contains("ws")) {
            if (wsManager != null) {
                wsManager.stopConnect();
                wsManager = null;
            }
            wsManager = new WsManager.Builder(getBaseContext())
                    .client(
                            new OkHttpClient().newBuilder()
                                    .pingInterval(15, TimeUnit.SECONDS)
                                    .retryOnConnectionFailure(true)
                                    .build())
                    .needReconnect(false)
                    .wsUrl(url)
                    .build();
            wsManager.setWsStatusListener(wsStatusListener);
            wsManager.startConnect();
        } else {
            Toast.makeText(getBaseContext(), "请填写需要链接的地址", Toast.LENGTH_SHORT).show();
        }
    }

}
