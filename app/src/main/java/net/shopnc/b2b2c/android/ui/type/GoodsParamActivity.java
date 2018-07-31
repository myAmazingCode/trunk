package net.shopnc.b2b2c.android.ui.type;

import java.util.ArrayList;
import java.util.Iterator;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.GoodsParamListViewAdapter;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.custom.XListView;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 
 * @author KingKong-HE
 * @Time 2015-1-6
 * @Email KingKong@QQ.COM
 */
public class GoodsParamActivity extends Activity implements OnClickListener{

	private GoodsParamListViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_param_view);
		MyExceptionHandler.getInstance().setContext(this);
		String goods_attr = getIntent().getStringExtra("goods_attr");

		XListView listViewID = findViewById(R.id.listViewID);
		ImageView imageBack = findViewById(R.id.imageBack);

		adapter = new GoodsParamListViewAdapter(this);

		listViewID.setAdapter(adapter);

		listViewID.setPullLoadEnable(false);
		listViewID.setPullRefreshEnable(false);
		imageBack.setOnClickListener(this);

		getData(goods_attr);// 获取数据
	}

	// 获取数据
	public void getData(String goods_attr) {
		if (goods_attr != null && !goods_attr.equals("") && !goods_attr.equals("null")) {
			try {
				JSONObject jsonSpecName = new JSONObject(goods_attr);
				Iterator<?> itName = jsonSpecName.keys();
				ArrayList<String> arrayList = new ArrayList<String>();
				while (itName.hasNext()) {
					String ID = itName.next().toString();
					String Name = jsonSpecName.getString(ID);
					
					JSONObject json = new JSONObject(Name);
					Iterator<?> it = json.keys();
					
					String str = "";
					
					ArrayList<String> strList = new ArrayList<String>();
					
					while (it.hasNext()) {
						String values = it.next().toString();
						String title = json.getString(values);
						
						strList.add(title);
					}
					
					for(int i = strList.size()-1 ; i >= 0 ; i--){
						str += "："+strList.get(i);
					}
					
					str = str.replaceFirst("：", "");
					arrayList.add(str);
				}
				adapter.setLists(arrayList);
				adapter.notifyDataSetChanged();
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageBack:
			
			finish();
			
			break;

		default:
			break;
		}
	}

}
