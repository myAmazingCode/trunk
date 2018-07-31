package net.shopnc.b2b2c.android.ui.type;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.custom.MyScrollView;
import net.shopnc.b2b2c.android.custom.MyWebView;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

/**
 * 商品描述Fragment
 */
public class GoodsDetailBodyFragment extends Fragment {
    private static final String ARG_GOODS_ID = "goods_id";

    private String goodsId;
    private MyWebView wvGoodsBody;
    private MyScrollView sc;
    private RelativeLayout main_rl;
    private Button bt;
    private OnFragmentInteractionListener mListener;
    public static final String TAG = GoodsDetailBodyFragment.class.getSimpleName();

    public static GoodsDetailBodyFragment newInstance(String goodsId) {
        GoodsDetailBodyFragment fragment = new GoodsDetailBodyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_GOODS_ID, goodsId);
        fragment.setArguments(args);
        return fragment;
    }

    public GoodsDetailBodyFragment() {
        // Required empty public constructor
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
        init();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            goodsId = getArguments().getString(ARG_GOODS_ID);
        }
    }


    private Boolean isTops = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_goods_detail_body, container, false);
        MyExceptionHandler.getInstance().setContext(getActivity());
        wvGoodsBody = layout.findViewById(R.id.wvGoodsBody);
        sc = layout.findViewById(R.id.sc);

        wvGoodsBody.setOnScrollToTopLintener(new MyWebView.OnScrollToTopListener() {
            @Override
            public void onScrollTopListener(boolean isTop) {
                isTops = isTop;
            }
        });
        wvGoodsBody.setOnScrollListener(new MyWebView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {
                if (isTops) {
                    bt.setVisibility(View.GONE);
                } else {
                    bt.setVisibility(View.VISIBLE);
                }
            }
        });

        main_rl = layout.findViewById(R.id.main_rl);

        bt = layout.findViewById(R.id.top_btn);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wvGoodsBody.post(new Runnable() {
                    @Override
                    public void run() {
                        wvGoodsBody.scrollTo(0, 0);
                    }
                });
                bt.setVisibility(View.GONE);
            }
        });

        init();

//        wvGoodsBody.loadUrl(Constants.URL_GOODS_BODY + "&goods_id=" + goodsId);
        return layout;
    }

    private void init() {
        Log.d(TAG, "init: " + goodsId);
        RemoteDataHandler.asyncDataStringGet1(Constants.URL_GOODS_BODY + "&goods_id=" + goodsId, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {

                if (data.getCode() == 700) {
                    Log.d(TAG, "dataLoaded: " + data.getJson());
                    initWebView(data.getJson());
                }
            }
        });
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
        mListener = null;
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

    private void initWebView(String goodsBody) {
        String s = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<style type=\"text/css\">\n" +
                "\t\t.img-ks-lazyload{\n" +
                "\t\t\ttext-align: center;\n" +
                "\t\t}\n" +
                "\t\t.img-ks-lazyload img{\n" +
                "\t\t\twidth: 100% !important;\n" +
                "\t\t}\n" +
                "\t</style>\n" +
                "</head>\n" +
                "<body>" +
                "<div class=\"img-ks-lazyload\">" +
                goodsBody +
                "</div>" +
                "</body>\n" +
                "</html>";
        wvGoodsBody.loadDataWithBaseURL(null, s, "text/html", "utf-8", null);
    }
}