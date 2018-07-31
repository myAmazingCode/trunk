package net.shopnc.b2b2c.android.ui.type;

import java.util.ArrayList;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.TwoTypeExpandableListViewAdapter;
import net.shopnc.b2b2c.android.bean.TwoType;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler.Callback;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 二级分类界面
 * @author KingKong-HE
 * @Time 2015-1-4
 * @Email KingKong@QQ.COM
 */
public class TwoTypeActivity extends Activity implements OnClickListener{
	
	private ExpandableListView typeNextExpandableListView;
	
	private TwoTypeExpandableListViewAdapter adapter;
	
	private String gc_id ;
	
	private String gc_name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.two_type_view);
		MyExceptionHandler.getInstance().setContext(this);
		gc_id = TwoTypeActivity.this.getIntent().getStringExtra("gc_id");//分类ID
		
		gc_name = TwoTypeActivity.this.getIntent().getStringExtra("gc_name");//分类Name
		
		initViewID();// 初始化注册控件ID
	}
	
	/** 初始化注册控件ID */
	public void initViewID(){
		
		typeNextExpandableListView = findViewById(R.id.typeNextExpandableListViewID);
		
		TextView textTypeTitleName = findViewById(R.id.textTypeTitleName);
		
		ImageView imageBack = findViewById(R.id.imageBack);
		
		adapter = new TwoTypeExpandableListViewAdapter(TwoTypeActivity.this);
		
		typeNextExpandableListView.setAdapter(adapter);
		
		textTypeTitleName.setText(gc_name == null ? "" : gc_name);
		
		loadingTypeNextGData(gc_id);//初始化加载二级分类数据
		
		imageBack.setOnClickListener(this);
		
		//二级分类点击监听
		typeNextExpandableListView.setOnGroupExpandListener(new OnGroupExpandListener() {
			@Override
			public void onGroupExpand(int groupPosition) {
				for (int i = 0; i < adapter.getGroupCount(); i++){
		          if (groupPosition != i){
		        	  typeNextExpandableListView.collapseGroup(i);
		          }
		        }
				TwoType bean =(TwoType) TwoTypeActivity.this.adapter.getGroup(groupPosition);
				
				if(bean != null){

					loadingTypeNextCData(bean.getGc_id());//初始化加载三级分类数据

				}
			}
		});
		
		//三级分类点击监听
		typeNextExpandableListView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				
				TwoType bean =(TwoType) adapter.getChild(groupPosition, childPosition);
				
				if(bean != null ){
					
					Intent intent =new Intent(TwoTypeActivity.this,GoodsListFragmentManager.class);
					intent.putExtra("gc_id", bean.getGc_id());
					intent.putExtra("gc_name", bean.getGc_name());
					startActivity(intent);
					
				}
				
				return false;
			}
		});
	}
	
	/** 初始化加载二级分类数据 */
	public void loadingTypeNextGData(String gc_id){
		
		String url = Constants.URL_GOODSCLASS+"&gc_id="+gc_id;
		
		RemoteDataHandler.asyncDataStringGet(url, new Callback() {
			@Override
			public void dataLoaded(ResponseData data) {
				if(data.getCode() == HttpStatus.SC_OK){
					String json = data.getJson();
					try {
						JSONObject obj = new JSONObject(json);
						String array = obj.getString("class_list");
						ArrayList<TwoType> typeNextList = TwoType.newInstanceList(array);
						adapter.setTypeNextGList(typeNextList);
						adapter.notifyDataSetChanged();
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}else{
					Toast.makeText(TwoTypeActivity.this, R.string.load_error, Toast.LENGTH_SHORT).show();
                }
			}
		});
	}
	
	/** 初始化加载三级分类数据 */
	public void loadingTypeNextCData(final String gc_id){
		
		String url = Constants.URL_GOODSCLASS+"&gc_id="+gc_id;
		
		RemoteDataHandler.asyncDataStringGet(url, new Callback() {
			@Override
			public void dataLoaded(ResponseData data) {
				if(data.getCode() == HttpStatus.SC_OK){
					String json = data.getJson();
					try {
						JSONObject obj = new JSONObject(json);
						String array = obj.getString("class_list");
						ArrayList<TwoType> typeNextList = new ArrayList<TwoType>();
						typeNextList.add(new TwoType(gc_id,"全部商品"));
						typeNextList.addAll(TwoType.newInstanceList(array));
						adapter.setTypeNextCList(typeNextList);
						adapter.notifyDataSetChanged();
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}else{
					Toast.makeText(TwoTypeActivity.this, R.string.load_error, Toast.LENGTH_SHORT).show();
				}
			}
		});
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
