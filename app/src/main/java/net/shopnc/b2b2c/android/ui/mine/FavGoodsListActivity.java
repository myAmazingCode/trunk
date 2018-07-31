package net.shopnc.b2b2c.android.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.FavoritesGoodsGridViewAdapter;
import net.shopnc.b2b2c.android.bean.FavoritesList;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.custom.NCDialog;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler.Callback;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ncinterface.INCOnDialogConfirm;
import net.shopnc.b2b2c.android.ncinterface.INCOnItemDel;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author dqw
 * @Time 2015/8/12
 */
public class FavGoodsListActivity extends BaseActivity {

    private MyShopApplication myApplication;
    private Handler mXLHandler;
    public int pageno = 1;
    private ArrayList<FavoritesList> favoritesLists;
    private FavoritesGoodsGridViewAdapter adapter;
    private GridView gvFavGoods;
    private INCOnItemDel incOnItemDel;
    boolean isHasMore = true;
    boolean isLastRow = false;
    private NCDialog ncDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favgoods_list_view);
        MyExceptionHandler.getInstance().setContext(this);
        myApplication = (MyShopApplication) getApplicationContext();
        mXLHandler = new Handler();

        setTabButton();

        gvFavGoods = (GridView) findViewById(R.id.gvFavGoods);
        favoritesLists = new ArrayList<FavoritesList>();
        incOnItemDel = new INCOnItemDel() {
            @Override
            public void onDel(String id, int position) {
                deleteFav(id, position);
            }
        };
        adapter = new FavoritesGoodsGridViewAdapter(FavGoodsListActivity.this, incOnItemDel);
        gvFavGoods.setAdapter(adapter);

        //下拉加载更多
        gvFavGoods.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (isHasMore && isLastRow && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    isLastRow = false;
                    pageno += 1;
                    loadingfavoritesListData();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
                    isLastRow = true;
                }
            }
        });

        loadingfavoritesListData();

        setCommonHeader("");
        setListEmpty(R.drawable.nc_icon_fav_goods, "您还没有关注任何商品", "可以去看看哪些商品值得收藏");
    }

    /**
     * 设置头部切换按钮
     */
    private void setTabButton() {
        Button btnFavGoods = (Button) findViewById(R.id.btnFavGoods);
        btnFavGoods.setActivated(true);
        Button btnFavStore = (Button) findViewById(R.id.btnFavStore);
        btnFavStore.setActivated(false);
        btnFavStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavGoodsListActivity.this, FavStoreListActivity.class));
                finish();
            }
        });
    }

    /**
     * 加载列表数据
     */
    public void loadingfavoritesListData() {
        String url = Constants.URL_FAVORITES_LIST + "&curpage=" + pageno + "&page=" + Constants.PAGESIZE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());

        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {

                    isHasMore = data.isHasMore();

                    if (pageno == 1) {
                        favoritesLists.clear();
                        hideListEmpty();
                    }

                    try {
                        JSONObject obj = new JSONObject(json);
                        String objJson = obj.getString("favorites_list");
                        ArrayList<FavoritesList> fList = FavoritesList.newInstanceList(objJson);
                        if (fList.size() > 0) {
                            favoritesLists.addAll(fList);
                            adapter.setfList(favoritesLists);
                            adapter.notifyDataSetChanged();
                        } else {
                            showListEmpty();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    ShopHelper.showApiError(FavGoodsListActivity.this, json);
                }
            }
        });
    }

    /**
     * 收藏删除
     *
     * @param fav_id 商品ID
     */
    public void deleteFav(final String fav_id, final int position) {

        ncDialog = new NCDialog(FavGoodsListActivity.this);
        ncDialog.setText1("确认删除该商品收藏?");
        ncDialog.setOnDialogConfirm(new INCOnDialogConfirm() {
            @Override
            public void onDialogConfirm() {
                String url = Constants.URL_DELETE_FAV;

                HashMap<String, String> params = new HashMap<String, String>();
                params.put("fav_id", fav_id);
                params.put("key", myApplication.getLoginKey());

                RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new Callback() {
                    @Override
                    public void dataLoaded(ResponseData data) {
                        String json = data.getJson();
                        if (data.getCode() == HttpStatus.SC_OK) {
                            Toast.makeText(FavGoodsListActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                            adapter.removeItem(position);
                            adapter.notifyDataSetChanged();
                        } else {
                            ShopHelper.showApiError(FavGoodsListActivity.this, json);
                        }
                    }
                });
            }
        });
        ncDialog.showPopupWindow();
            }
        }

