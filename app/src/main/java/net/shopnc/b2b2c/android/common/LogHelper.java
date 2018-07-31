package net.shopnc.b2b2c.android.common;

import android.util.Log;

import net.shopnc.b2b2c.android.BuildConfig;


/**
 * 日志帮助类
 *
 * @author huting
 * @date 2015/12/17
 */
public class LogHelper {

    public static void d(String tag, String msg){
        if(BuildConfig.DEBUG){
            Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable tr){
        if(BuildConfig.DEBUG){
            Log.d(tag, msg, tr);
        }
    }

    public static void i(String tag, String msg){
        if(BuildConfig.DEBUG){
            Log.i(tag, msg);
        }
    }
    public static void i(String tag, String msg, Throwable tr){
        if(BuildConfig.DEBUG){
            Log.i(tag, msg, tr);
        }
    }

    public static void e(String tag, String msg){
        if(BuildConfig.DEBUG){
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr){
        if(BuildConfig.DEBUG){
            Log.e(tag, msg, tr);
        }
    }
}
