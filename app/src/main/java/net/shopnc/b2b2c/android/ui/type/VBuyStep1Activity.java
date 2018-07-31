package net.shopnc.b2b2c.android.ui.type;

import java.util.HashMap;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.VirtualGoodsInFo;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler.Callback;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ui.mine.VirtualListActivity;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * 虚拟购买一步界面
 *
 * @author KingKong-HE
 * @Time 2015-1-15
 * @Email KingKong@QQ.COM
 */
public class VBuyStep1Activity extends Activity implements OnClickListener {

    private String is_fcode;//是否为F码商品 1是 0否

    private String ifcart;//购物车购买标志 1购物车 0不是

    private String cart_id;//购买参数

    private String goodscount;

    private boolean showAvailableRCBalance = false;//标识是否显示充值卡

    private boolean showAvailablePredeposit = false;//标识是否显示预存款

    private double goods_total = 0.00;//总价

    private double goods_freight = 0.00;//运费

    private double goods_voucher = 0.00;//折扣价格

    private String if_pd_pay = "0";//记录是否充值卡支付  1-使用 0-不使用

    private String if_rcb_pay = "0";//记录是否预存款支付 1-使用 0-不使用

    private String pay_name = "online";//记录付款方式，可选值 online(线上付款) offline(货到付款)

    private MyShopApplication myApplication;

    private TextView textViewGoodsFreight, textViewGoodsTotal, textVoucher, textviewAllPrice, goodsNameID, goodsPriceID, goodsNumID, storeNameID;

    private RadioButton ifshowOnpayID, ifshowOffpayID;

    private LinearLayout predepositLayoutID;

    private CheckBox availablePredepositID, availableRCBalanceID;

    private Button commitID;

    private ImageView goodsPicID;

    private EditText editPasswordID, editPhoneID;

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vbuy_step1_view);
        MyExceptionHandler.getInstance().setContext(this);
        myApplication = (MyShopApplication) getApplicationContext();

        ifcart = getIntent().getStringExtra("ifcart");
        cart_id = getIntent().getStringExtra("cart_id");
        goodscount = getIntent().getStringExtra("goodscount");

        initViewID();

    }

    /**
     * 初始化注册控件ID
     */
    public void initViewID() {

        ImageView imageBack = findViewById(R.id.imageBack);

        editPhoneID = findViewById(R.id.editPhoneID);

        editPasswordID = findViewById(R.id.editPasswordID);

        textviewAllPrice = findViewById(R.id.textviewAllPrice);

        goodsNameID = findViewById(R.id.goodsNameID);
        goodsPriceID = findViewById(R.id.goodsPriceID);
        goodsNumID = findViewById(R.id.goodsNumID);
        storeNameID = findViewById(R.id.storeNameID);

        textViewGoodsFreight = findViewById(R.id.textViewGoodsFreight);

        textViewGoodsTotal = findViewById(R.id.textViewGoodsTotal);

        ifshowOffpayID = findViewById(R.id.ifshowOffpayID);

        ifshowOnpayID = findViewById(R.id.ifshowOnpayID);

        predepositLayoutID = findViewById(R.id.predepositLayoutID);

        availablePredepositID = findViewById(R.id.availablePredepositID);

        availableRCBalanceID = findViewById(R.id.availableRCBalanceID);

        goodsPicID = findViewById(R.id.goodsPicID);

        commitID = findViewById(R.id.commitID);

        MyifshowOnpayRadioButtonClickListener onpayRadioButtonClickListener = new MyifshowOnpayRadioButtonClickListener();
        ifshowOffpayID.setOnClickListener(onpayRadioButtonClickListener);
        ifshowOnpayID.setOnClickListener(onpayRadioButtonClickListener);

        imageBack.setOnClickListener(this);

        commitID.setOnClickListener(this);

        loadingBuyStep1Data();//加载购买一数据

        availablePredepositID.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if_pd_pay = "1";
                } else {
                    if_pd_pay = "0";
                }
            }
        });

        availableRCBalanceID.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if_rcb_pay = "1";
                } else {
                    if_rcb_pay = "0";
                }
            }
        });
    }

    /**
     * 加载购买一数据
     */
    public void loadingBuyStep1Data() {
        String url = Constants.URL_MEMBER_VR_BUY;
        //index.php?act=member_buy&op=test
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("goods_id", cart_id);
        params.put("quantity", goodscount);

        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject jsonObj = new JSONObject(json);
                        String goods_info = jsonObj.getString("goods_info");
                        String member_info = jsonObj.getString("member_info");
                        JSONObject member_infojsonObj = new JSONObject(member_info);
                        String Available_predeposit = member_infojsonObj.getString("available_predeposit");
                        String Available_Rcb_pay = member_infojsonObj.getString("available_rc_balance");
                        String member_mobile = member_infojsonObj.getString("member_mobile");
                        if (member_mobile != null && !member_mobile.equals("null") && !member_mobile.equals("")) {
                            editPhoneID.setText(member_mobile);
                        }

                        VirtualGoodsInFo bean = VirtualGoodsInFo.newInstanceList(goods_info);

                        goodsNameID.setText(bean.getGoods_name() == null ? "" : bean.getGoods_name());
                        goodsPriceID.setText("价格：￥" + (bean.getGoods_price() == null ? "0.00" : bean.getGoods_price()));
                        goodsNumID.setText("数量：" + (bean.getQuantity() == null ? "0" : bean.getQuantity()));
                        storeNameID.setText(bean.getStore_name() == null ? "" : bean.getStore_name());
                        textViewGoodsTotal.setText("￥" + (bean.getGoods_total() == null ? "0" : bean.getGoods_total()));
                        textviewAllPrice.setText("￥" + (bean.getGoods_total() == null ? "0" : bean.getGoods_total()));
                        imageLoader.displayImage(bean.getGoods_image_url(), goodsPicID, options, animateFirstListener);

                        //判断是否显示预存款
                        if (Available_predeposit != null && !Available_predeposit.equals("null") && !Available_predeposit.equals("") && !Available_predeposit.equals("0") && !Available_predeposit.equals("0.00")) {
                            showAvailablePredeposit = true;
                            availablePredepositID.setVisibility(View.VISIBLE);
                        } else {
                            showAvailablePredeposit = false;
                            availablePredepositID.setVisibility(View.GONE);
                        }

                        //判断是否显示充值卡
                        if (Available_Rcb_pay != null && !Available_Rcb_pay.equals("null") && !Available_Rcb_pay.equals("") && !Available_Rcb_pay.equals("0") && !Available_Rcb_pay.equals("0.00")) {
                            showAvailableRCBalance = true;
                            availableRCBalanceID.setVisibility(View.VISIBLE);
                        } else {
                            showAvailableRCBalance = false;
                            availableRCBalanceID.setVisibility(View.GONE);
                        }

                        if (showAvailablePredeposit || showAvailableRCBalance) {
                            predepositLayoutID.setVisibility(View.VISIBLE);
                        } else {
                            predepositLayoutID.setVisibility(View.GONE);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {

                    try {
                        JSONObject obj = new JSONObject(json);
                        String error = obj.getString("error");
                        if (error != null) {
                            Toast.makeText(VBuyStep1Activity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 购买第二布 提交订单
     *
     * @param key当前登录令牌
     * @param goods_id    商品编号
     * @param quantity    购买数量
     * @param buyer_phone 接收手机
     * @param pd_pay      是否使用预存款 1-是 0-否
     * @param password    支付密码(可以提前使用“验证支付密码”接口进行验证)
     */
    public void sendBuyStep2Data(String password) {
        String url = Constants.URL_MEMBER_VR_BUY3;

        String buyer_phone = editPhoneID.getText().toString();

        if (buyer_phone == null || buyer_phone.equals("") || buyer_phone.equals("null")) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("goods_id", cart_id);
        params.put("quantity", goodscount);
        params.put("buyer_phone", buyer_phone);
        params.put("pd_pay", if_pd_pay);
        params.put("rcb_pay", if_rcb_pay);
        params.put("password", password);

        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    startActivity(new Intent(VBuyStep1Activity.this, VirtualListActivity.class));
                    finish();
                } else {
                    try {
                        JSONObject obj = new JSONObject(json);
                        String error = obj.getString("error");
                        if (error != null) {
                            Toast.makeText(VBuyStep1Activity.this, error, Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 验证支付密码
     *
     * @param key  登录返回标识
     * @param 支付密码
     */
    public void CheackPassword(final String password) {
        String url = Constants.URL_CHECK_PASSWORD;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("password", password);
        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    if (json.equals("1")) {
                        sendBuyStep2Data(password);
                    }

                } else {
                    try {
                        JSONObject obj = new JSONObject(json);
                        String error = obj.getString("error");
                        if (error != null) {
                            Toast.makeText(VBuyStep1Activity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 更新价格UI
     */
    public void upPriceUIData() {

        //显示折扣价格
        textVoucher.setText("-￥" + goods_voucher);

        //显示运费
        textViewGoodsFreight.setText(" +￥" + goods_freight);

        //显示商品总价
        textViewGoodsTotal.setText(" ￥" + goods_total);

        //显示总价
        textviewAllPrice.setText("￥" + (goods_total + goods_freight - goods_voucher));
    }

    class MyifshowOnpayRadioButtonClickListener implements View.OnClickListener {
        public void onClick(View v) {
            RadioButton btn = (RadioButton) v;
            switch (btn.getId()) {
                case R.id.ifshowOnpayID:

                    if (showAvailablePredeposit || showAvailableRCBalance) {
                        predepositLayoutID.setVisibility(View.VISIBLE);
                    }

                    pay_name = "online";//online(线上付款) offline(货到付款)
                    break;
                case R.id.ifshowOffpayID:

                    predepositLayoutID.setVisibility(View.GONE);

                    pay_name = "offline";//online(线上付款) offline(货到付款)

                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageBack:

                finish();

                break;

            case R.id.commitID:

                //判断是否使用预存款或者充值卡如果使用验证密码
                if (if_pd_pay.equals("1") || if_rcb_pay.equals("1")) {

                    String password = editPasswordID.getText().toString();

                    if (password != null && !password.equals("") && !password.equals("null")) {
                        CheackPassword(password);
                    } else {
                        Toast.makeText(VBuyStep1Activity.this, "支付密码不能为空", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    sendBuyStep2Data("");
                }

                break;

            default:
                break;
        }
    }
}
