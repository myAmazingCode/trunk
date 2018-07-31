package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.MemberFavGoods;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.custom.dialog.NCDialog;
import net.shopnc.b2b2c.android.custom.dialog.NCDialogConfirm;
import net.shopnc.b2b2c.android.ui.good.GoodDetailsActivity;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：Jie on 2016/6/13 17:42
 */
public class MyGoodsFavoriteAdapter extends RRecyclerAdapter<MemberFavGoods> {

    private NCDialog delNCDialog;
    private MyShopApplication application = MyShopApplication.getInstance();

    public MyGoodsFavoriteAdapter(Context context) {
        super(context, R.layout.favgoods_item);
    }

    @Override
    public void convert(RecyclerHolder holder, final MemberFavGoods goods) {
        holder.setText(R.id.favgood_name, goods.getGoodsName())
                .setText(R.id.favgood_price, context.getResources().getString(R.string.money_rmb) + ShopHelper.getPriceString(goods.getFavGoodsPrice()))
                .setImage(R.id.favgood_image, goods.getGoodsCommon().getImageSrc());

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodDetailsActivity.class);
                intent.putExtra(GoodDetailsActivity.COMMONID, goods.getCommonId());
                context.startActivity(intent);
            }
        });

        holder.setOnClickListener(R.id.favgoods_delete, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delNCDialog = new NCDialog(context);
                delNCDialog.setText1("确认删除?");
                delNCDialog.setOnDialogConfirm(new NCDialogConfirm() {
                    @Override
                    public void onDialogConfirm() {
                        Map<String, String> p = new HashMap<>();
                        p.put("commonId", "" + goods.getCommonId());
                        p.put("token", application.getToken());

                        OkHttpUtil.postAsyn(context,ConstantUrl.URL_FAV_GOODS_DEL, new BeanCallback<String>() {
                            @Override
                            public void response(String data) {
                                TToast.showShort(context, "删除成功");
                                datas.remove(goods);
                                MyGoodsFavoriteAdapter.this.notifyDataSetChanged();
                            }
                        }, p);
                    }
                });
                delNCDialog.showPopupWindow();
            }
        });

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodDetailsActivity.class);
                intent.putExtra(GoodDetailsActivity.COMMONID, goods.getCommonId());
                context.startActivity(intent);
            }
        });
    }
}
