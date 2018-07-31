package net.shopnc.b2b2c.android.ui.mine;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.event.MineWithDrawDepositEvent;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.LoadImage;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @author lulei
 *         Created 2017/5/31 14:58
 * @copyright Copyright (c) 2007-2017 ShopNC Inc. All rights reserved.
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * <p>
 * 预存款提现
 */
public class MineWithDrawDepositActivity extends BaseActivity {

    @Bind(R.id.ivMoneyBag)
    ImageView ivMoneyBag;
    @Bind(R.id.mine_deposit_num)
    TextView mineDepositNum;
    @Bind(R.id.etCashWithdrawalAmount)
    EditText etCashWithdrawalAmount;
    @Bind(R.id.etBankName)
    EditText etBankName;
    @Bind(R.id.etBankAccount)
    EditText etBankAccount;
    @Bind(R.id.etUsername)
    EditText etUsername;
    @Bind(R.id.etPassword)
    EditText etPassword;
    @Bind(R.id.llCashWithdrawalAmount)
    LinearLayout llCashWithdrawalAmount;
    @Bind(R.id.btnSubmit)
    Button btnSubmit;

    private String smsAuthCode;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_mine_withdraw_deposit);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("预存款提现");
        getDepositRemain();
        editWatcher();
        smsAuthCode = getIntent().getStringExtra("smsAuthCode");
        LoadImage.loadImageRotated(context, ivMoneyBag, 30f, R.drawable.mcc_06_w);
    }

    /**
     * 提交验证
     */
    private void checkSummit() {
        if (btnSubmit.isActivated()) {
            String cashWithdrawalAmount = etCashWithdrawalAmount.getText().toString();
            String bankName = etBankName.getText().toString();
            String bankAccount = etBankAccount.getText().toString();
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (cashWithdrawalAmount == null || cashWithdrawalAmount.trim().equals("")) {
                TToast.showShort(context, "提现金额不能为空");
                return;
            }
            if (bankName == null || bankName.trim().equals("")) {
                TToast.showShort(context, "收款银行不能为空");
                return;
            }
            if (bankAccount == null || bankAccount.trim().equals("")) {
                TToast.showShort(context, "收款账号不能为空");
                return;
            }
            if (username == null || username.trim().equals("")) {
                TToast.showShort(context, "开户人姓名不能为空");
                return;
            }

            if (password == null || password.trim().equals("")) {
                TToast.showShort(context, "密码不能为空");
                return;
            }

            postCashSave(cashWithdrawalAmount, bankName, bankAccount, username, password, smsAuthCode);
//            postCashSave(cashWithdrawalAmount, bankName, bankAccount, username, password, "");
        }
    }

    /**
     * 处理提交按钮状态
     */
    private void setBtnSubmitState() {
        if (etCashWithdrawalAmount.getText().toString().equals("") || etBankName.getText().toString().equals("") || etBankAccount.getText().toString().equals("") || etUsername.getText().toString().equals("")
                || etPassword.getText().toString().equals("")) {
            btnSubmit.setActivated(false);
        } else {
            btnSubmit.setActivated(true);
        }
    }

    /**
     * 账号和密码输入框的监听
     */
    private void editWatcher() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                setBtnSubmitState();
            }
        };
        etCashWithdrawalAmount.addTextChangedListener(textWatcher);
        etBankName.addTextChangedListener(textWatcher);
        etBankAccount.addTextChangedListener(textWatcher);
        etUsername.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);
    }


    @OnClick(R.id.btnSubmit)
    public void onViewClicked() {
        checkSummit();
    }


    /**
     * 用户存款余额
     */
    private void getDepositRemain() {
        Map<String, String> param = new HashMap<>();
        param.put("token", application.getToken());
        param.put("scope", "predeposit");

        OkHttpUtil.postAsyn(this, ConstantUrl.URL_DEPOSIT_REMAIN, new BeanCallback<String>() {

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


    /**
     * 会员预存款提现申请
     */
    private void postCashSave(String amount, String receiveCompany, String receiveAccount, String receiveUser, String payPwd, String smsAuthCode) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("amount", amount);
        params.put("receiveCompany", receiveCompany);
        params.put("receiveAccount", receiveAccount);
        params.put("receiveUser", receiveUser);
        params.put("payPwd", payPwd);
        params.put("smsAuthCode", smsAuthCode);

        OkHttpUtil.postAsyn(this, ConstantUrl.URL_WITHDRAW_DEPOSIT_CASH_SAVE, new BeanCallback<String>() {

            @Override
            public void response(String data) {

            }


            @Override
            public void onResponse(String response,int i) {
                if (JsonUtil.toInteger(response, "code") == 200) {
                    TToast.showShort(context, "操作成功");
                    EventBus.getDefault().post(new MineWithDrawDepositEvent());
                    finish();
                } else {
                    TToast.showShort(context, JsonUtil.toString(response, "datas", "error"));
                }
            }
        }, params);

    }


}
