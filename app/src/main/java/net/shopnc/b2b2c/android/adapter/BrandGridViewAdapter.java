package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.BrandInfo;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.ui.type.GoodsListFragmentManager;

import java.util.ArrayList;

/**
 * 品牌列表适配器
 */
public class BrandGridViewAdapter extends BaseAdapter{

	private Context context;
	private LayoutInflater inflater;
	private ArrayList<BrandInfo> brandInfoArrayList;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	public BrandGridViewAdapter(Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		return brandInfoArrayList == null ? 0 : brandInfoArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return brandInfoArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public ArrayList<BrandInfo> getBrandInfoArrayList() {
		return brandInfoArrayList;
	}
	public void setBrandArray(ArrayList<BrandInfo> brandInfoArrayList) {
		this.brandInfoArrayList = brandInfoArrayList;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (null == convertView) {
			convertView = inflater.inflate(R.layout.gridview_brand_item, null);
			holder = new ViewHolder();
			holder.llBrandItem = convertView.findViewById(R.id.llBrandItem);
			holder.ivBrandPic = convertView.findViewById(R.id.ivBrandPic);
			holder.tvBrandName = convertView.findViewById(R.id.tvBrandName);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		final BrandInfo brandInfo = brandInfoArrayList.get(position);
		holder.llBrandItem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, GoodsListFragmentManager.class);
				intent.putExtra("b_id", brandInfo.getBrandId());
				intent.putExtra("gc_name", brandInfo.getBrandName());
				context.startActivity(intent);
			}
		});
		imageLoader.displayImage(brandInfo.getBrandPic(), holder.ivBrandPic, options, animateFirstListener);
		holder.tvBrandName.setText(brandInfo.getBrandName());
		return convertView;
	}
	class ViewHolder {
		LinearLayout llBrandItem;
		ImageView ivBrandPic;
		TextView tvBrandName;
	}
}
