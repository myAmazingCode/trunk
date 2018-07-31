package net.shopnc.b2b2c.android.ui.points;

import android.os.Bundle;

import net.shopnc.b2b2c.android.util.ConstantUrl;

public class SigninActivity extends BasePointsActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadUrl(ConstantUrl.URL_WAP + "/tmpl/member/signin.html");
    }
}
