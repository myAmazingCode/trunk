package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.View;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.ItemData;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.ui.home.HomeLoadDataHelper;


/**
 * home3 adapter
 *
 * @author huting
 * @date 2016/4/8
 */
public class Home3GridViewAdapter extends CommonAdapter<ItemData> {

    public Home3GridViewAdapter(Context context) {
        super(context, R.layout.tab_home_item_home3_gridview_item);
    }


    @Override
    public void convert(ViewHolder holder, final ItemData itemData) {
        holder.setImage(R.id.ImageViewImagePic01,itemData.getImageUrl());

        holder.setOnClickListener(R.id.home3, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeLoadDataHelper.doClick(mContext, itemData.getType(), itemData.getData());
            }
        });
    }
}
