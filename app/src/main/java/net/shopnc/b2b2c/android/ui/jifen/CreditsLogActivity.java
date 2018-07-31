package net.shopnc.b2b2c.android.ui.jifen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.CreditsLogBean;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import java.util.HashMap;

/**
 *
 */
public class CreditsLogActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_credits_log);
    }


}
