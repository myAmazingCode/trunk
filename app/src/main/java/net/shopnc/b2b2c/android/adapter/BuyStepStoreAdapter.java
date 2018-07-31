package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.BuyGoodsItemVo;
import net.shopnc.b2b2c.android.bean.BuyGoodsSpuVo;
import net.shopnc.b2b2c.android.bean.BuyStepPrice;
import net.shopnc.b2b2c.android.bean.BuyStoreVo;
import net.shopnc.b2b2c.android.bean.CartBundlingVo;
import net.shopnc.b2b2c.android.bean.Conform;
import net.shopnc.b2b2c.android.bean.GoodGift;
import net.shopnc.b2b2c.android.bean.VoucherVo;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.ui.buy.BuyStepBus;
import net.shopnc.b2b2c.android.util.LoadImage;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Description 提交订单页storeAdapter
 * 1.优惠最优解：只记录选中的优惠的优惠金额
 * @Author qyf
 * <p>
 * Created 2016/5/5 11:21.
 */
public class BuyStepStoreAdapter extends RRecyclerAdapter<BuyStoreVo> {
    private String moneyRmb;
    private int areaId;

    private int isExistBundling; //是否优惠套装

    public int getIsExistBundling() {
        return isExistBundling;
    }

    public void setIsExistBundling(int isExistBundling) {
        this.isExistBundling = isExistBundling;
    }

    public BuyStepStoreAdapter(Context context) {
        super(context, R.layout.recyclerview_buystep_store_item);
        moneyRmb = context.getResources().getString(R.string.money_rmb);
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }


    @Override
    public void convert(final RecyclerHolder holder, final BuyStoreVo buyStoreVo) {
        final BuyStepPrice buyStepPrice = new BuyStepPrice();

        LinearLayout llspu = holder.getView(R.id.llSpu);
        llspu.removeAllViews();
        holder.setText(R.id.tvStoreName, buyStoreVo.getStoreName());

        //普通商品的绑定
        bindSpuData(llspu, buyStoreVo.getBuyGoodsSpuVoList());//绑定spu sku信息
        // 优惠套装商品的绑定,若有
        bindBundlingData(llspu, buyStoreVo.getCartBundlingVoList());

        final TextView tvVoucherName = holder.getView(R.id.tvVoucherName);//优惠券
        final TextView tvFreight = holder.getView(R.id.tvFreight);//运费
        final TextView tvConform = holder.getView(R.id.tvConform);//店铺优惠
        final TextView tvStoreAll = holder.getView(R.id.tvStoreAll);

        buyStepPrice.setStoreId(buyStoreVo.getStoreId());
        buyStepPrice.setFreight(buyStoreVo.getFreightAmount());
        buyStepPrice.setFreightOrigin(buyStepPrice.getFreight());
        buyStepPrice.setPriceGood(buyStoreVo.getBuyItemAmount());
        setStorePrice(tvStoreAll, tvFreight, buyStepPrice);

        sendPriceToBuyStep1Activity(buyStoreVo.getStoreId(), buyStepPrice);

        EditText etStoreMsg = holder.getView(R.id.etStoreMsg);//店铺留言
        getMsgAndSend(buyStoreVo.getStoreId(), etStoreMsg, buyStepPrice);

        /**
         * 优惠券和店铺优惠使用v7包兼容到2.X版本的MD风格dialog
         * 若有计算优惠问题再继续调、可记录当前选中为红色
         * 可将优惠券、店铺优惠、红包的选择继续封装
         */
        //优惠券
        final LinearLayout llStoreDiscount = holder.getView(R.id.llStoreDiscount);
        if (null != buyStoreVo.getVoucherVoList() && buyStoreVo.getVoucherVoList().size() > 0) {
            llStoreDiscount.setVisibility(View.VISIBLE);
            tvVoucherName.setText("请选择优惠券");
            llStoreDiscount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog dialog = new AlertDialog.Builder(context).create();
                    View view = LayoutInflater.from(context).inflate(R.layout.follow_bottom_dialog, null);
                    ListView listView = view.findViewById(R.id.llChoosens);
                    dialog.setView(view);
                    dialog.show();
                    listView.setAdapter(new CommonAdapter<VoucherVo>(context, buyStoreVo.getVoucherVoList(), R.layout.follow_botton_dialog_item) {
                        @Override
                        public void convert(ViewHolder holder, final VoucherVo voucherVo) {
                            Button btnSortView = holder.getView(R.id.btnSortView);
                            btnSortView.setText(voucherVo.getVoucherTitle() + ",满" + voucherVo.getLimitAmount() + "减" + voucherVo.getPrice() + ",有效时间" + voucherVo.getStartTime() + "至" + voucherVo.getEndTime());

                            btnSortView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    updataVoucherVoData(tvVoucherName, tvStoreAll, tvFreight, buyStoreVo.getStoreId(), voucherVo, buyStepPrice);
                                    dialog.dismiss();
                                }
                            });
                        }
                    });
                }
            });
        } else {
            llStoreDiscount.setVisibility(View.GONE);
        }

        //店铺优惠
        final LinearLayout llStore = holder.getView(R.id.llStore);
        if (null != buyStoreVo.getConformList() && buyStoreVo.getConformList().size() > 0) {
            llStore.setVisibility(View.VISIBLE);
            tvConform.setText("请选择店铺优惠");
            llStore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog dialog = new AlertDialog.Builder(context).create();
                    View view = LayoutInflater.from(context).inflate(R.layout.follow_bottom_dialog, null);
                    ListView listView = view.findViewById(R.id.llChoosens);
                    dialog.setView(view);
                    dialog.show();
                    listView.setAdapter(new CommonAdapter<Conform>(context, buyStoreVo.getConformList(), R.layout.follow_botton_dialog_item) {
                        @Override
                        public void convert(ViewHolder holder, final Conform conform) {
                            Button btnSortView = holder.getView(R.id.btnSortView);
                            btnSortView.setText(conform.getContentRule());

                            btnSortView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    updataConformData(tvFreight, tvConform, tvStoreAll, buyStoreVo.getStoreId(), conform, buyStepPrice);
                                    dialog.dismiss();
                                }
                            });
                        }
                    });
                }
            });
        } else {
            llStore.setVisibility(View.GONE);
        }

    }

    /**
     * 优惠套装商品的绑定
     *
     * @param llspu
     * @param cartBundlingVoList
     */
    private void bindBundlingData(LinearLayout llspu, List<CartBundlingVo> cartBundlingVoList) {
        for (int i = 0; i < cartBundlingVoList.size(); i++) {
            AddViewHolder bundlingHolder = new AddViewHolder(context, R.layout.buy_step1_bundling_header);
            //添加优惠套装的header
            llspu.addView(bundlingHolder.getCustomView());

            CartBundlingVo cartBundlingVo = cartBundlingVoList.get(i);
            int buyNum = cartBundlingVo.getBuyNum();
            bundlingHolder.setText(R.id.tvBundlingCount, "优惠套装x" + buyNum);//优惠套装数量
            bundlingHolder.setText(R.id.tvBundlingPrice, moneyRmb + ShopHelper.getPriceString(cartBundlingVo.getItemAmount()));//优惠套装价格

            List<CartBundlingVo.BuyBundlingItemVoListBean> buyBundlingItemVoList = cartBundlingVo.getBuyBundlingItemVoList();
            for (int j = 0; j < buyBundlingItemVoList.size(); j++) {
                final CartBundlingVo.BuyBundlingItemVoListBean goodsBean = buyBundlingItemVoList.get(j);
                AddViewHolder viewHolder = new AddViewHolder(context, R.layout.cart_bundling_item_goods_buy);
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
                llspu.addView(viewHolder.getCustomView());
            }
        }
    }

    /**
     * 普通商品的绑定
     *
     * @param llspu
     * @param buyStoreVos
     */
    private void bindSpuData(LinearLayout llspu, List<BuyGoodsSpuVo> buyStoreVos) {
        for (final BuyGoodsSpuVo buyGoodsSpuVo : buyStoreVos) {
            AddViewHolder spuHolder = new AddViewHolder(context, R.layout.recyclerview_buystep_spu_item);
            spuHolder.setImage(R.id.ivSpuImage, buyGoodsSpuVo.getImageSrc())
                    .setText(R.id.tvSpuName, buyGoodsSpuVo.getGoodsName());
            spuHolder.setOnClickListener(R.id.ivSpuImage, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShopHelper.gotoGoodDetailsActivity(context, buyGoodsSpuVo.getCommonId());
                }
            });
            spuHolder.setOnClickListener(R.id.tvSpuName, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShopHelper.gotoGoodDetailsActivity(context, buyGoodsSpuVo.getCommonId());
                }
            });
            LinearLayout llSkuItem = spuHolder.getView(R.id.llSkuItem);
            llSkuItem.removeAllViews();
            bindSkuData(llSkuItem, buyGoodsSpuVo.getBuyGoodsItemVoList());
            llspu.addView(spuHolder.getCustomView());
        }
    }

    private void bindSkuData(LinearLayout llSkuItem, List<BuyGoodsItemVo> buyGoodsItemVos) {
        for (BuyGoodsItemVo buyGoodsItemVo : buyGoodsItemVos) {
            AddViewHolder skuHolder = new AddViewHolder(context, R.layout.recyclerview_buystep_sku_item);
            skuHolder.setText(R.id.tvSkuSpec, Common.isEmpty(buyGoodsItemVo.getGoodsFullSpecs()) ? "无规格" : buyGoodsItemVo.getGoodsFullSpecs())
                    .setText(R.id.tvSkuNum, buyGoodsItemVo.getBuyNum() + buyGoodsItemVo.getUnitName())
                    .setText(R.id.tvSkuPrice, "单价：" + moneyRmb + ShopHelper.getPriceString(buyGoodsItemVo.getGoodsPrice()) + "/" + buyGoodsItemVo.getUnitName())
                    .setText(R.id.tvAllPrice, moneyRmb + ShopHelper.getPriceString(buyGoodsItemVo.getGoodsPrice().multiply(new BigDecimal(buyGoodsItemVo.getBuyNum()))));
            //有赠品的显示赠品
            LinearLayout llGift = skuHolder.getView(R.id.llGift);
            if (buyGoodsItemVo.getIsGift() == 1 && buyGoodsItemVo.getGiftVoList() != null) {
                skuHolder.setVisible(R.id.llGiftLayout, true);
                llGift.removeAllViews();
                for (GoodGift gift : buyGoodsItemVo.getGiftVoList()) {
                    AddViewHolder giftHolder = new AddViewHolder(context, R.layout.cart_gift_item);
                    giftHolder.setText(R.id.tvGiftName, gift.getCartShowText())
                            .setText(R.id.tvGiftNum, "x" + gift.getGiftNum());
                    llGift.addView(giftHolder.getCustomView());
                }
            } else {
                skuHolder.setVisible(R.id.llGiftLayout, false);
            }
            //试用情况显示
            LinearLayout llTryInfo = skuHolder.getView(R.id.llTryInfo);
            if (buyGoodsItemVo.getTrysPostUseState() == 0 && buyGoodsItemVo.getTrysSendUseState() == 0) {
                llTryInfo.setVisibility(View.GONE);
            } else if (buyGoodsItemVo.getTrysPostUseState() == 1) {
                llTryInfo.setVisibility(View.VISIBLE);
                skuHolder.setText(R.id.tvTryInfo, "只需付邮费即可购买1件试用商品");
            } else if (buyGoodsItemVo.getTrysSendUseState() == 1) {
                llTryInfo.setVisibility(View.VISIBLE);
                skuHolder.setText(R.id.tvTryInfo, "下单并提交试用报告可获得优惠券");
            }
            llSkuItem.addView(skuHolder.getCustomView());
        }
    }

    private void updataVoucherVoData(TextView tvVoucherName, TextView tvStoreAll, TextView tvFreight, int storeId, VoucherVo voucherVo, BuyStepPrice buyStepPrice) {
        tvVoucherName.setText(voucherVo.getVoucherTitle() + "     -" + moneyRmb + ShopHelper.getPriceString(voucherVo.getPrice()));
        buyStepPrice.setPriceVoucher(voucherVo.getPrice());
        buyStepPrice.setVoucherId(voucherVo.getVoucherId());

        setStorePrice(tvStoreAll, tvFreight, buyStepPrice);

        sendPriceToBuyStep1Activity(storeId, buyStepPrice);
    }

    /**
     * 计算店铺优惠后的总价
     * 1.满减
     * 2.满包邮
     * 3.满减包邮部分地区不包邮
     * 4.送券
     */
    private void updataConformData(TextView tvFreight, TextView tvConform, TextView tvStoreAll, int storeId, Conform conform, BuyStepPrice buyStepPrice) {
        buyStepPrice.setPriceConform(conform.getConformPrice());
        buyStepPrice.setConformId(conform.getConformId());

        buyStepPrice.setFreight(buyStepPrice.getFreightOrigin());   //每次点击都恢复初始邮费
        if (conform.getIsFreeFreight() == 1) {//包邮
            buyStepPrice.setFreight(new BigDecimal(0));

            //切割不包邮地区
            if (Common.isNotEmpty(conform.getRuleOutAreaIds())) {
                List<String> areas = Arrays.asList(conform.getRuleOutAreaIds().split(","));
                if (areas.contains(String.valueOf(areaId))) {
                    buyStepPrice.setFreight(buyStepPrice.getFreightOrigin());    //不包邮，恢复初始邮费
                }
            }
        }

        //送券后期补充
        //TODO


        tvConform.setText(conform.getContentRule());
        setStorePrice(tvStoreAll, tvFreight, buyStepPrice);

        sendPriceToBuyStep1Activity(storeId, buyStepPrice);
    }

    /**
     * 设置店铺运费及总金额显示
     *
     * @param tvStoreAll   总金额
     * @param tvFreight    运费
     * @param buyStepPrice 店铺价格对象
     */
    private void setStorePrice(TextView tvStoreAll, TextView tvFreight, BuyStepPrice buyStepPrice) {
        tvFreight.setText(buyStepPrice.getFreight().equals(new BigDecimal(0)) ? "0.00" : (moneyRmb + ShopHelper.getPriceString(buyStepPrice.getFreight())));
        String price = ShopHelper.getPriceString(buyStepPrice.getPriceGood().add(buyStepPrice.getFreight()).subtract(buyStepPrice.getPriceConform()).subtract(buyStepPrice.getPriceVoucher()));
        tvStoreAll.setText(Html.fromHtml("小计（含运费）：<font color=\"#ED5968\">" + price + "</font>元"));
    }

    /**
     * 发消息给BuyStep1Activity，更新显示
     *
     * @param storeId      店铺id
     * @param buyStepPrice 店铺传递对象
     */
    private void sendPriceToBuyStep1Activity(int storeId, BuyStepPrice buyStepPrice) {
        HashMap<String, Object> storePrice = new HashMap<>();
        storePrice.put(BuyStepBus.STOREID, storeId);
        storePrice.put(BuyStepBus.ALL, buyStepPrice);
        EventBus.getDefault().post(new BuyStepBus(BuyStepBus.FLAG_STOREPRICE, storePrice));
    }

    private void getMsgAndSend(final int storeId, EditText etStoreMsg, final BuyStepPrice buyStepPrice) {
        etStoreMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!Common.isEmpty(s.toString())) {
                    buyStepPrice.setReceiverMessage(s.toString());
                    sendPriceToBuyStep1Activity(storeId, buyStepPrice);
                }
            }
        });
    }

}
