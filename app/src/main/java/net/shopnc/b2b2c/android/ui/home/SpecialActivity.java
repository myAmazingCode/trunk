package net.shopnc.b2b2c.android.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.ApiSpecialItem;
import net.shopnc.b2b2c.android.circlelibrary.ImageCycleView;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.xrefresh.XRefreshView;
import net.shopnc.b2b2c.android.xrefresh.XScrollView;

import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

/**
 * 专题页面
 *
 * @author huting
 * @date 2016/5/4
 */
public class SpecialActivity extends BaseActivity{

    @Bind(R.id.cycleView) ImageCycleView cycleView;
    @Bind(R.id.xrefreshview) XRefreshView xrefreshview;
    @Bind(R.id.top_btn)Button toTopBtn;
    @Bind(R.id.sv)XScrollView scrollView;
    @Bind(R.id.homeViewID)LinearLayout homeViewID;
    @Bind(R.id.head)RelativeLayout head;

    @Bind(R.id.layoutEmpty)
    RelativeLayout layoutEmpty;
    @Bind(R.id.imgEmptyLogo)
    ImageView imgEmptyLogo;
    @Bind(R.id.tvEmptyTitle)
    TextView tvEmptyTitle;
    @Bind(R.id.tvEmptyBody)
    TextView tvEmptyBody;

    private HomeShowViewHelper homeHelper;
    private String url = "";

    @Override
    protected void setView() {
        setContentView(R.layout.activity_special);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getIntent().getStringExtra("url");
        setCommonHeader("专题页面");
        initView();
    }

    /**
     * 初始化组建
     */
    private void initView(){
        xrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });

        homeHelper = new HomeShowViewHelper(activity,homeViewID,cycleView);
        initData();
    }

    /**
     * 加载数据
     */
    private void initData(){
        OkHttpUtil.getAsyn(this,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                LogHelper.e("专题",data);
                List<ApiSpecialItem> apiSpecialItemList = JsonUtil.toBean(data, "itemList", new TypeToken<List<ApiSpecialItem>>() {
                }.getType());
                if(homeViewID == null) {
                    return;
                }

                homeViewID.removeAllViews();
                xrefreshview.stopRefresh();

                if(apiSpecialItemList!=null&&!apiSpecialItemList.isEmpty()){
                    xrefreshview.setVisibility(View.VISIBLE);
                    layoutEmpty.setVisibility(View.GONE);
                    HomeLoadDataHelper.indgeJump(homeHelper,apiSpecialItemList,true,head);
                }else {
                    xrefreshview.setVisibility(View.GONE);
                    layoutEmpty.setVisibility(View.VISIBLE);
                    imgEmptyLogo.setImageResource(R.drawable.mc_01_b);
                    tvEmptyTitle.setText("暂无专题");
                    tvEmptyBody.setText("请等待员工添加");
                }
            }

            @Override
            public void fail(String error) {
                super.fail(error);
                if(xrefreshview == null) {
                    return;
                }
                xrefreshview.stopRefresh();
            }

            @Override
            public void error(Call call, Exception e, int i) {
                super.error(call, e, i);
                if(xrefreshview == null) {
                    return;
                }
                xrefreshview.stopRefresh();
            }
        });
    }

}
