package com.mummyding.app.funnychat.Aty;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mummyding.app.funnychat.R;
import com.mummyding.app.funnychat.Tookit.DataHelper;
import com.mummyding.app.funnychat.Tookit.IntentHelper;
import com.mummyding.app.funnychat.Tookit.JSONHelper;
import com.mummyding.app.funnychat.Tookit.ListHelper;
import com.mummyding.app.funnychat.Value.MapHelper;
import com.mummyding.app.funnychat.Value.Values;

import org.json.JSONObject;

import java.util.Map;

public class RegisterActivity extends ActionBarActivity  implements View.OnClickListener{
    private Toolbar toolbar;
    private EditText et_username;
    private EditText et_nickname;
    private EditText et_password;
    private EditText et_repassword;
    private Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

    }
    private void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        toolbar.setTitle(" 注册");
        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call

        et_username = (EditText) findViewById(R.id.et_username);
        et_nickname = (EditText) findViewById(R.id.et_nickname);
        et_password = (EditText) findViewById(R.id.et_password);
        et_repassword = (EditText) findViewById(R.id.et_repassword);
        btn_register = (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                String username = DataHelper.getInputString(et_username);
                String nickname = DataHelper.getInputString(et_nickname);
                String password = DataHelper.getInputString(et_password);
                String repassword = DataHelper.getInputString(et_repassword);

                if(DataHelper.isStringNull(username) || DataHelper.isStringNull(nickname)
                        || DataHelper.isStringNull(password) || DataHelper.isStringNull(repassword)){
                    Toast.makeText(this,"信息不能为空",Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(password.equals(repassword) == false){
                    Toast.makeText(this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                    return ;
                }

                /*
                  提交数据
                 */
                ListHelper.addData("username", username);
                ListHelper.addData("password",password);
                ListHelper.addData("nickname",nickname);
                String responseData =IntentHelper.getData(Values.registerURL, ListHelper.getList());
                ListHelper.clearData();
                /*
                  解析数据
                 */
                JSONObject jsonObject = JSONHelper.getJSONObj(responseData);
                if(JSONHelper.getObjString(jsonObject,"code").equals("404")){
                    Toast.makeText(this,"该用户名已被注册",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"注册成功!",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
