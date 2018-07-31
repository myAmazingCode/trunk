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
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.CommendList;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.SystemHelper;

import java.util.ArrayList;

/**
 * @author KingKong·HE
 * @Time 2014-1-6 下午12:06:09
 * @E-mail hjgang@bizpoer.com
 */
public class CommendGridViewAdapter extends BaseAdapter{
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<CommendList> commendLists;
	
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	private TextView specGoodsPriceID;
	
	public CommendGridViewAdapter(Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		return commendLists ==  null ? 0 : commendLists.size();
	}

	@Override
	public Object getItem(int position) {
		return commendLists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public ArrayList<CommendList> getCommendLists() {
		return commendLists;
	}
	public void setCommendLists(ArrayList<CommendList> commendLists) {
		this.commendLists = commendLists;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (null == convertView) {
			convertView = inflater.inflate(R.layout.gridview_commend_item, null);
			holder = new ViewHolder();
			holder.imageviewPIC = convertView.findViewById(R.id.imageviewPIC);
			holder.textGoodsCommendName = convertView.findViewById(R.id.textGoodsCommendName);
			holder.textGoodsCommendPrice = convertView.findViewById(R.id.textGoodsCommendPrice);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		CommendList bean =  commendLists.get(position);
		holder.textGoodsCommendName.setText(bean.getGoods_name()  == null ? "" : bean.getGoods_name());
		
		if(bean.getPromotion_price() != null && !bean.getPromotion_price().equals("") && !bean.getPromotion_price().equals("null")){
			holder.textGoodsCommendPrice.setText("￥"+(bean.getPromotion_price() == null ? "0.00" : bean.getPromotion_price()));
		}else{
			holder.textGoodsCommendPrice.setText("￥"+(bean.getGoods_price() == null ? "0.00" : bean.getGoods_price()));
		}
		
		imageLoader.displayImage(bean.getGoods_image_url(), holder.imageviewPIC, options, animateFirstListener);
		
		return convertView;
	}
	class ViewHolder {
		ImageView imageviewPIC;
		TextView textGoodsCommendName;
		TextView textGoodsCommendPrice;
	}
}
