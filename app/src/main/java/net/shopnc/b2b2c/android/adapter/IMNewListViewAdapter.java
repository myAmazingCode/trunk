package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
 * Created by snm on 2016/5/31.
 */
public class IMNewListViewAdapter extends BaseAdapter {

    public Context mContent;
    public ArrayList<IMFriendsList> friendsGList;
    private LayoutInflater inflater;
    private HashMap<String, Integer> messageNum = new HashMap<String, Integer>();//记录未读消息数量

    public HashMap<String, String> userState = new HashMap<String, String>();//记录在线状态

    protected ImageLoader imageLoader = ImageLoader.getInstance();

    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();

    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public IMNewListViewAdapter(Context context){
        this.mContent = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setFriendsGList(ArrayList<IMFriendsList> friendsGList) {
        this.friendsGList = friendsGList;
    }
    public HashMap<String, Integer> getMessageNum() {
        return messageNum;
    }

    public void setMessageNum(HashMap<String, Integer> messageNum) {
        this.messageNum = messageNum;
    }


    @Override
    public int getCount() {
        return friendsGList == null ? 0 : friendsGList.size();
    }

    @Override
    public Object getItem(int i) {
        return friendsGList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null){
            view = inflater.inflate(R.layout.listview_friends_child_item, null);
            holder = new ViewHolder();
            holder.nameID = view.findViewById(R.id.nameID);
            holder.msgNumID = view.findViewById(R.id.msgNumID);
            holder.imageGoodsPic = view.findViewById(R.id.imageGoodsPic);
            holder.userStateID = view.findViewById(R.id.userStateID);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }
        IMFriendsList cbean = friendsGList.get(i);

        if(cbean !=null ){
            holder.nameID.setText(cbean.getU_name()== null ? "" : cbean.getU_name());
            imageLoader.displayImage(cbean.getAvatar(), holder.imageGoodsPic, options, animateFirstListener);
        }
        if(userState != null && userState.size() > 0){
            if(userState.get(cbean.getU_id()) != null && userState.get(cbean.getU_id()).equals("1")){
                holder.userStateID.setBackgroundResource(R.drawable.icon_im_online);

                if(messageNum != null && messageNum.size() > 0 && messageNum.get(cbean.getU_id())!=null){
                    int mag = messageNum.get(cbean.getU_id());
                    holder.msgNumID.setText(mag+"");
                    holder.msgNumID.setVisibility(View.VISIBLE);
                }else{
                    holder.msgNumID.setVisibility(View.GONE);
                }

            }else{
                holder.userStateID.setBackgroundResource(R.drawable.icon_im_no_online);
                holder.msgNumID.setVisibility(View.GONE);
            }
        }else{
            holder.userStateID.setBackgroundResource(R.drawable.icon_im_no_online);
            holder.msgNumID.setVisibility(View.GONE);
        }

        return view;

    }

    public class ViewHolder {
        TextView nameID,msgNumID;
        ImageView imageGoodsPic,userStateID;
    }

}
