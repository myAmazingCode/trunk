package net.shopnc.b2b2c.android.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;

/**
 * 空列表背景
 *
 * dqw
 * 2015/8/25
 */
public class MyListEmpty extends FrameLayout{
    private ImageView ivListEmpty;
    private TextView tvListEmptyTitle;
    private TextView tvListEmptySubTitle;
    public MyListEmpty(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.my_list_empty, this);
        ivListEmpty = findViewById(R.id.ivListEmpty);
        tvListEmptyTitle = findViewById(R.id.tvListEmptyTitle);
        tvListEmptySubTitle = findViewById(R.id.tvListEmptySubTitle);
    }

    public void setListEmpty(int resId, String title, String subTitle) {
        ivListEmpty.setImageResource(resId);
        tvListEmptyTitle.setText(title);
        tvListEmptySubTitle.setText(subTitle);
    }
}
