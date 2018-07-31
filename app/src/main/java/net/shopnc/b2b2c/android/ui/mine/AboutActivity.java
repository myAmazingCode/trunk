package net.shopnc.b2b2c.android.ui.mine;

import android.os.Bundle;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;

/**
 * 关于我们页面
 * @author KingKong-HE
 * @Time 2015-2-1
 * @Email KingKong@QQ.COM
 */
public class AboutActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_about);
		MyExceptionHandler.getInstance().setContext(this);
		setCommonHeader("关于我们");
	}
}
