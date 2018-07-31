package net.shopnc.b2b2c.android.ui.order;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.RefundDetailVo;
import net.shopnc.b2b2c.android.bean.RefundItemVo;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.custom.dialog.ClickBigImageDialog;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;

public class OrderRefundAndReturnDetailsActivity extends BaseActivity {

    @Bind(R.id.tvRefundSn)
    TextView tvRefundSn;
    @Bind(R.id.tvReasonInfo)
    TextView tvReasonInfo;
    @Bind(R.id.tvMoney)
    TextView tvMoney;
    @Bind(R.id.tvBuyerMessage)
    TextView tvBuyerMessage;
    @Bind(R.id.fblImages)
    FlexboxLayout fblImages;
    @Bind(R.id.tvImageNone)
    TextView tvImageNone;
    @Bind(R.id.tvSellerState)
    TextView tvSellerState;
    @Bind(R.id.tvSellerMessage)
    TextView tvSellerMessage;
    @Bind(R.id.tvRefundState)
    TextView tvRefundState;
    @Bind(R.id.tvAdminMessage)
    TextView tvAdminMessage;
    @Bind(R.id.tvRefundDetails)
    TextView tvRefundDetails;
    @Bind(R.id.tvPaymentName)
    TextView tvPaymentName;
    @Bind(R.id.tvPaymentAmount)
    TextView tvPaymentAmount;
    @Bind(R.id.tvPdAmount)
    TextView tvPdAmount;
    @Bind(R.id.llRefundDetails)
    LinearLayout llRefundDetails;
    @Bind(R.id.tvRequestName)
    TextView tvRequestName;
    @Bind(R.id.tvSnName)
    TextView tvSnName;
    @Bind(R.id.tvReasonName)
    TextView tvReasonName;
    @Bind(R.id.tvMessageName)
    TextView tvMessageName;

    private int refundId;
    private RefundItemVo refundItemVo;
    private String moneyRmb;
    private String uploadRoot;
    private List<String> imageList;
    private RefundDetailVo refundDetailVo;
    private String flag;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refundId = getIntent().getIntExtra("refundId", 0);
        flag = getIntent().getStringExtra("flag");
        setViewName();
        moneyRmb = context.getResources().getString(R.string.money_rmb);
        requestRefundInfo();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_order_refund_money_details);
    }

    private void setViewName() {
        if (flag.equals("refund")) {
            setCommonHeader("退款详情");
            tvRequestName.setText("我的退款申请");
            tvSnName.setText("退款编号");
            tvReasonName.setText("退款原因");
            tvMessageName.setText("退款说明");
            url = ConstantUrl.URL_ORDER_REFUND_INFO;
        } else {  //return
            setCommonHeader("退货详情");
            tvRequestName.setText("我的退货申请");
            tvSnName.setText("退货编号");
            tvReasonName.setText("退货原因");
            tvMessageName.setText("退货说明");
            url = ConstantUrl.URL_ORDER_RETURN_INFO;
        }
    }

    private void requestRefundInfo() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("refundId", refundId + "");

        OkHttpUtil.postAsyn(this,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                refundItemVo = JsonUtil.toBean(data, "refundItemVo", new TypeToken<RefundItemVo>() {
                }.getType());
                uploadRoot = JsonUtil.toString(data, "uploadRoot");
                refundDetailVo = JsonUtil.toBean(data, "refundDetailVo", new TypeToken<RefundDetailVo>() {
                }.getType());
                bindData();
            }
        }, params);
    }

    private void bindData() {
        tvRefundSn.setText(refundItemVo.getRefundSn() + "");
        tvReasonInfo.setText(refundItemVo.getReasonInfo());
        tvMoney.setText(moneyRmb + ShopHelper.getPriceString(refundItemVo.getRefundAmount()));
        tvBuyerMessage.setText(refundItemVo.getBuyerMessage());
        tvSellerState.setText(refundItemVo.getSellerStateText());
        tvSellerMessage.setText(refundItemVo.getSellerMessage());
        tvRefundState.setText(refundItemVo.getRefundStateText());
        tvAdminMessage.setText(refundItemVo.getAdminMessage());
        bindImages();
        if (refundDetailVo != null) {
            tvRefundDetails.setVisibility(View.VISIBLE);
            llRefundDetails.setVisibility(View.VISIBLE);
            tvPaymentName.setText(refundDetailVo.getPaymentName());
            tvPaymentAmount.setText(moneyRmb + ShopHelper.getPriceString(refundDetailVo.getPayAmount()));
            tvPdAmount.setText(moneyRmb + ShopHelper.getPriceString(refundDetailVo.getPdAmount()));
        }
    }

    private void bindImages() {
        if (Common.isNotEmpty(refundItemVo.getPicJson())) {
            tvImageNone.setVisibility(View.GONE);
            fblImages.setVisibility(View.VISIBLE);
            String[] images = refundItemVo.getPicJson().split(",");
//        imageList=Arrays.asList(images);
            imageList = new ArrayList<>();
            for (int i = 0; i < images.length; i++) {
                imageList.add(uploadRoot + images[i]);
            }
            for (final String s : imageList) {
                AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.view_image_simple);
                addViewHolder.setImage(R.id.ivImg, s);
                addViewHolder.getCustomView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击查看大图并设置当前位置
                        ClickBigImageDialog dialog = new ClickBigImageDialog(context, imageList, imageList.indexOf(s));
                        dialog.show();
                    }
                });
                fblImages.addView(addViewHolder.getCustomView());
            }
        }else {
            tvImageNone.setVisibility(View.VISIBLE);
            fblImages.setVisibility(View.GONE);
        }
    }

}
