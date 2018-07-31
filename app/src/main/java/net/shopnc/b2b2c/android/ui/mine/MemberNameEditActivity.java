package net.shopnc.b2b2c.android.ui.mine;

import android.content.Intent;
import android.os.Bundle;

import net.shopnc.b2b2c.android.base.BaseTencentX5Activity;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.Constants;
/**
 * @copyright Copyright (c) 2007-2017 ShopNC Inc. All rights reserved.
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 *
 * 会员名修改
 *
 * @author dqw
 * Created 2017/4/27 16:45
 */
public class MemberNameEditActivity extends BaseTencentX5Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadUrl(ConstantUrl.URL_WAP + "/tmpl/member/member_name_edit.html");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Intent intent = getIntent();
        setResult(Constants.MEMBER_NAME_EDIT_RESULT, intent);
    }
}
