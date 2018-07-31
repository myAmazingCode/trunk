package net.shopnc.b2b2c.android.ui.trys;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.MainFragmentManager;
import net.shopnc.b2b2c.android.adapter.TryGoodReportDetailsAdapter;
import net.shopnc.b2b2c.android.bean.TryGoodReportBean;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.custom.dialog.ChooseDialog;
import net.shopnc.b2b2c.android.ui.good.GoodDetailsActivity;
import net.shopnc.b2b2c.android.util.ConstantBroadCast;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.LoadImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TryGoodReportDetailsActivity extends AppCompatActivity {

    @Bind(R.id.etSearchText)
    TextView etSearchText;
    @Bind(R.id.title_bar_search)
    LinearLayout title_bar_search;
    @Bind(R.id.imgClassify)
    ImageView imgClassify;
    @Bind(R.id.imgMenu)
    ImageView imgMenu;
    @Bind(R.id.rvDetails)
    RecyclerView rvDetails;
    @Bind(R.id.tvMoreTry)
    TextView tvMoreTry;
    @Bind(R.id.tvBuy)
    TextView tvBuy;

    private int trysId;
    private int applyId;

    private Context context;
    private MyShopApplication application;

    private TryGoodReportDetailsAdapter adapter;
    private TryGoodReportBean tryGoodReportBean;

    public static void onStartActivity(Context c, int trysId, int applyId) {
        Intent intent = new Intent(c, TryGoodReportDetailsActivity.class);
        intent.putExtra("trysId", trysId);
        intent.putExtra("applyId", applyId);
        c.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_try_good_report_details);
        ButterKnife.bind(this);
        context = this;
        application = MyShopApplication.getInstance();
        trysId = getIntent().getIntExtra("trysId", 0);
        applyId = getIntent().getIntExtra("applyId", 0);
        LoadImage.loadLocalGreyImg(this, imgClassify, R.drawable.stiore_categroy_b);
        LoadImage.loadLocalGreyImg(this, imgMenu, R.drawable.nc_icon_more_dot);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvDetails.setLayoutManager(manager);
        adapter = new TryGoodReportDetailsAdapter(context);
        rvDetails.setAdapter(adapter);
        loadData();
    }

    private void loadData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("trysId", trysId + "");
        params.put("applyId", applyId + "");

        OkHttpUtil.postAsyn(this,ConstantUrl.URL_TRY_REPORT_INFO, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                tryGoodReportBean = JsonUtil.toBean(data, "reportInfo", TryGoodReportBean.class);
                AddViewHolder holder = new AddViewHolder(context, R.layout.recyclerview_try_good_report);
                holder.setImage(R.id.ivMemberImg, tryGoodReportBean.getAvatarUrl())
                        .setText(R.id.tvMemberName, tryGoodReportBean.getMemberNameEncrypt())
                        .setText(R.id.tvTime, tryGoodReportBean.getReportTime())
                        .setText(R.id.tvSuggest, tryGoodReportBean.getSuggest());
                List<View> views = new ArrayList<>();
                views.add(holder.getCustomView());
                adapter.setHeadViews(views);
                adapter.setDatas(tryGoodReportBean.getContentList());
                adapter.setImages(tryGoodReportBean.getImageList());
                adapter.notifyDataSetChanged();
            }
        }, params);
    }

    @OnClick({R.id.btnBack, R.id.imgClassify, R.id.imgMenu, R.id.tvMoreTry, R.id.tvBuy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.imgMenu:
                ChooseDialog dialog = new ChooseDialog(context, application, imgMenu);
                break;
            case R.id.imgClassify:
                Intent intent = new Intent(context, MainFragmentManager.class);
                application.sendBroadcast(new Intent(ConstantBroadCast.SHOW_ClASSIFY_URL));
                context.startActivity(intent);
                break;
            case R.id.tvMoreTry:
                context.startActivity(new Intent(context, TryGoodShowActivity.class));
                break;
            case R.id.tvBuy:
                Intent i = new Intent(context, GoodDetailsActivity.class);
                i.putExtra(GoodDetailsActivity.COMMONID, tryGoodReportBean.getCommonId());
                context.startActivity(i);
                break;
        }
    }
}
