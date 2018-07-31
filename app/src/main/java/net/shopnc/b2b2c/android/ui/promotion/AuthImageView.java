package net.shopnc.b2b2c.android.ui.promotion;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.util.LoadImage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xws on 2017/2/9.
 */

public class AuthImageView extends LinearLayout {

    @Bind(R.id.tvTitle1)
    TextView mTvTitle1;
    @Bind(R.id.tvTitle2)
    TextView mTvTitle2;
    @Bind(R.id.tvDesc1)
    TextView mTvDesc1;
    @Bind(R.id.ivImage)
    ImageView mIvImage;
    @Bind(R.id.tvDesc2)
    TextView mTvDesc2;
    @Bind(R.id.ivIMG)
    ImageView mIvIMG;
    @Bind(R.id.llContainer)
    LinearLayout mLlContainer;
    private Context mContext;
    private OnImageUploadListener mOnImageUploadListener;
    private int mFlag;
    public static final int FLAG_FRONT = 1;
    public static final int FLAG_BACK = 2;
    public static final int FLAG_HAND = 3;

    public void setTitle(String title1, String title2) {
        mTvTitle1.setText(title1);
        mTvTitle2.setText(title2);
    }

    public void setDesc(String title1, String title2) {
        mTvDesc1.setText(title1);
        mTvDesc2.setText(title2);
    }

    public void setImageIndicator(int resId) {
//        mIvImage.setBackgroundResource(resId);
        mIvImage.setBackground(mContext.getResources().getDrawable(resId));
//        mIvImage.setImageDrawable();
    }

    public void setImageRes(String url) {
        LoadImage.loadRemoteImg(mContext, mIvIMG, url);
        mLlContainer.setVisibility(GONE);
    }

    @OnClick({R.id.llUploadImg})
    public void upload() {
//        TToast.makeText(mContext, "上传", TToast.LENGTH_SHORT).show();
        if (mOnImageUploadListener != null) {
            mOnImageUploadListener.onImageUpload(mFlag);
        }
    }

    public void setOnImageUploadListener(OnImageUploadListener onImageUploadListener, int flag) {
        mOnImageUploadListener = onImageUploadListener;
        mFlag = flag;
    }


    public interface OnImageUploadListener {
        void onImageUpload(int flag);
    }

    public AuthImageView(Context context) {
        super(context);
        init(context);
    }

    public AuthImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AuthImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        View view = View.inflate(context, R.layout.promotion_add_image_view, this);
        ButterKnife.bind(this, view);

    }
}
