package net.shopnc.b2b2c.android.ui.buy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.BuyDataHelper;
import net.shopnc.b2b2c.android.adapter.OrderListAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.Address;
import net.shopnc.b2b2c.android.bean.BuyData;
import net.shopnc.b2b2c.android.bean.BuyGoodsItemFreightVo;
import net.shopnc.b2b2c.android.bean.BuyGoodsItemVo;
import net.shopnc.b2b2c.android.bean.BuyStep;
import net.shopnc.b2b2c.android.bean.BuyStepPrice;
import net.shopnc.b2b2c.android.bean.BuyStoreFreightVo;
import net.shopnc.b2b2c.android.bean.BuyStoreVo;
import net.shopnc.b2b2c.android.bean.CartBundlingVo;
import net.shopnc.b2b2c.android.bean.RedPackageVo;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.custom.dialog.NCDialog;
import net.shopnc.b2b2c.android.custom.dialog.NCDialogConfirm;
import net.shopnc.b2b2c.android.ui.good.GoodBusBean;
import net.shopnc.b2b2c.android.ui.mine.AddressADDActivity;
import net.shopnc.b2b2c.android.ui.mine.AddressListActivity;
import net.shopnc.b2b2c.android.ui.order.OrderListActivity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 购买第一步
 * 1.提交订单的实体类：可以在接收到各个结果的时候存入，但是为保证赋值集中，故使用成员变量存储，虽然增加了activity的体积
 *
 * @author qin
 * @date 2016/4/26
 */
public class BuyStep1Activity extends BaseActivity {

    public static String DATA = "data";
    public static String IS_GROUP = "isGroup";
    public static String GROUP_ID = "groupId";
    public static String GO_ID = "goId";
    public static String ISEXISTBUNDLING = "isExistBundling";
    @Bind(R.id.ivAddress)
    ImageView ivAddress;
    @Bind(R.id.tvAddressMemberName)
    TextView tvAddressMemberName;
    @Bind(R.id.tvNoGoodsWarning)
    TextView tvNoGoodsWarning;
    @Bind(R.id.tvDef)
    TextView tvDef;
    @Bind(R.id.tvAddressMemberArea)
    TextView tvAddressMemberArea;
    @Bind(R.id.tvMoneyAll)
    TextView tvMoneyAll;
    @Bind(R.id.btnCommitOrder)
    Button btnCommitOrder;
    @Bind(R.id.rlAddress)
    RelativeLayout rlAddress;
    @Bind(R.id.ifshowOnpayID)
    RadioButton ifshowOnpayID;
    @Bind(R.id.ifshowOffpayID)
    RadioButton ifshowOffpayID;
//    @Bind(R.id.rvStore)
//    RecyclerView rvStore;

    @Bind(R.id.llStoreData)
    LinearLayout llStoreData;
    @Bind(R.id.tvRPName)
    TextView tvRPName;
    @Bind(R.id.llRP)
    LinearLayout llRP;
    @Bind(R.id.tvAllowVatName)
    TextView tvAllowVatName;
    @Bind(R.id.llAllowVat)
    LinearLayout llAllowVat;


//    private BuyStepStoreAdapter adapter;

    private String data;
    private BuyStep buyStep;
    private List<BuyStoreVo> buyStoreVos;
    private Address address;
    private List<BuyStoreFreightVo> storeList;
    private List<RedPackageVo> redPackageVos;

    private List<StoreListItem> storeListItems;

    private HashMap<Integer, BuyStepPrice> storeMoney;

    private BigDecimal feeAll = new BigDecimal(0);
    private BigDecimal discountAll = new BigDecimal(0);
    private BigDecimal goodsAll = new BigDecimal(0);
    private BigDecimal redPackagePrice = new BigDecimal(0);


    private int isCart;  // 是否来源于购物车（1–是 0–否）,必填
    private int invoiceId;   //发票Id,选填，不使用时留空
    private int redPackageId;  //选中的红包的id  （为保证数据统一，可以保存红包对象）
    private int isGroup; //是否是团购商品
    private int isExistBundling; //是否优惠套装
    private int groupId; //拼团信息编号
    private int goId; //开团编号
    protected String[] invoice;//发票信息

    @Override
    protected void setView() {
        setContentView(R.layout.buy_step1_view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("确认订单");
        EventBus.getDefault().register(this);
        setBtnMoreVisible();

        data = getIntent().getStringExtra(BuyStep1Activity.DATA);
        //拼团参数
        isGroup = getIntent().getIntExtra(BuyStep1Activity.IS_GROUP, 0);
        groupId = getIntent().getIntExtra(BuyStep1Activity.GROUP_ID, 0);
        goId = getIntent().getIntExtra(BuyStep1Activity.GO_ID, 0);
        isExistBundling = getIntent().getIntExtra(BuyStep1Activity.ISEXISTBUNDLING, 0);

        storeListItems = new ArrayList<>();
        storeList = new ArrayList<>();
        redPackageVos = new ArrayList<>();
        storeMoney = new HashMap<>();

//        adapter = new BuyStepStoreAdapter(context);
//        //是否是优惠套装
//        adapter.setIsExistBundling(isExistBundling);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        rvStore.setLayoutManager(manager);
//        rvStore.setAdapter(adapter);

        getData();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void getData() {
        buyStep = JsonUtil.toBean(data, new TypeToken<BuyStep>() {
        }.getType());
        buyStoreVos = buyStep.getBuyStoreVoList();
        address = buyStep.getAddress();

//        getFreight(address.getAddressId());
        chooseFreight();

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
            trancelateStoreDataToUp();
            getFreight(address.getAddressId());
            initView();
        }
    }

    private void initView() {
        //货到付款
        if (buyStep.getAllowOffline() == 1) {
            ifshowOffpayID.setVisibility(View.VISIBLE);
        } else {
            ifshowOffpayID.setVisibility(View.GONE);
        }

        isCart = buyStep.getIsCart();   //1-来自购物车

        //发票信息的显示
//        invoiceId = -1;
        tvAllowVatName.setText("不需要发票");
        llAllowVat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InvoiceActivity.class);
                String content = tvAllowVatName.getText().toString();
                if (!content.equals("不需要发票")) {
                    String[] split = content.split(" ");
                    intent.putExtra("invoice", split);
                }
                context.startActivity(intent);
            }
        });


    }


    //绑定地址信息
    private void bindAddressData() {
        tvDef.setVisibility(address.getIsDefault() == 1 ? View.VISIBLE : View.GONE);
        tvAddressMemberName.setText(address.getRealName() + "\t\t" + address.getMobPhone());
        tvAddressMemberArea.setText(address.getAreaInfo() + "  " + address.getAddress());
    }

    private void bindStoreData() {
//        int size = buyStoreVos.size();
//        Log.d(TAG, "bindStoreData: size = " + size);
//        adapter.setDatas(buyStoreVos);
//        adapter.setAreaId(address.getAreaId1());
//        adapter.notifyDataSetChanged();

        llStoreData.removeAllViews();

        //使用LinearLayout添加数据，helper类
        BuyDataHelper dataHelper = new BuyDataHelper(this, llStoreData);
        dataHelper.setAreaId(address.getAreaId1());
        dataHelper.setDatas(buyStoreVos);
        dataHelper.setNoGoodsList(noGoodsList);
        dataHelper.process();
    }

    public static final String TAG = BuyStep1Activity.class.getSimpleName();

    /**
     * 更换地址后，重新请求得到运费等信息
     * buyData简单数据
     * {"addressId":"79","storeList":[{"storeId":4,"goodsList":[{"cartId":363,"goodsId":100,"buyNum":1}]},
     * {"storeId":10,"goodsList":[{"cartId":348,"goodsId":108,"buyNum":134}]}]}
     */
    private void getFreight(final int addressId) {
        String url = ConstantUrl.URL_ADDRESS_FREIGHT;
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        String buyData = new Gson().toJson(new AddressStore(addressId, storeListItems));
        Log.d(TAG, "getFreight: buyData = " + buyData);
        params.put("buyData", buyData);
        params.put("clientType", "android");
        OkHttpUtil.postAsyn(this, url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                address = JsonUtil.toBean(data, "address", new TypeToken<Address>() {
                }.getType());
                storeList = JsonUtil.toBean(data, "storeList", new TypeToken<ArrayList<BuyStoreFreightVo>>() {
                }.getType());
                bindAddressData();
                refreshBuyStoreVoData();
            }
        }, params);

    }

    private List<Integer> noGoodsList = new ArrayList<>();

    //更改地址之后，更新数据源的运费信息
    private void refreshBuyStoreVoData() {
        for (BuyStoreVo buyStoreVo : buyStoreVos) {
            int storeId = buyStoreVo.getStoreId();
            for (BuyStoreFreightVo buyStoreFreightVo : storeList) {
                if (storeId == buyStoreFreightVo.getStoreId()) {
                    buyStoreVo.setFreightAmount(buyStoreFreightVo.getStoreFreightAmount());
                    List<BuyGoodsItemFreightVo> goodsList = buyStoreFreightVo.getGoodsList();
                    for (int i = 0; i < goodsList.size(); i++) {
                        BuyGoodsItemFreightVo buyGoodsItemFreightVo = goodsList.get(i);
                        int allowSend = buyGoodsItemFreightVo.getAllowSend();
                        //任意一个allowSend=0则表明无货
                        if (allowSend == 0) {
                            int goodsId = buyGoodsItemFreightVo.getGoodsId();
                            //记录所有无货商品
                            noGoodsList.add(goodsId);
                            //显示无货提示
                            tvNoGoodsWarning.setVisibility(View.VISIBLE);
                            btnCommitOrder.setEnabled(false);
//                            break;
                        }
                    }
                }
            }
        }
        bindStoreData();
        refreshFollowFreight();
    }


    //更新底部费用信息
    private void refreshFollowFreight() {
        feeAll = new BigDecimal(0);
        discountAll = new BigDecimal(0);
        goodsAll = new BigDecimal(0);

        for (BuyStepPrice buyStepPrice : storeMoney.values()) {
            feeAll = feeAll.add(buyStepPrice.getFreight());
            discountAll = discountAll.add(buyStepPrice.getPriceConform().add(buyStepPrice.getPriceVoucher()));
            goodsAll = goodsAll.add(buyStepPrice.getPriceGood());
        }

        getRedPackageList();

        setFollowFreight();

    }

    //绑定底部费用信息
    private void setFollowFreight() {
//        tvDiacountAll.setText("优惠总计： -" + ShopHelper.getPriceString(discountAll.add(redPackagePrice)));
//        tvFeeAll.setText("运费总计：" + ShopHelper.getPriceString(feeAll));
//        tvGoodsPriceAll.setText("货款总计：" + ShopHelper.getPriceString(goodsAll));
        BigDecimal allPrice = goodsAll.add(feeAll).subtract(discountAll).subtract(redPackagePrice);
        if (allPrice.compareTo(new BigDecimal(0)) == -1) {  //避免为负的极端情况
            allPrice = new BigDecimal(0);
        }
        String s = "实付金额：" + context.getResources().getString(R.string.money_rmb) + ShopHelper.getPriceString(allPrice) + " (含运费)";
        int position = s.indexOf("：");
        int position2 = s.indexOf(" (含运费)");
        SpannableString ss = new SpannableString(s);
        ss.setSpan(new TextAppearanceSpan(context, R.style.buystep_all_black_small), 0, position + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new TextAppearanceSpan(context, R.style.buystep_all_red_small), position + 1, position + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new TextAppearanceSpan(context, R.style.buystep_all_red_big), position + 2, position2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new TextAppearanceSpan(context, R.style.buystep_all_black_small), position2 + 1, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvMoneyAll.setText(ss, TextView.BufferType.SPANNABLE);

    }


    //提交订单
    @OnClick(R.id.btnCommitOrder)
    public void btnCommitOrderClick() {
        BuyStepCommitBean buyStepCommitBean = new BuyStepCommitBean();
        buyStepCommitBean.setAddressId(address.getAddressId());

//        if (invoiceId != -1) {//-1  只是我自己写的不需要发票标志
//            buyStepCommitBean.setInvoiceId(invoiceId);
//        }

        //设置发票信息
        if (invoice != null) {
            buyStepCommitBean.setInvoiceTitle(invoice[0]);
            buyStepCommitBean.setInvoiceContent(invoice[1]);
        }

        buyStepCommitBean.setIsCart(isCart);

        if (ifshowOnpayID.isChecked()) {//支付方式(online–线上支付 offline–货到付款),必填
            buyStepCommitBean.setPaymentTypeCode("online");
        } else {
            buyStepCommitBean.setPaymentTypeCode("offline");
        }

        if (redPackageId != -1) {//-1  只是我自己写的不使用标志
            buyStepCommitBean.setRedPackageId(redPackageId);
        }

        //拼团参数
        buyStepCommitBean.isGroup = isGroup;
        buyStepCommitBean.groupId = groupId;
        buyStepCommitBean.goId = goId;
        //
        buyStepCommitBean.isExistBundling = isExistBundling;

        //设置店铺所有商品数据
        buyStepCommitBean.setStoreList(getTrancelateStoreDiscount());
        buyStepCommitBean.setIsExistTrys(buyStep.getIsExistTrys());

        requestSaveOrder(buyStepCommitBean);
    }

    /**
     * 提交订单
     *
     * @param buyStepCommitBean
     */
    private void requestSaveOrder(BuyStepCommitBean buyStepCommitBean) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("clientType", "android");
        String value = new Gson().toJson(buyStepCommitBean);
        Log.d("value", "requestSaveOrder: value = " + value);
        params.put("buyData", value);
        LogHelper.d(params.toString());

        OkHttpUtil.postAsyn(this, ConstantUrl.URL_SAVE_ORDER, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                Log.d("pay", "response: data = " + data);
                OrderListAdapter adapter = new OrderListAdapter(context, application);
                adapter.getAndShowPayment(JsonUtil.toInteger(data, "payId"), true);
                //支付成功则跳至onEventMainThread(Integer i)执行

                //更新购物车数量
                EventBus.getDefault().post(new GoodBusBean(GoodBusBean.REFRESH_CART_COUNT));
            }
        }, params);
    }

    //将订单数据转换成更新地址时的请求数据格式
    private void trancelateStoreDataToUp() {
        List<BuyData> goodsList = new ArrayList<>();
        //非优惠套装
        for (BuyStoreVo buyStoreVo : buyStoreVos) {
            for (BuyGoodsItemVo buyGoodsItemVo : buyStoreVo.getBuyGoodsItemVoList()) {
                BuyData buyData = new BuyData(buyGoodsItemVo.getGoodsId(), buyGoodsItemVo.getBuyNum());
                goodsList.add(buyData);
            }
            StoreListItem storeListItem = new StoreListItem(buyStoreVo.getStoreId(), goodsList);
            storeListItems.add(storeListItem);
        }

        //优惠套装
        for (BuyStoreVo buyStoreVo : buyStoreVos) {
            List<CartBundlingVo> cartBundlingVoList = buyStoreVo.getCartBundlingVoList();
            for (int i = 0; i < cartBundlingVoList.size(); i++) {
                CartBundlingVo cartBundlingVo = cartBundlingVoList.get(i);
                List<CartBundlingVo.BuyBundlingItemVoListBean> buyBundlingItemVoList = cartBundlingVo.getBuyBundlingItemVoList();
                for (int j = 0; j < buyBundlingItemVoList.size(); j++) {
                    CartBundlingVo.BuyBundlingItemVoListBean bean = buyBundlingItemVoList.get(j);
                    BuyData buyData = new BuyData(bean.getGoodsId(), bean.getBuyNum());
                    goodsList.add(buyData);
                }
            }
            //添加到店铺列表
            StoreListItem storeListItem = new StoreListItem(buyStoreVo.getStoreId(), goodsList);
            storeListItems.add(storeListItem);
        }
    }

    //获得各店铺的buyData信息
    private SparseArray<List<BuyData>> getStoreBuyDatas() {
        SparseArray<List<BuyData>> storeBuyDatas = new SparseArray<>();
        List<BuyData> buyDatas = new ArrayList<>();
        //非优惠套装
        for (BuyStoreVo buyStoreVo : buyStoreVos) {
            for (BuyGoodsItemVo buyGoodsItemVo : buyStoreVo.getBuyGoodsItemVoList()) {
                buyDatas.add(new BuyData(buyGoodsItemVo.getGoodsId(), buyGoodsItemVo.getCartId(), buyGoodsItemVo.getBuyNum()));
            }
            storeBuyDatas.put(buyStoreVo.getStoreId(), buyDatas);
        }
        //优惠套装
        for (BuyStoreVo buyStoreVo : buyStoreVos) {
            List<CartBundlingVo> cartBundlingVoList = buyStoreVo.getCartBundlingVoList();
            for (int i = 0; i < cartBundlingVoList.size(); i++) {
                CartBundlingVo cartBundlingVo = cartBundlingVoList.get(i);
                List<CartBundlingVo.BuyBundlingItemVoListBean> buyBundlingItemVoList = cartBundlingVo.getBuyBundlingItemVoList();
                for (int j = 0; j < buyBundlingItemVoList.size(); j++) {
                    CartBundlingVo.BuyBundlingItemVoListBean bean = buyBundlingItemVoList.get(j);
                    BuyData buyData = new BuyData(bean.getGoodsId(), bean.getCartId(), bean.getBuyNum());
                    buyDatas.add(buyData);
                }
            }
            storeBuyDatas.put(buyStoreVo.getStoreId(), buyDatas);
        }

        return storeBuyDatas;
    }


    //将数据转化为需要的对象格式
    private List<StoreDiscount> getTrancelateStoreDiscount() {
        SparseArray<List<BuyData>> datas = getStoreBuyDatas();
        List<StoreDiscount> storeDiscountList = new ArrayList<>();
        for (BuyStepPrice buyStepPrice : storeMoney.values()) {
            StoreDiscount storeDiscount = new StoreDiscount();
            storeDiscount.setGoodsList(datas.get(buyStepPrice.getStoreId()));
            storeDiscount.setStoreId(buyStepPrice.getStoreId());
            storeDiscount.setConformId(buyStepPrice.getConformId());
            storeDiscount.setVoucherId(buyStepPrice.getVoucherId());
            storeDiscount.setReceiverMessage(buyStepPrice.getReceiverMessage());
            storeDiscountList.add(storeDiscount);
        }
        return storeDiscountList;
    }


    @OnClick(R.id.rlAddress)
    public void rlAddressClick() {
        Intent i = new Intent(this, AddressListActivity.class);
        i.putExtra("addressFlag", "1");
        i.putExtra("addressId", address.getAddressId());
        startActivity(i);
    }

    /**
     * 取得会员可用的满足购买金额下限平台红包
     * goodsAmount 各店铺商品总额-各店铺优惠促销，不含运费
     */
    private void getRedPackageList() {
        //每次重新获得红包列表时，恢复原始数据
        tvRPName.setText("请选择");
        redPackagePrice = new BigDecimal(0);
        redPackageId = -1;


        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("goodsAmount", String.valueOf(goodsAll.subtract(discountAll)));
        params.put("clientType", "android");
        OkHttpUtil.postAsyn(this, ConstantUrl.URL_REDPACKAGE_LIST, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                redPackageVos = JsonUtil.toBean(data, "redPackageVoList", new TypeToken<ArrayList<RedPackageVo>>() {
                }.getType());
                if (null != redPackageVos && redPackageVos.size() > 0) {
                    llRP.setVisibility(View.VISIBLE);
                    llRP.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final AlertDialog dialog = new AlertDialog.Builder(context).create();
                            View view = LayoutInflater.from(context).inflate(R.layout.follow_bottom_dialog, null);
                            ListView listView = view.findViewById(R.id.llChoosens);
                            dialog.setView(view);
                            dialog.show();
                            listView.setAdapter(new CommonAdapter<RedPackageVo>(context, redPackageVos, R.layout.follow_botton_dialog_item) {
                                @Override
                                public void convert(ViewHolder holder, final RedPackageVo redPackageVo) {
                                    Button btnSortView = holder.getView(R.id.btnSortView);
                                    btnSortView.setText(redPackageVo.getRedPackageTitle());

                                    btnSortView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            updataRedPackage(redPackageVo);
                                            dialog.dismiss();
                                        }
                                    });
                                }
                            });
                        }
                    });
                } else {
                    llRP.setVisibility(View.GONE);
                }
            }
        }, params);
    }


    private void updataRedPackage(RedPackageVo redPackageVo) {
        redPackagePrice = redPackageVo.getRedPackagePrice();
        redPackageId = redPackageVo.getRedPackageId();
        tvRPName.setText("-" + context.getResources().getString(R.string.money_rmb) + ShopHelper.getPriceString(redPackageVo.getRedPackagePrice()));
        setFollowFreight();
    }


    public void onEventMainThread(BuyStepBus buyStepBus) {
        if (buyStepBus.getMsg().equals(BuyStepBus.ADDRESSID)) {    //重新选择地址的回调
            trancelateStoreDataToUp();
            getFreight((Integer) buyStepBus.getObj());
        } else if (buyStepBus.getMsg().equals(BuyStepBus.FLAG_STOREPRICE)) {    //店铺通知更新显示
            HashMap<String, Object> map = (HashMap<String, Object>) buyStepBus.getObj();
            storeMoney.put((Integer) map.get(BuyStepBus.STOREID), (BuyStepPrice) map.get(BuyStepBus.ALL));
            refreshFollowFreight();
        } else if (buyStepBus.getMsg().equals(BuyStepBus.INVOICE)) {
            invoice = (String[]) buyStepBus.getObj();
            Log.d("invoice", "onEventMainThread: invoice = " + invoice);
            if (invoice == null) {
//                invoiceId = -1;
                tvAllowVatName.setText("不需要发票");
            } else {
//                invoiceId = invoice.getInvoiceId();
//                tvAllowVatName.setText(invoice.getInvoiceContent());
                tvAllowVatName.setText(invoice[0] + " " + invoice[1]);

            }
        }
    }

    public void onEventMainThread(Integer i) {
        Intent intent = new Intent(activity, OrderListActivity.class);
        intent.putExtra(OrderListActivity.STATE, "");
        startActivity(intent);
        finish();
    }

    //更改地址后需更改的实体
    private class StoreListItem {
        private int storeId;
        private List<BuyData> goodsList;

        public StoreListItem(int storeId, List<BuyData> goodsList) {
            this.storeId = storeId;
            this.goodsList = goodsList;
        }
    }

    private class AddressStore {
        private int addressId;
        private List<StoreListItem> storeList;

        public AddressStore(int addressId, List<StoreListItem> storeList) {
            this.addressId = addressId;
            this.storeList = storeList;
        }
    }


    //提交订单时需使用的实体
    private class BuyStepCommitBean {
        private int addressId;
        private String paymentTypeCode;  //支付方式(online–线上支付 offline–货到付款),必填
        private int isCart;  // 是否来源于购物车（1–是 0–否）,必填
        //        private int invoiceId;   //发票Id,选填，不使用时留空
        private int redPackageId;   //红包Id,选填，不使用时留空
        private List<StoreDiscount> storeList;
        private int isExistTrys; //是否含有拥有试用资格的商品
        private int isGroup; //是否是团购商品
        private int groupId; //拼团信息编号
        private int goId; //开团编号

        private int isExistBundling; //是否优惠套装

        public int getIsExistBundling() {
            return isExistBundling;
        }

        public void setIsExistBundling(int isExistBundling) {
            this.isExistBundling = isExistBundling;
        }

        private String invoiceTitle;//发票抬头
        private String invoiceContent;//发票内容

        public String getInvoiceTitle() {
            return invoiceTitle;
        }

        public void setInvoiceTitle(String invoiceTitle) {
            this.invoiceTitle = invoiceTitle;
        }

        public String getInvoiceContent() {
            return invoiceContent;
        }

        public void setInvoiceContent(String invoiceContent) {
            this.invoiceContent = invoiceContent;
        }

        public int getAddressId() {
            return addressId;
        }

        public void setAddressId(int addressId) {
            this.addressId = addressId;
        }

        public String getPaymentTypeCode() {
            return paymentTypeCode;
        }

        public void setPaymentTypeCode(String paymentTypeCode) {
            this.paymentTypeCode = paymentTypeCode;
        }

        public int getIsCart() {
            return isCart;
        }

        public void setIsCart(int isCart) {
            this.isCart = isCart;
        }

        public int getInvoiceId() {
            return invoiceId;
        }

//        public void setInvoiceId(int invoiceId) {
//            this.invoiceId = invoiceId;
//        }

        public int getRedPackageId() {
            return redPackageId;
        }

        public void setRedPackageId(int redPackageId) {
            this.redPackageId = redPackageId;
        }

        public List<StoreDiscount> getStoreList() {
            return storeList;
        }

        public void setStoreList(List<StoreDiscount> storeList) {
            this.storeList = storeList;
        }

        public int getIsExistTrys() {
            return isExistTrys;
        }

        public void setIsExistTrys(int isExistTrys) {
            this.isExistTrys = isExistTrys;
        }

        public int getIsGroup() {
            return isGroup;
        }

        public void setIsGroup(int isGroup) {
            this.isGroup = isGroup;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public int getGoId() {
            return goId;
        }

        public void setGoId(int goId) {
            this.goId = goId;
        }
    }

    private class StoreDiscount {
        private int storeId;  //店铺Id
        private String receiverMessage;
        private int conformId;  //满优惠活动Id
        private int voucherId; //优惠券Id,可为空
        private List<BuyData> goodsList;

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getReceiverMessage() {
            return receiverMessage;
        }

        public void setReceiverMessage(String receiverMessage) {
            this.receiverMessage = receiverMessage;
        }

        public int getConformId() {
            return conformId;
        }

        public void setConformId(int conformId) {
            this.conformId = conformId;
        }

        public int getVoucherId() {
            return voucherId;
        }

        public void setVoucherId(int voucherId) {
            this.voucherId = voucherId;
        }

        public List<BuyData> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<BuyData> goodsList) {
            this.goodsList = goodsList;
        }
    }
}
