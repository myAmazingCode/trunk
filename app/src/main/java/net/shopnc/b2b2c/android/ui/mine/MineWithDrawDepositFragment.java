package net.shopnc.b2b2c.android.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.DepositWithDrawAdapter;
import net.shopnc.b2b2c.android.bean.MemberDetail;
import net.shopnc.b2b2c.android.bean.PageEntity;
import net.shopnc.b2b2c.android.bean.WithDrawDeposit;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.event.MineWithDrawDepositEvent;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.Global;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.xrefresh.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提现模块
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.ui.mine.MineWithDrawDepositFragment.java
 * @author: Jie
 * @date: 2016-05-29 09:11
 */
public class MineWithDrawDepositFragment extends MineBaseFragment {

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
    @Bind(R.id.btnApplyDeposit)
    Button btnApplyDeposit;

    private int mMobileIsBind;
    private String mMobile;
    private String mMobileEncrypt;

    /**
     * 提现list
     */
    private List<WithDrawDeposit> depositList = new ArrayList<>();

    /**
     *
     */
    private DepositWithDrawAdapter depositAdapter;

    public static MineWithDrawDepositFragment newInstance() {
        return new MineWithDrawDepositFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("cur_fragment", 3);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initData();
        loadWithDraw();
        initRecyclerView(mine_pre_recycler, listener);
        depositAdapter = new DepositWithDrawAdapter(getActivity());
        mine_pre_recycler.setAdapter(depositAdapter);
        btnApplyDeposit.setVisibility(View.VISIBLE);
//        setLayoutEmpty(R.drawable.nc_icon_mine_order_1, "您无提现记录", "快去充值吧，更快，更省，更便捷");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_deposit_withdraw;
    }

    /**
     * 实现上下拉刷新方法接口
     */
    private XRecyclerView.LoadingListener listener = new XRecyclerView.LoadingListener() {

        @Override
        public void onRefresh() {
            curPage = 1;
            loadWithDraw();
        }

        @Override
        public void onLoadMore() {
            ++curPage;
            loadWithDraw();
        }
    };

    /**
     * 拉取提现信息
     */
    private void loadWithDraw() {
        Map<String, String> param = new HashMap<>();
        param.put("token", myApplication.getToken());
        param.put("page", curPage + "");

        OkHttpUtil.postAsyn(getActivity(),ConstantUrl.URL_WITHDRAW_DEPOSIT, new BeanCallback<String>() {

            @Override
            public void response(String data) {
                ArrayList<WithDrawDeposit> list = JsonUtil.toBean(data, "cashList", new TypeToken<ArrayList<WithDrawDeposit>>() {
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
//                    btnApplyDeposit.setVisibility(View.VISIBLE);
                    imgEmptyLogo.setImageResource(R.drawable.no_data_a);
                    tvEmptyTitle.setText("亲，您尚未提现过预存款哦~");
                    tvEmptyBody.setText("");
                } else {
                    layoutEmpty.setVisibility(View.GONE);
//                    btnApplyDeposit.setVisibility(View.GONE);
                    if (curPage <= pageEntity.getTotalPage()) {
                        depositList.addAll(list);
                        depositAdapter.setDatas(depositList);
                        depositAdapter.notifyDataSetChanged();
                    }
                }
            }
        }, param);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btnApplyDeposit)
    public void onViewClicked() {
        // TODO: 2017/5/23 提现
//        Intent intent = new Intent(getActivity(), MineWithDrawDepositActivity.class);
        Intent intent = new Intent(getActivity(), MobileBindActivity.class);
        intent.putExtra("mMobileIsBind", 1);
        intent.putExtra("mMobileIsBind", mMobileIsBind);
        intent.putExtra("mMobileEncrypt", mMobileEncrypt);
        intent.putExtra("mMobile", mMobile);
        intent.putExtra("operationFlag", Global.OPERATIONFLAG_WITHDRAWDEPOSIT);
        startActivity(intent);

    }

    /**
     * 获取会员详情
     */
    private void initData() {
        String url = ConstantUrl.URL_API + "/member/detail";

        OkHttpUtil.postAsyn(getActivity(),url, new BeanCallback<String>() {

            @Override
            public void onResponse(String response,int i) {
                LogUtils.e("onResponse: response = " + response);
                if (JsonUtil.toInteger(response, "code") == 200) {
                    MemberDetail memberDetail = new Gson().fromJson(response, MemberDetail.class);
                    MemberDetail.DatasBean.MemberInfoBean memberInfo = memberDetail.getDatas().getMemberInfo();
                    mMobileIsBind = memberInfo.getMobileIsBind();
                    mMobileEncrypt = memberInfo.getMobileEncrypt();
                    mMobile = memberInfo.getMobile();
                } else {
                    TToast.showShort(myApplication, JsonUtil.toString(response, "datas", "error"));
                }
            }

            @Override
            public void response(String data) {

            }
        }, new OkHttpUtil.Param("token", myApplication.getToken()));
    }

    public void onEventMainThread(MineWithDrawDepositEvent event) {
        curPage = 1;
        loadWithDraw();
    }

}
