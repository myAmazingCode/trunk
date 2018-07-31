package net.shopnc.b2b2c.android.ui.store;

import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.VoucherListAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.VoucherTemplateVo;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * @author wj
 *         Date 2016年05月09日 10:27
 */
public class VoucherListActivity extends BaseActivity {
    @Bind(R.id.listView)
    ListView listView;
    private String storeId;
    String title = "优惠券";
    private VoucherListAdapter adapter;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_vorcher_list);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader(title);
        setLayoutEmpty(R.drawable.empty_photo, "该店铺没有优惠券", "");
        storeId = getIntent().getStringExtra("storeId");
        initView();
        getTemplate();
    }

    private void initView() {
        adapter = new VoucherListAdapter(VoucherListActivity.this);
        listView.setAdapter(adapter);
    }

    private void getTemplate() {
        if (Common.isEmpty(application.getToken())) {
            OkHttpUtil.getAsyn(this, ConstantUrl.URL_VOUCHER_LIST + "?storeId=" + storeId, new BeanCallback<String>() {
                @Override
                public void response(String data) {
                    callback(data);
                }
            });
        } else {
            Map<String, String> p = new HashMap<>();
            p.put("token", application.getToken());
            p.put("storeId", storeId);
            OkHttpUtil.postAsyn(this, ConstantUrl.URL_USER_GOT_VOUCHER, new BeanCallback<String>() {
                @Override
                public void response(String data) {
                    callback(data);
                }
            }, p);
        }


    }

    private void callback(String data) {
        List<VoucherTemplateVo> list = JsonUtil.toBean(data, "voucherTemplateList", new TypeToken<List<VoucherTemplateVo>>() {
        }.getType());
        if (list != null && list.size() != 0) {
            hideLayoutEmpty();
            adapter.setmDatas(list);
            adapter.notifyDataSetChanged();
        } else {
            showLayoutEmpty();
        }
    }
}
