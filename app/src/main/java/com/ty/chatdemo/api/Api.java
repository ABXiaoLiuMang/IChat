package com.ty.chatdemo.api;

import com.ty.net.NetCall;
import com.ty.net.http.POST;

public interface Api {

    String userId = "10001";
    int port = 8092;
    String host = "172.16.172.60";
    String appKey = "25fdlkh2d0gjmgpjg31frlgdad7etmdq";
    String source = "e6d5hou25huhrg62ct09mtjhcvm5ei8f";

    @POST("router/getSig")
    NetCall<BaseEntity<String>> getSig();

}
