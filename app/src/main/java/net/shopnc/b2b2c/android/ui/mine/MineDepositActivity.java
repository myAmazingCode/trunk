package net.shopnc.b2b2c.android.ui.mine;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.event.MineWithDrawDepositEvent;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.Global;
import net.shopnc.b2b2c.android.util.LoadImage;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 预存款
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.ui.mine.MineDepositActivity.java
 * @author: Jie
 * @date: 2016-05-29 12:23
 */
public class MineDepositActivity extends BaseActivity {

    @Bind(R.id.ivMoneyBag)
    ImageView ivMoneyBag;
    @Bind(R.id.mine_deposit_num)
    TextView mineDepositNum;
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
    @Bind(R.id.mine_deposit_fragment_container)
    FrameLayout mineDepositFragmentContainer;
    private MineDepositFragment depositFragment;
    private MineRechargeFragment rechargeFragment;
    private MineWithDrawDepositFragment drawDepositFragment;

    private FragmentManager fragmentManager;

    private int index = 0;

    private String depositRemain;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getFragmentManager();
        depositFragment = new MineDepositFragment();
        rechargeFragment = new MineRechargeFragment();
        drawDepositFragment = new MineWithDrawDepositFragment();
        fragmentManager.beginTransaction()
                .add(R.id.mine_deposit_fragment_container, depositFragment)
                .add(R.id.mine_deposit_fragment_container, rechargeFragment)
                .add(R.id.mine_deposit_fragment_container, drawDepositFragment)
                .commitAllowingStateLoss();

        getDepositRemain();
        changeTab(Global.TABLEFT);
        LoadImage.loadImageRotated(context, ivMoneyBag, 30f, R.drawable.mcc_06_w);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void setView() {
        setContentView(R.layout.mine_deposit_activity);
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
                setCommonHeader("预存款账户");
                turnPage(depositFragment, rechargeFragment, drawDepositFragment);
                break;
            case Global.TABMIDDLE:
                tvTabMiddle.setTextColor(ContextCompat.getColor(context, R.color.nc_red));
                vTabMiddle.setVisibility(View.VISIBLE);
                setCommonHeader("预存款充值");
                turnPage(rechargeFragment, depositFragment, drawDepositFragment);
                break;
            case Global.TABRIGHT:
                tvTabRight.setTextColor(ContextCompat.getColor(context, R.color.nc_red));
                vTabRight.setVisibility(View.VISIBLE);
                setCommonHeader("预存款提现");
                turnPage(drawDepositFragment, rechargeFragment, depositFragment);
                break;
        }
    }

    /**
     * 复位tab
     */
    private void resetTab() {
        tvTabLeft.setText("账户余额");
        tvTabMiddle.setText("充值明细");
        tvTabRight.setText("余额提现");
        tvTabLeft.setTextColor(ContextCompat.getColor(context, R.color.nc_text));
        tvTabMiddle.setTextColor(ContextCompat.getColor(context, R.color.nc_text));
        tvTabRight.setTextColor(ContextCompat.getColor(context, R.color.nc_text));
        vTabLeft.setVisibility(View.GONE);
        vTabMiddle.setVisibility(View.GONE);
        vTabRight.setVisibility(View.GONE);
    }


    /**
     * 切换界面
     *
     * @param fragment1 witch will be show
     * @param fragment2 hide
     * @param fragment3 hide
     */
    private void turnPage(MineBaseFragment fragment1, MineBaseFragment fragment2, MineBaseFragment fragment3) {
        fragmentManager.beginTransaction()
                .show(fragment1)
                .hide(fragment2)
                .hide(fragment3)
                .commitAllowingStateLoss();
    }

    /**
     * 用户存款余额
     */
    private void getDepositRemain() {
        Map<String, String> param = new HashMap<>();
        param.put("token", application.getToken());
        param.put("scope", "predeposit");

        OkHttpUtil.postAsyn(this,ConstantUrl.URL_DEPOSIT_REMAIN, new BeanCallback<String>() {

            @Override
            public void response(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    DecimalFormat df = new DecimalFormat("#0.00");//格式化
                    double d = jsonObject.getDouble("predepositAvailable");
                    mineDepositNum.setText(df.format(d));
                } catch (Exception e) {
                    LogHelper.e(e.getMessage());
                }
            }
        }, param);
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

    public void onEventMainThread(MineWithDrawDepositEvent event) {
        getDepositRemain();
    }
}
