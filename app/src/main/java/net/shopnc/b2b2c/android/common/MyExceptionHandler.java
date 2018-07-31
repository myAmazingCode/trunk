package net.shopnc.b2b2c.android.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常捕获
 *
 * @author huting
 * @date 2016/1/15
 */
public class MyExceptionHandler implements Thread.UncaughtExceptionHandler{

    private final static String TAG = "UncaughtException";
    private static MyExceptionHandler mUncaughtException;
    private Context context;
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    private Map<String, String> infos = new HashMap<String, String>();
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private MyExceptionHandler() {}


    public synchronized static MyExceptionHandler getInstance() {
        if (mUncaughtException == null) {
            mUncaughtException = new MyExceptionHandler();
        }
        return mUncaughtException;
    }


    public void init() {
        Thread.setDefaultUncaughtExceptionHandler(mUncaughtException);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        saveCrashInfo2File(throwable);
        Log.e(TAG, "uncaughtException thread : " + thread + "||name=" + thread.getName() + "||id=" + thread.getId() + "||exception=" + throwable);
        showDialog();
    }

    private void showDialog() {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                new AlertDialog.Builder(context).setTitle("泪奔提示").setCancelable(false).setMessage("程序出现异常...")
                        .setNeutralButton("我知道了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //System.exit(0);
                               // ((Activity)context).finish();
                                dialog.dismiss();
                                System.exit(0);
                            }
                        }).create().show();
                Looper.loop();
            }
        }.start();
    }


    private String saveCrashInfo2File(Throwable ex) {
        StringBuffer sb = new StringBuffer();

        long timestamp = System.currentTimeMillis();
        String time = formatter.format(new Date());
        sb.append("\n"+time+"----");
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();

        String result = writer.toString();
        sb.append(result);
        try {

            String fileName = "exception.log";

            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = "/sdcard/ShopNC/crash/";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path + fileName,true);
                fos.write(sb.toString().getBytes());
                fos.close();
            }

            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }

        return null;
    }
}
