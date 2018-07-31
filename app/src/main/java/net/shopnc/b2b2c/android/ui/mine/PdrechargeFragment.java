package net.shopnc.b2b2c.android.ui.mine;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.PdrechargeListViewAdapter;
import net.shopnc.b2b2c.android.bean.PdrechargeInfo;
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
 * 充值明细列表Fragment
 *
 * @author dqw
 * @Time 2015-8-25
 */
public class PdrechargeFragment extends Fragment {
    MyShopApplication myApplication;
    ListView lvPrecharge;
    ArrayList<PdrechargeInfo> pdrechargeInfoArrayList;
    PdrechargeListViewAdapter pdrechargeListViewAdapter;
    MyListEmpty myListEmpty;

    int currentPage = 1;
    boolean isHasMore = true;
    boolean isLastRow = false;

    public static PdrechargeFragment newInstance() {
        PdrechargeFragment fragment = new PdrechargeFragment();
        return fragment;
    }

    public PdrechargeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApplication = (MyShopApplication) getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_pdrecharge, container, false);
        MyExceptionHandler.getInstance().setContext(getActivity());
        lvPrecharge = layout.findViewById(R.id.lvPdrecharge);
        lvPrecharge.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (isHasMore && isLastRow && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    isLastRow = false;
                    currentPage += 1;
                    loadPdrecharge();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
                    isLastRow = true;
                }
            }
        });
        pdrechargeInfoArrayList = new ArrayList<PdrechargeInfo>();
        pdrechargeListViewAdapter = new PdrechargeListViewAdapter(getActivity());
        lvPrecharge.setAdapter(pdrechargeListViewAdapter);
        myListEmpty = layout.findViewById(R.id.myListEmpty);
        myListEmpty.setListEmpty(R.drawable.nc_icon_predeposit_white, "您尚未充值过预存款", "使用商城预存款结算更方便");
        loadPdrecharge();
        return layout;
    }

    /**
     * 读取充值明细
     */
    private void loadPdrecharge() {
        String url = Constants.URL_MEMBER_FUND_PDRECHARGELIST + "&curpage=" + currentPage + "&page=" + Constants.PAGESIZE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    isHasMore = data.isHasMore();

                    if (currentPage == 1) {
                        pdrechargeInfoArrayList.clear();
                        myListEmpty.setVisibility(View.GONE);
                    }

                    try {
                        JSONObject obj = new JSONObject(json);
                        String pdrechargeJson = obj.getString("list");
                        ArrayList<PdrechargeInfo> list = PdrechargeInfo.newInstanceList(pdrechargeJson);
                        if (list.size() > 0) {
                            pdrechargeInfoArrayList.addAll(list);
                            pdrechargeListViewAdapter.setList(pdrechargeInfoArrayList);
                            pdrechargeListViewAdapter.notifyDataSetChanged();
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
