package net.shopnc.b2b2c.android.ui.store;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.StoreClassifyAdapter;
import net.shopnc.b2b2c.android.bean.StoreGoodsClassList;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.DateConvert;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler.Callback;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * 店铺内搜索
 *
 * @author 胡婷
 */
public class StoreSearchActivity extends Activity implements OnClickListener{

	final static public Gson gson;

	static {
		gson = new GsonBuilder()
				.registerTypeAdapter(Date.class, new DateConvert())
				.create();
	}

	private StoreClassifyAdapter adapter;
	private String store_goods_class,store_id;
	private ImageButton btnBack;
	private EditText etSearchText;
	private TextView textSearchButton;
	private TextView textSearchAll;
	private ListView lv_category;

	private String keyword;
	private Intent intent;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.store_search_view);
		MyExceptionHandler.getInstance().setContext(this);
		store_id = getIntent().getStringExtra("store_id");

		initView();
		initData(store_id);
	}

	private void initView(){
		btnBack = this.findViewById(R.id.btnBack);
		etSearchText = this.findViewById(R.id.etSearchText);
		textSearchButton = this.findViewById(R.id.textSearchButton);
		textSearchAll = this.findViewById(R.id.textSearchAll);
		lv_category = this.findViewById(R.id.lv_category);
		adapter = new StoreClassifyAdapter(this);
		lv_category.setAdapter(adapter);

		btnBack.setOnClickListener(this);
		etSearchText.setOnClickListener(this);
		textSearchButton.setOnClickListener(this);
		textSearchAll.setOnClickListener(this);
	}
	
	/**
	 * 店铺商品分类
	 * @param store_id 店铺ID
	 * */
	public void initData(final String store_id){
		final ArrayList<StoreGoodsClassList> glist = new ArrayList<StoreGoodsClassList>();
		String url = Constants.URL_STORE_GOODS_CLASS+"&store_id="+store_id;

		RemoteDataHandler.asyncDataStringGet(url, new Callback() {
			@Override
			public void dataLoaded(ResponseData data) {
				if (data.getCode() == HttpStatus.SC_OK) {
					String json = data.getJson();

					if (!json.equals("") && !json.equals("{}") && json != null) {
						lv_category.setVisibility(View.VISIBLE);
						try {
							JSONObject jsonObject = new JSONObject(json);
							store_goods_class = jsonObject.getString("store_goods_class");
							String store_info = jsonObject.getString("store_info");

							JSONArray jsonArray = new JSONArray(store_goods_class);
							for (int i = 0; i < jsonArray.length(); i++) {
								StoreGoodsClassList storeGoodsClassList = gson.fromJson(jsonArray.get(i).toString(), StoreGoodsClassList.class);
								glist.add(storeGoodsClassList);
							}

							adapter.setData(glist);
							adapter.setStoreId(store_id);
							adapter.notifyDataSetChanged();

						} catch (JSONException e) {
							e.printStackTrace();
						}
					} else {
						lv_category.setVisibility(View.GONE);
					}
				} else {
					Toast.makeText(StoreSearchActivity.this, R.string.load_error, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnBack:
			finish();
			break;

		//搜索
		case R.id.textSearchButton:
			keyword = etSearchText.getText().toString();
			if(keyword.trim() == null || keyword.trim().equals("")){
				Toast.makeText(StoreSearchActivity.this, "关键字不能为空", Toast.LENGTH_SHORT).show();
                return ;
			}

			intent =new Intent(StoreSearchActivity.this,StoreGoodsListFragmentManager.class);
			intent.putExtra("keyword", keyword);
			intent.putExtra("store_id", store_id);
			StoreSearchActivity.this.startActivity(intent);
			break;


			//搜素全部
			case R.id.textSearchAll:
				intent =new Intent(StoreSearchActivity.this,StoreGoodsListFragmentManager.class);
				intent.putExtra("store_id", store_id);
				StoreSearchActivity.this.startActivity(intent);
				break;

		default:
			break;
		}
	}
}
