package net.shopnc.b2b2c.android.base;


import android.os.Bundle;

/**
 * @author lulei
 *         Created 2017/5/16 9:10
 * @copyright Copyright (c) 2007-2017 ShopNC Inc. All rights reserved.
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * 带更多按钮的BaseActivity
 */
public class BaseMoreMenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMoreMessage();
    }

    @Override
    protected void setView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        getMoreMessage();
    }

}
