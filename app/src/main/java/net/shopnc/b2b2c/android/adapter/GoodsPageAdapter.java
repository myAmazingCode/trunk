package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.GoodsVo;

import java.util.ArrayList;
import java.util.List;

/**
 * ght (c) 2007-2017 ShopNC Inc. All rights reserved.
 *
 * @author lzz
 *         Created 2017/5/4 10:07
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * @description 推荐商品的ViewPager适配器；带有水平分页功能的RecyclerView
 *
 * **********************
 *   ##     ##      ##  *
 *                      *
 *   ##     ##      ##  *
 * **********************
 */
public class GoodsPageAdapter extends PagerAdapter {

    private List<View> indicators = new ArrayList<>();//水平分页的指示器
    private ArrayList<GoodsVo> datas = new ArrayList<>();//RecyclerView数据集合
    private Context context;
    private LinearLayout llIndicators;//水平分页的容器

    public void setDatas(ArrayList<GoodsVo> datas) {
        this.datas = datas;
    }

    public List<View> getIndicators() {
        return indicators;
    }

    public GoodsPageAdapter(Context context, LinearLayout llIndicators) {
        this.context = context;
        this.llIndicators = llIndicators;
    }


    /**
     * 计算水平分页的数量
     *
     * @return
     */
    @Override
    public int getCount() {
        int count = datas.size() % 6;
        int divide = datas.size() / 6;
        return count == 0 ? divide : divide + 1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public RecyclerView instantiateItem(ViewGroup container, int position) {

        RecyclerView recyclerView = new RecyclerView(context);
        //2行显示。列数量通过item的宽度来控制，显示3列
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        GoodsDetailsHotGridAdapter hotGridAdapter = new GoodsDetailsHotGridAdapter(context);
        recyclerView.setAdapter(hotGridAdapter);

        List<GoodsVo> list = new ArrayList<GoodsVo>();
        //每页最多显示6个，小于数据集总数，且小于下一页开始的位置索引
        for (int i = position * 6; i < (position + 1) * 6 && i < datas.size(); i++) {
            list.add(datas.get(i));
        }

        //初始化指示器。position == 0只初始化一次;且有多页；
        for (int i = 0; position == 0 && getCount() != 1 && i < getCount(); i++) {
            View indicator = new View(context);
            Drawable drawable = context.getResources().getDrawable(R.drawable.indicator_selector);
            indicator.setBackground(drawable);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(24, 24);
            params.setMarginEnd(10);
            llIndicators.addView(indicator, params);
            indicators.add(indicator);
        }

        hotGridAdapter.setDatas(list);
        hotGridAdapter.notifyDataSetChanged();
        container.addView(recyclerView);
        return recyclerView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeViewAt(position);
    }
}
