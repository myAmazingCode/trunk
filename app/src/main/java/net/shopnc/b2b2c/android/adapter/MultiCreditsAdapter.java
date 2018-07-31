package net.shopnc.b2b2c.android.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.CreditsCenterBean;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ui.jifen.CreditsCenterActivity;
import net.shopnc.b2b2c.android.ui.jifen.CreditsDetailActivity;
import net.shopnc.b2b2c.android.ui.jifen.CreditsMoreActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yuanshuo on 2017/10/12.
 * 积分中心多类型item布局适配器
 */

public class MultiCreditsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    CreditsCenterBean bean;
    private final LayoutInflater inflater;
    private String loginKey;


    /**
     * 一共四种布局类型
     * ITEM_TYPE_TITLE：类型头布局：代金券、精选礼品、红包
     * ITEM_TYPE_POINTSPROD：精选礼品列表布局
     * ITEM_TYPE_VOUCHER：代金券列表布局
     * ITEM_TYPE_REDPACKET：红包列表布局
     */
    public enum ITEM_TYPE {
        ITEM_TYPE_TITLE,
        ITEM_TYPE_POINTSPROD,
        ITEM_TYPE_VOUCHER,
        ITEM_TYPE_REDPACKET
    }

    private int type_count_1 = 0;
    private int type_count_2 = 0;
    private int type_count_3 = 0;
    private Activity context;
    String rmb;

    public MultiCreditsAdapter(Activity context, CreditsCenterBean bean, String loginKey) {

        this.context = context;
        this.bean = bean;
        this.loginKey = loginKey;
        inflater = LayoutInflater.from(context);
        type_count_1 = bean.getVoucher().size();
        type_count_2 = bean.getPointsprod().size();
        type_count_3 = bean.getRedpacket().size();
        rmb = context.getString(R.string.money_rmb);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        根据不同类型布局返回不同holder

        if (viewType == ITEM_TYPE.ITEM_TYPE_TITLE.ordinal()) {
            return new TitleHolder(inflater.inflate(R.layout.adapter_multi_title_type, parent, false));
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_VOUCHER.ordinal()) {
            View view = inflater.inflate(R.layout.adapter_multi_voucher_type, parent, false);
            return new VoucherHolder(view);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_POINTSPROD.ordinal()) {
            View view = inflater.inflate(R.layout.adapter_multi_points_sprod, parent, false);
            return new PointsHolder(view);

        } else if (viewType == ITEM_TYPE.ITEM_TYPE_REDPACKET.ordinal()) {
            View view = inflater.inflate(R.layout.adapter_multi_voucher_type, parent, false);
            return new RedPacketHolder(view);
        } else {
            return null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

//        根据不同holder绑定不同控件
        if (holder instanceof TitleHolder) {
            TitleHolder titleHolder = (TitleHolder) holder;
            if (position == 0) {
                titleHolder.ivTitle.setImageResource(R.drawable.daijinquan);
                titleHolder.ivTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, CreditsMoreActivity.class);
                        intent.putExtra("type", 2);
                        context.startActivity(intent);

                    }
                });
            } else if (position == type_count_1 + 1) {
                titleHolder.ivTitle.setImageResource(R.drawable.lipin);
                titleHolder.ivTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, CreditsMoreActivity.class);
                        intent.putExtra("type", 1);
                        context.startActivity(intent);

                    }
                });
            } else {
                titleHolder.ivTitle.setImageResource(R.drawable.hongbao);
                titleHolder.ivTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, CreditsMoreActivity.class);
                        intent.putExtra("type", 3);
                        context.startActivity(intent);

                    }
                });
            }

        } else if (holder instanceof VoucherHolder) {
            VoucherHolder voucherHolder = (VoucherHolder) holder;
            Glide.with(context)
                    .load(bean.getVoucher().get(position - 1).getVoucher_t_customimg())
                    .into(voucherHolder.ivPict);
            voucherHolder.tvDescribeTitle.setText(bean.getVoucher().get(position - 1).getVoucher_t_storename());
            voucherHolder.tvType.setText(bean.getVoucher().get(position - 1).getVoucher_t_sc_name());
            voucherHolder.tvVoucherValue.setText(rmb + bean.getVoucher().get(position - 1).getVoucher_t_price());
            voucherHolder.tvVoucherCondition.setText("购物满" + bean.getVoucher().get(position - 1).getVoucher_t_limit() + "元可用");
            voucherHolder.tvVoucherPrice.setText("需" + bean.getVoucher().get(position - 1).getVoucher_t_price() + "积分");
            voucherHolder.tvVoucherLimitTime.setText("有效期至" + bean.getVoucher().get(position - 1).getVoucher_t_end_date());
            voucherHolder.tvExchangeNum.setText(bean.getVoucher().get(position - 1).getVoucher_t_giveout() + "人已兑换");
            voucherHolder.btnExchangeNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //兑换优惠卷

                    if (ShopHelper.isLogin(context, loginKey)) {

                        View view = View.inflate(context, R.layout.dialog_exchange_voucher, null);

                        TextView msg = view.findViewById(R.id.tv_msg);
                        msg.setText("您正在使用" + bean.getVoucher().get(position - 1).getVoucher_t_points() + "积分兑换1张" + bean.getVoucher()
                                .get(position - 1).getVoucher_t_storename() + bean.getVoucher().get(position - 1).getVoucher_t_price() + "元店铺代金券" +
                                "(满" + bean.getVoucher().get(position - 1).getVoucher_t_limit() + "减" + bean.getVoucher().get(position - 1).getVoucher_t_price() + ")"
                                + "\n店铺代金券有效期至" + bean.getVoucher().get(position - 1).getVoucher_t_end_date() + "\n每个ID领取" + bean.getVoucher().get(position - 1)
                                .getVoucher_t_eachlimit() + "张");
                        Button btnCancel = view.findViewById(R.id.btn_cancel);
                        Button btnPositive = view.findViewById(R.id.btn_positive);

                        final AlertDialog dialog = new AlertDialog.Builder(context)
                                .setView(view)
                                .create();
                        btnPositive.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                //调用接口兑换代金券

                                exchangeVoucher(bean.getVoucher().get(position - 1).getVoucher_t_id());
                            }
                        });
                        btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();

                    } else {
                        TToast.showShort(context, "请登录");
                    }

                }
            });
        } else if (holder instanceof PointsHolder) {
            PointsHolder pointsHolder = (PointsHolder) holder;
            Glide.with(context)
                    .load(bean.getPointsprod().get(position - 2 - bean.getVoucher().size()).getPgoods_image())
                    .into(pointsHolder.ivPict);

            pointsHolder.tvVoucherGoodsName.setText(bean.getPointsprod().get(position - 2 - bean.getVoucher().size()).getPgoods_name());
            pointsHolder.tvVoucherOriginPrice.setText(bean.getPointsprod().get(position - 2 - bean.getVoucher().size()).getPgoods_price());
            pointsHolder.tvVoucherOriginPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            pointsHolder.tvVoucherPrice.setText("需" + bean.getPointsprod().get(position - 2 - bean.getVoucher().size()).getPgoods_points() + "积分");

            String limitGrade = bean.getPointsprod().get(position - 2 - bean.getVoucher().size()).getPgoods_limitmgrade();
            if (Integer.parseInt(limitGrade) > 0) {
                pointsHolder.tvRequireLevel.setText("Lv." + limitGrade);
                pointsHolder.tvRequireLevel.setVisibility(View.VISIBLE);
            } else {
                pointsHolder.tvRequireLevel.setVisibility(View.GONE);
            }
            pointsHolder.layoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CreditsDetailActivity.class);
                    intent.putExtra("pgoods_id", bean.getPointsprod().get(position - 2 - bean.getVoucher().size()).getPgoods_id());
                    context.startActivity(intent);
                }
            });

        } else if (holder instanceof RedPacketHolder) {
            RedPacketHolder redPacketHolder = (RedPacketHolder) holder;
            Glide.with(context)
                    .load(bean.getRedpacket().get(position - 3 - type_count_1 - type_count_2).getRpacket_t_customimg_url())
                    .into(redPacketHolder.ivPict);
            redPacketHolder.tvType.setVisibility(View.GONE);
            redPacketHolder.tvDescribeTitle.setText(bean.getRedpacket().get(position - 3 - type_count_1 - type_count_2).getRpacket_t_title());
            redPacketHolder.tvVoucherValue.setText(rmb + bean.getRedpacket().get(position - 3 - type_count_1 - type_count_2).getRpacket_t_price());
            redPacketHolder.tvVoucherCondition.setText("购物满" + bean.getRedpacket().get(position - 3 - type_count_1 - type_count_2).getRpacket_t_limit() + "元可用");
            redPacketHolder.tvVoucherPrice.setText("需" + bean.getRedpacket().get(position - 3 - type_count_1 - type_count_2).getRpacket_t_points() + "积分");
            redPacketHolder.tvVoucherLimitTime.setText("有效期至" + bean.getRedpacket().get(position - 3 - type_count_1 - type_count_2).getRpacket_t_end_date());
            redPacketHolder.tvExchangeNum.setText(bean.getRedpacket().get(position - 3 - type_count_1 - type_count_2).getRpacket_t_giveout() + "人已兑换");
            redPacketHolder.btnExchangeNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ShopHelper.isLogin(context, loginKey)) {
                        exchangeRedPacket(bean.getRedpacket().get(position - 3 - type_count_1 - type_count_2).getRpacket_t_id());
                    } else {
                        TToast.showShort(context, "请登录");
                    }
                }
            });
        }
    }

    //兑换代金券
    private void exchangeVoucher(String voucher_t_id) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", voucher_t_id);
        params.put("key", loginKey);
        RemoteDataHandler.asyncPostDataString(Constants.URL_CREDITS_EXCHANGE_VOUCHER, params, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == 200) {
                    String res = data.getJson();
                    TToast.showShort(context, res);
                } else if (data.getCode() == 400) {
                    String res = data.getJson();
                    try {
                        JSONObject json = new JSONObject(res);
                        String error = json.getString("error");
                        TToast.showShort(context, error);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    //兑换红包
    private void exchangeRedPacket(String packetId) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", packetId);
        params.put("key", loginKey);
        RemoteDataHandler.asyncPostDataString(Constants.URL_CREDITS_EXCHANGE_REDPACKET, params, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == 200) {
                    String res = data.getJson();
                    TToast.showShort(context, res);
                } else if (data.getCode() == 400) {
                    String res = data.getJson();
                    try {
                        JSONObject json = new JSONObject(res);
                        String error = json.getString("error");
                        TToast.showShort(context, error);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        int a = 0, b = 0, c = 0;
        if (type_count_1 > 0) {
            a = 1;
        }
        if (type_count_2 > 0) {
            b = 1;
        }
        if (type_count_3 > 0) {
            c = 1;
        }
        return type_count_1 + type_count_2 + type_count_3 + a + b + c;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == type_count_1 + 1 || position == type_count_1 + type_count_2 + 2 || position == type_count_1 + type_count_2 + type_count_3 + 3) {
            //标题
            return ITEM_TYPE.ITEM_TYPE_TITLE.ordinal();
        } else if (position > 0 && position < type_count_1 + 1) {
            //代金券
            return ITEM_TYPE.ITEM_TYPE_VOUCHER.ordinal();
        } else if (position > type_count_1 + 1 && position < type_count_1 + type_count_2 + 2) {
            //精选礼品
            return ITEM_TYPE.ITEM_TYPE_POINTSPROD.ordinal();
        } else {
            //热门红包
            return ITEM_TYPE.ITEM_TYPE_REDPACKET.ordinal();
        }
    }

    //标题Holder
    public class TitleHolder extends RecyclerView.ViewHolder {

        ImageView ivTitle;

        public TitleHolder(View itemView) {
            super(itemView);
            ivTitle = itemView.findViewById(R.id.iv_title);
        }
    }

    //代金券holder
    public class VoucherHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_describe_title)
        TextView tvDescribeTitle;
        @Bind(R.id.tv_type)
        TextView tvType;
        @Bind(R.id.iv_pict)
        ImageView ivPict;
        @Bind(R.id.tv_voucher_goods_name)
        TextView tvVoucherValue;
        @Bind(R.id.tv_voucher_temp)
        TextView tvVoucherCondition;
        @Bind(R.id.tv_voucher_limit_time)
        TextView tvVoucherLimitTime;
        @Bind(R.id.tv_voucher_price)
        TextView tvVoucherPrice;
        @Bind(R.id.tv_exchange_num)
        TextView tvExchangeNum;
        @Bind(R.id.btn_exchange_now)
        Button btnExchangeNow;

        VoucherHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    //    红包holder
    public class RedPacketHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_describe_title)
        TextView tvDescribeTitle;
        @Bind(R.id.tv_type)
        TextView tvType;
        @Bind(R.id.iv_pict)
        ImageView ivPict;
        @Bind(R.id.tv_voucher_goods_name)
        TextView tvVoucherValue;
        @Bind(R.id.tv_voucher_temp)
        TextView tvVoucherCondition;
        @Bind(R.id.tv_voucher_limit_time)
        TextView tvVoucherLimitTime;
        @Bind(R.id.tv_voucher_price)
        TextView tvVoucherPrice;
        @Bind(R.id.tv_exchange_num)
        TextView tvExchangeNum;
        @Bind(R.id.btn_exchange_now)
        Button btnExchangeNow;

        RedPacketHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    //    精选礼品holder
    public class PointsHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_pict)
        ImageView ivPict;
        @Bind(R.id.tv_voucher_goods_name)
        TextView tvVoucherGoodsName;
        @Bind(R.id.tv_voucher_temp)
        TextView tvVoucherTemp;
        @Bind(R.id.tv_voucher_origin_price)
        TextView tvVoucherOriginPrice;
        @Bind(R.id.tv_voucher_price)
        TextView tvVoucherPrice;
        @Bind(R.id.tv_require_level)
        TextView tvRequireLevel;
        RelativeLayout layoutItem;

        PointsHolder(View view) {
            super(view);
            layoutItem = view.findViewById(R.id.layout_item);
            ButterKnife.bind(this, view);
        }
    }
}
