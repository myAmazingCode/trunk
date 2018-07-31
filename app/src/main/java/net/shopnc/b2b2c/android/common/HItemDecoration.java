package net.shopnc.b2b2c.android.common;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2017/2/14 11:08.
 */

public class HItemDecoration extends RecyclerView.ItemDecoration {
    private int height;

    public HItemDecoration(int height) {
        this.height = height;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = height;
    }
}
