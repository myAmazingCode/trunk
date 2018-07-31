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

import com.google.gson.reflect.TypeToken;
import com.ta.utdid2.android.utils.StringUtils;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.CateListBean;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.JsonUtil;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.ViewFindUtils;
import net.shopnc.b2b2c.android.event.LogoutEvent;
import net.shopnc.b2b2c.android.event.SimpleCardEvent;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.lib.tab.SlidingTabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snm on 2016/8/29.
 */
public class FenXiaoAudeoListTabActivity extends FragmentActivity {
    private ImageButton btnBack;
    private TextView tvCommonTitle;
    private TextView tvCommonTitleBorder;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewpager;
    List<CateListBean> cate_list = new ArrayList<CateListBean>();

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;
    private String cate_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenxiao_main_list);
        EventBus.getDefault().register(this);
        cate_id = getIntent().getStringExtra("cate_id");
        View decorView = getWindow().getDecorView();

        slidingTabLayout = ViewFindUtils.find(decorView, R.id.viewpagertab);
        viewpager = ViewFindUtils.find(decorView, R.id.viewpager);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                EventBus.getDefault().post(new SimpleCardEvent());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
        LoadTabDate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        List<CateListBean> cate_list;

        public MyPagerAdapter(FragmentManager fm, List<CateListBean> cate_list) {
            super(fm);
            this.cate_list = cate_list;
        }

        @Override
        public int getCount() {
            return cate_list == null ? 0 : cate_list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return cate_list.get(position).getCate_name();
        }

        @Override
        public Fragment getItem(int position) {
            return SimpleCardFragment.getInstance(cate_list.get(position).getCate_id());
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

    private void LoadTabDate() {
        String url = Constants.URL_CATE_LIST;

        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == 200) {
                    String json = data.getJson();
                    try {
                        String cate_lists = JsonUtil.getString(data.getJson(), "cate_list");

                        List<CateListBean> cateListBeen = JsonUtil.getBean(cate_lists, new TypeToken<ArrayList<CateListBean>>() {
                        }.getType());
                        if (!cateListBeen.isEmpty()) {
                            cate_list = cateListBeen;
                            mAdapter = new MyPagerAdapter(getSupportFragmentManager(), cate_list);
                            viewpager.setAdapter(mAdapter);
//                            mAdapter.notifyDataSetChanged();
                            slidingTabLayout.setViewPager(viewpager);
                            if (!StringUtils.isEmpty(cate_id)) {
                                setTablayout();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    Logger.d(json);
                } else {
                    ShopHelper.showApiError(getApplicationContext(), data.getJson());
                }
            }
        });

    }

    public void setTablayout() {
        if (!StringUtils.isEmpty(cate_id)) {
            for (int i = 0; i < cate_list.size(); i++) {
                CateListBean bean = cate_list.get(i);
                if (cate_id.equals(bean.getCate_id())) {
                    viewpager.setCurrentItem(i);
                    return;
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LogoutEvent event) {

    }

}
