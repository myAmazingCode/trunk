package net.shopnc.b2b2c.android.ui.type;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.GoodsListViewAdapter;
import net.shopnc.b2b2c.android.bean.GoodsList;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.custom.XListView;
import net.shopnc.b2b2c.android.custom.XListView.IXListViewListener;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler.Callback;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 商品列表Fragment
 *
 * @author dqw
 * @Time 2015-7-10
 */
public class GoodsListFragment extends Fragment implements IXListViewListener {

    public String url;
    public int pageno = 1;

    private GoodsListViewAdapter goodsListViewAdapter;

    private Handler mXLHandler;

    private XListView listViewID;

    private ArrayList<GoodsList> goodsLists;

    private TextView tvNoResult;
    private RelativeLayout rl_main_list;
    private Button top_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewLayout = inflater.inflate(R.layout.goods_fragment_list, container, false);
        MyExceptionHandler.getInstance().setContext(getActivity());
        initViewID(viewLayout);//注册控件ID

        return viewLayout;
    }

    /**
     * 初始化注册控件ID
     */
    public void initViewID(View view) {

        listViewID = view.findViewById(R.id.listViewID);
        rl_main_list = view.findViewById(R.id.rl_main_list);
        top_btn = view.findViewById(R.id.top_btn);
        goodsListViewAdapter = new GoodsListViewAdapter(getActivity(), "list");

        goodsLists = new ArrayList<GoodsList>();

        listViewID.setAdapter(goodsListViewAdapter);

        loadingGoodsListData();

        listViewID.setXListViewListener(this);
        listViewID.setPullRefreshEnable(false);//禁止下拉刷新
        mXLHandler = new Handler();
        listViewID.setOnScrollListener(new XListView.OnXScrollListener() {
            @Override
            public void onXScrolling(View view) {

            }

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                int top = absListView.getTop();
                int scrollheight = -top + i * absListView.getHeight();
                if (scrollheight >= rl_main_list.getBottom()) {
                    top_btn.setVisibility(View.VISIBLE);
                } else {
                    top_btn.setVisibility(View.GONE);
                }
            }
        });

        tvNoResult = view.findViewById(R.id.tvNoResult);

        top_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                top_btn.setVisibility(View.GONE);
                listViewID.setSelectionFromTop(0, 0);
            }
        });
    }

    @Override
    public void onRefresh() {
        //下拉刷新
    }

    @Override
    public void onLoadMore() {
        //上拉加载
        mXLHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pageno = pageno + 1;
                loadingGoodsListData();
            }
        }, 1000);
    }

    /**
     * 初始化加载列表数据
     */
    public void loadingGoodsListData() {
        url = url + "&curpage=" + pageno + "&page=" + Constants.PAGESIZE;
        Logger.d(url);
        RemoteDataHandler.asyncDataStringGet(url, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {

                listViewID.stopLoadMore();

                if (data.getCode() == HttpStatus.SC_OK) {
                    String json = data.getJson();
                    if (!data.isHasMore()) {
                        listViewID.setPullLoadEnable(false);
                    } else {
                        listViewID.setPullLoadEnable(data.isHasMore());
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
}
