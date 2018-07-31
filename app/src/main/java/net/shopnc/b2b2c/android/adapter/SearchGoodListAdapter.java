package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.EvaluateStoreVo;
import net.shopnc.b2b2c.android.bean.GoodsVo;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.ui.good.GoodDetailsActivity;
import net.shopnc.b2b2c.android.ui.store.StoreInfoFragmentActivity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

/**
 * @Description 搜索商品适配器
 * @Author qyf
 *
 * Created 2016/4/15 11:34.
 */
public class SearchGoodListAdapter extends RRecyclerAdapter<GoodsVo> {
    private String moneyRmb;
    private int moneyRmbLength;
    private int selectedCommonId = 0;
    private int greenColorId = 0;
    private int redColorId = 0;

    public SearchGoodListAdapter(Context context, int layoutId) {
        super(context, layoutId);
        moneyRmb = context.getResources().getString(R.string.money_rmb);
        moneyRmbLength = moneyRmb.length();
        greenColorId = ContextCompat.getColor(context, R.color.gooddetails_store_low);
        redColorId = ContextCompat.getColor(context, R.color.nc_red);
    }

    @Override
    public void convert(final RecyclerHolder holder, final GoodsVo goodsVo) {
        holder.setText(R.id.tvGoodName, goodsVo.getGoodsName())
//                .setText(R.id.tvGoodJingle, Common.isEmpty(goodsVo.getJingle()) ? "" : goodsVo.getJingle())
                .setText(R.id.tvGoodPrice, getPriceSpannableString(moneyRmb + ShopHelper.getPriceString(goodsVo.getAppPriceMin())), TextView.BufferType.SPANNABLE)
                .setText(R.id.tvGoodSalenum, "销量：" + goodsVo.getGoodsSaleNum())
                .setImage(R.id.ivGoodPic, goodsVo.getImageSrc());
//                .setText(R.id.btnStoreName, goodsVo.getStoreName());

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodDetailsActivity.class);
                intent.putExtra(GoodDetailsActivity.COMMONID, goodsVo.getCommonId());
                context.startActivity(intent);
            }
        });

        if (goodsVo.getIsOwnShop() == 1) {
            holder.setVisible(R.id.tvOwnShop, true);
        } else {
            holder.setVisible(R.id.tvOwnShop, false);
        }

        //批发模式显示起购量
        if (goodsVo.getGoodsModal() == 2) {//批发模式
            holder.setVisible(R.id.tvBitchNum, true);
            holder.setText(R.id.tvBitchNum, goodsVo.getBatchNum0() + goodsVo.getUnitName() + "起购");
        } else {
            holder.setVisible(R.id.tvBitchNum, false);
        }

        //限时折扣1  全款预售2 定金预售3  显示控制
        if (goodsVo.getPromotionType() != 0 && goodsVo.getAppUsable() == 1) {
            holder.setVisible(R.id.tvPromotionFlag, true);
            holder.setText(R.id.tvPromotionFlag, goodsVo.getPromotionTypeText());
        } else {
            holder.setVisible(R.id.tvPromotionFlag, false);
        }
        //赠品  显示控制
        if (goodsVo.getIsGift() == 1) {
            holder.setVisible(R.id.tvGiftFlag, true);
        } else {
            holder.setVisible(R.id.tvGiftFlag, false);
        }


        holder.setVisible(R.id.llPopStore, false);
        //点击查看店铺评分
        holder.setOnClickListener(R.id.llOwnStore, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCommonId = goodsVo.getCommonId();
                holder.setVisible(R.id.llPopStore, true);
                OkHttpUtil.getAsyn(context,ConstantUrl.URL_GOOD_DETAILS + goodsVo.getCommonId(), new BeanCallback<String>() {
                    @Override
                    public void response(String data) {
                        EvaluateStoreVo evaluateStoreVo = JsonUtil.toBean(data, "evaluateStoreVo", new TypeToken<EvaluateStoreVo>() {
                        }.getType());
                        if (evaluateStoreVo != null) {
                            holder.setText(R.id.tvStoreName, goodsVo.getStoreName())
                                    .setText(R.id.storeDesEval, evaluateStoreVo.getStoreDesEval())
                                    .setTextColor(R.id.storeDesEval, evaluateStoreVo.getDesEvalArrow().equals("low") ? greenColorId : redColorId)
                                    .setText(R.id.storeServiceEval, evaluateStoreVo.getStoreServiceEval())
                                    .setTextColor(R.id.storeServiceEval, evaluateStoreVo.getServiceEvalArrow().equals("low") ? greenColorId : redColorId)
                                    .setText(R.id.storeDeliveryEval, evaluateStoreVo.getStoreDeliveryEval())
                                    .setTextColor(R.id.storeDeliveryEval, evaluateStoreVo.getDeliveryEvalArrow().equals("low") ? greenColorId : redColorId)
                                    .setText(R.id.storeDesEvalTitle, evaluateStoreVo.getDesEvalTitle())
                                    .setBackgroundColor(R.id.storeDesEvalTitle, evaluateStoreVo.getDesEvalArrow().equals("low") ? greenColorId : redColorId)
                                    .setText(R.id.storeServiceEvalTitle, evaluateStoreVo.getServiceEvalTitle())
                                    .setBackgroundColor(R.id.storeServiceEvalTitle, evaluateStoreVo.getServiceEvalArrow().equals("low") ? greenColorId : redColorId)
                                    .setText(R.id.storeDeliveryEvalTitle, evaluateStoreVo.getDeliveryEvalTitle())
                                    .setBackgroundColor(R.id.storeDeliveryEvalTitle, evaluateStoreVo.getDeliveryEvalArrow().equals("low") ? greenColorId : redColorId);
                            holder.setOnClickListener(R.id.llStoreOther, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    holder.setVisible(R.id.llPopStore, false);
                                    selectedCommonId = 0;
                                }
                            });
                        }
                    }
                });
            }
        });

        holder.setOnClickListener(R.id.tvStoreName, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, StoreInfoFragmentActivity.class);
                i.putExtra("storeId", goodsVo.getStoreId());
                context.startActivity(i);
            }
        });
    }

    //大小显示价格串
    private SpannableString getPriceSpannableString(String s) {
        SpannableString spannableString = new SpannableString(s);
        int position = s.indexOf(".");
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.search_good_small), 0, moneyRmbLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.search_good_big), moneyRmbLength, position, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.search_good_small), position, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

}
