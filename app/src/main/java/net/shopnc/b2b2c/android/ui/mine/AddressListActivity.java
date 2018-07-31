package net.shopnc.b2b2c.android.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.AddressListAdapter;
import net.shopnc.b2b2c.android.adapter.AddressListSubmitAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.Address;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.custom.dialog.NCDialog;
import net.shopnc.b2b2c.android.custom.dialog.NCDialogConfirm;
import net.shopnc.b2b2c.android.interfac.INCOnDel;
import net.shopnc.b2b2c.android.interfac.INCOnEdit;
import net.shopnc.b2b2c.android.ui.buy.BuyStepBus;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.Constants;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;

/**
 * 地址列表
 *
 * @author huting
 * @date 2016/4/11
 */
public class AddressListActivity extends BaseActivity {

    @Bind(R.id.listViewID)
    ListView listViewID;
    @Bind(R.id.btnRight)
    TextView btnRight;
    @Bind(R.id.imgEmptyLogo)
    ImageView imgEmptyLogo;
    @Bind(R.id.layoutSearch)
    RelativeLayout layoutSearch;
    @Bind(R.id.tvEmptyTitle)
    TextView tvEmptyTitle;
    @Bind(R.id.tvEmptyBody)
    TextView tvEmptyBody;
    @Bind(R.id.btnChoose)
    Button btnChoose;
    @Bind(R.id.layoutEmpty)
    RelativeLayout layoutEmpty;
    @Bind(R.id.llAddressList)
    LinearLayout llAddressList;
    @Bind(R.id.listViewID1)
    ListView listViewID1;
    @Bind(R.id.rlAddressList)
    RelativeLayout rlAddressList;
//    @Bind(R.id.llAddressList) LinearLayout llAddressList;

    private INCOnDel incOnDel;
    private INCOnEdit incOnEdit;
    private NCDialog ncDialog;

    private String addressFlag;
    private AddressListAdapter adapter;
    private AddressListSubmitAdapter addressListSubmitAdapter;
    private List<Address> addressList = new ArrayList<>();
    private int addressId;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_address_list);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("收货地址管理");
        //设置购物车跳转回调 1-是提交订单跳转过去的 0-或者没有是个人中心
        addressFlag = getIntent().getStringExtra("addressFlag");
        addressId = getIntent().getIntExtra("addressId", -1);
        setLayoutEmpty(R.drawable.no_data_e, "亲，您还没有收货地址快添加一个吧~", "");
        initView();
    }


    /**
     * 初始化view
     */
    private void initView() {
        if (addressFlag.equals("1")) {
            llAddressList.setVisibility(View.GONE);
            rlAddressList.setVisibility(View.VISIBLE);
        } else {
            llAddressList.setVisibility(View.VISIBLE);
            rlAddressList.setVisibility(View.GONE);
        }

        incOnDel = new INCOnDel() {
            @Override
            public void onDel(String id) {
                detAddress(id);
            }
        };

        incOnEdit = new INCOnEdit() {
            @Override
            public void onEdit(String id) {//TODO
                Intent intent = new Intent(context, AddressADDActivity.class);
                intent.putExtra("address_id", id);
                startActivityForResult(intent, 3);
            }
        };

        adapter = new AddressListAdapter(this, incOnDel, incOnEdit);
        listViewID.setAdapter(adapter);
        addressListSubmitAdapter = new AddressListSubmitAdapter(this, incOnDel, incOnEdit, addressId);
        listViewID1.setAdapter(addressListSubmitAdapter);

        if (!Common.isEmpty(addressFlag) && !addressFlag.equals("0")) {
//            setCartCallBack();
//            adapter.setDeleteShowFlag(false);
            setSubmmitCartCallBack();
//            addressListSubmitAdapter.setDeleteShowFlag(false);
        }

        loadAddressList();
    }


    /**
     * 设置提交订单页跳转时的回调
     */
    public void setCartCallBack() {
        listViewID.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Address bean = (Address) listViewID.getItemAtPosition(arg2);
                if (bean != null) {
                    EventBus.getDefault().post(new BuyStepBus(BuyStepBus.ADDRESSID, bean.getAddressId()));
                    AddressListActivity.this.finish();
                }
            }
        });
    }

    /**
     * 设置提交订单页跳转时的回调
     */
    public void setSubmmitCartCallBack() {
        listViewID1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Address bean = (Address) listViewID1.getItemAtPosition(arg2);
                if (bean != null) {
                    EventBus.getDefault().post(new BuyStepBus(BuyStepBus.ADDRESSID, bean.getAddressId()));
                    AddressListActivity.this.finish();
                }
            }
        });
    }

    /**
     * 加载收货地址列表
     */
    private void loadAddressList() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", application.getToken());

        OkHttpUtil.postAsyn(this, ConstantUrl.URL_ADDRESS_LIST, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                addressList = JsonUtil.toBean(data, "addressList", new TypeToken<List<Address>>() {
                }.getType());

                if (addressList != null && addressList.size() > 0) {
//                    llNoResult.setVisibility(View.GONE);
                    hideLayoutEmpty();
//                    llAddressList.setVisibility(View.VISIBLE);
                    if (addressFlag.equals("1")) {
                        addressListSubmitAdapter.setmDatas(addressList);
                        addressListSubmitAdapter.notifyDataSetChanged();
                    } else {
                        adapter.setmDatas(addressList);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    showLayoutEmpty();
//                    llNoResult.setVisibility(View.VISIBLE);
//                    llAddressList.setVisibility(View.GONE);
                }
            }
        }, params);
    }


    /**
     * 删除收货地址
     *
     * @param addressId
     */
    private void detAddress(final String addressId) {
        ncDialog = new NCDialog(context);
        ncDialog.setText1("确认删除该地址?");
        ncDialog.setOnDialogConfirm(new NCDialogConfirm() {
            @Override
            public void onDialogConfirm() {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("addressId", addressId);
                params.put("token", application.getToken());

                OkHttpUtil.postAsyn(AddressListActivity.this, ConstantUrl.URL_ADDRESS_DELETE, new BeanCallback<String>() {
                    @Override
                    public void response(String data) {
                        TToast.showShort(context, JsonUtil.getSuccess(data));
                        loadAddressList();
                    }
                }, params);
            }
        });
        ncDialog.showPopupWindow();
    }


    /**
     * 添加地址
     */
    public void btnAddClick(View view) {
        Intent addIntent = new Intent(context, AddressADDActivity.class);
        addIntent.putExtra("address_id", "");
        startActivityForResult(addIntent, 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constants.ADD_ADDRESS_SUCC) {
            loadAddressList();
        }
    }

}
