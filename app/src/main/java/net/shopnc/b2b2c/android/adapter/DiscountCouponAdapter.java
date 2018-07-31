package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.View;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.MemberVoucher;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;

/**
 * 优惠券
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.adapter.DiscountCouponAdapter.java
 * @author: Jie
 * @date: 2016-05-30 10:26
 */
public class DiscountCouponAdapter extends CommonAdapter<MemberVoucher> {

    private String moneyRmb;

    public DiscountCouponAdapter(Context context) {
        super(context, R.layout.mine_discount_coupon_item);
        this.moneyRmb = context.getResources().getString(R.string.money_rmb);
    }

    @Override
    public void convert(ViewHolder holder, final MemberVoucher memberVoucher) {
        holder.setText(R.id.coupon_name, memberVoucher.getStoreName())
                .setText(R.id.coupon_store_type, memberVoucher.getVoucherUsableClientTypeText())
                .setText(R.id.coupon_time, "有效期至：" + memberVoucher.getEndTimeText())
                .setImage(R.id.coupon_image, memberVoucher.getStore().getStoreAvatarUrl());

        holder.setText(R.id.coupon_store_money, moneyRmb + ShopHelper.getPriceString(memberVoucher.getPrice()))
                .setText(R.id.coupon_store_condition, "满" + ShopHelper.getPriceString(memberVoucher.getLimitAmount()) + "元使用");

        if (memberVoucher.getVoucherState()==0&&memberVoucher.getVoucherExpiredState()==0){
            holder.setBackgroundRes(R.id.coupon_background, R.color.nc_red);
        }else {
            holder.setBackgroundRes(R.id.coupon_background, R.color.text_gray);
        }

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopHelper.gotoStoreActivity(mContext, memberVoucher.getStoreId());
            }
        });

    }


}
