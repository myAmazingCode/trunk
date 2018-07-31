package net.shopnc.b2b2c.android.ui.buy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.OrderListAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.Address;
import net.shopnc.b2b2c.android.bean.BuyBookStep;
import net.shopnc.b2b2c.android.bean.BuyGoodsItemVo;
import net.shopnc.b2b2c.android.bean.GoodGift;
import net.shopnc.b2b2c.android.bean.Invoice;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.dialog.NCDialog;
import net.shopnc.b2b2c.android.custom.dialog.NCDialogConfirm;
import net.shopnc.b2b2c.android.ui.mine.AddressADDActivity;
import net.shopnc.b2b2c.android.ui.mine.AddressListActivity;
import net.shopnc.b2b2c.android.ui.order.OrderListActivity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.LoadImage;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.math.BigDecimal;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 购买第一步
 * 1.提交订单的实体类：可以在接收到各个结果的时候存入，但是为保证赋值集中，故使用成员变量存储，虽然增加了activity的体积
 *
 * @author qin
 * @date 2016/4/26
 */
public class BuyStepBook1Activity extends BaseActivity {

    public static String DATA = "data";
    @Bind(R.id.ivAddress)
    ImageView ivAddress;
    @Bind(R.id.tvAddressMemberName)
    TextView tvAddressMemberName;
    @Bind(R.id.tvAddressMemberPhone)
    TextView tvAddressMemberPhone;
    @Bind(R.id.tvAddressMemberArea)
    TextView tvAddressMemberArea;
    @Bind(R.id.rlAddress)
    RelativeLayout rlAddress;
    @Bind(R.id.ifshowOnpayID)
    RadioButton ifshowOnpayID;
    @Bind(R.id.tvAllowVatName)
    TextView tvAllowVatName;
    @Bind(R.id.llAllowVat)
    LinearLayout llAllowVat;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.etPrePhone)
    EditText etPrePhone;
    @Bind(R.id.llPhone)
    LinearLayout llPhone;
    @Bind(R.id.btnPreSelect)
    ImageView btnPreSelect;
    @Bind(R.id.llPayPre)
    LinearLayout llPayPre;
    @Bind(R.id.btnFinishSelect)
    ImageView btnFinishSelect;
    @Bind(R.id.llPayFinish)
    LinearLayout llPayFinish;
    @Bind(R.id.ivSpuImage)
    ImageView ivSpuImage;
    @Bind(R.id.tvSpuName)
    TextView tvSpuName;
    @Bind(R.id.tvSkuSpec)
    TextView tvSkuSpec;
    @Bind(R.id.tvAllPrice)
    TextView tvAllPrice;
    @Bind(R.id.tvSkuPrice)
    TextView tvSkuPrice;
    @Bind(R.id.tvSkuNum)
    TextView tvSkuNum;
    @Bind(R.id.tvPrePrice)
    TextView tvPrePrice;
    @Bind(R.id.tvFinishPrice)
    TextView tvFinishPrice;
    @Bind(R.id.tvFee)
    TextView tvFee;
    @Bind(R.id.etStoreMsg)
    EditText etStoreMsg;
    @Bind(R.id.tvStoreAll)
    TextView tvStoreAll;
    @Bind(R.id.tvMoneyAll)
    TextView tvMoneyAll;
    @Bind(R.id.btnCommitOrder)
    Button btnCommitOrder;
    @Bind(R.id.llGift)
    LinearLayout llGift;
    @Bind(R.id.ifshowOffpayID)
    RadioButton ifshowOffpayID;
    @Bind(R.id.llSku)
    RelativeLayout llSku;

    private String data;
    private BuyBookStep buyBookStep;
    private Address address;
    private BuyGoodsItemVo buyGoodsItemVo;

    private String moneyRmb;
    private BigDecimal freightAmount = new BigDecimal(0);
    private BigDecimal goodsAll = new BigDecimal(0);
    private int invoiceId;   //发票Id,选填，不使用时留空

    @Override
    protected void setView() {
        setContentView(R.layout.buy_book_step1_view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("确认订单");
        EventBus.getDefault().register(this);
        data = getIntent().getStringExtra(BuyStepBook1Activity.DATA);
        setBtnMoreVisible();
        moneyRmb = context.getResources().getString(R.string.money_rmb);
        getData();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void getData() {
        buyBookStep = JsonUtil.toBean(data, new TypeToken<BuyBookStep>() {
        }.getType());
        address = buyBookStep.getAddress();
        chooseFreight();
        buyGoodsItemVo = buyBookStep.getBuyGoodsItemVo();
        initView();
    }

    private void chooseFreight() {
        if (null == address || null == address.getAddressId()) {
            final NCDialog ncDialog = new NCDialog(context);
            ncDialog.setText1("请添加地址");
            NCDialogConfirm ncDialogConfirm = new NCDialogConfirm() {
                @Override
                public void onDialogConfirm() {
                    startActivity(new Intent(context, AddressADDActivity.class));
                }
            };
            ncDialog.setOnDialogConfirm(ncDialogConfirm);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ncDialog.showPopupWindow();
                }
            }, 1000);
        } else {
            getFreight(address.getAddressId());
        }
    }

    private void initView() {
        //发票信息的显示
        invoiceId = -1;
        tvAllowVatName.setText("不需要发票");
        llAllowVat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InvoiceActivity.class);
                context.startActivity(intent);
            }
        });
        bindGoodInfo();
    }

    //绑定地址信息
    private void bindAddressData() {
        tvAddressMemberName.setText("收货人：" + address.getRealName());
        tvAddressMemberPhone.setText(address.getMobPhone());
        tvAddressMemberArea.setText(address.getAreaInfo() + "  " + address.getAddress());
    }


    /**
     * 更换地址后，重新请求得到运费等信息
     */
    private void getFreight(int addressId) {
        String url = ConstantUrl.URL_BOOK_ADDRESS_FREIGHT;
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("addressId", addressId + "");
        params.put("goodsId", buyBookStep.getBuyGoodsItemVo().getGoodsId() + "");
        params.put("buyNum", buyBookStep.getBuyGoodsItemVo().getBuyNum() + "");
        params.put("clientType", "android");
        OkHttpUtil.postAsyn(this, url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                address = JsonUtil.toBean(data, "address", new TypeToken<Address>() {
                }.getType());
                freightAmount = new BigDecimal(JsonUtil.toString(data, "freightAmount"));
                bindAddressData();
                tvFee.setText(moneyRmb + ShopHelper.getPriceString(freightAmount));
                setFollowFreight();
            }
        }, params);

    }


    private void bindGoodInfo() {
        LoadImage.loadRemoteImg(context, ivSpuImage, buyGoodsItemVo.getImageSrc());
        tvSpuName.setText(buyGoodsItemVo.getGoodsName());
        tvAllPrice.setText(moneyRmb + ShopHelper.getPriceString(buyGoodsItemVo.getGoodsPrice().multiply(new BigDecimal(buyGoodsItemVo.getBuyNum()))));
        tvSkuSpec.setText(Common.isEmpty(buyGoodsItemVo.getGoodsFullSpecs()) ? "无规格" : buyGoodsItemVo.getGoodsFullSpecs());
        tvSkuNum.setText(buyGoodsItemVo.getBuyNum() + buyGoodsItemVo.getUnitName());
        tvSkuPrice.setText("单价：" + moneyRmb + ShopHelper.getPriceString(buyGoodsItemVo.getGoodsPrice()) + "/" + buyGoodsItemVo.getUnitName());
        if (buyGoodsItemVo.getIsGift() == 1 && buyGoodsItemVo.getGiftVoList() != null) {
            llGift.setVisibility(View.VISIBLE);
            for (GoodGift gift : buyGoodsItemVo.getGiftVoList()) {
                AddViewHolder giftHolder = new AddViewHolder(context, R.layout.cart_gift_item);
                giftHolder.setText(R.id.tvGiftName, gift.getCartShowText())
                        .setText(R.id.tvGiftNum, "x" + gift.getGiftNum());
                llGift.addView(giftHolder.getCustomView());
            }
        } else {
            llGift.setVisibility(View.GONE);
        }
        tvPrePrice.setText("小计：" + moneyRmb + ShopHelper.getPriceString(buyGoodsItemVo.book.getDownPayment()));
        tvFinishPrice.setText("小计：" + moneyRmb + ShopHelper.getPriceString(buyGoodsItemVo.book.getFinalPayment()));
        etPrePhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                getFreight(address.getAddressId());
            }
        });
    }

    //绑定费用信息
    private void setFollowFreight() {
        BigDecimal orderAll = new BigDecimal(0);
        boolean commitSelected = false;
        if (btnFinishSelect.isSelected()) {  //支付尾款
            llPhone.setVisibility(View.GONE);
            commitSelected = btnPreSelect.isSelected();
            orderAll = buyGoodsItemVo.getGoodsPrice().multiply(new BigDecimal(buyGoodsItemVo.getBuyNum()));
        } else {
            llPhone.setVisibility(View.VISIBLE);
            commitSelected = Common.isMobileNO(Common.getText(etPrePhone)) && btnPreSelect.isSelected();
            orderAll = buyGoodsItemVo.book.getDownPayment().multiply(new BigDecimal(buyGoodsItemVo.getBuyNum()));
        }
        BigDecimal storeAll = buyGoodsItemVo.getGoodsPrice().multiply(new BigDecimal(buyGoodsItemVo.getBuyNum())).add(freightAmount);
        tvStoreAll.setText(Html.fromHtml("小计（含运费）：<font color=\"#ED5968\">" + storeAll + "</font>元"));
        btnCommitOrder.setSelected(commitSelected);
        BigDecimal allPrice = orderAll.add(freightAmount);
        String s = "应付总金额：" + context.getResources().getString(R.string.money_rmb) + ShopHelper.getPriceString(allPrice);
        int position = s.indexOf("：");
        SpannableString ss = new SpannableString(s);
        ss.setSpan(new TextAppearanceSpan(context, R.style.buystep_all_black_small), 0, position + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new TextAppearanceSpan(context, R.style.buystep_all_red_small), position + 1, position + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new TextAppearanceSpan(context, R.style.buystep_all_red_big), position + 2, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvMoneyAll.setText(ss, TextView.BufferType.SPANNABLE);
    }

    //提交订单
    @OnClick(R.id.btnCommitOrder)
    public void btnCommitOrderClick() {
        if (btnCommitOrder.isSelected()) {
            BuyStepCommitBean buyStepCommitBean = new BuyStepCommitBean();
            buyStepCommitBean.addressId = address.getAddressId();
            buyStepCommitBean.receiverMessage = Common.getText(etStoreMsg);
            if (invoiceId != -1) {//-1  只是我自己写的不需要发票标志
                buyStepCommitBean.invoiceId = invoiceId;
            }
            buyStepCommitBean.paymentType = btnFinishSelect.isSelected() ? 0 : 1;
            buyStepCommitBean.goodsId = buyGoodsItemVo.getGoodsId();
            buyStepCommitBean.buyNum = buyGoodsItemVo.getBuyNum();
            buyStepCommitBean.bookPhone = Common.getText(etPrePhone);
            requestSaveOrder(buyStepCommitBean);
        }
    }

    private void requestSaveOrder(BuyStepCommitBean buyStepCommitBean) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("buyData", new Gson().toJson(buyStepCommitBean));

        OkHttpUtil.postAsyn(this, ConstantUrl.URL_BOOK_SAVE_ORDER, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                OrderListAdapter adapter = new OrderListAdapter(context, application);
                adapter.getAndShowPayment(JsonUtil.toInteger(data, "payId"), true);
                //支付成功则跳至onEventMainThread(Integer i)执行
            }
        }, params);
    }

    @OnClick(R.id.rlAddress)
    public void rlAddressClick() {
        Intent i = new Intent(this, AddressListActivity.class);
        i.putExtra("addressFlag", "1");
        i.putExtra("addressId", address.getAddressId());
        startActivity(i);
    }

    public void onEventMainThread(BuyStepBus buyStepBus) {
        if (buyStepBus.getMsg().equals(BuyStepBus.ADDRESSID)) {    //重新选择地址的回调
            getFreight((Integer) buyStepBus.getObj());
        } else if (buyStepBus.getMsg().equals(BuyStepBus.INVOICE)) {
            Invoice invoice = (Invoice) buyStepBus.getObj();
            if (invoice == null) {
                invoiceId = -1;
                tvAllowVatName.setText("不需要发票");
            } else {
                invoiceId = invoice.getInvoiceId();
                tvAllowVatName.setText(invoice.getInvoiceContent());
            }
        }
    }

    public void onEventMainThread(Integer i) {
        Intent intent = new Intent(activity, OrderListActivity.class);
        intent.putExtra(OrderListActivity.STATE, "");
        startActivity(intent);
        finish();
    }

    @OnClick({R.id.btnPreSelect, R.id.btnFinishSelect})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPreSelect:
                btnPreSelect.setSelected(!btnPreSelect.isSelected());
                break;
            case R.id.btnFinishSelect:
                btnFinishSelect.setSelected(!btnFinishSelect.isSelected());
                break;
        }
        getFreight(address.getAddressId());
    }

    //提交订单时需使用的实体
    private class BuyStepCommitBean {
        public int addressId;
        public String receiverMessage;
        public int invoiceId;   //发票Id,选填，不使用时留空
        public int paymentType;
        public int goodsId;
        public int buyNum;
        public String bookPhone;
    }

}
