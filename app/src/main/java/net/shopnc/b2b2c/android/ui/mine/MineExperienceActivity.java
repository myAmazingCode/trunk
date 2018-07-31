package net.shopnc.b2b2c.android.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.PointsAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.MemberPoints;
import net.shopnc.b2b2c.android.bean.PageEntity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.LoadImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

/**
 * 经验值明细
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.ui.mine.MineExperienceActivity.java
 * @author: Jie
 * @date: 2016-05-31 16:02
 */
public class MineExperienceActivity extends BaseActivity {

    @Bind(R.id.lvPointLog)
    XRecyclerView recyclerView;
    @Bind(R.id.mine_point_num)
    TextView mine_point_num;
    @Bind(R.id.mine_point_title)
    TextView mine_point_title;

    @Bind(R.id.layoutEmpty)
    RelativeLayout layoutEmpty;
    @Bind(R.id.imgEmptyLogo)
    ImageView imgEmptyLogo;
    @Bind(R.id.tvEmptyTitle)
    TextView tvEmptyTitle;
    @Bind(R.id.tvEmptyBody)
    TextView tvEmptyBody;
    @Bind(R.id.ivPhoto)
    ImageView ivPhoto;


    private ArrayList<MemberPoints> pointses;

    private PointsAdapter adapter;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("经验值明细");
        mine_point_title.setText("我的经验");
        LoadImage.loadImageRotated(context, ivPhoto, 30f, R.drawable.mcc_10_w);

        pointses = new ArrayList<>();
        adapter = new PointsAdapter(this);

        initRecyclerView(recyclerView, listener);
        recyclerView.setAdapter(adapter);

        loadTotalPoints();
        loadPoints();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_point_log);
    }

    /**
     * 初始化recyclerView基本属性
     */
    private void initRecyclerView(XRecyclerView v, XRecyclerView.LoadingListener listener) {
        //设定为垂直布局
        LinearLayoutManager manager = new LinearLayoutManager(MineExperienceActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        v.setLayoutManager(manager);
        //设置下拉刷新动画
        v.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //设置加载动画
        v.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

        if (listener != null) {
            v.setLoadingListener(listener);
        } else {
            v.setLoadingListener(null);
        }

    }

    private void loadTotalPoints() {
        Map<String, String> p = new HashMap<>();
        p.put("token", application.getToken());
        p.put("scope", "expPoints");
        OkHttpUtil.postAsyn(this,ConstantUrl.URL_DEPOSIT_REMAIN, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                mine_point_num.setText(JsonUtil.toString(data, "expPoints"));
            }
        }, p);
    }

    private void loadPoints() {
        Map<String, String> p = new HashMap<>();
        p.put("token", application.getToken());
        p.put("page", "" + page);

        OkHttpUtil.postAsyn(this,ConstantUrl.URL_MEMBER_EXPERINCE, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                LogHelper.e("经验", data);
                ArrayList<MemberPoints> list = JsonUtil.toBean(data, "logList", new TypeToken<ArrayList<MemberPoints>>() {
                }.getType());
                PageEntity p = JsonUtil.toBean(data, "pageEntity", new TypeToken<PageEntity>() {
                }.getType());

                if (list == null || p == null || layoutEmpty == null) {
                    return;
                }

                //开启或关闭加载更多
                if (p.isHasMore()) {
                    recyclerView.setLoadingMoreEnabled(true);
                } else {
                    recyclerView.setLoadingMoreEnabled(false);
                }

                if (page == 1) {
                    pointses.clear();
                }

                //隐藏加载动画
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();

                if (list != null && p != null) {
                    if (list.isEmpty() && page == 1) {
                        layoutEmpty.setVisibility(View.VISIBLE);
                        imgEmptyLogo.setImageResource(R.drawable.no_data_a);
                        tvEmptyTitle.setText("亲，您当前没有任何经验哦~");
                        tvEmptyBody.setText("");
                    } else {
                        layoutEmpty.setVisibility(View.GONE);
                        if (page <= p.getTotalPage()) {
                            pointses.addAll(list);
                            adapter.setDatas(pointses);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
                ++page;
            }
        }, p);
    }

    private XRecyclerView.LoadingListener listener = new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
            page = 1;
            loadPoints();
        }

        @Override
        public void onLoadMore() {
            loadPoints();
        }

    };


}
