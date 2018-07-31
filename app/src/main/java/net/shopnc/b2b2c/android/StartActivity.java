package net.shopnc.b2b2c.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.shopnc.b2b2c.android.adapter.ViewPagerAdapter;

import java.util.ArrayList;

/**
 * Created by snm on 2016/9/23.
 */
public class StartActivity extends Activity implements ViewPager.OnPageChangeListener{

    ViewPager viewPager;
    // 引导图片资源
    private int[] pics = { R.drawable.guide1, R.drawable.guide2,
            R.drawable.guide3, R.drawable.guide4 };
    // 定义一个ArrayList来存放View
    private ArrayList<View> views = new ArrayList<View>();
    // 定义ViewPager适配器
    private ViewPagerAdapter vpAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        viewPager = findViewById(R.id.viewpager);
        // 实例化ViewPager适配器
        vpAdapter = new ViewPagerAdapter(views);
        initData();
    }
    private void initData() {
        // 定义一个布局并设置参数
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        // 初始化引导图片列表
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            iv.setImageResource(pics[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);//设置全屏幕填充
            views.add(iv);
        }
        // 设置数据
        //前面的views中没有数据 在前面的循环中才插入数据 而此时vpAdapter中已经有数据说明
        //初始化adapter的时候 参数传递是传引用
        viewPager.setAdapter(vpAdapter);
        vpAdapter.notifyDataSetChanged();
        // 设置监听
        viewPager.setOnPageChangeListener(this);
        //选择viewpaper的切换动画
//        viewPager.setPageTransformer(true,new DepthPageTransformer());
//        viewPager.setPageTransformer(true,new ZoomOutPageTransformer());

    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == (pics.length - 1)){
            View view = views.get(position);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it=new Intent();
                    it.setClass(StartActivity.this,MainFragmentManager.class);
                    startActivity(it);
                    finish();
                }
            });
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
