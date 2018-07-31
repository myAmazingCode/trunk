package net.shopnc.b2b2c.android.ui.promotion;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class AuthenticationStep3Activity extends BaseActivity {

    @Bind(R.id.tvIndicatorStep3)
    TextView mTvIndicatorStep3;
    @Bind(R.id.tvStep3)
    TextView mTvStep3;
    private boolean mUserFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("个人实名认证");
        mTvIndicatorStep3.setVisibility(View.VISIBLE);
        mTvStep3.setSelected(true);


        //查看页面
        mUserFlag = getIntent().getBooleanExtra("userFlag", false);

    }

    @OnClick({R.id.tvStep1, R.id.tvStep2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvStep1:
                if (!mUserFlag) {
                    finish();
                }
                break;
            case R.id.tvStep2:

                break;
        }
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_authentication_step3);
    }
}
