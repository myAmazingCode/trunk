package net.shopnc.b2b2c.android.ui.fenxiao;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.FlashBean;
import net.shopnc.b2b2c.android.lib.tab.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
//闪购活动页面

public class FlashBuyActivity extends BaseActivity {

    //    @Bind(R.id.flash_recycler)
//    RecyclerView flashRecycler;

    List<String> tab_list = new ArrayList<String>();
    List<Fragment> tab_fragments = new ArrayList<Fragment>();
    @Bind(R.id.kill_strip)
    SlidingTabLayout killStrip;
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initData();
        setCommonHeader("闪购活动");
        setBtnMoreVisible();
        setTabLayout();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_flash_buy);
    }

    public void setTabLayout() {
        tab_list.add("今日上新");
        tab_list.add("最后疯抢");
        tab_list.add("活动预告");
        Bundle bundle1 = new Bundle();
        bundle1.putString("state", "all");
        Bundle bundle2= new Bundle();
        bundle2.putString("state", "oldflash");
        Bundle bundle3 = new Bundle();
        bundle3.putString("state", "unflash");
        FlashBuyFragment todayNew = new FlashBuyFragment();
        FlashBuyFragment lastRob = new FlashBuyFragment();
        FlashBuyFragment killAdvance = new FlashBuyFragment();
        todayNew.setArguments(bundle1);
        lastRob.setArguments(bundle2);
        killAdvance.setArguments(bundle3);
        tab_fragments.add(todayNew);
        tab_fragments.add(lastRob);
        tab_fragments.add(killAdvance);
        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager(), tab_list, tab_fragments);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        killStrip = (SlidingTabLayout) findViewById(R.id.kill_strip);
        viewpager.setAdapter(mAdapter);
        killStrip.setViewPager(viewpager);
    }




//    用viewpager显示fragment
    private class MyPagerAdapter extends FragmentPagerAdapter {

        List<String> cate_list;
        List<Fragment> fragments;

        public MyPagerAdapter(FragmentManager fm, List<String> cate_list, List<Fragment> fragments) {
            super(fm);
            this.cate_list = cate_list;
            this.fragments = fragments;
        }

        @Override
        public int getCount() {
            return cate_list == null ? 0 : cate_list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return cate_list.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
    }
}
