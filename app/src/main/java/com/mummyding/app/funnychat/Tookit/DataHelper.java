package com.mummyding.app.funnychat.Tookit;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import com.mummyding.app.funnychat.Application.App;

/**
 * Created by mummyding on 15-8-31.
 */
public class DataHelper {
    private static Context context = App.getAppContext();
    /*
    * 判断字符串是否为空
     */
    public static boolean isStringNull(String str){
         return str.equals("");
    }
    /*
     *获取输入
     */
    public static String getInputString(EditText et){
        return et.getText().toString();
    }

    private static SharedPreferences sharedPreferences = context.getSharedPreferences("Userinfo",
            Activity.MODE_PRIVATE);

    private static SharedPreferences.Editor editor = sharedPreferences.edit();

    public static void putSharedData(String key,String value){
        editor.putString(key,value);
        editor.commit();
    }
    public static String getSharedData(String key){
        return sharedPreferences.getString(key,"");
    }
}
