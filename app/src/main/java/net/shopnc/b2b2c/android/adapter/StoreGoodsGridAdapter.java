package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.GoodsVo;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.ui.good.GoodDetailsActivity;

/**
 * 店铺商品adapter
 *
 * @author huting
 * @date 2016/4/18
 */
public class StoreGoodsGridAdapter extends CommonAdapter<GoodsVo> {

    public StoreGoodsGridAdapter(Context context){
        super(context, R.layout.gridview_store_goods_item);
    }

    @Override
    public void convert(final ViewHolder holder, final GoodsVo good) {
        holder.setVisible(R.id.llStoreInfo,false)
                .setImage(R.id.imageGoodsPic,good.getImageSrc())
                .setText(R.id.textGoodsName,good.getGoodsName())
                .setText(R.id.textGoodsJingle,good.getJingle())
                .setText(R.id.textGoodsPrice,"￥" + good.getGoodsPrice())
                .setText(R.id.tvGoodsSalenum, "销量：" + good.getGoodsSaleNum());

        //活动：暂且不做
        /*if (Boolean.valueOf(bean.getSole_flag())) {
            holder.textGoodsType.setText("");
            holder.textGoodsType.setVisibility(View.VISIBLE);
            holder.textGoodsType.setBackgroundResource(R.drawable.nc_icon_mobile_price);
        } else if (Boolean.valueOf(bean.getGroup_flag())) {
            holder.textGoodsType.setText(context.getString(R.string.text_groupbuy));
            holder.textGoodsType.setVisibility(View.VISIBLE);
            holder.textGoodsType.setBackgroundResource(R.color.text_tuangou);
        } else if (Boolean.valueOf(bean.getXianshi_flag())) {
            holder.textGoodsType.setText(context.getString(R.string.text_xianshi));
            holder.textGoodsType.setVisibility(View.VISIBLE);
            holder.textGoodsType.setBackgroundResource(R.color.text_xianshi);
        } else if (bean.getIs_appoint().equals("1")) {
            holder.textGoodsType.setText(context.getString(R.string.text_appoint));
            holder.textGoodsType.setVisibility(View.VISIBLE);
            holder.textGoodsType.setBackgroundResource(R.color.text_yuyue);
        } else if (bean.getIs_fcode().equals("1")) {
            holder.textGoodsType.setText(context.getString(R.string.text_fcode));
            holder.textGoodsType.setVisibility(View.VISIBLE);
            holder.textGoodsType.setBackgroundResource(R.color.text_Fcode);
        } else if (bean.getIs_presell().equals("1")) {
            holder.textGoodsType.setText(context.getString(R.string.text_presell));
            holder.textGoodsType.setVisibility(View.VISIBLE);
            holder.textGoodsType.setBackgroundResource(R.color.text_yushou);
        } else if (bean.getIs_virtual().equals("1")) {
            holder.textGoodsType.setText(context.getString(R.string.text_virtual));
            holder.textGoodsType.setVisibility(View.VISIBLE);
            holder.textGoodsType.setBackgroundResource(R.color.text_xuni);
        } else {
            holder.textGoodsType.setVisibility(View.GONE);
        }
        if (bean.getHave_gift().equals("1")) {
            holder.textZengPin.setVisibility(View.VISIBLE);
        } else {
            holder.textZengPin.setVisibility(View.GONE);
        }*/

        if (good.getIsOwnShop() == 0){//不是自营店铺
            holder.setVisible(R.id.tvOwnShop,true);
            holder.setVisible(R.id.btnStoreName,false);
        }else{
            holder.setVisible(R.id.tvOwnShop,false)
                    .setVisible(R.id.btnStoreName, true)
                    .setText(R.id.btnStoreName, good.getStoreName())
                    .setTag(R.id.btnStoreName, holder.getPosition())
                    .setOnClickListener(R.id.btnStoreName, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            holder.setVisible(R.id.llStoreInfo, true);
                            TToast.showShort(mContext, "点击了" + good.getGoodsId());
                        }
                    });

            //跳转到店铺
            holder.setOnClickListener(R.id.tvStoreName, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TToast.showShort(mContext, "调转到店铺");
                }
            });

            //关闭电铺信息
            holder.setOnClickListener(R.id.llStoreEval, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.setVisible(R.id.llStoreInfo, false);
                }
            });
        }

        //商品详情显示
        holder.setOnClickListener(R.id.goodItem, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,GoodDetailsActivity.class);
                intent.putExtra(GoodDetailsActivity.COMMONID, good.getCommonId());
                mContext.startActivity(intent);
            }
        });
    }


    /**
     * 显示店铺信息
     * @param credit
     * @param type
     * @param tvPoint
     * @param tvText
     */
    /*private void setStoreCredit(String credit, String type, TextView tvPoint, TextView tvText) {
        tvPoint.setText(credit);
        if(type.equals("low")) {
            tvPoint.setTextColor(context.getResources().getColor(R.color.nc_green));
            tvText.setText("低");
            tvText.setBackgroundColor(context.getResources().getColor(R.color.nc_green));
        }
        if(type.equals("equal")) {
            tvPoint.setTextColor(context.getResources().getColor(R.color.nc_red));
            tvText.setText("平");
            tvText.setBackgroundColor(context.getResources().getColor(R.color.nc_red));
        }
        if(type.equals("high")) {
            tvPoint.setTextColor(context.getResources().getColor(R.color.nc_red));
            tvText.setText("高");
            tvText.setBackgroundColor(context.getResources().getColor(R.color.nc_red));
        }
    }*/

}
