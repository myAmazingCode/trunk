package net.shopnc.b2b2c.android.ui.message;

import android.os.Bundle;

import net.shopnc.b2b2c.android.util.ConstantUrl;

public class MemberMessageActivity extends BaseMessageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int tplClass = getIntent().getIntExtra("tplClass", 0);
        if (tplClass == 0) {
            finish();
        }
        loadUrl(ConstantUrl.URL_WAP + "/tmpl/member/member_message.html?tplClass=" + tplClass);
    }
}
