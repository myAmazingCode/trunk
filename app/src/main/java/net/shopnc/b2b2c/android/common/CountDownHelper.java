package net.shopnc.b2b2c.android.common;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xws on 2017/3/17.
 * 限时折扣等的计时器
 */

public class CountDownHelper {

    private static final long DAY_IN_SEC = 24 * 60 * 60;
    private static final long HOUR_IN_SEC = 60 * 60;
    public static final String TAG = "CountDownHelper";
    private static Handler mHandler = new Handler();

    public static void exit() {
        mHandler.removeCallbacksAndMessages(null);
    }

    /**
     * @param tv1  天
     * @param tv2  小时
     * @param tv3  分
     * @param tv4  秒
     * @param time 剩余时间
     */
    public static void initCountDown(final TextView tv1, final TextView tv2, final TextView tv3, final TextView tv4, final long time) {


        Runnable r = new Runnable() {

            private long pst;

            @Override
            public void run() {
                String remainTime = getRemainTime(time - pst);
//                Log.d(TAG, "showPromotion: remainTime = " + remainTime);
                //长度一定为4
                String[] timeStr = remainTime.split("\\,");
                pst += 1;
                //显示天
                if (!"-1".equals(timeStr[0])) {
                    tv1.setVisibility(View.VISIBLE);
                    tv1.setText(timeStr[0] + "天");
                } else {
                    tv1.setVisibility(View.GONE);
                }

                //显示小时
                if (!"-1".equals(timeStr[1])) {
                    tv2.setVisibility(View.VISIBLE);
                    tv2.setText(timeStr[1]);
                } else {
                    tv2.setVisibility(View.GONE);
                }

                //显示分
                if (!"-1".equals(timeStr[2])) {
                    tv3.setVisibility(View.VISIBLE);
                    tv3.setText(timeStr[2]);
                } else {
                    tv3.setVisibility(View.GONE);
                }

                //显示秒
                if (!"-1".equals(timeStr[3])) {
                    tv4.setVisibility(View.VISIBLE);
                    tv4.setText(timeStr[3]);
                } else {
                    tv4.setVisibility(View.GONE);
                }
                mHandler.postDelayed(this, 1000);
            }
        };

        mHandler.post(r);
    }


    // 倒计时 天-时-分-秒
    public static String getRemainTime(long remain) {
        long day = remain / (DAY_IN_SEC);

        long days = day * DAY_IN_SEC;
        long hour = (remain - days) / HOUR_IN_SEC;

        long hours = hour * HOUR_IN_SEC;
        long minute = (remain - days - hours) / 60;

        long second = remain - days - hours - minute * 60;

        String value = "";

        //天
        if (day >= 0) {
            if (day < 10) {
                value += "0";
            }
            value += day + ",";
        } else {
            value += "-1,";
        }

        //时
        if (hour >= 0) {
            if (hour < 10) {
                value += "0";
            }
            value += hour + ",";
        } else {
            value += "-1,";
        }

        //分
        if (minute >= 0) {
            if (minute < 10) {
                value += "0";
            }
            value += minute + ",";
        } else {
            value += "-1,";
        }

        //秒
        if (second < 10) {
            value += "0";
        }
        value += second;
        return value;
    }

}
