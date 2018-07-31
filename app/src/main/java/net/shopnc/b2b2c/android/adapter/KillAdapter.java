package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import net.shopnc.b2b2c.android.bean.KillBuyItemBean;
import net.shopnc.b2b2c.android.bean.KillSecondListBean;
import net.shopnc.b2b2c.android.ui.fenxiao.SecondKillBrandActivity;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;
import net.shopnc.b2b2c.android.utils.SpaceItemDecoration;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yuanshuo on 2017/9/21.
 */

public class KillAdapter extends RecyclerView.Adapter<KillAdapter.ViewHolder> {
    private Context context;

    private List<KillSecondListBean.DatasBean.SpikeListBean> spike_list;
    private Map<String, List<KillBuyItemBean>> recom_goods;
    private String rmb;
    public KillAdapter(Context context, List<KillSecondListBean.DatasBean.SpikeListBean> spike_list, Map<String, List<KillBuyItemBean>> recom_goods) {
        this.context = context;
        this.spike_list = spike_list;
        this.recom_goods = recom_goods;

        rmb = context.getResources().getString(R.string.money_rmb);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.kill_adapter_layout, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(context)
                .load(spike_list.get(position).getSpike_common_bg())
                .into(holder.bgRelative);


        if (recom_goods.get(spike_list.get(position).getSpike_id()) != null) {
            final List<KillBuyItemBean> list=recom_goods.get(spike_list.get(position).getSpike_id());
            KillItemAdapter adapter = new KillItemAdapter(context, list);
            GridLayoutManager manager = new GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false);
            holder.recyclerItem.addItemDecoration(new SpaceItemDecoration(5));
            holder.recyclerItem.setLayoutManager(manager);
            holder.recyclerItem.setAdapter(adapter);

            if (list.size() >= 4) {
                holder.layoutItem.setVisibility(View.VISIBLE);
                holder.tvItemName.setText(list.get(3).getGoods_name());
                holder.tvKillPrice.setText(rmb + list.get(3).getSpike_price());
                holder.tvOriginPrice.setText(rmb + list.get(3).getGoods_price());
                holder.tvOriginPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                Glide.with(context)
                        .load(list.get(3).getGoods_img_url())
                        .into(holder.ivItemPicture);
                holder.tvProgress.setText("已秒" + list.get(3).getSpike_percent() + "%");
                holder.progress.setProgress(list.get(3).getSpike_percent());
                holder.layoutItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, GoodsDetailsActivity.class);
                        intent.putExtra("goods_id", list.get(3).getGoods_id());
                        context.startActivity(intent);
                    }
                });
                holder.btnGoKill.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, GoodsDetailsActivity.class);
                        intent.putExtra("goods_id", list.get(3).getGoods_id());
                        context.startActivity(intent);
                    }
                });
            }
        }
        holder.tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SecondKillBrandActivity.class);
                intent.putExtra("brand_id", spike_list.get(position).getSpike_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return spike_list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.black_line)
        View blackLine;
        @Bind(R.id.tv_more)
        TextView tvMore;
        @Bind(R.id.recycler_item)
        RecyclerView recyclerItem;
        @Bind(R.id.bg_relative)
        ImageView bgRelative;
        @Bind(R.id.item_view)
        RelativeLayout itemView;
        @Bind(R.id.iv_item_picture)
        ImageView ivItemPicture;
        @Bind(R.id.tv_item_name)
        TextView tvItemName;
        @Bind(R.id.tv_kill_price)
        TextView tvKillPrice;
        @Bind(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @Bind(R.id.btn_go_kill)
        Button btnGoKill;
        @Bind(R.id.tv_progress)
        TextView tvProgress;
        @Bind(R.id.progress)
        ProgressBar progress;
        @Bind(R.id.layout_item)
        RelativeLayout layoutItem;

        ViewHolder(View view) {
            super(view);
//            View view = LayoutInflater.from(context).inflate(R.layout.kill_adapter_layout, null);
            ButterKnife.bind(this, view);
        }

    }

}
