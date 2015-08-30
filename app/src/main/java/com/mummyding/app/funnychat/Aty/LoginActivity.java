package com.mummyding.app.funnychat.Aty;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mummyding.app.funnychat.R;

public class LoginActivity extends ActionBarActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private ImageButton newUser;
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        toolbar.setTitle(" 登陆");
        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call
        initView();
    }
    void initView(){
        newUser = (ImageButton) findViewById(R.id.imgbtn_newUser);
        loginBtn = (Button) findViewById(R.id.btn_login);
        newUser.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.imgbtn_newUser:
                intent = new Intent(LoginActivity.this,RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }

    }
}
