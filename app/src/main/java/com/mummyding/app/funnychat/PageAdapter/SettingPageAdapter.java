package com.mummyding.app.funnychat.PageAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.mummyding.app.funnychat.TabFragment.SettingFragment;

import io.rong.imkit.fragment.BaseSettingFragment;
import io.rong.imkit.fragment.ConversationListFragment;

/**
 * Created by mummyding on 15-9-1.
 */
public class SettingPageAdapter extends BasePagerAdapter{

    public SettingPageAdapter(FragmentManager fm, CharSequence[] mTitles, int mNumbOfTabsumb) {
        super(fm, mTitles, mNumbOfTabsumb);
    }

    public Fragment getItem(int position) {
        /*
         *position 0 表示好友 1表示群
         */
        if(position == 0)
        {
            /**
             * 设置界面
             */
            SettingFragment settingFragment = new SettingFragment();
            return settingFragment;
        }
        return null;
    }
}
