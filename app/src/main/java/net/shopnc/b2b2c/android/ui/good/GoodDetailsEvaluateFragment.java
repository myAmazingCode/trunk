package net.shopnc.b2b2c.android.ui.good;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.EvaluateAdapter;
import net.shopnc.b2b2c.android.base.BaseFragment;
import net.shopnc.b2b2c.android.bean.GoodsEvaluate;
import net.shopnc.b2b2c.android.bean.PageEntity;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodDetailsEvaluateFragment extends BaseFragment {


    @Bind(R.id.rbGoodAllEva)
    RadioButton rbGoodAllEva;
    @Bind(R.id.rbGoodBetterEva)
    RadioButton rbGoodBetterEva;
    @Bind(R.id.rbGoodNormalEva)
    RadioButton rbGoodNormalEva;
    @Bind(R.id.rbGoodBedEva)
    RadioButton rbGoodBedEva;
    @Bind(R.id.rbGoodImgEva)
    RadioButton rbGoodImgEva;


    @Bind(R.id.imgEmptyLogo)
    ImageView imgEmptyLogo;
    @Bind(R.id.tvEmptyTitle)
    TextView tvEmptyTitle;
    @Bind(R.id.tvEmptyBody)
    TextView tvEmptyBody;
    @Bind(R.id.btnChoose)
    Button btnChoose;
    @Bind(R.id.layoutEmpty)
    RelativeLayout layoutEmpty;

    @Bind(R.id.rvEvaluate)
    XRecyclerView rvEvaluate;


    private int commonId;
    private List<GoodsEvaluate> evaluates;
    private PageEntity pageEntity;
    private EvaluateAdapter adapter;
    private int page = 1;
    private String evaluateState;

    public static GoodDetailsEvaluateFragment newInstance(int commonId) {
        GoodDetailsEvaluateFragment fragment = new GoodDetailsEvaluateFragment();
        Bundle b = new Bundle();
        b.putInt("commonId", commonId);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gooddetails_evaluate, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        rbGoodAllEva.setChecked(true);
        evaluateState = "all";

        commonId = getArguments().getInt("commonId", 7);

        initRecyclerView();
        evaluates = new ArrayList<>();

        getEvaluateList();
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("good_current", 3);
    }

    //好中差等评价的数量
    public void onEventMainThread(GoodBusBean goodBusBean) {
        if (goodBusBean.getFlag().equals(GoodBusBean.EVALUATES_COUNT)) {
            String[] obj = (String[]) goodBusBean.getObj();

            for (int i = 0; i < obj.length; i++) {
                String s = obj[i];
                switch (i) {
                    case 0:
                        rbGoodAllEva.setText(getResources().getString(R.string.all_goods) + "\n" + s);
                        break;
                    case 1:
                        rbGoodBetterEva.setText(getResources().getString(R.string.better_goods) + "\n" + s);
                        break;
                    case 2:
                        rbGoodNormalEva.setText(getResources().getString(R.string.normal_goods) + "\n" + s);
                        break;
                    case 3:
                        rbGoodBedEva.setText(getResources().getString(R.string.bad_goods) + "\n" + s);
                        break;
                    case 4:
                        rbGoodImgEva.setText(getResources().getString(R.string.image_goods) + "\n" + s);
                        break;

                }
            }
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvEvaluate.setLayoutManager(layoutManager);

        adapter = new EvaluateAdapter(context);
        rvEvaluate.setAdapter(adapter);

        rvEvaluate.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvEvaluate.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

        rvEvaluate.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getEvaluateList();
            }

            @Override
            public void onLoadMore() {
                page++;
                getEvaluateList();
            }
        });
    }

    private void showEmpty() {
        rvEvaluate.setVisibility(View.GONE);
        layoutEmpty.setVisibility(View.VISIBLE);
        imgEmptyLogo.setImageResource(R.drawable.nc_icon_mine_order_4);
        tvEmptyTitle.setText("该商品未收到任何评价");
        tvEmptyBody.setText("期待您的购买与评论！");
        btnChoose.setVisibility(View.GONE);
    }

    private void getEvaluateList() {

        String url = ConstantUrl.URL_EVALUATE + "?commonId=" + commonId + "&evalLv=" + evaluateState + "&page=" + page;

        OkHttpUtil.getAsyn(getActivity(),url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                List<GoodsEvaluate> goodsEvaluateList = JsonUtil.toBean(data, "evaluateGoodsVoList", new TypeToken<List<GoodsEvaluate>>() {
                }.getType());
                pageEntity = JsonUtil.toBean(data, "pageEntity", new TypeToken<PageEntity>() {
                }.getType());

                if (goodsEvaluateList == null || pageEntity == null || layoutEmpty == null) {
                    return;
                }

                if (pageEntity.isHasMore()) {
                    rvEvaluate.setLoadingMoreEnabled(true);
                } else {
                    rvEvaluate.setLoadingMoreEnabled(false);
                }

                if (page == 1) {
                    evaluates.clear();
                }

                //隐藏加载动画
                rvEvaluate.refreshComplete();
                rvEvaluate.loadMoreComplete();

                if (goodsEvaluateList.isEmpty() && page == 1) {
                    showEmpty();
                } else {
                    rvEvaluate.setVisibility(View.VISIBLE);
                    layoutEmpty.setVisibility(View.GONE);
                    evaluates.addAll(goodsEvaluateList);
                    adapter.setDatas(evaluates);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.rbGoodAllEva, R.id.rbGoodBetterEva, R.id.rbGoodNormalEva, R.id.rbGoodBedEva, R.id.rbGoodImgEva})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rbGoodAllEva:
                evaluateState = "all";
                rbGoodAllEva.setChecked(true);
                break;
            case R.id.rbGoodBetterEva:
                evaluateState = "1";
                rbGoodBetterEva.setChecked(true);
                break;
            case R.id.rbGoodNormalEva:
                evaluateState = "2";
                rbGoodNormalEva.setChecked(true);
                break;
            case R.id.rbGoodBedEva:
                evaluateState = "3";
                rbGoodBedEva.setChecked(true);
                break;
            case R.id.rbGoodImgEva:
                evaluateState = "4";
                rbGoodImgEva.setChecked(true);
                break;
        }
        getEvaluateList();
    }


}
