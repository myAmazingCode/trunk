package net.shopnc.b2b2c.android.custom.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.PaymentBean;
import net.shopnc.b2b2c.android.bean.PaymentListItem;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.ui.order.OrderListActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description XXX
 * @Author qyf
 * <p>
 * Created 2016/8/24 15:53.
 */
public class PayChoosenDialog extends Dialog {
    @Bind(R.id.tv_onlineTotal)
    TextView tvOnlineTotal;
    @Bind(R.id.ib_usePDpy)
    ImageView ibUsePDpy;
    @Bind(R.id.tv_usePDpy)
    TextView tvUsePDpy;
    @Bind(R.id.llPwd)
    LinearLayout llPwd;
    @Bind(R.id.llPredeposit)
    LinearLayout llPredeposit;

    @Bind(R.id.etPredepositPsw)
    EditText etPredepositPsw;
    @Bind(R.id.ll_yck)
    LinearLayout llYck;
    @Bind(R.id.llPayLater)
    LinearLayout llPayLater;

    @Bind(R.id.ivWechatPay)
    ImageView ivWechatPay;
    @Bind(R.id.ivAlipayPay)
    ImageView ivAlipayPay;

    @Bind(R.id.tv_toPay)
    TextView tvToPay;

    private Context context;
    private PaymentBean paymentBean;
    private PayChoosen payChoosen;
    private boolean isPredepositPay;
    private boolean showPaylater;//是否显示稍后支付

    public PayChoosenDialog(Context context) {
        super(context);
    }

    public PayChoosenDialog(Context context, PaymentBean paymentBean) {
        super(context, R.style.CommonDialog);
        this.context = context;
        this.paymentBean = paymentBean;
    }

    public void setPayChoosen(PayChoosen payChoosen) {
        this.payChoosen = payChoosen;
    }

    /**
     * 是否开启稍后支付
     *
     * @param showPaylater
     */
    public void setShowPaylater(boolean showPaylater) {
        this.showPaylater = showPaylater;
    }

    public boolean isShowPaylater() {
        return showPaylater;
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_social_pay);
        ButterKnife.bind(this);
        //点击外部不可关闭
        setCanceledOnTouchOutside(false);
        getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);
        initView();
        llPayLater.setVisibility(showPaylater ? View.VISIBLE : View.GONE);
    }

    private void initView() {
        tvOnlineTotal.setText(Html.fromHtml(ShopHelper.getPriceString(paymentBean.getPayAmount())));
        tvUsePDpy.setText(ShopHelper.getPriceString(paymentBean.getPredepositAmount()));
        llYck.setVisibility(paymentBean.getAllowPredeposit() == 1 ? View.VISIBLE : View.GONE);//允许使用预存款
        List<PaymentListItem> itemList = paymentBean.getPaymentList();
        for (PaymentListItem item : itemList) {
            if ("wxpay".equals(item.getPaymentCode())) {
                ivWechatPay.setVisibility(View.VISIBLE);
                //默认选中微信支付
                ivWechatPay.setSelected(true);
            }
            if ("alipay".equals(item.getPaymentCode())) {
                ivAlipayPay.setVisibility(View.VISIBLE);
                if (!ivWechatPay.isSelected()) {
                    //若微信支付未选中，则选中支付宝
                    ivAlipayPay.setSelected(true);
                }
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        //当支付弹窗弹出时，按下返回键，跳转订单列表页（稍后支付）
        if (keyCode == KeyEvent.KEYCODE_BACK && showPaylater) {
            ButterKnife.unbind(this);
            Intent intent = new Intent(context, OrderListActivity.class);
            intent.putExtra(OrderListActivity.STATE, "new");
            context.startActivity(intent);
            ((Activity) context).finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.llPredeposit, R.id.ivWechatPay, R.id.ivAlipayPay, R.id.tv_toPay, R.id.tvPayLater})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvPayLater:
                //稍后支付
                if (context instanceof Activity) {
                    Intent intent = new Intent(context, OrderListActivity.class);
                    intent.putExtra(OrderListActivity.STATE, "new");
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }

                break;
            case R.id.llPredeposit:
                ibUsePDpy.setSelected(!ibUsePDpy.isSelected());
                isPredepositPay = ibUsePDpy.isSelected();
                llPwd.setVisibility(ibUsePDpy.isSelected() ? View.VISIBLE : View.GONE);
                break;
            case R.id.ivWechatPay:
                ivWechatPay.setSelected(true);
                break;
            case R.id.ivAlipayPay:
                ivAlipayPay.setSelected(false);
                break;
            case R.id.tv_toPay:
                if (isPredepositPay && Common.getText(etPredepositPsw).equals("")) {
                    TToast.showShort(context, "请输入预存款支付密码");
                    return;
                }
                //使用预存款支付时，默认选择支付宝
                if (isPredepositPay && paymentBean.getPredepositAmount().compareTo(paymentBean.getPayAmount()) != -1) { //预存款大于支付金额
                    //随便选择可用的方式进行支付
//                    if (viewPayVx.getVisibility() == View.VISIBLE) {
//                        payChoosen.requestWXpayUrl(isPredepositPay, Common.getText(etPredepositPsw));
//                    } else if (viewPayZhifubao.getVisibility() == View.VISIBLE) {
                    payChoosen.requestAlipayUrl(isPredepositPay, Common.getText(etPredepositPsw));
//                    }
                } else {
                    if (ivWechatPay.isSelected()) {
                        payChoosen.requestWXpayUrl(isPredepositPay, Common.getText(etPredepositPsw));
                    } else if (ivAlipayPay.isSelected()) {
                        payChoosen.requestAlipayUrl(isPredepositPay, Common.getText(etPredepositPsw));
                    } else {
                        TToast.showShort(context, "请选择一种支付方式");
                        return;
                    }
                }
//                dismiss();
                break;
        }
    }

    public interface PayChoosen {
        void requestWXpayUrl(boolean isPredepositPay, String psw);

        void requestAlipayUrl(boolean isPredepositPay, String psw);
    }
}
