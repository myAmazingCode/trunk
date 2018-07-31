package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.IMFriendsG;
import net.shopnc.b2b2c.android.bean.IMFriendsList;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.SystemHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author kingkong
 */
public class IMFriendsEListViewAdapter extends BaseExpandableListAdapter{

	public Context context;
	private LayoutInflater childInflater;
	private LayoutInflater groupInflater;
	private GroupViewHolder gvh;
	private ChildViewHolder cvh;
	private ArrayList<IMFriendsG> friendsGList;
	private ArrayList<IMFriendsList> friendsCList;
	
	
	private HashMap<String, Integer> messageNum = new HashMap<String, Integer>();//记录未读消息数量
	
	public HashMap<String, String> userState = new HashMap<String, String>();//记录在线状态
	
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	
	private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
	
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	
	public IMFriendsEListViewAdapter(Context ctx) {
		this.context = ctx;
		this.childInflater = LayoutInflater.from(ctx);
		this.groupInflater = LayoutInflater.from(ctx);
	}
	



	public ArrayList<IMFriendsG> getFriendsGList() {
		return friendsGList;
	}




	public void setFriendsGList(ArrayList<IMFriendsG> friendsGList) {
		this.friendsGList = friendsGList;
	}




	public ArrayList<IMFriendsList> getFriendsCList() {
		return friendsCList;
	}




	public void setFriendsCList(ArrayList<IMFriendsList> friendsCList) {
		this.friendsCList = friendsCList;
	}


	public HashMap<String, Integer> getMessageNum() {
		return messageNum;
	}

	public void setMessageNum(HashMap<String, Integer> messageNum) {
		this.messageNum = messageNum;
	}

	public HashMap<String, String> getUserState() {
		return userState;
	}


	public void setUserState(HashMap<String, String> userState) {
		this.userState = userState;
	}


	@Override
	public Object getChild(int groupPosition, int childPosition) {
		IMFriendsList bean = friendsCList.get(childPosition);
		return bean;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return friendsCList == null ? 0 : friendsCList.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		IMFriendsG bean = friendsGList.get(groupPosition);
		return bean;
	}

	@Override
	public int getGroupCount() {
		return friendsGList == null ? 0 : friendsGList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}
	

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		 if (convertView == null){
			 convertView = this.childInflater.inflate(R.layout.listview_friends_child_item, null);
			 cvh = new ChildViewHolder();
			 cvh.nameID = convertView.findViewById(R.id.nameID);
			 cvh.msgNumID = convertView.findViewById(R.id.msgNumID);
			 cvh.imageGoodsPic = convertView.findViewById(R.id.imageGoodsPic);
			 cvh.userStateID = convertView.findViewById(R.id.userStateID);
			 convertView.setTag(cvh);
		}else{
			cvh = (ChildViewHolder)convertView.getTag();
		}
		 IMFriendsList cbean = friendsCList.get(childPosition);
		 
		 if(cbean !=null ){
			 cvh.nameID.setText(cbean.getU_name()== null ? "" : cbean.getU_name());
			 imageLoader.displayImage(cbean.getAvatar(), cvh.imageGoodsPic, options, animateFirstListener);
		 }
			if(userState != null && userState.size() > 0){
				if(userState.get(cbean.getU_id()) != null && userState.get(cbean.getU_id()).equals("1")){
					cvh.userStateID.setBackgroundResource(R.drawable.icon_im_online);
					
					 if(messageNum != null && messageNum.size() > 0 && messageNum.get(cbean.getU_id())!=null){
							int mag = messageNum.get(cbean.getU_id());
							cvh.msgNumID.setText(mag+"");
							cvh.msgNumID.setVisibility(View.VISIBLE);
					}else{
						cvh.msgNumID.setVisibility(View.GONE);
					}
					
				}else{
					cvh.userStateID.setBackgroundResource(R.drawable.icon_im_no_online);
					cvh.msgNumID.setVisibility(View.GONE);
				}
			}else{
				cvh.userStateID.setBackgroundResource(R.drawable.icon_im_no_online);
				cvh.msgNumID.setVisibility(View.GONE);
			}
		 
		 return convertView;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		if (convertView == null){
			convertView = this.groupInflater.inflate(R.layout.listview_friends_group_item, null);
			gvh = new GroupViewHolder();
			gvh.group_name = convertView.findViewById(R.id.group_name);
			gvh.group_indicator = convertView.findViewById(R.id.group_indicator);
			convertView.setTag(gvh);
		}else{
			gvh = (GroupViewHolder)convertView.getTag();
		}
		IMFriendsG bean = friendsGList.get(groupPosition);
		
		if(bean != null ){
			gvh.group_name.setText(bean.getName() == null ? "" : bean.getName());
		}
		
		if (isExpanded) {
			gvh.group_indicator.setImageResource(R.drawable.qb_down);
		} else {
			gvh.group_indicator.setImageResource(R.drawable.qb_right);
		}
		
	    return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}
	

	class GroupViewHolder{
		TextView group_name;
		ImageView group_indicator;
	}
	
	class ChildViewHolder{
		TextView nameID,msgNumID;
		ImageView imageGoodsPic,userStateID;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
