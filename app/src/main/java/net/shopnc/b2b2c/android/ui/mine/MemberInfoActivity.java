package net.shopnc.b2b2c.android.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;

import net.shopnc.b2b2c.android.base.BaseTencentX5Activity;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.Constants;

/**
 * @copyright Copyright (c) 2007-2017 ShopNC Inc. All rights reserved.
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 *
 * 会员信息
 *
 * @author dqw
 * Created 2017/4/27 16:47
 */
public class MemberInfoActivity extends BaseTencentX5Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUrl(ConstantUrl.URL_WAP + "/tmpl/member/member_infomation.html");
    }

    /**
     * 跳转到修改真实姓名页面
     */
    @JavascriptInterface
    public void navigateToTrueNameEdit() {
        if (ShopHelper.isLogin(context)) {
            Intent intent = new Intent(context, MemberTrueNameEditActivity.class);
            startActivityForResult(intent, Constants.MEMBER_NAME_EDIT_RESULT);
        }
    }

    /**
     * 跳转到修改会员名页面
     */
    @JavascriptInterface
    public void navigateToMemberNameEdit() {
        if (ShopHelper.isLogin(context)) {
            Intent intent = new Intent(context, MemberNameEditActivity.class);
            startActivityForResult(intent, Constants.MEMBER_NAME_EDIT_RESULT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.MEMBER_NAME_EDIT_RESULT) {
            loadUrl(ConstantUrl.URL_WAP + "/tmpl/member/member_infomation.html");
        }
    }
}
