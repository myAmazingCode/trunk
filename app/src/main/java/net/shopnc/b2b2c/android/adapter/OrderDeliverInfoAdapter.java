package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.OrderDeliverInfoItemBean;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/5/17 11:04.
 */
public class OrderDeliverInfoAdapter extends CommonAdapter<OrderDeliverInfoItemBean> {
    public OrderDeliverInfoAdapter(Context context){
        super(context, R.layout.listview_order_deliver_item);
    }

    @Override
    public void convert(ViewHolder holder, OrderDeliverInfoItemBean orderDeliverInfoItemBean) {
        holder.setText(R.id.deliverInFoID,orderDeliverInfoItemBean.getTime()+orderDeliverInfoItemBean.getContext());
        if(position == 0){
            holder.setImage(R.id.ivShip,R.drawable.shipping_top);
            holder.setTextColor(R.id.deliverInFoID, ContextCompat.getColor(mContext,R.color.gooddetails_store_low));
        }else if (position == getCount()-1){
            holder.setImage(R.id.ivShip,R.drawable.shipping_bottom);
            holder.setTextColor(R.id.deliverInFoID, ContextCompat.getColor(mContext,R.color.nc_text_dark));
        }else{
            holder.setImage(R.id.ivShip,R.drawable.shipping_center);
            holder.setTextColor(R.id.deliverInFoID, ContextCompat.getColor(mContext,R.color.nc_text_dark));
        }
    }
}
