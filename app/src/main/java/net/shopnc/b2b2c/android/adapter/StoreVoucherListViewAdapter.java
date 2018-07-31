package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.StoreVoucherList;

import java.util.ArrayList;


/**
 * ListView分类选项的适配器<br/>
 * @author hjgang
 */
public class StoreVoucherListViewAdapter extends BaseAdapter {
	/** 存放选项数据的集合 */
	private ArrayList<StoreVoucherList> datas;
	/** 布局加载器 */
	private LayoutInflater inflater;
//	private int size;
	/**
	 * 构造方法
	 * @param ctx
	 */
	public StoreVoucherListViewAdapter(Context ctx) {
		inflater = LayoutInflater.from(ctx);
	}

	@Override
	public int getCount() {
		return datas == null ? 0 : datas.size();
	}

	public Object getItem(int index) {
		return datas.get(index);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listivew_voucher_item,
					null);
			vh = new ViewHolder();
			vh.txt_title = convertView.findViewById(R.id.searchKeyWord);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		
		StoreVoucherList bean = datas.get(position);
		vh.txt_title.setText(bean.getDesc());
		
		return convertView;
	}

	public ArrayList<StoreVoucherList> getDatas() {
		return datas;
	}

	public void setDatas(ArrayList<StoreVoucherList> datas) {
		this.datas = datas;
	}
	public class ViewHolder {
		TextView txt_title;
	}
}


