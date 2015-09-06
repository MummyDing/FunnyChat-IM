package com.mummyding.app.funnychat.TabFragment;

import android.content.Intent;
import android.hardware.usb.UsbRequest;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.mummyding.app.funnychat.Item.UserItem;
import com.mummyding.app.funnychat.ListAdapter.UserAdapter;
import com.mummyding.app.funnychat.R;
import com.mummyding.app.funnychat.Tookit.DataHelper;
import com.mummyding.app.funnychat.Tookit.IntentHelper;
import com.mummyding.app.funnychat.Tookit.JSONHelper;
import com.mummyding.app.funnychat.Tookit.ListHelper;
import com.mummyding.app.funnychat.Value.Values;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * Created by mummyding on 15-8-30.
 */
public class AllContactFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemClickListener{
    private ListView all_listvew;
    private SwipeRefreshLayout allContactContainer;
    private View parentView;
    private UserAdapter userAdapter;
    private List<UserItem> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView =inflater.inflate(R.layout.tab_all_contact,container,false);
        init();
        return parentView;
    }
    private void init(){
        list = new ArrayList<UserItem>();
        all_listvew = (ListView) parentView.findViewById(R.id.all_Contact_listview);
        allContactContainer = (SwipeRefreshLayout) parentView.findViewById(R.id.allContactContainer);
        allContactContainer.setOnRefreshListener(this);
        all_listvew.setOnItemClickListener(this);
        getData();
        userAdapter = new UserAdapter(getContext(),R.layout.useritem_view,list);
        all_listvew.setAdapter(userAdapter);
    }

    private void getData(){
        ListHelper.addData("userId", DataHelper.getSharedData("userId"));
        String jsonData = IntentHelper.getData(Values.queryContact,ListHelper.getList());
        ListHelper.clearData();
        JSONArray jsonArray = JSONHelper.getJSONArray(jsonData);
        for(int i = 0 ; i < jsonArray.length(); i++){
            try {
                UserItem userItem = new UserItem(
                        JSONHelper.getObjString((JSONObject) jsonArray.get(i),"contactname"),
                        JSONHelper.getObjString((JSONObject) jsonArray.get(i),"contactId"));
                list.add(userItem);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRefresh() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                list.clear();
                getData();
                userAdapter.notifyDataSetChanged();
                allContactContainer.setRefreshing(false);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserItem contactItem = (UserItem) all_listvew.getItemAtPosition(position); //AdapterView.getItem(position);
        String contactId = contactItem.getUserId();
        if(RongIM.getInstance() != null){
            RongIM.getInstance().startConversation(getContext(), Conversation.ConversationType.PRIVATE,contactId, DataHelper.getSharedData("username"));
        }
    }
}
