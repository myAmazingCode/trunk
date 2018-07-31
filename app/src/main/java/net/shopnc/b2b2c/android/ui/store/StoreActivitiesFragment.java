package net.shopnc.b2b2c.android.ui.store;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.StoreMSActivities;
import net.shopnc.b2b2c.android.bean.StoreMSRules;
import net.shopnc.b2b2c.android.bean.StoreXSActivities;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.DateConvert;
import net.shopnc.b2b2c.android.common.LoadImage;
import net.shopnc.b2b2c.android.common.LogHelper;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * 店铺活动fragment
 *
 * Created by 胡婷 on 2015/12/29.
 */
public class StoreActivitiesFragment extends Fragment {

    final static public Gson gson;

    static {
        gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateConvert())
                .create();
    }

    private MyShopApplication myApplication;
    private static final String ARG_STORE_ID = "store_id";
    private String store_id;

    private LinearLayout llNoData;
    private LinearLayout llStoreActivityList;

    public StoreActivitiesFragment() {
    }

    public static StoreActivitiesFragment newInstance(String store_id) {
        StoreActivitiesFragment fragment = new StoreActivitiesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_STORE_ID, store_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            store_id = getArguments().getString(ARG_STORE_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_store_activity, container, false);
        MyExceptionHandler.getInstance().setContext(getActivity());
        myApplication = (MyShopApplication) getActivity().getApplicationContext();

        initView(layout);
        initData();

        return layout;
    }

    /**
     * 初始化控件
     *
     * @param layout
     */
    private void initView(View layout) {
        llNoData = layout.findViewById(R.id.llNoData);
        llStoreActivityList = layout.findViewById(R.id.llStoreActivityList);
    }

    /**
     * 初始化界面数据
     */
    private void initData() {
        final ArrayList<StoreMSRules> arrayList = new ArrayList<StoreMSRules>();
        String url = Constants.URL_STORE_ACTIVITIES +"&store_id=" +store_id;

        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == HttpStatus.SC_OK) {
                    String json = data.getJson();

                    if (data.getCode() == HttpStatus.SC_OK) {
                        try {
                            JSONObject obj = new JSONObject(json);
                            String promotion = obj.getString("promotion");

                            if (promotion != null && !promotion.equals("") && !promotion.equals("{}")) {
                                JSONObject object = new JSONObject(promotion);
                                String xianshi = object.getString("xianshi");
                                String mansong = object.getString("mansong");

                                //满即送
                                if (!mansong.equals("") && !mansong.equals("{}") && mansong != null) {
                                    StoreMSActivities storeMSActivities = StoreMSActivities.newInstanceList(mansong);
                                    JSONArray jsonArray = new JSONArray(storeMSActivities.getRules());
                                    for (int i = 0; i < jsonArray.length(); i++){
                                        StoreMSRules storeMSRules = gson.fromJson(jsonArray.get(i).toString(),StoreMSRules.class);
                                        arrayList.add(storeMSRules);

                                        LogHelper.d("huting--rules:", storeMSRules.toString());
                                    }
                                    LogHelper.d("huting--mansong:", storeMSActivities.toString());

                                    //满即送
                                    View storeMsListView = getStoreMSListItemView(storeMSActivities,arrayList);
                                    llStoreActivityList.addView(storeMsListView);
                                }else{

                                }

                               //限时折扣
                                if (!xianshi.equals("") && !xianshi.equals("{}") && xianshi != null) {
                                   StoreXSActivities storeXSActivities = gson.fromJson(xianshi, StoreXSActivities.class);
                                   LogHelper.d("huting--xianshi:", storeXSActivities.toString());

                                    //限时折扣
                                    View storeXsListItemView = getStoreXSListItemView(storeXSActivities);
                                    llStoreActivityList.addView(storeXsListItemView);
                                }else{

                                }

                                llNoData.setVisibility(View.GONE);

                            } else {
                                llNoData.setVisibility(View.VISIBLE);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Toast.makeText(getActivity(), R.string.load_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 显示店铺活动--限时折扣的视图
     * @param storeXSActivities
     * @return
     */
    private View getStoreXSListItemView(StoreXSActivities storeXSActivities) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View storeXsListItemView = inflater.inflate(R.layout.store_activities_xs_item, null);

        //折扣标题
        TextView textTitle = storeXsListItemView.findViewById(R.id.textTitle);
        textTitle.setText(storeXSActivities.getXianshi_title());

        //活动时间
        TextView textTime = storeXsListItemView.findViewById(R.id.textTime);
        textTime.setText("活动时间：" + storeXSActivities.getStart_time_text() + "至" + storeXSActivities.getEnd_time_text());

        //活动限制
        TextView textLimit = storeXsListItemView.findViewById(R.id.textLimit);
        textLimit.setText("单件和活动商品满" + storeXSActivities.getLower_limit() + "件即可享受折扣价");

        //活动说明
        TextView textExplain = storeXsListItemView.findViewById(R.id.textExplain);
        textExplain.setText("活动说明：" + storeXSActivities.getXianshi_explain());

        LinearLayout llXS = storeXsListItemView.findViewById(R.id.llXS);
        llXS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),StoreGoodsListFragmentManager.class);
                intent.putExtra("store_id",store_id);
                startActivity(intent);
            }
        });

        return storeXsListItemView;
    }

    /**
     * 满即送
     * @param storeMSActivities
     * @return
     */
    private View getStoreMSListItemView(StoreMSActivities storeMSActivities,ArrayList<StoreMSRules> storeMSRules) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View storeMsListItemView = inflater.inflate(R.layout.store_activities_ms_item, null);

        //满送的标题
        TextView textMSName = storeMsListItemView.findViewById(R.id.textMSName);
        textMSName.setText(storeMSActivities.getMansong_name());

        //满送活动时间、textMSTime
        TextView textMSTime = storeMsListItemView.findViewById(R.id.textMSTime);
        textMSTime.setText("活动时间：" + storeMSActivities.getStart_time_text() + "至" + storeMSActivities.getEnd_time_text());

        //活动说明 textMSRemark
        TextView textMSRemark = storeMsListItemView.findViewById(R.id.textMSRemark);
        textMSRemark.setText("活动说明：" + storeMSActivities.getRemark());

        //活动规则
        LinearLayout llMSRules = storeMsListItemView.findViewById(R.id.llMSRules);
        for (int i = 0; i < storeMSRules.size(); i++){
            View rulesItem = inflater.inflate(R.layout.store_ms_rules_item, null);

            TextView textMSRules = rulesItem.findViewById(R.id.textMSRules);
            textMSRules.setText("单笔消费满￥" + storeMSRules.get(i).getPrice() + "，减现金￥" + storeMSRules.get(i).getDiscount() + "，获赠");

            ImageView imgMS = rulesItem.findViewById(R.id.imgMS);
            LoadImage.loadImg(getActivity(), imgMS, storeMSRules.get(i).getGoods_image_url());

            llMSRules.addView(rulesItem);
        }

        LinearLayout llMS = storeMsListItemView.findViewById(R.id.llMS);
        llMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),StoreGoodsListFragmentManager.class);
                intent.putExtra("store_id",store_id);
                startActivity(intent);
            }
        });

        return storeMsListItemView;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
