package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.MyRepoList;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.custom.ComboGoodsView;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.ui.promotion.CommissionActivity;
import net.shopnc.b2b2c.android.ui.promotion.NewRepoActivity;
import net.shopnc.b2b2c.android.ui.promotion.PromotionDialog;
import net.shopnc.b2b2c.android.ui.promotion.PromotionOrderListActivity;
import net.shopnc.b2b2c.android.ui.promotion.RepoGoodsListlActivity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.List;

/**
 * Created by xws on 2017/2/10.
 */

public class MyRepoListAdapter extends RRecyclerAdapter<MyRepoList.DatasBean.FavListBean> {

    private static final int FIRST_VIEW = 0x1;
    private static final int NORMAL_VIEW = 0x2;
    private int position;
    private String[] infos;

    public void setInfos(String[] infos) {
        this.infos = infos;
    }

    public MyRepoListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        this.position = position;

        if (position == 0) {
            return FIRST_VIEW;
        }

        return NORMAL_VIEW;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == FIRST_VIEW) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.myrepo_list_first, parent, false);
            return new RecyclerHolder(context, itemView);
        }

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.myrepo_list_normal, parent, false);
        return new RecyclerHolder(context, itemView);

    }

    public static final String TAG = "MyRepoAdapter";


    @Override
    public void convert(RecyclerHolder holder, final MyRepoList.DatasBean.FavListBean favListBean) {

//        int position = datas.indexOf(favListBean);
        Log.d(TAG, "convert: postition = " + position);

        holder.setText(R.id.tvName, favListBean.getDistributorFavoritesName())
                .setText(R.id.tvNum, favListBean.getGoodsCount());
        LinearLayout llGoods = holder.getView(R.id.llGoods);

        //设置头部基本信息
        if (position == 0) {
            TextView tvOrderNum = holder.getView(R.id.tvOrderNum);
            TextView tvGoodsType = holder.getView(R.id.tvGoodsType);
            TextView tvBalance = holder.getView(R.id.tvBalance);

            if (infos != null) {
                tvGoodsType.setText(infos[0]);
                tvOrderNum.setText(infos[1]);
                tvBalance.setText(infos[2]);
            }

            //佣金页面
            holder.setOnClickListener(R.id.llCommission, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CommissionActivity.class);
                    context.startActivity(intent);
                }
                //推广订单
            }).setOnClickListener(R.id.llOrders, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, PromotionOrderListActivity.class));
                }
                //默认选品库
            }).setOnClickListener(R.id.rlHeadItem, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RepoGoodsListlActivity.class);
                    intent.putExtra("repoName", favListBean.getDistributorFavoritesName());
                    intent.putExtra("favId", favListBean.getDistributorFavoritesId());
                    context.startActivity(intent);
                }
            });

        }

        List<MyRepoList.DatasBean.FavListBean.DistributionGoodsVoListBean> goodsVoList = favListBean.getDistributionGoodsVoList();
        llGoods.removeAllViews();
        for (int i = 0; i < goodsVoList.size(); i++) {
            MyRepoList.DatasBean.FavListBean.DistributionGoodsVoListBean goodsVoListBean = goodsVoList.get(i);
            String imageSrc = goodsVoListBean.getImageSrc();
            ComboGoodsView comboGoodsView = new ComboGoodsView(context);
            comboGoodsView.setLayoutParams();
            comboGoodsView.setImage(imageSrc, false);
            llGoods.addView(comboGoodsView);
        }


        if (position != 0) {
            //点击选品库
            holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RepoGoodsListlActivity.class);
                    intent.putExtra("repoName", favListBean.getDistributorFavoritesName());
                    intent.putExtra("favId", favListBean.getDistributorFavoritesId());
                    context.startActivity(intent);
                }
            });
            holder.setOnClickListener(R.id.tvDelete, new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PromotionDialog dialog = new PromotionDialog(context);
                    dialog.setOnConfirmListener(new PromotionDialog.OnConfirmListener() {
                        @Override
                        public void onConfirm() {
                            delete(favListBean);
                        }
                    });
                    dialog.show();
                    dialog.setUserMessage("删除后该分组中商品也将被移除，确认删除吗？", "确定");
                    dialog.setNegativeMsg("取消");
                }
            });

            holder.setOnClickListener(R.id.tvEdit, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewRepoActivity.class);
                    String distributorFavoritesId = favListBean.getDistributorFavoritesId();
                    intent.putExtra("edit", true);
                    intent.putExtra("distributorFavoritesName", favListBean.getDistributorFavoritesName());
                    intent.putExtra("distributorFavoritesId", distributorFavoritesId);
                    context.startActivity(intent);
                }
            });

        }
    }

    /**
     * 删除选品库
     *
     * @param favListBean
     */
    private void delete(final MyRepoList.DatasBean.FavListBean favListBean) {
        String url = ConstantUrl.URL_API + "/member/distributor/favorites/del";
        OkHttpUtil.postAsyn(context,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                TToast.showShort(context, "删除成功");
                datas.remove(favListBean);
                notifyDataSetChanged();
            }
        }, new OkHttpUtil.Param("token", MyShopApplication.getInstance().getToken()
        ), new OkHttpUtil.Param("distributorFavoritesId", favListBean.getDistributorFavoritesId()));
    }
}
