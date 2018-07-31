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
import net.shopnc.b2b2c.android.bean.FavStoreList;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.ncinterface.INCOnItemDel;

import java.util.ArrayList;

/**
 * 收藏商品列表适配器
 * @author KingKong·HE
 * @Time 2014-1-6 下午12:06:09
 * @E-mail hjgang@bizpoer.com 
 */
public class FavoritesStoreListViewAdapter extends BaseAdapter{
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<FavStoreList> fList;
	private INCOnItemDel incOnItemDel;
	
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	
	public FavoritesStoreListViewAdapter(Context context, INCOnItemDel incOnItemDel) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.incOnItemDel = incOnItemDel;
	}
	@Override
	public int getCount() {
		return fList == null ? 0 : fList.size();
	}

	@Override
	public Object getItem(int position) {
		return fList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void removeItem(int position) {
		fList.remove(position);
	}
	
	public ArrayList<FavStoreList> getfList() {
		return fList;
	}
	public void setfList(ArrayList<FavStoreList> fList) {
		this.fList = fList;
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listivew_favorites_store_item, null);
			holder = new ViewHolder();
			holder.imageGoodsPic = convertView.findViewById(R.id.imageGoodsPic);
			holder.nameID = convertView.findViewById(R.id.nameID);
			holder.goodsNumID = convertView.findViewById(R.id.goodsNumID);
			holder.favNumID = convertView.findViewById(R.id.favNumID);
			holder.ivFavDel = convertView.findViewById(R.id.ivFavDel);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		FavStoreList bean = fList.get(position);
		final String storeId = bean.getStore_id();
		
		holder.nameID.setText(bean.getStore_name() == null ? "" : bean.getStore_name());
        holder.goodsNumID.setText("商品："+(bean.getGoods_count() == null ? "0" : bean.getGoods_count()));
        holder.favNumID.setText("粉丝："+(bean.getStore_collect() == null ? "0" : bean.getStore_collect()));
        imageLoader.displayImage(bean.getStore_avatar_url(), holder.imageGoodsPic, options, animateFirstListener);
		holder.ivFavDel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				incOnItemDel.onDel(storeId, position);
			}
		});
		
		return convertView;
	}
	public class ViewHolder {
		ImageView imageGoodsPic,ivFavDel;
		TextView nameID,goodsNumID,favNumID;
	}
}
