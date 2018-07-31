package net.shopnc.b2b2c.android.ui.home;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.text.style.TabStopSpan;
import android.text.style.TextAppearanceSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.VoucherVoAdapter;
import net.shopnc.b2b2c.android.base.EventObj;
import net.shopnc.b2b2c.android.bean.BuyData;
import net.shopnc.b2b2c.android.bean.CartStoreVo;
import net.shopnc.b2b2c.android.bean.Conform;
import net.shopnc.b2b2c.android.bean.VoucherTemplateVo;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.ui.good.GoodBusBean;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CartHelper类，显示购物车优惠券等
 */
public class CartHelper {


    /**
     * 领券，必须使用对象调用，可能多个store
     *
     * @param context
     * @param holderStore
     * @param cartStoreVo
     */
    public void setVourcher(final Context context, AddViewHolder holderStore, final CartStoreVo cartStoreVo) {
        final List<VoucherTemplateVo> voucherTemplateVoList = cartStoreVo.getVoucherTemplateVoList();
        if (voucherTemplateVoList == null || voucherTemplateVoList.size() == 0) {
            holderStore.setVisible(R.id.llVoucher, false);
        } else {
            holderStore.setVisible(R.id.llVoucher, true);
            //加载优惠券
            holderStore.setOnClickListener(R.id.llVoucher, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showVoucherDialog(context, cartStoreVo.getStoreName(), voucherTemplateVoList);
                }
            });
        }
    }

    /**
     * 优惠券弹窗
     */
    private void showVoucherDialog(Context context, String storeDesc, List<VoucherTemplateVo> voucherTemplateVoList) {
        View view = LayoutInflater.from(context).inflate(R.layout.voucher_dialog, null);
        //店铺描述
        TextView tvStoreDesc = view.findViewById(R.id.tvStoreDesc);
        tvStoreDesc.setText(storeDesc + " 可用优惠券");

        ListView listView = view.findViewById(R.id.listView);
        VoucherVoAdapter adapter = new VoucherVoAdapter(context, voucherTemplateVoList, R.layout.voucher_dialog_list_item);
        listView.setAdapter(adapter);

        Dialog dialog = new Dialog(context, R.style.CommonDialog);
        dialog.setContentView(view);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, 1200);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();
    }

    /**
     * 满优惠弹窗
     *
     * @param context
     * @param holderStore
     * @param cartStoreVo
     */
    public void setConfirm(final Context context, AddViewHolder holderStore, final CartStoreVo cartStoreVo) {
        final List<Conform> conformList = cartStoreVo.getConformList();
        if (conformList == null || conformList.size() == 0) {
            holderStore.setVisible(R.id.rlDiscountShow, false);
            //不为空
        } else {
            holderStore.setVisible(R.id.rlDiscountShow, true);
            holderStore.setText(R.id.tvDiscountShow, conformList.get(0).getContentRule());
            holderStore.setOnClickListener(R.id.rlDiscountShow, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPromotionDialog(context, conformList);
                }
            });
        }
    }

    /**
     * 满优惠弹窗
     */
    private void showPromotionDialog(Context context, List<Conform> conforms) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_conforms_popupwindow, null);
        LinearLayout llDiscount = view.findViewById(R.id.llDiscount);

        //显示满优惠
        for (int i = 0; i < conforms.size(); i++) {
            final Conform conform = conforms.get(i);
            TextView textView = new TextView(context);
            textView.setTextColor(context.getResources().getColor(R.color.nc_black));
            textView.setText(conform.getContentCartRule());
            llDiscount.addView(textView);
        }

        Dialog dialog = new Dialog(context, R.style.CommonDialog);
        dialog.setContentView(view);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, 1200);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();

    }


    /**
     * 旧版本
     *
     * @param context
     * @param cartStoreVo
     */
    @Deprecated
    private void showConform(final Context context, final CartStoreVo cartStoreVo) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        View view = LayoutInflater.from(context).inflate(R.layout.follow_bottom_confirm_dialog, null);
        ListView listView = view.findViewById(R.id.llChoosens);
        dialog.setView(view);
        dialog.show();
        listView.setAdapter(new CommonAdapter<Conform>(context, cartStoreVo.getConformList(), R.layout.follow_botton_dialog_item) {
            @Override
            public void convert(ViewHolder holder, final Conform conform) {
                Button btnSortView = holder.getView(R.id.btnSortView);
                btnSortView.setText(conform.getContentRule());
            }
        });
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText("满优惠活动");
        Button btnSure = view.findViewById(R.id.btnSure);
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    public static SpannableString getPromotionString(Context context, String moneyRmb, String appPrice, String goodPrice, String unitName) {
        String s = moneyRmb + appPrice + "/" + unitName;
        int position = s.indexOf("/");
        String ss = "", sss = "";
        if (!goodPrice.equals("")) {
            ss = moneyRmb + goodPrice + "/" + unitName;
            sss = "\n" + "省" + ShopHelper.getPriceString(new BigDecimal(goodPrice).subtract(new BigDecimal(appPrice))) + "/" + unitName;
        }
        String string = s + ss + sss;
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.buystep_all_red_small), 0, position, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.refund_money_name), position, string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StrikethroughSpan(), s.length(), s.length() + ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TabStopSpan.Standard(s.length()), 0, string.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return spannableString;
    }

    public static void delete(final MyShopApplication application, HashMap<Integer, BuyData> buyDatas) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        String cartId = "";
        for (BuyData buyData : buyDatas.values()) {
            cartId = cartId + buyData.getGoodsId() + ",";
        }
        params.put("cartId", cartId.substring(0, cartId.length() - 1));
        if (Common.isEmpty(application.getToken()))
            params.put("cartData", application.getCartData());
        OkHttpUtil.postAsyn(application, ConstantUrl.URL_CART_BATCH_SEK, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                application.setCartData(JsonUtil.toString(data, "cartData"));
                EventBus.getDefault().post(new EventObj(EventObj.CARTREFRESH));
                //更新购物车数量
                EventBus.getDefault().post(new GoodBusBean(GoodBusBean.REFRESH_CART_COUNT));
            }
        }, params);
    }

    /**
     * 详情页和首页购物车红点数量显示
     *
     * @param tvCount
     */
    public static void requestCartCount(Context context,final TextView tvCount) {
        MyShopApplication application = MyShopApplication.getInstance();
        String token = application.getToken();
        Map<String, String> map = new HashMap<>();
        map.put("clientType", "android");
        map.put("token", token);
        //未登录时本地读取数量
        map.put("cartData", TextUtils.isEmpty(token) ? application.getCartData() : "");

        OkHttpUtil.postAsyn(context, ConstantUrl.URL_CART_AMOUNT, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                String cartCount = JsonUtil.toString(data, "cartCount");
                if (tvCount != null) {
                    if (TextUtils.isEmpty(cartCount)) {
                        tvCount.setVisibility(View.GONE);
                    } else {
                        tvCount.setVisibility(View.VISIBLE);
                        tvCount.setText(cartCount);
                        //数量为0隐藏
                        if ("0".equals(cartCount)) {
                            tvCount.setVisibility(View.GONE);
                        }
                    }
                }
            }
        }, map);
    }
}
