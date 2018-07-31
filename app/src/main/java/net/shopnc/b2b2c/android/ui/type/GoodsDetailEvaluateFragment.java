package net.shopnc.b2b2c.android.ui.type;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.GoodsEvaluateDetailListViewAdapter;
import net.shopnc.b2b2c.android.bean.GoodsEvaluateInfo;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 商品详细评价Fragment
 */
public class GoodsDetailEvaluateFragment extends Fragment {

    private static final String ARG_GOODS_ID = "goods_id";
    private static final int EVALUATE_TYPE_ALL = 0;
    private static final int EVALUATE_TYPE_GOOD = 1;
    private static final int EVALUATE_TYPE_NORMAL = 2;
    private static final int EVALUATE_TYPE_BAD = 3;
    private static final int EVALUATE_TYPE_IMAGE = 4;
    private static final int EVALUATE_TYPE_AGAIN = 5;

    private String goodsId;
    ArrayList<GoodsEvaluateInfo> goodsEvaluateInfoList = new ArrayList<GoodsEvaluateInfo>();
    private String currentType;
    private int currentPage;
    boolean isHasMore = true;
    boolean isLastRow = false;

    private Button btnEvaluateAll;
    private Button btnEvaluateGood;
    private Button btnEvaluateNormal;
    private Button btnEvaluateBad;
    private Button btnEvaluateImage;
    private Button btnEvaluateAgain;

    private ListView lvEvaluateList;
    private GoodsEvaluateDetailListViewAdapter goodsEvaluateDetailListViewAdapter;

    //空列表
    private LinearLayout llListEmpty;

    private OnFragmentInteractionListener mListener;

    private boolean isCreate = false;//fragment是否创建

    public static GoodsDetailEvaluateFragment newInstance(String goodsId) {
        GoodsDetailEvaluateFragment fragment = new GoodsDetailEvaluateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_GOODS_ID, goodsId);
        fragment.setArguments(args);
        return fragment;
    }

    public GoodsDetailEvaluateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreate = true;
        if (getArguments() != null) {
            goodsId = getArguments().getString(ARG_GOODS_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("333333", "onCreateView: ");
        View layout = inflater.inflate(R.layout.fragment_goods_detail_evaluate, container, false);
        MyExceptionHandler.getInstance().setContext(getActivity());
        btnEvaluateAll = layout.findViewById(R.id.btnEvaluateAll);
        btnEvaluateAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeType(EVALUATE_TYPE_ALL);
            }
        });
        btnEvaluateGood = layout.findViewById(R.id.btnEvaluateGood);
        btnEvaluateGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeType(EVALUATE_TYPE_GOOD);
            }
        });
        btnEvaluateNormal = layout.findViewById(R.id.btnEvaluateNormal);
        btnEvaluateNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeType(EVALUATE_TYPE_NORMAL);
            }
        });
        btnEvaluateBad = layout.findViewById(R.id.btnEvaluateBad);
        btnEvaluateBad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeType(EVALUATE_TYPE_BAD);
            }
        });
        btnEvaluateImage = layout.findViewById(R.id.btnEvaluateImage);
        btnEvaluateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeType(EVALUATE_TYPE_IMAGE);
            }
        });
        btnEvaluateAgain = layout.findViewById(R.id.btnEvaluateAgain);
        btnEvaluateAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeType(EVALUATE_TYPE_AGAIN);
            }
        });

        lvEvaluateList = layout.findViewById(R.id.lvEvaluateList);
        lvEvaluateList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (isHasMore && isLastRow && i == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    isLastRow = false;
                    currentPage += 1;
                    loadingGoodsEvaluate();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //判断是否滚到最后一行
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
                    isLastRow = true;
                }
            }
        });
        goodsEvaluateDetailListViewAdapter = new GoodsEvaluateDetailListViewAdapter(getActivity());
        lvEvaluateList.setAdapter(goodsEvaluateDetailListViewAdapter);

        //空列表
        llListEmpty = layout.findViewById(R.id.llListEmpty);
        ImageView ivListEmpty = layout.findViewById(R.id.ivListEmpty);
        ivListEmpty.setImageResource(R.drawable.nc_icon_eval);
        TextView tvListEmptyTitle = layout.findViewById(R.id.tvListEmptyTitle);
        TextView tvListEmptySubTitle = layout.findViewById(R.id.tvListEmptySubTitle);
        tvListEmptyTitle.setText("该商品未收到任何评价");
        tvListEmptySubTitle.setText("期待您的购买与评论！");

        changeType(EVALUATE_TYPE_ALL);
        goodsId = Constants.goodId;
        loadingGoodsEvaluate();
        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        goodsId = Constants.goodId;
        loadingGoodsEvaluate();
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
        if (isCreate) {
            loadingGoodsEvaluate();
        }
    }

    /**
     * 初始化加载数据
     */
    public void loadingGoodsEvaluate() {
        String url = Constants.URL_GOODS_EVALUATE + "&goods_id=" + goodsId + "&type=" + currentType + "&curpage=" + String.valueOf(currentPage) + "&page=" + Constants.PAGESIZE;

        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    isHasMore = data.isHasMore();
                    if (currentPage == 1) {
                        goodsEvaluateInfoList.clear();
                        if (lvEvaluateList != null) {
                            lvEvaluateList.smoothScrollToPosition(0);
                        }
                    }
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        goodsEvaluateInfoList.addAll(GoodsEvaluateInfo.newInstanceList(jsonObject.getString("goods_eval_list")));
                        if (goodsEvaluateInfoList.size() > 0) {
                            if (llListEmpty != null) {
                                llListEmpty.setVisibility(View.GONE);
                            }
                            goodsEvaluateDetailListViewAdapter.setList(goodsEvaluateInfoList);
                            goodsEvaluateDetailListViewAdapter.notifyDataSetChanged();
                        } else {
                            if (llListEmpty != null) {
                                llListEmpty.setVisibility(View.VISIBLE);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ShopHelper.showApiError(getActivity(), json);
                }
            }
        });
    }

    /**
     * 设置按钮状态
     *
     * @param type
     */
    private void changeType(int type) {
        currentType = String.valueOf(type);
        currentPage = 1;

        btnEvaluateAll.setActivated(false);
        btnEvaluateGood.setActivated(false);
        btnEvaluateNormal.setActivated(false);
        btnEvaluateBad.setActivated(false);
        btnEvaluateImage.setActivated(false);
        btnEvaluateAgain.setActivated(false);

        switch (type) {
            case EVALUATE_TYPE_ALL:
                btnEvaluateAll.setActivated(true);
                loadingGoodsEvaluate();
                break;
            case EVALUATE_TYPE_GOOD:
                btnEvaluateGood.setActivated(true);
                loadingGoodsEvaluate();
                break;
            case EVALUATE_TYPE_NORMAL:
                btnEvaluateNormal.setActivated(true);
                loadingGoodsEvaluate();
                break;
            case EVALUATE_TYPE_BAD:
                btnEvaluateBad.setActivated(true);
                loadingGoodsEvaluate();
                break;
            case EVALUATE_TYPE_IMAGE:
                btnEvaluateImage.setActivated(true);
                loadingGoodsEvaluate();
                break;
            case EVALUATE_TYPE_AGAIN:
                btnEvaluateAgain.setActivated(true);
                loadingGoodsEvaluate();
                break;
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
