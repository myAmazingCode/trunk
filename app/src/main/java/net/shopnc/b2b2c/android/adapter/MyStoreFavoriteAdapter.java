package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.MemberFavStore;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.custom.dialog.NCDialog;
import net.shopnc.b2b2c.android.custom.dialog.NCDialogConfirm;
import net.shopnc.b2b2c.android.ui.store.StoreInfoFragmentActivity;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：Jie on 2016/6/13 17:42
 */
public class MyStoreFavoriteAdapter extends RRecyclerAdapter<MemberFavStore> {

    private NCDialog delNCDialog;
    private MyShopApplication application;

    public MyStoreFavoriteAdapter(Context context) {
        super(context, R.layout.favsotre_item);
        application=MyShopApplication.getInstance();
    }

    @Override
    public void convert(RecyclerHolder holder, final MemberFavStore storeInfo) {
        holder.setText(R.id.favstore_name, storeInfo.getStore().getStoreName())
                .setText(R.id.favstore_fun, storeInfo.getStore().getStoreCollect() + "")
                .setImage(R.id.favstore_image, storeInfo.getStore().getStoreAvatarUrl())
                .setText(R.id.favstore_goods, storeInfo.getGoodsCommonCount() + "");

        holder.setOnClickListener(R.id.favstore_delete, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delNCDialog = new NCDialog(context);
                delNCDialog.setText1("确认删除?");
                delNCDialog.setOnDialogConfirm(new NCDialogConfirm() {
                    @Override
                    public void onDialogConfirm() {
                        Map<String, String> p = new HashMap<>();
                        p.put("storeId", "" + storeInfo.getStoreId());
                        p.put("token", application.getToken());

                        OkHttpUtil.postAsyn(context,ConstantUrl.URL_STORE_FAVORITE_DEL, new BeanCallback<String>() {
                            @Override
                            public void response(String data) {
                                TToast.showShort(context, "删除成功");
                                datas.remove(storeInfo);
                                MyStoreFavoriteAdapter.this.notifyDataSetChanged();
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
                Intent i = new Intent(context, StoreInfoFragmentActivity.class);
                i.putExtra("storeId", storeInfo.getStoreId());
                context.startActivity(i);
            }
        });
    }

}
