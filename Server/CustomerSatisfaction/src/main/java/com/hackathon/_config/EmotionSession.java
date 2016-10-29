package com.hackathon._config;

import com.hackathon.common.Pair;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public class EmotionSession implements Serializable {
    private static Map<String, Pair<String, String>> map = new HashMap<>();


    private EmotionSession() {
    }

    public static Pair<String, String> getValue(String key){
        System.out.println("Key: -->" + key);
        System.out.println("Value: -->" + map.get(key));
        return map.get(key);

    }

    public static void remove(String key){
        map.remove(key);
    }
    public static void setValue(String key, Pair<String, String> value){
        map.put(key, value);
    }


}

