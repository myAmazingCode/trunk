package net.shopnc.b2b2c.android.ui.mine;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class PredepositActivity extends BaseActivity {
    private MyShopApplication myApplication;
    FragmentManager fragmentManager = getFragmentManager();
    PredepositFragment predepositFragment;
    PdrechargeFragment pdrechargeFragment;
    PdcashFragment pdcashFragment;

    private TextView tvPredeposit;
    private Button btnPredeposit;
    private Button btnPdrecharge;
    private Button btnPdcash;
    private LinearLayout llFragmentContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predeposit);
        MyExceptionHandler.getInstance().setContext(this);
        setCommonHeader("预存款");

        myApplication = (MyShopApplication) getApplicationContext();
        tvPredeposit = (TextView) findViewById(R.id.tvPredeposit);
        btnPredeposit = (Button) findViewById(R.id.btnPredeposit);
        btnPredeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPredeposit();
            }
        });
        btnPdrecharge = (Button) findViewById(R.id.btnPdrecharge);
        btnPdrecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPdrecharge();
            }
        });
        btnPdcash = (Button) findViewById(R.id.btnPdcash);
        btnPdcash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPdcash();
            }
        });

        predepositFragment = PredepositFragment.newInstance();
        pdrechargeFragment = PdrechargeFragment.newInstance();
        pdcashFragment = PdcashFragment.newInstance();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.llFragmentContent, predepositFragment);
        transaction.add(R.id.llFragmentContent, pdrechargeFragment);
        transaction.add(R.id.llFragmentContent, pdcashFragment);
        transaction.commit();

        loadPredeposit();
        showPredeposit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPredeposit();
        showPredeposit();
    }

    /**
     * 显示账户余额列表
     */
    private void showPredeposit() {
        setTabButtonState(btnPredeposit);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.show(predepositFragment);
        transaction.hide(pdrechargeFragment);
        transaction.hide(pdcashFragment);
        transaction.commit();
    }

    /**
     * 显示充值明细
     */
    private void showPdrecharge() {
        setTabButtonState(btnPdrecharge);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(predepositFragment);
        transaction.show(pdrechargeFragment);
        transaction.hide(pdcashFragment);
        transaction.commit();
    }

    /**
     * 显示余额提现
     */
    private void showPdcash() {
        setTabButtonState(btnPdcash);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(predepositFragment);
        transaction.hide(pdrechargeFragment);
        transaction.show(pdcashFragment);
        transaction.commit();
    }

    /**
     * 设置切换按钮状态
     */
    private void setTabButtonState(Button btn) {
        btnPredeposit.setActivated(false);
        btnPdrecharge.setActivated(false);
        btnPdcash.setActivated(false);
        btn.setActivated(true);
    }

    /**
     * 读取预存款
     */
    private void loadPredeposit() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        RemoteDataHandler.asyncLoginPostDataString(Constants.URL_MEMBER_MY_ASSET + "&fields=predepoit", params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        tvPredeposit.setText("¥" + obj.optString("predepoit"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_predeposit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
