//package net.shopnc.b2b2c.android.adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.View;
//
//import net.shopnc.b2b2c.R;
//import net.shopnc.b2b2c.android.bean.GoodsVo;
//import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
//import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
//import net.shopnc.b2b2c.android.ui.good.GoodDetailsActivity;
//
///**
// * @Description XXX
// * @Author qyf
// *
// * Created 2016/4/25 16:44.
// */
//public class GoodsDetailsHotGridAdapter extends CommonAdapter<GoodsVo> {
//    private String moneyRmb;
//
//    public GoodsDetailsHotGridAdapter(Context context) {
//        super(context, R.layout.recyclerview_goodsdetails_hotgoods_item);
//        moneyRmb = context.getResources().getString(R.string.money_rmb);
//    }
//
//    @Override
//    public void convert(ViewHolder holder,final GoodsVo goodsVo) {
//        holder.setImage(R.id.ivPic, goodsVo.getImageSrc())
//                .setText(R.id.tvGoodsCommendName, goodsVo.getGoodsName())
//                .setText(R.id.tvGoodsCommendPrice, moneyRmb + goodsVo.getAppPriceMin());
//        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, GoodDetailsActivity.class);
//                intent.putExtra(GoodDetailsActivity.COMMONID, goodsVo.getCommonId());
//                mContext.startActivity(intent);
//            }
//        });
//    }
//
//}
package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.GoodsVo;
import net.shopnc.b2b2c.android.common.ScreenUtil;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.ui.good.GoodDetailsActivity;


/**
 * @Description XXX
 * @Author qyf
 * <p>
 * Created 2016/4/25 16:44.
 */
public class GoodsDetailsHotGridAdapter extends RRecyclerAdapter<GoodsVo> {
    private String moneyRmb;
    private LinearLayout.LayoutParams mLayoutParams;
    public static final String TAG = "HotGridAdapter";

    public GoodsDetailsHotGridAdapter(Context context) {
        super(context, R.layout.recyclerview_goodsdetails_hotgoods_item);
        moneyRmb = context.getResources().getString(R.string.money_rmb);

        Point screenSize = ScreenUtil.getScreenSize(context);
        //10dp共6个
        int totalPadding = ScreenUtil.dip2px(context, 10) * 6;
        int width = (screenSize.x - totalPadding) / 3;

        //LayoutParams(int ,int )单位是pixels
        mLayoutParams = new LinearLayout.LayoutParams(width, width);
    }

    @Override
    public void convert(RecyclerHolder holder, final GoodsVo goodsVo) {
        ImageView ivPic = holder.getView(R.id.ivPic);

        ivPic.setLayoutParams(mLayoutParams);
        holder.setImage(R.id.ivPic, goodsVo.getImageSrc())
                .setText(R.id.tvGoodsCommendName, goodsVo.getGoodsName())
                .setText(R.id.tvGoodsCommendPrice, moneyRmb + ShopHelper.getPriceString(goodsVo.getAppPriceMin()));
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodDetailsActivity.class);
                intent.putExtra(GoodDetailsActivity.COMMONID, goodsVo.getCommonId());
                context.startActivity(intent);
            }
        });
    }
}

