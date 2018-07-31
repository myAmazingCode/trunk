package net.shopnc.b2b2c.android.ui.good;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseFragment;
import net.shopnc.b2b2c.android.bean.GoodsDetailBean;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description 商品详情、电脑板详情
 * @Author qyf
 * <p>
 * Created 2016/4/19 17:11.
 */
public class GoodDetailsBrowseFragment extends BaseFragment {

    @Bind(R.id.webDetail)
    WebView webView;

    private String mIntroHtml;
    private String mProtectionHtml;
    private String mCommitmentHtml;
    private String mSpecHtml;
    public int commonId;

    //商品详情tabs
    @Bind(R.id.tvIntro)
    TextView tvIntro;
    @Bind(R.id.tvSpec)
    TextView tvSpec;
    @Bind(R.id.dividerSpec)
    View dividerSpec;
    @Bind(R.id.dividerProtection)
    View dividerProtection;
    @Bind(R.id.dividerCommitment)
    View dividerCommitment;
    @Bind(R.id.tvProtection)
    TextView tvProtection;
    @Bind(R.id.tvCommitment)
    TextView tvCommitment;


    //商品介绍、规格参数、售后保障、商家承诺
    @OnClick({R.id.tvIntro, R.id.tvSpec, R.id.tvCommitment, R.id.tvProtection,})
    public void tabClick(View view) {
        switch (view.getId()) {
            case R.id.tvIntro:
                webView.loadDataWithBaseURL(null, mIntroHtml, "text/html", "utf-8", null);
                break;
            case R.id.tvSpec:
                webView.loadDataWithBaseURL(null, mSpecHtml, "text/html", "utf-8", null);
                break;
            case R.id.tvCommitment:
//                webView.loadDataWithBaseURL(null, mCommitmentHtml, "text/html", "utf-8", null);
                initWebView(mCommitmentHtml);
                break;
            case R.id.tvProtection:
//                webView.loadDataWithBaseURL(null, mProtectionHtml, "text/html", "utf-8", null);
                initWebView(mProtectionHtml);
                break;
        }
        setTabStatus(view);
    }

    /**
     * 设置选中状态
     *
     * @param v
     */
    private void setTabStatus(View v) {
        tvIntro.setSelected(false);
        tvSpec.setSelected(false);
        tvProtection.setSelected(false);
        tvCommitment.setSelected(false);

        v.setSelected(true);
    }

    //商品详情等
    private void loadGoodsDetail() {
        String url = ConstantUrl.URL_API + "/goods/extend/" + commonId;
        OkHttpUtil.getAsyn(getActivity(),url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                GoodsDetailBean goodsDetailBean = JsonUtil.toBean(data, GoodsDetailBean.class);
                //售后保障
                mProtectionHtml = goodsDetailBean.getProtection();
                if (TextUtils.isEmpty(mProtectionHtml)) {
                    tvProtection.setVisibility(View.GONE);
                    dividerProtection.setVisibility(View.GONE);
                }
                //商家承诺
                mCommitmentHtml = goodsDetailBean.getCommitment();
                if (TextUtils.isEmpty(mCommitmentHtml)) {
                    tvCommitment.setVisibility(View.GONE);
                    dividerCommitment.setVisibility(View.GONE);
                }
                //商品介绍
                List<GoodsDetailBean.GoodsMobileBodyVoListBean> goodsMobileBodyVoList = goodsDetailBean.getGoodsMobileBodyVoList();
                mIntroHtml = "";
                if (goodsMobileBodyVoList != null) {
                    for (int i = 0; i < goodsMobileBodyVoList.size(); i++) {
                        GoodsDetailBean.GoodsMobileBodyVoListBean bean = goodsMobileBodyVoList.get(i);
                        String value = bean.getValue();
                        mIntroHtml += "<img src=\"" + value + "\" width=\"100%\"/><br>";
                    }
                } else {
                    tvIntro.setVisibility(View.GONE);
                }

//                Log.d(TAG, "response: introHtml = " + mIntroHtml);
                webView.loadDataWithBaseURL(null, mIntroHtml, "text/html", "utf-8", null);
                tvIntro.setSelected(true);
                //规格参数
                List<GoodsDetailBean.GoodsAttrVoListBean> goodsAttrVoList = goodsDetailBean.getGoodsAttrVoList();
                mSpecHtml = "";
                if (goodsAttrVoList != null && goodsAttrVoList.size() != 0) {
                    mSpecHtml += "<table border=\"1px\" width=\"100%\" style=\"border-collapse: collapse\">";
                    for (int i = 0; i < goodsAttrVoList.size(); i++) {
                        GoodsDetailBean.GoodsAttrVoListBean goodsAttrVoListBean = goodsAttrVoList.get(i);
                        String name = goodsAttrVoListBean.getName();
                        String value = goodsAttrVoListBean.getValue();
                        mSpecHtml += "<tr>";
                        mSpecHtml += "<td>";
                        mSpecHtml += name;
                        mSpecHtml += "</td>";

                        mSpecHtml += "<td>";
                        mSpecHtml += value;
                        mSpecHtml += "</td>";

                        mSpecHtml += "</tr>";
                    }
                    mSpecHtml += "</table>";

                } else {
                    tvSpec.setVisibility(View.GONE);
                    dividerSpec.setVisibility(View.GONE);
                }

            }
        });
    }

    public static GoodDetailsBrowseFragment newInstance(int commonId) {
        GoodDetailsBrowseFragment fragment = new GoodDetailsBrowseFragment();
        Bundle b = new Bundle();
        b.putInt("commonId", commonId);
        fragment.setArguments(b);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gooddetails_browse, container, false);
        ButterKnife.bind(this, view);
        this.commonId = getArguments().getInt("commonId", 0);

        WebSettings webSettings = webView.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);  //默认模式、读缓存可用并不过时，否则网络请求
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setDisplayZoomControls(false);//不显示缩放按钮
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);//不显示滚动条
        webView.setWebViewClient(new WebViewClient());
        loadGoodsDetail();
        return view;
    }

    private void initWebView(String goodsBody) {
        String s = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<style type=\"text/css\">\n" +
                "\t\t.img-ks-lazyload p{\n" +
                "\t\t\twidth: 98%;\n" +
//                "\t\t\ttext-align: center;\n" +
                "font-size:30px !important;" +
                "\t\t}\n" +
                "\t</style>\n" +
                "</head>\n" +
                "<body>" +
                "<div class=\"img-ks-lazyload\">" +
                goodsBody +
                "</div>" +
                "</body>\n" +
                "</html>";
//        Log.d("webView", "initWebView: s = "+s);
        webView.loadDataWithBaseURL(null, s, "text/html", "utf-8", null);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        webView.removeAllViews();
        webView.destroy();
        webView = null;
        ButterKnife.unbind(this);
    }

}
