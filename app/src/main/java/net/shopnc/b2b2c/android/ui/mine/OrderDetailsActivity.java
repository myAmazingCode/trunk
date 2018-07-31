package net.shopnc.b2b2c.android.ui.mine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.OrderDetailsBean;
import net.shopnc.b2b2c.android.bean.OrderGoods;
import net.shopnc.b2b2c.android.bean.ZenPinBean;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ui.store.newStoreInFoActivity;
import net.shopnc.b2b2c.android.ui.type.EvaluateActivity;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by qin on 2016/1/8.
 */
public class OrderDetailsActivity extends BaseActivity {
    private MyShopApplication myApplication;
    private String orderId;
    private TextView textstate_desc;
    private TextView textreciver_name;
    private TextView textreciver_phone;
    private TextView textreciver_addr;
    private TextView textinvoice;
    private TextView textOrderStoreName;
    private TextView textpromotion;
    private TextView textshipping_fee;
    private TextView textorder_amount;
    private TextView textChatMe;
    private TextView textCallMe;
    private TextView textOrderSn;
    private TextView textAddTime;
    private TextView textTips;
    private TextView textWuLiu;
    private Button textOrderOperation;
    private TextView textFuKuanStyle;
    private LinearLayout lll;
    private LinearLayout addViewID;
    private RelativeLayout layoutWuLiu;
    private LinearLayout layoutYouHui;
    private LayoutInflater inflater;
    private TextView textpayment_time;
    private TextView textshipping_time;
    private TextView textfinnshed_time;
    private Button textOrderSure;
    private LinearLayout layoutMessage;
    private TextView textmessage;
    private LinearLayout layoutInvoice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        MyExceptionHandler.getInstance().setContext(this);
        inflater = LayoutInflater.from(OrderDetailsActivity.this);
        setCommonHeader("订单详情");
        myApplication = (MyShopApplication) getApplicationContext();
        orderId = getIntent().getStringExtra("order_id");
        initView();
        loadOrderDetails();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadOrderDetails();
    }

    private void initView() {
        textstate_desc = (TextView) findViewById(R.id.textstate_desc);
        textreciver_name = (TextView) findViewById(R.id.textreciver_name);
        textreciver_phone = (TextView) findViewById(R.id.textreciver_phone);
        textreciver_addr = (TextView) findViewById(R.id.textreciver_addr);
        textinvoice = (TextView) findViewById(R.id.textinvoice);
        textOrderStoreName = (TextView) findViewById(R.id.textOrderStoreName);
        textshipping_fee = (TextView) findViewById(R.id.textshipping_fee);
        textorder_amount = (TextView) findViewById(R.id.textorder_amount);
        textChatMe = (TextView) findViewById(R.id.textChatMe);
        textCallMe = (TextView) findViewById(R.id.textCallMe);
        textOrderSn = (TextView) findViewById(R.id.textOrderSn);
        textAddTime = (TextView) findViewById(R.id.textAddTime);
        textTips = (TextView) findViewById(R.id.textTips);
        lll = (LinearLayout) findViewById(R.id.lll);
        addViewID = lll.findViewById(R.id.addViewID);
        textFuKuanStyle = (TextView) findViewById(R.id.textFuKuanStyle);
        textOrderOperation = (Button) findViewById(R.id.textOrderOperation);
        textWuLiu = (TextView) findViewById(R.id.textWuLiu);
        layoutWuLiu = (RelativeLayout) findViewById(R.id.layoutWuLiu);
        layoutYouHui = (LinearLayout) findViewById(R.id.layoutYouHui);
        textpromotion = (TextView) findViewById(R.id.textpromotion);
        textpayment_time = (TextView) findViewById(R.id.textpayment_time);
        textshipping_time = (TextView) findViewById(R.id.textshipping_time);
        textfinnshed_time = (TextView) findViewById(R.id.textfinnshed_time);
        textOrderSure = (Button) findViewById(R.id.textOrderSure);
        layoutMessage = (LinearLayout) findViewById(R.id.layoutMessage);
        textmessage = (TextView) findViewById(R.id.textmessage);
        layoutInvoice = (LinearLayout) findViewById(R.id.layoutInvoice);

    }

    private void loadOrderDetails() {
        String url = Constants.URL_ORDER_DETAILS + "&key=" + myApplication.getLoginKey() + "&order_id=" + orderId;

        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                Log.i("QIN", json);
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        String order_info = obj.getString("order_info");
                        final OrderDetailsBean orderDetails = com.alibaba.fastjson.JSONObject.parseObject(order_info, OrderDetailsBean.class);
                        textstate_desc.setText(orderDetails.getState_desc());
                        textreciver_name.setText(orderDetails.getReciver_name());
                        textreciver_phone.setText(orderDetails.getReciver_phone());
                        textreciver_addr.setText("收货地址：" + orderDetails.getReciver_addr());
                        textOrderStoreName.setText(orderDetails.getStore_name());
                        textshipping_fee.setText("￥" + orderDetails.getShipping_fee());
                        textorder_amount.setText("￥" + orderDetails.getOrder_amount());
                        textFuKuanStyle.setText(orderDetails.getPayment_name());
                        textAddTime.setText("创建时间：" + orderDetails.getAdd_time());
                        textOrderSn.setText("订单编号：" + orderDetails.getOrder_sn());


                        //判断是否显示优惠信息
                        if (orderDetails.getPromotion().equals("[]")) {
                            layoutYouHui.setVisibility(View.GONE);
                        } else {
                            layoutYouHui.setVisibility(View.VISIBLE);
                            String s = orderDetails.getPromotion().substring(2);
                            textpromotion.setText(s.substring(0, s.length() - 2));
                        }


                        //判断物流信息的显示
                        if (orderDetails.getState_desc().equals("待收货") || orderDetails.getState_desc().equals("交易完成")) {
                            textTips.setVisibility(View.GONE);
                            layoutWuLiu.setVisibility(View.VISIBLE);
                            loadNewWuLiuInfo();
                        }

                        layoutWuLiu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(OrderDetailsActivity.this, OrderDeliverDetailsActivity.class);
                                intent.putExtra("order_id", orderDetails.getOrder_id());
                                startActivity(intent);
                            }
                        });

                        textOrderSure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (textOrderSure.getText().toString().equals("确认收货")) {
                                    loadingSaveOrderData(Constants.URL_ORDER_RECEIVE, orderDetails.getOrder_id());
                                } else if (textOrderSure.getText().toString().equals("评价订单")) {
                                    Intent i = new Intent(OrderDetailsActivity.this, EvaluateActivity.class);
                                    i.putExtra("order_id", orderDetails.getOrder_id());
                                    OrderDetailsActivity.this.startActivity(i);
                                }
                            }
                        });

                        textOrderStoreName.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                if (orderDetails.getStore_id().equals("1")) {

                                } else {
                                    Intent intent = new Intent(OrderDetailsActivity.this, newStoreInFoActivity.class);
                                    intent.putExtra("store_id", orderDetails.getStore_id());
                                    startActivity(intent);
                                }
                            }
                        });

                        //待付款订单的提示显示
                        if (orderDetails.getState_desc().equals("已取消")) {
                            textTips.setVisibility(View.GONE);
                            textOrderOperation.setVisibility(View.GONE);
                        }

                        //待付款订单的提示显示
                        if (orderDetails.getState_desc().equals("待付款")) {
                            textTips.setVisibility(View.VISIBLE);
                            textTips.setText(orderDetails.getOrder_tips());
                            textOrderOperation.setVisibility(View.VISIBLE);
                            textOrderOperation.setText("取消订单");
                        }

                        //待收货订单的提示显示
                        if (orderDetails.getState_desc().equals("待收货")) {
                            textOrderOperation.setVisibility(View.VISIBLE);
                            textOrderOperation.setText("查看物流");
                            textOrderSure.setVisibility(View.VISIBLE);
                            textOrderSure.setText("确认收货");
                        }
                        //待评价订单的提示显示
                        if (orderDetails.getState_desc().equals("交易完成")) {
                            textOrderOperation.setVisibility(View.VISIBLE);
                            textOrderOperation.setText("查看物流");
                            textOrderSure.setVisibility(View.VISIBLE);
                            textOrderSure.setText("评价订单");
                        }

                        //待自提订单的提示显示
                        if (orderDetails.getState_desc().equals("待自提")) {
                            textOrderOperation.setVisibility(View.VISIBLE);
                            textOrderOperation.setText("订单退款");
                        }

                        //待发货订单的提示显示
                        if (orderDetails.getState_desc().equals("待发货")) {
                            textOrderOperation.setVisibility(View.VISIBLE);
                            textOrderOperation.setText("订单退款");
                        }

                        //判断付款时间的显示
                        if (!orderDetails.getPayment_time().isEmpty()) {
                            textpayment_time.setVisibility(View.VISIBLE);
                            textpayment_time.setText("付款时间：" + orderDetails.getPayment_time());
                        }

                        //判断发货时间的显示
                        if (!orderDetails.getShipping_time().isEmpty()) {
                            textshipping_time.setVisibility(View.VISIBLE);
                            textshipping_time.setText("发货时间：" + orderDetails.getShipping_time());
                        }

                        //判断完成时间的显示
                        if (!orderDetails.getFinnshed_time().isEmpty()) {
                            textfinnshed_time.setVisibility(View.VISIBLE);
                            textfinnshed_time.setText("完成时间：" + orderDetails.getFinnshed_time());
                        }

                        //判断买家留言的显示
                        if (orderDetails.getOrder_message() != null && !orderDetails.getOrder_message().isEmpty()) {
                            layoutMessage.setVisibility(View.VISIBLE);
                            textmessage.setText(orderDetails.getOrder_message());
                        }

                        //判断买家留言的显示
                        if (orderDetails.getInvoice() != null && !orderDetails.getInvoice().equals("")) {
                            layoutInvoice.setVisibility(View.VISIBLE);
                            textinvoice.setText(orderDetails.getInvoice());
                        } else {
                            layoutInvoice.setVisibility(View.GONE);
                        }


                        if (orderDetails.getIf_lock().equals("true")) {
                            textOrderOperation.setVisibility(View.GONE);
                            textOrderSure.setVisibility(View.VISIBLE);
                            textOrderSure.setText("退款/退货中");
                        }

                        textOrderOperation.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final String s = textOrderOperation.getText().toString();
                                if (s.equals("查看物流")) {
                                    Intent intent = new Intent(OrderDetailsActivity.this, OrderDeliverDetailsActivity.class);
                                    intent.putExtra("order_id", orderDetails.getOrder_id());
                                    startActivity(intent);
                                    return;
                                }
                                AlertDialog.Builder builder = new AlertDialog.Builder(OrderDetailsActivity.this);
                                builder.setTitle("操作提示")
                                        .setMessage("是否确认操作")
                                        .setNegativeButton("取消",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int whichButton) {
                                                    }
                                                })
                                        .setPositiveButton("确认",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(
                                                            DialogInterface dialog,
                                                            int whichButton) {
                                                        if (s.equals("取消订单")) {
                                                            loadingSaveOrderData(Constants.URL_ORDER_CANCEL, orderDetails.getOrder_id());
                                                        } else if (s.equals("订单退款")) {
                                                            Intent intent = new Intent(OrderDetailsActivity.this, OrderExchangeActivity.class);
                                                            intent.putExtra("order_id", orderDetails.getOrder_id());
                                                            startActivity(intent);
                                                        }
                                                    }
                                                }).create().show();
                            }
                        });


                        LinearLayout llGift = null;
                        LinearLayout llGiftList = null;
                        ArrayList<OrderGoods> goodsDatas = new ArrayList<OrderGoods>(com.alibaba.fastjson.JSONArray.parseArray(orderDetails.getGoods_list(), OrderGoods.class));
                        TextView imgZeng = null;
                        addViewID.removeAllViews();
                        for (int j = 0; j < goodsDatas.size(); j++) {
                            final OrderGoods ordergoodsList = goodsDatas.get(j);
                            View orderGoodsListView = inflater.inflate(
                                    R.layout.listivew_order_goods_item, null);
                            addViewID.addView(orderGoodsListView);

                            ImageView imageGoodsPic = orderGoodsListView
                                    .findViewById(R.id.imageGoodsPic);
                            TextView textGoodsName = orderGoodsListView
                                    .findViewById(R.id.textGoodsName);
                            TextView textGoodsPrice = orderGoodsListView
                                    .findViewById(R.id.textGoodsPrice);
                            TextView textGoodsNUM = orderGoodsListView
                                    .findViewById(R.id.textGoodsNUM);
                            imgZeng = (TextView) findViewById(R.id.imgZeng);
                            TextView textGoodsSPec = orderGoodsListView.findViewById(R.id.textGoodsSPec);
                            Button btnTuiMoney = orderGoodsListView.findViewById(R.id.btnTuiMoney);
                            Button btnTuiGoods = orderGoodsListView.findViewById(R.id.btnTuiGoods);
                            llGift = orderGoodsListView.findViewById(R.id.llGift);
                            llGiftList = orderGoodsListView.findViewById(R.id.llGiftList);

                            textGoodsName.setText(ordergoodsList.getGoods_name());
                            textGoodsPrice.setText("￥" + ordergoodsList.getGoods_price());
                            textGoodsNUM.setText("×" + ordergoodsList.getGoods_num());
                            Glide.with(OrderDetailsActivity.this).load(ordergoodsList.getImage_url()).into(imageGoodsPic);
                            if (ordergoodsList.getGoods_spec() == null) {
                                textGoodsSPec.setVisibility(View.GONE);
                            } else {
                                textGoodsSPec.setText(ordergoodsList.getGoods_spec());
                            }
                            if (ordergoodsList.getRefund().equals("1")) {
                                btnTuiMoney.setVisibility(View.VISIBLE);
                                btnTuiGoods.setVisibility(View.VISIBLE);
                            }

                            btnTuiMoney.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent i = new Intent(OrderDetailsActivity.this, OrderGoodsTuiMoneyActivity.class);
                                    i.putExtra("order_id", orderDetails.getOrder_id());
                                    i.putExtra("order_goods_id", ordergoodsList.getRec_id());
                                    startActivity(i);
                                }
                            });

                            btnTuiGoods.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent i = new Intent(OrderDetailsActivity.this, OrderGoodsTuiGoodsActivity.class);
                                    i.putExtra("order_id", orderDetails.getOrder_id());
                                    i.putExtra("order_goods_id", ordergoodsList.getRec_id());
                                    startActivity(i);
                                }
                            });


                            textGoodsName.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent i = new Intent(OrderDetailsActivity.this, GoodsDetailsActivity.class);
                                    i.putExtra("goods_id", ordergoodsList.getGoods_id());
                                    i.putExtra("order_goods_id", ordergoodsList.getRec_id());
                                    OrderDetailsActivity.this.startActivity(i);
                                }
                            });
                            textChatMe.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    ShopHelper.showIm(OrderDetailsActivity.this, myApplication, orderDetails.getStore_member_id(), orderDetails.getStore_name());
                                }
                            });
                            textCallMe.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (orderDetails.getStore_phone() == null) {
                                        Toast.makeText(OrderDetailsActivity.this, "商家店铺电话为空", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + orderDetails.getStore_phone()));
                                        startActivity(intent);
                                        finish();

                                    }

                                }
                            });
                        }

                        if (orderDetails.getZengpin_list().equals("") || orderDetails.getZengpin_list().equals("[]")) {
                            llGift.setVisibility(View.GONE);
                        } else {
                            imgZeng.setVisibility(View.VISIBLE);
                            ArrayList<ZenPinBean> giftList = new ArrayList<ZenPinBean>(com.alibaba.fastjson.JSONArray.parseArray(orderDetails.getZengpin_list(), ZenPinBean.class));
                            for (int j = 0; j < giftList.size(); j++) {
                                View giftView = inflater.inflate(R.layout.cart_list_gift_item, null);
                                TextView tvGiftInfo = giftView.findViewById(R.id.tvGiftInfo);
                                tvGiftInfo.setText(giftList.get(j).getGoods_name() + "x" + giftList.get(j).getGoods_num());
                                llGiftList.addView(giftView);
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ShopHelper.showApiError(OrderDetailsActivity.this, json);
                }
            }
        });

    }

    public void loadNewWuLiuInfo() {
        String url = Constants.URL_ORDER_WULIU_NEW_ONE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("order_id", orderId);

        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        String s = obj.getString("deliver_info");
                        JSONObject o = new JSONObject(s);
                        textWuLiu.setText(o.getString("time") + o.getString("context"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
//                    ShopHelper.showApiError(OrderDetailsActivity.this, json);
                }
            }
        });

    }

    /**
     * 确认收货、取消订单
     *
     * @param url
     * @param //orderID 订单ID
     */
    public void loadingSaveOrderData(String url, String order_id) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("order_id", order_id);

        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication,
                new RemoteDataHandler.Callback() {
                    @Override
                    public void dataLoaded(ResponseData data) {
                        String json = data.getJson();
                        if (data.getCode() == HttpStatus.SC_OK) {
                            if (json.equals("1")) {
                                loadOrderDetails();
                                Toast.makeText(OrderDetailsActivity.this, "订单操作成功",
                                        Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            try {
                                JSONObject obj2 = new JSONObject(json);
                                String error = obj2.getString("error");
                                if (error != null) {
                                    Toast.makeText(OrderDetailsActivity.this, error,
                                            Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

}
