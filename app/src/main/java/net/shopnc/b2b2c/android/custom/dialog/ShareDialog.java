package net.shopnc.b2b2c.android.custom.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.custom.ShareCodeDialog;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/8/30 14:03.
 */
public class ShareDialog extends Dialog {
    @Bind(R.id.tvCode)
    TextView mTvCode;
    @Bind(R.id.tvTitle)
    TextView mTvTitle;
    @Bind(R.id.llCopy)
    LinearLayout mLlCopy;
    private String title;
    private String text;
    private String targetUrl;
    private UMImage image;
    private UMShareListener umShareListener;
    private Context context;
    private String imageSrc;
    private String appPriceMin;
    private String shareUrl;

    public ShareDialog(Context context, String title, String text, String targetUrl, UMImage image, UMShareListener umShareListener) {
        super(context, R.style.CommonDialog);
        this.context = context;
        this.title = title;
        this.text = text;
        this.targetUrl = targetUrl;
        this.image = image;
        this.umShareListener = umShareListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_share_dialog);
        ButterKnife.bind(this);
        getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);
    }

    public void showShareCode(String imageSrc, String appPriceMin, String shareUrl) {
        mTvCode.setVisibility(View.VISIBLE);
        this.imageSrc = imageSrc;
        this.appPriceMin = appPriceMin;
        this.shareUrl = shareUrl;

        mTvTitle.setText("分享给小伙伴");
        mLlCopy.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.dismiss();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tvCode, R.id.tvCopyLink, R.id.tvZxing, R.id.tvWX, R.id.tvWXCircle, R.id.tvQQ, R.id.tvQQZone, R.id.tvWeibo, R.id.btnCancle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvCopyLink:
                ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(ConstantUrl.URL_WAP + targetUrl);
                TToast.showShort(context, "复制成功");
                break;
            case R.id.tvZxing:
                //TODO  生成二维码图片
                break;
            case R.id.tvWX:
                new ShareAction((Activity) context)
                        .setPlatform(SHARE_MEDIA.WEIXIN)
                        .setCallback(umShareListener)
                        .withTitle(title)
                        .withText(text)
                        .withTargetUrl(targetUrl)
                        .withMedia(image)
                        .share();
                break;
            case R.id.tvWXCircle:
                new ShareAction((Activity) context)
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setCallback(umShareListener)
                        .withTitle(title)
                        .withText(text)
                        .withTargetUrl(targetUrl)
                        .withMedia(image)
                        .share();
                break;
            case R.id.tvQQ:
                new ShareAction((Activity) context)
                        .setPlatform(SHARE_MEDIA.QQ)
                        .setCallback(umShareListener)
                        .withTitle(title)
                        .withText(text)
                        .withTargetUrl(targetUrl)
                        .withMedia(image)
                        .share();
                break;
            case R.id.tvQQZone:
                new ShareAction((Activity) context)
                        .setPlatform(SHARE_MEDIA.QZONE)
                        .setCallback(umShareListener)
                        .withTitle(title)
                        .withText(text)
                        .withTargetUrl(targetUrl)
                        .withMedia(image)
                        .share();
                break;
            case R.id.tvWeibo:
                new ShareAction((Activity) context)
                        .setPlatform(SHARE_MEDIA.SINA)
                        .setCallback(umShareListener)
                        .withTitle(title)
                        .withText(text)
                        .withTargetUrl(targetUrl)
                        .withMedia(image)
                        .share();
                break;
            case R.id.tvCode:
                geneCode();
                break;
            case R.id.btnCancle:
                dismiss();
                break;
        }
        dismiss();
    }

    public static final String TAG = "二维码";

    private void geneCode() {
        String url = ConstantUrl.URL_API + "/getshareqr";
        Map<String, String> map = new HashMap<>();
        map.put("imageUrl", imageSrc);
        map.put("text", text);
        map.put("price", appPriceMin);
        map.put("url", shareUrl);

        OkHttpUtil.postAsyn(context,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                LogHelper.d("share",data);
                String shareQRUrl = JsonUtil.toString(data, "shareQRUrl");
                ShareCodeDialog dialog = new ShareCodeDialog(context);
                dialog.show();
                dialog.display(shareQRUrl);
            }
        }, map);
    }
}
