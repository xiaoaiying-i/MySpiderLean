package com.xiaoai.defaultsetting;

import java.util.HashMap;

/**
 * @Author
 * @Date 2021-01-21 21:29
 */
public class RequstHeadConfigration {

    private static HashMap<String, String> heads = new HashMap<>();

    static {
        heads.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.106 Safari/537.36");
    }

    public static HashMap<String, String> getHeads(){
        return heads;
    }

    public static HashMap<String, String> setHead(String headName, String headValue){
        heads.put(headName,headValue);
        return heads;
    }
}
