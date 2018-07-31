package net.shopnc.b2b2c.android.ui.promotion;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.MyRepoListAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.DistributionInfo;
import net.shopnc.b2b2c.android.bean.MyRepoList;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MyRepoActivity extends BaseActivity {

    public static final String TAG = "MyRepoActivity";
    @Bind(R.id.ivAdd)
    ImageView mIvAdd;
    @Bind({R.id.tvAdd})
    TextView tvAdd;
    @Bind(R.id.rv)
    RecyclerView mRv;
    @Bind(R.id.tvGoodsType)
    TextView mTvGoodsType;
    @Bind(R.id.tvOrderNum)
    TextView mTvOrderNum;
    @Bind(R.id.tvBalance)
    TextView mTvBalance;
    private MyRepoListAdapter mMyRepoListAdapter;
    private List<MyRepoList.DatasBean.FavListBean> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("推广分佣");

        initRV();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDatas.size() != 0) {
            mDatas.clear();
            mMyRepoListAdapter.notifyDataSetChanged();
        }

        initList();
        initInfo();
    }

    //获取推广信息
    private void initInfo() {
        String url = ConstantUrl.URL_API + "/member/distributor/info";

        OkHttpUtil.postAsyn(this,url, new BeanCallback<String>() {

            @Override
            public void response(String data) {

            }

            @Override
            public void onResponse(String response,int i) {
                Log.d(TAG, "onResponse: response = " + response);

                if (200 == JsonUtil.toInteger(response, "code")) {
                    DistributionInfo info = new Gson().fromJson(response, DistributionInfo.class);
                    DistributionInfo.DatasBean datas = info.getDatas();
                    mTvOrderNum.setText(datas.getDistributorGoodsCount() + "笔");
                    mTvGoodsType.setText(datas.getDistributorOrdersCount() + "种");
                    double commissionAvailable = datas.getDistributor().getCommissionAvailable();
                    mTvBalance.setText(String.format("￥%.2f", commissionAvailable));

                    mMyRepoListAdapter.setInfos(new String[]{datas.getDistributorGoodsCount() + "种",
                            datas.getDistributorOrdersCount() + "笔", String.format("￥%.2f", commissionAvailable)});
                    mMyRepoListAdapter.notifyDataSetChanged();

                    tvAdd.setVisibility(View.VISIBLE);
                } else {
                    String error = JsonUtil.toString(response, "datas", "error");
                    Log.d(TAG, "onResponse: error = "+error);
//                    TToast.showShort(application, error);
                    tvAdd.setVisibility(View.GONE);
                    PromotionDialog dialog = new PromotionDialog(context);
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            MyRepoActivity.this.finish();
                        }
                    });
                    dialog.setOnCancelListener(new PromotionDialog.OnDialogCancelListener() {
                        @Override
                        public void onCancel() {
                            MyRepoActivity.this.finish();
                        }
                    });
                    dialog.show();
                }
            }
        }, new OkHttpUtil.Param("token", application.getToken()));
    }


    @OnClick({R.id.tvAdd,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvAdd:
                startActivity(new Intent(this, NewRepoActivity.class));
                break;
        }
    }

    private void initRV() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(layoutManager);

        mMyRepoListAdapter = new MyRepoListAdapter(this);
        mMyRepoListAdapter.setDatas(mDatas);
        mRv.setAdapter(mMyRepoListAdapter);
    }

    private void initList() {
        String url = ConstantUrl.URL_API + "/member/distributor/favorites/list";

        OkHttpUtil.postAsyn(this,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {

            }


            @Override
            public void onResponse(String response,int i) {
                Log.d(TAG, "onResponse: response = " + response);

                if (200 == JsonUtil.toInteger(response, "code")) {
                    MyRepoList myRepoList = new Gson().fromJson(response, MyRepoList.class);
                    List<MyRepoList.DatasBean.FavListBean> favList = myRepoList.getDatas().getFavList();
                    //列表数据
                    mDatas.addAll(favList);
                    int itemCount = mMyRepoListAdapter.getItemCount();
                    Log.d(TAG, "onResponse: itemCount = "+itemCount);
                    mMyRepoListAdapter.notifyDataSetChanged();
                } else {
//                    TToast.showShort(application, JsonUtil.toString(response, "datas", "error"));
                }
            }
        }, new OkHttpUtil.Param("token", application.getToken()));
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_my_repo);
    }
}
