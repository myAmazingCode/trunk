package net.shopnc.b2b2c.android.ui.im;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.IMListAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.LinkSellerBean;
import net.shopnc.b2b2c.android.common.adapter.OnActivityTouchListener;
import net.shopnc.b2b2c.android.common.adapter.RecyclerTouchListener;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.dialog.NCDialog;
import net.shopnc.b2b2c.android.custom.dialog.NCDialogConfirm;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;

public class IMListActivity extends BaseActivity implements RecyclerTouchListener.RecyclerTouchListenerHelper {

    @Bind(R.id.rvSellers)
    RecyclerView rvSellers;

    private RecyclerTouchListener onTouchListener;
    private OnActivityTouchListener touchListener;

    private List<LinkSellerBean> sellers;
    private IMListAdapter adapter;
    private NCDialog ncDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("消息列表");
        setLayoutEmpty(R.drawable.talk_w, "您还没有和任何人联系过", "对话后可在此找到最近联系的客服");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSellers.setLayoutManager(layoutManager);
        adapter = new IMListAdapter(context);
        rvSellers.setAdapter(adapter);
        initDeleteDialog();
        onTouchListener = new RecyclerTouchListener(this, rvSellers);
        onTouchListener
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        Intent intent = new Intent(context, IMDetailsActivity.class);
                        intent.putExtra("sid", sellers.get(position).getLink_man_store_id());
                        intent.putExtra("gid", sellers.get(position).getCommon_id());
                        intent.putExtra("flag", "imList");
                        context.startActivity(intent);
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {
                    }
                })
                .setLongClickable(true, new RecyclerTouchListener.OnRowLongClickListener() {
                    @Override
                    public void onRowLongClicked(int position) {
                        showDeleteSeller(sellers.get(position).getLink_id());
                    }
                })
                .setSwipeOptionViews(R.id.tvDelete)
                .setSwipeable(R.id.rlFG, R.id.rlBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                        showDeleteSeller(sellers.get(position).getLink_id());
                    }
                });
    }

    private void initDeleteDialog() {
        ncDialog = new NCDialog(context);
        ncDialog.setText1("确定删除此联系人吗？");
    }

    private void showDeleteSeller(final int link_id) {
        ncDialog.showPopupWindow();
        ncDialog.setOnDialogConfirm(new NCDialogConfirm() {
            @Override
            public void onDialogConfirm() {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("token", application.getToken());
                params.put("lid", link_id + "");

                OkHttpUtil.postAsyn(IMListActivity.this,ConstantUrl.URL_IM_SELLER_DELETE, new BeanCallback<String>() {
                    @Override
                    public void response(String data) {
                        requestSellersList();
                    }
                }, params);
            }
        });
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_imlist);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestSellersList();
        rvSellers.addOnItemTouchListener(onTouchListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        rvSellers.removeOnItemTouchListener(onTouchListener);
    }

    private void requestSellersList() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        OkHttpUtil.postAsyn(this,ConstantUrl.URL_IM_SELLER_LIST, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                sellers = JsonUtil.toBean(data, "list", new TypeToken<ArrayList<LinkSellerBean>>() {
                }.getType());
                if (sellers == null || sellers.isEmpty()) {
                    showLayoutEmpty();
                } else {
                    adapter.setDatas(sellers);
                    adapter.notifyDataSetChanged();
                }
            }
        }, params);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (touchListener != null) touchListener.getTouchCoordinates(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void setOnActivityTouchListener(OnActivityTouchListener listener) {
        this.touchListener = listener;
    }

}
