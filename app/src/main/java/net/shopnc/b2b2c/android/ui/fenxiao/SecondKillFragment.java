package net.shopnc.b2b2c.android.ui.fenxiao;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.j256.ormlite.stmt.query.In;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;

import net.shopnc.b2b2c.android.MainFragmentManager;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.KillAdapter;
import net.shopnc.b2b2c.android.bean.KillBuyItemBean;
import net.shopnc.b2b2c.android.bean.KillSecondListBean;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondKillFragment extends Fragment {

    @Bind(R.id.spike_recycler)
    XRecyclerView spikeRecycler;
    @Bind(R.id.ivListEmpty)
    ImageView ivListEmpty;
    @Bind(R.id.goShoppingID)
    Button goShoppingID;
    @Bind(R.id.llNoData)
    LinearLayout llNoData;

    private int curpage = 1;

    private KillAdapter adapter;
    private Map<String, List<KillBuyItemBean>> recom_goods;

    private List<KillSecondListBean.DatasBean.SpikeListBean> spike_list;

    public SecondKillFragment() {

        // Required empty public constructor

        recom_goods = new HashMap<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_kill, container, false);
        ButterKnife.bind(this, view);
        final String state = getArguments().getString("state");
        spike_list = new ArrayList<>();

        goShoppingID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainFragmentManager.class);
                getActivity().sendBroadcast(new Intent(Constants.SHOW_HOME_URL));
                startActivity(intent);
            }
        });

        adapter = new KillAdapter(getActivity(), spike_list, recom_goods);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        spikeRecycler.setLayoutManager(manager);
        spikeRecycler.setAdapter(adapter);

        getSpikeData(state, true);
        spikeRecycler.setLoadingMoreEnabled(true);
        spikeRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                curpage = 1;
                spikeRecycler.setLoadingMoreEnabled(true);
                getSpikeData(state, true);
            }

            @Override
            public void onLoadMore() {
                getSpikeData(state, false);

            }
        });
        return view;
    }

    //获取秒杀信息数据
    public void getSpikeData(String state, final boolean isRefresh) {
        RemoteDataHandler.asyncDataStringGet(Constants.URL_GOODS_SPIKE + "&state=" + state + "&curpage=" + curpage, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String res = data.getJson();
                if (data.isHasMore()) {
                    curpage++;
                } else {
                    spikeRecycler.setLoadingMoreEnabled(false);
                }
                Logger.json(res + "");


                try {
                    KillSecondListBean.DatasBean bean = new Gson().fromJson(res, KillSecondListBean.DatasBean.class);
                    if (bean.getSpike_list().size() == 0) {
                        llNoData.setVisibility(View.VISIBLE);
                    } else {
                        try {
                            JSONObject jsonObject = new JSONObject(res);
                            JSONObject recomObj = jsonObject.getJSONObject("recommend_goods");
                            if (isRefresh) {
                                recom_goods.clear();
                                spike_list.clear();
                            }

                            //遍历spike_list,这个是列表背景的信息bean，长度是列表的长度
                            for (int i = 0; i < bean.getSpike_list().size(); i++) {

                                //判断是否存在key值
                                if (recomObj.has(bean.getSpike_list().get(i).getSpike_id())) {

                                    JSONArray recomList = recomObj.getJSONArray(bean.getSpike_list().get(i).getSpike_id());

                                    List<KillBuyItemBean> itemList = new ArrayList<KillBuyItemBean>();

                                    //将recommend_goods转换为链表
                                    for (int j = 0; j < recomList.length(); j++) {
                                        try {
                                            itemList.add(new Gson().fromJson(recomList.getJSONObject(j).toString(), KillBuyItemBean.class));
                                        } catch (JsonSyntaxException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    recom_goods.put(bean.getSpike_list().get(i).getSpike_id(), itemList);
                                }

                            }
                            if (isRefresh) {
                                llNoData.setVisibility(View.GONE);
                                spikeRecycler.refreshComplete();
                            } else {
                                spikeRecycler.loadMoreComplete();

                            }
                            spike_list.addAll(bean.getSpike_list());
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                } catch (JsonSyntaxException | NullPointerException e) {
                    e.printStackTrace();
                    llNoData.setVisibility(View.VISIBLE);
                }


            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
