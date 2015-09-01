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

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

/**
 * Created by mummyding on 15-8-29.
 */
public class FriendsChatFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_friends_chat, container, false);
      //  View fragment = v.getsu //v.findViewById(R.id.conversationlist);
        /* 给 IMKit 传递默认的参数，用于显示*/
     /*   Uri uri = Uri.parse("rong://" + App.getAppContext().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //设置私聊会话采用聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true") //设置群组会话采用聚合显示
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false") // 设置讨论组不采用聚合显示
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true") //设置系统会话采用聚合显示
                .build();

        fragment.setU*/
        return v;
    }
}
