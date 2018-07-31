package net.shopnc.b2b2c.android.ui.store;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import net.shopnc.b2b2c.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StoreIndexLinkedActivity extends AppCompatActivity {


    @Bind(R.id.wvShow)
    WebView wvShow;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_store_index_linked);
        ButterKnife.bind(this);
        url=getIntent().getStringExtra("url");
        wvShow.loadUrl(url);
        wvShow.getSettings().setJavaScriptEnabled(true);
        wvShow.getSettings().setLoadWithOverviewMode(true);
        wvShow.getSettings().setUseWideViewPort(true);
        wvShow.setWebChromeClient(new WebChromeClient());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wvShow.removeAllViews();
        wvShow.destroy();
        wvShow = null;
        ButterKnife.unbind(this);
    }
}
