package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.StoreGoodsClassList;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.ui.store.StoreGoodsListFragmentManager;


/**
 * 商家商品二级分类适配器
 *
 * @author 胡婷
 * @date 2015/12/1
 */
public class StoreClassifyItemAdapter extends ListBaseAdapter<StoreGoodsClassList>{

    private MyShopApplication myApplication;

    public StoreClassifyItemAdapter(Context ctx) {
        super(ctx);
        this.ctx = ctx;
        myApplication = (MyShopApplication) ctx.getApplicationContext();
    }

    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (null == convertView) {
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.gv_category_item,parent,false);
            vh.tv_category_gv_name = convertView.findViewById(R.id.tv_category_gv_name);

            convertView.setTag(vh);

        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        final StoreGoodsClassList category = mDatas.get(position);
        vh.tv_category_gv_name.setText(category.getName());
        vh.tv_category_gv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*LogHelper.d("huting--storeId2",store_id);
                LogHelper.d("huting--gc_name2",category.getName());
                LogHelper.d("huting--stc_id2",category.getId());*/

                Intent intent =new Intent(ctx,StoreGoodsListFragmentManager.class);
                intent.putExtra("stc_id", category.getId());
                intent.putExtra("gc_name", category.getName());
                intent.putExtra("store_id", store_id);
                ctx.startActivity(intent);

            }
        });

        return convertView;
    }

    private class ViewHolder{
        TextView  tv_category_gv_name;//二级分类名称
    }

    @Override
    public void clear() {
        super.clear();
        mDatas.clear();
    }
}
