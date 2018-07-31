package net.shopnc.b2b2c.android.ui.trys;

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
import net.shopnc.b2b2c.android.util.Global;

import butterknife.Bind;
import butterknife.OnClick;

public class MyTryListActivity extends BaseActivity {


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
    private MyTryListFragment tryListFragment;
    private MyTryVoucherListFragment voucherListFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("试用");
        setBtnMoreVisible();
        fragmentManager = getSupportFragmentManager();
        tryListFragment = MyTryListFragment.newInstance();
        voucherListFragment = MyTryVoucherListFragment.newInstance();
        fragmentManager.beginTransaction()
                .add(R.id.flFragment, tryListFragment)
                .add(R.id.flFragment, voucherListFragment)
                .commitAllowingStateLoss();
        changeTab(Global.TABLEFT);
        getMoreMessage();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_my_try_list);
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
                turnPage(tryListFragment, voucherListFragment);
                break;
            case Global.TABRIGHT:
                tvTabRight.setTextColor(ContextCompat.getColor(context, R.color.nc_red));
                vTabRight.setVisibility(View.VISIBLE);
                turnPage(voucherListFragment, tryListFragment);
                break;
        }
    }

    /**
     * 复位tab
     */
    private void resetTab() {
        tvTabLeft.setText("我的试用");
        tvTabRight.setText("试用优惠券");
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
