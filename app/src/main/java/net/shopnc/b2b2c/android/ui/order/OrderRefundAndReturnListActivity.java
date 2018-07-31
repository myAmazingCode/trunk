package net.shopnc.b2b2c.android.ui.order;

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

public class OrderRefundAndReturnListActivity extends BaseActivity {
    @Bind(R.id.tvTabLeft)
    TextView tvTabLeft;
    @Bind(R.id.vTabLeft)
    View vTabLeft;
    @Bind(R.id.rlTabLeft)
    RelativeLayout rlTabLeft;
    @Bind(R.id.tvTabMiddle)
    TextView tvTabMiddle;
    @Bind(R.id.vTabMiddle)
    View vTabMiddle;
    @Bind(R.id.rlTabMiddle)
    RelativeLayout rlTabMiddle;
    @Bind(R.id.tvTabRight)
    TextView tvTabRight;
    @Bind(R.id.vTabRight)
    View vTabRight;
    @Bind(R.id.rlTabRight)
    RelativeLayout rlTabRight;
    @Bind(R.id.flFragment)
    FrameLayout flFragment;

//    @Bind(R.id.rbLeft)
//    RadioButton rbLeft;
//    @Bind(R.id.rbRight)
//    RadioButton rbRight;

    private FragmentManager fragmentManager;
    private OrderRefundAndReturnListFragment moneyListFragment;
    private OrderRefundAndReturnListFragment goodListFragment;
    private OrderRefundAndReturnListFragment complaintListFragment;

    private String flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        moneyListFragment = OrderRefundAndReturnListFragment.newInstance("refund");
        goodListFragment = OrderRefundAndReturnListFragment.newInstance("return");
        complaintListFragment = OrderRefundAndReturnListFragment.newInstance("complaint");
        fragmentManager.beginTransaction()
                .add(R.id.flFragment, moneyListFragment)
                .add(R.id.flFragment, goodListFragment)
                .add(R.id.flFragment, complaintListFragment)
                .commitAllowingStateLoss();
        flag = getIntent().getStringExtra("flag");
        switchShow();
        setBtnMoreVisible();
        getMoreMessage();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_order_refund_list);
    }

    private void switchShow() {
        if (flag.equals("refund")) {
            changeTab(Global.TABLEFT);
        } else if (flag.equals("return")) {
            changeTab(Global.TABMIDDLE);
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
                setCommonHeader("退款列表");
                turnPage(moneyListFragment, goodListFragment, complaintListFragment);
                break;
            case Global.TABMIDDLE:
                tvTabMiddle.setTextColor(ContextCompat.getColor(context, R.color.nc_red));
                vTabMiddle.setVisibility(View.VISIBLE);
                setCommonHeader("退货列表");
                turnPage(goodListFragment, moneyListFragment, complaintListFragment);
                break;
            case Global.TABRIGHT:
                tvTabRight.setTextColor(ContextCompat.getColor(context, R.color.nc_red));
                vTabRight.setVisibility(View.VISIBLE);
                setCommonHeader("投诉列表");
                turnPage(complaintListFragment, moneyListFragment, goodListFragment);
                break;
        }
    }

    /**
     * 复位tab
     */
    private void resetTab() {
        tvTabLeft.setText("退款列表");
        tvTabMiddle.setText("退货列表");
        tvTabRight.setText("投诉列表");
        tvTabLeft.setTextColor(ContextCompat.getColor(context, R.color.nc_text));
        tvTabMiddle.setTextColor(ContextCompat.getColor(context, R.color.nc_text));
        tvTabRight.setTextColor(ContextCompat.getColor(context, R.color.nc_text));
        vTabLeft.setVisibility(View.GONE);
        vTabMiddle.setVisibility(View.GONE);
        vTabRight.setVisibility(View.GONE);
    }


    private void turnPage(Fragment fragment1, Fragment fragment2, Fragment fragment3) {
        fragmentManager.beginTransaction()
                .show(fragment1)
                .hide(fragment2)
                .hide(fragment3)
                .commit();
    }

    @OnClick({R.id.rlTabLeft, R.id.rlTabMiddle, R.id.rlTabRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlTabLeft:
                changeTab(Global.TABLEFT);
                break;
            case R.id.rlTabMiddle:
                changeTab(Global.TABMIDDLE);
                break;
            case R.id.rlTabRight:
                changeTab(Global.TABRIGHT);
                break;
        }
    }
}
