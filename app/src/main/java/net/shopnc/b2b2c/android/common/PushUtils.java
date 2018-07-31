package net.shopnc.b2b2c.android.common;

import android.app.Notification;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.MediaStore;

import com.baidu.android.pushservice.CustomPushNotificationBuilder;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;

import java.util.List;

/**
 * Created by snm on 2016/6/7.
 * baidu 推送设置和使用
 */
public class PushUtils {
//  设置tag  请输入多个标签，以英文逗号隔开
    public static void setTag(Context context,String tag){
        List<String> tags = Utils.getTagsList(tag);
        PushManager.setTags(context, tags);
    }
    //    删除tag
    public static void delTag(Context context,String tag){
        List<String> tags = Utils.getTagsList(tag);
        PushManager.delTags(context, tags);
    }
    // 解绑
    public static void unBindForApp(Context context) {
        // Push: 解绑
        PushManager.stopWork(context);
    }

    public static void BinForApp(Context context){
        PushManager.startWork(context,
                PushConstants.LOGIN_TYPE_API_KEY,
                Utils.getMetaValue(context, "P1vKEMreHXWtuazwHDHgGgVY"));
        // Push: 无账号初始化，用api key绑定
        // checkApikey();
        PushManager.startWork(context,
                PushConstants.LOGIN_TYPE_API_KEY,
                Utils.getMetaValue(context, "api_key"));
        Resources resource = context.getResources();
        String pkgName = context.getPackageName();
        CustomPushNotificationBuilder cBuilder = new CustomPushNotificationBuilder(
                resource.getIdentifier(
                        "notification_custom_builder", "layout", pkgName),
                resource.getIdentifier("notification_icon", "id", pkgName),
                resource.getIdentifier("notification_title", "id", pkgName),
                resource.getIdentifier("notification_text", "id", pkgName));
        cBuilder.setNotificationFlags(Notification.FLAG_AUTO_CANCEL);
        cBuilder.setNotificationDefaults(Notification.DEFAULT_VIBRATE);
        cBuilder.setStatusbarIcon(context.getApplicationInfo().icon);
        cBuilder.setLayoutDrawable(resource.getIdentifier(
                "ic_launcher", "drawable", pkgName));
        cBuilder.setNotificationSound(Uri.withAppendedPath(
                MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "6").toString());
        // 推送高级设置，通知栏样式设置为下面的ID
        PushManager.setNotificationBuilder(context, 1, cBuilder);
    }
}
