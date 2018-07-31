package net.shopnc.b2b2c.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.ui.order.PaySuccessActivity;
import net.shopnc.b2b2c.android.util.Constants;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

	private IWXAPI api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.pay_result);

		api = WXAPIFactory.createWXAPI(this, Constants.WX_APP_ID);
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
				//支付成功
				Intent intent = new Intent(this, PaySuccessActivity.class);
				intent.putExtra("pay_type", 0);
				startActivity(intent);

//				TToast.showShort(this, "支付成功");
//				EventBus.getDefault().post(1);
//				sendBroadcast(new Intent("duccessful"));
//				sendBroadcast(new Intent("fail"));
			}else if(resp.errCode == -2){
				TToast.showShort(this, "没有交易");
			}else{
				TToast.showShort(this, "支付失败");
			}
			finish();
		}
	}
}