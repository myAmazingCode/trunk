package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.ta.utdid2.android.utils.StringUtils;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.IMMemberInFo;
import net.shopnc.b2b2c.android.bean.IMMsgList;
import net.shopnc.b2b2c.android.bean.SmiliesList;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.SystemHelper;

import java.util.ArrayList;

/**
 * 聊天列表适配器
 * @author KingKong·HE
 * @Time 2014-1-6 下午12:06:09
 */
public class SendMsgListViewAdapter extends BaseAdapter{
	
	public interface IMsgViewType
	{
		int IMVT_COM_MSG = 0;
		int IMVT_TO_MSG = 1;
	}
	
	private Context context;
	private LayoutInflater inflater;
	public ViewHolder holder;
	private IMMemberInFo imMemberInFo;
	private ArrayList<IMMsgList> imMsgList;
	private ArrayList<SmiliesList> smiliesLists;
	
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	
	private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
	
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	
	public SendMsgListViewAdapter(Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.imMemberInFo = new IMMemberInFo();
		smiliesLists= new ArrayList<SmiliesList>();
	}
	@Override
	public int getCount() {
		return imMsgList == null ? 0 : imMsgList.size();
	}

	@Override
	public Object getItem(int position) {
		return imMsgList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public IMMemberInFo getImMemberInFo() {
		return imMemberInFo;
	}
	public void setImMemberInFo(IMMemberInFo imMemberInFo) {
		this.imMemberInFo = imMemberInFo;
	}
	
	public ArrayList<SmiliesList> getSmiliesLists() {
		return smiliesLists;
	}
	public void setSmiliesLists(ArrayList<SmiliesList> smiliesLists) {
		this.smiliesLists = smiliesLists;
	}
	@Override
	public int getItemViewType(int position) {
		IMMsgList bean = imMsgList.get(position);
		if (bean.isViewFlag())
	 	{
	 		return IMsgViewType.IMVT_COM_MSG;
	 	}else{
	 		return IMsgViewType.IMVT_TO_MSG;
	 	}
	}
	
	public int getViewTypeCount() {
		return 2;
	}
	
	public ArrayList<IMMsgList> getIMMsgList() {
		return imMsgList;
	}
	public void setIMMsgList(ArrayList<IMMsgList> iMMsgList) {
		imMsgList = iMMsgList;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		IMMsgList bean = imMsgList.get(position);
		boolean isComMsg = bean.isViewFlag();
		if (null == convertView) {
			if(isComMsg){
				convertView = inflater.inflate(R.layout.listivew_send_msg_left_item, null);
			}else{
				convertView = inflater.inflate(R.layout.listivew_send_msg_right_item, null);
			}
			holder = new ViewHolder();
			holder.tv_username = convertView.findViewById(R.id.tv_username);
			holder.tv_chatcontent = convertView.findViewById(R.id.tv_chatcontent);
			holder.tv_sendtime = convertView.findViewById(R.id.tv_sendtime);
			holder.iv_userhead = convertView.findViewById(R.id.iv_userhead);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		IMMsgList imbean = IMMsgList.newInstanceUserBean(bean.getUser() == null ? "" : bean.getUser());
		if(isComMsg){
			holder.tv_username.setText(bean.getF_name() == null ? "" : bean.getF_name());
//			holder.tv_chatcontent.setText(bean.getT_msg());
//			holder.tv_sendtime.setText(bean.getAdd_time() == null ? "" : bean.getAdd_time());
//			String avatar = imbean.getAvatar();
			if(imbean != null){
				imageLoader.displayImage( imbean.getAvatar() == null ? "" : imbean.getAvatar(), holder.iv_userhead, options, animateFirstListener);
			}

		}else{
			holder.tv_username.setText(bean.getT_name()  == null ? "" : bean.getT_name());
//			holder.tv_chatcontent.setText(bean.getT_msg());
			if(imbean != null){
				imageLoader.displayImage(imbean.getAvatar() == null ? "" : imbean.getAvatar(), holder.iv_userhead, options, animateFirstListener);
			}
		}
		holder.tv_sendtime.setText(bean.getAdd_time()  == null ? "" : bean.getAdd_time());
		
		holder.tv_chatcontent.setText("");
		String msg = bean.getT_msg();
		SpannableString spannableString = new SpannableString(msg);
		for(int i =0 ;i < smiliesLists.size() ; i++){
			for(int j =0 ;j < smiliesLists.size() ; j++){
				int num = msg.indexOf(smiliesLists.get(j).getTitle());
				if(num != -1){
					String str = "";
					for(int k = 0 ; k < smiliesLists.get(j).getTitle().length() ;k++){
						str += "1";
					}
					msg = msg.replaceFirst(smiliesLists.get(j).getTitle(),str);
					int start = num ;
					int end = num  +smiliesLists.get(j).getTitle().length();
					Bitmap bitmap = null;
					bitmap = BitmapFactory.decodeResource(context.getResources(), smiliesLists.get(j).getPath());
					ImageSpan imageSpan = new ImageSpan(context, bitmap);
					spannableString.setSpan(imageSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				}
			}
		}
		holder.tv_chatcontent.append(spannableString);
		return convertView;
	}
	public class ViewHolder {
		TextView tv_username;
		TextView tv_chatcontent;
		TextView tv_sendtime;
		ImageView iv_userhead;
	}
}
