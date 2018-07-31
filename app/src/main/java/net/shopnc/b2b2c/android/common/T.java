package net.shopnc.b2b2c.android.common;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by wj on 2015/12/18.
 */
public class T {

        private final static boolean isShow = true;

        private T(){
            throw new UnsupportedOperationException("T cannot be instantiated");
        }
        private static Toast mToast;
        public static void showShort(Context context,CharSequence text) {
            if(isShow) {
                if(mToast == null){
                    mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                }else {
                    mToast.setText(text);
                }
                mToast.show();
            }
        }

        public static void showLong(Context context,CharSequence text) {
            if (isShow) {
                if(mToast == null){
                    mToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                }else {
                    mToast.setText(text);
                }
                mToast.show();
            }
        }
}
