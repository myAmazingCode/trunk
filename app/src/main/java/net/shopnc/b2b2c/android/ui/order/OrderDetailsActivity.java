package net.shopnc.b2b2c.android.ui.order;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.GoodGift;
import net.shopnc.b2b2c.android.bean.OrdersGoodsVo;
import net.shopnc.b2b2c.android.bean.OrdersVo;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.constants.OrdersType;
import net.shopnc.b2b2c.android.custom.dialog.NCDialog;
import net.shopnc.b2b2c.android.custom.dialog.NCDialogConfirm;
import net.shopnc.b2b2c.android.ui.group.GroupShareActivity;
import net.shopnc.b2b2c.android.ui.im.IMDetailsActivity;
import net.shopnc.b2b2c.android.ui.store.StoreInfoFragmentActivity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class OrderDetailsActivity extends BaseActivity {

    public static String ORDERSID = "ordersId";

    @Bind(R.id.tvStateDesc)
    TextView tvStateDesc;
    @Bind(R.id.tvTips)
    TextView tvTips;
    //    @Bind(R.id.tvShip)
//    TextView tvShip;
    @Bind(R.id.tvReciverPhone)
    TextView tvReciverPhone;
    @Bind(R.id.tvReciverAddress)
    TextView tvReciverAddress;
    @Bind(R.id.tvInvoice)
    TextView tvInvoice;
    @Bind(R.id.layoutInvoice)
    LinearLayout layoutInvoice;
    @Bind(R.id.tvPay)
    TextView tvPay;
    @Bind(R.id.tvFuKuanStyle)
    TextView tvFuKuanStyle;
    @Bind(R.id.tvOrderStoreName)
    TextView tvOrderStoreName;
    @Bind(R.id.llSku)
    LinearLayout llSku;
    @Bind(R.id.tvDiscount)
    TextView tvDiscount;
    @Bind(R.id.tvDiscountName)
    TextView tvDiscountName;
    @Bind(R.id.tvShippingFee)
    TextView tvShippingFee;
    @Bind(R.id.tvDelivery)
    TextView tvDelivery;
    @Bind(R.id.tvOrderAmount)
    TextView tvOrderAmount;
    @Bind(R.id.tvOrderSn)
    TextView tvOrderSn;
    @Bind(R.id.tvAddTime)
    TextView tvAddTime;
    @Bind(R.id.tvPaymentTime)
    TextView tvPaymentTime;
    @Bind(R.id.tvShippingTime)
    TextView tvShippingTime;
    @Bind(R.id.tvFinnshedTime)
    TextView tvFinnshedTime;
    @Bind(R.id.tvMsgInfo)
    TextView tvMsgInfo;
    @Bind(R.id.layoutMsg)
    RelativeLayout layoutMsg;
    @Bind(R.id.tvReciverName)
    TextView tvReciverName;
    @Bind(R.id.tvMsg)
    TextView tvMsg;
    @Bind(R.id.tvInvoiceName)
    TextView tvInvoiceName;
    @Bind(R.id.btnOrderCancel)
    TextView btnOrderCancel;
    @Bind(R.id.btnOrderAllRefund)
    TextView btnOrderAllRefund;
    @Bind(R.id.btnOrderShipping)
    TextView btnOrderShipping;
    @Bind(R.id.btnGroupDetail)
    TextView btnGroupDetail;
    @Bind(R.id.btnOrderSure)
    TextView btnOrderSure;
    @Bind(R.id.btnOrderEva)
    TextView btnOrderEva;
    @Bind(R.id.btnOrderEvaAg)
    TextView btnOrderEvaAg;
    @Bind(R.id.btnOrderGoodRefund)
    TextView btnOrderGoodRefund;
    @Bind(R.id.llGift)
    LinearLayout llGift;
    @Bind(R.id.llGiftLayout)
    LinearLayout llGiftLayout;
    @Bind(R.id.tvPayStyle)
    TextView tvPayStyle;
    @Bind(R.id.tvPayName)
    TextView tvPayName;
    @Bind(R.id.tvPayAmount)
    TextView tvPayAmount;
    @Bind(R.id.tvPayStyle2)
    TextView tvPayStyle2;
    @Bind(R.id.tvPayName2)
    TextView tvPayName2;
    @Bind(R.id.tvPayAmount2)
    TextView tvPayAmount2;
    @Bind(R.id.rlPromotionPreSell2)
    RelativeLayout rlPromotionPreSell2;
    @Bind(R.id.rlPromotionPreSell)
    RelativeLayout rlPromotionPreSell;
    @Bind(R.id.tvPredepoitAmount)
    TextView tvPredepoitAmount;
    @Bind(R.id.tvPayWarning)
    TextView tvPayWarning;
    @Bind(R.id.rlPredepoitAmount)
    RelativeLayout rlPredepoitAmount;
//    @Bind(R.id.layoutShip)
//    RelativeLayout layoutShip;

    private int ordersId;
    private OrdersVo ordersVo;
    private String ordersTips;

    private String moneyRmb;
    private String goodNumFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("订单详情");
        ordersId = getIntent().getIntExtra(ORDERSID, 0);
        LogHelper.d("OrderDetails ordersId:" + ordersId);
        moneyRmb = context.getResources().getString(R.string.money_rmb);
        goodNumFlag = context.getResources().getString(R.string.good_num);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestOrdersDetails();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_order_details);
    }

    private void requestOrdersDetails() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("ordersId", ordersId + "");

        OkHttpUtil.postAsyn(this, ConstantUrl.URL_ORDER_DETAILS, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                LogHelper.d(data);
                ordersVo = JsonUtil.toBean(data, "ordersVo", new TypeToken<OrdersVo>() {
                }.getType());

                //获得订单未支付状态提示
                ordersTips = JsonUtil.toString(data, "ordersTips");
                bindOrderDetails();
            }
        }, params);
    }

    private void bindOrderDetails() {
//        //交易状态
        tvStateDesc.setText(ordersVo.getOrdersStateName());
        if (ordersVo.getOrdersState() == 10) {
//            tvTips.setVisibility(View.VISIBLE);
            tvTips.setText(ordersTips);
        }
//
//
//        if (ordersVo.getShowShipSearch()==1){
//            //TODO  最新物流信息显示
//            requestLastDeliveryInfo();
//            tvShip.setText("点击查看物流信息");
//            layoutShip.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent=new Intent(context,OrderDeliveryInfoActivity.class);
//                    intent.putExtra(OrderDeliveryInfoActivity.SHIPSN,ordersVo.getShipSn());
//                    intent.putExtra(OrderDeliveryInfoActivity.SHIPCODE,ordersVo.getShipCode());
//                    startActivity(intent);
//                }
//            });
//        }


        //收货信息
        tvReciverName.setText(ordersVo.getReceiverName());
        tvReciverPhone.setText(ordersVo.getReceiverPhone());
        tvReciverAddress.setText(/*"收货地址：" + */ordersVo.getReceiverAreaInfo() + ordersVo.getReceiverAddress());

        //留言信息
        if (Common.isEmpty(ordersVo.getReceiverMessage())) {
            layoutMsg.setVisibility(View.GONE);
        } else {
            tvMsgInfo.setText(ordersVo.getReceiverMessage());
        }

        //发票信息
//        if (Common.isEmpty(ordersVo.getInvoiceInfo())) {
//            layoutInvoice.setVisibility(View.GONE);
//        } else {
//            tvInvoice.setText("类型：" + ordersVo.getInvoiceInfo());
//        }

        List<String> invoiceList = ordersVo.getInvoiceList();
        if (invoiceList == null || invoiceList.size() == 0) {
            layoutInvoice.setVisibility(View.GONE);
        } else {
            String invoiceInfo = "";
            for (int i = 0; i < invoiceList.size(); i++) {
                invoiceInfo += invoiceList.get(i);
            }
            tvInvoice.setText(invoiceInfo);
        }

        //付款方式
        tvFuKuanStyle.setText(ordersVo.getPaymentName());

        //店铺及sku信息
        tvOrderStoreName.setText(" " + ordersVo.getStoreName() + " ");
        bindStoreItem();
        bindGiftView();

        //定金预售信息显示
        if (ordersVo.getOrdersBookVoList().size() > 0) {
            rlPromotionPreSell.setVisibility(View.VISIBLE);
            if (ordersVo.getOrdersBookVoList().size() == 2) {
                tvPayStyle.setText("阶段1：" + ordersVo.getOrdersBookVoList().get(0).getBookState());
                tvPayName.setText("定金");
                if (Common.isEmpty(ordersVo.getOrdersBookVoList().get(0).getPaymentTime())) {
                    tvPayAmount.setText(Html.fromHtml(moneyRmb + "<font color=\"#ED5968\">" + ordersVo.getOrdersBookVoList().get(0).getBookAmount() + "</font>"));
                } else {
                    tvPayAmount.setText(Html.fromHtml(moneyRmb + "<font color=\"#555555\">" + ordersVo.getOrdersBookVoList().get(0).getBookAmount() + "</font>"));
                }
                rlPromotionPreSell2.setVisibility(View.VISIBLE);
                tvPayStyle2.setText("阶段2：" + ordersVo.getOrdersBookVoList().get(1).getBookState());
                tvPayName2.setText("尾款+运费");
                if (Common.isEmpty(ordersVo.getOrdersBookVoList().get(1).getPaymentTime())) {
                    tvPayAmount2.setText(Html.fromHtml(moneyRmb + "<font color=\"#ED5968\">" + ordersVo.getOrdersBookVoList().get(1).getBookAmount() + "</font>"));
                } else {
                    tvPayAmount2.setText(Html.fromHtml(moneyRmb + "<font color=\"#555555\">" + ordersVo.getOrdersBookVoList().get(1).getBookAmount() + "</font>"));
                }
            } else {
                tvPayStyle.setText("全款支付");
                tvPayName.setText("定金+尾款+运费");
                tvPayAmount.setText(Html.fromHtml(moneyRmb + "<font color=\"#ED5968\">" + ordersVo.getOrdersBookVoList().get(0).getBookAmount() + "</font>"));
            }
        }

        //优惠信息的显示
        String discount = "";
        if (ordersVo.getConformPrice().compareTo(new BigDecimal(0)) == 1) {
            discount = discount + "满" + ShopHelper.getPriceString(ordersVo.getLimitAmount()) + "减" + ShopHelper.getPriceString(ordersVo.getConformPrice()) + "元" + (ordersVo.getIsFreeFreight() == 1 ? "包邮" : "") + (ordersVo.getTemplateId() > 0 ? "送券" : "");
        }
        if (ordersVo.getVoucherPrice().compareTo(new BigDecimal(0)) == 1) {
            if (Common.isNotEmpty(discount)) {
                discount = discount + "<br></br>";
            }
            discount = discount + "使用了" + ordersVo.getVoucherPrice() + "元优惠券";
        }
        if (ordersVo.getRedPackageAmount().compareTo(new BigDecimal(0)) == 1) {
            if (Common.isNotEmpty(discount)) {
                discount = discount + "<br></br>";
            }
            discount = discount + "使用了" + ordersVo.getRedPackageAmount() + "元红包";
        }
        if (discount.equals("")) {
            tvDiscountName.setVisibility(View.GONE);
            tvDiscount.setVisibility(View.GONE);
        } else {
            tvDiscount.setText(Html.fromHtml(discount));
        }

        //运费
        double price = ordersVo.getFreightAmount().doubleValue();
        if (price != 0) {
            tvShippingFee.setText(moneyRmb + ShopHelper.getPriceString(price));
        } else {
            //免运费时隐藏运费信息，在价格后显示包邮
            tvShippingFee.setVisibility(View.GONE);
            tvDelivery.setVisibility(View.GONE);
        }
        //订单总金额的显示
        if (ordersVo.getOrdersBookVoList().size() > 0) {
            tvOrderAmount.setText(Html.fromHtml("<font color=\"#555555\">" + moneyRmb + ShopHelper.getPriceString(ordersVo.getOrdersAmount()) + "</font>" + (price == 0 ? "(包邮)" : "")));
        } else {
            if (ordersVo.getOrdersState() != 0 && ordersVo.getPredepositAmount().compareTo(new BigDecimal(0)) == 1) {
                rlPredepoitAmount.setVisibility(View.VISIBLE);
                tvPredepoitAmount.setText(moneyRmb + ShopHelper.getPriceString(ordersVo.getPredepositAmount()));
            }
            tvOrderAmount.setText(Html.fromHtml("<font color=\"#ED5968\">" + moneyRmb + ShopHelper.getPriceString(ordersVo.getOrdersAmount()) + "</font>" + (price == 0 ? "(包邮)" : "")));
        }

        switchTimeByState(ordersVo.getOrdersState());
        setOrderDoVisible();
    }

    //设置编号时间等
    private void switchTimeByState(int orderState) {
        tvOrderSn.setText("订单编号：" + ordersVo.getOrdersSn());
        tvAddTime.setText("创建时间：" + ordersVo.getCreateTime());
        if (Common.isNotEmpty(ordersVo.getPaymentTime())) {
            tvPaymentTime.setVisibility(View.VISIBLE);
            tvPaymentTime.setText("付款时间：" + ordersVo.getPaymentTime());
        }
        if (Common.isNotEmpty(ordersVo.getSendTime())) {
            tvShippingTime.setVisibility(View.VISIBLE);
            tvShippingTime.setText("发货时间：" + ordersVo.getSendTime());
        }
        if (Common.isNotEmpty(ordersVo.getFinishTime())) {
            tvFinnshedTime.setVisibility(View.VISIBLE);//
            tvFinnshedTime.setText("完成时间：" + ordersVo.getFinishTime());
        }
    }

    private void bindStoreItem() {
        llSku.removeAllViews();
        for (final OrdersGoodsVo goodsVo : ordersVo.getOrdersGoodsVoList()) {
            AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.listview_order_spu_item);
            addViewHolder.setImage(R.id.ivGoodPic, goodsVo.getImageSrc())
                    .setText(R.id.tvGoodName, goodsVo.getGoodsName())
                    .setText(R.id.tvGoodsSPec, goodsVo.getGoodsFullSpecs())
                    .setText(R.id.tvGoodPrice, moneyRmb + ShopHelper.getPriceString(goodsVo.getGoodsPrice()))
                    .setText(R.id.tvGoodsNum, goodNumFlag + goodsVo.getBuyNum())
                    .setText(R.id.tvPromotionTitle, goodsVo.getPromotionTitle())
                    .setVisible(R.id.tvPromotionTitle, Common.isNotEmpty(goodsVo.getPromotionTitle()));
            addViewHolder.getCustomView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShopHelper.gotoGoodDetailsActivity(context, goodsVo.getCommonId());
                }
            });


            addViewHolder.setVisible(R.id.llOrderRefund, goodsVo.getShowRefund() == 1 || ordersVo.getShowMemberComplain() == 1);
            addViewHolder.setVisible(R.id.btnOrderMoney, goodsVo.getShowRefund() == 1);
            addViewHolder.setOnClickListener(R.id.btnOrderMoney, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, OrderRefundMoneyActivity.class);
                    intent.putExtra("ordersId", ordersId);
                    intent.putExtra("ordersGoodsId", goodsVo.getOrdersGoodsId());
                    context.startActivity(intent);
                }
            });
            addViewHolder.setVisible(R.id.btnOrderGood, goodsVo.getShowRefund() == 1);
            addViewHolder.setOnClickListener(R.id.btnOrderGood, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, OrderReturnGoodActivity.class);
                    intent.putExtra("ordersId", ordersId);
                    intent.putExtra("ordersGoodsId", goodsVo.getOrdersGoodsId());
                    context.startActivity(intent);
                }
            });

            addViewHolder.setVisible(R.id.btnOrderComplaint, ordersVo.getShowMemberComplain() == 1 && goodsVo.getComplainId() == 0);
            addViewHolder.setOnClickListener(R.id.btnOrderComplaint, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, OrderComplaintGoodActivity.class);
                    intent.putExtra("ordersId", ordersId);
                    intent.putExtra("ordersGoodsId", goodsVo.getOrdersGoodsId());
                    context.startActivity(intent);
                }
            });
            addViewHolder.setVisible(R.id.tvOrderComplaint, ordersVo.getShowMemberComplain() == 1 && goodsVo.getComplainId() > 0);

            llSku.addView(addViewHolder.getCustomView());
        }

    }

    private void bindGiftView() {
        if (ordersVo.getOrdersGiftVoList() != null && ordersVo.getOrdersGiftVoList().size() > 0) {
            llGiftLayout.setVisibility(View.VISIBLE);
            llGift.removeAllViews();
            for (GoodGift gift : ordersVo.getOrdersGiftVoList()) {
                AddViewHolder giftHolder = new AddViewHolder(context, R.layout.cart_gift_item);
                giftHolder.setText(R.id.tvGiftName, gift.getCartShowText())
                        .setText(R.id.tvGiftNum, "x" + gift.getGiftNum());
                llGift.addView(giftHolder.getCustomView());
            }
        } else {
            llGiftLayout.setVisibility(View.GONE);
        }
    }

    private void setOrderDoVisible() {
        if (ordersVo.getShowRefundWaiting() == 1) { //如果退款退货中显示、则其他都不显示
            btnOrderGoodRefund.setVisibility(View.VISIBLE);
            return;
        } else {
            btnOrderGoodRefund.setVisibility(View.GONE);
        }
        if (ordersVo.getShowMemberCancel() == 1) {
            btnOrderCancel.setVisibility(View.VISIBLE);
            tvPayWarning.setVisibility(View.VISIBLE);
        } else {
            btnOrderCancel.setVisibility(View.GONE);
            tvPayWarning.setVisibility(View.GONE);
        }
        if (ordersVo.getShowMemberRefundAll() == 1) {
            btnOrderAllRefund.setVisibility(View.VISIBLE);
        } else {
            btnOrderAllRefund.setVisibility(View.GONE);
        }
        if (ordersVo.getShowShipSearch() == 1) {
            btnOrderShipping.setVisibility(View.VISIBLE);
        } else {
            btnOrderShipping.setVisibility(View.GONE);
        }
        if (ordersVo.getOrdersType() == OrdersType.GROUPS && ordersVo.getGoId() > 0) {
            btnGroupDetail.setVisibility(View.VISIBLE);
        } else {
            btnGroupDetail.setVisibility(View.GONE);
        }
        if (ordersVo.getShowMemberReceive() == 1) {
            btnOrderSure.setVisibility(View.VISIBLE);
        } else {
            btnOrderSure.setVisibility(View.GONE);
        }
        if (ordersVo.getShowEvaluation() == 1) {
            btnOrderEva.setVisibility(View.VISIBLE);
        } else {
            btnOrderEva.setVisibility(View.GONE);
        }
        if (ordersVo.getShowEvaluationAppend() == 1) {
            btnOrderEvaAg.setVisibility(View.VISIBLE);
        } else {
            btnOrderEvaAg.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.tvOrderStoreName)
    public void gotoStore() {
        Intent i = new Intent(OrderDetailsActivity.this, StoreInfoFragmentActivity.class);
        i.putExtra("storeId", ordersVo.getStoreId());
        startActivity(i);
    }

    private void requestLastDeliveryInfo() {
        //TODO  最新物流信息显示

    }

    @OnClick({R.id.btnOrderCancel, R.id.btnOrderAllRefund, R.id.btnOrderShipping,
            R.id.btnOrderSure, R.id.btnOrderEva, R.id.btnOrderEvaAg, R.id.btnGroupDetail
            , R.id.textChatMe, R.id.textCallMe})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textChatMe:
                if (ShopHelper.isLogin(this)) {
                    Intent intent = new Intent(context, IMDetailsActivity.class);
                    intent.putExtra("sid", ordersVo.getStoreId());
                    ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    // 将文本内容放到系统剪贴板里。
                    int commonId = ordersVo.getOrdersGoodsVoList().get(0).getCommonId();
                    cm.setText(ConstantUrl.URL_WEB_GOODS + commonId);
                    intent.putExtra("gid", commonId);
                    intent.putExtra("flag", "details");
                    startActivity(intent);
                }
                break;
            case R.id.textCallMe:
                String storePhone = ordersVo.getStorePhone();
                if (!TextUtils.isEmpty(storePhone)) {
                    ShopHelper.call(this, storePhone);
                }
                break;
            case R.id.btnOrderCancel:
                NCDialog dialog = new NCDialog(context);
                dialog.setText1("确定取消订单？");
                NCDialogConfirm confirm = new NCDialogConfirm() {
                    @Override
                    public void onDialogConfirm() {
                        requestCancelOrder();
                    }
                };
                dialog.setOnDialogConfirm(confirm);
                dialog.showPopupWindow();
                break;
            case R.id.btnOrderAllRefund:
                Intent intentAll = new Intent(context, OrderRefundActivity.class);
                intentAll.putExtra("ordersId", ordersVo.getOrdersId());
                context.startActivity(intentAll);
                break;
            case R.id.btnOrderShipping:
                Intent intent = new Intent(context, OrderDeliveryInfoActivity.class);
                intent.putExtra(OrderDeliveryInfoActivity.SHIPSN, ordersVo.getShipSn());
                intent.putExtra(OrderDeliveryInfoActivity.SHIPCODE, ordersVo.getShipCode());
                context.startActivity(intent);
                break;
            case R.id.btnOrderSure:
                NCDialog conformDialog = new NCDialog(context);
                conformDialog.setText1("确定收到货了吗？");
                NCDialogConfirm dialogConfirm = new NCDialogConfirm() {
                    @Override
                    public void onDialogConfirm() {
                        requestSureOrder();
                    }
                };
                conformDialog.setOnDialogConfirm(dialogConfirm);
                conformDialog.showPopupWindow();
                break;
            case R.id.btnOrderEva:
                Intent intentEva = new Intent(context, OrderEvaluateActivity.class);
                intentEva.putExtra("ordersId", ordersVo.getOrdersId() + "");
                startActivity(intentEva);
                break;
            case R.id.btnOrderEvaAg:
                Intent intentEvaAg = new Intent(context, OrderEvaluateAppendActivity.class);
                intentEvaAg.putExtra("ordersId", ordersVo.getOrdersId() + "");
                startActivity(intentEvaAg);
                break;
            case R.id.btnGroupDetail:
                //跳转到拼团分享页面
                LogHelper.e("btnGroupDetail");
                Intent intentGroupDetail = new Intent(context, GroupShareActivity.class);
                intentGroupDetail.putExtra("goId", ordersVo.getGoId());
                startActivity(intentGroupDetail);
                break;
        }
    }

    //取消订单
    private void requestCancelOrder() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("ordersId", ordersId + "");

        OkHttpUtil.postAsyn(this, ConstantUrl.URL_ORDER_CANCEL, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                requestOrdersDetails();
            }
        }, params);
    }

    //确认收货
    private void requestSureOrder() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("ordersId", ordersId + "");

        OkHttpUtil.postAsyn(this, ConstantUrl.URL_ORDER_SURE, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                requestOrdersDetails();
            }
        }, params);
    }

}
