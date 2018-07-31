package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.GoodsVo;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.ui.good.GoodDetailsActivity;

import java.text.DecimalFormat;

/**
 * 作者：Jie on 2016/6/20 15:33
 */
public class SearchGoodsGridAdapter extends RRecyclerAdapter<GoodsVo> {

    private String moneyRmb = "¥";

    public SearchGoodsGridAdapter(Context context) {
        super(context, R.layout.recyclerview_searchgood_grid_item);
    }

    @Override
    public void convert(final RecyclerHolder holder,final GoodsVo goodsVo) {
        DecimalFormat df = new DecimalFormat("#.00");//格式化

        holder.setText(R.id.tvGoodName, goodsVo.getGoodsName())
                .setText(R.id.tvGoodJingle, Common.isEmpty(goodsVo.getJingle()) ? "" : goodsVo.getJingle())
                .setText(R.id.tvGoodPrice, moneyRmb + df.format(goodsVo.getAppPriceMin()))
                .setText(R.id.tvGoodSalenum, "" + goodsVo.getGoodsSaleNum())
                .setImage(R.id.ivGoodPic, goodsVo.getImageSrc())
//                .setText(R.id.tvGoodLimit, goodsVo.getBatchNum0() + goodsVo.getUnitName() + "起购")
                .setText(R.id.btnStoreName, goodsVo.getStoreName());

        holder.setOnClickListener(R.id.tvGoodName, R.id.tvGoodJingle, R.id.tvGoodPrice,
                R.id.ivGoodPic, R.id.tvGoodSalenum, R.id.btnStoreName, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, GoodDetailsActivity.class);
                        intent.putExtra(GoodDetailsActivity.COMMONID, goodsVo.getCommonId());
                        context.startActivity(intent);
                    }
                });

        if(goodsVo.getIsOwnShop() == 1){
            holder.setVisible(R.id.tvOwnShop,true);
//            holder.setVisible(R.id.llOwnShop,false);
        }else {
            holder.setVisible(R.id.tvOwnShop,false);
//            holder.setVisible(R.id.llOwnShop,true);
        }

//        holder.setOnClickListener(R.id.btnMoreInfo, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.setVisible(R.id.search_goods_layout1, false);
//                holder.setVisible(R.id.search_goods_layout2, true);
//
//                OkHttpUtil.getAsyn(ConstantUrl.URL_GOOD_DETAILS + goodsVo.getCommonId(), new BeanCallback<String>() {
//                    @Override
//                    public void response(String data) {
//                        EvaluateStoreVo evaluateStoreVo = JsonUtil.toBean(data, "evaluateStoreVo", new TypeToken<EvaluateStoreVo>() {
//                        }.getType());
//                        if (evaluateStoreVo != null) {
//                            holder.setText(R.id.gotoStore, goodsVo.getStoreName())
//                                    .setText(R.id.search_evaluate1, evaluateStoreVo.getStoreDesEval())
//                                    .setText(R.id.search_evaluate2, evaluateStoreVo.getStoreServiceEval())
//                                    .setText(R.id.search_evaluate3, evaluateStoreVo.getStoreDeliveryEval())
//                                    .setText(R.id.search_evaluate11, evaluateStoreVo.getDesEvalTitle())
//                                    .setText(R.id.search_evaluate22, evaluateStoreVo.getServiceEvalTitle())
//                                    .setText(R.id.search_evaluate33, evaluateStoreVo.getDeliveryEvalTitle());
//                        }
//                    }
//                });
//            }
//        });
//
//        holder.setOnClickListener(R.id.search_goods_layout2, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.setVisible(R.id.search_goods_layout1, true);
//                holder.setVisible(R.id.search_goods_layout2, false);
//            }
//        });
//
//        holder.setOnClickListener(R.id.gotoStore, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context, GoodDetailsActivity.class);
//                i.putExtra("commonId", goodsVo.getCommonId());
//                context.startActivity(i);
//            }
//        });
    }
}
