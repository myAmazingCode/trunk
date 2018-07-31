package net.shopnc.b2b2c.android.custom.horizontalnavigationbar;


import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import net.shopnc.b2b2c.android.bean.TrySortBean;

public class NCHorizontalNavigationBar extends HorizontalNavigationBar<TrySortBean> {

    public NCHorizontalNavigationBar(Context paramContext) {
        super(paramContext);
    }

    public NCHorizontalNavigationBar(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public NCHorizontalNavigationBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    @Override
    public void renderingItemView(HorizontalNavigationItemView itemView, int index, int currentPosition) {
        TrySortBean trySortBean = getItem(index);
        itemView.setChannelTitle(trySortBean.getCategoryName());
        itemView.setChecked(index == currentPosition);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
