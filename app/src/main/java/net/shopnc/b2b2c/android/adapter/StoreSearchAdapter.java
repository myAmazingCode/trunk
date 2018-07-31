package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.GoodsVo;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.ui.good.GoodDetailsActivity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 店铺搜索adapter
 *
 * @author huting
 * @date 2016/5/11
 */
public class StoreSearchAdapter extends RRecyclerAdapter<GoodsVo> {
    private String moneyRmb;
    private MyShopApplication application;
    private int selectedCommonId = 0;

    public StoreSearchAdapter(Context context, int layoutId) {
        super(context, layoutId);
        this.moneyRmb = context.getResources().getString(R.string.money_rmb);
        this.application = MyShopApplication.getInstance();
    }

    @Override
    public void convert(final RecyclerHolder holder, final GoodsVo goodsVo) {

        holder.setText(R.id.tvGoodName, goodsVo.getGoodsName())
                .setText(R.id.tvGoodJingle, Common.isEmpty(goodsVo.getJingle()) ? "" : goodsVo.getJingle())
                .setText(R.id.tvGoodPrice, moneyRmb + ShopHelper.getPriceString(Double.valueOf(goodsVo.getAppPriceMin())))
                .setText(R.id.tvGoodSalenum, "销量：" + goodsVo.getGoodsSaleNum())
                .setImage(R.id.ivGoodPic, goodsVo.getImageSrc())
                .setVisible(R.id.tvBitchNum, goodsVo.getGoodsModal() == 2)
                .setText(R.id.tvBitchNum, goodsVo.getBatchNum0() + goodsVo.getUnitName() + "起购");

        //限时折扣  显示控制
        if (goodsVo.getPromotionType() == 1 && goodsVo.getAppUsable() == 1) {
            holder.setVisible(R.id.tvDownFlag, true);
        } else {
            holder.setVisible(R.id.tvDownFlag, false);
        }
        //全款预售  显示控制
        if (goodsVo.getPromotionType() == 2 && goodsVo.getAppUsable() == 1) {
            holder.setVisible(R.id.tvPreShowFlag, true);
        } else {
            holder.setVisible(R.id.tvPreShowFlag, false);
        }
        //赠品  显示控制
        if (goodsVo.getIsGift() == 1) {
            holder.setVisible(R.id.tvGiftFlag, true);
        } else {
            holder.setVisible(R.id.tvGiftFlag, false);
        }

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodDetailsActivity.class);
                intent.putExtra(GoodDetailsActivity.COMMONID, goodsVo.getCommonId());
                context.startActivity(intent);
            }
        });

        holder.setOnClickListener(R.id.ivMore, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.setVisible(R.id.rlAddFav, true);
                selectedCommonId = goodsVo.getCommonId();
                requestIsFav(holder, goodsVo.getCommonId());
                holder.setOnClickListener(R.id.ibImgFav, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addOrCancleFav(holder, goodsVo.getCommonId());
                    }
                });
                holder.setOnClickListener(R.id.rlAddFav, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.setVisible(R.id.rlAddFav, false);
                        selectedCommonId = 0;
                    }
                });
            }
        });

        //防止item复用
        if (selectedCommonId == 0 || selectedCommonId != goodsVo.getCommonId()) {
            holder.setVisible(R.id.rlAddFav, false);
        }


    }

    private void addOrCancleFav(final RecyclerHolder holder, int commonId) {
        if (!ShopHelper.isLogin(context)) {
            holder.setVisible(R.id.rlAddFav, false);
            selectedCommonId = 0;
        }
        Map<String, String> p = new HashMap<>();
        p.put("token", application.getToken());
        p.put("commonId", commonId + "");
        if (holder.isImageButtonSelected(R.id.ibImgFav)) {
            OkHttpUtil.postAsyn(context,ConstantUrl.URL_DELETE_FAV_GOODS, new BeanCallback<String>() {
                @Override
                public void response(String data) {
                    if (data.contains("success")) {
                        holder.getView(R.id.ibImgFav).setSelected(false);
                        holder.setText(R.id.tvFav, "加收藏");
                    }
                }
            }, p);
        } else {
            OkHttpUtil.postAsyn(context,ConstantUrl.URL_FAV_GOODS, new BeanCallback<String>() {
                @Override
                public void response(String data) {
                    if (data.contains("success")) {
                        holder.getView(R.id.ibImgFav).setSelected(true);
                        holder.setText(R.id.tvFav, "已收藏");
                    }
                }
            }, p);

        }
    }

    private void requestIsFav(final RecyclerHolder holder, int commonId) {
        if (Common.isEmpty(application.getToken())) {
            holder.getView(R.id.ibImgFav).setSelected(false);
            holder.setText(R.id.tvFav, "加收藏");
            return;
        }

        Map<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("commonId", commonId + "");
        OkHttpUtil.postAsyn(context,ConstantUrl.URL_WHETHER_FAV_GOODS, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                if (JsonUtil.toString(data, "isExist").equals("1")) {
                    holder.getView(R.id.ibImgFav).setSelected(true);
                    holder.setText(R.id.tvFav, "已收藏");
                } else {
                    holder.getView(R.id.ibImgFav).setSelected(false);
                    holder.setText(R.id.tvFav, "加收藏");
                }
            }
        }, params);
    }

}
