package net.shopnc.b2b2c.android.custom;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * ght (c) 2007-2017 ShopNC Inc. All rights reserved.
 *
 * @author lzz
 *         Created 2017/5/8 13:11
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 *
 * @description 可禁止水平滑动的ViewPager
 */
public class MyViewPager extends ViewPager {

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //默认为true
    private boolean isCanScroll = true;

    //通过设置为false，禁止ViewPager的水平滑动
    public void setCanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    @Override
    public void scrollTo(int x, int y) {
        if (isCanScroll) {
            super.scrollTo(x, y);
        }
    }
}