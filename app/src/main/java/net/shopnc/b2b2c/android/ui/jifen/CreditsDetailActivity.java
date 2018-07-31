package net.shopnc.b2b2c.android.ui.jifen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.CreditsGoodDetailBean;
import net.shopnc.b2b2c.android.bean.CreditsLogBean;
import net.shopnc.b2b2c.android.bean.CreditsOrderListBean;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;

/**
 * 精选礼品的详情页
 */
public class CreditsDetailActivity extends BaseActivity {

    @Bind(R.id.iv_detail_pict)
    ImageView ivDetailPict;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_credits_num)
    TextView tvCreditsNum;
    @Bind(R.id.tv_left_num)
    TextView tvLeftNum;
    @Bind(R.id.tv_guide_price)
    TextView tvGuidePrice;
    @Bind(R.id.tv_goods_no)
    TextView tvGoodsNo;
    @Bind(R.id.tv_add_time)
    TextView tvAddTime;
    @Bind(R.id.tv_browse_num)
    TextView tvBrowseNum;
    @Bind(R.id.tv_exchange_cart)
    TextView tvExchangeCart;
    @Bind(R.id.btn_exchange)
    Button btnExchange;
    @Bind(R.id.recycler_record_list)
    RecyclerView recyclerRecordList;
    private String pGoodsID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pGoodsID = getIntent().getStringExtra("pgoods_id");
        setCommonHeader("兑换商品详情");
        setBtnMoreVisible();
        initData();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_credits_detail);
    }

    //加载服务器数据，获得数据后页面布局
    public void initData() {
        RemoteDataHandler.asyncDataStringGet(Constants.URL_CREDITS_DETAIL + "&pgoods_id=" + pGoodsID, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == 200) {
                    String res = data.getJson();
                    CreditsGoodDetailBean bean = new Gson().fromJson(res, CreditsGoodDetailBean.class);
                    Glide.with(CreditsDetailActivity.this)
                            .load(bean.getPgoods_info().getPgoods_image_max())
                            .into(ivDetailPict);
                    tvName.setText(bean.getPgoods_info().getPgoods_name());
                    tvCreditsNum.setText(bean.getPgoods_info().getPgoods_points());
                    tvLeftNum.setText("剩余：" + bean.getPgoods_info().getPgoods_storage() + "件");
                    tvGuidePrice.setText("市场参考价 " + bean.getPgoods_info().getPgoods_price() + "元");
                    tvGoodsNo.setText(bean.getPgoods_info().getPgoods_serial());
                    tvAddTime.setText(bean.getPgoods_info().getPgoods_add_time());
                    tvBrowseNum.setText(bean.getPgoods_info().getPgoods_view() + "人次");
                    //点击进入购物车
                    tvExchangeCart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, CreditsCartActivity.class);
                            startActivity(intent);
                        }
                    });
                    //加入购物车事件
                    btnExchange.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    initRecordData();
                }
            }
        });


    }

    //获取兑换记录
    public void initRecordData() {
        RemoteDataHandler.asyncDataStringGet(Constants.URL_CREDITS_DETAIL_RECORD + "&pgoods_id=" + pGoodsID, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == 200) {
                    String res = data.getJson();
                    CreditsOrderListBean bean = new Gson().fromJson(res, CreditsOrderListBean.class);
                    if (bean.getOrder_list().size() > 0) {
                        CreditsRecordAdapter adapter = new CreditsRecordAdapter(context, R.layout.adapter_credits_record_list, bean.getOrder_list());
                        recyclerRecordList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false){
                            @Override
                            public boolean canScrollVertically() {
                                return false;
                            }
                        });
                        recyclerRecordList.setAdapter(adapter);
                    }
                }
            }
        });
    }



    //兑换记录适配器
    public class CreditsRecordAdapter extends CommonAdapter<CreditsOrderListBean.OrderListBean> {

        public CreditsRecordAdapter(Context context, int layoutId, List<CreditsOrderListBean.OrderListBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, CreditsOrderListBean.OrderListBean orderListBean, int position) {
            ImageView ivHeader = holder.getView(R.id.iv_header);
            Glide.with(context)
                    .load(orderListBean.getMember_avatar())
                    .into(ivHeader);
            holder.setText(R.id.tv_name, orderListBean.getMember_name());
            holder.setText(R.id.tv_record, orderListBean.getPoint_addtime() + " 兑换了" + orderListBean.getPoint_goodsnum() + "件");
        }
    }
}
