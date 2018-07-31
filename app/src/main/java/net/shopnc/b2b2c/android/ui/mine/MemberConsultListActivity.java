package net.shopnc.b2b2c.android.ui.mine;

import android.os.Bundle;

import net.shopnc.b2b2c.android.base.BaseTencentX5Activity;
import net.shopnc.b2b2c.android.util.ConstantUrl;

public class MemberConsultListActivity extends BaseTencentX5Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadUrl(ConstantUrl.URL_WAP + "/tmpl/member/member_consult_list.html");
    }
}
