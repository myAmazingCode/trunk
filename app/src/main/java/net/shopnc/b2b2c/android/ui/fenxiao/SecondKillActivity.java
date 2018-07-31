package net.shopnc.b2b2c.android.ui.fenxiao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.lib.tab.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class SecondKillActivity extends BaseActivity {


//    @Bind(R.id.kill_strip)
    SlidingTabLayout killStrip;
    List<String> tab_list = new ArrayList<String>();
    List<Fragment> tab_fragments = new ArrayList<Fragment>();
//    @Bind(R.id.viewpager)
    ViewPager viewpager;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setTabLayout();

    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_second_kill);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setCommonHeader("秒杀活动");

        setBtnMoreVisible();
    }

    public void setTabLayout() {
        tab_list.add("正在秒杀");
        tab_list.add("秒杀预告");
        SecondKillFragment killAll = new SecondKillFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("state", "all");
        killAll.setArguments(bundle1);


        SecondKillFragment killAdvance = new SecondKillFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("state", "unspike");
        killAdvance.setArguments(bundle2);


        tab_fragments.add(killAll);
        tab_fragments.add(killAdvance);
        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager(), tab_list, tab_fragments);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        killStrip = (SlidingTabLayout) findViewById(R.id.kill_strip);
        viewpager.setAdapter(mAdapter);
        killStrip.setViewPager(viewpager);
    }

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
