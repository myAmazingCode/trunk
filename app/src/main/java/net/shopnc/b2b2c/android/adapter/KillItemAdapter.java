package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.KillBuyItemBean;
import net.shopnc.b2b2c.android.bean.KillSecondListBean;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yuanshuo on 2017/9/21.
 */

public class KillItemAdapter extends RecyclerView.Adapter<KillItemAdapter.ViewHolder> {
    private Context context;
    private List<KillBuyItemBean> list;
    private String rmb;

    public KillItemAdapter(Context context, List<KillBuyItemBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_kill_adapter_item, null);
        ViewHolder holder = new ViewHolder(view);
        rmb= context.getResources().getString(R.string.money_rmb);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvKillPrice.setText(rmb+list.get(position).getSpike_price());
        holder.tvOriginPrice.setText(rmb+list.get(position).getGoods_price());
        holder.tvOriginPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
        Glide.with(context)
                .load(list.get(position).getGoods_img_url())
                .into(holder.ivSpikePicture);
        holder.ivSpikePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodsDetailsActivity.class);
                intent.putExtra("goods_id", list.get(position).getGoods_id());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list.size() >= 3) {
            return 3;
        } else  {
            return list.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.iv_spike_picture)
        ImageView ivSpikePicture;
        @Bind(R.id.tv_kill_price)
        TextView tvKillPrice;
        @Bind(R.id.tv_origin_price)
        TextView tvOriginPrice;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
