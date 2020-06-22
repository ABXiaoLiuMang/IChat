//package com.ty.chatdemo;
//
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.ViewModelProvider;
//
//import com.ty.chat.ChatSdk;
//import com.ty.chat.protocol.Command;
//import com.ty.chat.protocol.DeviceType;
//import com.ty.chat.protocol.KfMessage;
//import com.ty.chat.protocol.KfReq;
//import com.ty.chatdemo.api.Api;
//import com.ty.chatdemo.api.BaseEntity;
//import com.ty.chatdemo.viewmodel.MainViewModel;
//import com.ty.net.callback.NetObserver;
//
//public class MainActivity1 extends AppCompatActivity {
//    MainViewModel mainViewModel;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        findViewById(R.id.sendMsg).setOnClickListener(v -> {
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
//                }
//        );
//
//        findViewById(R.id.register).setOnClickListener(v -> mainViewModel.getSig());
//
//        findViewById(R.id.sendHeart).setOnClickListener(v -> {
////                    KfHeartbeat kfHeartbeat = KfHeartbeat.newBuilder().setId(id++).setAck(ack++).build();
////                    KfMessage kfMessage = KfMessage.newBuilder().setCmd(Command.COMMAND_HEARTBEAT_REQ).setKfHeartbeat(kfHeartbeat).build();
////                    ChatSdk.getClient().send(kfMessage);
//                }
//        );
//
//        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
//        mainViewModel.signLiveData.observe(this,new NetObserver<BaseEntity<String>>(){
//
//            @Override
//            protected void onSuccess(BaseEntity<String> baseEntity) {
//                ChatSdk.getClient().connect();
//                ChatSdk.getClient().register(baseEntity.getData());
//            }
//
//            @Override
//            protected void onError(int code, String errMsg) {
//
//            }
//        });
//
//    }
//}
