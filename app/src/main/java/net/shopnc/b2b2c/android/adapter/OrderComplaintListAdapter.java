package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TabStopSpan;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.LinearLayout;

import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.EventObj;
import net.shopnc.b2b2c.android.bean.Complain;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.custom.dialog.NCDialog;
import net.shopnc.b2b2c.android.custom.dialog.NCDialogConfirm;
import net.shopnc.b2b2c.android.ui.order.OrderComplaintDetailActivity;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.math.BigDecimal;
import java.util.HashMap;

public class OrderComplaintListAdapter extends RRecyclerAdapter<Complain> {

    private String moneyRmb;
    private String flag;

    public OrderComplaintListAdapter(Context context) {
        super(context, R.layout.recyclerview_complain_list_item);
        this.moneyRmb = context.getResources().getString(R.string.money_rmb);
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public void convert(RecyclerHolder holder, final Complain complain) {
        holder.setText(R.id.tvStoreName, "  " + complain.getAccusedName())
                .setText(R.id.tvRefundState, complain.getComplainStateName())
                .setText(R.id.tvTime, complain.getAccuserTime())
                .setText(R.id.tvPrice, complain.getSubjectTitle())
                .setText(R.id.tvRefundDelay, "投诉详情");


        LinearLayout llGoods = holder.getView(R.id.llGoods);
        bindGoods(llGoods, complain);

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderComplaintDetailActivity.class);
                intent.putExtra("complainId", complain.getComplainId());
                context.startActivity(intent);
            }
        });

//        holder.setVisible(R.id.tvGoodSend, complain.getShowMemberClose() == 1 ? true : false);
//        holder.setText(R.id.tvGoodSend, "撤销投诉");
//        holder.setOnClickListener(R.id.tvGoodSend, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(context, OrderReturnGoodSendActivity.class);
////                intent.putExtra("refundId", refundItemVo.getRefundId());
////                context.startActivity(intent);
//            }
//        });

        holder.setVisible(R.id.tvRefundDetails, complain.getShowMemberClose() == 1);
        holder.setText(R.id.tvRefundDetails, "撤销投诉");
        holder.setOnClickListener(R.id.tvRefundDetails, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NCDialog ncDialog = new NCDialog(context);
                ncDialog.setText1("确定撤销吗？");
                ncDialog.showPopupWindow();
                ncDialog.setOnDialogConfirm(new NCDialogConfirm() {
                    @Override
                    public void onDialogConfirm() {
                        HashMap<String, String> params = new HashMap<>();
                        params.put("token", MyShopApplication.getInstance().getToken());
                        params.put("complainId", complain.getComplainId() + "");

                        OkHttpUtil.postAsyn(context,ConstantUrl.URL_MEMBER_COMPLAIN_CLOSE, new BeanCallback<String>() {
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

    //    private void bindGoods(LinearLayout llGoods, Complain complain) {
//        llGoods.removeAllViews();
//        for (OrdersGoodsVo ordersGoodsVo : refundItemVo.getOrdersGoodsVoList()) {
//            AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.recyclerview_refund_good_item);
//            addViewHolder.setImage(R.id.ivGoodPic, ordersGoodsVo.getImageSrc())
//                    .setText(R.id.tvGoodName, ordersGoodsVo.getGoodsName())
//                    .setText(R.id.tvGoodsSPec, ordersGoodsVo.getGoodsFullSpecs());
//            llGoods.addView(addViewHolder.getCustomView());
//        }
//    }
    private void bindGoods(LinearLayout llGoods, Complain complain) {
        llGoods.removeAllViews();
        AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.recyclerview_refund_good_item);
        addViewHolder.setImage(R.id.ivGoodPic, complain.getImageSrc())
                .setText(R.id.tvGoodName, complain.getGoodsName())
                .setText(R.id.tvGoodsSPec, complain.getGoodsFullSpecs());
        llGoods.addView(addViewHolder.getCustomView());
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
