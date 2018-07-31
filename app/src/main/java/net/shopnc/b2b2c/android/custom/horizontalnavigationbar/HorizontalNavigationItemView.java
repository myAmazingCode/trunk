package net.shopnc.b2b2c.android.custom.horizontalnavigationbar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.shopnc.b2b2c.R;

/**
 * 水平滚动栏的子Item
 * <p>
 *
 * @author liuyk
 */
public class HorizontalNavigationItemView extends LinearLayout implements Checkable {

    //分割线颜色
    protected int mSplitColor = Color.RED;

    private View mItemView;
    private View mChannelSplit;
    private TextView mChannelTitle;

    /**
     * 是否有下划线
     */
    private boolean isChannelSplit;

    /**
     * 是否选中
     */
    private boolean isChecked;

    public HorizontalNavigationItemView(Context context) {
        this(context, null);
    }

    public HorizontalNavigationItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        //attachToRoot(默认true)决定了，root是否 是resource的父对象
        this.mItemView = LayoutInflater.from(getContext()).inflate(R.layout.horizontal_bar_layout, this);
        mChannelTitle = this.mItemView.findViewById(R.id.horizontal_bar_channel_title);
        mChannelSplit = this.mItemView.findViewById(R.id.horizontal_bar_channel_split);
    }

    /**
     * 设置标题
     *
     * @param channelTitle 标题
     */
    public void setChannelTitle(String channelTitle) {
        mChannelTitle.setText(channelTitle);
    }

    public boolean isChannelSplit() {
        return isChannelSplit;
    }

    public void setChannelSplit(boolean channelSplit) {
        isChannelSplit = channelSplit;
    }

    @Override
    public void setChecked(boolean checked) {
        isChecked = checked;
        if (checked) {//是否被选中
            if (isChannelSplit) {//是否有下划线
                mChannelSplit.setVisibility(View.VISIBLE);
//                mChannelSplit.setBackgroundColor(mSplitColor);
                mChannelSplit.setBackgroundColor(getResources().getColor(R.color.nc_red));
            }
            mChannelTitle.setTextColor(Color.parseColor("#ED5968"));
        } else {
            mChannelTitle.setTextColor(Color.parseColor("#555555"));
            mChannelSplit.setVisibility(INVISIBLE);
        }
    }

    public void setSplitColor(int resId) {
        mSplitColor = resId;
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked);
    }
}
