package net.shopnc.b2b2c.android.ui.jifen;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.CreditsLogBean;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;

/**
 * 积分明细页面
 */

public class CreditsRecordActivity extends BaseActivity {

    @Bind(R.id.recycler_record)
    RecyclerView recyclerRecord;
    private RecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setBtnMoreVisible();
        setCommonHeader("积分明细");
        exchangeGoods();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_credits_record);
    }

    //加载数据
    public void exchangeGoods(){
        HashMap<String, String> params = new HashMap<>();
        params.put("key", application.getLoginKey());
        params.put("curpage", "1");
        params.put("page", "10");
        RemoteDataHandler.asyncPostDataString(Constants.URL_CREDITS_CREDITS_LOG, params, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == 200) {
                    String res = data.getJson();
                    CreditsLogBean bean = new Gson().fromJson(res, CreditsLogBean.class);
                    adapter = new RecordAdapter(context, R.layout.adapter_credits_log_list, bean.getLog_list());
                    recyclerRecord.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
//                    recyclerRecord.setAdapter(adapter);
                    initHeader();
                }
            }
        });
    }

    //加载头view
    public void initHeader(){
        HashMap<String, String> params = new HashMap<>();
        params.put("key", application.getLoginKey());
        params.put("fields", "point");
        RemoteDataHandler.asyncPostDataString(Constants.URL_CREDITS_CREDITS_LOG_HEAD, params, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == 200) {
                    String res = data.getJson();
                    try {
                        View view = View.inflate(CreditsRecordActivity.this, R.layout.adapter_credits_log_header, null);
                        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                        view.setLayoutParams(lp);
                        TextView tvCreditsNum = view.findViewById(R.id.tv_credits_num);
                        JSONObject json = new JSONObject(res);
                        String points = json.getString("point");
                        tvCreditsNum.setText(points);
                        HeaderAndFooterWrapper wrapper = new HeaderAndFooterWrapper(adapter);
                        wrapper.addHeaderView(view);
                        recyclerRecord.setAdapter(wrapper);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public class RecordAdapter extends CommonAdapter<CreditsLogBean.LogListBean> {

        public RecordAdapter(Context context, int layoutId, List<CreditsLogBean.LogListBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, CreditsLogBean.LogListBean logListBean, int position) {
            int pl_points = Integer.parseInt(logListBean.getPl_points());

            holder.setText(R.id.tv_stage_text, logListBean.getStagetext());
            holder.setText(R.id.tv_points, logListBean.getPl_points());
            holder.setText(R.id.tv_points_desc, logListBean.getPl_desc());
            holder.setText(R.id.tv_add_time, logListBean.getAddtimetext());
            if (pl_points < 0) {
                holder.setTextColorRes(R.id.tv_points, R.color.nc_text_blue);
            } else {
                holder.setTextColorRes(R.id.tv_points, R.color.nc_red);
            }
        }
    }
}
