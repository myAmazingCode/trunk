package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.GoodsVo;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.ui.good.GoodDetailsActivity;

/**
 * 店铺商品列表adapter
 *
 * @author huting
 * @date 2016/4/18
 */
public class StoreGoodsListAdapter extends CommonAdapter<GoodsVo> {

    public StoreGoodsListAdapter(Context context){
        super(context,R.layout.listivew_store_goods_item);
    }

    @Override
    public void convert(final ViewHolder holder, final GoodsVo good) {
        holder.setVisible(R.id.llStoreInfo,false)
                .setImage(R.id.imageGoodsPic,good.getImageSrc())
                .setText(R.id.textGoodsName,good.getGoodsName())
                .setText(R.id.textGoodsJingle,good.getJingle())
                .setText(R.id.textGoodsPrice,"￥" + good.getAppPriceMin())
                .setText(R.id.tvGoodsSalenum, "销量：" + good.getGoodsSaleNum());
        LogHelper.e("item里面","￥" + good.getAppPriceMin());

        if (good.getIsOwnShop() == 0){//不是自营店铺
            holder.setVisible(R.id.tvOwnShop,true);
            holder.setVisible(R.id.btnStoreName,false);
        }else{
            holder.setVisible(R.id.tvOwnShop,false)
                    .setVisible(R.id.btnStoreName, true)
                    .setText(R.id.btnStoreName, good.getStoreName())
                    .setTag(R.id.btnStoreName, holder.getPosition())
                    .setOnClickListener(R.id.btnStoreName, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            holder.setVisible(R.id.llStoreInfo, true);
                        }
                    });

            //跳转到店铺
            holder.setOnClickListener(R.id.tvStoreName, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    TToast.showShort(mContext, "调转到店铺");
                }
            });

            //关闭电铺信息
            holder.setOnClickListener(R.id.llStoreEval, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.setVisible(R.id.llStoreInfo, false);
                }
            });
        }

        //商品详情显示
        holder.setOnClickListener(R.id.goodsItem, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,GoodDetailsActivity.class);
                intent.putExtra(GoodDetailsActivity.COMMONID, good.getCommonId());
                mContext.startActivity(intent);
            }
        });
    }


    /**
     * 显示店铺信息
     * @param credit
     * @param type
     * @param tvPoint
     * @param tvText
     */
    /*private void setStoreCredit(String credit, String type, TextView tvPoint, TextView tvText) {
        tvPoint.setText(credit);
        if(type.equals("low")) {
            tvPoint.setTextColor(context.getResources().getColor(R.color.nc_green));
            tvText.setText("低");
            tvText.setBackgroundColor(context.getResources().getColor(R.color.nc_green));
        }
        if(type.equals("equal")) {
            tvPoint.setTextColor(context.getResources().getColor(R.color.nc_red));
            tvText.setText("平");
            tvText.setBackgroundColor(context.getResources().getColor(R.color.nc_red));
        }
        if(type.equals("high")) {
            tvPoint.setTextColor(context.getResources().getColor(R.color.nc_red));
            tvText.setText("高");
            tvText.setBackgroundColor(context.getResources().getColor(R.color.nc_red));
        }
    }*/
}
