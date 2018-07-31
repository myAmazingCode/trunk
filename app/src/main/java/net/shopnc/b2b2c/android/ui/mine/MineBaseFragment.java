package net.shopnc.b2b2c.android.ui.mine;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.common.MyShopApplication;

import butterknife.ButterKnife;

/**
 * 此类事我的商城界面BaseFragment，区别于原来的BaseFragment
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.ui.mine.MineBaseFragment.java
 * @author: Jie
 * @date: 2016-05-27 14:19
 */
public abstract class MineBaseFragment extends Fragment {

    private static final String TAG = "MineBaseFragment";

    protected Activity mActivity;
    protected MyShopApplication myApplication;
    protected int curPage = 1;

    /**
     * 初始化View
     */
    protected abstract void initView(View view, Bundle savedInstanceState);

    /**
     * 获取布局ID
     */
    protected abstract int getLayoutId();

    /**
     * 获取宿主Activity
     */
    protected Activity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApplication = (MyShopApplication) getActivity().getApplicationContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        initView(view, savedInstanceState);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //apk后台运行时，若activity被销毁，则会报空指针，下面是解决方案，设定activity来代替getActivity()
        this.mActivity = (Activity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ButterKnife.unbind(this);
    }

    /**
     * 初始化recyclerView基本属性
     */
    public void initRecyclerView(XRecyclerView v, XRecyclerView.LoadingListener listener) {
        //设定为垂直布局
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        v.setLayoutManager(manager);
        //设置下拉刷新动画
        v.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //设置加载动画
        v.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

        v.setLoadingListener(listener);
    }

    /**
     * 设置空标记的显示
     */
    protected RelativeLayout layoutEmpty;

    protected void setLayoutEmpty(int resId, String emptyTitle, String emptyBody) {
        layoutEmpty = getActivity().findViewById(R.id.layoutEmpty);
        ImageView imgEmptyLogo = getActivity().findViewById(R.id.imgEmptyLogo);
        TextView tvEmptyTitle = getActivity().findViewById(R.id.tvEmptyTitle);
        TextView tvEmptyBody = getActivity().findViewById(R.id.tvEmptyBody);
        imgEmptyLogo.setImageResource(resId);
        tvEmptyTitle.setText(emptyTitle);
        tvEmptyBody.setText(emptyBody);
    }

    /**
     * 显示空标志
     */
    protected void showLayoutEmpty() {
        layoutEmpty.setVisibility(View.VISIBLE);
    }

    protected void hideLayoutEmpty() {
        layoutEmpty.setVisibility(View.GONE);
    }

}
