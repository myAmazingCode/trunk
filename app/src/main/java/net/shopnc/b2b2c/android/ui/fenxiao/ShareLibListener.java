package net.shopnc.b2b2c.android.ui.fenxiao;

import android.app.Activity;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.Constant;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyShopApplication;

/**
 * Created by snm on 2016/9/21.
 */
public class ShareLibListener {

    //设置单个监听是否分享成功
    public static UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            if(platform ==SHARE_MEDIA.QQ){
                Toast.makeText(MyShopApplication.getInstance(), "QQ分享成功啦", Toast.LENGTH_SHORT).show();
            }else if (platform == SHARE_MEDIA.SINA){
                Toast.makeText(MyShopApplication.getInstance(), "新浪微博分享成功啦", Toast.LENGTH_SHORT).show();
            }else if(platform == SHARE_MEDIA.QZONE){
                Toast.makeText(MyShopApplication.getInstance(), "QQ空间分享成功啦", Toast.LENGTH_SHORT).show();
            }else if(platform == SHARE_MEDIA.WEIXIN){
                Toast.makeText(MyShopApplication.getInstance(), "微信分享成功啦", Toast.LENGTH_SHORT).show();
            }else if(platform == SHARE_MEDIA.WEIXIN_CIRCLE){
                Toast.makeText(MyShopApplication.getInstance(), "微信朋友圈分享成功啦", Toast.LENGTH_SHORT).show();
            }else if(platform == SHARE_MEDIA.SMS){
                Toast.makeText(MyShopApplication.getInstance(), "短信分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {

            if(platform ==SHARE_MEDIA.QQ){
                Toast.makeText(MyShopApplication.getInstance(), "QQ分享失败啦", Toast.LENGTH_SHORT).show();
            }else if (platform == SHARE_MEDIA.SINA){
                Toast.makeText(MyShopApplication.getInstance(), "新浪微博分享失败啦", Toast.LENGTH_SHORT).show();
            }else if(platform == SHARE_MEDIA.QZONE){
                Toast.makeText(MyShopApplication.getInstance(), "QQ空间分享失败啦", Toast.LENGTH_SHORT).show();
            }else if(platform == SHARE_MEDIA.WEIXIN){
                Toast.makeText(MyShopApplication.getInstance(), "微信分享失败啦", Toast.LENGTH_SHORT).show();
            }else if(platform == SHARE_MEDIA.WEIXIN_CIRCLE){
                Toast.makeText(MyShopApplication.getInstance(), "微信朋友圈分享失败啦", Toast.LENGTH_SHORT).show();
            }else if(platform == SHARE_MEDIA.SMS){
                Toast.makeText(MyShopApplication.getInstance(), "短信分享失败啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

            if(platform ==SHARE_MEDIA.QQ){
                Toast.makeText(MyShopApplication.getInstance(), "QQ分享取消了", Toast.LENGTH_SHORT).show();
            }else if (platform == SHARE_MEDIA.SINA){
                Toast.makeText(MyShopApplication.getInstance(), "新浪微博分享取消了", Toast.LENGTH_SHORT).show();
            }else if(platform == SHARE_MEDIA.QZONE){
                Toast.makeText(MyShopApplication.getInstance(), "QQ空间分享取消了", Toast.LENGTH_SHORT).show();
            }else if(platform == SHARE_MEDIA.WEIXIN){
                Toast.makeText(MyShopApplication.getInstance(), "微信分享取消了", Toast.LENGTH_SHORT).show();
            }else if(platform == SHARE_MEDIA.WEIXIN_CIRCLE){
                Toast.makeText(MyShopApplication.getInstance(), "微信朋友圈分享取消了", Toast.LENGTH_SHORT).show();
            }else if(platform == SHARE_MEDIA.SMS){
                Toast.makeText(MyShopApplication.getInstance(), "短信分享取消了", Toast.LENGTH_SHORT).show();
            }
        }
    };
    public static Activity mContent;
    public static String goodsWapUrl;
    public static String text;
    public static String url;
    public static String title;

    //设置多平台分享监听
    public static ShareBoardlistener shareBoardlistener = new ShareBoardlistener() {

        @Override
        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
            UMImage image = new UMImage(MyShopApplication.getInstance(), goodsWapUrl);
            if(share_media != SHARE_MEDIA.SMS){
                new ShareAction(mContent).setPlatform(share_media).setCallback(umShareListener)
                        .withText(text)
                        .withTargetUrl(url)
                        .withMedia(image)
                        .withTitle(title)
                        .share();
            }else{
                new ShareAction(mContent).setPlatform(share_media).setCallback(umShareListener)
                        .withText(text)
                        .withTitle(text)
                        .withTargetUrl(url)
                        .share();
            }
        }
    };

}
