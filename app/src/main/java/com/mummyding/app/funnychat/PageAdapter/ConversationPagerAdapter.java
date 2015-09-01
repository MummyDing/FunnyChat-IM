package com.mummyding.app.funnychat.PageAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import io.rong.imkit.fragment.ConversationListFragment;
import com.mummyding.app.funnychat.TabFragment.GroupsChatFragment;

/**
 * Created by mummyding on 15-8-29.
 */
public class ConversationPagerAdapter extends BasePagerAdapter {
    public ConversationPagerAdapter(FragmentManager fm, CharSequence[] mTitles, int mNumbOfTabsumb) {
        super(fm, mTitles, mNumbOfTabsumb);
    }
    @Override
    public Fragment getItem(int position) {
        /*
         *position 0 表示好友 1表示群
         */
        if(position == 0)
        {
            ConversationListFragment listFragment = new ConversationListFragment();
            return listFragment;
        }
        else
        {
            ConversationListFragment listFragment = new ConversationListFragment();
            return listFragment;
        }

    }
}
