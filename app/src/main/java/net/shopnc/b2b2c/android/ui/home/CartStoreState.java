package net.shopnc.b2b2c.android.ui.home;

import android.content.Context;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.BuyData;
import net.shopnc.b2b2c.android.bean.CartItemVo;
import net.shopnc.b2b2c.android.common.LinkedMultiValueMap;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.ui.good.GoodHelper;
import net.shopnc.b2b2c.android.util.Constants;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @Description XXX
 * @Author qyf
 * <p>
 * Created 2016/8/18 14:47.
 */
public class CartStoreState {
    private HashMap<Integer, Store> storeList;
    private LinkedMultiValueMap<Integer, Spu> spuList;
    private LinkedMultiValueMap<Integer, Sku> skuList;
    private LinkedMultiValueMap<Integer, Sku> skuTouchList; //批发模式下点击过的sku列表
    private String storeSortString;
    private Cart cart;
    private String cartString;
    private Context context;
    private HashMap<Integer, BuyData> buydatas;
    private String moneyRmb;


    public CartStoreState() {
        this.storeList = new HashMap<>();
        this.spuList = new LinkedMultiValueMap<>();
        this.skuList = new LinkedMultiValueMap<>();
        this.buydatas = new HashMap<>();
        this.skuTouchList = new LinkedMultiValueMap<>();
    }

    public void putCart(Context context, TextView cartBtn, TextView cartTvAll, TextView cartBuy, String cartString) {
        cart = new Cart(cartBtn, cartTvAll, cartBuy);
        this.cartString = cartString;
        this.context = context;
        moneyRmb = context.getResources().getString(R.string.money_rmb);
        cartBtn.setSelected(false);
        if (cartTvAll != null) {  //编辑状态传空
            cartBuy.setSelected(false);
            setCartAllString();
        }
    }

    public void setStoreSortString(String storeSortString) {
        this.storeSortString = storeSortString;
    }

    public void putStore(int storeId, ImageView storeBtn, TextView storeTvAll) {
        storeList.put(storeId, new Store(storeId, storeBtn, storeTvAll));
    }

    /**
     * 单个记录普通商品或优惠套装
     *
     * @param storeId
     * @param spuId
     * @param spuBtn
     * @param batchNum
     */
    public void putSpu(int storeId, int spuId, ImageView spuBtn, int batchNum) {
        spuList.put(storeId, new Spu(spuId, spuBtn, batchNum));
    }

    /**
     * 记录普通商品的规格
     *
     * @param spuId
     * @param skuId
     * @param skuBtn
     * @param skuPrice
     * @param num
     * @param tvBatchNum
     * @param unitName
     * @param tvSkuPrice
     * @param cartItemVo
     */
    public void putSku(int spuId, int skuId, ImageView skuBtn, BigDecimal skuPrice, int num, TextView tvBatchNum, String unitName, TextView tvSkuPrice, CartItemVo cartItemVo, int bundlingFactor) {
        skuList.put(spuId, new Sku(skuId, skuBtn, skuPrice, num, tvBatchNum, unitName, tvSkuPrice, cartItemVo, bundlingFactor));
    }

    public void setCartAllString() {
        if (cart.cartTvAll == null) {//edit状态
            return;
        }
        BigDecimal price = new BigDecimal(0);
        for (Store store : storeList.values()) {
            price = price.add(store.storePriceAll);
        }
        String s = cartString + ShopHelper.getPriceString(price) + " (不含运费)";
        int position = s.indexOf(moneyRmb);
        int position2 = s.indexOf(" (不含运费)");
        SpannableString spannableString = new SpannableString(s);
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.refund_money_name), 0, position, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.refund_money_num), position, position + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.buystep_all_red_big), position + 1, position2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.refund_money_name), position2 + 1, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        cart.cartTvAll.setText(spannableString, TextView.BufferType.SPANNABLE);
        cart.cartBuy.setSelected(!price.equals(new BigDecimal(0)));
    }

    public void setCartState(boolean b) {
        for (Store store : storeList.values()) {
            setStoreState(store.storeId, b);
        }
        setCartAllString();
    }

    public void setStoreState(int storeId, boolean b) {
        storeList.get(storeId).storeBtn.setSelected(b);
        for (Spu spu : spuList.getValues(storeId)) {
            setSpuState(storeId, spu.spuId, b);
        }
        setStoreTvAll(storeId);
        checkCartBtn();
        setCartAllString();
    }

    public void setSpuState(int storeId, int spuId, boolean b) {
        for (Spu spu : spuList.getValues(storeId)) {
            if (spu.spuId == spuId) {
                if (spu.batchNum > 0) { //混批模式
                    if (getSkuBuyNum(spu.spuId) < spu.batchNum) {  //未达到起批量
                        spu.spuBtn.setSelected(false);
                        for (Sku sku : skuList.getValues(spu.spuId)) {
                            sku.skuBtn.setSelected(false);
                            sku.tvBatchNum.setText(spu.batchNum + sku.unitName + "起批");
                            sku.tvBatchNum.setVisibility(View.VISIBLE);
                        }
                    } else {  //达到起批量
                        spu.spuBtn.setSelected(b);
                        for (Sku sku : skuList.getValues(spu.spuId)) {
                            sku.skuBtn.setSelected(b);
                            sku.tvBatchNum.setVisibility(View.INVISIBLE);
                        }
                    }

                } else {  //零售模式
                    spu.spuBtn.setSelected(b);
                    for (Sku sku : skuList.getValues(spu.spuId)) {
                        sku.skuBtn.setSelected(b);
                    }
                }
            }
        }
        checkStore(storeId);
        setStoreTvAll(storeId);
        checkCartBtn();
        setCartAllString();
    }

    public void setSkuState(int storeId, int spuId, int skuId, boolean b) {
        Spu spu = getSpu(storeId, spuId);
        for (Sku sku : skuList.getValues(spuId)) {
            if (sku.skuId == skuId) {
                if (spu.batchNum > 0) { //批发模式
                    skuTouchList.put(spuId, sku);
                    if (b) {
                        if (getSkuTouchBuyNum(spuId) < spu.batchNum) {
                            spu.spuBtn.setSelected(false);
                            sku.skuBtn.setSelected(false);
                            sku.tvBatchNum.setText(spu.batchNum + sku.unitName + "起批");
                            sku.tvBatchNum.setVisibility(View.VISIBLE);
                            checkSku(storeId, spuId);
                        } else {
                            for (Sku s : skuTouchList.getValues(spuId)) {
                                s.skuBtn.setSelected(true);
                                s.tvBatchNum.setVisibility(View.INVISIBLE);
                            }
                        }
                    } else {
                        sku.skuBtn.setSelected(false);
                        sku.tvBatchNum.setText(spu.batchNum + sku.unitName + "起批");
                        sku.tvBatchNum.setVisibility(View.VISIBLE);
                        checkSku(storeId, spuId);
                    }

                } else {
                    sku.skuBtn.setSelected(b);
                }
            }
        }
        checkSpu(storeId, spuId);
        checkStore(storeId);
        setStoreTvAll(storeId);
        checkCartBtn();
        setCartAllString();
    }

    public void checkCartBtn() {
        for (Store store : storeList.values()) {
            if (store.storeBtn.isSelected() == true) {
                cart.cartBtn.setSelected(true);
            } else {
                cart.cartBtn.setSelected(false);
                return;
            }
        }
    }

    public void checkStore(int storeId) {
        for (Spu spu : spuList.getValues(storeId)) {
            if (spu.spuBtn.isSelected() == true) {
                storeList.get(storeId).storeBtn.setSelected(true);
            } else {
                storeList.get(storeId).storeBtn.setSelected(false);
                storeList.get(storeId).storePriceAll = new BigDecimal(0);
                return;
            }
        }
    }

    public void checkSpu(int storeId, int spuId) {
        Spu spu = getSpu(storeId, spuId);
        for (Sku sku : skuList.getValues(spuId)) {
            if (sku.skuBtn.isSelected() == true) {
                spu.spuBtn.setSelected(true);
            } else {
                spu.spuBtn.setSelected(false);
                return;
            }
        }
    }

    public void checkSku(int storeId, int spuId) {
        Spu spu = getSpu(storeId, spuId);
        int num = 0;
        for (Sku sku : skuList.getValues(spuId)) {
            if (sku.skuBtn.isSelected()) {
                num += sku.num;
            }
        }
        if (num < spu.batchNum) {
            for (Sku sku : skuList.getValues(spuId)) {
                sku.tvBatchNum.setText(spu.batchNum + sku.unitName + "起批");
                sku.tvBatchNum.setVisibility(View.VISIBLE);
                sku.skuBtn.setSelected(false);
            }
        }
    }

    public Spu getSpu(int storeId, int spuId) {
        for (Spu spu : spuList.getValues(storeId)) {
            if (spu.spuId == spuId) {
                return spu;
            }
        }
        return null;
    }

    public void updateSkuBuyNum(int spuId, int skuId, int buyNum) {
        for (Sku sku : skuList.getValues(spuId)) {
            if (sku.skuId == skuId)
                sku.num = buyNum;
        }
    }

    public int getSkuBuyNum(int spuId) {
        int numAll = 0;
        for (Sku sku : skuList.getValues(spuId)) {
            numAll += sku.num;
        }
        return numAll;
    }

    public int getSkuTouchBuyNum(int spuId) {
        int numAll = 0;
        for (Sku sku : skuTouchList.getValues(spuId)) {
            numAll += sku.num;
        }
        return numAll;
    }

    public void setStoreTvAll(int storeId) {
        int spuNum = 0, skuNum = 0;
        BigDecimal priceAll = new BigDecimal(0);
        for (Spu spu : spuList.getValues(storeId)) {
            if (spu.batchNum > 0) {
                int numSkuAll = 0;
                for (Sku sku : skuList.getValues(spu.spuId)) {
                    if (sku.skuBtn.isSelected()) {
                        numSkuAll += sku.num;
                    }
                }
                for (Sku sku : skuList.getValues(spu.spuId)) {
                    sku.skuPrice = new BigDecimal(GoodHelper.getSinglePrice(sku.cartItemVo, numSkuAll));
                    sku.tvSkuPrice.setText(CartHelper.getPromotionString(context, moneyRmb, GoodHelper.getSinglePrice(sku.cartItemVo, numSkuAll), sku.cartItemVo.getAppUsable() == 1 ? GoodHelper.getOriginPrice(sku.cartItemVo, numSkuAll) : "", sku.cartItemVo.getUnitName()), TextView.BufferType.SPANNABLE);
                }
            }
        }
        for (Spu spu : spuList.getValues(storeId)) {
            for (Sku sku : skuList.getValues(spu.spuId)) {
                if (sku.skuBtn.isSelected()) {
                    spuNum += sku.bundlingFactor;
                    skuNum += sku.num * sku.bundlingFactor;
                    priceAll = priceAll.add(sku.skuPrice.multiply(new BigDecimal(sku.num)));
                }
            }
        }
        if (storeList.get(storeId).storeTvAll == null) {//edit状态
            return;
        }
        storeList.get(storeId).storePriceAll = priceAll;
        Spanned spanned = Html.fromHtml("共 " + spuNum + " 种 " + skuNum + " 件 | " + "<font color=\"#f23030\">" + moneyRmb + " " + ShopHelper.getPriceString(priceAll) + "</font>");
        storeList.get(storeId).storeTvAll.setText(spanned);
//        storeList.get(storeId).storeTvAll.setText(String.format(storeSortString, spuNum, skuNum, ShopHelper.getPriceString(priceAll)));
    }

    private int isExistBundling;//是否含有优惠套装

    public void initBuyDatas() {
        //重置为false
        isExistBundling = Constants.NO;
        for (Sku sku : skuList.values()) {
            if (sku.skuBtn.isSelected()) {
                BuyData buyData = new BuyData(sku.skuId, sku.num);
                buydatas.put(sku.skuId, buyData);//选中列表
                Log.d(TAG, "initBuyDatas: buyData = " + buyData);

                if (sku.bundlingFactor != 1) {
                    //任意一个不为1，则包含优惠套装
                    isExistBundling = Constants.YES;
                }
            }

            Log.d(TAG, "initBuyDatas: sku = " + sku);
        }

    }

    public static final String TAG = CartStoreState.class.getSimpleName();

    public void buy(String token) {
        initBuyDatas();
        String buydatas = new Gson().toJson(this.buydatas.values());
        GoodHelper.buyNow(context, token, buydatas, Constants.YES, Constants.NO, 0, 0, isExistBundling);
    }

    public void delete(MyShopApplication application) {
        initBuyDatas();
        if (buydatas.size() == 0) {
            TToast.showShort(context, "请选择要移出的商品");
            return;
        }
        CartHelper.delete(application, buydatas);
    }

    static class Sku {
        public int skuId;//使用商品的cartId
        public ImageView skuBtn;
        public BigDecimal skuPrice;
        public int num;
        public TextView tvBatchNum;
        public String unitName;
        public TextView tvSkuPrice;
        public CartItemVo cartItemVo;
        public int bundlingFactor;//普通商品默认为1，若为优惠套装需与num乘机
        public static final int FACTOR_DEFAULT = 1;//普通商品常量1

        public Sku(int skuId, ImageView skuBtn, BigDecimal skuPrice, int num, TextView tvBatchNum, String unitName, TextView tvSkuPrice, CartItemVo cartItemVo, int bundlingFactor) {
            this.skuId = skuId;
            this.skuBtn = skuBtn;
            this.skuPrice = skuPrice;
            this.num = num;
            this.tvBatchNum = tvBatchNum;
            this.unitName = unitName;
            this.tvSkuPrice = tvSkuPrice;
            this.cartItemVo = cartItemVo;
            this.bundlingFactor = bundlingFactor;
        }

        @Override
        public String toString() {
            return "Sku{" +
                    "skuId=" + skuId +
                    ", skuBtn=" + skuBtn +
                    ", skuPrice=" + skuPrice +
                    ", num=" + num +
                    ", tvBatchNum=" + tvBatchNum +
                    ", unitName='" + unitName + '\'' +
                    ", tvSkuPrice=" + tvSkuPrice +
                    ", cartItemVo=" + cartItemVo +
                    ", bundlingFactor=" + bundlingFactor +
                    '}';
        }
    }

    static class Spu {
        public int spuId;
        public ImageView spuBtn;
        public int batchNum;

        public Spu(int spuId, ImageView spuBtn, int batchNum) {
            this.spuId = spuId;
            this.spuBtn = spuBtn;
            this.batchNum = batchNum;
        }
    }

    static class Store {
        public int storeId;
        public ImageView storeBtn;
        public TextView storeTvAll;
        public BigDecimal storePriceAll = new BigDecimal(0);

        public Store(int storeId, ImageView storeBtn, TextView storeTvAll) {
            this.storeId = storeId;
            this.storeBtn = storeBtn;
            this.storeTvAll = storeTvAll;
        }
    }

    static class Cart {
        public TextView cartBtn;
        public TextView cartTvAll;
        public TextView cartBuy;

        public Cart(TextView cartBtn, TextView cartTvAll, TextView cartBuy) {
            this.cartBtn = cartBtn;
            this.cartTvAll = cartTvAll;
            this.cartBuy = cartBuy;
        }
    }
}
