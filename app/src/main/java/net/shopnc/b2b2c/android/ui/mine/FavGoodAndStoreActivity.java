package net.shopnc.b2b2c.android.ui.mine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.ui.mine.fragment.FavGoodFragment;
import net.shopnc.b2b2c.android.ui.mine.fragment.FavStoreFragment;
import net.shopnc.b2b2c.android.util.Global;

import butterknife.Bind;
import butterknife.OnClick;

public class FavGoodAndStoreActivity extends BaseActivity {


    @Bind(R.id.tvTabLeft)
    TextView tvTabLeft;
    @Bind(R.id.vTabLeft)
    View vTabLeft;
    @Bind(R.id.rlTabLeft)
    RelativeLayout rlTabLeft;
    @Bind(R.id.tvTabRight)
    TextView tvTabRight;
    @Bind(R.id.vTabRight)
    View vTabRight;
    @Bind(R.id.rlTabRight)
    RelativeLayout rlTabRight;
    @Bind(R.id.flFragment)
    FrameLayout flFragment;
    private FragmentManager fragmentManager;
    private FavGoodFragment goodFragment;
    private FavStoreFragment storeFragment;

    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("收藏");
        setBtnMoreVisible();
        fragmentManager = getSupportFragmentManager();
        goodFragment = new FavGoodFragment();
        storeFragment = new FavStoreFragment();
        fragmentManager.beginTransaction()
                .add(R.id.flFragment, goodFragment)
                .add(R.id.flFragment, storeFragment)
                .commitAllowingStateLoss();
        flag = getIntent().getStringExtra("flag");
        switchShow();
        getMoreMessage();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_fav_good_add_store);
    }

    private void switchShow() {
        if (flag.equals("good")) {
            changeTab(Global.TABLEFT);
        } else {
            changeTab(Global.TABRIGHT);
        }
    }

    /**
     * 选择tab
     */
    private void changeTab(int i) {
        resetTab();
        switch (i) {
            case Global.TABLEFT:
                tvTabLeft.setTextColor(ContextCompat.getColor(context, R.color.nc_red));
                vTabLeft.setVisibility(View.VISIBLE);
                turnPage(goodFragment, storeFragment);
                break;
            case Global.TABRIGHT:
                tvTabRight.setTextColor(ContextCompat.getColor(context, R.color.nc_red));
                vTabRight.setVisibility(View.VISIBLE);
                turnPage(storeFragment, goodFragment);
                break;
        }
    }

    /**
     * 复位tab
     */
    private void resetTab() {
        tvTabLeft.setText("商品收藏");
        tvTabRight.setText("店铺收藏");
        tvTabLeft.setTextColor(ContextCompat.getColor(context, R.color.nc_text));
        tvTabRight.setTextColor(ContextCompat.getColor(context, R.color.nc_text));
        vTabLeft.setVisibility(View.GONE);
        vTabRight.setVisibility(View.GONE);
    }


    private void turnPage(Fragment fragment1, Fragment fragment2) {
        fragmentManager.beginTransaction()
                .show(fragment1)
                .hide(fragment2)
                .commit();
    }

    @OnClick({R.id.rlTabLeft, R.id.rlTabRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlTabLeft:
                changeTab(Global.TABLEFT);
                break;
            case R.id.rlTabRight:
                changeTab(Global.TABRIGHT);
                break;
        }
    }
}
