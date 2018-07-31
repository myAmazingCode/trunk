package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.widget.ImageView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.PromotionOrder;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.util.LoadImage;

import java.util.List;

/**
 * ght (c) 2007-2017 ShopNC Inc. All rights reserved.
 *
 * @author lzz
 *         Created 2017/5/8 15:31
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * @description 推广订单列表适配器
 */
public class PromotionOrderListAdapter extends CommonAdapter<PromotionOrder.DistributorOrdersListBean> {

    private final String moneyRmb;

    public PromotionOrderListAdapter(Context context, List<PromotionOrder.DistributorOrdersListBean> datas, int layoutId) {
        super(context, datas, layoutId);
        moneyRmb = mContext.getResources().getString(R.string.money_rmb);
    }

    @Override
    public void convert(ViewHolder holder, PromotionOrder.DistributorOrdersListBean dist) {

        holder.setText(R.id.tvOrderNo, "推广单号：" + dist.getDistributionOrdersId())
                .setText(R.id.tvOrderState, dist.getDistributionOrdersTypeText())
                .setText(R.id.tvStateDesc, dist.getOrdersStateText())
                .setText(R.id.tvGoodName, dist.getGoodsName())
                .setText(R.id.tvGoodPrice, moneyRmb + String.format("%.2f", dist.getGoodsPrice()))
                .setText(R.id.tvGoodPrice2, moneyRmb + String.format("%.2f", dist.getGoodsPayAmount()))
                .setText(R.id.tvGoodsNum, "x" + dist.getBuyNum() + dist.getUnitName())
                .setText(R.id.tvGoodsSpec, dist.getGoodsFullSpecs())
                .setText(R.id.tvStoreName, "店铺：" + dist.getStoreName())
                .setText(R.id.tvCommissionPercentage, String.format("%.2f", dist.getCommissionRate()) + "%")
                .setText(R.id.tvCommission, moneyRmb + String.format("%.2f", dist.getCommission()));

        ImageView ivGoodPic = holder.getView(R.id.ivGoodPic);
        LoadImage.loadRemoteImg(mContext, ivGoodPic, dist.getImageSrc());


    }
}
