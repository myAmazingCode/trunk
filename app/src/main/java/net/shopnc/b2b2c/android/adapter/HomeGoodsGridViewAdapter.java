package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.ItemGoods;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.ui.good.GoodDetailsActivity;

/**
 * 首页商品adapter
 *
 * @author huting
 * @date 2016/5/3
 */
public class HomeGoodsGridViewAdapter extends CommonAdapter<ItemGoods> {
    private String moneyRmb;

    public HomeGoodsGridViewAdapter(Context context) {
        super(context, R.layout.tab_home_item_goods_gridview_item);
        this.moneyRmb=context.getResources().getString(R.string.money_rmb);
    }

    @Override
    public void convert(ViewHolder holder, final ItemGoods itemGoods) {
        holder.setText(R.id.TextViewTitle, itemGoods.getGoodsName());
        holder.setImage(R.id.ImageViewImagePic01, itemGoods.getImageUrl());
        holder.setText(R.id.TextViewPrice, moneyRmb + ShopHelper.getPriceString(itemGoods.getAppPriceMin()));

        holder.setOnClickListener(R.id.goods, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodDetailsActivity.class);
                intent.putExtra("commonId", itemGoods.getCommonId());
                mContext.startActivity(intent);
            }
        });
    }
}
