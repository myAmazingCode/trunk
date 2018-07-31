package net.shopnc.b2b2c.android.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.shopnc.b2b2c.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ght (c) 2007-2017 ShopNC Inc. All rights reserved.
 *
 * @author lzz
 *         Created 2017/4/26 18:08
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 *
 * 详情页领券图标
 *
 */
public class VoucherTemplateView extends LinearLayout {
    @Bind(R.id.tvContent)
    TextView mTvContent;

    public VoucherTemplateView(Context context) {
        super(context);
        init(context);
    }

    public VoucherTemplateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public VoucherTemplateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = View.inflate(context, R.layout.voucher_template_view, this);
        ButterKnife.bind(this, view);
    }

    public void setText(String text){
        mTvContent.setText(text);
    }
}
