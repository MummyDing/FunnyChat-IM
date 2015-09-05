package com.mummyding.app.funnychat.Aty;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener{
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
    }
    private void exitLogin(){
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        finish();
        startActivity(intent);
    }
    private void linkRongCloud(){
        RongIM.connect(DataHelper.getSharedData("token"), new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Toast.makeText(MainActivity.this,"身份认证失败",Toast.LENGTH_SHORT).show();
                exitLogin();
            }
            @Override
            public void onSuccess(String s) {
                Toast.makeText(MainActivity.this,"成功登陆",Toast.LENGTH_SHORT).show();
                tv_usernmae.setText(DataHelper.getSharedData("username"));
                tv_nickname.setText(DataHelper.getSharedData("nickname"));

                /*
                    设置用户信息
                 */
                RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
                    @Override
                    public UserInfo getUserInfo(String s) {
                        return new UserInfo(DataHelper.getSharedData("userId"),
                                DataHelper.getSharedData("username"), Uri.parse("http://p2.gexing.com/touxiang/20120802/0922/5019d66eef7ed_200x200_3.jpg"));
                    }
                },true);
            }
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Toast.makeText(MainActivity.this,"连接服务器失败,请检查网络设置",Toast.LENGTH_SHORT).show();
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
            case R.id.menu_setting:
                pager.setAdapter(settingPageAdapter);
                toolbar.setTitle("设置");
                tabs.setViewPager(pager);
                break;
        }
        return false;
    }
}
