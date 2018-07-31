package net.shopnc.b2b2c.android.ui.fenxiao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ta.utdid2.android.utils.StringUtils;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.common.ViewFindUtils;
import net.shopnc.b2b2c.android.lib.tab.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snm on 2016/9/22.
 */
public class FenxiaoSettlementActivity extends FragmentActivity {

    private ImageButton btnBack;
    private TextView tvCommonTitle;
    private TextView tvCommonTitleBorder;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewpager;
    List<String> cate_list = new ArrayList<String>();

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;
    private String cate_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement);
        setCommonHeader("分销结算");
        cate_id = getIntent().getStringExtra("cate_id");
        View decorView = getWindow().getDecorView();
        cate_list.add("全部");
        cate_list.add("未结算");
        cate_list.add("已结算");
        slidingTabLayout = ViewFindUtils.find(decorView, R.id.viewpagertab);
        viewpager = ViewFindUtils.find(decorView, R.id.viewpager);
        setTablayout();
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        List<String> cate_list;

        public MyPagerAdapter(FragmentManager fm, List<String> cate_list) {
            super(fm);
            this.cate_list = cate_list;
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
            return FenxiaoSettlementFragment.getInstance(getStates(cate_list.get(position)));
        }
    }

    /**
     * 设置Activity通用标题文字
     */
    protected void setCommonHeader(String title) {
        btnBack = findViewById(R.id.btnBack);
        tvCommonTitle = findViewById(R.id.tvCommonTitle);
        tvCommonTitleBorder = findViewById(R.id.tvCommonTitleBorder);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvCommonTitle.setText(title);
    }

    public void setTablayout() {
        mAdapter = new MyPagerAdapter(getSupportFragmentManager(),cate_list);
        viewpager.setAdapter(mAdapter);
        slidingTabLayout.setViewPager(viewpager);

        if (!StringUtils.isEmpty(cate_id)) {
            for (int i = 0; i < cate_list.size(); i++) {
                if (cate_id.equals(cate_list.get(i))) {
                    viewpager.setCurrentItem(i);
                    return;
                }
            }
        }else {
            viewpager.setCurrentItem(0);
        }
    }
    public String getStates(String states){
        if("全部".equals(states)){
            return "";
        }else if("已结算".equals(states)){
            return "1";
        }else if("未结算".equals(states)){
            return "0";
        }
        return "";
    }
}
