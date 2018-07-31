package net.shopnc.b2b2c.android.ui.mine;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.UserToKonw;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.Bind;

/**
 * 作者：Jie on 2016/6/20 17:50
 */
public class User2Konw extends BaseActivity {

    @Bind(R.id.user2konw_title)
    TextView user2konw_title;
    @Bind(R.id.user2konw_content)
    WebView user2konw_content;
    @Bind(R.id.user2konw_time)
    TextView user2konw_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCommonHeader("注册协议");
        getData();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.user_to_konw);
    }

    private void getData() {
        OkHttpUtil.getAsyn(this,ConstantUrl.URL_REGISTER_AGREEMENT, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                UserToKonw userToKonw = JsonUtil.toBean(data, new TypeToken<UserToKonw>() {
                }.getType());
                user2konw_title.setText(userToKonw.getTitle());
                user2konw_content.loadDataWithBaseURL(null, userToKonw.getContent(), "text/html", "utf-8", null);
                user2konw_time.setText(userToKonw.getCreateTime());
            }
        });
    }

    private String fmtString(String str) {
        String notice = "";
        try {
            notice = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException ex) {
        }
        return notice;
    }

}
