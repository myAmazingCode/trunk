package net.shopnc.b2b2c.android.ui.group;

import android.os.Bundle;

import net.shopnc.b2b2c.android.util.ConstantUrl;

public class GroupShareActivity extends BaseGroupActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int goId = getIntent().getIntExtra("goId", 0);
        if (goId == 0) {
            finish();
        }

        loadUrl(ConstantUrl.URL_WAP + "/tmpl/group_share.html?goId=" + goId);
    }

}
