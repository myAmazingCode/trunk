package net.shopnc.b2b2c.android.ui.buy;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.Invoice;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class InvoiceActivity extends BaseActivity {

    @Bind(R.id.rbNo)
    RadioButton rbNo;
    @Bind(R.id.rbNeed)
    RadioButton rbNeed;
    @Bind(R.id.etInvoiceTitle)
    EditText etInvoiceTitle;
    @Bind(R.id.tvInvoiceContent)
    TextView tvInvoiceContent;
    @Bind(R.id.rvInvoice)
    LinearLayout rvInvoice;

    private List<String> contentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("发票信息设置");
        String[] invoices = getIntent().getStringArrayExtra("invoice");

        if (invoices != null) {
            etInvoiceTitle.setText(invoices[0]);
            tvInvoiceContent.setText(invoices[1]);
            rbNeed.setChecked(true);
        }

        etInvoiceTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = etInvoiceTitle.getText().toString();
                if (TextUtils.isEmpty(text)){
                    rvInvoice.setVisibility(View.VISIBLE);
                    loadInvoiceData();
                }else {
                    rvInvoice.setVisibility(View.GONE);
                }
            }
        });
    }

    private void loadInvoiceData() {
        HashMap<String, String> params = new HashMap();
        params.put("token", application.getToken());
        OkHttpUtil.postAsyn(this,ConstantUrl.URL_INVOICE_LIST, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                Log.d(TAG, "response: data = "+data);
                List<Invoice> invoiceList = JsonUtil.toBean(data, "invoiceList", new TypeToken<ArrayList<Invoice>>() {
                }.getType());
                bindInvoiceList(invoiceList);
            }
        }, params);
    }

    private void bindInvoiceList(List<Invoice> invoiceList) {
        rvInvoice.removeAllViews();
        for (final Invoice invoice : invoiceList) {
            final AddViewHolder holder = new AddViewHolder(context, R.layout.invoice_item_view);
            holder.setText(R.id.tv, invoice.getTitle());
            holder.getCustomView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    etInvoiceTitle.setText(invoice.getTitle());
                }
            });
            rvInvoice.addView(holder.getCustomView());
        }

    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_invoice);
    }

    @OnClick({R.id.rbNeed, R.id.rbNo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rbNo:
                if (rbNo.isChecked()) {
                    EventBus.getDefault().post(new BuyStepBus(BuyStepBus.INVOICE, null));
                    finish();
                }
                break;
            case R.id.rbNeed:
                if (rbNeed.isChecked()) {
                    String title = etInvoiceTitle.getText().toString();
                    String content = tvInvoiceContent.getText().toString();
                    EventBus.getDefault().post(new BuyStepBus(BuyStepBus.INVOICE, new String[]{title, content}));
                    finish();
                }
                break;
        }
    }

    @OnClick({R.id.tvInvoiceContent,})
    public void onAddClick(View view) {
        switch (view.getId()) {
            case R.id.tvInvoiceContent:
                if (null == contentList) {
                    loadContentList();
                } else {
                    showDialog();
                }
                break;
        }
    }

    private void loadContentList() {
        HashMap params = new HashMap();
        params.put("token", application.getToken());

        OkHttpUtil.postAsyn(this,ConstantUrl.URL_INVOICE_CONTENT_LIST, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                contentList = JsonUtil.toBean(data, "contentList", new TypeToken<ArrayList<String>>() {
                }.getType());

                showDialog();
            }
        }, params);
    }


    private void showDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.invoice_list, null);
        final Dialog dialog = new Dialog(this, R.style.CommonDialog);
        dialog.setContentView(view);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, 1600);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        final ListView listView = view.findViewById(R.id.llChoosens);
        listView.setAdapter(new CommonAdapter<String>(context, contentList, R.layout.invoice_item_view) {
            @Override
            public void convert(ViewHolder holder, final String contentName) {
                final TextView tv = holder.getView(R.id.tv);
                tv.setText(contentName);
                tv.setSelected(false);

                if (tvInvoiceContent.getText().toString().equals(tv.getText().toString())) {
                    tv.setSelected(true);
                }

                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        tv.setSelected(true);
                        tvInvoiceContent.setText(contentName);
                        for (int i = 0; i < listView.getChildCount(); i++) {
                            TextView child = listView.getChildAt(i).findViewById(R.id.tv);
                            if (!contentName.equals(child.getText().toString())) {
                                child.setSelected(false);
                            }
                        }
                    }
                });
            }
        });

        dialog.show();
    }

    public static final String TAG = InvoiceActivity.class.getSimpleName();


}
