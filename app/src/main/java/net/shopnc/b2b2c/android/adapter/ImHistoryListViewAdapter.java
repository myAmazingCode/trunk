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
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.IMHistoryList;
import net.shopnc.b2b2c.android.bean.IMMemberInFo;
import net.shopnc.b2b2c.android.bean.SmiliesList;
import net.shopnc.b2b2c.android.common.MyShopApplication;

import java.util.ArrayList;

/**
 * 聊天记录适配器
 * @author KingKong·HE
 * @Time 2014-1-6 下午12:06:09
 */
public class ImHistoryListViewAdapter extends BaseAdapter{
	
	public interface IMsgViewType
	{
		int IMVT_COM_MSG = 0;
		int IMVT_TO_MSG = 1;
	}
	
	private Context context;
	private LayoutInflater inflater;
	public ViewHolder holder;
	private IMMemberInFo imMemberInFo;
	private ArrayList<IMHistoryList> historyLists;
	private ArrayList<SmiliesList> smiliesLists;
	private MyShopApplication myApplication;
	
	public ImHistoryListViewAdapter(Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.imMemberInFo = new IMMemberInFo();
		smiliesLists= new ArrayList<SmiliesList>();
		myApplication = (MyShopApplication) context.getApplicationContext();
	}
	@Override
	public int getCount() {
		return historyLists == null ? 0 : historyLists.size();
	}

	@Override
	public Object getItem(int position) {
		return historyLists.get(position);
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
		IMHistoryList bean = historyLists.get(position);
		if (!bean.getF_id().equals(myApplication.getMemberID()))
	 	{
	 		return IMsgViewType.IMVT_COM_MSG;
	 	}else{
	 		return IMsgViewType.IMVT_TO_MSG;
	 	}
	}
	
	public int getViewTypeCount() {
		return 2;
	}
	
	public ArrayList<IMHistoryList> getHistoryLists() {
		return historyLists;
	}
	public void setHistoryLists(ArrayList<IMHistoryList> historyLists) {
		this.historyLists = historyLists;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		IMHistoryList bean = historyLists.get(position);
		if (null == convertView) {
			if(!bean.getF_id().equals(myApplication.getMemberID())){
				convertView = inflater.inflate(R.layout.listivew_history_msg_left_item, null);
			}else{
				convertView = inflater.inflate(R.layout.listivew_history_msg_right_item, null);
			}
			holder = new ViewHolder();
			holder.tv_username = convertView.findViewById(R.id.tv_username);
			holder.tv_chatcontent = convertView.findViewById(R.id.tv_chatcontent);
			holder.tv_sendtime = convertView.findViewById(R.id.tv_sendtime);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
	
		holder.tv_sendtime.setText(bean.getTime());
		holder.tv_username.setText(bean.getF_name());
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
	}
}
