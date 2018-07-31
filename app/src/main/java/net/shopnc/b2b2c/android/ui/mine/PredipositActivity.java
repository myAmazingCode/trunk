package net.shopnc.b2b2c.android.ui.mine;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;

import butterknife.Bind;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.ui.mine.PredipositActivity.java
 * @author: Jie
 * @date: 2016-05-27 09:13
 */
public class PredipositActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.tvPredeposit)
    TextView tvPredeposit;
    @Bind(R.id.btnPredeposit)
    Button btnPredeposit;
    @Bind(R.id.btnPdrecharge)
    Button btnPdrecharge;
    @Bind(R.id.btnPdcash)
    Button btnPdcash;

    private static final String TAG = "PredipositActivity";

    @Override
    protected void setView() {
        setContentView(R.layout.activity_deposit);
        setCommonHeader("预存款");

        tvPredeposit.setText("");

        btnPredeposit.setOnClickListener(this);
        btnPdrecharge.setOnClickListener(this);
        btnPdcash.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPredeposit:


                break;
            case R.id.btnPdrecharge:


                break;
            case R.id.btnPdcash:


                break;
        }
    }

    private void initFragment(){
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.add(R.id.llFragmentContent, predepositFragment);
//        transaction.add(R.id.llFragmentContent, pdrechargeFragment);
//        transaction.add(R.id.llFragmentContent, pdcashFragment);
//        transaction.commit();
    }

}
