package com.mummyding.app.funnychat.TabFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mummyding.app.funnychat.R;

/**
 * Created by mummyding on 15-8-30.
 */
public class AllContactFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_all_contact,container,false);
        return v;
    }
}
