package net.shopnc.b2b2c.android.custom;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.CityList;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

/**
 * 自定义发票弹出窗口
 * @author HJGANG
 */
public class MyAddInvoice extends Dialog {
	public Button btu_on;
	public Button btu_off;
	public RadioButton add_invoice_geren;
	public RadioButton add_invoice_danwei;
	public EditText add_invoice_edit_danwei_name;
	public Spinner add_invoice_spinner_context;
	
	public String inv_title_select = "person";//发票类型，可选值：person(个人) company(单位)
	public String inv_content;//发票内容

	public MyAddInvoice(Context context) {
		super(context, R.style.MyProgressDialog);
		this.setContentView(R.layout.my_add_invoic);

		btu_on = findViewById(R.id.btu_on);
		btu_off = findViewById(R.id.btu_off);
		add_invoice_geren = findViewById(R.id.add_invoice_geren);
		add_invoice_danwei = findViewById(R.id.add_invoice_danwei);
		add_invoice_edit_danwei_name = findViewById(R.id.add_invoice_edit_danwei_name);
		add_invoice_spinner_context = findViewById(R.id.add_invoice_spinner_context);

		MyRadioButtonClickListener listener = new MyRadioButtonClickListener();
		add_invoice_geren.setOnClickListener(listener);
		add_invoice_danwei.setOnClickListener(listener);
		
		add_invoice_spinner_context.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				CityList bean = (CityList) add_invoice_spinner_context.getItemAtPosition(arg2);
				
				if(bean !=null ){
					inv_content = bean.getArea_name();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});

	}
	
	class MyRadioButtonClickListener implements View.OnClickListener {
		public void onClick(View v) {
			RadioButton btn = (RadioButton) v;
			switch (btn.getId()) {
			case R.id.add_invoice_geren:
				inv_title_select = "person";
				add_invoice_edit_danwei_name.setVisibility(View.GONE);
				break;
			case R.id.add_invoice_danwei:
				inv_title_select = "company";
				add_invoice_edit_danwei_name.setVisibility(View.VISIBLE);
				break;
			}
		}
	}
}
