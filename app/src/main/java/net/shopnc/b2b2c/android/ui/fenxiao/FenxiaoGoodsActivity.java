package net.shopnc.b2b2c.android.ui.fenxiao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.ta.utdid2.android.utils.StringUtils;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.FenxiaoGoodsMaiAdapter;
import net.shopnc.b2b2c.android.bean.FenxiaoGoods;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.JsonUtil;
import net.shopnc.b2b2c.android.common.LoadImage;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.SwpipeListViewOnScrollListener;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.custom.NCDialog;
import net.shopnc.b2b2c.android.custom.XListView;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.lib.tab.OnItemClickListener;
import net.shopnc.b2b2c.android.ncinterface.INCOnDialogConfirm;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by snm on 2016/9/22.
 */
public class FenxiaoGoodsActivity extends BaseActivity {

    private RecyclerView recyclerView;
    FenxiaoGoodsMaiAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<FenxiaoGoods> list = new ArrayList<FenxiaoGoods>();
    private SwipeRefreshLayout.OnRefreshListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenxiao_goods);
        setCommonHeader("分销商品");
        setListEmpty(R.drawable.nc_icon_order, "没有分销商品", "");
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView)findViewById(R.id.goods_recycler_view);
        adapter = new FenxiaoGoodsMaiAdapter(getApplicationContext());
        LinearLayoutManager linearManager = new LinearLayoutManager(getApplicationContext());
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(final int position) {
                NCDialog ncDialog = new NCDialog(FenxiaoGoodsActivity.this);
                ncDialog.setText1("确认删除该商品吗?");
                ncDialog.setOnDialogConfirm(new INCOnDialogConfirm() {
                    @Override
                    public void onDialogConfirm() {
                        initdata(list.get(position).getDistri_id());
                    }
                });
                ncDialog.showPopupWindow();
            }

            @Override
            public void onLongClick(int position) {

            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);


        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);


        listener = new SwipeRefreshLayout.OnRefreshListener(){
            public void onRefresh(){
                list.clear();
                initdata();
            }
        };
        swipeRefreshLayout.setOnRefreshListener(listener);


        swipeRefreshLayout.post(new Runnable(){
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        listener.onRefresh();
    }

    private void initdata(){
        String url = Constants.URL_DISTRI_GOODS;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", MyShopApplication.getInstance().getLoginKey());

        RemoteDataHandler.asyncLoginPostDataString(url, params, MyShopApplication.getInstance(), new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {

                String json = data.getJson();
                Logger.d(data.toString());
                if (data.getCode() == HttpStatus.SC_OK) {
                   try{
                       JSONObject objError = new JSONObject(json);
                       String goods_list = objError.getString("goods_list");
                       ArrayList<FenxiaoGoods> fenxiaoGoodses = JsonUtil.getBean(goods_list, new TypeToken<ArrayList<FenxiaoGoods>>() {
                       }.getType());
                       list.addAll(fenxiaoGoodses);
                       if(list.isEmpty()){
                           showListEmpty();
                           stopRefreshing();
                           T.showShort(getApplicationContext(),"没有分销商品");
                       }else {
                           hideListEmpty();
                           adapter.setmList(list);
                           stopRefreshing();
                       }
                   }catch (Exception e){
                       showListEmpty();
                       stopRefreshing();
                       e.printStackTrace();
                   }
                } else {
                    stopRefreshing();
                    showListEmpty();
                    ShopHelper.showApiError(getApplicationContext(), json);
                }
            }
        });
    }
    public void stopRefreshing(){
        swipeRefreshLayout.post(new Runnable(){
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initdata(final String distri_id){
        String url = Constants.URL_DROP_GOODS;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", MyShopApplication.getInstance().getLoginKey());
        if(!StringUtils.isEmpty(distri_id)) {
            params.put("distri_id", distri_id);
        }
        RemoteDataHandler.asyncLoginPostDataString(url, params, MyShopApplication.getInstance(), new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {

                String json = data.getJson();
                Logger.d(data.toString());
                if (data.getCode() == HttpStatus.SC_OK) {
                    if("1".equals(json)){
                        T.showShort(getApplicationContext(),"删除成功");
                        list.clear();
                        initdata();
                    }else {
                        T.showShort(getApplicationContext(),"删除失败");
                    }
                } else {
                    ShopHelper.showApiError(getApplicationContext(), json);
                }
            }
        });
    }

}
