package net.shopnc.b2b2c.android;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.StringUtils;

/**
 * 软件启动界面
 *
 * @author KingKong-HE
 * @Time 2014-12-30
 * @Email KingKong@QQ.COM
 */
public class WelcomeActivity extends Activity {

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_view);
        MyExceptionHandler.getInstance().setContext(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        onLoad();
        if (i >= 2) {
            finish();
        }
    }

    private void onLoad() {
        Intent intent = getIntent();
        String scheme = intent.getScheme();
        Uri uri = intent.getData();
        System.out.println("scheme:" + scheme);
        if (uri != null) {
            String host = uri.getHost();
            String dataString = intent.getDataString();
            String id = uri.getQueryParameter("id");
            String url = uri.getQueryParameter("url");
            String path = uri.getPath();
            String path1 = uri.getEncodedPath();
            String queryString = uri.getQuery();
            System.out.println("host:" + host);
            System.out.println("dataString:" + dataString);
            System.out.println("id:" + id);
            System.out.println("url:" + url);
            System.out.println("path:" + path);
            System.out.println("path1:" + path1);
            System.out.println("queryString:" + queryString);
            if (!StringUtils.isEmpty(id)) {

            } else {
                Welcome();
            }
        } else {
            Welcome();
        }
    }

    private void Welcome() {
        //加入定时器 睡眠 2000毫秒 自动跳转页面
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent it = new Intent();
//				it.setClass(WelcomeActivity.this,StartActivity.class);
                it.setClass(WelcomeActivity.this, MainFragmentManager.class);
                startActivity(it);
                WelcomeActivity.this.finish();
            }
        }, 1000);
    }
}
