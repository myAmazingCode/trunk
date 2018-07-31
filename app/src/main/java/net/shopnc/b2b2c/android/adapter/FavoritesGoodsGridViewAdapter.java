package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.FavoritesList;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.ncinterface.INCOnItemDel;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;

import java.util.ArrayList;

/**
 * 收藏商品列表适配器
 *
 * @author KingKong·HE
 * @Time 2014-1-6 下午12:06:09
 * @E-mail hjgang@bizpoer.com
 */
public class FavoritesGoodsGridViewAdapter extends BaseAdapter {
    private Context context;

    private LayoutInflater inflater;

    private ArrayList<FavoritesList> fList;

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    private INCOnItemDel incOnItemDel;

    public FavoritesGoodsGridViewAdapter(Context context, INCOnItemDel incOnItemDel) {
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

    public ArrayList<FavoritesList> getfList() {
        return fList;
    }

    public void removeItem(int position) {
        fList.remove(position);
    }

    public void setfList(ArrayList<FavoritesList> fList) {
        this.fList = fList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.gridview_favorites_goods_item, null);
            holder = new ViewHolder();
            holder.llGoodsItem = convertView.findViewById(R.id.llGoodsItem);
            holder.imageGoodsPic = convertView.findViewById(R.id.imageGoodsPic);
            holder.textGoodsName = convertView.findViewById(R.id.textGoodsName);
            holder.textGoodsPrice = convertView.findViewById(R.id.textGoodsPrice);
            holder.rlFavGoodsDel = convertView.findViewById(R.id.rlFavGoodsDel);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        FavoritesList bean = fList.get(position);
        final String favId = bean.getFav_id();

        holder.llGoodsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GoodsDetailsActivity.class);
                intent.putExtra("goods_id", favId);
                context.startActivity(intent);
            }
        });
        holder.textGoodsName.setText(bean.getGoods_name() == null ? "" : bean.getGoods_name());
        holder.textGoodsPrice.setText("￥" + (bean.getGoods_price() == null ? "0.00" : bean.getGoods_price()));
        imageLoader.displayImage(bean.getGoods_image_url(), holder.imageGoodsPic, options, animateFirstListener);
        holder.rlFavGoodsDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (incOnItemDel != null) {
                    incOnItemDel.onDel(favId, position);
                }
            }
        });

        return convertView;
    }

    public class ViewHolder {
        LinearLayout llGoodsItem;
        ImageView imageGoodsPic;
        TextView textGoodsName;
        TextView textGoodsPrice;
        RelativeLayout rlFavGoodsDel;
    }
}
