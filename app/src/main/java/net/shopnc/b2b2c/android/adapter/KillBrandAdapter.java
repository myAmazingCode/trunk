package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.KillBrandBean;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;
import net.shopnc.b2b2c.android.xrefresh.recyclerview.BaseRecyclerAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yuanshuo on 2017/9/22.
 */

public class KillBrandAdapter extends RecyclerView.Adapter<KillBrandAdapter.ViewHolder>{
//    RecyclerView.Adapter<KillBrandAdapter.ViewHolder>
//} {
    private Context context;
    private List<KillBrandBean.GoodsListBean> goods_list;
    private String rmb;

    public KillBrandAdapter(Context context,List<KillBrandBean.GoodsListBean> goods_list) {
        this.context = context;
        this.goods_list = goods_list;
        rmb = context.getResources().getString(R.string.money_rmb);
    }

//    public void setDatas(List<KillBrandBean.GoodsListBean> goods_list) {
//        this.goods_list.addAll(goods_list);
//
//        notifyDataSetChanged();
//    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.kill_brand_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvItemName.setText(goods_list.get(position).getGoods_name());
        holder.tvKillPrice.setText(rmb + goods_list.get(position).getSpike_price());
        holder.tvOriginPrice.setText(rmb + goods_list.get(position).getGoods_price());
        holder.tvOriginPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        Glide.with(context)
                .load(goods_list.get(position).getGoods_img_url())
                .into(holder.ivItemPicture);
        holder.tvProgress.setText("已秒" + goods_list.get(position).getSpike_percent() + "%");
        holder.progress.setProgress(goods_list.get(position).getSpike_percent());
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodsDetailsActivity.class);
                intent.putExtra("goods_id", goods_list.get(position).getGoods_id());
                context.startActivity(intent);
            }
        });
        holder.btnGoKill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodsDetailsActivity.class);
                intent.putExtra("goods_id", goods_list.get(position).getGoods_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return goods_list==null?0:goods_list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_item_picture)
        ImageView ivItemPicture;
        @Bind(R.id.tv_item_name)
        TextView tvItemName;
        @Bind(R.id.tv_kill_price)
        TextView tvKillPrice;
        @Bind(R.id.btn_go_kill)
        Button btnGoKill;
        @Bind(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @Bind(R.id.progress)
        ProgressBar progress;
        @Bind(R.id.tv_progress)
        TextView tvProgress;
        @Bind(R.id.layout_item)
        RelativeLayout layoutItem;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
