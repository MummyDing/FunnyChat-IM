package com.mummyding.app.funnychat.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mummyding on 15-8-31.
 */
public class MapHelper {
    private static Map<String,String> map = new HashMap<String, String>();
    public static Map<String,String> getMap(){
        return map;
    }
    public static void addData(String para,String value){
        map.put(para,value);
    }
    public static void clearData(){
        map.clear();
    }
}
