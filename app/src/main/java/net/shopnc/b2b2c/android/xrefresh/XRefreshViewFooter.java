package net.shopnc.b2b2c.android.xrefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.xrefresh.callback.IFooterCallBack;


public class XRefreshViewFooter extends LinearLayout implements IFooterCallBack {
    private Context mContext;

    private View mContentView;
    private View mProgressBar;
    private TextView mHintView;
    private TextView mClickView;

    public XRefreshViewFooter(Context context) {
        super(context);
        initView(context);
    }

    public XRefreshViewFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    @Override
    public void callWhenNotAutoLoadMore(final XRefreshView.XRefreshViewListener listener) {
        mClickView.setText(R.string.xrefreshview_footer_hint_click);
        mClickView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onLoadMore(false);
                    onStateRefreshing();
                }
            }
        });
    }

    @Override
    public void onStateReady() {
        mHintView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        mClickView.setVisibility(View.VISIBLE);
        setVisibility(VISIBLE);
    }

    @Override
    public void onStateRefreshing() {
        mHintView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        mClickView.setVisibility(View.GONE);
        setVisibility(VISIBLE);
    }

    @Override
    public void onStateFinish() {
        mHintView.setText(R.string.xrefreshview_footer_hint_normal);
        mHintView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        mClickView.setVisibility(View.GONE);
        setVisibility(VISIBLE);

    }

    @Override
    public void onStateComplete() {
        mHintView.setText(R.string.xrefreshview_footer_hint_complete);
        mHintView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        setVisibility(VISIBLE);
    }

    public void hide() {
//        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mContentView
//                .getLayoutParams();
//        lp.height = 0;
//        mContentView.setLayoutParams(lp);
        mContentView.setVisibility(GONE);
    }

    public void show() {
//        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mContentView
//                .getLayoutParams();
//        lp.height = LayoutParams.WRAP_CONTENT;
//        mContentView.setLayoutParams(lp);
        mContentView.setVisibility(VISIBLE);
    }

    private void initView(Context context) {
        setOrientation(HORIZONTAL);
        mContext = context;
        RelativeLayout moreView = (RelativeLayout) LayoutInflater
                .from(mContext).inflate(R.layout.xrefreshview_footer, null);
        addView(moreView);

        mContentView = moreView.findViewById(R.id.xrefreshview_footer_content);
        mProgressBar = moreView
                .findViewById(R.id.xrefreshview_footer_progressbar);
        mHintView = moreView
                .findViewById(R.id.xrefreshview_footer_hint_textview);
        mClickView = moreView
                .findViewById(R.id.xrefreshview_footer_click_textview);
        setVisibility(GONE);
    }

    @Override
    public int getFooterHeight() {
        return getMeasuredHeight();
    }
}
