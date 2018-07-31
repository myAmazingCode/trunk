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
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.LoadImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;


/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.ui.mine.MinePointsActivity.java
 * @author: Jie
 * @date: 2016-05-31 12:03
 */
public class MinePointsActivity extends BaseActivity {

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

    private ArrayList<MemberPoints> points;

    private PointsAdapter adapter;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCommonHeader("积分明细");
        mine_point_title.setText("我的积分");
        LoadImage.loadImageRotated(context, ivPhoto, 30f, R.drawable.mcc_10_w);

        points = new ArrayList<>();
        adapter = new PointsAdapter(this);

        initRecyclerView(recyclerView, listener);
        recyclerView.setAdapter(adapter);

        getTotalPoints();
        getPointsList();

    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_point_log);
    }

    private void getTotalPoints() {
        Map<String, String> p = new HashMap<>();
        p.put("token", application.getToken());
        p.put("scope", "points");
        OkHttpUtil.postAsyn(this,ConstantUrl.URL_DEPOSIT_REMAIN, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                mine_point_num.setText(JsonUtil.toString(data, "points"));
            }
        }, p);
    }

    /**
     * 初始化recyclerView基本属性
     */
    private void initRecyclerView(XRecyclerView v, XRecyclerView.LoadingListener listener) {
        //设定为垂直布局
        LinearLayoutManager manager = new LinearLayoutManager(MinePointsActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        v.setLayoutManager(manager);
        //设置下拉刷新动画
        v.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //设置加载动画
        v.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
//        v.addItemDecoration(new ItemDecorationDivider(MinePointsActivity.this,));

        if (listener != null) {
            v.setLoadingListener(listener);
        } else {
            v.setLoadingListener(null);
        }

    }

    private void getPointsList() {
        Map<String, String> p = new HashMap<>();
        p.put("token", application.getToken());
        p.put("page", "" + page);

        OkHttpUtil.postAsyn(this,ConstantUrl.URL_MEMBER_POINTS, new BeanCallback<String>() {
            @Override
            public void response(String data) {

                ArrayList<MemberPoints> list = JsonUtil.toBean(data, "logList", new TypeToken<List<MemberPoints>>() {
                }.getType());
                PageEntity pageEntity = JsonUtil.toBean(data, "pageEntity", new TypeToken<PageEntity>() {
                }.getType());

                if (list == null || pageEntity == null || layoutEmpty == null) {
                    return;
                }

                //开启或关闭加载更多
                if (pageEntity.isHasMore()) {
                    recyclerView.setLoadingMoreEnabled(true);
                } else {
                    recyclerView.setLoadingMoreEnabled(false);
                }

                if (page == 1) {
                    points.clear();
                }

                //隐藏加载动画
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();

                if (list.isEmpty() && page == 1) {
                    layoutEmpty.setVisibility(View.VISIBLE);
                    imgEmptyLogo.setImageResource(R.drawable.no_data_a);
                    tvEmptyTitle.setText("亲，您当前没有任何积分哦~");
                    tvEmptyBody.setText("");
                } else {
                    layoutEmpty.setVisibility(View.GONE);
                    if (page <= pageEntity.getTotalPage()) {
                        points.addAll(list);
                        adapter.setDatas(points);
                        adapter.notifyDataSetChanged();
                    }
                }

            }
        }, p);
    }

    private XRecyclerView.LoadingListener listener = new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
            page = 1;
            getPointsList();
        }

        @Override
        public void onLoadMore() {
            ++page;
            getPointsList();
        }

    };

}
