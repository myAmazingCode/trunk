package net.shopnc.b2b2c.android.ui.mine;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.PdcashListViewAdapter;
import net.shopnc.b2b2c.android.bean.PdcashInfo;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.custom.MyListEmpty;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 余额提现列表Fragment
 *
 * @author dqw
 * @Time 2015-8-25
 */
public class PdcashFragment extends Fragment {
    MyShopApplication myApplication;
    ListView lvPdcash;
    ArrayList<PdcashInfo> pdcashInfoArrayList;
    PdcashListViewAdapter pdcashListViewAdapter;
    MyListEmpty myListEmpty;

    int currentPage = 1;
    boolean isHasMore = true;
    boolean isLastRow = false;

    public static PdcashFragment newInstance() {
        PdcashFragment fragment = new PdcashFragment();
        return fragment;
    }

    public PdcashFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApplication = (MyShopApplication) getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_pdcash, container, false);
        MyExceptionHandler.getInstance().setContext(getActivity());
        lvPdcash = layout.findViewById(R.id.lvPdcash);
        lvPdcash.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (isHasMore && isLastRow && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    isLastRow = false;
                    currentPage += 1;
                    loadPdcash();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
                    isLastRow = true;
                }
            }
        });
        pdcashInfoArrayList = new ArrayList<PdcashInfo>();
        pdcashListViewAdapter = new PdcashListViewAdapter(getActivity());
        lvPdcash.setAdapter(pdcashListViewAdapter);
        myListEmpty = layout.findViewById(R.id.myListEmpty);
        myListEmpty.setListEmpty(R.drawable.nc_icon_predeposit_white, "您尚未提现过预存款", "使用商城预存款结算更方便");
        loadPdcash();
        return layout;
    }

    /**
     * 读取充值明细
     */
    private void loadPdcash() {
        String url = Constants.URL_MEMBER_FUND_PDCASHLIST + "&curpage=" + currentPage + "&page=" + Constants.PAGESIZE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    isHasMore = data.isHasMore();

                    if (currentPage == 1) {
                        pdcashInfoArrayList.clear();
                        myListEmpty.setVisibility(View.GONE);
                    }

                    try {
                        JSONObject obj = new JSONObject(json);
                        String pdcashJson = obj.getString("list");
                        ArrayList<PdcashInfo> list = PdcashInfo.newInstanceList(pdcashJson);
                        if (list.size() > 0) {
                            pdcashInfoArrayList.addAll(list);
                            pdcashListViewAdapter.setList(pdcashInfoArrayList);
                            pdcashListViewAdapter.notifyDataSetChanged();
                        } else {
                            myListEmpty.setVisibility(View.VISIBLE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ShopHelper.showApiError(getActivity(), json);
                }
            }
        });
    }


}
