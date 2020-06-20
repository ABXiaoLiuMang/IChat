package com.ty.chatdemo.util;

import java.util.HashMap;

public class HttpSignUtils {

    /**
     * 设置公共响应头
     *
     * @return
     */
    public static HashMap getBaseHeader() {
        HashMap<String,String> headHashMap = new HashMap<>();
        headHashMap.put("APP_KEY", "25fdlkh2d0gjmgpjg31frlgdad7etmdq");
        headHashMap.put("description", "");
        headHashMap.put("type", "text");
        headHashMap.put("enabled", "true");
        return headHashMap;
    }


}
