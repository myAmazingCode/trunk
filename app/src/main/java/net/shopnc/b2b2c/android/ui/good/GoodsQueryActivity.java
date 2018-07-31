package net.shopnc.b2b2c.android.ui.good;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseTencentX5Activity;
import net.shopnc.b2b2c.android.util.ConstantUrl;

public class GoodsQueryActivity extends BaseTencentX5Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //http://192.168.1.232/devwap/tmpl/product_consultation.html?commonId=107868

        int commonId = getIntent().getIntExtra("commonId", 0);
        String url = ConstantUrl.URL_WAP + "/tmpl/product_consultation.html?commonId=" + commonId;
        loadUrl(url);
    }
}
