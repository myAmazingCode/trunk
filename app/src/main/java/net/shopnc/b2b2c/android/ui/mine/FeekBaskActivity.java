package net.shopnc.b2b2c.android.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler.Callback;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;

import java.util.HashMap;

/**
 * 意见反馈界面
 *
 * @author dqw
 * @Time 2015/8/17
 */
public class FeekBaskActivity extends BaseActivity {
    private EditText etFeed;
    private MyShopApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_feekbask);
        MyExceptionHandler.getInstance().setContext(this);
        myApplication = (MyShopApplication) getApplication();
        etFeed = (EditText) findViewById(R.id.etFeed);

        setCommonHeader("用户反馈");
    }

    /**
     * 发布反馈
     */
    public void btnFeedClick(View view) {
        String feedback_content = etFeed.getText().toString();
        if (feedback_content.equals("") || feedback_content == null) {
            Toast.makeText(FeekBaskActivity.this, "反馈内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        sendFeekBask(feedback_content);
    }

    private void sendFeekBask(String feedback_content) {
        String url = Constants.URL_FEEDBACK_ADD;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("feedback", feedback_content);
        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    if (json.equals("1")) {
                        Toast.makeText(FeekBaskActivity.this, "反馈成功", Toast.LENGTH_SHORT).show();
                        FeekBaskActivity.this.finish();
                    }

                } else {
                    ShopHelper.showApiError(FeekBaskActivity.this, json);
                }
            }
        });
    }
}
