package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.GoodCategory;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.ui.promotion.CategoryHelper;


/**
 * 商品分类
 *
 * @author huting
 * @date 2016/4/8
 */
public class GoodsCategoryAdapter extends CommonAdapter<GoodCategory> {

    public GoodsCategoryAdapter(Context context) {
        super(context, R.layout.gridview_goods_class_item);
    }

    @Override
    public void convert(ViewHolder holder, final GoodCategory goodsCategory) {
        holder.setText(R.id.tvGoodsClassName, goodsCategory.getCategoryName());

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent();
//                i.setClass(mContext, SearchGoodsShowActivity.class);
//                i.putExtra("cat", goodsCategory.getCategoryId());
//                mContext.startActivity(i);
                Log.d("Catogory", "onClick: cat = " + goodsCategory.getCategoryId());
//
                CategoryHelper.jump(mContext, goodsCategory.getCategoryId(), false);
            }
        });
    }

}
