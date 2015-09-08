package com.mummyding.app.funnychat.TabFragment;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.mummyding.app.funnychat.Application.App;
import com.mummyding.app.funnychat.Item.GroupItem;
import com.mummyding.app.funnychat.Item.UserItem;
import com.mummyding.app.funnychat.ListAdapter.GroupAdapter;
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
import io.rong.imlib.model.Group;

/**
 * Created by mummyding on 15-8-30.
 */
public class AllGroupsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{
    private ListView all_listvew;
    private SwipeRefreshLayout allGroupsContainer;
    private View parentView;
    private GroupAdapter groupAdapter;
    private List<GroupItem> list;
    private PopupWindow popupWindow;
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
        all_listvew.setOnItemLongClickListener(this);
        getData(); //获取数据
        groupAdapter = new GroupAdapter(getContext(),R.layout.groupitem_view,list);
        all_listvew.setAdapter(groupAdapter);
    }
    /*
        获取组群信息
     */
    private void getData(){
        ListHelper.addData("userId", DataHelper.getSharedData("userId"));
        String jsonData = IntentHelper.getData(Values.queryGroupRS, ListHelper.getList());
        ListHelper.clearData();
        /*
          获取数据失败
         */
        if(jsonData == null){
            Toast.makeText(getContext(),"获取网路数据失败,请检查网络设置",Toast.LENGTH_SHORT).show();
            return;
        }
        JSONArray jsonArray = JSONHelper.getJSONArray(jsonData);
        for(int i = 0 ; i < jsonArray.length(); i++){
            try {
                String groupId = JSONHelper.getObjString((JSONObject) jsonArray.get(i),"groupId");
                GroupItem groupItem = new GroupItem(groupId);
                list.add(groupItem);
                RongIM.getInstance().refreshGroupInfoCache(new Group(groupId,groupId, Uri.parse("")));
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
                groupAdapter.notifyDataSetChanged();
                allGroupsContainer.setRefreshing(false);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser){
            onRefresh();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GroupItem groupItem = (GroupItem) all_listvew.getItemAtPosition(position); //AdapterView.getItem(position);
        String groupId = groupItem.getGroupId();
        if(RongIM.getInstance() != null){
            RongIM.getInstance().startGroupChat(getContext(), groupId, groupId);
        }
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        showPopUp(view, position);
        return true;
    }
    private void showPopUp(View v, final int position) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.popwindow_delete, null);
        TextView delete_tv = (TextView) view.findViewById(R.id.delete_tv);

        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        popupWindow.showAtLocation(v, Gravity.TOP, 0, location[1]);
        delete_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupItem groupItem = (GroupItem) all_listvew.getItemAtPosition(position);
                String groupId = groupItem.getGroupId();
                ListHelper.addData("userId",DataHelper.getSharedData("userId"));
                ListHelper.addData("groupId", groupId);
                String responseData = IntentHelper.getData(Values.quitGroupURL, ListHelper.getList());
                ListHelper.clearData();
                /*
                获取数据失败
                */
                if(responseData == null) {
                    Toast.makeText(App.getAppContext(), "获取网路数据失败,请检查网络设置", Toast.LENGTH_SHORT).show();
                    return ;
                }
                JSONObject jsonObject = JSONHelper.getJSONObj(responseData);
                if(JSONHelper.getObjString(jsonObject,"code").equals("200")){
                    Toast.makeText(getContext(),"退群成功",Toast.LENGTH_SHORT).show();
                    popupWindow.dismiss();
                    onRefresh();
                }else{
                    Toast.makeText(getContext(), "退群失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
