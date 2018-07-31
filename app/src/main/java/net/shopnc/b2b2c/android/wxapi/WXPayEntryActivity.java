package net.shopnc.b2b2c.android.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import net.shopnc.b2b2c.android.common.Constants;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

	private IWXAPI api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.pay_result);

		api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
		api.handleIntent(getIntent(), this);

	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			if (resp.errCode == 0) {
				Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
				sendBroadcast(new Intent(Constants.PAYMENT_SUCCESS));
				sendBroadcast(new Intent(Constants.VPAYMENT_SUCCESS));
			}else if(resp.errCode == -2){
				Toast.makeText(this, "没有交易", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
			}
			finish();
		}
	}
}