package net.shopnc.b2b2c.android.adapter;

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
import net.shopnc.b2b2c.android.common.LoadImage;
import net.shopnc.b2b2c.android.lib.tab.OnItemClickListener;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;

import java.util.ArrayList;

/**
 * Created by snm on 2016/9/22.
 */
public class FenxiaoGoodsMaiAdapter extends RecyclerView.Adapter<FenxiaoGoodsMaiAdapter.MyViewHolder> {

    private OnItemClickListener onItemClickListener;
    private Context mContext;
    private LayoutInflater inflater;
    ArrayList<FenxiaoGoods> mList = new ArrayList<FenxiaoGoods>();

    public FenxiaoGoodsMaiAdapter(Context context){
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }


    public void setmList(ArrayList<FenxiaoGoods> list) {
//        mList.clear();
        this.mList = list;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout =inflater.inflate(R.layout.activity_fenxiao_goods_items, parent, false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int pos) {
        FenxiaoGoods goods = mList.get(pos);
        holder.textGoodsName.setText(goods.getGoods_name());
        holder.textGoodsPrice.setText("￥" + goods.getGoods_price());
        LoadImage.loadImg(mContext,holder.imageGoodsPic,goods.getGoods_image_url());

        holder.textGoodsPrice.setOnClickListener(new goGoodsInfo(mContext,goods.getGoods_commonid()));
        holder.textGoodsName.setOnClickListener(new goGoodsInfo(mContext,goods.getGoods_commonid()));
        holder.imageGoodsPic.setOnClickListener(new goGoodsInfo(mContext,goods.getGoods_commonid()));

        holder.ll_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != onItemClickListener){
                    onItemClickListener.onClick(pos);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textGoodsName,textGoodsPrice;
        ImageView imageGoodsPic;
        LinearLayout ll_del;
        public MyViewHolder(View view) {
            super(view);
            imageGoodsPic = view.findViewById(R.id.imageGoodsPic);
            textGoodsName = view.findViewById(R.id.textGoodsName);
            textGoodsPrice = view.findViewById(R.id.textGoodsPrice);
            ll_del = view.findViewById(R.id.ll_del);
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
//            Intent intent = new Intent(context, GoodsDetailsActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.putExtra("goods_id", goodsId);
//            context.startActivity(intent);
        }
    }

}