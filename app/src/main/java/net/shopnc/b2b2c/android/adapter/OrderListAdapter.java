package net.shopnc.b2b2c.android.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.pay.PayDemoActivity;
import com.google.gson.reflect.TypeToken;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.GoodGift;
import net.shopnc.b2b2c.android.bean.OrdersGoodsVo;
import net.shopnc.b2b2c.android.bean.OrdersPayVo;
import net.shopnc.b2b2c.android.bean.OrdersVo;
import net.shopnc.b2b2c.android.bean.PaymentBean;
import net.shopnc.b2b2c.android.bean.WXPayReq;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.custom.dialog.NCDialog;
import net.shopnc.b2b2c.android.custom.dialog.NCDialogConfirm;
import net.shopnc.b2b2c.android.custom.dialog.PayChoosenDialog;
import net.shopnc.b2b2c.android.ui.order.OrderDeliveryInfoActivity;
import net.shopnc.b2b2c.android.ui.order.OrderDetailsActivity;
import net.shopnc.b2b2c.android.ui.order.OrderEvaluateActivity;
import net.shopnc.b2b2c.android.ui.order.OrderEvaluateAppendActivity;
import net.shopnc.b2b2c.android.ui.order.PaySuccessActivity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @Description 订单列表页
 * @Author qyf
 * <p>
 * Created 2016/5/12 9:31.
 */
public class OrderListAdapter extends CommonAdapter<OrdersPayVo> {
    private MyShopApplication application;
    private String moneyRmb;
    private String goodNumFlag;
    private String allPrice = "";

    public OrderListAdapter(Context context, MyShopApplication application) {
        super(context, R.layout.listview_order_list_item);
        this.application = application;
        moneyRmb = mContext.getResources().getString(R.string.money_rmb);
        goodNumFlag = mContext.getResources().getString(R.string.good_num);
    }

    @Override
    public void convert(ViewHolder holder, final OrdersPayVo ordersPayVo) {
        Button btnPayOrder = holder.getView(R.id.btnPayOrder);

        LinearLayout llStore = holder.getView(R.id.llStore);
        llStore.removeAllViews();
        bindStoreItem(llStore, btnPayOrder, ordersPayVo.getOrdersOnlineDiffAmount(), ordersPayVo.getOrdersVoList());
    }

    //绑定店铺信息
    private void bindStoreItem(LinearLayout llStore, final Button btnPayOrder, BigDecimal orderPay, List<OrdersVo> ordersVoList) {
        for (final OrdersVo ordersVo : ordersVoList) {
            final AddViewHolder storeHolder = new AddViewHolder(mContext, R.layout.listview_order_store_item);
//            final AddViewHolder storeHolder = AddViewHolder.get(mContext, R.layout.listview_order_store_item);
            String ordersStateName = ordersVo.getOrdersStateName();
            TextView tvOrderState = storeHolder.getView(R.id.tvOrderState);
            storeHolder.setText(R.id.tvStoreName, "  " + ordersVo.getStoreName() + "  ")
                    .setText(R.id.tvOrderState, ordersStateName)
                    .setText(R.id.tvOrderGoodsNum, "共" + getStoreGoods(ordersVo) + "件商品，合计")
                    .setText(R.id.tvOrderAllPrice, moneyRmb + ShopHelper.getPriceString(ordersVo.getOrdersAmount()))
                    .setText(R.id.tvOrderShippingFee, ordersVo.getFreightAmount().compareTo(new BigDecimal(0)) == 0 ? "(包邮)" : "(含运费" + moneyRmb + ShopHelper.getPriceString(ordersVo.getFreightAmount()) + ")");

            //店铺跳转
            storeHolder.setOnClickListener(R.id.tvStoreName, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShopHelper.gotoStoreActivity(mContext, ordersVo.getStoreId());
                }
            });

            LinearLayout llSpu = storeHolder.getView(R.id.llSpu);
            //绑定商品信息
            bindSpuItem(llSpu, ordersVo.getOrdersGoodsVoList(), tvOrderState, ordersStateName);

            LinearLayout llGift = storeHolder.getView(R.id.llGift);
            LinearLayout llGiftLayout = storeHolder.getView(R.id.llGiftLayout);
            bindGiftView(llGiftLayout, llGift, ordersVo.getOrdersGiftVoList());

            setOnderAction(ordersVo, storeHolder);
            if (ordersVo.getShowMemberPay() == 1) {  //是否可以支付(1-是,0-否)
                btnPayOrder.setVisibility(View.VISIBLE);
                allPrice = moneyRmb + ShopHelper.getPriceString(orderPay);
                btnPayOrder.setText("订单支付（" + allPrice + ")");
                btnPayOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getAndShowPayment(ordersVo.getPayId());
                    }
                });
            } else {
                btnPayOrder.setVisibility(View.GONE);
            }


            llStore.addView(storeHolder.getCustomView());

        }

    }

    //绑定商品信息
    private void bindSpuItem(LinearLayout llStore, List<OrdersGoodsVo> ordersGoodsVoList, TextView tvOrderState, String ordersStateName) {
        for (final OrdersGoodsVo ordersGoodsVo : ordersGoodsVoList) {
            AddViewHolder spuHolder = new AddViewHolder(mContext, R.layout.listview_order_spu_item);
//            AddViewHolder spuHolder = AddViewHolder.get(mContext, R.layout.listview_order_spu_item);
            String promotionTitle = ordersGoodsVo.getPromotionTitle();
            if ("多人拼团".equals(promotionTitle)) {
                Spanned spanned = Html.fromHtml("<font color=\"#555555\">(多人拼团)</font>" + "<font color=\"#ED5968\">" + ordersStateName + "</font>");
                tvOrderState.setText(spanned);
            }

            spuHolder.setImage(R.id.ivGoodPic, ordersGoodsVo.getImageSrc())
                    .setText(R.id.tvGoodName, ordersGoodsVo.getGoodsName())
                    .setText(R.id.tvGoodsSPec, ordersGoodsVo.getGoodsFullSpecs())
                    .setText(R.id.tvGoodPrice, moneyRmb + ShopHelper.getPriceString(ordersGoodsVo.getGoodsPrice()))
                    .setText(R.id.tvGoodsNum, goodNumFlag + ordersGoodsVo.getBuyNum())
                    .setText(R.id.tvPromotionTitle, promotionTitle)
                    .setVisible(R.id.tvPromotionTitle, Common.isNotEmpty(ordersGoodsVo.getPromotionTitle()));
            //跳转订单详情
            spuHolder.getCustomView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, OrderDetailsActivity.class);
                    intent.putExtra(OrderDetailsActivity.ORDERSID, ordersGoodsVo.getOrdersId());
                    mContext.startActivity(intent);
                }
            });
            llStore.addView(spuHolder.getCustomView());
        }
    }

    private void bindGiftView(LinearLayout llGiftLayout, LinearLayout llGift, List<GoodGift> giftList) {
        if (giftList != null && giftList.size() > 0) {
            llGiftLayout.setVisibility(View.VISIBLE);
            llGift.removeAllViews();
            for (GoodGift gift : giftList) {
                AddViewHolder giftHolder = new AddViewHolder(mContext, R.layout.cart_gift_item);
//                AddViewHolder giftHolder = AddViewHolder.get(mContext, R.layout.cart_gift_item);
                giftHolder.setText(R.id.tvGiftName, gift.getCartShowText())
                        .setText(R.id.tvGiftNum, "x" + gift.getGiftNum());
                llGift.addView(giftHolder.getCustomView());
            }
        } else {
            llGiftLayout.setVisibility(View.GONE);
        }
    }

    //获取店铺商品总数
    private int getStoreGoods(OrdersVo ordersVoList) {
        int num = 0;
        for (OrdersGoodsVo ordersGoodsVo : ordersVoList.getOrdersGoodsVoList()) {
            num += ordersGoodsVo.getBuyNum();
        }
        for (GoodGift goodGift : ordersVoList.getOrdersGiftVoList()) {
            num += goodGift.getGiftNum();
        }
        return num;
    }

    private void setOnderAction(final OrdersVo ordersVo, final AddViewHolder storeHolder) {
        if (ordersVo.getShowRefundWaiting() == 1) {  //如果退款退货中显示、则其他都不显示
            storeHolder.setVisible(R.id.btnOrderGoodRefund, true);
            return;
        } else {
            storeHolder.setVisible(R.id.btnOrderGoodRefund, false);
        }

        if (ordersVo.getShowMemberCancel() == 1) {  //是否可以取消订单(1-是,0-否)
            storeHolder.setVisible(R.id.btnOrderCancel, true);
            storeHolder.setOnClickListener(R.id.btnOrderCancel, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NCDialog dialog = new NCDialog(mContext);
                    dialog.setText1("确定取消订单？");
                    NCDialogConfirm confirm = new NCDialogConfirm() {
                        @Override
                        public void onDialogConfirm() {
                            requestCancelOrder(ordersVo.getOrdersId());
                        }
                    };
                    dialog.setOnDialogConfirm(confirm);
                    dialog.showPopupWindow();
                }
            });
        } else {
            storeHolder.setVisible(R.id.btnOrderCancel, false);
        }

        if (ordersVo.getShowShipSearch() == 1) {  // 是否可以查询物流(1-是,0-否)
            storeHolder.setVisible(R.id.btnOrderShipping, true);
            storeHolder.setOnClickListener(R.id.btnOrderShipping, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, OrderDeliveryInfoActivity.class);
                    intent.putExtra(OrderDeliveryInfoActivity.SHIPSN, ordersVo.getShipSn());
                    intent.putExtra(OrderDeliveryInfoActivity.SHIPCODE, ordersVo.getShipCode());
                    mContext.startActivity(intent);
                }
            });
        } else {
            storeHolder.setVisible(R.id.btnOrderShipping, false);
        }

        if (ordersVo.getShowMemberReceive() == 1) {   //是否可以收货(1 - 是, 0 - 否)
            storeHolder.setVisible(R.id.btnOrderSure, true);
            storeHolder.setOnClickListener(R.id.btnOrderSure, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NCDialog dialog = new NCDialog(mContext);
                    dialog.setText1("确定收到货了吗？");
                    NCDialogConfirm confirm = new NCDialogConfirm() {
                        @Override
                        public void onDialogConfirm() {
                            requestSureOrder(ordersVo.getOrdersId());
                        }
                    };
                    dialog.setOnDialogConfirm(confirm);
                    dialog.showPopupWindow();
                }
            });
        } else {
            storeHolder.setVisible(R.id.btnOrderSure, false);
        }

        if (ordersVo.getShowEvaluation() == 1) {
            storeHolder.setVisible(R.id.btnOrderEva, true);
            storeHolder.setOnClickListener(R.id.btnOrderEva, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, OrderEvaluateActivity.class);
                    intent.putExtra("ordersId", ordersVo.getOrdersId() + "");
                    mContext.startActivity(intent);
                }
            });
        } else {
            storeHolder.setVisible(R.id.btnOrderEva, false);
        }

        if (ordersVo.getShowEvaluationAppend() == 1) {
            storeHolder.setVisible(R.id.btnOrderEvaAg, true);
            storeHolder.setOnClickListener(R.id.btnOrderEvaAg, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, OrderEvaluateAppendActivity.class);
                    intent.putExtra("ordersId", ordersVo.getOrdersId() + "");
                    mContext.startActivity(intent);
                }
            });
        } else {
            storeHolder.setVisible(R.id.btnOrderEvaAg, false);
        }

//        if (ordersVo.getShowMemberDelayReceive() == 1) {
//            storeHolder.setVisible(R.id.btnOrderDelay, true);
//            storeHolder.setOnClickListener(R.id.btnOrderDelay, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //TODO  延迟收货
//                    TToast.showShort(mContext, "请去PC端延迟收货");
//                }
//            });
//        } else {
//            storeHolder.setVisible(R.id.btnOrderDelay, false);
//        }
    }

    //取消订单
    private void requestCancelOrder(int ordersId) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("ordersId", ordersId + "");

        OkHttpUtil.postAsyn(mContext, ConstantUrl.URL_ORDER_CANCEL, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                EventBus.getDefault().post(1);
            }
        }, params);
    }

    //确认收货
    private void requestSureOrder(int ordersId) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("ordersId", ordersId + "");

        OkHttpUtil.postAsyn(mContext, ConstantUrl.URL_ORDER_SURE, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                EventBus.getDefault().post(1);
            }
        }, params);
    }

    /**
     * 订单列表页调用，隐藏稍后支付
     *
     * @param payId
     */
    public void getAndShowPayment(int payId) {
        getAndShowPayment(payId, false);
    }

    //订单支付点击获取可用支付列表
    public void getAndShowPayment(int payId, final boolean showPayLater) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("clientType", "app");
        params.put("payId", payId + "");

        OkHttpUtil.postAsyn(mContext, ConstantUrl.URL_PAY_LIST, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                final PaymentBean paymentBean = JsonUtil.toBean(data, new TypeToken<PaymentBean>() {
                }.getType());

                Log.d("pay", "response: paymentBean = " + paymentBean);
                if (paymentBean.getPayAmount().compareTo(new BigDecimal(0)) == 1) {
                    if (paymentBean.getAllowPredeposit() != 1) {
                        if (paymentBean.getPaymentList().isEmpty()) {
                            TToast.showShort(mContext, "商家暂未开启支付方式");
                            return;
                        }
                    }
                    final PayChoosenDialog dialog = new PayChoosenDialog(mContext, paymentBean);
                    dialog.setPayChoosen(new PayChoosenDialog.PayChoosen() {
                        @Override
                        public void requestWXpayUrl(boolean isPredepositPay, String psw) {
                            requestWXpay(paymentBean, isPredepositPay, psw, dialog);
                        }

                        @Override
                        public void requestAlipayUrl(boolean isPredepositPay, String psw) {
                            requestAlipay(paymentBean, isPredepositPay, psw, dialog);
                        }
                    });
                    dialog.setShowPaylater(showPayLater);
                    dialog.show();
                } else {
                    //货到付款
                    Intent intent = new Intent(mContext, PaySuccessActivity.class);
                    intent.putExtra("pay_type", 1);
                    mContext.startActivity(intent);
                    ((Activity) mContext).finish();

                    //原有逻辑
//                    EventBus.getDefault().post(1);
                }
            }
        }, params);
    }

    /*微信支付*/
    private void requestWXpay(final PaymentBean paymentBean, boolean isPredepositPay, String psw, final PayChoosenDialog dialog) {

        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        int payId = paymentBean.getPayId();
        params.put("payId", payId + "");
        if (isPredepositPay) {
            params.put("predepositPay", "1");
            params.put("payPwd", psw);
        } else {
            params.put("predepositPay", "0");
        }


        OkHttpUtil.postAsyn(mContext, ConstantUrl.URL_PAY_WXPAY, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                //关闭dialog
                dialog.dismiss();

                //使用了预存款且支付完成,默认使用支付宝，不会执行这里
                if (JsonUtil.toInteger(data, "isPayed") == 1) {
                    TToast.showShort(mContext, "支付成功");
                    EventBus.getDefault().post(1);//刷新显示
                } else {
                    WXPayReq wxPayReq = JsonUtil.toBean(data, "payParam", new TypeToken<WXPayReq>() {
                    }.getType());

                    IWXAPI api = WXAPIFactory.createWXAPI(mContext, wxPayReq.getAppid());
                    PayReq req = new PayReq();
                    req.appId = wxPayReq.getAppid();
                    req.partnerId = wxPayReq.getPartnerid();
                    req.prepayId = wxPayReq.getPrepayid();
                    req.nonceStr = wxPayReq.getNoncestr();
                    req.timeStamp = wxPayReq.getTimestamp();
                    req.packageValue = JsonUtil.toString(data, "payParam", "package");
                    req.sign = wxPayReq.getSign();
                    req.extData = "app data"; // optional
//                    TToast.showShort(mContext, "正常调起微信支付");

                    //传递payId
//                    int payId = paymentBean.getPayId();
//                    EventBus.getDefault().post(new BuyStepBus(BuyStepBus.PAYID, payId));

                    api.sendReq(req);

                }

                //销毁当前Act，BuyStep1或OrderList
                ((Activity) mContext).finish();
            }
        }, params);
    }

    /*支付宝支付*/
    private void requestAlipay(PaymentBean paymentBean, boolean isPredepositPay, String psw, final PayChoosenDialog dialog) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("payId", paymentBean.getPayId() + "");
        if (isPredepositPay) {
            params.put("predepositPay", "1");
            params.put("payPwd", psw);
        } else {
            params.put("predepositPay", "0");
        }

        OkHttpUtil.postAsyn(mContext, ConstantUrl.URL_PAY_ALIBABA, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                //关闭dialog
                dialog.dismiss();

                String payId = JsonUtil.toString(data, "payId");
                //使用了预存款且支付完成
                if (JsonUtil.toInteger(data, "isPayed") == 1) {
                    //跳转支付成功页面
                    Intent intent = new Intent(mContext, PaySuccessActivity.class);
                    intent.putExtra("pay_type", 0);
                    intent.putExtra("payId", payId);
                    mContext.startActivity(intent);

                    //原有逻辑
//                    TToast.showShort(mContext, "支付成功");
//                    EventBus.getDefault().post(1);
                } else {
                    PayDemoActivity payDemoActivity = new PayDemoActivity(mContext, JsonUtil.toString(data, "payParamString"));
                    //传递payId
                    payDemoActivity.setPayId(payId);
                    payDemoActivity.doPay();
                }

                //销毁当前Act，BuyStep1或OrderList
                ((Activity) mContext).finish();
            }

        }, params);

    }
}
