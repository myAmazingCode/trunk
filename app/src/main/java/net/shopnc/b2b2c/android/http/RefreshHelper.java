package net.shopnc.b2b2c.android.http;

import android.os.Handler;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.BaseFragmentActivity;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.xrefresh.XRefreshView;

import java.util.Map;

/**
 * 刷新的工具类
 *
 * @author huting
 * @date 2016/3/2
 * @email 495773046@qq.com
 */
public class RefreshHelper {
    public static int pageno = 1;
    public static int i = 1;
    public static int j = 1;

    public RefreshHelper() {}

    /**
     * 进行刷新条的初始化
     *
     * @param xRefreshView
     */
    private static void initRefresh(XRefreshView xRefreshView) {
        //xRefreshView.setPullLoadEnable(true);
        //xRefreshView.setAutoLoadMore(true);//自动加载
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        //adapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
    }

    /**
     * 执行刷新(适用于activity)
     * @param activity
     * @param url
     * @param params
     * @param xRefreshView
     */
    public static void doRefresh(final BaseActivity activity,final String url,final Map<String,String> params,
                                 final XRefreshView xRefreshView){
        initRefresh(xRefreshView);

        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //T.showShort(activity, "第" + i + "次刷新");
                        pageno = 1;
                        xRefreshView.setPullLoadEnable(true);

                        RemoteHelper.okHttpPostString(activity, url + "&curpage=" + pageno, params, xRefreshView);

                        xRefreshView.stopRefresh();

                       // i++;
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(boolean isSlience) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //T.showShort(activity,"第" + j + "次加载");

                        pageno = pageno + 1;
                        RemoteHelper.okHttpPostString(activity, url + "&curpage=" + pageno, params, xRefreshView);

                        // xRefreshView.setLoadComplete(true);
                        xRefreshView.stopLoadMore();

                        //j++;
                    }
                }, 1000);
            }
        });
    }


    /**
     * 执行刷新(适用于FragmentActivity)
     * @param fragmentActivity
     * @param url
     * @param params
     * @param xRefreshView
     */
    public static void doRefresh(final BaseFragmentActivity fragmentActivity,final String url,final Map<String,String> params,
                                 final XRefreshView xRefreshView){
        initRefresh(xRefreshView);

        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //T.showShort(fragmentActivity,"第" + i + "次刷新");
                        pageno = 1;
                        xRefreshView.setPullLoadEnable(true);

                        RemoteHelper.okHttpPostString(fragmentActivity, url + "&curpage=" + pageno, params, xRefreshView);
                        xRefreshView.stopRefresh();

                        //i++;
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(boolean isSlience) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                       // T.showShort(fragmentActivity,"第" + j + "次加载");

                        pageno = pageno + 1;
                        RemoteHelper.okHttpPostString(fragmentActivity, url + "&curpage=" + pageno, params, xRefreshView);

                        //j++;
                    }
                }, 1000);
            }
        });
    }
}
