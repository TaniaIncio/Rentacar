package com.tincio.rentacar.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.tincio.rentacar.R;

import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tincio on 30/05/2016.
 */
public class ListExpandablePreguntasAdapter extends BaseExpandableListAdapter {

    HashMap<String, List<String>> hashMap;
    List<String> listTitle;
    /*@Bind(R.id.txt_item_group)
    TextView txtItemGroup;

    @Bind(R.id.txt_item_list)
    TextView txtItemList;*/
    TextView txtItemGroup;
    TextView txtItemList;

    public ListExpandablePreguntasAdapter(HashMap<String, List<String>> hashMap, List<String> listTitle){
        this.hashMap = hashMap;
        this.listTitle  = listTitle;
    }
    @Override
    public int getGroupCount() {
        return listTitle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return hashMap.get(listTitle.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return hashMap.get(listTitle.get(groupPosition));
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return hashMap.get(listTitle.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group,parent, false);
       // ButterKnife.bind(this, view);
        txtItemGroup = (TextView)view.findViewById(R.id.txt_item_group);
        txtItemGroup.setText(listTitle.get(groupPosition));
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent, false);
       // ButterKnife.bind(this, view);
        txtItemList = (TextView)view.findViewById(R.id.txt_item_list);
        txtItemList.setText(hashMap.get(listTitle.get(groupPosition)).get(childPosition));
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
