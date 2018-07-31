package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.GoodsList;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;

import java.util.ArrayList;

/**
 * 店铺GoodsGridView适配器
 * 
 * @author KingKong-HE
 * @Time 2015年1月4日
 * @Email KingKong@QQ.COM
 */
public class StoreGoodsMyGridViewListAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<GoodsList> goodsDatas;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	public StoreGoodsMyGridViewListAdapter(Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return goodsDatas == null ? 0 : goodsDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return goodsDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public ArrayList<GoodsList> getGoodsList() {
		return goodsDatas;
	}

	public void setGoodsList(ArrayList<GoodsList> goodsDatas) {
		this.goodsDatas = goodsDatas;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (null == convertView) {
			convertView = inflater.inflate(
					R.layout.tab_home_item_goods_gridview_item, null);
			holder = new ViewHolder();
			holder.ImageViewImagePic01 = convertView
					.findViewById(R.id.ImageViewImagePic01);
			holder.TextViewTitle = convertView
					.findViewById(R.id.TextViewTitle);
			holder.TextViewPrice = convertView
					.findViewById(R.id.TextViewPrice);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		GoodsList bean = goodsDatas.get(position);

		holder.TextViewTitle.setText(bean.getGoods_name());
		holder.TextViewPrice.setText("￥" + bean.getGoods_price());

		imageLoader.displayImage(bean.getGoods_image_url(),holder.ImageViewImagePic01, options, animateFirstListener);
		OnImageViewClick(holder.ImageViewImagePic01, "goods",bean.getGoods_id());

		return convertView;
	}

	class ViewHolder {
		ImageView ImageViewImagePic01;
		TextView TextViewTitle;
		TextView TextViewPrice;
	}

	public void OnImageViewClick(View view, final String type, final String data) {
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (type.equals("goods")) {// 商品编号
					Intent intent = new Intent(context,GoodsDetailsActivity.class);
					intent.putExtra("goods_id", data);
					context.startActivity(intent);
				}
			}
		});
	}
}
