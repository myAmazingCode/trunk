package net.shopnc.b2b2c.android.custom;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.common.ScreenUtil;
import net.shopnc.b2b2c.android.util.LoadImage;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ght (c) 2007-2017 ShopNC Inc. All rights reserved.
 *
 * @author lzz
 *         Created 2017/5/27 11:13
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * @description
 */
public class ShareCodeDialog extends Dialog {
    private Context mContext;

    @Bind(R.id.iv)
    ImageView iv;

    public ShareCodeDialog(@NonNull Context context) {
        super(context, R.style.promotionDialog);
        mContext = context;
    }

    /*
    显示图片
     */
    public void display(String url) {
        Point size = ScreenUtil.getScreenSize(mContext);
        int width = (int) (size.x * 0.8);
        int height = (int) (size.y * 0.8);
        iv.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        LoadImage.loadRemoteImg(mContext, iv, url);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(mContext).inflate(R.layout.promotion_share_code, null);
        setContentView(view, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        setCanceledOnTouchOutside(true);
        ButterKnife.bind(this);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.unbind(this);
    }
}
