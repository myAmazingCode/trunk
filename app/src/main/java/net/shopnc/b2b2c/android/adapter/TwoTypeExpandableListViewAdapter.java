/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.adapter
 * FileNmae:TypeNextExpandableListViewAdapter.java
 */
package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.TwoType;

import java.util.ArrayList;

/**
 * 二级分类适配器
 * @author KingKong·HE
 * @Time 2014-1-7 下午3:39:17
 * @E-mail hjgang@bizpoer.com
 */
public class TwoTypeExpandableListViewAdapter extends BaseExpandableListAdapter{

	public Context context;
	private LayoutInflater childInflater;
	private LayoutInflater groupInflater;
	private GroupViewHolder gvh;
	private ChildViewHolder cvh;
	private ArrayList<TwoType> typeNextGList;
	private ArrayList<TwoType> typeNextCList;
	
	public TwoTypeExpandableListViewAdapter(Context ctx) {
		this.context = ctx;
		this.childInflater = LayoutInflater.from(ctx);
		this.groupInflater = LayoutInflater.from(ctx);
	}
	

	public ArrayList<TwoType> getTypeNextGList() {
		return typeNextGList;
	}


	public void setTypeNextGList(ArrayList<TwoType> typeNextGList) {
		this.typeNextGList = typeNextGList;
	}


	public ArrayList<TwoType> getTypeNextCList() {
		return typeNextCList;
	}


	public void setTypeNextCList(ArrayList<TwoType> typeNextCList) {
		this.typeNextCList = typeNextCList;
	}


	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return typeNextCList.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return typeNextCList == null ? 0 : typeNextCList.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return typeNextGList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return typeNextGList == null ? 0 : typeNextGList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}
	

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		 if (convertView == null){
			 convertView = this.childInflater.inflate(R.layout.expandable_listview_type_next_child_item, null);
			 cvh = new ChildViewHolder();
			 cvh.textTitleName = convertView.findViewById(R.id.textTitleName);
			 convertView.setTag(cvh);
		}else{
			cvh = (ChildViewHolder)convertView.getTag();
		}
		 TwoType bean = typeNextCList.get(childPosition);
		 cvh.textTitleName.setText(bean.getGc_name());
		 
		 if(childPosition % 2 == 0){
			 convertView.setBackgroundColor(Color.rgb(245, 245, 245));
		 }else{
			 convertView.setBackgroundColor(Color.rgb(238, 238, 238));
		 }
		 
		 return convertView;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		if (convertView == null){
			convertView = this.groupInflater.inflate(R.layout.expandable_listview_type_next_group_item, null);
			gvh = new GroupViewHolder();
			gvh.textTitleName = convertView.findViewById(R.id.textTitleName);
			convertView.setTag(gvh);
		}else{
			gvh = (GroupViewHolder)convertView.getTag();
		}
		TwoType Gbean = typeNextGList.get(groupPosition);
		
		gvh.textTitleName.setText(Gbean.getGc_name());

	    return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}
	

	class GroupViewHolder{
		TextView textTitleName;
	}
	
	class ChildViewHolder{
		TextView textTitleName;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
