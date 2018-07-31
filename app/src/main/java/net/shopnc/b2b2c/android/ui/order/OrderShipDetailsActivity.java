package net.shopnc.b2b2c.android.ui.order;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;

import net.shopnc.b2b2c.R;

public class OrderShipDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_order_ship_details);
    }

}
