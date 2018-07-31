package net.shopnc.b2b2c.android.custom;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.shopnc.b2b2c.android.R;

/**
 * Created by qyf on 2016/3/3.
 */
public class TToast {

    private static Toast mToast;
    private static Toast bToast;
    private static ImageView imgToast;
    private static TextView tvToast;

    public static void showShort(Context context, CharSequence text) {
        if (mToast == null) {
            createToast(context, Toast.LENGTH_SHORT);
            imgToast.setVisibility(View.GONE);
            tvToast.setText(text);
        } else {
            imgToast.setVisibility(View.GONE);
            tvToast.setText(text);
        }
        mToast.show();
    }

    public static void showShort(Context context, int textId) {
        String text = context.getResources().getString(textId);
        if (mToast == null) {
            createToast(context, Toast.LENGTH_SHORT);
            imgToast.setVisibility(View.GONE);
            tvToast.setText(text);
        } else {
            imgToast.setVisibility(View.GONE);
            tvToast.setText(text);
        }
        mToast.show();
    }

    public static void showLong(Context context, CharSequence text) {
        if (mToast == null) {
            createToast(context, Toast.LENGTH_LONG);
            imgToast.setVisibility(View.GONE);
            tvToast.setText(text);
        } else {
            imgToast.setVisibility(View.GONE);
            tvToast.setText(text);
        }
        mToast.show();
    }


    public static void showLong(Context context, int textId) {
        String text = context.getResources().getString(textId);
        if (mToast == null) {
            createToast(context, Toast.LENGTH_LONG);
            imgToast.setVisibility(View.GONE);
            tvToast.setText(text);
        } else {
            imgToast.setVisibility(View.GONE);
            tvToast.setText(text);
        }
        mToast.show();
    }

    public static void cancleToast() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }

    public static void showImgShort(Context context, int imgResId, CharSequence text) {
        if (mToast == null) {
            createToast(context, Toast.LENGTH_SHORT);
            imgToast.setImageResource(imgResId);
            tvToast.setText(text);
        } else {
            imgToast.setVisibility(View.VISIBLE);
            imgToast.setImageResource(imgResId);
            tvToast.setText(text);
        }
        mToast.show();
    }

    public static void showImgShort(Context context, int imgResId, int textId) {
        String text = context.getResources().getString(textId);
        if (mToast == null) {
            createToast(context, Toast.LENGTH_SHORT);
            imgToast.setImageResource(imgResId);
            tvToast.setText(text);
        } else {
            imgToast.setVisibility(View.VISIBLE);
            imgToast.setImageResource(imgResId);
            tvToast.setText(text);
        }
        mToast.show();
    }


    public static void showImgLong(Context context, int imgResId, CharSequence text) {
        if (mToast == null) {
            createToast(context, Toast.LENGTH_LONG);
            imgToast.setImageResource(imgResId);
            tvToast.setText(text);
        } else {
            imgToast.setVisibility(View.VISIBLE);
            imgToast.setImageResource(imgResId);
            tvToast.setText(text);
        }
        mToast.show();
    }

    public static void showImgLong(Context context, int imgResId, int textId) {
        String text = context.getResources().getString(textId);
        if (mToast == null) {
            createToast(context, Toast.LENGTH_LONG);
            imgToast.setImageResource(imgResId);
            tvToast.setText(text);
        } else {
            imgToast.setVisibility(View.VISIBLE);
            imgToast.setImageResource(imgResId);
            tvToast.setText(text);
        }
        mToast.show();
    }


    private static void createToast(Context context, int duration) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.toast_img_show, null);
        imgToast = view.findViewById(R.id.imgToast);
        tvToast = view.findViewById(R.id.tvToast);
        mToast = new Toast(context);
        mToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
        mToast.setDuration(duration);
        mToast.setView(view);
    }

    public static void showBlackShort(Context context, CharSequence text) {
        if (bToast == null) {
            createToast(context);
            tvToast.setText(text);
        } else {
            tvToast.setText(text);
        }
        bToast.show();
    }


    private static void createToast(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.toast_black_show, null);
        tvToast = view.findViewById(R.id.tvToast);
        bToast = new Toast(context);
        bToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 100);
        bToast.setDuration(Toast.LENGTH_SHORT);
        bToast.setView(view);
    }
}
