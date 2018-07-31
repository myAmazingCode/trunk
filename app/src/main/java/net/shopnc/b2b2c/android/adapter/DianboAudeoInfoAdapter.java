package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.common.LoadImage;
import net.shopnc.b2b2c.android.bean.GoodsCommonLists;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;

import java.util.ArrayList;

/**
 * Created by snm on 2016/8/30.
 */
public class DianboAudeoInfoAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<GoodsCommonLists> mList;
    public DianboAudeoInfoAdapter(Context context){
        this.mContext = context;
    }

    public void setmList(ArrayList<GoodsCommonLists> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_audeo_dianbo_content_item,null);
            holder = new MyViewHolder();

            holder.goods_add = convertView.findViewById(R.id.goods_add);
            holder.goods_img = convertView.findViewById(R.id.goods_img);
            holder.goods_name = convertView.findViewById(R.id.goods_name);
            holder.goods_price = convertView.findViewById(R.id.goods_price);
            holder.goods_old_price = convertView.findViewById(R.id.goods_old_price);

            convertView.setTag(holder);
        }else {
            holder = (MyViewHolder)convertView.getTag();
        }
        final GoodsCommonLists goods = mList.get(position);
        String goods_image = goods.getGoods_image_url();
        LoadImage.loadImg(mContext,holder.goods_img,goods_image);
        holder.goods_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addGouWuListener != null){
                    addGouWuListener.ShowaddgouwuPop(goods);
                }
            }
        });

        holder.goods_img.setOnClickListener(new GoodsInfoOnckerListener(goods.getGoods_id()));
        holder.goods_name.setOnClickListener(new GoodsInfoOnckerListener(goods.getGoods_id()));
        holder.goods_price.setOnClickListener(new GoodsInfoOnckerListener(goods.getGoods_id()));
        holder.goods_old_price.setOnClickListener(new GoodsInfoOnckerListener(goods.getGoods_id()));

        holder.goods_name.setText(goods.getGoods_name());
        holder.goods_price.setText("￥" + goods.getGoods_price());
        holder.goods_old_price.setText("市场价格："+goods.getGoods_marketprice());

        return convertView;
    }
    public class MyViewHolder{
        ImageView goods_img,goods_add;
        TextView goods_name,goods_price,goods_old_price;

    }
    /*跳转到商品详情页面*/
    public class GoodsInfoOnckerListener implements View.OnClickListener{
        private String goods_id;

        public GoodsInfoOnckerListener(String newsListBean){
            this.goods_id = newsListBean;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, GoodsDetailsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("goods_id", goods_id);
            mContext.startActivity(intent);
        }
    }
    private addGouWuListener addGouWuListener;

    public void setAddGouWuListener(DianboAudeoInfoAdapter.addGouWuListener listener) {
        this.addGouWuListener = listener;
    }

    public interface addGouWuListener{
        void ShowaddgouwuPop(GoodsCommonLists goods);
    }
}
