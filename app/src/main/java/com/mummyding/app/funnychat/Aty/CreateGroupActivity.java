package com.mummyding.app.funnychat.Aty;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class CreateGroupActivity extends ActionBarActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private Button createGroup;
    private EditText et_groupId;
    private EditText et_groupName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        initView();
    }
    void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        toolbar.setTitle(" 建群");
        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call

        createGroup = (Button) findViewById(R.id.btn_create);
        et_groupId = (EditText) findViewById(R.id.et_groupId);
        et_groupName = (EditText) findViewById(R.id.et_groupName);
        createGroup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_create:
                String groupId = DataHelper.getInputString(et_groupId);
                String groupName = DataHelper.getInputString(et_groupName);
                ListHelper.addData("userId",DataHelper.getSharedData("userId"));
                ListHelper.addData("groupId", groupId);
                ListHelper.addData("groupname", groupName);
                String responseData = IntentHelper.getData(Values.createGroupURL,ListHelper.getList());
                ListHelper.clearData();
                /*
                    获取数据失败
                 */
                if(responseData == null){
                    Toast.makeText(CreateGroupActivity.this, "获取网路数据失败,请检查网络设置", Toast.LENGTH_SHORT).show();
                    return;
                }
                /*
                    根据返回数据判断是否创建成功
                 */
                JSONObject jsonObject = JSONHelper.getJSONObj(responseData);
                if(JSONHelper.getObjString(jsonObject,"code").equals("200")){
                    Toast.makeText(this,"创建成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"该ID已被使用!",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
