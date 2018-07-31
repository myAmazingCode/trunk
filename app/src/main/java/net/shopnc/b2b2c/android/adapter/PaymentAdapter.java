package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.alipay.sdk.pay.PayDemoActivity;
import com.google.gson.reflect.TypeToken;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.PaymentBean;
import net.shopnc.b2b2c.android.bean.PaymentListItem;
import net.shopnc.b2b2c.android.bean.WXPayReq;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.HashMap;

/**
 * @Description 交易方式选择
 * @Author qyf
 *
 * Created 2016/5/17 16:16.
 */
public class PaymentAdapter extends CommonAdapter<PaymentListItem> {
    private PaymentBean paymentBean;
    private MyShopApplication application;
    private ImageButton btnSelected;
    private EditText etPredepositPsw;
    private AlertDialog dialog;

    public PaymentAdapter(Context context, MyShopApplication application) {
        super(context, R.layout.order_list_payment_list_item);
        this.application = application;
    }

    public void setPaymentBean(PaymentBean paymentBean) {
        this.paymentBean = paymentBean;
        mDatas = paymentBean.getPaymentList();
    }

    public void setBtnSelected(ImageButton btnSelected) {
        this.btnSelected = btnSelected;
    }

    public void setEtPredepositPsw(EditText etPredepositPsw) {
        this.etPredepositPsw = etPredepositPsw;
    }

    public void setDialog(AlertDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void convert(ViewHolder holder, final PaymentListItem paymentListItem) {
        if (paymentListItem.getPaymentCode().equals("alipay")) {// "支付宝"
            holder.setImage(R.id.ivImg, R.drawable.zhifubao_appicon);
            holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    requestAlipayUrl();
                    dialog.dismiss();
                }
            });
        } else if (paymentListItem.getPaymentCode().equals("wxpay")) {  //微信
            holder.setImage(R.id.ivImg, R.drawable.sns_weixin_icon);
            holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    requestWXpayUrl();
                    dialog.dismiss();
                }
            });
        }
        holder.setText(R.id.tvName, paymentListItem.getPaymentName());

    }

    private void requestWXpayUrl() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("payId", paymentBean.getPayId() + "");
        if (null != btnSelected && btnSelected.isSelected()) {
            params.put("predepositPay", "1");
            if (Common.isEmpty(Common.getText(etPredepositPsw))) {
                TToast.showShort(mContext, "请填写预存款支付密码");
                return;
            } else {
                params.put("payPwd", Common.getText(etPredepositPsw));
            }
        } else {
            params.put("predepositPay", "0");
        }

        OkHttpUtil.postAsyn(mContext,ConstantUrl.URL_PAY_WXPAY, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                if (JsonUtil.toInteger(data, "isPayed") == 1) {
                    TToast.showShort(mContext, "支付成功");
                    EventBus.getDefault().post(1);//刷新显示
                } else {
                    WXPayReq wxPayReq=JsonUtil.toBean(data, "payParam", new TypeToken<WXPayReq>(){}.getType());

                    IWXAPI api = WXAPIFactory.createWXAPI(mContext, wxPayReq.getAppid());
                    PayReq req = new PayReq();
                    req.appId=wxPayReq.getAppid();
                    req.partnerId=wxPayReq.getPartnerid();
                    req.prepayId=wxPayReq.getPrepayid();
                    req.nonceStr=wxPayReq.getNoncestr();
                    req.timeStamp=wxPayReq.getTimestamp();
                    req.packageValue=JsonUtil.toString(data, "payParam", "package");
                    req.sign=wxPayReq.getSign();
                    req.extData = "app data"; // optional
                    TToast.showShort(mContext, "正常调起微信支付");
                    api.sendReq(req);
                }

            }
        }, params);
    }

    private void requestAlipayUrl() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("payId", paymentBean.getPayId() + "");
        if (null != btnSelected && btnSelected.isSelected()) {
            params.put("predepositPay", "1");
            if (Common.isEmpty(Common.getText(etPredepositPsw))) {
                TToast.showShort(mContext, "请填写预存款支付密码");
                return;
            } else {
                params.put("payPwd", Common.getText(etPredepositPsw));
            }
        } else {
            params.put("predepositPay", "0");
        }


        OkHttpUtil.postAsyn(mContext,ConstantUrl.URL_PAY_ALIBABA, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                if (JsonUtil.toInteger(data, "isPayed") == 1) {
                    TToast.showShort(mContext, "支付成功");
                    EventBus.getDefault().post(1);
                } else {
                    PayDemoActivity payDemoActivity = new PayDemoActivity(mContext, JsonUtil.toString(data, "payParamString"));
                    payDemoActivity.doPay();
                }
            }
        }, params);

    }

}
