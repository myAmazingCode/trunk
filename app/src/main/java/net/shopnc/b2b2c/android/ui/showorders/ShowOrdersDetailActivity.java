package net.shopnc.b2b2c.android.ui.showorders;

import android.os.Bundle;

import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.util.ConstantUrl;

public class ShowOrdersDetailActivity extends BaseShowOrdersActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id = getIntent().getIntExtra("id", 0);
        if (id == 0) {
            finish();
        }

        String url = ConstantUrl.URL_WAP + "/tmpl/show_orders_detail.html?id=" + id;

        LogHelper.d("ShowOrdersDetail url:" + url);
        loadUrl(url);
    }

}
