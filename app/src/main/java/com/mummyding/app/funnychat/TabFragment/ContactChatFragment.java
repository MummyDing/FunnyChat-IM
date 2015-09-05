package com.mummyding.app.funnychat.TabFragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mummyding.app.funnychat.Application.App;
import com.mummyding.app.funnychat.R;

import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

/**
 * Created by mummyding on 15-8-29.
 */
public class ContactChatFragment extends ConversationListFragment {
   @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_friends_chat, container, false);
        return v;
    }
}
