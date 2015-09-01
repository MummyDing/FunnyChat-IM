package com.mummyding.app.funnychat.Application;

import android.app.Application;

import io.rong.imkit.RongIM;

/**
 * Created by mummyding on 15-8-31.
 */
public class App extends Application {
    private static App context;
    public static App getAppContext(){
        return context;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 初始化融云
         */
        RongIM.init(this);
        context = this;
    }
}
