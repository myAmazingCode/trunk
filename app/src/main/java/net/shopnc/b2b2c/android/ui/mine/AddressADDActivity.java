package net.shopnc.b2b2c.android.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.Address;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.custom.dialog.AddressDialog;
import net.shopnc.b2b2c.android.ui.buy.BuyStepBus;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.Constants;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.HashMap;

import butterknife.Bind;

/**
 * 增加收获地址
 *
 * @author huting
 * @date 2016/4/11
 */
public class AddressADDActivity extends BaseActivity {

    @Bind(R.id.editAddressName)
    EditText editAddressName;
    @Bind(R.id.editAddressMobPhone)
    EditText editAddressMobPhone;
    @Bind(R.id.editAddressInfo)
    TextView editAddressInfo;
    @Bind(R.id.editJieDaoAddress)
    EditText editJieDaoAddress;
    @Bind(R.id.btnDefaultAddress)
    ImageButton btnDefaultAddress;
    @Bind(R.id.btnAdd)
    Button btnAdd;

    private String addressId = "";
    private int area_id1 = 0;
    private int area_id2 = 0;
    private int area_id3 = 0;
    private int area_id4 = 0;
    private String area_id = "-1";
    private boolean isINEdit = true;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_address_add);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addressId = getIntent().getStringExtra("address_id");
        if (!Common.isEmpty(addressId)) {
            //编辑收货地址
            setCommonHeader("编辑收货地址");
            loadAddressDetail();
            isINEdit = true;
        } else {
            //新增收货地址
            setCommonHeader("新增收货地址");
            isINEdit = false;
        }
        setTextChange();


        initView();
    }


    /**
     * 初始化
     */
    private void initView() {

        //默认收货地址
        btnDefaultAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDefaultAddress.setSelected(!btnDefaultAddress.isSelected());
            }
        });

        //提交
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isINEdit) {
                    editAddress();
                } else {
                    saveAddress();
                }
            }
        });

        //地区选择对话框
        editAddressInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressDialog dialog = new AddressDialog(AddressADDActivity.this);
                dialog.show();
                dialog.setOnAreaInfoConfirmedListener(new AddressDialog.OnAreaInfoConfirmedListener() {

                    @Override
                    public void onAreaInfoConfirmed(int id1, int id2, int id3, String info) {
                        area_id1 = id1;
                        area_id2 = id2;
                        area_id3 = id3;
                        editAddressInfo.setText(info);
                    }
                });
                dialog.setTitle("所在地区");
            }
        });


    }

    /**
     * 设置输入框变动事件
     */
    private void setTextChange() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                setBtnAddState();
            }
        };
        editAddressInfo.addTextChangedListener(textWatcher);
        editAddressName.addTextChangedListener(textWatcher);
        editJieDaoAddress.addTextChangedListener(textWatcher);
        editAddressMobPhone.addTextChangedListener(textWatcher);
    }

    /**
     * 设置按钮提交按钮状态
     */
    private void setBtnAddState() {
        String true_name = editAddressName.getText().toString();//姓名
        String area_info = editAddressInfo.getText().toString();//地区信息，例：天津 天津市 红桥区
        String address = editJieDaoAddress.getText().toString();//地址信息，例：水游城8层
        String mob_phone = editAddressMobPhone.getText().toString();//手机
        if (true_name.equals("") || area_info.equals("") || address.equals("") || mob_phone.equals("")) {
            btnAdd.setSelected(false);
        } else {
            btnAdd.setSelected(true);
        }
    }

    /**
     * 保存收货地址
     */
    private void saveAddress() {
        if (editAddressInfo.getText().length() == 0) {
            TToast.showShort(context, "请选择地区");
            return;
        }

        if (Common.isEmpty(editJieDaoAddress.getText().toString())) {
            TToast.showShort(context, "详细地址不能为空");
            return;
        }
        if (Common.isEmpty(editAddressName.getText().toString())) {
            TToast.showShort(context, "收货人不能为空");
            return;
        }
        if (!Common.isMobileNO(editAddressMobPhone.getText().toString())) {
            TToast.showShort(context, "请正确填写联系手机");
            return;
        }


        if (!isINEdit) {
            addAddress();
        } else {
            editAddress();
        }
    }

    /**
     * 添加收货地址
     */
    private void addAddress() {
        HashMap<String, String> params = new HashMap<>();

        params.put("token", application.getToken());
        params.put("realName", editAddressName.getText().toString());

        params.put("areaId1", area_id1 + "");
        params.put("areaId2", area_id2 + "");
        params.put("areaId3", area_id3 + "");
        params.put("areaId", area_id3 + "");

        params.put("areaInfo", editAddressInfo.getText().toString());
        params.put("address", editJieDaoAddress.getText().toString());
        params.put("mobPhone", editAddressMobPhone.getText().toString());
        params.put("telPhone", "");
        String is_default = "0";
        if (btnDefaultAddress.isSelected()) {
            is_default = "1";
        }
        params.put("isDefault", is_default);
        OkHttpUtil.postAsyn(this, ConstantUrl.URL_ADDRESS_ADD, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                Intent mIntent = getIntent();
                setResult(Constants.ADD_ADDRESS_SUCC, mIntent);
                EventBus.getDefault().post(new BuyStepBus(BuyStepBus.ADDRESSID, JsonUtil.toInteger(data, "addressId")));
                finish();
            }
        }, params);

    }

    /**
     * 编辑收货地址
     */
    private void editAddress() {
        HashMap<String, String> params = new HashMap<>();

        params.put("token", application.getToken());
        params.put("addressId", addressId);

        params.put("realName", editAddressName.getText().toString());
        params.put("areaId1", area_id1 + "");
        params.put("areaId2", area_id2 + "");
        params.put("areaId3", area_id3 + "");
        params.put("areaId", area_id3 + "");

        params.put("areaInfo", editAddressInfo.getText().toString());
        params.put("address", editJieDaoAddress.getText().toString());
        params.put("mobPhone", editAddressMobPhone.getText().toString());
        params.put("telPhone", "");
        String is_default = "0";
        if (btnDefaultAddress.isSelected()) {
            is_default = "1";
        }
        params.put("isDefault", is_default);

        OkHttpUtil.postAsyn(this, ConstantUrl.URL_ADDRESS_EDIT, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                Intent mIntent = new Intent(context, AddressListActivity.class);
                TToast.showShort(context, JsonUtil.getSuccess(data));
                setResult(Constants.ADD_ADDRESS_SUCC, mIntent);
                finish();
            }
        }, params);
    }

    /**
     * 加载收货地址详细信息
     */
    public void loadAddressDetail() {
        HashMap<String, String> params = new HashMap<>();
        params.put("addressId", addressId);
        params.put("token", application.getToken());

        OkHttpUtil.postAsyn(this, ConstantUrl.URL_ADDRESS_INFO, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                Address addressDetails = JsonUtil.toBean(data, "address", new TypeToken<Address>() {
                }.getType());

                if (addressDetails != null) {
                    area_id = String.valueOf(addressDetails.getAreaId());
                    editAddressName.setText(addressDetails.getRealName());
                    editAddressInfo.setText(addressDetails.getAreaInfo());
                    editAddressMobPhone.setText(addressDetails.getMobPhone());
                    editJieDaoAddress.setText(addressDetails.getAddress() == null ? "" : addressDetails.getAddress());
                    area_id1 = addressDetails.getAreaId1();
                    area_id2 = addressDetails.getAreaId2();
                    area_id3 = addressDetails.getAreaId3();
                    area_id4 = addressDetails.getAreaId4();

                    if (addressDetails.getIsDefault() == 1) {
                        btnDefaultAddress.setSelected(true);
                    } else {
                        btnDefaultAddress.setSelected(false);
                    }
                }
            }
        }, params);
    }

}
