package net.shopnc.b2b2c.android.ui.mine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.GoodsListBean;
import net.shopnc.b2b2c.android.bean.OrderRefundDetailsBean;
import net.shopnc.b2b2c.android.bean.OrderReturnDetailsBean;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderExchangeListActivity extends BaseActivity {
    private MyShopApplication myApplication;
    private LayoutInflater inflater;
    private LinearLayout addOne;
    private Button btnMoney,btnGoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_exchange_list);
        setCommonHeader("");
        MyExceptionHandler.getInstance().setContext(this);
        myApplication = (MyShopApplication) getApplicationContext();
        inflater = LayoutInflater.from(OrderExchangeListActivity.this);
        initViews();
        setCombinButton();
        setListEmpty(R.drawable.nc_icon_order, "您还没有相关退款订单", "随便逛逛吧");
        loadMoneyDatas();
    }

    public void initViews(){
        addOne=(LinearLayout)findViewById(R.id.addOne);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(btnMoney.isActivated()){
            loadMoneyDatas();
        }else{
            loadGoodsDatas();
        }
    }

    /**
     * 设置头部切换按钮
     */
    private void setCombinButton() {
        btnMoney = (Button) findViewById(R.id.btnMoney);
        btnGoods = (Button) findViewById(R.id.btnGoods);

        btnMoney.setActivated(true);
        btnMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadMoneyDatas();
                btnMoney.setActivated(true);
                btnGoods.setActivated(false);
            }
        });

        btnGoods.setActivated(false);
        btnGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadGoodsDatas();
                btnMoney.setActivated(false);
                btnGoods.setActivated(true);
            }
        });
    }

    private void loadMoneyDatas(){
        String url= Constants.URL_ORDER_EXCHANGE_MONEY_LIST+"&key="+myApplication.getLoginKey();
        RemoteDataHandler.asyncDataStringGet(url,new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json=data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try{
                        addOne.removeAllViews();
                        org.json.JSONObject obj=new org.json.JSONObject(json);
                        ArrayList<OrderRefundDetailsBean> refundDetails=new ArrayList<OrderRefundDetailsBean>(JSONObject.parseArray(obj.getString("refund_list"),OrderRefundDetailsBean.class));
                        for (OrderRefundDetailsBean d:refundDetails){
                            View orderListView = inflater.inflate(R.layout.exchange_refund_list_item, null);
                            initUIOrderList(orderListView, d);
                            addOne.addView(orderListView);
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                }else{
                    T.showShort(OrderExchangeListActivity.this,json);
                }
            }
        });


    }


    public void loadGoodsDatas(){
        String url= Constants.URL_ORDER_EXCHANGE_GOODS_LIST+"&key="+myApplication.getLoginKey();
        RemoteDataHandler.asyncDataStringGet(url,new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json=data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try{
                        addOne.removeAllViews();
                        org.json.JSONObject obj=new org.json.JSONObject(json);
                        ArrayList<OrderReturnDetailsBean> refundDetails=new ArrayList<OrderReturnDetailsBean>(JSONObject.parseArray(obj.getString("return_list"),OrderReturnDetailsBean.class));
                        for (OrderReturnDetailsBean d:refundDetails){
                            View orderListView = inflater.inflate(R.layout.exchange_refund_list_item, null);
                            initUIOrderGoodsList(orderListView, d);
                            addOne.addView(orderListView);
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                }else{
                    T.showShort(OrderExchangeListActivity.this,json);
                }
            }
        });
    }

    private void initUIOrderGoodsList(View view, final OrderReturnDetailsBean returnDetailsBean){
        TextView textOrderStoreName = view.findViewById(R.id.textOrderStoreName);
        TextView  textOrderSuccess = view.findViewById(R.id.textOrderSuccess);
        TextView textTime = view.findViewById(R.id.textTime);
        TextView textExchangeAllPrice = view.findViewById(R.id.textExchangeAllPrice);
        LinearLayout addViewID= view.findViewById(R.id.addViewID);
        LinearLayout layoutNum= view.findViewById(R.id.layoutNum);
        TextView textExchangeNum= view.findViewById(R.id.textExchangeNum);
        Button textExchangeDetails= view.findViewById(R.id.textExchangeDetails);
        Button textExchangeSure= view.findViewById(R.id.textExchangeSure);



//        if(returnDetailsBean.getGoods_state_v().equals("1")){
//            textExchangeSure.setText("退货发货");
//        }else if(returnDetailsBean.getGoods_state_v().equals("2")){
//            textExchangeSure.setText("商家待收货");
//        }else if(returnDetailsBean.getGoods_state_v().equals("3")){
//            textExchangeSure.setText("商家未收到");
//        }else if(returnDetailsBean.getGoods_state_v().equals("4")){
//            textExchangeSure.setText("商家已收到");
//        }

        if (returnDetailsBean.getShip_state().equals("1")){
            textExchangeSure.setVisibility(View.VISIBLE);
            textExchangeSure.setText("退货发货");
        }

        if (returnDetailsBean.getSeller_state().equals("待审核")||returnDetailsBean.getSeller_state().equals("不同意")){
            textExchangeSure.setVisibility(View.GONE);
        }

        if(returnDetailsBean.getDelay_state().equals("1")){
            textExchangeSure.setText("延迟收货");
        }

        textExchangeSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(returnDetailsBean.getAdmin_state_v().equals("1")){
                    Intent intent=new Intent(OrderExchangeListActivity.this, OrderExchangeSendGoodsActivity.class);
                    intent.putExtra("id",returnDetailsBean.getRefund_id());
                    startActivity(intent);
                }
                if(returnDetailsBean.getDelay_state().equals("1")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(OrderExchangeListActivity.this);
                    builder.setTitle("操作提示")
                            .setMessage("发货 5 天后，当商家选择未收到则要进行延迟时间操作；\n" +
                                    "如果超过 7 天不处理按弃货处理，直接由管理员确认退款")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                }
                            })
                            .setPositiveButton("确认",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            String url=Constants.URL_ORDER_EXCHANGE_GOODS_DELAY;
                                            HashMap<String, String> params = new HashMap<String, String>();
                                            params.put("key", myApplication.getLoginKey());
                                            params.put("return_id", returnDetailsBean.getRefund_id());

                                            RemoteDataHandler.asyncLoginPostDataString(url,params,myApplication,new RemoteDataHandler.Callback() {
                                                @Override
                                                public void dataLoaded(ResponseData data) {
                                                    String json=data.getJson();
                                                    if(data.getCode()== HttpStatus.SC_OK){
                                                        if (json.equals("1")) {
                                                            finish();
                                                            T.showShort(OrderExchangeListActivity.this,"提交成功");
                                                        }else{
                                                            T.showShort(OrderExchangeListActivity.this,"提交失败");
                                                        }

                                                    }else{
                                                        ShopHelper.showApiError(OrderExchangeListActivity.this, json);
                                                    }
                                                }
                                            });
                                        }
                                    }).create().show();
                }
            }
        });
        textExchangeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OrderExchangeListActivity.this, OrderExchangeControlDetailsActivity.class);
                intent.putExtra("id",returnDetailsBean.getRefund_id());
                intent.putExtra("type","goods");
                startActivity(intent);
            }
        });
        textOrderStoreName.setText(returnDetailsBean.getStore_name());
        textOrderSuccess.setText(returnDetailsBean.getSeller_state());
        textTime.setText(returnDetailsBean.getAdd_time());
        textExchangeAllPrice.setText(returnDetailsBean.getRefund_amount());
        layoutNum.setVisibility(View.VISIBLE);
        textExchangeNum.setText(returnDetailsBean.getGoods_num());

        View goodView=inflater.inflate(R.layout.listivew_order_goods_item,null);
        ImageView imageGoodsPic = goodView.findViewById(R.id.imageGoodsPic);
        TextView textGoodsName = goodView.findViewById(R.id.textGoodsName);
        TextView textGoodsSPec= goodView.findViewById(R.id.textGoodsSPec);
        textGoodsName.setText(returnDetailsBean.getGoods_name());
        TextView textGoodsPrice = goodView.findViewById(R.id.textGoodsPrice);
        TextView textGoodsNUM = goodView.findViewById(R.id.textGoodsNUM);
        textGoodsPrice.setVisibility(View.GONE);
        textGoodsNUM.setVisibility(View.GONE);
        Glide.with(OrderExchangeListActivity.this).load(returnDetailsBean.getGoods_img_360()).into(imageGoodsPic);
        if(returnDetailsBean.getGoods_spec()==null||returnDetailsBean.getGoods_spec().equals("null")||returnDetailsBean.getGoods_spec().equals("")){
            textGoodsSPec.setVisibility(View.GONE);
        }else{
            textGoodsSPec.setText(returnDetailsBean.getGoods_spec());
        }
        addViewID.addView(goodView);

        goodView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OrderExchangeListActivity.this, OrderExchangeControlDetailsActivity.class);
                intent.putExtra("id",returnDetailsBean.getRefund_id());
                intent.putExtra("type","goods");
                startActivity(intent);

            }
        });

    }

    /**
     * 生成界面
     */
    public void initUIOrderList(View view, final OrderRefundDetailsBean orderList) {
        TextView textOrderStoreName = view.findViewById(R.id.textOrderStoreName);
        TextView  textOrderSuccess = view.findViewById(R.id.textOrderSuccess);
        TextView textTime = view.findViewById(R.id.textTime);
        TextView textExchangeAllPrice = view.findViewById(R.id.textExchangeAllPrice);
        Button textExchangeDetails= view.findViewById(R.id.textExchangeDetails);
        textExchangeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OrderExchangeListActivity.this, OrderExchangeControlDetailsActivity.class);
                intent.putExtra("id",orderList.getRefund_id());
                intent.putExtra("type","money");
                startActivity(intent);
            }
        });
        Button textExchangeSure= view.findViewById(R.id.textExchangeSure);
        textExchangeSure.setVisibility(View.GONE);
        LinearLayout addViewID= view.findViewById(R.id.addViewID);
        LinearLayout layoutNum= view.findViewById(R.id.layoutNum);
        textOrderStoreName.setText(orderList.getStore_name());
        textOrderSuccess.setText(orderList.getSeller_state());
        textTime.setText(orderList.getAdd_time());
        textExchangeAllPrice.setText(orderList.getRefund_amount());
        layoutNum.setVisibility(View.GONE);


        //显示商品列表
        ArrayList<GoodsListBean> goods=new ArrayList<GoodsListBean>(JSONObject.parseArray(orderList.getGoods_list(),GoodsListBean.class));
        for (GoodsListBean good:goods){
            View goodView=inflater.inflate(R.layout.listivew_order_goods_item,null);
            initGoodsListUI(goodView,good,orderList.getRefund_id());
            addViewID.addView(goodView);
        }

    }



    public void initGoodsListUI(View view,GoodsListBean good,final String refundId){
        ImageView imageGoodsPic = view.findViewById(R.id.imageGoodsPic);
        TextView textGoodsName = view.findViewById(R.id.textGoodsName);
        TextView textGoodsSPec= view.findViewById(R.id.textGoodsSPec);
        textGoodsName.setText(good.getGoods_name());
        Glide.with(OrderExchangeListActivity.this).load(good.getGoods_img_360()).into(imageGoodsPic);
        if(good.getGoods_spec()==null||good.getGoods_spec().equals("null")||good.getGoods_spec().equals("")){
            textGoodsSPec.setVisibility(View.GONE);
        }else{
            textGoodsSPec.setText(good.getGoods_spec());
        }
        TextView textGoodsPrice = view.findViewById(R.id.textGoodsPrice);
        TextView textGoodsNUM = view.findViewById(R.id.textGoodsNUM);
        textGoodsPrice.setVisibility(View.GONE);
        textGoodsNUM.setVisibility(View.GONE);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OrderExchangeListActivity.this, OrderExchangeControlDetailsActivity.class);
                intent.putExtra("id",refundId);
                intent.putExtra("type","money");
                startActivity(intent);
            }
        });

    }

}
