package net.shopnc.b2b2c.android.ui.fenxiao;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.volokh.danylo.visibility_utils.calculator.DefaultSingleItemCalculatorCallback;
import com.volokh.danylo.visibility_utils.calculator.ListItemsVisibilityCalculator;
import com.volokh.danylo.visibility_utils.calculator.SingleListViewItemActiveCalculator;
import com.volokh.danylo.visibility_utils.scroll_utils.ItemsPositionGetter;
import com.volokh.danylo.visibility_utils.scroll_utils.ListViewItemPositionGetter;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.SimpleCardAdapter;
import net.shopnc.b2b2c.android.base.BaseFragment;
import net.shopnc.b2b2c.android.bean.FocusListBean;
import net.shopnc.b2b2c.android.bean.NewsListBean;
import net.shopnc.b2b2c.android.bean.item.VisibilityItem;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.JsonUtil;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.SwpipeListViewOnScrollListener;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.event.SimpleCardEvent;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.xrefresh.utils.LogUtils;

import org.apache.http.HttpStatus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;


@SuppressLint("ValidFragment")
public class SimpleCardFragment extends BaseFragment {

    private String cate_id;
    private ArrayList<NewsListBean> newsList = new ArrayList<NewsListBean>();
    private RefreshLayout swipe_container;
    private SimpleCardAdapter adapter;
    private SwipeRefreshLayout.OnRefreshListener listener;
    private ListView myListView;
    public int lastVisibleItem = 0;

    private int page = 1;

    private final ArrayList<VisibilityItem> mList = new ArrayList<>();

    private final ListItemsVisibilityCalculator mListItemVisibilityCalculator =
            new SingleListViewItemActiveCalculator(new DefaultSingleItemCalculatorCallback(), mList);

    private ItemsPositionGetter mItemsPositionGetter;

    private int mScrollState = AbsListView.OnScrollListener.SCROLL_STATE_IDLE;


    public static SimpleCardFragment getInstance(String cate_id) {
        SimpleCardFragment sf = new SimpleCardFragment();
        sf.cate_id = cate_id;
        return sf;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_fenxiao_listview, null);
        swipe_container = v.findViewById(R.id.swipe_container);

        myListView = v.findViewById(R.id.mylistview);

//        myListView.setXListViewListener(this);

        mItemsPositionGetter = new ListViewItemPositionGetter(myListView);

        swipe_container.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);

        adapter = new SimpleCardAdapter(getActivity());

        listener = new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                page = 1;
                LoadDate();
            }
        };
        swipe_container.setOnRefreshListener(listener);


        swipe_container.post(new Runnable() {
            @Override
            public void run() {
                swipe_container.setRefreshing(true);
            }
        });


        adapter.setShoucangLintener(new SimpleCardAdapter.setShoucangLintener() {
            @Override
            public void setShoucang(String store_id, String isShou) {
                if ("1".equals(isShou)) {
                    ShouCang(store_id);
                } else {
                    UnShouCang(store_id);
                }
            }
        });
        swipe_container.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                //onLoadMore();
            }
        });

        return v;
    }


    private void LoadDate() {
        mList.clear();
        newsList.clear();
        adapter.clearDeviceList();
        LoadTabDate(cate_id);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    public void initCalculator(ArrayList<NewsListBean> cateList) {
        if (!cateList.isEmpty()) {
            myListView.post(new Runnable() {
                @Override
                public void run() {
                    mListItemVisibilityCalculator.onScrollStateIdle(
                            mItemsPositionGetter,
                            myListView.getFirstVisiblePosition(),
                            myListView.getLastVisiblePosition());
                }
            });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        listener.onRefresh();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    boolean isHasMore = false;

    private void LoadTabDate(final String cate_id) {
        String url = Constants.URL_VIDEO_LIST_INDEX + "&cate_id=" + cate_id + "&key=" + MyShopApplication.getInstance().getLoginKey() + "&page=" + Constants.PAGESIZE + "&curpage=" + page;
        Logger.d(url);

        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == 200) {
                    isHasMore = data.isHasMore();
                    try {
                        String focus_list = JsonUtil.getString(data.getJson(), "focus_list");
                        String lists = JsonUtil.getString(data.getJson(), "lists");
                        final ArrayList<FocusListBean> ListBeen = JsonUtil.getBean(focus_list, new TypeToken<ArrayList<FocusListBean>>() {
                        }.getType());

                        if (ListBeen != null) {
                            if (!ListBeen.isEmpty()) {
                                if (1 == page) {
                                    NewsListBean bean = new NewsListBean();
                                    bean.setCateList(ListBeen);
                                    newsList.add(bean);
                                }
                            }
                        }

                        ArrayList<NewsListBean> cateList = JsonUtil.getBean(lists, new TypeToken<ArrayList<NewsListBean>>() {
                        }.getType());

                        if (cateList != null) {
                            if (!cateList.isEmpty()) {
                                newsList.addAll(cateList);
                            }
                        }

                        setList(newsList);
                        setMylistview(newsList);
                    } catch (Exception e) {
//                        T.showShort(getActivity(),"解析数据失败");
                        e.printStackTrace();
                    }
                    stopRefreshing();
                } else {
                    ShopHelper.showApiError(getActivity(), data.getJson());
                }
            }
        });
    }

    private void setMylistview(final ArrayList<NewsListBean> cateList) {
        if (!cateList.isEmpty()) {
//            myListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            myListView.setOnScrollListener(new SwpipeListViewOnScrollListener(swipe_container, new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    mScrollState = scrollState;
                    switch (scrollState) {
                        case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                            Log.e("videoTest", "SCROLL_STATE_FLING");
                            break;
                        case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                            Log.e("videoTest", "SCROLL_STATE_IDLE");
                            if (!cateList.isEmpty()) {
                                mListItemVisibilityCalculator.onScrollStateIdle(mItemsPositionGetter, view.getFirstVisiblePosition(), view.getLastVisiblePosition());
                            }
//                                            autoPlayVideo(view);
                            if (myListView.getLastVisiblePosition() == newsList.size() - 1) {
                                if (isHasMore) {
                                    onLoadMore();
                                }
                            }
                            break;
                        case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                            Log.e("videoTest", "SCROLL_STATE_TOUCH_SCROLL");
                            break;
                        default:
                            break;
                    }


                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if (!cateList.isEmpty()) {
                        mListItemVisibilityCalculator.onScroll(mItemsPositionGetter, firstVisibleItem, visibleItemCount, mScrollState);
                        lastVisibleItem = myListView.getLastVisiblePosition();
                    }
                }
            }));
//            });
        }
    }

    public void stopRefreshing() {
//        T.showShort(getActivity(),"加载完成");
        swipe_container.post(new Runnable() {
            @Override
            public void run() {
                swipe_container.setRefreshing(false);
            }
        });
    }

    public void setList(ArrayList<NewsListBean> cateList) {
        mList.clear();
        for (int i = 0; i < cateList.size(); i++) {
            mList.add(new VisibilityItem(cateList.get(i)));
        }
//        myListView.setFooterView();
        if (1 == page) {
            myListView.setAdapter(adapter);
        }
        adapter.setmList(mList);
//        adapter.notifyDataSetChanged();
        initCalculator(cateList);
    }


    /*收藏*/
    public void ShouCang(String store_id) {
        String url = Constants.URL_STORE_ADD_FAVORITES;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", MyShopApplication.getInstance().getLoginKey());
        params.put("store_id", store_id);
        RemoteDataHandler.asyncLoginPostDataString(url, params, MyShopApplication.getInstance(), new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    if (json.equals("1")) {
                        T.showShort(getActivity(), "收藏成功");
                        LoadDate();
                    }
                } else {
                    ShopHelper.showApiError(getActivity(), json);
                }
            }
        });
    }

    /*取消收藏*/
    public void UnShouCang(String store_id) {
        String url = Constants.URL_STORE_DELETE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", MyShopApplication.getInstance().getLoginKey());
        params.put("store_id", store_id);
        RemoteDataHandler.asyncLoginPostDataString(url, params, MyShopApplication.getInstance(), new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    if ("1".equals(json)) {
                        T.showShort(getActivity(), "取消成功");
                        LoadDate();
                    }
                } else {
                    ShopHelper.showApiError(getActivity(), json);
                }
            }
        });
    }

    public void onLoadMore() {
        page++;
        LoadTabDate(cate_id);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SimpleCardEvent event) {
            initCalculator(newsList);
    }
}