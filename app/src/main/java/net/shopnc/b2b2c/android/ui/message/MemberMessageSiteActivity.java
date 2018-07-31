package net.shopnc.b2b2c.android.ui.message;

import android.os.Bundle;

import net.shopnc.b2b2c.android.util.ConstantUrl;

public class MemberMessageSiteActivity extends BaseMessageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUrl(ConstantUrl.URL_WAP + "/tmpl/member/member_message_site.html");
    }
}
