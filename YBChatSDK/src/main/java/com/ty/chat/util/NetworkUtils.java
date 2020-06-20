package com.ty.chat.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.RequiresPermission;

import com.ty.chat.ChatSdk;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;

public class NetworkUtils {

    private NetworkUtils() {
        throw new UnsupportedOperationException("u can't fuck me...");
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    private static NetworkInfo getActiveNetworkInfo() {
        return ((ConnectivityManager) ChatSdk.getApp().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
    }


    /**
     * 网络是否连接
     * @return true表示已连接,但未必有网
     */
    public static boolean isConnected() {
        NetworkInfo var1 = getActiveNetworkInfo();
        return var1 != null && var1.isConnected();
    }

}
