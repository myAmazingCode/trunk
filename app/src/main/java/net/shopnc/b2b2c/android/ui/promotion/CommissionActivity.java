package net.shopnc.b2b2c.android.ui.promotion;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.ui.mine.MineDepositFragment;
import net.shopnc.b2b2c.android.ui.mine.MineRechargeFragment;
import net.shopnc.b2b2c.android.ui.mine.MineWithDrawDepositFragment;

import butterknife.Bind;
import butterknife.OnClick;

public class CommissionActivity extends BaseActivity implements BalanceFragment.OnSumListener {

    @Bind(R.id.deposit_btnRemain)
    RadioButton deposit_btnRemain;
    @Bind(R.id.deposit_btnWithDraw)
    RadioButton deposit_btnWithDraw;
    @Bind(R.id.rbSettings)
    RadioButton rbSettings;
    @Bind(R.id.tvBalance)
    TextView mTvBalance;
    @Bind(R.id.flFragment)
    FrameLayout mFlFragment;
    private FragmentManager mFragmentManager;
    private BalanceFragment mBalanceFragment;
    private WithdrawFragment mWithdrawFragment;
    private CommissionSettingFragment mCommissionSettingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("佣金");
        mFragmentManager = getSupportFragmentManager();
        showBalance();
    }

    @OnClick({R.id.deposit_btnRemain, R.id.deposit_btnWithDraw, R.id.rbSettings})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.deposit_btnRemain:
                showBalance();
                break;
            case R.id.deposit_btnWithDraw:
                showWithdraw();
                break;
            case R.id.rbSettings:
                showSettings();
                break;
        }
    }

    //显示账户设置
    private void showSettings() {
        rbSettings.setChecked(true);
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (mCommissionSettingFragment == null) {
            mCommissionSettingFragment = new CommissionSettingFragment();
            ft.add(R.id.flFragment, mCommissionSettingFragment, "CommissionSettingFragment");
        }

        ft.show(mCommissionSettingFragment);
        if (mWithdrawFragment != null) {
            ft.hide(mWithdrawFragment);
        }
        if (mBalanceFragment != null) {
            ft.hide(mBalanceFragment);
        }
        ft.commit();
    }

    private void showBalance() {
        deposit_btnRemain.setChecked(true);
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (mBalanceFragment == null) {
            mBalanceFragment = new BalanceFragment();
            ft.add(R.id.flFragment, mBalanceFragment, "BalanceFragment");
            mBalanceFragment.setOnSumListener(this);
        }

        ft.show(mBalanceFragment);
        if (mWithdrawFragment != null) {
            ft.hide(mWithdrawFragment);
        }
        if (mCommissionSettingFragment != null) {
            ft.hide(mCommissionSettingFragment);
        }
        ft.commit();
    }

    private void showWithdraw() {
        deposit_btnWithDraw.setChecked(true);
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (mWithdrawFragment == null) {
            mWithdrawFragment = new WithdrawFragment();
            ft.add(R.id.flFragment, mWithdrawFragment, "WithdrawFragment");
        }

        ft.show(mWithdrawFragment);
        if (mBalanceFragment != null) {
            ft.hide(mBalanceFragment);
        }

        if (mCommissionSettingFragment != null) {
            ft.hide(mCommissionSettingFragment);
        }
        ft.commit();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_commission);
    }

    @Override
    public void onSum(String sum) {
        float value = Float.parseFloat(sum);
        mTvBalance.setText(String.format("%.2f", value));
//        mTvBalance.setText(sum);
    }
}
