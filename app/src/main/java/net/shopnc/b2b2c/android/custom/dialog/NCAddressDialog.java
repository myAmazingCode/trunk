package net.shopnc.b2b2c.android.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.Address;
import net.shopnc.b2b2c.android.bean.Area;
import net.shopnc.b2b2c.android.adapter.InvoiceAddSpinnerAdapter;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.interfac.INCOnAddressDialogConfirm;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.xrefresh.utils.LogUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 选择地址弹出窗口
 *
 * @author huting
 * @Time 2016/4/11
 */
public class NCAddressDialog extends Dialog {
    @Bind(R.id.spinner_shengID)
    Spinner spinnerShengID;
    @Bind(R.id.spinner_shiID)
    Spinner spinnerShiID;
    @Bind(R.id.llShi)
    LinearLayout llShi;
    @Bind(R.id.spinner_quID)
    Spinner spinnerQuID;
    @Bind(R.id.llQu)
    LinearLayout llQu;
//    @Bind(R.id.spinner_jieID)
//    Spinner spinnerJieID;
//    @Bind(R.id.llJie)
//    LinearLayout llJie;
    @Bind(R.id.btu_on)
    Button btuOn;
    @Bind(R.id.btu_off)
    Button btuOff;

    private Context context;
    private String cityId = "-1";
    private String areaId = "-1";
    private String[] addressINFO = new String[4];
    private Address address = new Address();

    INCOnAddressDialogConfirm incOnAddressDialogConfirm;

    public NCAddressDialog(Context context) {
        super(context, R.style.MyProgressDialog);
        this.setContentView(R.layout.my_address_dialog);
        this.context = context;
        ButterKnife.bind(this);

        spinnerShengID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                Area bean = (Area) spinnerShengID.getItemAtPosition(arg2);
                loadingGetCityData(spinnerShiID, String.valueOf(bean.getAreaId()), 1);
                addressINFO[0] = bean.getAreaName();
                address.setAreaId1(bean.getAreaId());
//                cityId = String.valueOf(bean.getAreaId());
//                areaId = String.valueOf(bean.getAreaId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        spinnerShiID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                Area bean = (Area) spinnerShiID.getItemAtPosition(arg2);
                loadingGetCityData(spinnerQuID, String.valueOf(bean.getAreaId()), 2);
                address.setAreaId2(bean.getAreaId());
//                cityId = String.valueOf(bean.getAreaId());
//                areaId = String.valueOf(bean.getAreaId());
                addressINFO[1] = bean.getAreaName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        spinnerQuID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                Area bean = (Area) spinnerQuID.getItemAtPosition(arg2);
//                areaId = String.valueOf(bean.getAreaId());
                addressINFO[2] = bean.getAreaName();
//                loadingGetCityData(spinnerJieID, String.valueOf(bean.getAreaId()), 3);
                address.setAreaId3(bean.getAreaId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
//        spinnerJieID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Area bean = (Area) spinnerJieID.getItemAtPosition(i);
////                areaId = String.valueOf(bean.getAreaId());
//                addressINFO[3] = bean.getAreaName();
//                address.setAreaId4(bean.getAreaId());
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
        btuOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        btuOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                //TODO 改动,增加把4级数字ID传给activity
                incOnAddressDialogConfirm.onAddressDialogConfirm(address.getAreaId1(), address.getAreaId2(), address.getAreaId3(), address.getAreaId4(), addressINFO[0] + "\t" + addressINFO[1] + "\t" + addressINFO[2]);
            }
        });
        loadingGetCityData(spinnerShengID, "0", 1);

    }

    /**
     * 获取地区列表
     */
    public void loadingGetCityData(final Spinner view, String area_id, final int level) {
        String url = ConstantUrl.URL_AREA_LIST + "?areaId=" + area_id;

        OkHttpUtil.getAsyn(context,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                String area_list = JsonUtil.toString(data, "areaList");
                List<Area> areas = JsonUtil.toBean(data, "areaList", new TypeToken<List<Area>>() {
                }.getType());

                if (area_list != null && !area_list.equals("[]")) {
                    showArea(level);
                } else {
                    hideArea(level);
                }

                InvoiceAddSpinnerAdapter addSpinnerAdapter = new InvoiceAddSpinnerAdapter(context);
                addSpinnerAdapter.setmDatas(areas);
                view.setAdapter(addSpinnerAdapter);
                addSpinnerAdapter.notifyDataSetChanged();
            }
        });
    }


    private void showArea(int level) {
        if (level == 1) {
            llShi.setVisibility(View.VISIBLE);
        }
        if (level == 2) {
            llQu.setVisibility(View.VISIBLE);
        }
//        if (level == 3) {
//            llJie.setVisibility(View.VISIBLE);
//        }
    }

    private void hideArea(int level) {
        if (level == 1) {
            llShi.setVisibility(View.GONE);
            llQu.setVisibility(View.GONE);
//            llJie.setVisibility(View.GONE);
        }
        if (level == 2) {
            llQu.setVisibility(View.GONE);
//            llJie.setVisibility(View.GONE);
        }
        if (level == 3) {
//            llJie.setVisibility(View.GONE);
        }
    }

    public void setOnAddressDialogConfirm(INCOnAddressDialogConfirm incOnAddressDialogConfirm) {
        this.incOnAddressDialogConfirm = incOnAddressDialogConfirm;
    }
}
