package net.shopnc.b2b2c.android.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yuanshuo on 2017/9/21.
 */

public class SpaceItemTopDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpaceItemTopDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.top = space;

        //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
        if (parent.getChildLayoutPosition(view) ==0) {
            outRect.top = 0;
        }
    }

}