package net.shopnc.b2b2c.android.ui.jifen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.MultiCreditsAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.PointsGoodsBean;
import net.shopnc.b2b2c.android.bean.RedPacketBean;
import net.shopnc.b2b2c.android.bean.VoucherBean;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;

public class CreditsMoreActivity extends BaseActivity {

    @Bind(R.id.credits_recycler)
    RecyclerView creditsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int type = getIntent().getIntExtra("type", 0);
        initData(type);
        setBtnMoreVisible();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_credits_more);
    }


    public void initData(final int type) {
        HashMap<String, String> params = new HashMap<>();
        params.put("act", "points");
        if (type == 1) { //精选礼品
            params.put("op", "exchange_pgoods");
            setCommonHeader("精选礼品");
        } else if (type == 2) { //代金券
            params.put("op", "exchange_voucher");
            setCommonHeader("代金券");

        } else if (type == 3) {//红包
            params.put("op", "exchange_redpacket");
            setCommonHeader("平台红包");

        } else {  //无
            return;
        }

        if (!application.getLoginKey().equals("") && type != 1) {

            params.put("key", application.getLoginKey());
        }
        RemoteDataHandler.asyncPostDataString(Constants.URL_CREDITS_CENTER, params, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {

                if (data.getCode() == 200) {
                    String res = data.getJson();
                    creditsRecycler.setLayoutManager(new LinearLayoutManager(CreditsMoreActivity.this, LinearLayoutManager.VERTICAL, false));
                    if (type == 1) { //精选礼品
                        PointsGoodsBean bean = new Gson().fromJson(res, PointsGoodsBean.class);
                        creditsRecycler.setAdapter(new PointsAdapter(CreditsMoreActivity.this,R.layout.adapter_multi_points_sprod,bean.getPointprod_list()));

                    } else if (type == 2) { //代金券
                        VoucherBean voucherBean = new Gson().fromJson(res, VoucherBean.class);
                        creditsRecycler.setAdapter(new VoucherAdapter(CreditsMoreActivity.this, R.layout.adapter_multi_voucher_type, voucherBean.getVoucherlist()));
                    } else if (type == 3) { //红包
                        RedPacketBean packetBean = new Gson().fromJson(res, RedPacketBean.class);

                        creditsRecycler.setAdapter(new RedPacketHolder(CreditsMoreActivity.this, R.layout.adapter_multi_voucher_type, packetBean.getRptlist()));
                    } else {
                        return;
                    }
                }
            }
        });
    }

    //积分商品适配器
    public class PointsAdapter extends CommonAdapter<PointsGoodsBean.PointprodListBean> {

        public PointsAdapter(Context context, int layoutId, List<PointsGoodsBean.PointprodListBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, final PointsGoodsBean.PointprodListBean bean, final int position) {

            ImageView ivPict = holder.getView(R.id.iv_pict);
            Glide.with(context)
                    .load(bean.getPgoods_image())
                    .into(ivPict);

            holder.setText(R.id.tv_voucher_goods_name,bean.getPgoods_name());
            holder.setText(R.id.tv_voucher_origin_price,bean.getPgoods_price());
            TextView tvOriginPrice = holder.getView(R.id.tv_voucher_origin_price);
            tvOriginPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
            holder.setText(R.id.tv_voucher_price,"需" + bean.getPgoods_points() + "积分");

            String limitGrade = bean.getPgoods_limitmgrade();
            TextView tvRequireLevel = holder.getView(R.id.tv_require_level);
            if (Integer.parseInt(limitGrade) > 0) {
                holder.setText(R.id.tv_require_level,"Lv." + limitGrade);

                tvRequireLevel.setVisibility(View.VISIBLE);
            } else {
               tvRequireLevel.setVisibility(View.GONE);
            }
            holder.setOnClickListener(R.id.layout_item,new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CreditsDetailActivity.class);
                    intent.putExtra("pgoods_id", bean.getPgoods_id());
                    context.startActivity(intent);
                }
            });
        }
    }

    //代金券适配器
    public class VoucherAdapter extends CommonAdapter<VoucherBean.VoucherlistBean> {
        public VoucherAdapter(Context context, int layoutId, List<VoucherBean.VoucherlistBean> datas) {
            super(context, layoutId, datas);
        }
        @Override
        protected void convert(ViewHolder holder, VoucherBean.VoucherlistBean voucherlistBean, int position) {

            ImageView ivPict = holder.getView(R.id.iv_pict);
            Glide.with(context)
                    .load(voucherlistBean.getVoucher_t_customimg())
                    .into(ivPict);
            holder.setText(R.id.tv_describe_title, voucherlistBean.getVoucher_t_storename());
            holder.setText(R.id.tv_type, voucherlistBean.getVoucher_t_sc_name());
            holder.setText(R.id.tv_voucher_goods_name, rmb + voucherlistBean.getVoucher_t_price());
            holder.setText(R.id.tv_voucher_temp, "购物满" + voucherlistBean.getVoucher_t_limit() + "元可用");
            holder.setText(R.id.tv_voucher_price, "需" + voucherlistBean.getVoucher_t_price() + "积分");
            holder.setText(R.id.tv_voucher_limit_time, "有效期至" + voucherlistBean.getVoucher_t_end_date());
            holder.setText(R.id.tv_exchange_num, voucherlistBean.getVoucher_t_giveout() + "人已兑换");
            holder.setOnClickListener(R.id.btn_exchange_now, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //兑换优惠卷

                }
            });
        }
    }

    //红包适配器
    public class RedPacketHolder extends CommonAdapter<RedPacketBean.RptlistBean>{

        public RedPacketHolder(Context context, int layoutId, List<RedPacketBean.RptlistBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, RedPacketBean.RptlistBean rptlistBean, int position) {
            ImageView ivPict = holder.getView(R.id.iv_pict);

            Glide.with(context)
                    .load(rptlistBean.getRpacket_t_customimg_url())
                    .into(ivPict);
            holder.setVisible(R.id.tv_type,false);
            holder.setText(R.id.tv_describe_title,rptlistBean.getRpacket_t_title());
            holder.setText(R.id.tv_voucher_goods_name,rmb + rptlistBean.getRpacket_t_price());
            holder.setText(R.id.tv_voucher_temp,"购物满" + rptlistBean.getRpacket_t_limit() + "元可用");
            holder.setText(R.id.tv_voucher_price, "需" + rptlistBean.getRpacket_t_price() + "积分");
            holder.setText(R.id.tv_voucher_limit_time, "有效期至" + rptlistBean.getRpacket_t_end_date());
            holder.setText(R.id.tv_exchange_num, rptlistBean.getRpacket_t_giveout() + "人已兑换");
            holder.setOnClickListener(R.id.btn_exchange_now,new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }





}
