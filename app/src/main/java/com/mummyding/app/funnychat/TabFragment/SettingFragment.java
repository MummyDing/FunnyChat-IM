package com.mummyding.app.funnychat.TabFragment;

import android.view.View;

import io.rong.imkit.fragment.BaseSettingFragment;

/**
 * Created by mummyding on 15-9-1.
 */
public class SettingFragment extends BaseSettingFragment {
    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected boolean setSwitchButtonEnabled() {
        return false;
    }

    @Override
    protected int setSwitchBtnVisibility() {
        return 0;
    }

    @Override
    protected void onSettingItemClick(View view) {

    }

    @Override
    protected void toggleSwitch(boolean b) {

    }

    @Override
    protected void initData() {

    }
}
