package com.ty.chatdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ty.chatdemo.api.Api;
import com.ty.chatdemo.api.BaseEntity;
import com.ty.chatdemo.util.IMSClientBootstrap;
import com.ty.chatdemo.viewmodel.MainViewModel;
import com.ty.chatlib.netty.AA;
import com.ty.chatlib.protocol.Command;
import com.ty.chatlib.protocol.DeviceType;
import com.ty.chatlib.protocol.KfMessage;
import com.ty.chatlib.protocol.KfReq;
import com.ty.net.callback.NetObserver;




public class MainActivity extends AppCompatActivity {
    MainViewModel mainViewModel;

    String userId = "10000";
    String token = "token_" + userId;
    String hosts = "[{\"host\":\"192.168.0.102\", \"port\":8855}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.sendMsg).setOnClickListener(v -> {
//                    KfReq kfReq = KfReq.newBuilder()
//                            .setServiceId(String.valueOf(16))
//                            .setVisiterId(Api.userId)
//                            .setDeviceType(DeviceType.DEVICE_TYPE_ANDROID)
//                            .setDialogueSource("Dale测试")
//                            .build();
//                    KfMessage create = KfMessage.newBuilder()
//                            .setCmd(Command.COMMAND_CREATE_SESSION)
//                            .setKfReq(kfReq)
//                            .build();
//                    ChatSdk.getClient().send(create);
                }
        );

        findViewById(R.id.register).setOnClickListener(v -> mainViewModel.getSig());

        findViewById(R.id.sendHeart).setOnClickListener(v -> {
//                    KfHeartbeat kfHeartbeat = KfHeartbeat.newBuilder().setId(id++).setAck(ack++).build();
//                    KfMessage kfMessage = KfMessage.newBuilder().setCmd(Command.COMMAND_HEARTBEAT_REQ).setKfHeartbeat(kfHeartbeat).build();
//                    ChatSdk.getClient().send(kfMessage);
                }
        );

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.signLiveData.observe(this,new NetObserver<BaseEntity<String>>(){

            @Override
            protected void onSuccess(BaseEntity<String> baseEntity) {
//                ChatSdk.getClient().connect();
//                ChatSdk.getClient().register(baseEntity.getData());
                AA.appSig = baseEntity.getData();
                IMSClientBootstrap.getInstance().init(userId, token, hosts, 1);
            }

            @Override
            protected void onError(int code, String errMsg) {

            }
        });

    }
}
