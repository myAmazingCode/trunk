package net.shopnc.b2b2c.android.util;

import android.os.Environment;

import net.shopnc.b2b2c.BuildConfig;

/**
 * Created by qyf on 2016/3/3.
 */
public class Constants {
    public static final String ROOT_DIR;

    //获取外部存储路径
    static {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            ROOT_DIR = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            ROOT_DIR = Environment.getRootDirectory().getAbsolutePath();
        }
    }

    //根路径
    public static final String APP_DIR = ROOT_DIR + "/trunk";
//    public static final String APP_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/trunk";

    //图片缓存路径
    public static final String CACHE_DIR = APP_DIR + "/cachePic";

    //log缓存路径
    public static final String LOG_DIR = APP_DIR + "/log";

    public static final String URL_PATH = "http://b2b2c.shopnctest.com/dema/mo_bile/index.php?";
    public static final String URL_LIST = "act=goods&op=goods_list&keyword=+&key=&order=2&gift=null&groupbuy=null&xianshi=null&own_shop=null&price_from=null&price_to=null&area_id=null&ci=null";


    public static final String SYSTEM_INIT_FILE_NAME = "sysini";

    /**
     * 新增收货地址返回标识
     */
    public static final int ADD_ADDRESS_SUCC = 5;

    /**
     * 修改用户真实姓名返回标识
     */
    public static final int MEMBER_NAME_EDIT_RESULT = 0;

    /**
     * 分页的时候，一页显示的条数
     */
    public static final int pageSize = 10;

    /**
     * YES-是 NO-否
     */
    public static final int YES = 1;
    public static final int NO = 0;

    /**
     * 首页专题类型
     */
    public static final String AD = "ad";
    public static final String GOODS = "goods";
    public static final String TEXT = "text";
    public static final String HOME1 = "home1";
    public static final String HOME2 = "home2";
    public static final String HOME3 = "home3";
    public static final String HOME4 = "home4";
    public static final String HOME5 = "home5";
    public static final String HOME6 = "home6";
    public static final String HOME7 = "home7";
    public static final String HOME8 = "home8";
    public static final String HOME9 = "home9";
    public static final String HOME10 = "home10";

    /**
     * 第三方应用信息
     */
    //微信APPID可测试登录
    public static final String WX_APP_ID = BuildConfig.WX_APP_ID;
    public static final String WX_APP_SECRET = BuildConfig.WX_APP_SECRET;

    //微博
    public static final String WEIBO_APP_KEY = BuildConfig.WEIBO_APP_KEY;
    public static final String WEIBO_APP_SECRET = BuildConfig.WEIBO_APP_SECRET;
    public static final String WEIBO_REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";
    public static final String WEIBO_SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";
    //QQ
    public static final String QQ_APP_ID = BuildConfig.QQ_APP_ID;
    public static final String QQ_APP_KEY = BuildConfig.QQ_APP_KEY;
}
