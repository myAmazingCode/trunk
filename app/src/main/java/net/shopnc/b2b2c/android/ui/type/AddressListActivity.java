package net.shopnc.b2b2c.android.ui.type;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.AddressListViewAdapter;
import net.shopnc.b2b2c.android.bean.AddressList;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.custom.MyListView;
import net.shopnc.b2b2c.android.custom.NCDialog;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler.Callback;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ncinterface.INCOnDel;
import net.shopnc.b2b2c.android.ncinterface.INCOnDialogConfirm;
import net.shopnc.b2b2c.android.ncinterface.INCOnEdit;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 收货地址列表
 *
 * @author dqw
 * @Time 2015-7-16
 */
public class AddressListActivity extends BaseActivity {

    private MyShopApplication myApplication;
    private AddressListViewAdapter adapter;
    private MyListView listViewID;
    private LinearLayout llNoResult;
    private LinearLayout llAddressList;
    private INCOnDel incOnDel;
    private INCOnEdit incOnEdit;
    private NCDialog ncDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_list_view);
        MyExceptionHandler.getInstance().setContext(this);
        myApplication = (MyShopApplication) getApplicationContext();

        llNoResult = (LinearLayout) findViewById(R.id.llNoResult);
        llAddressList = (LinearLayout) findViewById(R.id.llAddressList);

        incOnDel = new INCOnDel() {
            @Override
            public void onDel(String id) {
                detAddress(id);
            }
        };

        incOnEdit = new INCOnEdit() {
            @Override
            public void onEdit(String id) {
                Intent intent = new Intent(AddressListActivity.this, AddressADDActivity.class);
                intent.putExtra("address_id", id);
                startActivityForResult(intent, 3);
            }
        };

        listViewID = (MyListView) findViewById(R.id.listViewID);
        adapter = new AddressListViewAdapter(AddressListActivity.this, incOnDel, incOnEdit);
        listViewID.setAdapter(adapter);
        loadAddressList();

        //设置购物车跳转回调 1-是提交订单跳转过去的 0-或者没有是个人中心
        String addressFlag = getIntent().getStringExtra("addressFlag");
        if (addressFlag != null && !addressFlag.equals("") && !addressFlag.equals("0")) {
            setCartCallBack();
        }

        setCommonHeader("地址管理");
    }

    /**
     * 设置购物车跳转时的回调
     */
    public void setCartCallBack() {
        listViewID.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                AddressList bean = (AddressList) listViewID.getItemAtPosition(arg2);
                if (bean != null) {
                    Intent mIntent = new Intent(AddressListActivity.this, BuyStep1Activity.class);
                    mIntent.putExtra("address_id", bean.getAddress_id());
                    mIntent.putExtra("city_id", bean.getCity_id() == null ? "" : bean.getCity_id());
                    mIntent.putExtra("area_id", bean.getArea_id() == null ? "" : bean.getArea_id());
                    mIntent.putExtra("addressInFo", bean.getArea_info() == null ? "" : bean.getArea_info());
                    mIntent.putExtra("address", bean.getAddress() == null ? "" : bean.getAddress());
                    mIntent.putExtra("tureName", bean.getTrue_name() == null ? "" : bean.getTrue_name());
                    mIntent.putExtra("mobPhone", bean.getMob_phone() == null ? "" : bean.getMob_phone());
                    setResult(Constants.SELECT_ADDRESS, mIntent);
                    AddressListActivity.this.finish();
                }
            }
        });
    }

    /**
     * 添加地址
     */
    public void btnAddClick(View view) {
        Intent addIntent = new Intent(AddressListActivity.this, AddressADDActivity.class);
        startActivityForResult(addIntent, 3);
    }

    /**
     * 加载收货地址列表
     */
    public void loadAddressList() {
        String url = Constants.URL_ADDRESS_LIST;
        HashMap<String, String> params = new HashMap<>();
        params.put("key", myApplication.getLoginKey());
        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        String addressListString = obj.getString("address_list");
                        if (addressListString != null && !addressListString.equals("[]")) {
                            llNoResult.setVisibility(View.GONE);
                            llAddressList.setVisibility(View.VISIBLE);
                            ArrayList<AddressList> addressList = AddressList.newInstanceList(addressListString);
                            adapter.setAddressLists(addressList);
                            adapter.notifyDataSetChanged();
                        } else {
                            llNoResult.setVisibility(View.VISIBLE);
                            llAddressList.setVisibility(View.GONE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ShopHelper.showApiError(AddressListActivity.this, json);
                }
            }
        });
    }

    /**
     * 删除收货地址
     *
     * @param addressId
     */
    public void detAddress(final String addressId) {
        ncDialog = new NCDialog(AddressListActivity.this);
        ncDialog.setText1("确认删除该地址?");
        ncDialog.setOnDialogConfirm(new INCOnDialogConfirm() {
            @Override
            public void onDialogConfirm() {
                String url = Constants.URL_ADDRESS_DETELE;
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("key", myApplication.getLoginKey());
                params.put("address_id", addressId);
                RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new Callback() {
                    @Override
                    public void dataLoaded(ResponseData data) {
                        String json = data.getJson();
                        if (data.getCode() == HttpStatus.SC_OK) {
                            if (json.equals("1")) {
                                Toast.makeText(AddressListActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                                loadAddressList();
                            }
                        } else {
                            ShopHelper.showApiError(AddressListActivity.this, json);
                        }
                    }
                });
            }
        });
        ncDialog.showPopupWindow();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constants.ADD_ADDRESS_SUCC) {
            loadAddressList();
        }
    }
}

