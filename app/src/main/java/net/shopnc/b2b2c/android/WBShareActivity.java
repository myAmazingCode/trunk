package net.shopnc.b2b2c.android;

import android.os.Bundle;

import com.umeng.socialize.media.WBShareCallBackActivity;

/**
 * Created by wangfei on 15/12/3.
 */
public class WBShareActivity extends WBShareCallBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
        }catch (Exception e){
            finish();
        }
    }
}
