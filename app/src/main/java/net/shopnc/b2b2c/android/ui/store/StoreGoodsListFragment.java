package net.shopnc.b2b2c.android.ui.store;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.GoodsListViewAdapter;
import net.shopnc.b2b2c.android.bean.GoodsList;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.custom.ListViewForScrollView;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler.Callback;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 商品列表Fragment
 * @author dqw
 * @Time 2015-7-10
 */
public class StoreGoodsListFragment extends Fragment{
	
	public String url;
	public int  pageno = 1;
	
	private GoodsListViewAdapter goodsListViewAdapter;
    private ListViewForScrollView listViewID;
	private ArrayList<GoodsList> goodsLists;

    private TextView tvNoResult;
	private TextView textMore;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View viewLayout = inflater.inflate(R.layout.store_goods_fragment_list, container,false);
		MyExceptionHandler.getInstance().setContext(getActivity());
		initViewID(viewLayout);//注册控件ID
		
		return viewLayout;
	}
	
	/** 初始化注册控件ID */
	public void initViewID(View view){
		textMore = view.findViewById(R.id.textMore);
		listViewID = view.findViewById(R.id.listViewID);
		goodsListViewAdapter = new GoodsListViewAdapter(getActivity(),"list");
		goodsLists = new ArrayList<GoodsList>();
		listViewID.setAdapter(goodsListViewAdapter);

		loadingGoodsListData();

        tvNoResult = view.findViewById(R.id.tvNoResult);
	}
	

	
	/** 初始化加载列表数据 */
	public void loadingGoodsListData(){
		
		url = url +"&curpage=" +pageno  + "&page=" +Constants.PAGESIZE;
		
		RemoteDataHandler.asyncDataStringGet(url, new Callback() {
			@Override
			public void dataLoaded(ResponseData data) {

				if (data.getCode() == HttpStatus.SC_OK) {

					String json = data.getJson();

					if (!data.isHasMore()) {
						textMore.setVisibility(View.GONE);
					} else {
						textMore.setVisibility(View.VISIBLE);
						loadMoreData();
					}
					if (pageno == 1) {
						goodsLists.clear();
					}

					try {

						JSONObject obj = new JSONObject(json);
						String array = obj.getString("goods_list");
						  if (array != "" && !array.equals("array") && array != null && !array.equals("[]")) {
							ArrayList<GoodsList> list = GoodsList.newInstanceList(array);
							goodsLists.addAll(list);
							goodsListViewAdapter.setGoodsLists(goodsLists);
							goodsListViewAdapter.notifyDataSetChanged();
						} else {
							if (pageno == 1) {
								tvNoResult.setVisibility(View.VISIBLE);
							}
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					Toast.makeText(getActivity(), R.string.load_error, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	private void loadMoreData(){
		textMore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				pageno = pageno+1;
				loadingGoodsListData();
			}
		});
	}
}
