package net.shopnc.b2b2c.android.ui.fenxiao;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.FlashDetailBean;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.JsonUtil;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;
import net.shopnc.b2b2c.android.utils.SpaceItemDecoration;
import net.shopnc.b2b2c.android.utils.SpaceItemFlashDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class FlashBuyDetailActivity extends BaseActivity {

    @Bind(R.id.flash_detail_recycler)
    XRecyclerView flashDetailRecycler;
    String flashId;
    String rmb;
    private CountDownTimer timer;
    private TextView tvLeftTime;
    private int curpage = 1;

    private List<FlashDetailBean.GoodsListBean> goods_list;
    private FlashDetailAdapter adapter;
    private View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goods_list = new ArrayList<>();
        flashId = getIntent().getStringExtra("flash_id");
        rmb = getResources().getString(R.string.money_rmb);
        if (flashId != null) {
            initData(true);
        }

        flashDetailRecycler.setLoadingMoreEnabled(true);
        flashDetailRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                curpage  = 1;
                flashDetailRecycler.setLoadingMoreEnabled(true);
                initData(true);
            }

            @Override
            public void onLoadMore() {
                initData(false);
            }
        });
        initRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setCommonHeader("闪购活动详情");
        setBtnMoreVisible();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_flash_buy_detail);
    }

    public void initRecyclerView(){
        header = LayoutInflater.from(FlashBuyDetailActivity.this).inflate(R.layout.header_flash_detail, null);
        adapter = new FlashDetailAdapter(FlashBuyDetailActivity.this, R.layout.adapter_flash_buy_detail_item, goods_list);
        GridLayoutManager manager = new GridLayoutManager(FlashBuyDetailActivity.this, 2);
        flashDetailRecycler.setLayoutManager(manager);
        flashDetailRecycler.setAdapter(adapter);
        SpaceItemFlashDecoration decoration = new SpaceItemFlashDecoration(2, 5);
        flashDetailRecycler.addItemDecoration(decoration);
        flashDetailRecycler.addHeaderView(header);
    }

    public void initData(final boolean isRefresh){
        RemoteDataHandler.asyncDataStringGet(Constants.URL_FLASH_DETAIL+"&flash_id="+flashId+"&curpage="+curpage, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.isHasMore()) {
                    curpage++;
                } else {
                    flashDetailRecycler.setLoadingMoreEnabled(false);
                }

                String info = data.getJson();
                FlashDetailBean bean = JsonUtil.getBean(info, FlashDetailBean.class);



                if (isRefresh) { //初次加载或者下拉刷新
                    goods_list.clear();
                    goods_list.addAll(bean.getGoods_list());
                    ImageView ivHead = header.findViewById(R.id.iv_flash_brand);
                    Glide.with(FlashBuyDetailActivity.this)
                            .load(bean.getFlash_info().getFlash_banner_url())
                            .into(ivHead);
                    tvLeftTime = header.findViewById(R.id.tv_left_time);
                    initTime(bean);

                    adapter.notifyDataSetChanged();
                    flashDetailRecycler.refreshComplete();
                } else {  //上拉加载

                    goods_list.addAll(bean.getGoods_list());
                    adapter.notifyDataSetChanged();
                    flashDetailRecycler.loadMoreComplete();
                }


            }
        });
    }

    private void initTime(FlashDetailBean bean) {
            startCountDownTime(Long.parseLong(bean.getFlash_info().getEnd_time())*1000 - System.currentTimeMillis());
    }

    private void startCountDownTime(long time) {
        /**
         * 最简单的倒计时类，实现了官方的CountDownTimer类（没有特殊要求的话可以使用）
         * 即使退出activity，倒计时还能进行，因为是创建了后台的线程。
         * 有onTick，onFinsh、cancel和start方法
         */
        //每隔countDownInterval秒会回调一次onTick()方法
        timer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //每隔countDownInterval秒会回调一次onTick()方法
                tvLeftTime.setText(getTime(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                tvLeftTime.setText("已结束");
            }
        };
        timer.start();// 开始计时
    }

    private String getTime(long millisUntilFinished) {
        long s = millisUntilFinished / 1000;
        int day = (int) s / 60 / 60 / 24;
        int honor = (int) (s - day * 24 * 60 * 60) / 60 / 60;
        int second = (int) (s - day * 24 * 60 * 60 - honor * 60 * 60) / 60;
        int min = (int) s - day * 60 * 60 * 24 - honor * 60 * 60 - second * 60;
        return ShopHelper.getTwoString(day) + "天" + ShopHelper.getTwoString(honor) + "时" + ShopHelper.getTwoString(second) + "分" + ShopHelper.getTwoString(min) + "秒";
    }

    public class FlashDetailAdapter extends CommonAdapter<FlashDetailBean.GoodsListBean>{

        public FlashDetailAdapter(Context context, int layoutId, List<FlashDetailBean.GoodsListBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, final FlashDetailBean.GoodsListBean detailBean, int position) {
            ImageView ivPict = holder.getView(R.id.iv_flash_detail_item);
            Glide.with(context)
                    .load(detailBean.getGoods_img_url())
                    .into(ivPict);
            holder.setText(R.id.tv_flash_item_price, rmb+ detailBean.getFlash_price());
            holder.setText(R.id.tv_flash_origin_price,  rmb+ detailBean.getGoods_price());
            TextView tvOriginPrice = holder.getView(R.id.tv_flash_origin_price);
            tvOriginPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
            holder.setText(R.id.tv_discount, detailBean.getFlash_discount());
            holder.setText(R.id.tv_flash_item_name, detailBean.getGoods_name());
            holder.setOnClickListener(R.id.layout_item, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(FlashBuyDetailActivity.this, GoodsDetailsActivity.class);
                    intent.putExtra("goods_id", detailBean.getGoods_id());
                    startActivity(intent);
                }
            });
        }
    }



}
