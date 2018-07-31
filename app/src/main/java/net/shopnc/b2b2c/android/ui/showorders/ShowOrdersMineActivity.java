package net.shopnc.b2b2c.android.ui.showorders;

import android.os.Bundle;

import net.shopnc.b2b2c.android.util.ConstantUrl;

public class ShowOrdersMineActivity extends BaseShowOrdersActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUrl(ConstantUrl.URL_WAP + "/tmpl/member/show/show_orders_mine.html");
    }
}
