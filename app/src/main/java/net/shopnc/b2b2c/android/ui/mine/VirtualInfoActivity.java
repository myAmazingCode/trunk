package net.shopnc.b2b2c.android.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.VirtualInfo;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VirtualInfoActivity extends BaseActivity {
    private MyShopApplication myApplication;

    private String orderId;

    private TextView tvStateDesc;
    private TextView tvBuyerPhone;
    private Button btnResend;
    private TextView tvBuyerMsg;
    private TextView tvIndate;
    private LinearLayout llCodeList;
    private RelativeLayout rlOrderItem;
    private ImageView ivGoodsImage;
    private TextView tvGoodsName;
    private TextView tvGoodsPrice;
    private TextView tvGoodsNum;
    private TextView tvOrderSn;
    private TextView tvAddTime;
    private TextView tvPayTime;
    private RelativeLayout rlStoreName;

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_info);
        MyExceptionHandler.getInstance().setContext(this);
        myApplication = (MyShopApplication) VirtualInfoActivity.this.getApplication();

        orderId = getIntent().getStringExtra("order_id");

        tvStateDesc = (TextView) findViewById(R.id.tvStateDesc);
        tvBuyerPhone = (TextView) findViewById(R.id.tvBuyerPhone);
        btnResend = (Button) findViewById(R.id.btnResend);
        tvBuyerMsg = (TextView) findViewById(R.id.tvBuyerMsg);
        tvIndate = (TextView) findViewById(R.id.tvIndate);
        llCodeList = (LinearLayout) findViewById(R.id.llCodeList);
        rlOrderItem = (RelativeLayout) findViewById(R.id.rlOrderItem);
        ivGoodsImage = (ImageView) findViewById(R.id.ivGoodsImage);
        tvGoodsName = (TextView) findViewById(R.id.tvGoodsName);
        tvGoodsPrice = (TextView) findViewById(R.id.tvGoodsPrice);
        tvGoodsNum = (TextView) findViewById(R.id.tvGoodsNum);
        tvOrderSn = (TextView) findViewById(R.id.tvOrderSn);
        tvAddTime = (TextView) findViewById(R.id.tvAddTime);
        tvPayTime = (TextView) findViewById(R.id.tvPayTime);
        rlStoreName = (RelativeLayout) findViewById(R.id.rlStoreName);

        setCommonHeader("订单详情");

        loadOrderDetail();
    }

    private void loadOrderDetail() {
        String url = Constants.URL_MEMBER_VR_ORDER_INFO + "&order_id=" + orderId + "&key=" + myApplication.getLoginKey();
        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
                    @Override
                    public void dataLoaded(ResponseData data) {
                        String json = data.getJson();
                        if (data.getCode() == HttpStatus.SC_OK) {
                            try {
                                JSONObject obj = new JSONObject(json);
                                String objString = obj.getString("order_info");
                                final VirtualInfo virtualInfo = VirtualInfo.newInstanceInfo(objString);
                                tvStateDesc.setText(virtualInfo.getStateDesc());
                                tvBuyerPhone.setText(virtualInfo.getBuyerPhone());
                                if (virtualInfo.getIfResend().equals("true")) {
                                    btnResend.setVisibility(View.VISIBLE);
                                } else {
                                    btnResend.setVisibility(View.GONE);
                                }
                                tvBuyerMsg.setText(virtualInfo.getBuyerMsg().equals("null") ? "" : virtualInfo.getBuyerMsg());
                                tvIndate.setText("有效期至" + virtualInfo.getVrIndate());
                                rlOrderItem.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(VirtualInfoActivity.this, GoodsDetailsActivity.class);
                                        intent.putExtra("goods_id", virtualInfo.getGoodsId());
                                        startActivity(intent);
                                    }
                                });
                                imageLoader.displayImage(virtualInfo.getGoodsImageUrl(), ivGoodsImage, options, animateFirstListener);
                                tvGoodsName.setText(virtualInfo.getGoodsName());
                                tvGoodsPrice.setText(virtualInfo.getGoodsPrice());
                                tvGoodsNum.setText("x" + virtualInfo.getGoodsNum());
                                showCodeList(virtualInfo.getCodeList());
                                tvOrderSn.setText("订单编号：" + virtualInfo.getOrderSn());
                                tvAddTime.setText("创建时间：" + virtualInfo.getAddTime());
                                if (!virtualInfo.getPaymentTime().equals("")) {
                                    tvPayTime.setText("付款时间：" + virtualInfo.getPaymentTime());
                                    tvPayTime.setVisibility(View.VISIBLE);
                                } else {
                                    tvPayTime.setVisibility(View.GONE);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ShopHelper.showApiError(VirtualInfoActivity.this, json);
                        }
                    }
                }
        );
    }

    /**
     * 设置虚拟兑换码列表
     *
     * @param codeListString
     * @throws JSONException
     */
    private void showCodeList(String codeListString) throws JSONException {
        JSONArray codeArray = new JSONArray(codeListString);
        int size = codeArray.length();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                JSONObject codeInfo = codeArray.getJSONObject(i);
                String state = codeInfo.optString("vr_state");
                String code = codeInfo.optString("vr_code");
                View codeItem = getLayoutInflater().inflate(R.layout.view_vr_code_item, null);
                TextView tvCodeState = codeItem.findViewById(R.id.tvCodeState);
                TextView tvCode = codeItem.findViewById(R.id.tvCode);
                tvCode.setText(code);
                if (state.equals("0")) {
                    //有效
                    tvCode.setTextColor(getResources().getColor(R.color.nc_green));
                    tvCodeState.setText("有效");
                    tvCodeState.setBackgroundColor(getResources().getColor(R.color.nc_green));
                } else {
                    //失效
                    tvCode.setTextColor(getResources().getColor(R.color.nc_text_hint));
                    tvCodeState.setText("失效");
                    tvCodeState.setBackgroundColor(getResources().getColor(R.color.nc_text_hint));
                }
                llCodeList.addView(codeItem);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_virtual_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
