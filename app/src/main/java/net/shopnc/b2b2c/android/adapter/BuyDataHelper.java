package net.shopnc.b2b2c.android.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.Gravity;
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
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.ui.buy.BuyStepBus;
import net.shopnc.b2b2c.android.util.LoadImage;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * ght (c) 2007-2017 ShopNC Inc. All rights reserved.
 *
 * @author lzz
 *         Created 2017/5/23 17:11
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * @description BuyStep1商品数据帮助类，仿adapter；使用LinearLayout添加数据，helper类
 */
public class BuyDataHelper {

    private String moneyRmb;
    private int areaId;
    private Context context;
    private List<BuyStoreVo> datas;
    private LinearLayout llStoreData;
    private List<Integer> noGoodsList;//无货商品列表

    public void setNoGoodsList(List<Integer> noGoodsList) {
        this.noGoodsList = noGoodsList;
    }

    /**
     * 设置数据源
     *
     * @param datas
     */
    public void setDatas(List<BuyStoreVo> datas) {
        this.datas = datas;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public BuyDataHelper(Context context, LinearLayout llStoreData) {
        this.context = context;
        this.llStoreData = llStoreData;
        moneyRmb = context.getResources().getString(R.string.money_rmb);
    }

    /**
     * 处理数据
     */
    public void process() {
        //遍历所有店铺
        for (int i = 0; i < datas.size(); i++) {
            //构建ViewHolder
            AddViewHolder holder = new AddViewHolder(context, R.layout.recyclerview_buystep_store_item);
            //添加到容器中，布局文件
            llStoreData.addView(holder.getCustomView());

            final BuyStoreVo buyStoreVo = datas.get(i);
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
                tvVoucherName.setText("不使用优惠");
                llStoreDiscount.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showVouchers(context, buyStoreVo, tvVoucherName, tvStoreAll, tvFreight, buyStepPrice);
                    }
                });
            } else {
                llStoreDiscount.setVisibility(View.GONE);
            }

            //店铺优惠
            final LinearLayout llStore = holder.getView(R.id.llStore);
            if (null != buyStoreVo.getConformList() && buyStoreVo.getConformList().size() > 0) {
                llStore.setVisibility(View.VISIBLE);
                tvConform.setText("不使用优惠");
                llStore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        showConform(buyStoreVo, tvFreight, tvConform, tvStoreAll, buyStepPrice);
                        showConform(context, buyStoreVo, tvFreight, tvConform, tvStoreAll, buyStepPrice);
                    }
                });
            } else {
                llStore.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 显示优惠券
     */
    private void showVouchers(Context context, final BuyStoreVo buyStoreVo, final TextView tvVoucherName, final TextView tvStoreAll, final TextView tvFreight, final BuyStepPrice buyStepPrice) {
        View view = LayoutInflater.from(context).inflate(R.layout.buy_voucher_dialog, null);
        final Dialog dialog = new Dialog(context, R.style.CommonDialog);
        dialog.setContentView(view);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, 1200);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        //活动描述
        TextView tvDesc = view.findViewById(R.id.tvDesc);
        tvDesc.setText("选择店铺优惠券");

        //当前使用的Voucher
        final String selectedVoucher = tvVoucherName.getText().toString();

        final ListView listView = view.findViewById(R.id.listView);
        List<VoucherVo> voucherVoList = buyStoreVo.getVoucherVoList();
        if (voucherVoList.get(0) != null) {
            voucherVoList.add(0, null);//添加到第一个，不使用优惠
        }
        CommonAdapter adapter = new CommonAdapter<VoucherVo>(context, voucherVoList, R.layout.buy_voucher_list_item) {
            @Override
            public void convert(ViewHolder holder, final VoucherVo voucherVo) {
                final TextView tvAmount = holder.getView(R.id.tvAmount);
                if (voucherVo == null) {
                    tvAmount.setText("不使用优惠");
                    holder.setText(R.id.tvDesc, "");
                } else {
                    double doubleValue = voucherVo.getLimitAmount().doubleValue();
                    final String priceString = ShopHelper.getPriceString(voucherVo.getPrice().doubleValue());
                    tvAmount.setText("面额" + moneyRmb + priceString);
                    holder.setText(R.id.tvDesc, "满" + ShopHelper.getPriceString(doubleValue) + "元可用");
                }

                //选中状态设置
                final View ivSelect = holder.getView(R.id.ivSelect);
                ivSelect.setSelected(false);//强制取消选中
                if (voucherVo != null && selectedVoucher.contains(ShopHelper.getPriceString(voucherVo.getPrice().doubleValue()))) {
                    ivSelect.setSelected(true);
                }

                if (voucherVo == null && selectedVoucher.equals("不使用优惠")) {
                    ivSelect.setSelected(true);
                }

                //点击选中
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!ivSelect.isSelected()) {
                            for (int i = 0; i < listView.getChildCount(); i++) {
                                View child = listView.getChildAt(i).findViewById(R.id.ivSelect);
                                //选中当前，并设置显示
                                child.setSelected(ivSelect == child);

                                if (voucherVo == null) {
                                    tvVoucherName.setText("不使用优惠");
                                } else {
                                    tvVoucherName.setText("使用店铺优惠券省" + ShopHelper.getPriceString(voucherVo.getPrice().doubleValue()));
                                }
                            }
                            dialog.dismiss();
                            updataVoucherVoData(tvStoreAll, tvFreight, buyStoreVo.getStoreId(), voucherVo, buyStepPrice);
                        }
                    }
                });
            }
        };
        listView.setAdapter(adapter);

        dialog.show();
    }

    /**
     * 显示满优惠
     */
    private void showConform(Context context, final BuyStoreVo buyStoreVo, final TextView tvFreight, final TextView tvConform, final TextView tvStoreAll, final BuyStepPrice buyStepPrice) {
        View view = LayoutInflater.from(context).inflate(R.layout.buy_voucher_dialog, null);
        final Dialog dialog = new Dialog(context, R.style.CommonDialog);
        dialog.setContentView(view);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, 1200);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        //活动描述
        TextView tvDesc = view.findViewById(R.id.tvDesc);
        tvDesc.setText("选择满优惠活动");

        //当前使用的Conform
        final String selectedConform = tvConform.getText().toString();

        final ListView listView = view.findViewById(R.id.listView);
        List<Conform> conforms = buyStoreVo.getConformList();
        if (conforms.get(0) != null) {
            conforms.add(0, null);//添加到第一个，不使用优惠
        }

        CommonAdapter adapter = new CommonAdapter<Conform>(context, conforms, R.layout.buy_voucher_list_item) {
            @Override
            public void convert(ViewHolder holder, final Conform conform) {
                final TextView tvAmount = holder.getView(R.id.tvAmount);
                if (conform == null) {
                    tvAmount.setText("不使用优惠");
                    holder.setText(R.id.tvDesc, "");
                } else {
                    double doubleValue = conform.getLimitAmount().doubleValue();
                    tvAmount.setText(conform.getShortRule());
                    holder.setText(R.id.tvDesc, "满" + ShopHelper.getPriceString(doubleValue) + "元可用");
                }

                //选中状态设置
                final View ivSelect = holder.getView(R.id.ivSelect);
                ivSelect.setSelected(false);//强制取消选中
                if (conform != null && selectedConform.contains(conform.getShortRule())) {
                    ivSelect.setSelected(true);
                }

                if (conform == null && selectedConform.equals("不使用优惠")) {
                    ivSelect.setSelected(true);
                }

                //点击选中
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!ivSelect.isSelected()) {
                            for (int i = 0; i < listView.getChildCount(); i++) {
                                View child = listView.getChildAt(i).findViewById(R.id.ivSelect);
                                //选中当前，并设置显示
                                child.setSelected(ivSelect == child);

                                if (conform == null) {
                                    tvConform.setText("不使用优惠");
                                } else {
                                    tvConform.setText("参加满优惠活动" + conform.getShortRule());
                                }
                            }
                            dialog.dismiss();
                            updataConformData(tvFreight, tvStoreAll, buyStoreVo.getStoreId(), conform, buyStepPrice);
                        }
                    }
                });
            }
        };
        listView.setAdapter(adapter);

        dialog.show();

    }

    /**
     * 显示优惠券,旧版
     *
     * @param buyStoreVo
     * @param tvVoucherName
     * @param tvStoreAll
     * @param tvFreight
     * @param buyStepPrice
     */
    @Deprecated
    private void showVouchers2(final BuyStoreVo buyStoreVo, final TextView tvVoucherName, final TextView tvStoreAll, final TextView tvFreight, final BuyStepPrice buyStepPrice) {
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
//                        updataVoucherVoData(tvVoucherName, tvStoreAll, tvFreight, buyStoreVo.getStoreId(), voucherVo, buyStepPrice);
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    /**
     * 显示满优惠，旧版
     *
     * @param buyStoreVo
     * @param tvFreight
     * @param tvConform
     * @param tvStoreAll
     * @param buyStepPrice
     */
    @Deprecated
    private void showConform(final BuyStoreVo buyStoreVo, final TextView tvFreight, final TextView tvConform, final TextView tvStoreAll, final BuyStepPrice buyStepPrice) {
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
//                        updataConformData(tvFreight, tvConform, tvStoreAll, buyStoreVo.getStoreId(), conform, buyStepPrice);
                        dialog.dismiss();
                    }
                });
            }
        });
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
                //点击商品区域，跳转详情页
                viewHolder.getView(R.id.llContainer).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShopHelper.gotoGoodDetailsActivity(context, goodsBean.getCommonId());
                    }
                });
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

            //是否有无货商品
            for (int i = 0; noGoodsList != null && i < noGoodsList.size(); i++) {
                int gid = noGoodsList.get(i);
                //匹配任意一个
                skuHolder.setVisible(R.id.rlNoGoods, buyGoodsItemVo.getGoodsId() == gid);
            }

            //有赠品的显示赠品
            LinearLayout llGift = skuHolder.getView(R.id.llGift);
            List<GoodGift> giftVoList = buyGoodsItemVo.getGiftVoList();
            if (buyGoodsItemVo.getIsGift() == 1 && giftVoList != null && giftVoList.size() != 0) {
                skuHolder.setVisible(R.id.llGiftLayout, true);
                llGift.removeAllViews();
                for (GoodGift gift : giftVoList) {
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

    private void updataVoucherVoData(TextView tvStoreAll, TextView tvFreight, int storeId, VoucherVo voucherVo, BuyStepPrice buyStepPrice) {
        if (voucherVo == null) {
            buyStepPrice.setPriceVoucher(new BigDecimal(0));
            buyStepPrice.setVoucherId(0);
        } else {
            buyStepPrice.setPriceVoucher(voucherVo.getPrice());
            buyStepPrice.setVoucherId(voucherVo.getVoucherId());
        }

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
    private void updataConformData(TextView tvFreight, TextView tvStoreAll, int storeId, Conform conform, BuyStepPrice buyStepPrice) {

        if (conform != null) {
            buyStepPrice.setPriceConform(conform.getConformPrice());
            buyStepPrice.setConformId(conform.getConformId());

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
        } else {
            buyStepPrice.setPriceConform(new BigDecimal(0));
            buyStepPrice.setConformId(0);
        }

        buyStepPrice.setFreight(buyStepPrice.getFreightOrigin());   //每次点击都恢复初始邮费
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
        tvFreight.setText(buyStepPrice.getFreight().equals(new BigDecimal(0)) ? moneyRmb + "0.00" : (moneyRmb + ShopHelper.getPriceString(buyStepPrice.getFreight())));
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
