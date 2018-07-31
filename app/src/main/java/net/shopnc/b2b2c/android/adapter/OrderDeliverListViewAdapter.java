package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * 物流列表适配器
 * @author KingKong·HE
 * @Time 2014-1-6 下午12:06:09
 */
public class OrderDeliverListViewAdapter extends BaseAdapter{
	private Context context;
	private LayoutInflater inflater;
	private JSONArray jsonArray;
	public OrderDeliverListViewAdapter(Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		return jsonArray == null ? 0 : jsonArray.length();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public JSONArray getjsonArray() {
		return jsonArray;
	}
	public void setjsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listview_order_delive_item, null);
			holder = new ViewHolder();
			holder.deliverInFoID = convertView.findViewById(R.id.deliverInFoID);
			holder.xuID1 = convertView.findViewById(R.id.xuID1);
			holder.xuID2 = convertView.findViewById(R.id.xuID2);
			holder.iocID1 = convertView.findViewById(R.id.iocID1);
			holder.iocID2 = convertView.findViewById(R.id.iocID2);
			holder.iocID3 = convertView.findViewById(R.id.iocID3);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		if(position == 0){
			holder.xuID1.setVisibility(View.GONE);
			holder.xuID2.setVisibility(View.VISIBLE);
			holder.iocID1.setVisibility(View.VISIBLE);
			holder.iocID2.setVisibility(View.GONE);
			holder.iocID3.setVisibility(View.GONE);
		}else if (position == getCount()-1){
			holder.xuID1.setVisibility(View.VISIBLE);
			holder.xuID2.setVisibility(View.GONE);
			holder.iocID1.setVisibility(View.GONE);
			holder.iocID2.setVisibility(View.GONE);
			holder.iocID3.setVisibility(View.VISIBLE);
		}else{
			holder.xuID1.setVisibility(View.VISIBLE);
			holder.xuID2.setVisibility(View.VISIBLE);
			holder.iocID1.setVisibility(View.GONE);
			holder.iocID2.setVisibility(View.VISIBLE);
			holder.iocID3.setVisibility(View.GONE);
		}
		
		try {
			String str = jsonArray.getString(position);
			holder.deliverInFoID.setText(Html.fromHtml(str));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return convertView;
	}
	class ViewHolder {
		TextView deliverInFoID;
		ImageView xuID1,xuID2,iocID1,iocID2,iocID3;
	}
}
