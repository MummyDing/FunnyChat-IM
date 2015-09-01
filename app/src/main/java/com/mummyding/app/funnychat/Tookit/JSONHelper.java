package com.mummyding.app.funnychat.Tookit;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mummyding on 15-8-31.
 */
public class JSONHelper {
    private static JSONObject jsonObject = null;

    public static void setJSON(String json){
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static String getString(String key){
        try {
            return jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
