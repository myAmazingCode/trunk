package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @Description 商品详情页面切换
 * @Author qyf
 *
 * Created 2016/7/18 14:36.
 */
public class GoodFragmentPageAdapter extends FragmentPagerAdapter {
    private List<String> titleList;
    private List<Fragment> fragmentList;


    public GoodFragmentPageAdapter(FragmentManager fm, Context context){
        super(fm);
    }

    public void setTitleList(List<String> titleList){
        this.titleList=titleList;
    }

    public void setFragmentList(List<Fragment> fragmentList){
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return null==titleList?0:titleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
