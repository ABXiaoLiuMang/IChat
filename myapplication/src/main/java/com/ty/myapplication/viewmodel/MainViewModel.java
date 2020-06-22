package com.ty.myapplication.viewmodel;

import androidx.lifecycle.ViewModel;

import com.ty.myapplication.api.Api;
import com.ty.myapplication.api.BaseEntity;
import com.ty.net.NetSdk;
import com.ty.net.bean.NetLiveData;

public class MainViewModel extends ViewModel {

    public NetLiveData<BaseEntity<String>> signLiveData = new NetLiveData<>();

    public void getSig(){
        NetSdk.create(Api.class)
                .getSig()
                .params("id", Api.userId)
                .params("appRequestSource",Api.source)
                .asJSONType()
                .send(signLiveData);
    }
}
