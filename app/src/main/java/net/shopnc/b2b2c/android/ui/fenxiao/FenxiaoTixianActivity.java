package net.shopnc.b2b2c.android.ui.fenxiao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.FenxiaoOrderlist;
import net.shopnc.b2b2c.android.bean.PointLogInfo;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.JsonUtil;
import net.shopnc.b2b2c.android.common.LoadImage;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.SwpipeListViewOnScrollListener;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.custom.XListView;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by snm on 2016/9/23.
 */
public class FenxiaoTixianActivity extends BaseActivity implements XListView.IXListViewListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private XListView listView;
    private SwipeRefreshLayout.OnRefreshListener listener;
    public String paystate_search;
    FenxiaoOrderAdapter adapter;
    private TextView yongjin, dongjie, tvCommonRight;
    String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian_list);
        setCommonHeader("分销提现");
        setListEmpty(R.drawable.nc_icon_order, "没有找到任何相关信息", "");
        initView();
    }

    public void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        listView = (XListView) findViewById(R.id.mylistview);

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);

        listView.setXListViewListener(this);
        listView.setPullRefreshEnable(false);
        listView.setPullLoadEnable(false);

        listener = new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                LoadDate();
            }
        };
        swipeRefreshLayout.setOnRefreshListener(listener);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        listener.onRefresh();
        adapter = new FenxiaoOrderAdapter(getApplicationContext());
        listView.setOnScrollListener(new SwpipeListViewOnScrollListener(swipeRefreshLayout));
        yongjin = (TextView) findViewById(R.id.yongjian);
        dongjie = (TextView) findViewById(R.id.dongjie);
        tvCommonRight = (TextView) findViewById(R.id.tvCommonRight);
        tvCommonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TixianOnclick();
            }
        });
    }

    private void LoadDate() {
        String url = Constants.URL_DISTRI_CASH;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", MyShopApplication.getInstance().getLoginKey());
//        params.put("sn_search", "");
//        params.put("paystate_search", "");
        Logger.d(params.toString());
        RemoteDataHandler.asyncLoginPostDataString(url, params, MyShopApplication.getInstance(), new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {

                String json = data.getJson();
                Logger.d(data.getJson());
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        String available_trad = obj.getString("available_trad");
                        String freeze_trad = obj.getString("freeze_trad");
                        String cash_list = obj.getString("cash_list");
                        price = available_trad;
                        yongjin.setText("可提现金额: ￥" + available_trad);
                        dongjie.setText("冻结金额: ￥" + freeze_trad);
                        ArrayList<CashList> list = JsonUtil.getBean(cash_list, new TypeToken<ArrayList<CashList>>() {
                        }.getType());
                        if (list.isEmpty()) {
                            showListEmpty();
                        } else {
                            hideListEmpty();
                            adapter.setList(list);
                            listView.setAdapter(adapter);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    showListEmpty();
                    ShopHelper.showApiError(getApplicationContext(), json);
                }
                stopRefreshing();
            }
        });
    }

    public void stopRefreshing() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }


    public class FenxiaoOrderAdapter extends BaseAdapter {

        ArrayList<CashList> list;
        private LayoutInflater inflater;
        private Context mContext;

        private FenxiaoOrderAdapter(Context c) {
            this.mContext = c;
            this.inflater = LayoutInflater.from(c);
        }

        public void setList(ArrayList<CashList> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (null == convertView) {
                convertView = inflater.inflate(R.layout.listview_point_log_item, parent, false);
                holder = new ViewHolder();
                holder.tvDesc = convertView.findViewById(R.id.tvDesc);
                holder.tvSn = convertView.findViewById(R.id.tvSn);
                holder.tvPoints = convertView.findViewById(R.id.tvPoints);
                holder.tvAddTimeText = convertView.findViewById(R.id.tvAddTimeText);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final CashList info = list.get(position);
            holder.tvDesc.setText("订单" + info.getTradc_sn());
            String state = info.getTradc_payment_state();
            if (state != null) {
                if ("1".equals(state)) {
                    holder.tvSn.setText("已支付");
                } else {
                    holder.tvSn.setText("未支付");
                }

            }
            Double points = Double.parseDouble(info.getTradc_amount());
            if (points > 0) {
                holder.tvPoints.setText("+" + info.getTradc_amount());
                holder.tvPoints.setTextColor(mContext.getResources().getColor(R.color.nc_red));
            } else {
                holder.tvPoints.setText(info.getTradc_amount());
                holder.tvPoints.setTextColor(mContext.getResources().getColor(R.color.nc_green));
            }
            holder.tvAddTimeText.setText(SystemHelper.getTimeStr(info.getTradc_add_time()));

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, FenxiaoTixianInfoActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id", info.getTradc_id());
                    mContext.startActivity(intent);
                }
            });

            return convertView;
        }

        class ViewHolder {
            TextView tvDesc;
            TextView tvSn;
            TextView tvPoints;
            TextView tvAddTimeText;
        }
    }

    /*提现点击事件*/
    public void TixianOnclick() {
        Intent intent = new Intent(getApplicationContext(), FenxiaoWithdrawalsActivity.class);
        intent.putExtra("price", price);
        startActivity(intent);
    }

    public class CashList {

        /**
         * tradc_add_time : 1474161983
         * tradc_payment_state : 未支付
         * tradc_sn : 820527505984088011
         * tradc_amount : 421.70
         * tradc_id : 2
         */

        private String tradc_add_time;
        private String tradc_payment_state;
        private String tradc_sn;
        private String tradc_amount;
        private String tradc_id;

        public String getTradc_add_time() {
            return tradc_add_time;
        }

        public void setTradc_add_time(String tradc_add_time) {
            this.tradc_add_time = tradc_add_time;
        }

        public String getTradc_payment_state() {
            return tradc_payment_state;
        }

        public void setTradc_payment_state(String tradc_payment_state) {
            this.tradc_payment_state = tradc_payment_state;
        }

        public String getTradc_sn() {
            return tradc_sn;
        }

        public void setTradc_sn(String tradc_sn) {
            this.tradc_sn = tradc_sn;
        }

        public String getTradc_amount() {
            return tradc_amount;
        }

        public void setTradc_amount(String tradc_amount) {
            this.tradc_amount = tradc_amount;
        }

        public String getTradc_id() {
            return tradc_id;
        }

        public void setTradc_id(String tradc_id) {
            this.tradc_id = tradc_id;
        }
    }
}
