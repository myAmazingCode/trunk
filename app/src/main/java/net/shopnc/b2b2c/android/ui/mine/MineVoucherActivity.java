package net.shopnc.b2b2c.android.ui.mine;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.util.Global;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 优惠券
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.ui.mine.MineVoucherActivity.java
 * @author: Jie
 * @date: 2016-05-29 20:37
 */
public class MineVoucherActivity extends BaseActivity {

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
    @Bind(R.id.discount_fragment_container)
    FrameLayout discountFragmentContainer;
    private MineVoucherMyFragment myFragment;
    private MineVoucherGetFragment getFragment;

    private FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("优惠券");
        setBtnMoreVisible();
        fragmentManager = getFragmentManager();
        myFragment = new MineVoucherMyFragment();
        getFragment = new MineVoucherGetFragment();
        fragmentManager.beginTransaction()
                .add(R.id.discount_fragment_container, myFragment)
                .add(R.id.discount_fragment_container, getFragment)
                .commitAllowingStateLoss();

        changeTab(Global.TABLEFT);
        getMoreMessage();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_mine_voucher);
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
                turnPage(myFragment, getFragment);
                break;
            case Global.TABRIGHT:
                tvTabRight.setTextColor(ContextCompat.getColor(context, R.color.nc_red));
                vTabRight.setVisibility(View.VISIBLE);
                turnPage(getFragment, myFragment);
                break;
        }
    }

    /**
     * 复位tab
     */
    private void resetTab() {
        tvTabLeft.setText("我的优惠券");
        tvTabRight.setText("领取优惠券");
        tvTabLeft.setTextColor(ContextCompat.getColor(context, R.color.nc_text));
        tvTabRight.setTextColor(ContextCompat.getColor(context, R.color.nc_text));
        vTabLeft.setVisibility(View.GONE);
        vTabRight.setVisibility(View.GONE);
    }


//    @OnClick({R.id.left_btn, R.id.right_btn, R.id.btn_discount_back})
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_discount_back:
//                finish();
//                break;
//            case R.id.left_btn:
//                turnPage(myFragment, getFragment);
//                left_btn.setBackgroundResource(R.drawable.mine_left_red_stroke);
//                right_btn.setBackgroundResource(R.drawable.mine_right_default_stroke);
//                left_btn.setTextColor(getResources().getColor(R.color.white));
//                right_btn.setTextColor(getResources().getColor(R.color.nc_black));
//                break;
//            case R.id.right_btn:
//                turnPage(getFragment, myFragment);
//                left_btn.setBackgroundResource(R.drawable.mine_left_default_stroke);
//                right_btn.setBackgroundResource(R.drawable.mine_right_red_stroke);
//                left_btn.setTextColor(getResources().getColor(R.color.nc_black));
//                right_btn.setTextColor(getResources().getColor(R.color.white));
//                break;
//        }
//    }

    private void turnPage(MineBaseFragment fragment1, MineBaseFragment fragment2) {
        fragmentManager.beginTransaction()
                .show(fragment1)
                .hide(fragment2)
                .commit();
    }

//    private void changeTextAndBack(int leftColor, int rightColor, int leftDrawble, int rightDrawble) {
//        left_btn.setTextColor(getResources().getColor(leftColor));
//        right_btn.setTextColor(rightColor);
//        left_btn.setBackgroundResource(leftDrawble);
//        right_btn.setBackgroundResource(rightDrawble);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
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
