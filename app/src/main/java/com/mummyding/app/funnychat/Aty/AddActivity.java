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

import com.mummyding.app.funnychat.R;

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
        switch (v.getId()){
            case R.id.btn_add_contact:
                break;
            case R.id.btn_addGroup:
                break;
            case R.id.imgbtn_createGroup:
                break;
        }
    }
}
