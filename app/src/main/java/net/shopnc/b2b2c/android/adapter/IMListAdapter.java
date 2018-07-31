package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.LinkSellerBean;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.util.ConstantUrl;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/8/12 17:28.
 */
public class IMListAdapter extends RRecyclerAdapter<LinkSellerBean> {
    public IMListAdapter(Context context) {
        super(context, R.layout.recyclerview_imseller_item);
    }


    @Override
    public void convert(RecyclerHolder holder, final LinkSellerBean linkSellerBean) {
        holder.setCircleImage(R.id.ivSellerImg, linkSellerBean.getLink_man_avatar().equals("") ? ConstantUrl.URL_SELLER_IMG_DEFAULT : linkSellerBean.getLink_man_avatar())
                .setText(R.id.tvSellerName, linkSellerBean.getLink_man_name())
                .setText(R.id.tvMsgTime, (Common.isEmpty(linkSellerBean.getMsgTime())) ? "" : (ShopHelper.isToday(linkSellerBean.getMsgTime()) ? ShopHelper.dateFormatHm(linkSellerBean.getMsgTime()) : ShopHelper.dateFormat(linkSellerBean.getMsgTime())))
                .setText(R.id.tvMsg, ShopHelper.getFormatSmileString(context, linkSellerBean.getMsg()), TextView.BufferType.SPANNABLE);
    }
}
