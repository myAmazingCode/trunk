package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.GoodsVo;
import net.shopnc.b2b2c.android.bean.StoreInfo;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.ScreenUtil;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/9/7 14:33.
 */
public class SearchStoresAdapter extends RRecyclerAdapter<StoreInfo> {
    private String saleString;
    private String moneyRmb;

    public SearchStoresAdapter(Context context) {
        super(context, R.layout.recyclerview_search_store_item);
        saleString = context.getResources().getString(R.string.search_store_sale_all);
        moneyRmb = context.getResources().getString(R.string.money_rmb);
    }

    @Override
    public void convert(RecyclerHolder holder, final StoreInfo storeInfo) {
        holder.setImage(R.id.ivStoreImg, storeInfo.getStoreAvatarUrl())
                .setVisible(R.id.tvOwnShopFlag, storeInfo.getIsOwnShop() == 1)
                .setText(R.id.tvStoreName, storeInfo.getStoreName())
                .setText(R.id.tvSale, String.format(saleString, storeInfo.getStoreSales(), storeInfo.getGoodsCommonCount(), storeInfo.getStoreCollect()))
                .setText(R.id.tvGoodsName, "主营产品：" + storeInfo.getStoreZy());

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopHelper.gotoStoreActivity(context, storeInfo.getStoreId());
            }
        });

        LinearLayout llGood = holder.getView(R.id.llGood);
        if (storeInfo.getGoodsCommonList() == null || storeInfo.getGoodsCommonList().size() == 0) {
            llGood.setVisibility(View.GONE);
        } else {
            llGood.setVisibility(View.VISIBLE);
            llGood.removeAllViews();
            for (final GoodsVo goodsVo : storeInfo.getGoodsCommonList()) {
                AddViewHolder goodHolder = new AddViewHolder(context, R.layout.recyclerview_search_store_good_item);
                goodHolder.getCustomView().setLayoutParams(new RelativeLayout.LayoutParams(ScreenUtil.getScreenSize(context).x / 3, ViewGroup.LayoutParams.MATCH_PARENT));
                goodHolder.setImage(R.id.ivGoodImg, goodsVo.getImageSrc())
                        .setText(R.id.tvGoodPrice, moneyRmb + ShopHelper.getPriceString(goodsVo.getAppPriceMin()));
                goodHolder.getCustomView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShopHelper.gotoGoodDetailsActivity(context, goodsVo.getCommonId());
                    }
                });
                llGood.addView(goodHolder.getCustomView());
            }
        }
    }
}
