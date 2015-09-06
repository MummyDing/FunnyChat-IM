package com.mummyding.app.funnychat.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mummyding.app.funnychat.Item.GroupItem;
import com.mummyding.app.funnychat.Item.UserItem;
import com.mummyding.app.funnychat.R;

import java.util.List;

/**
 * Created by mummyding on 15-9-5.
 */
public class GroupAdapter extends ArrayAdapter<GroupItem> {
    public GroupAdapter(Context context, int resource, List<GroupItem> objects) {
        super(context, resource, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        GroupItem groupItem = getItem(position);
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.groupitem_view,null);
            viewHolder = new ViewHolder();
            viewHolder.groupId = (TextView) view.findViewById(R.id.groupId);
            viewHolder.groupId.setText(groupItem.getGroupId());
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
            viewHolder.groupId.setText(groupItem.getGroupId());
        }
        return view;
    }

    private class ViewHolder{
        TextView groupId;
    }
}
