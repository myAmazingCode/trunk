package net.shopnc.b2b2c.android.ui.type;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.InvoiceAddSpinnerAdapter;
import net.shopnc.b2b2c.android.adapter.InvoiceListViewAdapter;
import net.shopnc.b2b2c.android.bean.CityList;
import net.shopnc.b2b2c.android.bean.InvoiceList;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.custom.MyAddInvoice;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler.Callback;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 发票列表
 *
 * @author KingKong-HE
 * @Time 2015-1-20
 * @Email KingKong@QQ.COM
 */
public class InvoiceListActivity extends BaseActivity {

    private MyShopApplication myApplication;

    private InvoiceListViewAdapter aListViewAdapter;

    private InvoiceAddSpinnerAdapter invoiceAddSpinnerAdapter;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invoice_list_view);
        MyExceptionHandler.getInstance().setContext(this);
        setCommonHeader("发票管理");

        myApplication = (MyShopApplication) getApplicationContext();

        initViewID();//初始化控件ID

    }

    /**
     * 初始化控件ID
     */
    public void initViewID() {

        listView = (ListView) findViewById(R.id.listViewID);

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final MyAddInvoice addInvoiceDialog = new MyAddInvoice(InvoiceListActivity.this);

                getInvoiceContextData(addInvoiceDialog.add_invoice_spinner_context);//发票内容列表请求地址

                addInvoiceDialog.show();

                addInvoiceDialog.btu_on.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String inv_title = addInvoiceDialog.add_invoice_edit_danwei_name.getText().toString();

                        //新增发票
                        loadingSaveInvoiceData(addInvoiceDialog, addInvoiceDialog.inv_title_select, inv_title, addInvoiceDialog.inv_content);
                    }
                });

                addInvoiceDialog.btu_off.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addInvoiceDialog.dismiss();
                    }
                });

            }
        });

        aListViewAdapter = new InvoiceListViewAdapter(InvoiceListActivity.this);

        invoiceAddSpinnerAdapter = new InvoiceAddSpinnerAdapter(InvoiceListActivity.this);

        listView.setAdapter(aListViewAdapter);

        loadingInvoiceListData();//初始化加载发票列表数据

        //listview添加点击事件
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                InvoiceList bean = (InvoiceList) listView.getItemAtPosition(arg2);

                if (bean != null) {
                    Intent intent = new Intent(InvoiceListActivity.this, BuyStep1Activity.class);
                    intent.putExtra("inv_id", bean.getInv_id());
                    intent.putExtra("inv_context", bean.getInv_title() + bean.getInv_content());
                    setResult(Constants.SELECT_INVOICE, intent);
                    finish();
                }
            }
        });

        //listview添加长按监听事件
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int arg2, long arg3) {

                final InvoiceList bean = (InvoiceList) listView.getItemAtPosition(arg2);

                if (bean != null && !bean.getInv_id().equals("-1")) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(InvoiceListActivity.this);
                    builder.setTitle("功能选择")
                            .setMessage("您确定移除？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton) {

                            invoiceDelete(bean.getInv_id());//删除发票

                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).create().show();

                }

                return true;
            }
        });

    }

    /**
     * 初始化加载发票列表数据
     */
    public void loadingInvoiceListData() {
        String url = Constants.URL_INVOICE_LIST;
        //index.php?act=member_buy&op=test
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == HttpStatus.SC_OK) {
                    String json = data.getJson();
                    try {
                        JSONObject obj = new JSONObject(json);
                        String invoice_list = obj.getString("invoice_list");
                        ArrayList<InvoiceList> list = InvoiceList.newInstanceList(invoice_list);
                        aListViewAdapter.setInvoiceList(list);
                        aListViewAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(InvoiceListActivity.this, R.string.load_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 发票内容列表请求地址
     */
    public void getInvoiceContextData(final Spinner spinner) {
        String url = Constants.URL_INVOICE_CONTEXT_LIST;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == HttpStatus.SC_OK) {
                    String json = data.getJson();
                    try {
                        JSONObject objJSON = new JSONObject(json);
                        String invoice_list = objJSON.getString("invoice_content_list");
                        JSONArray arr = new JSONArray(invoice_list);
                        int size = null == arr ? 0 : arr.length();
                        ArrayList<CityList> lists = new ArrayList<CityList>();
                        for (int i = 0; i < size; i++) {
                            lists.add(new CityList("-1", arr.get(i).toString()));
                        }
                        invoiceAddSpinnerAdapter.setDatas(lists);
                        spinner.setAdapter(invoiceAddSpinnerAdapter);
                        invoiceAddSpinnerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(InvoiceListActivity.this, R.string.load_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 删除发票
     *
     * @param inv_id 发票编号
     */
    public void invoiceDelete(String inv_id) {
        String url = Constants.URL_INVOICE_DELETE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("inv_id", inv_id);

        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {

                    if (json.equals("1")) {
                        Toast.makeText(InvoiceListActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        loadingInvoiceListData();//刷新列表
                    }
                } else {
                    try {
                        JSONObject obj = new JSONObject(json);
                        String error = obj.getString("error");
                        if (error != null) {
                            Toast.makeText(InvoiceListActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 新增发票
     *
     * @param inv_title_select 发票类型，可选值：person(个人) company(单位)
     * @param inv_title        发票抬头(inv_title_select为company时需要提交)
     * @param inv_content      发票内容，可通过invoice_content_list接口获取可选值列表
     */
    public void loadingSaveInvoiceData(final MyAddInvoice addInvoiceDialog, String inv_title_select, String inv_title, String inv_content) {
        String url = Constants.URL_INVOICE_ADD;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("inv_title_select", inv_title_select);
        if (inv_title_select.equals("company")) {
            params.put("inv_title", inv_title);
        }
        params.put("inv_content", inv_content);
        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                addInvoiceDialog.dismiss();
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {

                    loadingInvoiceListData();//刷新列表

                    loadingInvoiceListData();
                } else {
                    try {
                        JSONObject obj = new JSONObject(json);
                        String error = obj.getString("error");
                        if (error != null) {
                            Toast.makeText(InvoiceListActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


}
