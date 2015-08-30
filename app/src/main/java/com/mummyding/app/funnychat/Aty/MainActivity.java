package com.mummyding.app.funnychat.Aty;

import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mummyding.app.funnychat.PageAdapter.ContactPagerAdapter;
import com.mummyding.app.funnychat.PageAdapter.ConversationPagerAdapter;
import com.mummyding.app.funnychat.R;
import com.mummyding.app.funnychat.SlideTookit.SlidingTabLayout;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener{
    private Toolbar toolbar;
    private ViewPager pager;
    private ConversationPagerAdapter conversationPagerAdapter;
    private ContactPagerAdapter contactPagerAdapter;
    private SlidingTabLayout tabs;
    private CharSequence conversationTitles[]={"好友","群"};
    private final CharSequence contactTitles[]={"最近","所有"};
    private final int Numboftabs =2;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        pager = (ViewPager) findViewById(R.id.pager);
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        conversationPagerAdapter =  new ConversationPagerAdapter(getSupportFragmentManager(),conversationTitles,Numboftabs);
        contactPagerAdapter = new ContactPagerAdapter(getSupportFragmentManager(),contactTitles,Numboftabs);
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
        }
        return false;
    }
}
