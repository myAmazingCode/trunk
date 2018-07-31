package net.shopnc.b2b2c.android.ui.showorders;

import android.os.Bundle;

import net.shopnc.b2b2c.android.util.ConstantUrl;

public class ShowOrdersListActivity extends BaseShowOrdersActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUrl(ConstantUrl.URL_WAP + "/tmpl/show_orders_list.html");
    }

}
