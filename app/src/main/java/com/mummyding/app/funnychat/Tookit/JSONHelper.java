package com.mummyding.app.funnychat.Tookit;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
/**
 * Created by mummyding on 15-8-31.
 */
public class JSONHelper {
    public static JSONObject getJSONObj(String json){
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static JSONArray getJSONArray(String json){
        try {
            return new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getObjString(JSONObject jsonObject,String key){
        try {
            return jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
