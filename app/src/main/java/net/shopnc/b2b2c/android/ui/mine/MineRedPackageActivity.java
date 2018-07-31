package net.shopnc.b2b2c.android.ui.mine;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.base.EventObj;
import net.shopnc.b2b2c.android.util.Global;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.ui.mine.MineRedPackageActivity.java
 * @author: Jie
 * @date: 2016-05-31 10:34
 */
public class MineRedPackageActivity extends BaseActivity {

    private static final String TAG = "MineRedPackageActivity";
    @Bind(R.id.tvTabLeft)
    TextView tvTabLeft;//我的红包按钮
    @Bind(R.id.vTabLeft)
    View vTabLeft;
    @Bind(R.id.rlTabLeft)
    RelativeLayout rlTabLeft;
    @Bind(R.id.tvTabRight)
    TextView tvTabRight;//领取红包按钮
    @Bind(R.id.vTabRight)
    View vTabRight;
    @Bind(R.id.rlTabRight)
    RelativeLayout rlTabRight;
    @Bind(R.id.red_discount_fragment_container)
    FrameLayout redDiscountFragmentContainer;

    private FragmentManager fragmentManager;
    private MineMyRedPacFragment fragment1;
    private MineRedPacGetFragment fragment2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setCommonHeader("红包");
        setBtnMoreVisible();
        fragmentManager = getFragmentManager();

        fragment1 = new MineMyRedPacFragment();
        fragment2 = new MineRedPacGetFragment();
        fragmentManager.beginTransaction()
                .add(R.id.red_discount_fragment_container, fragment1)
                .add(R.id.red_discount_fragment_container, fragment2)
                .commitAllowingStateLoss();

        changeTab(Global.TABLEFT);
        getMoreMessage();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_redpackage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
                turnPage(fragment1, fragment2);
                break;
            case Global.TABRIGHT:
                tvTabRight.setTextColor(ContextCompat.getColor(context, R.color.nc_red));
                vTabRight.setVisibility(View.VISIBLE);
                turnPage(fragment2, fragment1);
                break;
        }
    }

    /**
     * 复位tab
     */
    private void resetTab() {
        tvTabLeft.setText("我的红包");
        tvTabRight.setText("领取红包");
        tvTabLeft.setTextColor(ContextCompat.getColor(context, R.color.nc_text));
        tvTabRight.setTextColor(ContextCompat.getColor(context, R.color.nc_text));
        vTabLeft.setVisibility(View.GONE);
        vTabRight.setVisibility(View.GONE);
    }

    private void turnPage(MineBaseFragment fragment1, MineBaseFragment fragment2) {
        fragmentManager.beginTransaction()
                .show(fragment1)
                .hide(fragment2)
                .commit();
    }

    public void onEventMainThread(EventObj eventObj) {
        if (eventObj.getFlag().equals(EventObj.REDPACKAGEGETSUCCESS)) {
            changeTab(Global.TABLEFT);
            fragment1.onResume();
        }
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
