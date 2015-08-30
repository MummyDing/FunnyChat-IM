package com.mummyding.app.funnychat.PageAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.mummyding.app.funnychat.TabFragment.AllContactFragment;
import com.mummyding.app.funnychat.TabFragment.FriendsChatFragment;
import com.mummyding.app.funnychat.TabFragment.GroupsChatFragment;
import com.mummyding.app.funnychat.TabFragment.RecentContactFragment;

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
         *position 0 最近联系人 1表示所有联系人
         */
        if(position == 0)
        {
            RecentContactFragment recentContactFragment = new RecentContactFragment();
            return recentContactFragment;
        }
        else
        {
            AllContactFragment allContactFragment = new AllContactFragment();
            return allContactFragment;
        }

    }
}
