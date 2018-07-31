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
 * Created by xws on 2017/4/26.
 */

public class GiftItemView extends LinearLayout {


    @Bind(R.id.tv)
    TextView mTv;

    public GiftItemView(Context context) {
        super(context);
        init(context);
    }

    public GiftItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GiftItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context) {
        View view = View.inflate(context, R.layout.gift_item_view, this);
        ButterKnife.bind(this, view);
    }

    public void setText(String s) {
        mTv.setText(s);
    }
}
