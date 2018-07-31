package net.shopnc.b2b2c.android.adapter;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/8/3 16:19.
 */

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TabStopSpan;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.EventObj;
import net.shopnc.b2b2c.android.bean.OrdersGoodsVo;
import net.shopnc.b2b2c.android.bean.RefundItemVo;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.custom.dialog.NCDialog;
import net.shopnc.b2b2c.android.custom.dialog.NCDialogConfirm;
import net.shopnc.b2b2c.android.ui.order.OrderRefundAndReturnDetailsActivity;
import net.shopnc.b2b2c.android.ui.order.OrderReturnGoodSendActivity;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.math.BigDecimal;
import java.util.HashMap;

public class OrderRefundAndReturnListAdapter extends RRecyclerAdapter<RefundItemVo> {
    private String moneyRmb;
    private String flag;

    public OrderRefundAndReturnListAdapter(Context context) {
        super(context, R.layout.recyclerview_refund_list_item);
        this.moneyRmb = context.getResources().getString(R.string.money_rmb);
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public void convert(RecyclerHolder holder, final RefundItemVo refundItemVo) {
        holder.setText(R.id.tvStoreName, "  " + refundItemVo.getStoreName())
                .setText(R.id.tvRefundState, (Common.isEmpty(refundItemVo.getAdminTime()) ? "商家" : "") + refundItemVo.getSellerStateText())
                .setText(R.id.tvTime, refundItemVo.getAddTime())
                .setText(R.id.tvPrice, flag.equals("refund") ? getPriceString(refundItemVo.getRefundAmount()) : getPriceString(refundItemVo.getRefundAmount(), refundItemVo.getGoodsNum(), refundItemVo.getOrdersGoodsVoList().get(0).getUnitName()), TextView.BufferType.SPANNABLE)
                .setText(R.id.tvRefundDetails, flag.equals("refund") ? "退款详情" : "退货详情");

        LinearLayout llGoods = holder.getView(R.id.llGoods);
        bindGoods(llGoods, refundItemVo);

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderRefundAndReturnDetailsActivity.class);
                intent.putExtra("refundId", refundItemVo.getRefundId());
                intent.putExtra("flag", flag);
                context.startActivity(intent);
            }
        });

        holder.setVisible(R.id.tvGoodSend, refundItemVo.getShowMemberReturnShip() == 1);
        holder.setOnClickListener(R.id.tvGoodSend, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderReturnGoodSendActivity.class);
                intent.putExtra("refundId", refundItemVo.getRefundId());
                context.startActivity(intent);
            }
        });


        holder.setVisible(R.id.tvRefundDelay, refundItemVo.getShowMemberReturnDelay() == 1);
        holder.setOnClickListener(R.id.tvRefundDelay, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NCDialog ncDialog = new NCDialog(context);
                ncDialog.setText1("发货" + refundItemVo.getMaxDayReturnDelay() + "天后，当商家选择未收到则要进行延迟时间操作；如果超过" + refundItemVo.getMaxDayReturnConfirm() + "天不处理按弃货处理，直接由管理员确认退款。");
                ncDialog.showPopupWindow();
                ncDialog.setOnDialogConfirm(new NCDialogConfirm() {
                    @Override
                    public void onDialogConfirm() {
                        HashMap<String, String> params = new HashMap<>();
                        params.put("token", MyShopApplication.getInstance().getToken());
                        params.put("refundId", refundItemVo.getRefundId() + "");

                        OkHttpUtil.postAsyn(context,ConstantUrl.URL_ORDER_RETURN_GOOD_SEND_DELAY, new BeanCallback<String>() {
                            @Override
                            public void response(String data) {
                                EventBus.getDefault().post(new EventObj(EventObj.ORDERREFUNDSENDDELAY));
                            }
                        }, params);
                    }
                });
            }
        });
    }

    private void bindGoods(LinearLayout llGoods, RefundItemVo refundItemVo) {
        llGoods.removeAllViews();
        for (OrdersGoodsVo ordersGoodsVo : refundItemVo.getOrdersGoodsVoList()) {
            AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.recyclerview_refund_good_item);
            addViewHolder.setImage(R.id.ivGoodPic, ordersGoodsVo.getImageSrc())
                    .setText(R.id.tvGoodName, ordersGoodsVo.getGoodsName())
                    .setText(R.id.tvGoodsSPec, ordersGoodsVo.getGoodsFullSpecs());
            llGoods.addView(addViewHolder.getCustomView());
        }
    }

    private SpannableString getPriceString(BigDecimal price) {
        String s = "退款金额：" + moneyRmb + ShopHelper.getPriceString(price);
        int position = s.indexOf("：");
        SpannableString sp = new SpannableString(s);
        sp.setSpan(new TextAppearanceSpan(context, R.style.refund_money_name), 0, position + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new TextAppearanceSpan(context, R.style.refund_money_num), position + 1, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sp;
    }

    private SpannableString getPriceString(BigDecimal price, int buyNum, String unitName) {
        String s = "退款金额：" + moneyRmb + ShopHelper.getPriceString(price);
        int positionS = s.indexOf("：");
        String ss = "\n" + "退货金额：" + buyNum + unitName;
        String showString = s + ss;
        int positionSS = showString.lastIndexOf("：");
        SpannableString sp = new SpannableString(showString);
        sp.setSpan(new TextAppearanceSpan(context, R.style.refund_money_name), 0, positionS + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new TextAppearanceSpan(context, R.style.refund_money_num), positionS + 1, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new TextAppearanceSpan(context, R.style.refund_money_name), s.length(), positionSS + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new TextAppearanceSpan(context, R.style.refund_money_num), positionSS + 1, showString.length() - unitName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new TextAppearanceSpan(context, R.style.refund_money_name), showString.length() - unitName.length(), showString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new TabStopSpan.Standard(s.length()), 0, showString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return sp;
    }
}

