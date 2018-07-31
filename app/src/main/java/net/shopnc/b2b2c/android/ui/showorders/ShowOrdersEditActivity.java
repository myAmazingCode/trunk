package net.shopnc.b2b2c.android.ui.showorders;

import android.os.Bundle;

import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.util.ConstantUrl;

public class ShowOrdersEditActivity extends BaseShowOrdersActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id = getIntent().getIntExtra("id", 0);
        if (id == 0) {
            finish();
        }

        String url = ConstantUrl.URL_WAP + "/tmpl/member/show/show_orders_edit.html?id=" + id;

        LogHelper.d("ShowOrdersEdit url:" + url);
        loadUrl(url);
    }
}
