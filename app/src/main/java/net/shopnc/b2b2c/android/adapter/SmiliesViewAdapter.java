package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.SmiliesList;

import java.util.ArrayList;

/**
 * 表情列表适配器
 * @author KingKong·HE
 * @Time 2014-1-6 下午12:06:09
 * @E-mail hjgang@bizpoer.com
 */
public class SmiliesViewAdapter extends BaseAdapter{
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<SmiliesList> smiliesLists;
	public SmiliesViewAdapter(Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		smiliesLists= new ArrayList<SmiliesList>();
	}
	@Override
	public int getCount() {
		return smiliesLists.size();
	}

	@Override
	public Object getItem(int position) {
		return smiliesLists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public ArrayList<SmiliesList> getSmiliesLists() {
		return smiliesLists;
	}
	public void setSmiliesLists(ArrayList<SmiliesList> smiliesLists) {
		this.smiliesLists = smiliesLists;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (null == convertView) {
			convertView = inflater.inflate(R.layout.im_msg_smilies_item, null);
			holder = new ViewHolder();
			holder.imagePic = convertView.findViewById(R.id.imagePic);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		SmiliesList bean = smiliesLists.get(position);
		
		holder.imagePic.setBackgroundResource(bean.getPath());
		
		return convertView;
	}
	class ViewHolder {
		ImageView imagePic;
	}
}
