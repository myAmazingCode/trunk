package net.shopnc.b2b2c.android.ui.home;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseFragment;
import net.shopnc.b2b2c.android.base.EventObj;
import net.shopnc.b2b2c.android.bean.CartBundlingVo;
import net.shopnc.b2b2c.android.bean.CartItemVo;
import net.shopnc.b2b2c.android.bean.CartSpuVo;
import net.shopnc.b2b2c.android.bean.CartStoreVo;
import net.shopnc.b2b2c.android.bean.GoodGift;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.ui.good.GoodBusBean;
import net.shopnc.b2b2c.android.ui.good.GoodHelper;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.LoadImage;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.xrefresh.XRefreshView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartShowFragment extends BaseFragment {

    @Bind(R.id.llCartStore)
    LinearLayout llCartStore;
    @Bind(R.id.ivCartAll)
    TextView ivCartAll;
    @Bind(R.id.tvAllPrice)
    TextView tvAllPrice;
    @Bind(R.id.tvBuy)
    TextView tvBuy;
    @Bind(R.id.xrvCart)
    XRefreshView xrvCart;

    private String moneyRmb;
    private String storeSortString;
    private CartStoreState storeState;
    private String cartAllString;
    private List<CartStoreVo> cartStoreVoList;
    private boolean isNumEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_cart_show, container, false);
        ButterKnife.bind(this, viewInflate);
        EventBus.getDefault().register(this);
        moneyRmb = context.getResources().getString(R.string.money_rmb);
        storeSortString = context.getResources().getString(R.string.cart_store_all);
        cartAllString = context.getResources().getString(R.string.cart_all);

        xrvCart.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                EventBus.getDefault().post(new EventObj(EventObj.CARTREFRESH));
                //刷新购物车tab中的数量
                EventBus.getDefault().post(new EventObj(GoodBusBean.REFRESH_CART_COUNT));
                xrvCart.stopRefresh();
            }
        });

        return viewInflate;
    }

    //显示店铺信息
    private void showStoreList(final CartStoreVo cartStoreVo) {
        final AddViewHolder holderStore = new AddViewHolder(context, R.layout.cart_store_list_item);
        holderStore.setText(R.id.tvStoreName, cartStoreVo.getStoreName());
        TextView tvStoreAll = holderStore.getView(R.id.tvStoreAll);
//        tvStoreAll.setText(String.format(storeSortString, 0, 0, "0.00"));//状态改变、数量改变均要重新设置
        Spanned spanned = Html.fromHtml("共 0 种 0 件 | " + "<font color=\"#f23030\">" + moneyRmb + " 0.00</font>");
        tvStoreAll.setText(spanned);//状态改变、数量改变均要重新设置
        storeState.setStoreSortString(storeSortString);
        //店铺选择设置
        holderStore.setSelected(R.id.btnSelectStore, false);
        final ImageView btnSelectStore = holderStore.getView(R.id.btnSelectStore);
        storeState.putStore(cartStoreVo.getStoreId(), btnSelectStore, tvStoreAll);
        //选中店铺
        holderStore.setOnClickListener(R.id.btnSelectStore, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSelectStore.setSelected(!btnSelectStore.isSelected());
                storeState.setStoreState(cartStoreVo.getStoreId(), btnSelectStore.isSelected());
            }
        });

        //店铺跳转
        holderStore.setOnClickListener(R.id.tvStoreName, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopHelper.gotoStoreActivity(context, cartStoreVo.getStoreId());
            }
        });
        holderStore.setOnClickListener(R.id.storeImg, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopHelper.gotoStoreActivity(context, cartStoreVo.getStoreId());
            }
        });

        //创建cartHelper对象
        CartHelper cartHelper = new CartHelper();
        // 领券
        cartHelper.setVourcher(context, holderStore, cartStoreVo);
        // 满优惠显示
        cartHelper.setConfirm(context, holderStore, cartStoreVo);

        LinearLayout llspu = holderStore.getView(R.id.llCartSpuList);
        llspu.removeAllViews();
        for (int i = 0; i < cartStoreVo.getCartSpuVoList().size(); i++) {
            llspu.addView(spuView(cartStoreVo.getStoreId(), cartStoreVo.getCartSpuVoList().get(i)));
        }

        llCartStore.addView(holderStore.getCustomView());

        /**
         * 显示每个优惠套装cartBundlingVoList.get(i)
         */
        List<CartBundlingVo> cartBundlingVoList = cartStoreVo.getCartBundlingVoList();
        LinearLayout llBundlingList = holderStore.getView(R.id.llBundlingList);
        for (int i = 0; cartBundlingVoList != null && i < cartBundlingVoList.size(); i++) {
            final CartBundlingVo cartBundlingVo = cartBundlingVoList.get(i);
            final AddViewHolder viewHolder = new AddViewHolder(context, R.layout.cart_bundling_item);

            //购买数量
            viewHolder.setText(R.id.tvAppCommonCount, String.valueOf(cartBundlingVo.getBuyNum()));

            //购买数量减
            viewHolder.setOnClickListener(R.id.btnAppCommonMinus, new View.OnClickListener() {  //减
                @Override
                public void onClick(View v) {
                    if (cartBundlingVo.getBuyNum() > 1) {
                        bundlingNumEdit(viewHolder, cartBundlingVo, cartBundlingVo.getBuyNum() - 1);
                    } else {
                        TToast.showShort(context, "购买数量最小为1");
                    }
                }
            });

            //数量加
            viewHolder.setOnClickListener(R.id.btnAppCommonAdd, new View.OnClickListener() {   //加
                @Override
                public void onClick(View v) {
                    bundlingNumEdit(viewHolder, cartBundlingVo, cartBundlingVo.getBuyNum() + 1);
                }
            });


            //checkbox事件
            final ImageView btnSkuSelect = viewHolder.getView(R.id.btnSkuSelect);
            btnSkuSelect.setSelected(false);

            final int storeId = cartBundlingVo.getStoreId();
            final int bundlingId = cartBundlingVo.getBundlingId();
            final int cartId = cartBundlingVo.getCartId();
            //记录优惠套装的状态
            storeState.putSpu(storeId, bundlingId, btnSkuSelect, 0);
            List<CartBundlingVo.BuyBundlingItemVoListBean> buyBundlingItemVoList = cartBundlingVo.getBuyBundlingItemVoList();

            //sku数量=num*size
            int size = buyBundlingItemVoList.size();
            int buyNum = cartBundlingVo.getBuyNum();
            //storeId->bundlingId->cartId：完成类似普通商品spu和sku的一对一关系
            storeState.putSku(bundlingId, cartId, btnSkuSelect, new BigDecimal(ShopHelper.getPriceString(cartBundlingVo.getGoodsPrice())), buyNum, null, "", null, null, size);

            //选中优惠套装
            btnSkuSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnSkuSelect.setSelected(!btnSkuSelect.isSelected());
                    storeState.setSpuState(storeId, bundlingId, btnSkuSelect.isSelected());
                }
            });

            llBundlingList.addView(viewHolder.getCustomView());


            //显示优惠套装中的每个商品
            for (int j = 0; j < size; j++) {
                CartBundlingVo.BuyBundlingItemVoListBean buyBundlingItemVoListBean = buyBundlingItemVoList.get(j);
                llBundlingList.addView(getBundlingItem(buyBundlingItemVoListBean));
            }
        }
    }

    /**
     * 优惠套装购买数量修改
     *
     * @param holderSku
     * @param bundlingVo
     * @param num
     */
    private void bundlingNumEdit(final AddViewHolder holderSku, final CartBundlingVo bundlingVo, final int num) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("cartId", bundlingVo.getCartId() + "");
        params.put("buyNum", num + "");
        params.put("clientType", "android");
        if (Common.isEmpty(application.getToken()))
            params.put("cartData", application.getCartData());


        String url = ConstantUrl.URL_CART_EDIT;


        OkHttpUtil.postAsyn(getActivity(), url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                isNumEdit = true;
                holderSku.setText(R.id.tvAppCommonCount, String.valueOf(num));
                bundlingVo.setBuyNum(num);
                application.setCartData(JsonUtil.toString(data, "cartData"));
                storeState.updateSkuBuyNum(bundlingVo.getBundlingId(), bundlingVo.getCartId(), num);
                storeState.setSkuState(bundlingVo.getStoreId(), bundlingVo.getBundlingId(), bundlingVo.getCartId(), true);
            }
        }, params);
    }

    /**
     * 获取优惠套装中的单个商品
     *
     * @param goodsBean
     * @return view
     */
    private View getBundlingItem(final CartBundlingVo.BuyBundlingItemVoListBean goodsBean) {
        AddViewHolder viewHolder = new AddViewHolder(context, R.layout.cart_bundling_item_goods);
        ImageView ivGoodsImage = viewHolder.getView(R.id.ivGoodsImage);
        LoadImage.loadRemoteImg(context, ivGoodsImage, goodsBean.getImageSrc());
        viewHolder.setText(R.id.tvGoodsName, goodsBean.getGoodsName());
        TextView tvGoodsSpec = viewHolder.getView(R.id.tvGoodsSpec);
        tvGoodsSpec.setText(goodsBean.getGoodsFullSpecs());
        TextView tvGoodsPrice = viewHolder.getView(R.id.tvGoodsPrice);
        String price = moneyRmb + ShopHelper.getPriceString(goodsBean.getAppPrice0());
        Spanned spanned = Html.fromHtml(price + "<font color=\"#7F7F7F\">/" + goodsBean.getUnitName() + "</font>");
        tvGoodsPrice.setText(spanned);

        View llGift = viewHolder.getView(R.id.llGift);
        List<CartBundlingVo.BuyBundlingItemVoListBean.GiftVoListBean> giftVoList = goodsBean.getGiftVoList();
        if (giftVoList != null && giftVoList.size() != 0) {
            llGift.setVisibility(View.VISIBLE);
            TextView tvGiftName = viewHolder.getView(R.id.tvGiftName);
            CartBundlingVo.BuyBundlingItemVoListBean.GiftVoListBean giftVoListBean = giftVoList.get(0);
            tvGiftName.setText(giftVoListBean.getGoodsName());
            TextView tvGiftNum = viewHolder.getView(R.id.tvGiftNum);
            tvGiftNum.setText(giftVoListBean.getGoodsFullSpecs() + "x" + giftVoListBean.getGiftNum());
        } else {
            llGift.setVisibility(View.GONE);
        }

        View customView = viewHolder.getCustomView();
        customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //商品详情页
                ShopHelper.gotoGoodDetailsActivityFromCart(context, goodsBean.getCommonId());
            }
        });
        return customView;
    }

    /**
     * 普通商品列表单个商品添加cartSpuVoList.get(i)
     *
     * @param storeId
     * @param cartSpuVo
     * @return
     */
    private View spuView(final int storeId, final CartSpuVo cartSpuVo) {
        final AddViewHolder holderSpu = new AddViewHolder(context, R.layout.cart_spu_list_item);
        holderSpu.setImage(R.id.ivGoodsImage, cartSpuVo.getImageSrc());
        holderSpu.setText(R.id.tvGoodsName, cartSpuVo.getGoodsName());

        holderSpu.setSelected(R.id.btnSpuSelect, false);
        final ImageView btnSpuSelect = holderSpu.getView(R.id.btnSpuSelect);
        //混批模式的商品添加最低起批量
        storeState.putSpu(storeId, cartSpuVo.getCommonId(), btnSpuSelect, cartSpuVo.getGoodsModal() == 2 ? cartSpuVo.getBatchNum0() : 0);
        btnSpuSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSpuSelect.setSelected(!btnSpuSelect.isSelected());
                storeState.setSpuState(storeId, cartSpuVo.getCommonId(), btnSpuSelect.isSelected());
            }
        });

        holderSpu.setOnClickListener(R.id.ivGoodsImage, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopHelper.gotoGoodDetailsActivityFromCart(context, cartSpuVo.getCommonId());
            }
        });
        holderSpu.setOnClickListener(R.id.tvGoodsName, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopHelper.gotoGoodDetailsActivityFromCart(context, cartSpuVo.getCommonId());
            }
        });

        LinearLayout llSku = holderSpu.getView(R.id.skuList);
        llSku.removeAllViews();
        for (CartItemVo cartItemVo : cartSpuVo.getCartItemVoList()) {
            llSku.addView(skuView(cartItemVo));
        }
        return holderSpu.getCustomView();
    }


    /**
     * 单个普通商品规格的添加cartItemVoList.get(i)
     *
     * @param cartItemVo
     * @return
     */
    private View skuView(final CartItemVo cartItemVo) {
        final AddViewHolder holderSku = new AddViewHolder(context, R.layout.cart_sku_list_item_finish);
        holderSku.setText(R.id.skuSpec, Common.isEmpty(cartItemVo.getGoodsFullSpecs()) ? "无规格" : cartItemVo.getGoodsFullSpecs());
        holderSku.setText(R.id.tvAppCommonCount, String.valueOf(cartItemVo.getBuyNum()));

        //有显示折扣的促销  默认未选中状态显示最高价
        TextView skuPrice = holderSku.getView(R.id.skuPrice);
        skuPrice.setText(CartHelper.getPromotionString(context, moneyRmb, ShopHelper.getPriceString(cartItemVo.getAppPrice0()), cartItemVo.getAppUsable() == 1 ? ShopHelper.getPriceString(cartItemVo.getGoodsPrice0()) : "", cartItemVo.getUnitName()), TextView.BufferType.SPANNABLE);
        //有赠品的显示赠品
        LinearLayout llGift = holderSku.getView(R.id.llGift);
        List<GoodGift> giftVoList = cartItemVo.getGiftVoList();
        if (cartItemVo.getIsGift() == 1 && giftVoList != null && giftVoList.size() != 0) {
            llGift.setVisibility(View.VISIBLE);
            llGift.removeAllViews();
            for (GoodGift gift : giftVoList) {
                AddViewHolder giftHolder = new AddViewHolder(context, R.layout.cart_gift_item);
                giftHolder.setText(R.id.tvGiftName, gift.getCartShowText())
                        .setText(R.id.tvGiftNum, "x" + gift.getGiftNum());
                llGift.addView(giftHolder.getCustomView());
            }
        } else {
            llGift.setVisibility(View.GONE);
        }

        holderSku.setSelected(R.id.btnSkuSelect, false);
        final ImageView btnSkuSelect = holderSku.getView(R.id.btnSkuSelect);
        TextView tvBatchNum = holderSku.getView(R.id.tvBatchNum);//不够混批量的显示
        tvBatchNum.setVisibility(View.INVISIBLE);
        if (cartItemVo.getGoodsModal() == 2) {
            storeState.putSku(cartItemVo.getCommonId(), cartItemVo.getCartId(), btnSkuSelect, new BigDecimal(GoodHelper.getSinglePrice(cartItemVo, cartItemVo.getSpuBuyNum())), cartItemVo.getBuyNum(), tvBatchNum, cartItemVo.getUnitName(), skuPrice, cartItemVo, CartStoreState.Sku.FACTOR_DEFAULT);
        } else {
            storeState.putSku(cartItemVo.getCommonId(), cartItemVo.getCartId(), btnSkuSelect, new BigDecimal(GoodHelper.getSinglePrice(cartItemVo, cartItemVo.getSpuBuyNum())), cartItemVo.getBuyNum(), null, "", null, null, CartStoreState.Sku.FACTOR_DEFAULT);
        }

        btnSkuSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSkuSelect.setSelected(!btnSkuSelect.isSelected());
                storeState.setSkuState(cartItemVo.getStoreId(), cartItemVo.getCommonId(), cartItemVo.getCartId(), btnSkuSelect.isSelected());
            }
        });

        holderSku.setOnClickListener(R.id.btnAppCommonMinus, new View.OnClickListener() {  //减
            @Override
            public void onClick(View v) {
                if (cartItemVo.getBuyNum() > 1) {
                    commonNumEdit(holderSku, cartItemVo, cartItemVo.getBuyNum() - 1);
                } else {
                    TToast.showShort(context, "购买数量最小为1");
                }
            }
        });
        holderSku.setOnClickListener(R.id.btnAppCommonAdd, new View.OnClickListener() {   //加
            @Override
            public void onClick(View v) {
                commonNumEdit(holderSku, cartItemVo, cartItemVo.getBuyNum() + 1);
            }
        });

        holderSku.setOnClickListener(R.id.skuSpec, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopHelper.gotoGoodDetailsActivityFromCart(context, cartItemVo.getCommonId());
            }
        });

        return holderSku.getCustomView();
    }

    public static final String TAG = CartShowFragment.class.getSimpleName();

    /**
     * 数量增加
     *
     * @param holderSku
     * @param cartItemVo
     * @param num
     */
    private void commonNumEdit(final AddViewHolder holderSku, final CartItemVo cartItemVo, final int num) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("cartId", cartItemVo.getCartId() + "");
        params.put("buyNum", num + "");
        params.put("clientType", "android");
        if (Common.isEmpty(application.getToken()))
            params.put("cartData", application.getCartData());


        String url = ConstantUrl.URL_CART_EDIT;


        OkHttpUtil.postAsyn(getActivity(), url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                isNumEdit = true;
                holderSku.setText(R.id.tvAppCommonCount, String.valueOf(num));
                cartItemVo.setBuyNum(num);
                application.setCartData(JsonUtil.toString(data, "cartData"));
                storeState.updateSkuBuyNum(cartItemVo.getCommonId(), cartItemVo.getCartId(), num);
                storeState.setSkuState(cartItemVo.getStoreId(), cartItemVo.getCommonId(), cartItemVo.getCartId(), true);
            }
        }, params);
    }

    @OnClick({R.id.ivCartAll, R.id.tvBuy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivCartAll:
                ivCartAll.setSelected(!ivCartAll.isSelected());
                storeState.setCartState(ivCartAll.isSelected());
                break;
            case R.id.tvBuy:
                if (tvBuy.isSelected() && ShopHelper.isLogin(context)) {
                    storeState.buy(application.getToken());
                }
                break;
        }
    }

    // 接收EventBus传递的数据
    public void onEventMainThread(EventObj event) {
        llCartStore.removeAllViews();
        if (event.getFlag().equals(EventObj.CARTDATA)) {
            storeState = new CartStoreState();
            storeState.putCart(context, ivCartAll, tvAllPrice, tvBuy, cartAllString);
            cartStoreVoList = (List<CartStoreVo>) event.getObj();
            for (int i = 0; i < cartStoreVoList.size(); i++) {
                showStoreList(cartStoreVoList.get(i));
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden && isNumEdit) {
            EventBus.getDefault().post(new EventObj(EventObj.CARTDATA, cartStoreVoList));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

}
