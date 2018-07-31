package net.shopnc.b2b2c.android;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import net.shopnc.b2b2c.android.adapter.BrandGridViewAdapter;
import net.shopnc.b2b2c.android.bean.BrandInfo;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class BrandActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
        setCommonHeader("推荐品牌");
        MyExceptionHandler.getInstance().setContext(this);
        RemoteDataHandler.asyncDataStringGet(Constants.URL_BRAND, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == HttpStatus.SC_OK) {
                    String json = data.getJson();
                    try {
                        JSONObject obj = new JSONObject(json);
                        String brandList = obj.getString("brand_list");
                        if (brandList != "" && brandList != null && !brandList.equals("[]")) {
                            ArrayList<BrandInfo> brandArray = BrandInfo.newInstanceList(brandList);
                            BrandGridViewAdapter brandGridViewAdapter = new BrandGridViewAdapter(BrandActivity.this);
                            brandGridViewAdapter.setBrandArray(brandArray);
                            GridView gvBrand = (GridView) findViewById(R.id.gvBrand);
                            gvBrand.setAdapter(brandGridViewAdapter);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(BrandActivity.this, R.string.load_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_brand, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
