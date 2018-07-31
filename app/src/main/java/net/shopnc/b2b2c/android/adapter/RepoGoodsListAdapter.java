package net.shopnc.b2b2c.android.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.MyRepoList;
import net.shopnc.b2b2c.android.bean.RepoGoodsList;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.custom.dialog.ShareDialog;
import net.shopnc.b2b2c.android.ui.promotion.PromotionDialog;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.LoadImage;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xws on 2017/2/15.
 */

public class RepoGoodsListAdapter extends RRecyclerAdapter<RepoGoodsList.DatasBean.DistributorGoodsListBean> {

    private String favId;

    public RepoGoodsListAdapter(Context context) {
        super(context, R.layout.repo_goods_list_item);
    }

    @Override
    public void convert(RecyclerHolder holder, final RepoGoodsList.DatasBean.DistributorGoodsListBean distributorGoodsListBean) {

        double appPriceMin = distributorGoodsListBean.getAppPriceMin();

        holder.setText(R.id.tvShopName, distributorGoodsListBean.getStoreName())
                .setText(R.id.tvGoodsName, distributorGoodsListBean.getGoodsName())
                .setText(R.id.tvPrice, "￥" + appPriceMin)
                .setText(R.id.tvPromotionNum, "推广数量：" + distributorGoodsListBean.getDistributionCount());

        ImageView view = holder.getView(R.id.ivGoodPic);
        LoadImage.loadRemoteImg(context, view, distributorGoodsListBean.getImageSrc());

        double commissionRate = distributorGoodsListBean.getCommissionRate();
        TextView tvRate = holder.getView(R.id.tvPayRate);
        tvRate.setText(Html.fromHtml("佣金比例：<font color=\"#F23030\" <b>" + String.format("%.2f", commissionRate) + "%</b></font>"));


        holder.setOnClickListener(R.id.tvMove, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //移动
                showAddPopWindow(v, distributorGoodsListBean);

            }
        }).setOnClickListener(R.id.tvDelete, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除
                PromotionDialog dialog = new PromotionDialog(context);
                dialog.setOnConfirmListener(new PromotionDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        delete(distributorGoodsListBean);
                    }
                });
                dialog.show();
                dialog.setNegativeMsg("取消");
                dialog.setUserMessage("确认删除", "确定");
            }
        }).setOnClickListener(R.id.tvShare, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //分享
                share(distributorGoodsListBean);
            }
        });

    }

    private void showAddPopWindow(View v, final RepoGoodsList.DatasBean.DistributorGoodsListBean goods) {

        View rootView = LayoutInflater.from(context).inflate(R.layout.promotion_selected_goods_popwindow, null);

        final PopupWindow selectedGoodsWindow = new PopupWindow(rootView, ViewGroup.LayoutParams.MATCH_PARENT, 1200, true);
        selectedGoodsWindow.setTouchable(true);
        selectedGoodsWindow.setOutsideTouchable(true);
        selectedGoodsWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        selectedGoodsWindow.setAnimationStyle(R.style.AnimBottom);


        selectedGoodsWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams attributes = ((Activity) context).getWindow().getAttributes();
                attributes.alpha = 1.0f;
                ((Activity) context).getWindow().setAttributes(attributes);
                ((Activity) context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
        });


        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (selectedGoodsWindow.isShowing()) {
                    selectedGoodsWindow.dismiss();
                }
                return true;
            }
        });

        final TextView tvEmpty = rootView.findViewById(R.id.tvEmpty);
        final TextView tvTitle = rootView.findViewById(R.id.tvTitle);
        tvTitle.setText("推广选品库分组");
        rootView.findViewById(R.id.ivClear).setVisibility(View.GONE);
        final RecyclerView rvSelected = rootView.findViewById(R.id.rvSelected);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
//        rvSelected.addItemDecoration(new DividerGridItemDecoration(this));
        rvSelected.setLayoutManager(layoutManager);

        //获取选品库列表
        String url = ConstantUrl.URL_API + "/member/distributor/favorites/list";
        OkHttpUtil.postAsyn(context, url, new BeanCallback<String>() {

            @Override
            public void response(String data) {

            }

            @Override
            public void onResponse(String response, int c) {
                Log.d(TAG, "onResponse: response = " + response);

                if (200 == JsonUtil.toInteger(response, "code")) {
                    MyRepoList myRepoList = new Gson().fromJson(response, MyRepoList.class);
                    List<MyRepoList.DatasBean.FavListBean> favList = myRepoList.getDatas().getFavList();
                    //列表数据
                    if (favList != null && favList.size() != 0) {

                        for (int i = 0; i < favList.size(); i++) {
                            MyRepoList.DatasBean.FavListBean favListBean = favList.get(i);
                            String favoritesId = favListBean.getDistributorFavoritesId();
                            if (favId.equals(favoritesId)) {
                                favList.remove(favListBean);
                            }
                        }
                        initAdapter(favList, rvSelected, selectedGoodsWindow, goods);

                    } else {
                        tvEmpty.setVisibility(View.VISIBLE);
                        tvEmpty.setText("您还没有相关选品库");
                        rvSelected.setVisibility(View.GONE);
                    }
                } else {
                    TToast.showShort(context, JsonUtil.toString(response, "datas", "error"));
                }
            }
        }, new OkHttpUtil.Param("token", MyShopApplication.getInstance().getToken()));

        selectedGoodsWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams attributes = ((Activity) context).getWindow().getAttributes();
        attributes.alpha = 0.7f;
        ((Activity) context).getWindow().setAttributes(attributes);
        ((Activity) context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    private void initAdapter(final List<MyRepoList.DatasBean.FavListBean> favList, RecyclerView rvSelected, final PopupWindow selectedGoodsWindow, final RepoGoodsList.DatasBean.DistributorGoodsListBean goods) {
        RRecyclerAdapter<MyRepoList.DatasBean.FavListBean> adapter = new RRecyclerAdapter<MyRepoList.DatasBean.FavListBean>(context, R.layout.repo_grid_item, favList) {
            @Override
            public void convert(RecyclerHolder holder, final MyRepoList.DatasBean.FavListBean favListBean) {
                holder.setText(R.id.tvRepoName, favListBean.getDistributorFavoritesName())
                        .setText(R.id.tvGoodsNum, "商品数量：" + favListBean.getGoodsCount());
                List<MyRepoList.DatasBean.FavListBean.DistributionGoodsVoListBean> goodsVoList = favListBean.getDistributionGoodsVoList();
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        move(favListBean.getDistributorFavoritesId(), goods);
                        selectedGoodsWindow.dismiss();
                    }
                });

                for (int i = 0; i < goodsVoList.size(); i++) {
                    MyRepoList.DatasBean.FavListBean.DistributionGoodsVoListBean bean = goodsVoList.get(i);
                    if (i == 0) {
                        ImageView view1 = holder.getView(R.id.ivGoodPic1);
                        LoadImage.loadRemoteImg(context, view1, bean.getImageSrc());
                    }

                    if (i == 1) {
                        ImageView view1 = holder.getView(R.id.ivGoodPic2);
                        LoadImage.loadRemoteImg(context, view1, bean.getImageSrc());
                    }

                    if (i == 2) {
                        ImageView view1 = holder.getView(R.id.ivGoodPic3);
                        LoadImage.loadRemoteImg(context, view1, bean.getImageSrc());
                    }

                    if (i == 3) {
                        ImageView view1 = holder.getView(R.id.ivGoodPic4);
                        LoadImage.loadRemoteImg(context, view1, bean.getImageSrc());
                    }
                }
            }
        };


        rvSelected.setAdapter(adapter);
    }

    private void move(String favId, final RepoGoodsList.DatasBean.DistributorGoodsListBean goods) {
        String url = ConstantUrl.URL_API + "/member/distributor/goods/movefavorites";

        /*
        token String 当前登录令牌
distributorGoodsId int 推广商品id
distributorFavoritesId int 目标选品库分组id
         */

        Map<String, String> map = new HashMap<>();
        map.put("token", MyShopApplication.getInstance().getToken());
        map.put("distributorFavoritesId", favId);
        map.put("distributorGoodsId", "" + goods.getDistributorGoodsId());

        OkHttpUtil.postAsyn(context, url, new BeanCallback<String>() {

            @Override
            public void response(String data) {

            }

            @Override
            public void onResponse(String response, int i) {
                Log.d(TAG, "onResponse: response = " + response);
                if (200 == JsonUtil.toInteger(response, "code")) {
                    TToast.showShort(context, "操作成功");
                    datas.remove(goods);
                    notifyDataSetChanged();
                } else {
                    TToast.showShort(context, JsonUtil.toString(response, "datas", "error"));
                }
            }
        }, map);

    }

    public static final String TAG = "RepoGoodsList";

    private void delete(final RepoGoodsList.DatasBean.DistributorGoodsListBean distributorGoodsListBean) {
        String url = ConstantUrl.URL_API + "/member/distributor/goods/del";
        Map<String, String> map = new HashMap<>();
        map.put("token", MyShopApplication.getInstance().getToken());
        map.put("distributorGoodsId", String.valueOf(distributorGoodsListBean.getDistributorGoodsId()));
        OkHttpUtil.postAsyn(context,url, new BeanCallback<String>() {

            @Override
            public void onResponse(String response,int i) {
                Log.d(TAG, "onResponse: response = " + response);
                if (JsonUtil.toInteger(response, "code") == 200) {
                    TToast.showShort(context, "操作成功");
                    datas.remove(distributorGoodsListBean);
                    notifyDataSetChanged();
                } else {
                    TToast.showShort(context, JsonUtil.toString(response, "datas", "error"));
                }
            }

            @Override
            public void response(String data) {

            }
        }, map);
    }

    private void share(RepoGoodsList.DatasBean.DistributorGoodsListBean goodsListBean) {
        String imageSrc = goodsListBean.getImageSrc();
        UMImage image = new UMImage(context, imageSrc);
        int commonId = goodsListBean.getCommonId();
        String targetUrl = ConstantUrl.WAP_GOODS_URL + commonId;
        ShareDialog shareDialog = new ShareDialog(context, context.getResources().getString(R.string.app_name),
                goodsListBean.getGoodsName() + "     " + targetUrl + "     " +
                        "(" + context.getString(R.string.app_name) + ")", targetUrl, image, umShareListener);
        shareDialog.show();
        String url = ConstantUrl.WAP_GOODS_URL + commonId + "&distributionGoodsId=" + goodsListBean.getDistributorGoodsId();
        shareDialog.showShareCode(imageSrc, "" + goodsListBean.getAppPriceMin(), url);
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

    public void setRepo(String favId) {
        this.favId = favId;
    }
}
