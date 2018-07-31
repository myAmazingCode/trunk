package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.CreditsCartBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 积分中心购物车适配器
 * Created by yuanshuo on 2017/10/20.
 */

public class CreditsCartAdapter extends RecyclerView.Adapter<CreditsCartAdapter.ViewHolder> {

    private Context context;
    private CreditsCartBean bean;

    public CreditsCartAdapter(Context context, CreditsCartBean bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_credits_cart_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CreditsCartBean.CartListBean listBean = bean.getCart_list().get(position);

        Glide.with(context)
                .load(bean.getCart_list().get(position).getPgoods_image_small())
                .into(holder.ivPict);
        holder.tvTitle.setText(listBean.getPgoods_name());
        holder.tvNeedCredits.setText("兑换积分" + listBean.getPgoods_points());
        holder.tvCreditsSubtotal.setText("积分小计" + listBean.getPgoods_pointone());
        holder.tvAppCommonCount.setText(listBean.getPgoods_choosenum());
        //点击增加按钮
        holder.btnAppCommonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //点击减少按钮
        holder.btnAppCommonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //点击删除按钮
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return bean.getCart_list().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.iv_pict)
        ImageView ivPict;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.iv_delete)
        ImageView ivDelete;
        @Bind(R.id.tv_need_credits)
        TextView tvNeedCredits;
        @Bind(R.id.tv_credits_subtotal)
        TextView tvCreditsSubtotal;
        @Bind(R.id.btnAppCommonMinus)
        Button btnAppCommonMinus;
        @Bind(R.id.tvAppCommonCount)
        TextView tvAppCommonCount;
        @Bind(R.id.btnAppCommonAdd)
        Button btnAppCommonAdd;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
