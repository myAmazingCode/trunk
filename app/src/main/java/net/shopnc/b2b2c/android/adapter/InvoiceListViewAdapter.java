package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.InvoiceList;

import java.util.ArrayList;

/**
 * 发票列表适配器
 * @author KingKong·HE
 * @Time 2015-1-6 下午12:06:09
 * @E-mail hjgang@bizpoer.com 
 */
public class InvoiceListViewAdapter extends BaseAdapter{
	
	private Context context;
	
	private LayoutInflater inflater;
	
	private ArrayList<InvoiceList> invoiceList;

	public InvoiceListViewAdapter(Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		return invoiceList == null ? 0 : invoiceList.size();
	}

	@Override
	public Object getItem(int position) {
		return invoiceList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public ArrayList<InvoiceList> getInvoiceList() {
		return invoiceList;
	}
	public void setInvoiceList(ArrayList<InvoiceList> invoiceList) {
		this.invoiceList = invoiceList;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listview_invoice_item, null);
			holder = new ViewHolder();
			holder.textID = convertView.findViewById(R.id.textID);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		InvoiceList bean = invoiceList.get(position);
		 
		if(bean != null){
			holder.textID.setText((bean.getInv_title() == null ? "" :bean.getInv_title())+(bean.getInv_content() == null ? "" :bean.getInv_content()));
		}
		
		return convertView;
	}
	class ViewHolder {
		TextView textID;
	}
}
