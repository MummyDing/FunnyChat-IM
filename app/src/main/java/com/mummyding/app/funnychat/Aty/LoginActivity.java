package com.mummyding.app.funnychat.Aty;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mummyding.app.funnychat.R;
import com.mummyding.app.funnychat.Tookit.DataHelper;
import com.mummyding.app.funnychat.Tookit.IntentHelper;
import com.mummyding.app.funnychat.Tookit.JSONHelper;
import com.mummyding.app.funnychat.Tookit.ListHelper;
import com.mummyding.app.funnychat.Value.MapHelper;
import com.mummyding.app.funnychat.Value.Values;

import org.json.JSONObject;

public class LoginActivity extends ActionBarActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private ImageButton newUser;
    private Button loginBtn;
    private EditText et_username;
    private EditText et_password;
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
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        newUser.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                String username = DataHelper.getInputString(et_username);
                String password = DataHelper.getInputString(et_password);

                if(DataHelper.isStringNull(username)||DataHelper.isStringNull(password)){
                    Toast.makeText(this,"输入不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                /*
                  发送网络数据
                 */
                ListHelper.addData("username",username);
                ListHelper.addData("password",password);
                String responseData = IntentHelper.getData(Values.loginURL,ListHelper.getList());
                ListHelper.clearData();

                /*
                    根据返回数据判断该用户是否存在
                 */
                JSONObject jsonObject = JSONHelper.getJSONObj(responseData);
                if(JSONHelper.getObjString(jsonObject,"code").equals("404")){
                    Toast.makeText(this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                    return;
                }

                /*
                 验证成功 保存token 进入主界面
                 */
                DataHelper.putSharedData("token",JSONHelper.getObjString(jsonObject,"token"));
                DataHelper.putSharedData("userId",JSONHelper.getObjString(jsonObject,"userId"));
                DataHelper.putSharedData("nickname",JSONHelper.getObjString(jsonObject,"nickname"));
                DataHelper.putSharedData("username",username);
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                finish();
                startActivity(intent);
                break;
            case R.id.imgbtn_newUser:
                intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
