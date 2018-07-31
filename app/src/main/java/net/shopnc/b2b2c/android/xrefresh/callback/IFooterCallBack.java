package net.shopnc.b2b2c.android.xrefresh.callback;


import net.shopnc.b2b2c.android.xrefresh.XRefreshView;

public interface IFooterCallBack {
	/**
	 * 当不是到达底部自动加载更多的时候，需要自己写点击事件
	 * @param xRefreshViewListener
	 */
    void callWhenNotAutoLoadMore(XRefreshView.XRefreshViewListener xRefreshViewListener);
	/**
	 * 正常状态，例如需要点击footerview才能加载更多，主要是到达底部不自动加载更多时会被调用
	 */
    void onStateReady();
	/**
	 * 正在刷新
	 */
    void onStateRefreshing();
	/**
	 * 刷新结束
	 */
    void onStateFinish();
	/**
	 * 已无更多数据
	 */
    void onStateComplete();
	/**
	 * 隐藏footerview
	 */
    void hide();
	/**
	 * 显示footerview
	 */
    void show();
	/**
	 * 获得footerview的高度
	 * @return
	 */
    int getFooterHeight();
}
