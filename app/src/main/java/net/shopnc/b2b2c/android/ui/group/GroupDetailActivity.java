package net.shopnc.b2b2c.android.ui.group;

import android.os.Bundle;

import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.util.ConstantUrl;

public class GroupDetailActivity extends BaseGroupActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int commonId = getIntent().getIntExtra("commonId", 0);
        if (commonId == 0) {
            finish();
        }

        String url = ConstantUrl.URL_WAP + "/tmpl/group_detail.html?commonId=" + commonId;

        int goId = getIntent().getIntExtra("goId", 0);
        if (goId > 0) {
            url += "&goId=" + String.valueOf(goId);
        }

        LogHelper.d("GroupDetail url:" + url);
        loadUrl(url);
    }
}
