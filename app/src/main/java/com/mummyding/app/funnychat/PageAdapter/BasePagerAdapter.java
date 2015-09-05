package com.mummyding.app.funnychat.PageAdapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by mummyding on 15-8-29.
 */
public abstract class BasePagerAdapter extends FragmentStatePagerAdapter {
    protected CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    protected int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    // Build a Constructor and assign the passed Values to appropriate values in the class
    public BasePagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb){
        super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    @Override
    public int getCount() {
        return NumbOfTabs;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setTitles(CharSequence[] titles) {
        Titles = titles;
    }
}
