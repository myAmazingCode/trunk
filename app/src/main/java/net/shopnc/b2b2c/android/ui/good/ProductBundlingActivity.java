package net.shopnc.b2b2c.android.ui.good;

import android.app.Activity;
import android.os.Bundle;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseTencentX5Activity;
import net.shopnc.b2b2c.android.util.ConstantUrl;

public class ProductBundlingActivity extends BaseTencentX5Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int commonId = getIntent().getIntExtra("commonId", 0);
        String url = ConstantUrl.URL_WAP + "/tmpl/product_bundling.html?commonId=" + commonId;
        loadUrl(url);
    }
}
