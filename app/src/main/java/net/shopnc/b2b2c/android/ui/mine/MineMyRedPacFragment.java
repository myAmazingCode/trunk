package net.shopnc.b2b2c.android.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.RedPackageAdapter;
import net.shopnc.b2b2c.android.bean.PageEntity;
import net.shopnc.b2b2c.android.bean.RedPackageMember;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.custom.MyListView;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.xrefresh.XRefreshView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName:
 * @author: Jie
 * @date: 2016-05-31 09:23
 */
public class MineMyRedPacFragment extends MineBaseFragment {

    @Bind(R.id.red_pack_refresh)
    XRefreshView redPackageRefresh;
    @Bind(R.id.mine_pre_recycler)
    MyListView mine_pre_recycler;
    @Bind(R.id.layoutEmpty)
    RelativeLayout layoutEmpty;
    @Bind(R.id.imgEmptyLogo)
    ImageView imgEmptyLogo;
    @Bind(R.id.tvEmptyTitle)
    TextView tvEmptyTitle;
    @Bind(R.id.tvEmptyBody)
    TextView tvEmptyBody;

    private List<RedPackageMember> redPackageVos = new ArrayList<>();
    private PageEntity pageEntity;
    private RedPackageAdapter adapter;

    public static MineMyRedPacFragment newInstance() {
        return new MineMyRedPacFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("cur_fragment", 1);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        adapter = new RedPackageAdapter(getActivity());
        mine_pre_recycler.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_deposit2;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadRedPac(redPackageRefresh);
        initRecyclerView(redPackageRefresh);
    }

    private void initRecyclerView(final XRefreshView customView) {
        customView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                curPage = 1;
                loadRedPac(customView);
            }

            @Override
            public void onLoadMore(boolean isSlience) {
                if (curPage < pageEntity.getTotalPage()) {
                    curPage += 1;
                    loadRedPac(customView);
                }
            }
        });
    }

    /**
     * 红包列表
     */
    private void loadRedPac(final XRefreshView customView) {
        final Map<String, String> param = new HashMap<>();
        param.put("token", myApplication.getToken());
        param.put("page", curPage + "");

        OkHttpUtil.postAsyn(getActivity(),ConstantUrl.URL_REDPACKAGE_GETLIST, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                List<RedPackageMember> reds = JsonUtil.toBean(data, "redpackageList", new TypeToken<List<RedPackageMember>>() {
                }.getType());
                pageEntity = JsonUtil.toBean(data, "pageEntity", new TypeToken<PageEntity>() {
                }.getType());

                if (customView == null) {
                    return;
                }

                if (pageEntity != null && !pageEntity.isHasMore()) {
                    customView.setPullLoadEnable(false);
                } else {
                    customView.setPullLoadEnable(true);
                }

                if (curPage == 1) {
                    redPackageVos.clear();
                }

                customView.stopRefresh();
                customView.endLoadMore();

                if (reds != null && !reds.isEmpty()) {
                    redPackageVos.addAll(reds);
                    adapter.setmDatas(redPackageVos);
                    adapter.notifyDataSetChanged();
                } else {
                    mine_pre_recycler.setVisibility(View.GONE);
                    layoutEmpty.setVisibility(View.VISIBLE);
                    imgEmptyLogo.setImageResource(R.drawable.no_data_b);
                    tvEmptyTitle.setText("亲，您还没有相关的红包哦~");
                    tvEmptyBody.setText("");
                }
            }
        }, param);
    }
}
