package net.shopnc.b2b2c.android.custom;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.InvoiceAddSpinnerAdapter;
import net.shopnc.b2b2c.android.bean.CityList;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.ncinterface.INCOnAddressDialogConfirm;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 选择地址弹出窗口
 *
 * @author dqw
 * @Time 2015/7/16
 */
public class NCAddressDialog extends Dialog {
    private Context context;
    public Button btu_on;
    public Button btu_off;
    public LinearLayout llShi;
    public LinearLayout llQu;
    public LinearLayout llJie;
    public Spinner spinner_shengID;
    public Spinner spinner_shiID;
    public Spinner spinner_quID;
    public Spinner spinner_jieID;
    private String cityId = "-1";
    private String areaId = "-1";
    private String ThreeareaId = "-1";
    private String[] addressINFO = new String[4];

    INCOnAddressDialogConfirm incOnAddressDialogConfirm;

    public NCAddressDialog(Context context) {
        super(context, R.style.MyProgressDialog);
        this.setContentView(R.layout.my_address_dialog);

        this.context = context;
        btu_on = findViewById(R.id.btu_on);
        btu_off = findViewById(R.id.btu_off);
        llShi = findViewById(R.id.llShi);
        llQu = findViewById(R.id.llQu);
        llJie = findViewById(R.id.llJie);
        spinner_shengID = findViewById(R.id.spinner_shengID);
        spinner_shiID = findViewById(R.id.spinner_shiID);
        spinner_quID = findViewById(R.id.spinner_quID);
        spinner_jieID = findViewById(R.id.spinner_jieID);
        spinner_shengID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                CityList bean = (CityList) spinner_shengID.getItemAtPosition(arg2);
                loadingGetCityData(spinner_shiID, bean.getArea_id(), 1);
                addressINFO[0] = bean.getArea_name();
                addressINFO[1] = "";
                addressINFO[2] = "";
                addressINFO[3] = "";
                cityId = bean.getArea_id();
                //areaId = bean.getArea_id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        spinner_shiID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                CityList bean = (CityList) spinner_shiID.getItemAtPosition(arg2);
                loadingGetCityData(spinner_quID, bean.getArea_id(), 2);
//                cityId = bean.getArea_id();
                areaId = bean.getArea_id();
                addressINFO[1] = bean.getArea_name();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        spinner_quID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                CityList bean = (CityList) spinner_quID.getItemAtPosition(arg2);
//                areaId = bean.getArea_id();
                ThreeareaId = bean.getArea_id();
                addressINFO[2] = bean.getArea_name();
                loadingGetCityData(spinner_jieID, bean.getArea_id(), 3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        spinner_jieID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CityList bean = (CityList) spinner_jieID.getItemAtPosition(i);
                //areaId = bean.getArea_id();
                addressINFO[3] = bean.getArea_name();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btu_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        btu_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                incOnAddressDialogConfirm.onAddressDialogConfirm(cityId, areaId,ThreeareaId, addressINFO[0] + " " + addressINFO[1] + " " + addressINFO[2] + " " + addressINFO[3]);
            }
        });
        loadingGetCityData(spinner_shengID, "0", 1);
    }

    /**
     * 初始化加载数据
     */
    public void loadingGetCityData(final Spinner view, String area_id, final int level) {
        String url = Constants.URL_GET_CITY + "&area_id=" + area_id;
        HashMap<String, String> params = new HashMap<String, String>();
        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == HttpStatus.SC_OK) {
                    String json = data.getJson();
                    try {
                        JSONObject objJSON = new JSONObject(json);
                        String area_list = objJSON.getString("area_list");
                        ArrayList<CityList> CList = new ArrayList<CityList>();
                        if (!area_list.equals("[]")) {
                            CList = CityList.newInstanceList(area_list);
                            showArea(level);
                        } else {
                            hideArea(level);
                            if (!area_list.equals("[]")) {
                                CList = CityList.newInstanceList(area_list);
                                showArea(level);
                            } else {
                                hideArea(level);
                                switch (level) {
                                    case 1:
                                        areaId = "-1";
                                        ThreeareaId = "-1";
                                        break;
                                    case 2:
                                        ThreeareaId = "-1";
                                        break;
                                    case 3:

                                        break;
                                    default:
                                        break;
                                }
                            }

                        }
                        InvoiceAddSpinnerAdapter addSpinnerAdapter = new InvoiceAddSpinnerAdapter(context);
                        addSpinnerAdapter.setDatas(CList);
                        view.setAdapter(addSpinnerAdapter);
                        addSpinnerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, R.string.load_error, Toast.LENGTH_SHORT).show();
                }
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
        if (level == 3) {
            llJie.setVisibility(View.VISIBLE);
        }
    }

    private void hideArea(int level) {
        if (level == 1) {
            llShi.setVisibility(View.GONE);
            llQu.setVisibility(View.GONE);
            llJie.setVisibility(View.GONE);
        }
        if (level == 2) {
            llQu.setVisibility(View.GONE);
            llJie.setVisibility(View.GONE);
        }
        if (level == 3) {
            llJie.setVisibility(View.GONE);
        }
    }

    public void setOnAddressDialogConfirm(INCOnAddressDialogConfirm incOnAddressDialogConfirm) {
        this.incOnAddressDialogConfirm = incOnAddressDialogConfirm;
    }
}
