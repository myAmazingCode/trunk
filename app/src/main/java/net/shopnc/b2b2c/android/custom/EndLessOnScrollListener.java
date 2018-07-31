package net.shopnc.b2b2c.android.custom;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/9/18 9:44.
 */
public abstract class EndLessOnScrollListener extends RecyclerView.OnScrollListener {

    //获取LayoutManager
    private LinearLayoutManager linearLayoutManager;

    //已经请求得到的所有item总数
    private int totalItemCount;

    //上一个totalItemCount
    private int previousTotal = 0;

    //屏幕上可见的item数量
    private int visibleItemCount;

    //在屏幕可见的第一个item的位置
    private int firstVisibleItem;

    //是否正在请求数据
//    private boolean loading = true;
    //当已经没有更多页的时候，需要将loading设为true  才可以在更改筛选方案之后自动加载正常
    private Loading loading = new Loading(true);

    public EndLessOnScrollListener() {
    }


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

        if (layoutManager instanceof LinearLayoutManager) {  //LinearLayoutManager  and  GridLayoutManager
            linearLayoutManager = (LinearLayoutManager) layoutManager;
        } else {
            return;
        }

        totalItemCount = linearLayoutManager.getItemCount();
        firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

        if (loading.isLoading()) {
            if (totalItemCount > previousTotal) {
                //说明加载已经结束
                loading.setLoading(false);
                previousTotal = totalItemCount;
            }
        }

//        滑动到最底部再进行加载
//        if (!loading && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
        //还剩下一屏的时候就开始加载
        if (!loading.isLoading() && firstVisibleItem + 2 * visibleItemCount >= totalItemCount) {
            onLoadMore(loading);
//            loading.setLoading(true);
        }

    }

    public abstract void onLoadMore(Loading loading);

    public class Loading {
        private boolean loading;

        public Loading(boolean loading) {
            this.loading = loading;
        }

        public boolean isLoading() {
            return loading;
        }

        public void setLoading(boolean loading) {
            this.loading = loading;
        }
    }
}
