/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.adapter
 * FileNmae:CommendGridViewAdapter.java
 */
package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;

import java.util.ArrayList;

/**
 * @author KingKong·HE
 * @Time 2014-1-6 下午12:06:09
 * @E-mail hjgang@bizpoer.com
 */
public class GoodsParamListViewAdapter extends BaseAdapter{
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<String> lists;
	
	public GoodsParamListViewAdapter(Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		return lists ==  null ? 0 : lists.size();
	}

	@Override
	public Object getItem(int position) {
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public ArrayList<String> getLists() {
		return lists;
	}
	public void setLists(ArrayList<String> lists) {
		this.lists = lists;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listview_goods_param_item_view, null);
			holder = new ViewHolder();
			holder.paramID = convertView.findViewById(R.id.paramID);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		String param = lists.get(position);
		
		holder.paramID.setText(param);
		
		return convertView;
	}
	class ViewHolder {
		TextView paramID;
	}
}
