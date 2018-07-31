package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.VoucherTemplateVo;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.ui.mine.LoginActivity;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.DBHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author wj
 * Date 2016年05月09日 13:02
 */
public class VoucherListAdapter extends CommonAdapter<VoucherTemplateVo> {

    private MyShopApplication application;
    private List<Integer> ids;
    private DBHelper helper;
    private DecimalFormat df = new DecimalFormat("#0.00");

    public VoucherListAdapter(Context context) {
        super(context, R.layout.voucher_listview_item);
        application = MyShopApplication.getInstance();
        helper = new DBHelper(mContext);
        ids = new ArrayList<>();
    }

    @Override
    public void convert(final ViewHolder holder, final VoucherTemplateVo voucherTemplate) {
        holder.setText(R.id.tvtemplatePrice, voucherTemplate.getTemplatePrice() + "")
                .setText(R.id.tvtemplateTitle, voucherTemplate.getTemplateTitle() + "-满" + voucherTemplate.getLimitAmount() + "元可用")
                .setText(R.id.tvuseStartTimeText, voucherTemplate.getUseStartTimeText())
                .setText(R.id.tvuseEndTimeText, voucherTemplate.getUseEndTimeText());

        if (voucherTemplate.getMemberIsReceive() == 1) {
            holder.setBackgroundRes(R.id.llVoucher, R.color.gray)
                    .setBackgroundRes(R.id.llVoucherContainer, R.color.nc_white)
                    .setText(R.id.btnGet, "已领取")
                    .setTextColor(R.id.btnGet, R.color.gray);
        }

        holder.setOnClickListener(R.id.btnGet, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShopHelper.isLogin(mContext)) {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("token", application.getToken());
                    params.put("templateId", String.valueOf(voucherTemplate.getTemplateId()));

                    OkHttpUtil.postAsyn(mContext,ConstantUrl.URL_VOUCHER_GET, new BeanCallback<String>() {
                        @Override
                        public void response(String data) {

                        }

                        @Override
                        public void onResponse(String resp, int i) {
                            super.onResponse(resp, i);
                            int code = JsonUtil.toInteger(resp, "code");
                            if (code == 401) {
                                Intent intent = new Intent(mContext, LoginActivity.class);
                                mContext.startActivity(intent);
                            } else {
                                holder.setBackgroundRes(R.id.llVoucher, R.color.gray)
                                        .setBackgroundRes(R.id.llVoucherContainer, R.color.nc_white)
                                        .setText(R.id.btnGet, "已领取")
                                        .setTextColor(R.id.btnGet, R.color.gray);
                                helper.saveVouchersId(voucherTemplate.getTemplateId());
                            }
                        }
                    }, params);
                }
            }
        });

    }

}
