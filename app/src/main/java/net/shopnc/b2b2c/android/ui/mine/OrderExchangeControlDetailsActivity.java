package net.shopnc.b2b2c.android.ui.mine;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.ReturnBean;
import net.shopnc.b2b2c.android.bean.ReturnPayDetails;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;

public class OrderExchangeControlDetailsActivity extends BaseActivity {
    private MyShopApplication myApplication;
    private String id;
    private String type;
    private TextView refund_sn, reason_info, refund_amount, buyer_message, seller_state, admin_state, admin_message, seller_message, refund_code, pay_amount, pd_amount, rcb_amount;
    private ImageView img1, img2, img3;
    private LinearLayout pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_exchange_control_details);
        MyExceptionHandler.getInstance().setContext(this);
        myApplication = (MyShopApplication) getApplicationContext();
        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        setCommonHeader("处理详情");
        initView();
        loadDatas();
    }

    public void initView() {
        refund_sn = (TextView) findViewById(R.id.refund_sn);
        reason_info = (TextView) findViewById(R.id.reason_info);
        refund_amount = (TextView) findViewById(R.id.refund_amount);
        buyer_message = (TextView) findViewById(R.id.buyer_message);
        seller_state = (TextView) findViewById(R.id.seller_state);
        admin_state = (TextView) findViewById(R.id.admin_state);
        admin_message = (TextView) findViewById(R.id.admin_message);
        seller_message = (TextView) findViewById(R.id.seller_message);
        refund_code = (TextView) findViewById(R.id.refund_code);
        pay_amount = (TextView) findViewById(R.id.pay_amount);
        pd_amount = (TextView) findViewById(R.id.pd_amount);
        rcb_amount = (TextView) findViewById(R.id.rcb_amount);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        pay = (LinearLayout) findViewById(R.id.pay);
    }


    public void loadDatas() {
        String url = "";

        if (type.equals("money")) {
            url = Constants.URL_ORDER_EXCHANGE_MONEY_DETAILS + "&key=" + myApplication.getLoginKey() + "&refund_id=" + id;
            RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
                @Override
                public void dataLoaded(ResponseData data) {
                    String json = data.getJson();
                    if (data.getCode() == HttpStatus.SC_OK) {
                        try {
                            org.json.JSONObject obj = new org.json.JSONObject(json);
                            ReturnBean refund = JSONObject.parseObject(obj.getString("refund").equals("[]") ? "{}" : obj.getString("refund"), ReturnBean.class);
                            JSONArray pics = new JSONArray(obj.getString("pic_list"));
                            ReturnPayDetails payDetails = JSONObject.parseObject((obj.getString("detail_array") == null || obj.getString("detail_array").equals("[]") ? null : obj.getString("detail_array")), ReturnPayDetails.class);
                            loadUi(refund, pics, payDetails);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } else {
                        T.showShort(OrderExchangeControlDetailsActivity.this, json);
                    }
                }
            });
        } else if (type.equals("goods")) {
            url = Constants.URL_ORDER_EXCHANGE_GOODS_DETAILS + "&key=" + myApplication.getLoginKey() + "&return_id=" + id;
            RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
                @Override
                public void dataLoaded(ResponseData data) {
                    String json = data.getJson();
                    Log.i("QINGoods", json);
                    if (data.getCode() == HttpStatus.SC_OK) {
                        try {
                            org.json.JSONObject obj = new org.json.JSONObject(json);
                            ReturnBean refund = JSONObject.parseObject(obj.getString("return_info").equals("[]") ? "{}" : obj.getString("return_info"), ReturnBean.class);
                            JSONArray pics = new JSONArray(obj.getString("pic_list"));
                            ReturnPayDetails payDetails = JSONObject.parseObject(obj.getString("detail_array").equals("[]") ? null : obj.getString("detail_array"), ReturnPayDetails.class);
                            loadUi(refund, pics, payDetails);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        T.showShort(OrderExchangeControlDetailsActivity.this, json);
                    }
                }
            });
        }

    }


    public void loadPic(int i, String pic) {
        if (i == -1) {
            img1.setVisibility(View.GONE);
            img2.setVisibility(View.GONE);
            img3.setVisibility(View.GONE);
        }
        if (i == 0) {
            img1.setVisibility(View.VISIBLE);
            Glide.with(OrderExchangeControlDetailsActivity.this).load(pic).error(R.drawable.ic_launcher).into(img1);
        }
        if (i == 1) {
            img2.setVisibility(View.VISIBLE);
            Glide.with(OrderExchangeControlDetailsActivity.this).load(pic).error(R.drawable.ic_launcher).into(img2);
        }
        if (i == 2) {
            img3.setVisibility(View.VISIBLE);
            Glide.with(OrderExchangeControlDetailsActivity.this).load(pic).error(R.drawable.ic_launcher).into(img3);
        }
    }


    private void loadUi(ReturnBean refund, JSONArray pics, ReturnPayDetails payDetails) {
        refund_sn.setText(refund.getRefund_sn());
        reason_info.setText(refund.getReason_info());
        refund_amount.setText(refund.getRefund_amount());
        buyer_message.setText(refund.getBuyer_message());
        seller_state.setText(refund.getSeller_state());
        admin_state.setText(refund.getAdmin_state());
        admin_message.setText(refund.getAdmin_message());
        seller_message.setText(refund.getSeller_message());

        if (payDetails == null) {
            pay.setVisibility(View.GONE);
        } else {
            pay.setVisibility(View.VISIBLE);
            refund_code.setText(payDetails.getRefund_code());
            pay_amount.setText(payDetails.getPay_amount());
            pd_amount.setText(payDetails.getPd_amount());
            rcb_amount.setText(payDetails.getRcb_amount());
        }

        if (pics == null || pics.length() == 0) {
            loadPic(-1, "");
        }
        try {
            for (int i = 0; i < pics.length(); i++) {
                String pic = (String) pics.get(i);
                loadPic(i, pic);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
