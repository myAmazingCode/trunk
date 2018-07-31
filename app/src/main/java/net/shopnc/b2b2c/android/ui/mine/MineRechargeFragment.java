package net.shopnc.b2b2c.android.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.DepositChargeAdapter;
import net.shopnc.b2b2c.android.bean.DepositRecharge;
import net.shopnc.b2b2c.android.bean.PageEntity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * 充值明细
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.ui.mine.MineRechargeFragment.java
 * @author: Jie
 * @date: 2016-05-27 17:32
 */
public class MineRechargeFragment extends MineBaseFragment {

    private static final String TAG = "MineRechargeFragment";

    @Bind(R.id.mine_pre_recycler)
    XRecyclerView mine_pre_recycler;

    @Bind(R.id.layoutEmpty)
    RelativeLayout layoutEmpty;
    @Bind(R.id.imgEmptyLogo)
    ImageView imgEmptyLogo;
    @Bind(R.id.tvEmptyTitle)
    TextView tvEmptyTitle;
    @Bind(R.id.tvEmptyBody)
    TextView tvEmptyBody;

    /**
     * 充值明细list
     */
    private List<DepositRecharge> depositList = new ArrayList<>();

    /**
     * adapter
     */
    private DepositChargeAdapter depositAdapter;

    public static MineRechargeFragment newInstance() {
        return new MineRechargeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("cur_fragment", 2);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        loadCharge();
        initRecyclerView(mine_pre_recycler, listener);
        depositAdapter = new DepositChargeAdapter(getActivity());
        mine_pre_recycler.setAdapter(depositAdapter);
        setLayoutEmpty(R.drawable.no_data_a, "亲，您尚未有预存款信息哦~", "");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_deposit3;
    }

    /**
     * 实现上下拉刷新方法接口
     */
    private XRecyclerView.LoadingListener listener = new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
            curPage = 1;
            loadCharge();
        }

        @Override
        public void onLoadMore() {
            ++curPage;
            loadCharge();
        }
    };

    /**
     * 读取充值明细信息，已含有加载更多设定
     */
    private void loadCharge() {

        Map<String, String> param = new HashMap<>();
        param.put("token", myApplication.getToken());
        param.put("page", curPage + "");

        OkHttpUtil.postAsyn(getActivity(),ConstantUrl.URL_RECHARGE_GET, new BeanCallback<String>() {

            public void response(String data) {
                ArrayList<DepositRecharge> list = JsonUtil.toBean(data, "rechargeList", new TypeToken<ArrayList<DepositRecharge>>() {
                }.getType());
                PageEntity pageEntity = JsonUtil.toBean(data, "pageEntity", new TypeToken<PageEntity>() {
                }.getType());

                if (list == null || pageEntity == null || layoutEmpty == null) {
                    return;
                }

                //开启或关闭加载更多
                if (pageEntity.isHasMore()) {
                    mine_pre_recycler.setLoadingMoreEnabled(true);
                } else {
                    mine_pre_recycler.setLoadingMoreEnabled(false);
                }

                if (curPage == 1) {
                    depositList.clear();
                }

                //隐藏加载动画
                mine_pre_recycler.refreshComplete();
                mine_pre_recycler.loadMoreComplete();

                if (list.isEmpty() && curPage == 1) {
                    layoutEmpty.setVisibility(View.VISIBLE);
                    imgEmptyLogo.setImageResource(R.drawable.no_data_a);
                    tvEmptyTitle.setText("亲，您尚未有预存款信息哦~");
                    tvEmptyBody.setText("");
                } else {
                    layoutEmpty.setVisibility(View.GONE);
                    if (curPage <= pageEntity.getTotalPage()) {
                        depositList.addAll(list);
                        depositAdapter.setDatas(depositList);
                        depositAdapter.notifyDataSetChanged();
                    }
                }
            }
        }, param);
    }

}
