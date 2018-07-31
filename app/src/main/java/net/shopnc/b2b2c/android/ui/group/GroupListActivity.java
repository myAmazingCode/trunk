package net.shopnc.b2b2c.android.ui.group;

import android.os.Bundle;

import net.shopnc.b2b2c.android.util.ConstantUrl;

public class GroupListActivity extends BaseGroupActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUrl(ConstantUrl.URL_WAP + "/tmpl/group_list.html");
    }

}
