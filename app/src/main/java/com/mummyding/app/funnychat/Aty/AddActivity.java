package com.mummyding.app.funnychat.Aty;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mummyding.app.funnychat.R;
import com.mummyding.app.funnychat.Tookit.DataHelper;
import com.mummyding.app.funnychat.Tookit.IntentHelper;
import com.mummyding.app.funnychat.Tookit.JSONHelper;
import com.mummyding.app.funnychat.Tookit.ListHelper;
import com.mummyding.app.funnychat.Value.Values;

import org.json.JSONObject;

import javax.crypto.spec.DHGenParameterSpec;

public class AddActivity extends ActionBarActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private ImageButton createGroup;
    private Button addContact,addGroup;
    private EditText et_username;
    private EditText et_groupId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
    }
    void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        toolbar.setTitle(" 添加");
        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call

        createGroup = (ImageButton) findViewById(R.id.imgbtn_createGroup);
        addContact = (Button) findViewById(R.id.btn_add_contact);
        addGroup = (Button) findViewById(R.id.btn_addGroup);
        et_username = (EditText) findViewById(R.id.et_username);
        et_groupId = (EditText) findViewById(R.id.et_groupId);
        createGroup.setOnClickListener(this);
        addContact.setOnClickListener(this);
        addGroup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        switch (v.getId()){
            case R.id.btn_add_contact:
                //隐藏软键盘
                inputMethodManager.hideSoftInputFromWindow(et_username.getWindowToken(),0);
                String contactname = DataHelper.getInputString(et_username);
                ListHelper.addData("userId",DataHelper.getSharedData("userId"));
                ListHelper.addData("contactname", contactname);
                String res = IntentHelper.getData(Values.addContact, ListHelper.getList());
                ListHelper.clearData();
                /*
                    获取数据失败
                 */
                if(res == null){
                    Toast.makeText(AddActivity.this, "获取网路数据失败,请检查网络设置", Toast.LENGTH_SHORT).show();
                    return ;
                }
                JSONObject jsonObject = JSONHelper.getJSONObj(res);
                if(JSONHelper.getObjString(jsonObject,"code").equals("200")){
                    Toast.makeText(AddActivity.this,"添加成功!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddActivity.this,"失败:用户名错误或已添加!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_addGroup:
                //隐藏软键盘
                inputMethodManager.hideSoftInputFromWindow(et_groupId.getWindowToken(),0);
                String groupId = DataHelper.getInputString(et_groupId);
                ListHelper.addData("userId",DataHelper.getSharedData("userId"));
                ListHelper.addData("groupId", groupId);
                res = IntentHelper.getData(Values.addGroupURL, ListHelper.getList());
                ListHelper.clearData();
                /*
                    获取数据失败
                 */
                if(res == null) {
                    Toast.makeText(AddActivity.this, "获取网路数据失败,请检查网络设置", Toast.LENGTH_SHORT).show();
                    return;
                }
                 jsonObject = JSONHelper.getJSONObj(res);
                if(JSONHelper.getObjString(jsonObject,"code").equals("200")){
                    Toast.makeText(AddActivity.this,"添加成功!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddActivity.this,"失败:群Id错误或已添加!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imgbtn_createGroup:
                Intent intent = new Intent(AddActivity.this,CreateGroupActivity.class);
                startActivity(intent);
                break;
        }
    }
}
