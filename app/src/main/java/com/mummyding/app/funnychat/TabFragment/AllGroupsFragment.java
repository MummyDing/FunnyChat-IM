package com.mummyding.app.funnychat.TabFragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mummyding.app.funnychat.Aty.MainActivity;
import com.mummyding.app.funnychat.Item.GroupItem;
import com.mummyding.app.funnychat.Item.UserItem;
import com.mummyding.app.funnychat.ListAdapter.GroupAdapter;
import com.mummyding.app.funnychat.ListAdapter.UserAdapter;
import com.mummyding.app.funnychat.R;
import com.mummyding.app.funnychat.Tookit.DataHelper;
import com.mummyding.app.funnychat.Tookit.IntentHelper;
import com.mummyding.app.funnychat.Tookit.JSONHelper;
import com.mummyding.app.funnychat.Tookit.ListHelper;
import com.mummyding.app.funnychat.Value.Values;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import uk.co.senab.photoview.scrollerproxy.GingerScroller;

/**
 * Created by mummyding on 15-8-30.
 */
public class AllGroupsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemClickListener{
    private ListView all_listvew;
    private SwipeRefreshLayout allGroupsContainer;
    private View parentView;
    private GroupAdapter groupAdapter;
    private List<GroupItem> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView=inflater.inflate(R.layout.tab_all_groups, container, false);
        init();
        return parentView;
    }
    private void init(){
        list = new ArrayList<GroupItem>();
        all_listvew = (ListView) parentView.findViewById(R.id.all_Groups_listview);
        allGroupsContainer = (SwipeRefreshLayout) parentView.findViewById(R.id.allGroupsContainer);
        allGroupsContainer.setOnRefreshListener(this);
        all_listvew.setOnItemClickListener(this);
        getData();
        groupAdapter = new GroupAdapter(getContext(),R.layout.groupitem_view,list);
        all_listvew.setAdapter(groupAdapter);
    }
    private void getData(){
        ListHelper.addData("userId", DataHelper.getSharedData("userId"));
        String jsonData = IntentHelper.getData(Values.queryGroupRS, ListHelper.getList());
        ListHelper.clearData();
        JSONArray jsonArray = JSONHelper.getJSONArray(jsonData);
        for(int i = 0 ; i < jsonArray.length(); i++){
            try {
                GroupItem groupItem = new GroupItem(
                        JSONHelper.getObjString((JSONObject) jsonArray.get(i),"groupId"));
                list.add(groupItem);
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
                allGroupsContainer.setRefreshing(false);
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GroupItem groupItem = (GroupItem) all_listvew.getItemAtPosition(position); //AdapterView.getItem(position);
        String groupId = groupItem.getGroupId();
        if(RongIM.getInstance() != null){
            RongIM.getInstance().startGroupChat(getContext(), groupId, groupId);
        }
    }
}
