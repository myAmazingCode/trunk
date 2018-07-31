package net.shopnc.b2b2c.android.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.shopnc.b2b2c.R;


/**
 * 等待对话框
 * 
 * @author HJGANG
 */
public class MyUpdateDialog extends Dialog {
	public Button btu_on;
	public Button btu_off;
	public ProgressBar progress;
	public TextView shuzhi,tv2;

	public MyUpdateDialog(Context context) {
		super(context, R.style.MyProgressDialog);
		this.setContentView(R.layout.my_update);

		btu_on = findViewById(R.id.btu_on);
		btu_off = findViewById(R.id.btu_off);
		progress = findViewById(R.id.progress);
		shuzhi = findViewById(R.id.shuzhi);
		tv2 = findViewById(R.id.tv2);
	}
	public void setTv2(String text){
		tv2.setText(text);
	}
}
