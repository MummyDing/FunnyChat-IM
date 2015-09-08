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
import com.mummyding.app.funnychat.Item.UserItem;
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
import io.rong.imlib.model.UserInfo;

/**
 * Created by mummyding on 15-8-30.
 */
public class AllContactFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{
    private ListView all_listvew;
    private SwipeRefreshLayout allContactContainer;
    private View parentView;
    private UserAdapter userAdapter;
    private List<UserItem> list;
    private PopupWindow popupWindow;
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
        all_listvew.setOnItemLongClickListener(this);
        getData(); //获取数据
        userAdapter = new UserAdapter(getContext(),R.layout.useritem_view,list);
        all_listvew.setAdapter(userAdapter);
    }

    private void getData(){
        ListHelper.addData("userId", DataHelper.getSharedData("userId"));
        String jsonData = IntentHelper.getData(Values.queryContact,ListHelper.getList());
        ListHelper.clearData();
        /*
                    获取数据失败
        */
        if(jsonData == null){
            Toast.makeText(App.getAppContext(), "获取网路数据失败,请检查网络设置", Toast.LENGTH_SHORT).show();
            return;
        }
        JSONArray jsonArray = JSONHelper.getJSONArray(jsonData);
        for(int i = 0 ; i < jsonArray.length(); i++){
            try {
                String contactId = JSONHelper.getObjString((JSONObject) jsonArray.get(i),"contactId");
                String contactName=JSONHelper.getObjString((JSONObject) jsonArray.get(i),"contactname");
                UserItem userItem = new UserItem(contactName,contactId);
                list.add(userItem);
                 /*
                刷新用户信息
                */
                RongIM.getInstance().refreshUserInfoCache(new UserInfo(contactId,contactName, Uri.parse("")));
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        onRefresh();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserItem contactItem = (UserItem) all_listvew.getItemAtPosition(position); //AdapterView.getItem(position);
        String contactId = contactItem.getUserId();
        if(RongIM.getInstance() != null){
            RongIM.getInstance().startConversation(getContext(), Conversation.ConversationType.PRIVATE,contactId, DataHelper.getSharedData("username"));
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
         showPopUp(view,position);
        return true;
    }
    private void showPopUp(View v, final int position) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.popwindow_delete,null);
        TextView delete_tv = (TextView) view.findViewById(R.id.delete_tv);

        popupWindow = new PopupWindow(view,LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        popupWindow.showAtLocation(v, Gravity.TOP, 0, location[1]);
        delete_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserItem contactItem = (UserItem) all_listvew.getItemAtPosition(position); //AdapterView.getItem(position);
                String contactname = contactItem.getUserName();
                ListHelper.addData("userId",DataHelper.getSharedData("userId"));
                ListHelper.addData("contactname", contactname);
                String responseData = IntentHelper.getData(Values.deleteContact, ListHelper.getList());
                ListHelper.clearData();
                /*
                获取数据失败
                */
                if(responseData == null) {
                    Toast.makeText(getContext(), "获取网路数据失败,请检查网络设置", Toast.LENGTH_SHORT).show();
                    return ;
                }
                JSONObject jsonObject = JSONHelper.getJSONObj(responseData);
                if(JSONHelper.getObjString(jsonObject,"code").equals("200")){
                    Toast.makeText(getContext(),"删除联系人成功",Toast.LENGTH_SHORT).show();
                    popupWindow.dismiss();
                    onRefresh();
                }else{
                    Toast.makeText(getContext(), "删除联系人失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
