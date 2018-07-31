package net.shopnc.b2b2c.android.adapter;

import android.content.Context;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.RedPackageMember;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * FileName: net.shopnc.b2b2c.android.adapter.RedPackageAdapter.java
 * author: Jie
 * date: 2016-05-31 16:29
 */
public class RedPackageAdapter extends CommonAdapter<RedPackageMember> {

    public RedPackageAdapter(Context context) {
        super(context, R.layout.mine_redpackage_coupon_item);
    }

    @Override
    public void convert(ViewHolder holder, RedPackageMember packageMember) {
        holder.setText(R.id.coupon_name, packageMember.getRedPackageTitle())
                .setText(R.id.coupon_time, "有效期至" + packageMember.getEndTimeText())
                .setText(R.id.coupon_store_type, packageMember.getRedPackageUsableClientTypeText());

        holder.setText(R.id.coupon_store_money, ShopHelper.getPriceString(packageMember.getRedPackagePrice()))
                .setText(R.id.coupon_store_condition, "满" + ShopHelper.getPriceString(packageMember.getLimitAmount()) + "元使用");

        //未过期未作废
        if (packageMember.getRedPackageState()==0&&packageMember.getRedPackageExpiredState()==0){
            holder.setBackgroundRes(R.id.coupon_background, R.color.nc_red);
        }else {
            holder.setBackgroundRes(R.id.coupon_background, R.color.text_gray);
        }
    }

}
