package net.shopnc.b2b2c.android.common.log;

import com.orhanobut.logger.Logger;

/**
 * 日志帮助类
 *
 * @Author dqw
 * Created 2017/3/32 14:55
 */
public class LogHelper {

    public static void d(String msg) {
        Logger.d(msg);
    }

    public static void d(String tag, String msg) {
        Logger.d(tag + ":" + msg);
    }

    public static void i(String msg) {
        Logger.i(msg);
    }

    public static void i(String tag, String msg) {
        Logger.i(tag + ":" + msg);
    }

    public static void w(String msg) {
        Logger.w(msg);
    }

    public static void w(String tag, String msg) {
        Logger.w(tag + ":" + msg);
    }

    public static void e(String msg) {
        Logger.e(msg);
    }

    public static void e(String tag, String msg) {
        Logger.e(tag + ":" + msg);
    }
}
