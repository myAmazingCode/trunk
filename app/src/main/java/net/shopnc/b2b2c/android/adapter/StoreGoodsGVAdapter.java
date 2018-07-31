package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.GoodsVo;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.ui.good.GoodDetailsActivity;

/**
 * 店铺首页商品adapter
 *
 * @author huting
 * @date 2016/4/14
 */
public class StoreGoodsGVAdapter extends CommonAdapter<GoodsVo> {



    public StoreGoodsGVAdapter(Context context) {
        super(context, R.layout.gv_store_goods_index);
    }

    @Override
    public void convert(ViewHolder holder, final GoodsVo goodsVo) {
        holder.setText(R.id.TextViewTitle, goodsVo.getGoodsName());
        holder.setText(R.id.TextViewPrice,"￥" + ShopHelper.getPriceString(goodsVo.getAppPriceMin()));
        holder.setImage(R.id.ImageViewImagePic01, goodsVo.getImageSrc());

        holder.setOnClickListener(R.id.ImageViewImagePic01, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,GoodDetailsActivity.class);
                intent.putExtra(GoodDetailsActivity.COMMONID, goodsVo.getCommonId());
                mContext.startActivity(intent);
            }
        });
    }
}
