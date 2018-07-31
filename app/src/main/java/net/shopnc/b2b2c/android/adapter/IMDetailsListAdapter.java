package net.shopnc.b2b2c.android.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.IMMessage;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.MultiItemRecycleAdapter;
import net.shopnc.b2b2c.android.common.adapter.MultiRecyclerItemSupport;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.util.LoadImage;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/8/5 18:12.
 */
public class IMDetailsListAdapter extends MultiItemRecycleAdapter<IMMessage> {
    private String userImg;
    private String sellerImg;

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public void setSellerImg(String sellerImg) {
        this.sellerImg = sellerImg;
    }

    public IMDetailsListAdapter(Context context, MultiRecyclerItemSupport<IMMessage> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);
    }

    @Override
    public void convert(RecyclerHolder holder, final IMMessage message) {
        switch ((int) holder.getConvertView().getTag()) {
            case R.layout.right:
                holder.setCircleImage(R.id.ivSellerImg, userImg);
                break;
            case R.layout.left:
                holder.setCircleImage(R.id.ivSellerImg, sellerImg);
                break;
        }

        //消息中含有商品信息
        if (message.isHas_goods()) {
            holder.setVisible(R.id.tvSellerMessage, false);
            holder.setVisible(R.id.rlGood, true);
            holder.setImage(R.id.ivGoodImg, message.getGoods_info().getImageName())
                    .setText(R.id.tvGoodName, message.getGoods_info().getGoodsName())
                    .setText(R.id.tvGoodPrice, context.getResources().getString(R.string.money_rmb) + ShopHelper.getPriceString(message.getGoods_info().getAppPriceMin()));
            holder.setOnClickListener(R.id.rlGood, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShopHelper.gotoGoodDetailsActivity(context, message.getGoods_info().getCommonId());
                    ((Activity) context).finish();
                }
            });
        } else {
            holder.setVisible(R.id.tvSellerMessage, true);
            holder.setVisible(R.id.rlGood, false);
            holder.setText(R.id.tvSellerMessage, ShopHelper.getFormatSmileString(context, message.getMessage_content()), TextView.BufferType.SPANNABLE);
        }

        //消息是一个图片
        if (message.isHas_pic()) {
            holder.setVisible(R.id.tvSellerMessage, false);
            holder.setVisible(R.id.rlImg, true);
//            ResizableImageView ivImg = holder.getView(R.id.ivImg);
//            Glide.with(context)
//                    .load(message.getPic_url())
//                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                    .into(ivImg);
            //如果将图片的宽高传回来，则可直接这样设置
            ImageView imageView = holder.getView(R.id.ivImg);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (message.getPic_width()>600){
                params.width=600;
            }else {
                params.height = message.getPic_height();
                params.width = message.getPic_width();
            }

            imageView.setLayoutParams(params);
            LogHelper.e("QIN", message.getPic_height() + "-" + message.getPic_width());
            LoadImage.loadRemoteImg(context, imageView, message.getPic_url());
//            holder.setImage(R.id.ivImg,message.getPic_url());
        } else {
            holder.setVisible(R.id.tvSellerMessage, true);
            holder.setVisible(R.id.rlImg, false);
        }

        //时间间隔在两分钟之内的连续显示，否则显示时间
        if (ShopHelper.isToday(message.getAdd_time())) {  //今天
            if (position == 0 || datas.size() == 2) {//首个显示时间
                holder.setVisible(R.id.tvTime, true);
                holder.setText(R.id.tvTime, ShopHelper.dateFormatHm(message.getAdd_time()));
            } else {
                //前一个可能是商品广告位  故判断
                if ((message.getAdd_time() - (datas.get(position - 1) == null ? datas.get(position - 2).getAdd_time() : datas.get(position - 1).getAdd_time())) / (1000 * 60) > 2) {
                    holder.setVisible(R.id.tvTime, true);
                    holder.setText(R.id.tvTime, ShopHelper.dateFormatHm(message.getAdd_time()));
                } else {
                    holder.setVisible(R.id.tvTime, false);
                }
            }
        } else {
            holder.setVisible(R.id.tvTime, true);
            holder.setText(R.id.tvTime, ShopHelper.dateTimeFormat(message.getAdd_time()));
        }


    }

}
