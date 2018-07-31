package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.View;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.VoucherList;
import net.shopnc.b2b2c.android.bean.VoucherTemplateVo;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.List;

/**
 * ght (c) 2007-2017 ShopNC Inc. All rights reserved.
 *
 * @author lzz
 *         Created 2017/5/25 14:57
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * @description
 */
public class VoucherVoAdapter extends CommonAdapter<VoucherTemplateVo> {


    public VoucherVoAdapter(Context context, List<VoucherTemplateVo> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, final VoucherTemplateVo voucherBean) {
        holder.setText(R.id.tvNum, voucherBean.getTemplatePrice() + "")
                .setText(R.id.tvDesc, "满" + voucherBean.getLimitAmount() + "元可用")
                .setText(R.id.tvVoucherName, voucherBean.getTemplateTitle())
                .setText(R.id.tvVoucherDesc, "可购买" + voucherBean.getStoreName() + "商品")
                .setText(R.id.tvVoucherValidDate, "有效期至" + voucherBean.getUseEndTime());

        int memberIsReceive = voucherBean.getMemberIsReceive();


        View tvGetVoucher = holder.getView(R.id.tvGetVoucher);
        View ivStatus = holder.getView(R.id.ivStatus);
        if (0 == memberIsReceive) {
            tvGetVoucher.setVisibility(View.VISIBLE);
            tvGetVoucher.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //领取优惠券
                    if (ShopHelper.isLogin(mContext))
                        getVoucher(voucherBean);
                }
            });
            ivStatus.setVisibility(View.GONE);
        } else if (1 == memberIsReceive) {
            tvGetVoucher.setVisibility(View.GONE);
            ivStatus.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 领取
     *
     * @param voucherBean
     */
    private void getVoucher(final VoucherTemplateVo voucherBean) {

        OkHttpUtil.postAsyn(mContext,ConstantUrl.URL_VOUCHER_GET, new BeanCallback<String>() {
                    @Override
                    public void response(String data) {
                        TToast.showShort(mContext, "领取成功");
                        voucherBean.setMemberIsReceive(1);
                        notifyDataSetChanged();
                    }
                }, new OkHttpUtil.Param("token", MyShopApplication.getInstance().getToken()),
                new OkHttpUtil.Param("templateId", voucherBean.getTemplateId() + ""));
    }
}
