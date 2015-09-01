package com.mummyding.app.funnychat.Aty;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.mummyding.app.funnychat.R;


public class WelcomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                finish();
                startActivity(intent);
                return true;
            }
        });
        handler.sendEmptyMessageDelayed(0,800);
    }
}
