package net.shopnc.b2b2c.android.ui.type;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.StoreO2oAddressListViewAdapter;
import net.shopnc.b2b2c.android.bean.GpsInfo;
import net.shopnc.b2b2c.android.bean.StoreO2oAddressInfo;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;

import java.util.ArrayList;

public class StoreO2oAddressActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_o2o_address);
        MyExceptionHandler.getInstance().setContext(this);
        setCommonHeader("商家信息");

        TextView tvAllStore = (TextView) findViewById(R.id.tvAllStore);
        ListView lvStoreList = (ListView) findViewById(R.id.lvStoreList);

        String addressString = getIntent().getStringExtra("address");
        final ArrayList<StoreO2oAddressInfo> storeO2oAddressList = StoreO2oAddressInfo.newInstanceList(addressString);

        tvAllStore.setText("全部实体分店共" + String.valueOf(storeO2oAddressList.size()) + "家");
        tvAllStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoreO2oAddressActivity.this, BaiduMapActivity.class);
                ArrayList<GpsInfo> gpsList = new ArrayList<GpsInfo>();
                for (int i = 0; i < storeO2oAddressList.size(); i++) {
                    StoreO2oAddressInfo bean = storeO2oAddressList.get(i);
                    double lng = Double.valueOf(bean.getLng());
                    double lat = Double.valueOf(bean.getLat());
                    gpsList.add(new GpsInfo(lng, lat));
                }
                intent.putParcelableArrayListExtra("gps_list", gpsList);
                startActivity(intent);
            }
        });

        StoreO2oAddressListViewAdapter adapter = new StoreO2oAddressListViewAdapter(StoreO2oAddressActivity.this);
        adapter.setlist(storeO2oAddressList);
        lvStoreList.setAdapter(adapter);
    }

}
