package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.PromotionSearch;
import net.shopnc.b2b2c.android.bean.SingleShare;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.custom.dialog.ShareDialog;
import net.shopnc.b2b2c.android.ui.good.GoodDetailsActivity;
import net.shopnc.b2b2c.android.ui.promotion.PromotionInfoHelper;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.LoadImage;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xws on 2017/2/6.
 */

public class PromotionListAdapter extends RRecyclerAdapter<PromotionSearch.DatasBean.GoodsListBean> {

    private List<PromotionSearch.DatasBean.GoodsListBean> mSelectedList;

    public List<PromotionSearch.DatasBean.GoodsListBean> getSelectedList() {
        return mSelectedList;
    }

    public PromotionListAdapter(Context context, List<PromotionSearch.DatasBean.GoodsListBean> datas) {
        super(context, R.layout.promotion_goods_list_item);
        this.datas = datas;
    }

    @Override
    public void convert(RecyclerHolder holder, final PromotionSearch.DatasBean.GoodsListBean goodsListBean) {

        final TextView tvName = holder.getView(R.id.tvGoodName);

        tvName.setText(goodsListBean.getGoodsName());
        holder.setText(R.id.tvGoodSalenum, "销量" + goodsListBean.getGoodsSaleNum())
                .setText(R.id.tvGoodPrice, String.format("￥%s", goodsListBean.getAppPriceMin()));
        holder.setVisible(R.id.tvGiftFlag, "1".equals(goodsListBean.getIsGift()));
        String promotionType = goodsListBean.getPromotionType();
        String appUsable = goodsListBean.getAppUsable();
        //限时折扣
        holder.setVisible(R.id.tvPromotionFlag, "1".equals(promotionType) && "1".equals(appUsable));
        //定金预售
        holder.setVisible(R.id.tvPayPresell, "3".equals(promotionType) && "1".equals(appUsable));
        //全款预售
        holder.setVisible(R.id.tvPresell, "2".equals(promotionType) && "1".equals(appUsable));

        //批发起购量
        boolean visible = "2".equals(goodsListBean.getGoodsModal());
        holder.setVisible(R.id.llBatch, visible);
        if (visible) {
            holder.setText(R.id.tvBatch, goodsListBean.getBatchNum0() + goodsListBean.getUnitName() + "起购");
        }

        TextView tvRate = holder.getView(R.id.tvRate);
        String commissionRate = goodsListBean.getCommissionRate();
        double rate = Double.parseDouble(commissionRate);
        tvRate.setText(Html.fromHtml("比率：<font color=\"#555555\" <b>" + ShopHelper.getPriceString(rate) + "%</b></font>"));

        TextView tvPay = holder.getView(R.id.tvPay);
        tvPay.setText(Html.fromHtml("佣金：<font color=\"#555555\" <b>￥" + ShopHelper.getPriceString(goodsListBean.getAppCommission()) + "</b></font>"));

        ImageView ivGoodPic = holder.getView(R.id.ivGoodPic);
        LoadImage.loadRemoteImg(context, ivGoodPic, goodsListBean.getImageSrc());

        //分享按钮
        View view = holder.getView(R.id.tvShare);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopWindow(tvName, goodsListBean);
            }
        });

        //商品详情页
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commonId = goodsListBean.getCommonId();
                Intent intent = new Intent(context, GoodDetailsActivity.class);
                intent.putExtra("commonId", Integer.parseInt(commonId));
                context.startActivity(intent);
            }
        });
    }

    private void showPopWindow(View v, final PromotionSearch.DatasBean.GoodsListBean goodsListBean) {

        //是否已选
        final boolean marked = goodsListBean.isMarked();

        View viewPopSort = LayoutInflater.from(context).inflate(R.layout.promotion_share_window, null);
        final PopupWindow window = new PopupWindow(viewPopSort, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        window.setTouchable(true);
        window.setOutsideTouchable(true);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        viewPopSort.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (window != null && window.isShowing()) {
                    window.dismiss();
                }
                return true;
            }
        });

        //选取
        final TextView tvChoose = viewPopSort.findViewById(R.id.tvChoose);
        tvChoose.setSelected(marked);

        tvChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!marked) {
                    tvChoose.setSelected(true);
                    goodsListBean.setMarked(true);
                    if (mOnItemSelectedListener != null) {
                        mOnItemSelectedListener.onItemSelected(1);
                    }

                    if (mSelectedList == null) {
                        mSelectedList = new ArrayList<>();
                    }

                    //添加到集合
                    mSelectedList.add(goodsListBean);
                }
                window.dismiss();
            }
        });

        //分享
        viewPopSort.findViewById(R.id.tvShare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                PromotionInfoHelper.getJoinInfo(context, new PromotionInfoHelper.OnEventListener() {
                    @Override
                    public void onEvent() {
                        //分享商品
                        addSingle(goodsListBean);
//                        share(goodsListBean);
                    }
                });
            }
        });

        window.showAsDropDown(v, 0, -60);
    }

    public static final String TAG = "ProAdapter";

    private void addSingle(final PromotionSearch.DatasBean.GoodsListBean goodsListBean) {
        String url = ConstantUrl.URL_API + "/member/distributor/goods/add";
        Map<String, String> params = new HashMap<>();
        params.put("token", MyShopApplication.getInstance().getToken());
        params.put("commonId", goodsListBean.getCommonId());

        OkHttpUtil.postAsyn(context,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {

            }


            @Override
            public void onResponse(String response,int i) {
                Log.d(TAG, "onResponse: response = " + response);
                if (JsonUtil.toInteger(response, "code") == 200) {

                    SingleShare singleShare = new Gson().fromJson(response, SingleShare.class);
                    SingleShare.DatasBean.DistributionGoodsBean distributionGoods = singleShare.getDatas().getDistributionGoods();
                    share(distributionGoods, goodsListBean.getAppPriceMin());
                } else {
                    TToast.showShort(context, JsonUtil.toString(response, "datas", "error"));
                }
            }
        }, params);
    }

    private void share(SingleShare.DatasBean.DistributionGoodsBean goodsListBean, String appPriceMin) {
        String imageSrc = goodsListBean.getGoodsImageSrc();
        UMImage image = new UMImage(context, imageSrc);
        int commonId = goodsListBean.getCommonId();
        String url = ConstantUrl.WAP_GOODS_URL + commonId;
        ShareDialog shareDialog = new ShareDialog(context, context.getResources().getString(R.string.app_name),
                goodsListBean.getGoodsName() + "     " + url + "     " +
                        "(" + context.getString(R.string.app_name) + ")", url, image, umShareListener);
        shareDialog.show();
        shareDialog.showShareCode(imageSrc, appPriceMin, goodsListBean.getWapShareUrl());
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                TToast.showShort(context, platform + " 收藏成功啦");
            } else {
                TToast.showShort(context, platform + " 分享成功啦");
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            TToast.showShort(context, platform + " 分享失败啦");
            if (t != null) {
                LogHelper.d("throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            TToast.showShort(context, platform + " 分享取消了");
        }
    };

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        mOnItemSelectedListener = onItemSelectedListener;
    }

    private OnItemSelectedListener mOnItemSelectedListener;

    public interface OnItemSelectedListener {
        void onItemSelected(int count);
    }
}
