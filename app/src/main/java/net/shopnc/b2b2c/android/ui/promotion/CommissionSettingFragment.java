package net.shopnc.b2b2c.android.ui.promotion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.DistributorInfo;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommissionSettingFragment extends Fragment {


    @Bind(R.id.tvName)
    TextView mTvName;
    @Bind(R.id.tvPhone)
    TextView mTvPhone;
    @Bind(R.id.tvIDCard)
    TextView mTvIDCard;
    @Bind(R.id.etPayPerson)
    EditText mEtPayPerson;
    @Bind(R.id.etAccount)
    EditText mEtAccount;
    @Bind(R.id.etBankName)
    EditText mEtBankName;
    @Bind(R.id.tvApply)
    TextView mTvApply;
    @Bind(R.id.rbBank)
    RadioButton mRbBank;
    @Bind(R.id.rbAlipay)
    RadioButton mRbAlipay;

    @Bind(R.id.llBank)
    LinearLayout llBank;
    private String mPaypseron;
    private String mAccount;
    private String mBankName;

    public CommissionSettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_commission_setting, container, false);
        ButterKnife.bind(this, view);
        initData();

        mRbBank.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkInput();
                if (isChecked) {
                    llBank.setVisibility(View.VISIBLE);
                } else {
                    llBank.setVisibility(View.GONE);
                }
            }
        });

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkInput();
            }
        };
        mEtBankName.addTextChangedListener(watcher);
        mEtAccount.addTextChangedListener(watcher);
        mEtPayPerson.addTextChangedListener(watcher);
        return view;
    }

    /**
     * 检查输入
     */
    private void checkInput() {
        mPaypseron = mEtPayPerson.getText().toString();
        mAccount = mEtAccount.getText().toString();
        mBankName = mEtBankName.getText().toString();

        if (mRbBank.isChecked()) {
            if (TextUtils.isEmpty(mPaypseron) || TextUtils.isEmpty(mAccount) || TextUtils.isEmpty(mBankName)) {
                mTvApply.setActivated(false);
            } else {
                mTvApply.setActivated(true);
            }
        } else {
            if (TextUtils.isEmpty(mPaypseron) || TextUtils.isEmpty(mAccount)) {
                mTvApply.setActivated(false);
            } else {
                mTvApply.setActivated(true);
            }
        }
    }

    private void initData() {
        String token = MyShopApplication.getInstance().getToken();

        OkHttpUtil.postAsyn(getActivity(), ConstantUrl.DISTRIBUTOR_JOIN_INFO, new BeanCallback<String>() {

            @Override
            public void response(String data) {
                DistributorInfo distributorInfo = JsonUtil.toBean(data, DistributorInfo.class);

                DistributorInfo.DistributorInfoBean info = distributorInfo.getDistributorInfo();
                if (info != null) {
                    mTvName.setText(info.getRealName());
                    mTvPhone.setText(info.getBindPhone());
                    mTvIDCard.setText(info.getIdCartNumber());

                    mEtPayPerson.setText(info.getPayPerson());
                    mEtAccount.setText(info.getBankAccountNumber());
                    mEtBankName.setText(info.getBankAccountName());
                }
            }
        }, new OkHttpUtil.Param("token", token));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.etBankName, R.id.tvApply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.etBankName:
                break;
            case R.id.tvApply:
                submit();
                break;
        }
    }


    private void submit() {
        if (!mTvApply.isActivated()) return;

        String url = ConstantUrl.URL_API + "/member/distributor/setting";
        Map<String, String> map = new HashMap<>();
        map.put("token", MyShopApplication.getInstance().getToken());
        map.put("payPerson", mPaypseron);
        if (mRbBank.isChecked()) {
            map.put("accountType", "bank");
            map.put("bankAccountName", mBankName);
        } else {
            map.put("accountType", "alipay");
        }
        map.put("bankAccountNumber", mAccount);

        OkHttpUtil.postAsyn(getActivity(), url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                TToast.showShort(getActivity(), "修改成功");
            }
        }, map);
    }
}
