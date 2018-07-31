package net.shopnc.b2b2c.android.ui.promotion;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;


import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class NewRepoActivity extends BaseActivity {

    @Bind(R.id.etName)
    EditText mEtName;
    @Bind(R.id.btnSave)
    Button mBtnSave;
    private boolean mEdit;
    private String distributorFavoritesId;
    private String distributorFavoritesName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEdit = getIntent().getBooleanExtra("edit", false);
        distributorFavoritesId = getIntent().getStringExtra("distributorFavoritesId");
        distributorFavoritesName = getIntent().getStringExtra("distributorFavoritesName");
        mEtName.setText(distributorFavoritesName);
        if (!TextUtils.isEmpty(distributorFavoritesName)) {
            mBtnSave.setActivated(true);
        }
        setCommonHeader(mEdit ? "编辑选品库分组名称" : "新增选品库分组");
        mEtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(mEtName.getText().toString())) {
                    mBtnSave.setActivated(false);
                } else {
                    mBtnSave.setActivated(true);
                }
            }
        });
    }

    @OnClick({R.id.btnSave})
    public void save() {

        if (mBtnSave.isActivated()) {

            String url = ConstantUrl.URL_API + "/member/distributor/favorites/" + (mEdit ? "modname" : "add");
            Map<String, String> map = new HashMap<>();
            map.put("distributorFavoritesName", mEtName.getText().toString());
            map.put("token", application.getToken());
            if (mEdit) map.put("distributorFavoritesId", distributorFavoritesId);

            OkHttpUtil.postAsyn(this,url, new BeanCallback<String>() {
                @Override
                public void response(String data) {

                }


                @Override
                public void onResponse(String response,int i) {
                    Log.d(TAG, "onResponse: response = " + response);
                    if (200 == JsonUtil.toInteger(response, "code")) {
                        TToast.showShort(application, mEdit ? "修改成功！" : "保存成功！");
                        finish();
                    } else {
                        TToast.showShort(application, JsonUtil.toString(response, "datas", "error"));
                    }
                }
            }, map);
        }
    }

    public static final String TAG = "NewRepo";

    @Override
    protected void setView() {
        setContentView(R.layout.activity_new_repo);
    }
}
