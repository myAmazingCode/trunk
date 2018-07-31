package net.shopnc.b2b2c.android.adapter.fenxiao;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.FenxiaoGoods;
import net.shopnc.b2b2c.android.bean.fenxiao.GoodsList;
import net.shopnc.b2b2c.android.common.LoadImage;
import net.shopnc.b2b2c.android.lib.tab.OnItemClickListener;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;

import java.util.ArrayList;

/**
 * Created by snm on 2016/9/28.
 */
public class ZhiBoGoodsMaiAdapter extends RecyclerView.Adapter<ZhiBoGoodsMaiAdapter.MyViewHolder> {

    private OnItemClickListener onItemClickListener;
    private Context mContext;
    private LayoutInflater inflater;
    ArrayList<GoodsList> mList  = new ArrayList<GoodsList>();
    boolean isShop = true;
    public ZhiBoGoodsMaiAdapter(Context context,boolean isShowShop){
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.isShop = isShowShop;
    }


    public void setmList(ArrayList<GoodsList> list) {
        mList.clear();
        this.mList = list;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout =inflater.inflate(R.layout.activity_zhibo_goods_item, parent, false);
//            View layout =inflater.inflate(R.layout.activity_fenxiao_goods_items, parent, false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int pos) {
//            LoadImage.loadImg(mContext,holder.iv_man_head,"");
//            Glide.with(mContext).load("http://img.firefoxchina.cn/2016/09/4/201609200816220.jpg").transform(new GlideCircleTransform(mContext)).into(holder.goods_img);
        GoodsList goods = mList.get(pos);
        LoadImage.loadImg(mContext,holder.goods_img,goods.getGoods_image_url());
        holder.goods_mun.setVisibility(View.GONE);
        holder.goods_price.setText("￥" + goods.getGoods_price());
        holder.goods_name.setText(goods.getGoods_name());
        if(isShop){
            holder.goods_add.setVisibility(View.VISIBLE);
        }else {
            holder.goods_add.setVisibility(View.GONE);
        }
        holder.goods_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onClick(pos);
                }
            }
        });
        holder.goods_img.setOnClickListener(new goGoodsInfo(mContext,goods.getGoods_id()));
        holder.goods_mun.setOnClickListener(new goGoodsInfo(mContext,goods.getGoods_id()));
        holder.goods_price.setOnClickListener(new goGoodsInfo(mContext,goods.getGoods_id()));
        holder.ll_goods.setOnClickListener(new goGoodsInfo(mContext,goods.getGoods_id()));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView goods_name,goods_price,goods_mun;
        ImageView goods_img,goods_add;
        LinearLayout ll_goods;
        public MyViewHolder(View view) {
            super(view);
            goods_img = view.findViewById(R.id.goods_img);
            goods_add = view.findViewById(R.id.goods_add);
            goods_name = view.findViewById(R.id.goods_name);
            goods_price = view.findViewById(R.id.goods_price);
            goods_mun = view.findViewById(R.id.goods_old_price);
            ll_goods = view.findViewById(R.id.ll_goods);
        }
    }


    public class goGoodsInfo implements View.OnClickListener{
        Context context;
        String goodsId;
        private goGoodsInfo(Context c,String id){
            this.context = c;
            this.goodsId = id;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, GoodsDetailsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("goods_id", goodsId);
            context.startActivity(intent);
        }
    }
}
