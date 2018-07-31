package net.shopnc.b2b2c.android.ui.group;

import android.os.Bundle;

import net.shopnc.b2b2c.android.util.ConstantUrl;

public class GroupRuleActivity extends BaseGroupActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int groupId = getIntent().getIntExtra("groupId", 0);
        if (groupId == 0) {
            finish();
        }

        loadUrl(ConstantUrl.URL_WAP + "/tmpl/group_rule.html?groupId=" + groupId);
    }

}
