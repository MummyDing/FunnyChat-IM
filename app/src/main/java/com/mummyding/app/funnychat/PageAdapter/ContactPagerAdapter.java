package com.mummyding.app.funnychat.PageAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.mummyding.app.funnychat.TabFragment.AllContactFragment;
import com.mummyding.app.funnychat.TabFragment.AllGroupsFragment;

/**
 * Created by mummyding on 15-8-30.
 */
public class ContactPagerAdapter extends BasePagerAdapter {
    public ContactPagerAdapter(FragmentManager fm, CharSequence[] mTitles, int mNumbOfTabsumb) {
        super(fm, mTitles, mNumbOfTabsumb);
    }

    @Override
    public Fragment getItem(int position) {
        /*
         *position 0 最近联系人 1表示所有联系群
         */
        if(position == 0)
        {
            AllContactFragment allContactFragment = new AllContactFragment();
            return allContactFragment;

        }
        else
        {
            AllGroupsFragment allGroupsFragment = new AllGroupsFragment();
            return allGroupsFragment;
        }

    }
}
