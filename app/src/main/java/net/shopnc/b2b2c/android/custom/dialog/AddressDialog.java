package net.shopnc.b2b2c.android.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.Area;
import net.shopnc.b2b2c.android.common.ScreenUtil;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ght (c) 2007-2017 ShopNC Inc. All rights reserved.
 *
 * @author lzz
 *         Created 2017/6/7 14:10
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * @description 地址选择的弹窗，底部显示
 */
public class AddressDialog extends Dialog {

    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvProvince)
    TextView tvProvince;
    @Bind(R.id.tvCity)
    TextView tvCity;
    @Bind(R.id.tvArea)
    TextView tvArea;
    @Bind(R.id.listView)
    ListView listView;
    private AddressListAdapter adapter;
    //三级区域对应的列表
    private List<Area> provinces = new ArrayList<>();
    private List<Area> cities = new ArrayList<>();
    private List<Area> areas = new ArrayList<>();
    private Context context;

    private int flag;//当前显示位置：默认0-省，1-市，2-区。
    private Area[] positions = new Area[3];//记录已选择的省市区

    public static final String TAG = AddressDialog.class.getSimpleName();

    public AddressDialog(@NonNull Context context) {
        super(context, R.style.CommonDialog);
        this.context = context;
        adapter = new AddressListAdapter(context, provinces, R.layout.address_list_item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_select_dialog);
        ButterKnife.bind(this);
        int windowHeight = (int) (0.6 * ScreenUtil.getScreenSize(context).y);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, windowHeight);
        getWindow().setGravity(Gravity.BOTTOM);

        tvProvince.setSelected(true);
        tvCity.setVisibility(View.GONE);
        tvArea.setVisibility(View.GONE);

        //隐藏分割线
        listView.setDividerHeight(0);
        listView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Area area = adapter.getmDatas().get(position);
                String areaName = area.getAreaName();
                positions[flag] = area;
                adapter.setCurText(areaName);

                int childCount = listView.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View item = listView.getChildAt(i).findViewById(R.id.item);
                    TextView tvName = listView.getChildAt(i).findViewById(R.id.tvName);
                    tvName.setSelected(item == view);
                }

                //判断标记顺序2-1-0；避免修改标记后影响本次的判断
                if (flag == 2) {
                    //记录区
                    tvArea.setText(areaName);
                    onAreaInfoConfirmedListener.onAreaInfoConfirmed(
                            positions[0].getAreaId(), //省级id
                            positions[1].getAreaId(), //市级id
                            positions[2].getAreaId(), //区级id
                            //区域信息
                            positions[0].getAreaName()
                                    + "\t" + positions[1].getAreaName()
                                    + "\t" + positions[2].getAreaName());
                    dismiss();
                } else if (flag == 1) {
                    //记录市
                    tvCity.setText(areaName);
                    tvCity.setSelected(false);
                    tvArea.setVisibility(View.VISIBLE);
                    tvArea.setText("请选择");
                    tvArea.setSelected(true);
                    flag = 2;
                    //加载区级列表
                    loadData(area.getAreaId());
                } else if (flag == 0) {
                    //记录省
                    tvProvince.setText(areaName);
                    tvProvince.setSelected(false);
                    tvCity.setVisibility(View.VISIBLE);
                    tvCity.setText("请选择");
                    tvCity.setSelected(true);
                    tvArea.setVisibility(View.GONE);
                    flag = 1;
                    //加载市级列表
                    loadData(area.getAreaId());
                }

            }
        });
        //加载省级列表
        loadData(0);
    }

    /**
     * 请求地址列表
     *
     * @param areaId
     */
    private void loadData(int areaId) {
        String url = ConstantUrl.URL_API + "/area/list?areaId=" + areaId;

        OkHttpUtil.getAsyn(context, url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                String areaList = JsonUtil.toString(data, "areaList");
                List<Area> temp = JsonUtil.toBean(areaList, new TypeToken<ArrayList<Area>>() {
                }.getType());

                Log.d(TAG, "response: temp = " + temp);

                if (flag == 0) {
                    provinces.clear();
                    provinces.addAll(temp);
                    adapter.setmDatas(provinces);
                    adapter.notifyDataSetChanged();
                } else if (flag == 1) {
                    cities.clear();
                    cities.addAll(temp);
                    adapter.setmDatas(cities);
                    adapter.notifyDataSetChanged();
                } else if (flag == 2) {
                    areas.clear();
                    areas.addAll(temp);
                    adapter.setmDatas(areas);
                    adapter.notifyDataSetChanged();
                }

            }
        });
    }

    @Override
    public void dismiss() {
        super.dismiss();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tvProvince, R.id.tvCity, R.id.tvArea})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvProvince:
                flag = 0;
                adapter.setmDatas(provinces);
                setStatus(view);
                break;
            case R.id.tvCity:
                flag = 1;
                adapter.setmDatas(cities);
                setStatus(view);
                break;
            case R.id.tvArea:
                flag = 2;
                adapter.setmDatas(areas);
                setStatus(view);
                break;
        }
    }

    /**
     * 设置按钮选中状态
     *
     * @param view
     */
    private void setStatus(View view) {
        tvProvince.setSelected(false);
        tvCity.setSelected(false);
        tvArea.setSelected(false);
        view.setSelected(true);

        Area area = positions[flag];
        if (area != null) {
            adapter.setCurText(area.getAreaName());
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 设置弹窗的标题
     *
     * @param title
     */
    public void setTitle(String title) {
        tvTitle.setText(title);
    }


    public interface OnAreaInfoConfirmedListener {
        void onAreaInfoConfirmed(int id1, int id2, int id3, String info);
    }

    private OnAreaInfoConfirmedListener onAreaInfoConfirmedListener;

    public void setOnAreaInfoConfirmedListener(OnAreaInfoConfirmedListener onAreaInfoConfirmedListener) {
        this.onAreaInfoConfirmedListener = onAreaInfoConfirmedListener;
    }

    /**
     * ListView 数据适配器
     */
    private static class AddressListAdapter extends CommonAdapter<Area> {

        private String curText;

        void setCurText(String curText) {
            this.curText = curText;
        }

        AddressListAdapter(Context context, List<Area> datas, int layoutId) {
            super(context, datas, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, Area area) {
            TextView tvName = holder.getView(R.id.tvName);
            String areaName = area.getAreaName();
            tvName.setText(areaName);
            //当前选中的内容
            tvName.setSelected(areaName.equals(curText));
        }
    }
}
