package net.shopnc.b2b2c.android.ui.promotion;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.shopnc.b2b2c.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xws on 2017/2/8.
 */

public class PromotionDialog extends Dialog {


    @Bind(R.id.tvTitle)
    TextView mTvTitle;

    @Bind(R.id.tvBack)
    TextView tvBack;
    @Bind(R.id.tvApply)
    TextView mTvApply;
    private Context mContext;
    private boolean userFlag;

    public PromotionDialog(Context context) {
        super(context, R.style.promotionDialog);
        mContext = context;
    }

    public interface OnConfirmListener {
        void onConfirm();
    }

    public interface OnDialogCancelListener {
        void onCancel();
    }

    private OnConfirmListener mOnConfirmListener;
    private OnDialogCancelListener mOnCancelListener;

    public void setOnCancelListener(OnDialogCancelListener onCancelListener) {
        mOnCancelListener = onCancelListener;
    }

    public void setOnConfirmListener(OnConfirmListener onConfirmListener) {
        mOnConfirmListener = onConfirmListener;
    }

    public void setUserMessage(String title, String btnText) {

        mTvTitle.setText(title);
        mTvApply.setText(btnText);
        userFlag = true;
    }


    public void setNegativeMsg(String msg) {
        tvBack.setText(msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(mContext).inflate(R.layout.promotion_error_dialog, null);
        setContentView(view, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        setCanceledOnTouchOutside(false);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tvApply, R.id.tvBack})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvApply:

                if (mOnConfirmListener == null) {
                    Intent intent = new Intent(mContext, userFlag ? AuthenticationStep3Activity.class : AuthenticationStep1Activity.class);
                    intent.putExtra("userFlag", userFlag);
                    mContext.startActivity(intent);
                } else {
                    mOnConfirmListener.onConfirm();
                }

                dismiss();
                break;
            case R.id.tvBack:
                if (mOnCancelListener != null) {
                    mOnCancelListener.onCancel();
                }
                dismiss();
                break;
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.unbind(this);
    }

}
