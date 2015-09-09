package com.mummyding.app.funnychat.Aty;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ActionMenuView;
import android.widget.TextView;
import android.widget.Toast;

import com.mummyding.app.funnychat.PageAdapter.ContactPagerAdapter;
import com.mummyding.app.funnychat.PageAdapter.ConversationPagerAdapter;
import com.mummyding.app.funnychat.PageAdapter.SettingPageAdapter;
import com.mummyding.app.funnychat.R;
import com.mummyding.app.funnychat.SlideTookit.SlidingTabLayout;
import com.mummyding.app.funnychat.Tookit.DataHelper;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener,Toolbar.OnMenuItemClickListener{
    private Toolbar toolbar;
    private TextView tv_usernmae;
    private TextView tv_nickname;
    private ViewPager pager;
    private ConversationPagerAdapter conversationPagerAdapter;
    private ContactPagerAdapter contactPagerAdapter;
    private SlidingTabLayout tabs;
    private CharSequence conversationTitles[]={"联系人","联系群"};
    private final CharSequence contactTitles[]={"联系人","联系群"};
    private final CharSequence settingTitles[]={"设置"};
    private final int Numboftabs =2;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private SettingPageAdapter settingPageAdapter;
    private static boolean isExit = false;
    private long exitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 连接融云服务器
         */
        linkRongCloud();
        initView();
    }
    private void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        pager = (ViewPager) findViewById(R.id.pager);
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tv_usernmae = (TextView) findViewById(R.id.tv_username);
        tv_nickname = (TextView) findViewById(R.id.tv_nickname);
        conversationPagerAdapter =  new ConversationPagerAdapter(getSupportFragmentManager(),conversationTitles,Numboftabs);
        contactPagerAdapter = new ContactPagerAdapter(getSupportFragmentManager(),contactTitles,Numboftabs);
        settingPageAdapter = new SettingPageAdapter(getSupportFragmentManager(),settingTitles,1);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        toolbar.setTitle("会话");
        setSupportActionBar(toolbar);


        pager.setAdapter(conversationPagerAdapter);

        tabs.setDistributeEvenly(true);
        /*
            设置菜单&导航Item响应
         */
        toolbar.setOnMenuItemClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
        tabs.setViewPager(pager);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer)
        {
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        /*
            显示用户名&昵称
         */
        tv_usernmae.setText(DataHelper.getSharedData("username"));
        tv_nickname.setText(DataHelper.getSharedData("nickname"));
          /*
             刷新用户信息
          */
        RongIM.getInstance().refreshUserInfoCache(new UserInfo(DataHelper.getSharedData("userId"),
                DataHelper.getSharedData("username"), Uri.parse("http://p2.gexing.com/touxiang/20120802/0922/5019d66eef7ed_200x200_3.jpg")));


    }
    private void exitLogin(){
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        finish();
        startActivity(intent);
    }
    private void linkRongCloud() {
        RongIM.connect(DataHelper.getSharedData("token"), new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Toast.makeText(MainActivity.this, "身份认证失败", Toast.LENGTH_SHORT).show();
                exitLogin();
            }

            @Override
            public void onSuccess(String s) {
                Toast.makeText(MainActivity.this, "成功登陆", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Toast.makeText(MainActivity.this, "连接服务器失败,请检查网络设置", Toast.LENGTH_SHORT).show();
                exitLogin();
            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        if(menuItem.isChecked()) menuItem.setChecked(false);
        else menuItem.setChecked(true);
        drawerLayout.closeDrawers();
        switch (menuItem.getItemId())
        {
            case R.id.menu_conversation:
                pager.setAdapter(conversationPagerAdapter);
                toolbar.setTitle("会话");
                tabs.setViewPager(pager);
                break;
            case R.id.menu_contact:
                pager.setAdapter(contactPagerAdapter);
                toolbar.setTitle("关系");
                tabs.setViewPager(pager);
                break;
            case R.id.menu_notification:
                Intent intent = new Intent(MainActivity.this,NotifyActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_setting:
                intent = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_about:
                intent = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);
                break;
        }
        return false;
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(this,AddActivity.class);
                startActivity(intent);
                break;
            case R.id.action_notify:
                intent = new Intent(MainActivity.this,NotifyActivity.class);
                startActivity(intent);
                break;
            case R.id.action_settings:
                intent = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent);
                break;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }
}
