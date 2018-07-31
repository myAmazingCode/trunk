package net.shopnc.b2b2c.android.custom.dialog;

import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.BookBean;
import net.shopnc.b2b2c.android.bean.BuyData;
import net.shopnc.b2b2c.android.bean.GoodDetailVo;
import net.shopnc.b2b2c.android.bean.Goods;
import net.shopnc.b2b2c.android.bean.SpecJsonVo;
import net.shopnc.b2b2c.android.bean.SpecValueListVo;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.custom.SelectedGoodsPopwin;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.ui.buy.BuyStepBook1Activity;
import net.shopnc.b2b2c.android.ui.good.GoodBusBean;
import net.shopnc.b2b2c.android.ui.good.GoodHelper;
import net.shopnc.b2b2c.android.ui.good.PreGoods;
import net.shopnc.b2b2c.android.ui.home.HomeLoadDataHelper;
import net.shopnc.b2b2c.android.ui.im.IMDetailsActivity;
import net.shopnc.b2b2c.android.ui.mine.LoginActivity;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.Constants;
import net.shopnc.b2b2c.android.util.LoadImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description 商品详情页弹出规格选择页面
 * @Author qyf
 * <p>
 * Created 2016/4/22 13:40.
 */
public class GoodDetailsSpecDialog extends Dialog {

    @Bind(R.id.ivSelectedGoodsImg)
    ImageView ivSelectedGoodsImg;
    @Bind(R.id.tvGoodsName)
    TextView tvGoodsName;
    @Bind(R.id.tvSelectedPrice)
    TextView tvSelectedPrice;
    @Bind(R.id.llSpec)
    LinearLayout llSpec;
    @Bind(R.id.tvSpecName0)
    TextView tvSpecName0;
    @Bind(R.id.rvSpecList0)
    FlexboxLayout rvSpecList0;
    @Bind(R.id.llSpec0)
    LinearLayout llSpec0;
    @Bind(R.id.tvSpecName1)
    TextView tvSpecName1;
    @Bind(R.id.rvSpecList1)
    FlexboxLayout rvSpecList1;
    @Bind(R.id.llSpec1)
    LinearLayout llSpec1;
    @Bind(R.id.tvSpecName2)
    TextView tvSpecName2;
    @Bind(R.id.rvSpecList2)
    FlexboxLayout rvSpecList2;
    @Bind(R.id.llSpec2)
    LinearLayout llSpec2;
    @Bind(R.id.tvAllNumPrice)
    TextView tvAllNumPrice;
    @Bind(R.id.btnBuy)
    Button btnBuy;
    @Bind(R.id.btnAddCart)
    Button btnAddCart;
    @Bind(R.id.btnArrivalNotice)
    Button btnArrivalNotice;
    @Bind(R.id.llBuyCart)
    LinearLayout llBuyCart;
    @Bind(R.id.btnAppCommonMinus)
    Button btnAppCommonMinus;
    @Bind(R.id.tvAppCommonCount)
    TextView tvAppCommonCount;
    @Bind(R.id.btnAppCommonAdd)
    Button btnAppCommonAdd;
    @Bind(R.id.btnEditSelectedList)
    Button btnEditSelectedList;
    @Bind(R.id.tvSkuStorage)
    TextView tvSkuStorage;
    @Bind(R.id.tvPrePrice)
    TextView tvPrePrice;
    //加折扣
    @Bind(R.id.tvPromotionType)
    TextView tvPromotionType;

    @Bind(R.id.llPreBottom)
    LinearLayout llPreBottom;
    @Bind(R.id.imID)
    TextView imID;
    @Bind(R.id.showCartLayoutID)
    TextView showCartLayoutID;
    @Bind(R.id.btnBuyPre)
    Button btnBuyPre;

    private Context context;
    private GoodDetailVo goodDetail;

    private Goods selectedGoods;
    private TextView selectedSpecTv0;
    private TextView selectedSpecTv1;
    private TextView selectedSpecTv2;
    private int selectedSpec0;
    private int selectedSpec1;
    private int selectedSpec2;
    private BookBean selectedBook = null;

    private HashMap<Integer, PreGoods> preGoodsMap;
    private HashMap<Integer, BuyData> buydatas;
    private int allGoodsNum;
    private String moneyRmb;
    private String allNumPrice;

    private MyShopApplication application;
    private int addAndMinusCount = 1;

    public GoodDetailsSpecDialog(Context context, GoodDetailVo goodDetail, HashMap<Integer, PreGoods> preGoodsMap, Goods selectedGoods, int allGoodsNum) {
        super(context, R.style.CommonDialog);
        this.context = context;
        this.goodDetail = goodDetail;
        this.selectedGoods = selectedGoods;
        this.preGoodsMap = preGoodsMap;
        this.allGoodsNum = allGoodsNum;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.gooddetails_spec_dialog);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        //初始化合计数字符串
        allNumPrice = context.getResources().getString(R.string.goods_details_spec_select_all);
        moneyRmb = context.getResources().getString(R.string.money_rmb);
        application = MyShopApplication.getInstance();

        //加载默认选中商品图
        LoadImage.loadRemoteImg(context, ivSelectedGoodsImg, selectedGoods.getImageSrc());
        tvGoodsName.setText(goodDetail.getGoodsName());
        tvSkuStorage.setText("库存:" + selectedGoods.getGoodsStorage() + goodDetail.getUnitName());
        //根据数量价格显示
        if (goodDetail.getAppUsable() == 1 && goodDetail.getPromotionType() == 3) {
            selectedBook = goodDetail.getBook();
            tvSelectedPrice.setText(moneyRmb + ShopHelper.getPriceString(selectedBook.getDownPayment()));
            llBuyCart.setVisibility(View.GONE);
            llPreBottom.setVisibility(View.VISIBLE);
        } else {
            llBuyCart.setVisibility(View.VISIBLE);
            llPreBottom.setVisibility(View.GONE);
            tvSelectedPrice.setText(moneyRmb + GoodHelper.getPriceStringAllShow(goodDetail, allGoodsNum));
        }
        if (goodDetail.getGoodsModal() == 2) {//批发模式显示左边计数
            updatePriceStringShow();
        }
        setArrivalNoticeShow(goodDetail.getGoodsModal());

        switchSpecBygoodsSpecNameList(goodDetail.getGoodsSpecNameList().size());
        switchCreateNumByGoodsModal(goodDetail.getGoodsModal());

        discountController();

        buydatas = new HashMap<>();
    }

    //弹出规格列表的折扣显示
    private void discountController() {
        if (goodDetail.getAppUsable() == 1 && goodDetail.getPromotionType() == 1 && goodDetail.getDiscount() != null) {
            tvPromotionType.setVisibility(View.VISIBLE);
            tvPromotionType.setText(goodDetail.getPromotionTypeText());
        } else if (goodDetail.getAppUsable() == 1 && goodDetail.getPromotionType() == 3) {
            tvPromotionType.setVisibility(View.VISIBLE);
            tvPromotionType.setText(goodDetail.getPromotionTypeText());
        }
    }

    //不同模式下初始化规格的显示
    private void switchCreateNumByGoodsModal(int goodsModal) {
        switch (goodsModal) {
            case 1://零售
                btnEditSelectedList.setVisibility(View.GONE);
                addAndMinusCount = allGoodsNum;
                break;
            case 2://批发
                btnEditSelectedList.setVisibility(View.VISIBLE);
                if (preGoodsMap.containsKey(selectedGoods.getGoodsId())) {
                    addAndMinusCount = preGoodsMap.get(selectedGoods.getGoodsId()).getCount();
                } else {
                    addAndMinusCount = 0;
                }
                break;
        }
        tvAppCommonCount.setText(addAndMinusCount + "");
    }

    //不同模式下切换规格的数量显示
    private void switchSpecChangeByGoodsModal(int goodsModal) {
        switch (goodsModal) {
            case 1://零售
                break;
            case 2://批发
                if (preGoodsMap.containsKey(selectedGoods.getGoodsId())) {
                    addAndMinusCount = preGoodsMap.get(selectedGoods.getGoodsId()).getCount();
                } else {
                    addAndMinusCount = 0;
                }
                tvAppCommonCount.setText(addAndMinusCount + "");
                break;
        }
    }

    //不同模式下切换规格的价格显示
    private void switchPriceChangeByGoodsModal(int goodsModal) {
        switch (goodsModal) {
            case 1://零售
                if (selectedBook != null) {
                    tvSelectedPrice.setText(moneyRmb + ShopHelper.getPriceString(selectedBook.getDownPayment()));
                } else {
                    tvSelectedPrice.setText(moneyRmb + ShopHelper.getPriceString(selectedGoods.getAppPrice0()));
                }
                break;
            case 2://批发
                break;
        }
    }

    //不同模式下减数量的点击后的数量显示
    private void switchNumMinByGoodsModal(int goodsModal) {
        switch (goodsModal) {
            case 1://零售
                if (addAndMinusCount < 1) {
                    addAndMinusCount = 1;
                }
                allGoodsNum = addAndMinusCount;
                break;
            case 2://批发
                if (addAndMinusCount < 0) {
                    addAndMinusCount = 0;
                    preGoodsMap.remove(selectedGoods.getGoodsId());
                } else {
                    preGoodsMap.put(selectedGoods.getGoodsId(),
                            new PreGoods(selectedGoods.getGoodsId(), addAndMinusCount, selectedGoods.getGoodsSpecString()));
                }
                updatePriceStringShow();

                break;
        }

    }

    //不同模式下加数量的点击后的数量显示
    private void switchNumAddByGoodsModal(int goodsModal) {
        switch (goodsModal) {
            case 1://零售
                allGoodsNum = addAndMinusCount;
                break;
            case 2://批发
                preGoodsMap.put(selectedGoods.getGoodsId(),
                        new PreGoods(selectedGoods.getGoodsId(), addAndMinusCount, selectedGoods.getGoodsSpecString()));
                updatePriceStringShow();
                break;
        }
    }

    private void updatePriceStringShow() {
        allGoodsNum = 0;
        for (PreGoods preGoods : preGoodsMap.values()) {
            allGoodsNum += Integer.valueOf(preGoods.getCount());
        }

        if (allGoodsNum == 0) {
            tvAllNumPrice.setText("");
            return;
        }

        //计算单品价格并显示
        String singlePrice = GoodHelper.getSinglePrice(goodDetail, allGoodsNum);
        tvSelectedPrice.setText(moneyRmb + singlePrice);
        double total = Double.valueOf(singlePrice) * allGoodsNum;
        //设置总价的显示
        String allNumPriceString = String.format(allNumPrice, allGoodsNum, goodDetail.getUnitName(), total);
        String[] allNumPriceStrings = allNumPriceString.split(",");
        tvAllNumPrice.setText(Html.fromHtml("<font color=\"#555555\">" + allNumPriceStrings[0] + "</font><font color=\"#DE5765\">" + allNumPriceStrings[1] + "</font>"));
    }

    //规格参数选择
    private void switchSpecBygoodsSpecNameList(int size) {
        switch (size) {
            case 1:
                llSpec0.setVisibility(View.VISIBLE);
                llSpec1.setVisibility(View.GONE);
                llSpec2.setVisibility(View.GONE);
                selectedSpec0 = 0;
                selectedSpec1 = Integer.MAX_VALUE;
                selectedSpec2 = Integer.MAX_VALUE;
                bindSpecByLlSpec(llSpec0);
                break;
            case 2:
                llSpec0.setVisibility(View.VISIBLE);
                llSpec1.setVisibility(View.VISIBLE);
                llSpec2.setVisibility(View.GONE);
                selectedSpec0 = 0;
                selectedSpec1 = 0;
                selectedSpec2 = Integer.MAX_VALUE;
                bindSpecByLlSpec(llSpec0, llSpec1);
                break;
            case 3:
                llSpec0.setVisibility(View.VISIBLE);
                llSpec1.setVisibility(View.VISIBLE);
                llSpec2.setVisibility(View.VISIBLE);
                selectedSpec0 = 0;
                selectedSpec1 = 0;
                selectedSpec2 = 0;
                bindSpecByLlSpec(llSpec0, llSpec1, llSpec2);
                break;
            default:
                llSpec.setVisibility(View.GONE);
                break;
        }
    }


    private void bindSpecByLlSpec(LinearLayout... linearLayouts) {
        for (LinearLayout ll : linearLayouts) {
            if (ll.equals(llSpec0)) {
                final SpecJsonVo specJson = goodDetail.getSpecJson().get(0);
                tvSpecName0.setText(specJson.getSpecName() + "：");
                selectedSpec0 = Integer.valueOf(selectedGoods.getSpecValueIds().split(",")[0]);
                updateSelectedGoodsId();
                for (final SpecValueListVo specValueListVo : specJson.getSpecValueList()) {
                    AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.recyclerview_item_one_button);
                    final TextView btnSpec = addViewHolder.getView(R.id.btnSpec);
                    btnSpec.setText(specValueListVo.getSpecValueName());
                    if (selectedSpec0 == specValueListVo.getSpecValueId()) {
                        selectedSpecTv0 = btnSpec;
                        btnSpec.setActivated(true);
                    } else {
                        btnSpec.setActivated(false);
                    }
                    btnSpec.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!btnSpec.isActivated()) {
                                selectedSpecTv0.setActivated(false);
                                selectedSpecTv0 = btnSpec;
                                selectedSpecTv0.setActivated(true);
                                selectedSpec0 = specValueListVo.getSpecValueId();
                                updateSelectedGoodsId();
                            }
                        }
                    });
                    rvSpecList0.addView(addViewHolder.getCustomView());
                }
            } else if (ll.equals(llSpec1)) {
                final SpecJsonVo specJson = goodDetail.getSpecJson().get(1);
                tvSpecName1.setText(specJson.getSpecName() + "：");
                selectedSpec1 = Integer.valueOf(selectedGoods.getSpecValueIds().split(",")[1]);
                updateSelectedGoodsId();
                for (final SpecValueListVo specValueListVo : specJson.getSpecValueList()) {
                    AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.recyclerview_item_one_button);
                    final TextView btnSpec = addViewHolder.getView(R.id.btnSpec);
                    btnSpec.setText(specValueListVo.getSpecValueName());
                    if (selectedSpec1 == specValueListVo.getSpecValueId()) {
                        selectedSpecTv1 = btnSpec;
                        btnSpec.setActivated(true);
                    } else {
                        btnSpec.setActivated(false);
                    }
                    btnSpec.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!btnSpec.isActivated()) {
                                selectedSpecTv1.setActivated(false);
                                selectedSpecTv1 = btnSpec;
                                selectedSpecTv1.setActivated(true);
                                selectedSpec1 = specValueListVo.getSpecValueId();
                                updateSelectedGoodsId();
                            }
                        }
                    });
                    rvSpecList1.addView(addViewHolder.getCustomView());
                }
            } else if (ll.equals(llSpec2)) {
                final SpecJsonVo specJson = goodDetail.getSpecJson().get(2);
                tvSpecName2.setText(specJson.getSpecName() + "：");
                selectedSpec2 = Integer.valueOf(selectedGoods.getSpecValueIds().split(",")[2]);
                updateSelectedGoodsId();
                for (final SpecValueListVo specValueListVo : specJson.getSpecValueList()) {
                    AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.recyclerview_item_one_button);
                    final TextView btnSpec = addViewHolder.getView(R.id.btnSpec);
                    btnSpec.setText(specValueListVo.getSpecValueName());
                    if (selectedSpec2 == specValueListVo.getSpecValueId()) {
                        selectedSpecTv2 = btnSpec;
                        btnSpec.setActivated(true);
                    } else {
                        btnSpec.setActivated(false);
                    }
                    btnSpec.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!btnSpec.isActivated()) {
                                selectedSpecTv2.setActivated(false);
                                selectedSpecTv2 = btnSpec;
                                selectedSpecTv2.setActivated(true);
                                selectedSpec2 = specValueListVo.getSpecValueId();
                                updateSelectedGoodsId();
                            }
                        }
                    });
                    rvSpecList2.addView(addViewHolder.getCustomView());
                }
            }
        }
    }

    //更改规则后获得当前选中规格商品信息
    private void updateSelectedGoodsId() {
        String specValue = selectedSpec0 + "";
        if (selectedSpec1 != Integer.MAX_VALUE) {
            specValue += "," + selectedSpec1;
            if (selectedSpec2 != Integer.MAX_VALUE) {
                specValue += "," + selectedSpec2;
            }
        }
        for (Goods goods : goodDetail.getGoodsList()) {
            if (goods.getSpecValueIds().equals(specValue)) {
                selectedGoods = goods;
                if (goodDetail.getAppUsable() == 1 && goodDetail.getPromotionType() == 3) {
                    for (BookBean bookBean : goodDetail.getBookList()) {
                        if (bookBean.getGoodsId() == selectedGoods.getGoodsId()) {
                            selectedBook = bookBean;
                            tvPrePrice.setText("定金：" + moneyRmb + ShopHelper.getPriceString(selectedBook.getDownPayment()) + " + 尾款：" + moneyRmb + ShopHelper.getPriceString(selectedBook.getFinalPayment()));
                        }
                    }
                }
                setArrivalNoticeShow(goodDetail.getGoodsModal());
                LoadImage.loadRemoteImg(context, ivSelectedGoodsImg, selectedGoods.getImageSrc());
                tvSkuStorage.setText("库存:" + selectedGoods.getGoodsStorage() + goodDetail.getUnitName());
                EventBus.getDefault().post(new GoodBusBean(GoodBusBean.SelectedGoods, selectedGoods));
                switchPriceChangeByGoodsModal(goodDetail.getGoodsModal());
                switchSpecChangeByGoodsModal(goodDetail.getGoodsModal());
                break;
            }
        }
    }

    private void setArrivalNoticeShow(int goodsModal) {
        switch (goodsModal) {
            case 1:
                if (selectedGoods.getGoodsStorage() == 0) {
                    btnBuy.setVisibility(View.GONE);
                    btnAddCart.setVisibility(View.GONE);
                    btnArrivalNotice.setVisibility(View.VISIBLE);
                } else {
                    btnBuy.setVisibility(View.VISIBLE);
                    btnAddCart.setVisibility(View.VISIBLE);
                    btnArrivalNotice.setVisibility(View.GONE);
                }
                break;
            case 2:
                int num = 0;
                for (Goods goods : goodDetail.getGoodsList()) {
                    num += goods.getGoodsStorage();
                }
                if (num == 0) {
                    btnBuy.setVisibility(View.GONE);
                    btnAddCart.setVisibility(View.GONE);
                    btnArrivalNotice.setVisibility(View.VISIBLE);
                } else {
                    btnBuy.setVisibility(View.VISIBLE);
                    btnAddCart.setVisibility(View.VISIBLE);
                    btnArrivalNotice.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().post(new GoodBusBean(GoodBusBean.GoodNum, allGoodsNum));
        EventBus.getDefault().post(new GoodBusBean(GoodBusBean.GoodPreHashMap, preGoodsMap));
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.tvOut)
    public void tvOutClick() {
        dismiss();
    }


    //数量减
    @OnClick(R.id.btnAppCommonMinus)
    public void btnAppCommonMinusClick() {
        addAndMinusCount = Integer.valueOf(Common.getText(tvAppCommonCount));
        addAndMinusCount -= 1;
        switchNumMinByGoodsModal(goodDetail.getGoodsModal());
        tvAppCommonCount.setText(addAndMinusCount + "");
    }

    //数量加
    @OnClick(R.id.btnAppCommonAdd)
    public void btnAppCommonAddClick() {
        addAndMinusCount = Integer.valueOf(Common.getText(tvAppCommonCount).trim());

        if (addAndMinusCount < selectedGoods.getGoodsStorage()) {
            addAndMinusCount += 1;
            switchNumAddByGoodsModal(goodDetail.getGoodsModal());
            tvAppCommonCount.setText(addAndMinusCount + "");
        }
    }


    @OnClick(R.id.btnEditSelectedList)
    public void onClick() {
        if (preGoodsMap.size() == 0) {
            TToast.showShort(context, "请选择商品");
        } else {
            SelectedGoodsPopwin selectedGoodsPopwin = new SelectedGoodsPopwin(context, preGoodsMap, goodDetail.getUnitName());
            selectedGoodsPopwin.show(btnBuy);
        }
    }

    public void onEventMainThread(GoodBusBean goodBusBean) {
        if (goodBusBean.getFlag().equals(GoodBusBean.GoodDelete)) {
            List<Integer> goodsIds = (ArrayList<Integer>) goodBusBean.getObj();
            for (int i : goodsIds) {
                preGoodsMap.remove(i);
            }
            updatePriceStringShow();
        }
    }

    @OnClick({R.id.btnBuy, R.id.btnAddCart, R.id.btnArrivalNotice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBuy:
                if (application.getToken().equals("")) {
                    Intent i = new Intent(context, LoginActivity.class);
                    context.startActivity(i);
                    return;
                }
                translatePreHashMapToBuyData();
                switchBuyByGoodsModal(goodDetail.getGoodsModal());
                break;
            case R.id.btnAddCart:
                translatePreHashMapToBuyData();
                switchAddCartByGoodsModal(goodDetail.getGoodsModal());
                break;
            case R.id.btnArrivalNotice:
                if (application.getToken().equals("")) {
                    Intent i = new Intent(context, LoginActivity.class);
                    context.startActivity(i);
                    return;
                }
                translatePreHashMapToBuyData();
                switchArrivalNoticeByGoodsModal(goodDetail.getGoodsModal());
                break;
        }
    }


    private void translatePreHashMapToBuyData() {
        buydatas.clear();
        for (PreGoods preGoods : preGoodsMap.values()) {
            buydatas.put(preGoods.getGoodsId(), new BuyData(preGoods.getGoodsId(), preGoods.getCount()));
        }
    }

    private void switchArrivalNoticeByGoodsModal(int goodsModal) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("commonId", selectedGoods.getCommonId() + "");

        switch (goodsModal) {
            case 1://零售
                params.put("goodsId", selectedGoods.getGoodsId() + "");
                break;
            case 2:
                break;
        }

        OkHttpUtil.postAsyn(context,ConstantUrl.URL_ARRIVAL_NOTICE, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                TToast.showShort(context, "若商品在" + JsonUtil.toString(data, "arrivalNoticeMaxTime") + "天内到货\n我们会通过邮件、短信和手机客户端来通知您哦~");
            }
        }, params);
    }

    //不同模式下立即购买
    private void switchBuyByGoodsModal(int goodsModal) {
        if (goodDetail.getGoodsStatus() == 0) {
            TToast.showShort(context, "当前商品暂无法购买");
            return;
        }

        switch (goodsModal) {
            case 1://零售
                buydatas.put(selectedGoods.getGoodsId(), new BuyData(selectedGoods.getGoodsId(), addAndMinusCount));
                GoodHelper.buyNow(context, application.getToken(), new Gson().toJson(buydatas.values()), Constants.NO, Constants.NO, 0, 0);
                dismiss();
                break;
            case 2://批发
                if (preGoodsMap.size() == 0) {
                    TToast.showShort(context, "请先选择商品");
                    break;
                }
                if (allGoodsNum >= goodDetail.getBatchNum0()) {
                    GoodHelper.buyNow(context, application.getToken(), new Gson().toJson(buydatas.values()), Constants.NO, Constants.NO, 0, 0);
                    dismiss();
                    break;
                } else {
                    TToast.showShort(context, "商品总数未达到最低起购量，" + goodDetail.getBatchNum0() + goodDetail.getUnitName() + "起购");
                    break;
                }
        }
    }

    //不同模式下加购物车
    private void switchAddCartByGoodsModal(int goodsModal) {
        if (goodDetail.getGoodsStatus() == 0) {
            TToast.showShort(context, "当前商品暂无法加入购物车");
            return;
        }

        switch (goodsModal) {
            case 1://零售
                buydatas.put(selectedGoods.getGoodsId(), new BuyData(selectedGoods.getGoodsId(), addAndMinusCount));
                GoodHelper.addCart(context, application, buydatas.values());
                dismiss();
                break;
            case 2://批发
                if (preGoodsMap.size() == 0) {
                    TToast.showShort(context, "请先选择商品");
                    break;
                }
                if (allGoodsNum >= goodDetail.getBatchNum0()) {
                    GoodHelper.addCart(context, application, buydatas.values());
                    dismiss();
                    break;
                } else {
                    TToast.showShort(context, "商品总数未达到最低起购量，" + goodDetail.getBatchNum0() + goodDetail.getUnitName() + "起购");
                    break;
                }
        }
    }

    @OnClick({R.id.imID, R.id.showCartLayoutID, R.id.btnBuyPre})
    public void onPreClick(View view) {
        switch (view.getId()) {
            case R.id.imID:
                if (ShopHelper.isLogin(context)) {
                    ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    // 将文本内容放到系统剪贴板里。
                    cm.setText(ConstantUrl.URL_WEB_GOODS + goodDetail.getCommonId());
                    Intent intent = new Intent(context, IMDetailsActivity.class);
                    intent.putExtra("sid", goodDetail.getStoreId());
                    intent.putExtra("gid", goodDetail.getCommonId());
                    intent.putExtra("flag", "details");
                    context.startActivity(intent);
                }
                break;
            case R.id.showCartLayoutID:
                HomeLoadDataHelper.doClick(context, "cart", null);
                break;
            case R.id.btnBuyPre:
                if (application.getToken().equals("")) {
                    Intent i = new Intent(context, LoginActivity.class);
                    context.startActivity(i);
                    return;
                }
                translatePreHashMapToBuyData();
                if (goodDetail.getGoodsStatus() == 0) {
                    TToast.showShort(context, "当前商品暂无法购买");
                    return;
                }
                buydatas.put(selectedGoods.getGoodsId(), new BuyData(selectedGoods.getGoodsId(), addAndMinusCount));
                String url = ConstantUrl.URL_BUY_BOOK_STEP1;
                HashMap<String, String> params = new HashMap<>();
                params.put("token", application.getToken());
                params.put("buyData", new Gson().toJson(buydatas.values()));
                params.put("clientType", "android");
                OkHttpUtil.postAsyn(context,url, new BeanCallback<String>() {
                    @Override
                    public void response(String data) {
                        Intent intent = new Intent(context, BuyStepBook1Activity.class);
                        intent.putExtra(BuyStepBook1Activity.DATA, data);
                        context.startActivity(intent);
                    }
                }, params);
                dismiss();
                break;
        }
    }

}
