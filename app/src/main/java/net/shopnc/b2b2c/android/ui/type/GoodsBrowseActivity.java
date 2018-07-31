package net.shopnc.b2b2c.android.ui.type;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.GoodsBrowseListViewAdapter;
import net.shopnc.b2b2c.android.bean.GoodsBrowseInfo;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GoodsBrowseActivity extends BaseActivity {

    private MyShopApplication myApplication;
    public int pageno = 1;
    private ArrayList<GoodsBrowseInfo> goodsBrowseList;
    private GoodsBrowseListViewAdapter adapter;
    private ListView lvGoodsBrowse;
    boolean isHasMore = true;
    boolean isLastRow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_browse);
        MyExceptionHandler.getInstance().setContext(this);
        myApplication = (MyShopApplication) getApplicationContext();

        lvGoodsBrowse = (ListView) findViewById(R.id.lvGoodsBrowse);

        goodsBrowseList = new ArrayList<GoodsBrowseInfo>();
        adapter = new GoodsBrowseListViewAdapter(GoodsBrowseActivity.this);
        lvGoodsBrowse.setAdapter(adapter);
        lvGoodsBrowse.setAdapter(adapter);
        loadGoodsBorwse();
        lvGoodsBrowse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GoodsBrowseInfo bean = (GoodsBrowseInfo) lvGoodsBrowse.getItemAtPosition(i);
                Intent intent = new Intent(GoodsBrowseActivity.this, GoodsDetailsActivity.class);
                intent.putExtra("goods_id", bean.getGoodsId());
                startActivity(intent);
            }
        });
        lvGoodsBrowse.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (isHasMore && isLastRow && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    isLastRow = false;
                    pageno += 1;
                    loadGoodsBorwse();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if ((firstVisibleItem + visibleItemCount) == totalItemCount && totalItemCount > 0) {
                    isLastRow = true;
                }
            }
        });

        loadGoodsBorwse();

        setCommonHeader("浏览记录");
        setListEmpty(R.drawable.nc_icon_goods_browse, "暂无您的浏览记录", "可以去看看哪些需要买的");
    }

    /**
     * 加载列表数据
     */
    public void loadGoodsBorwse() {
        String url = Constants.URL_GOODS_BROWSE + "&curpage=" + pageno + "&page=" + Constants.PAGESIZE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());

        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {

                    isHasMore = data.isHasMore();

                    if (pageno == 1) {
                        goodsBrowseList.clear();
                        hideListEmpty();
                    }

                    try {
                        JSONObject obj = new JSONObject(json);
                        String objJson = obj.getString("goodsbrowse_list");
                        ArrayList<GoodsBrowseInfo> list = GoodsBrowseInfo.newInstanceList(objJson);
                        if (list.size() > 0) {
                            goodsBrowseList.addAll(list);
                            adapter.setlist(goodsBrowseList);
                            adapter.notifyDataSetChanged();
                        } else {
                            showListEmpty();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    ShopHelper.showApiError(GoodsBrowseActivity.this, json);
                }
            }
        });
    }

    /**
     * 清空浏览记录
     */
    public void btnGoodsBrowseClearClick(View view) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());

        RemoteDataHandler.asyncLoginPostDataString(Constants.URL_GOODS_BROWSE_CLEAR, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    goodsBrowseList.clear();
                    adapter.notifyDataSetChanged();
                    showListEmpty();
                } else {
                    ShopHelper.showApiError(GoodsBrowseActivity.this, json);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_goods_browse, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
