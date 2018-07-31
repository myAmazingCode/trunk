package net.shopnc.b2b2c.android.common;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.widget.TextView;

import com.alivc.player.AccessKey;
import com.alivc.player.AccessKeyCallback;
import com.alivc.player.AliVcMediaPlayer;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.bumptech.glide.request.target.ViewTarget;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.emitter.Emitter.Listener;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.umeng.socialize.PlatformConfig;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.IMMemberInFo;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler.Callback;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ui.mine.IMNewListActivity;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 全局变量
 *
 * @author KingKong-HE
 * @Time 2014-12-31
 * @Email KingKong@QQ.COM
 */
public class MyShopApplication extends Application {

    /**
     * 系统初始化配置文件操作器
     */
    private SharedPreferences sysInitSharedPreferences;

    /**
     * 记录用户登录后的密钥KEY
     */
    private String loginKey;

    /**
     * 记录用户登录后的MemberID
     */
    private String memberID;

    /**
     * 记录用户登录后的UserName
     */
    private String userName;

    /**
     * 记录用户登录后的memberAvatar
     */
    private String memberAvatar;

    /**
     * 记录是否自动登录
     */
    private boolean IsCheckLogin;

    /**
     * 记录IM是否在线
     */
    private boolean IMConnect = false;

    /**
     * 记录IM是否提示Notification
     */
    private boolean IMNotification = true;

    /**
     * 记录是否显示未读消息
     */
    private boolean showNum = true;

    /**
     * 消息通知
     */
    private Notification mNotification;
    private NotificationManager mNotificationManager;

    /**
     * Socket即时通讯实例
     */
    private Socket mSocket;

    //热门搜索
    private String searchHotName;
    private String searchHotValue;

    //搜索关键词列表
    private ArrayList<String> searchKeyList;

    //配送区域地区
    private String areaId;
    private String areaName;

    //记录拍照地址
    private String imgPath;

    //百度地图
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    public TextView mLocationResult;
    public double lat, lon;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getAreaId() {
        String areaId = sysInitSharedPreferences.getString("areaId", "");
        if (areaId == null) {
            areaId = "";
        }
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
        sysInitSharedPreferences.edit().putString("areaId", this.areaId).commit();
    }

    public String getAreaName() {
        String areaName = sysInitSharedPreferences.getString("areaName", "");
        if (areaName == null || areaName.equals("")) {
            areaName = "全国";
        }
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
        sysInitSharedPreferences.edit().putString("areaName", this.areaName).commit();
    }

    private static MyShopApplication instance;

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        PlatformConfig.setWeixin(Constants.APP_ID, Constants.APP_SECRET);
        //微信 appid appsecret
        PlatformConfig.setSinaWeibo(Constants.WEIBO_APP_KEY, Constants.WEIBO_APP_SECRET);
        //新浪微博 appkey appsecret
        PlatformConfig.setQQZone(Constants.QQ_APP_ID, Constants.QQ_APP_KEY);
        // QQ和Qzone appid appkey

        //百度地图
        initBaiduMap();

        sysInitSharedPreferences = getSharedPreferences(Constants.SYSTEM_INIT_FILE_NAME, MODE_PRIVATE);
        ViewTarget.setTagId(R.id.glide_tag);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotification = new Notification(R.drawable.ic_launcher, getString(R.string.more_aboutus_appname), System.currentTimeMillis());

        loginKey = sysInitSharedPreferences.getString("loginKey", "");

        memberID = sysInitSharedPreferences.getString("memberID", "");

        userName = sysInitSharedPreferences.getString("userName", "");

        memberAvatar = sysInitSharedPreferences.getString("memberAvatar", "");

        IsCheckLogin = sysInitSharedPreferences.getBoolean("IsCheckLogin", false);

        loadingUserInfo(loginKey, memberID);

        createCacheDir();

        initImageLoader(this);

//        MyExceptionHandler mUncaughtException = MyExceptionHandler.getInstance();
//        mUncaughtException.init();

        try {
            //连接Socket
            mSocket = IO.socket(Constants.IM_HOST);

            mSocket.io().reconnectionDelay(2000);

            mSocket.connect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        //通知已连接
        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            public void call(Object... args) {
//			   System.out.println("已连接");

                UpDateUser();

            }
        });

        //通知已断开
        mSocket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

            public void call(Object... args) {

//			  System.out.println("已断开");

                IMConnect = false;////设置链接失败
//			  mNotification.tickerText = "您的IM帐号已离线";;
//			  Intent intent = new Intent(getApplicationContext(),IMFriendsListActivity.class);  
//              PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);  
//              mNotification.setLatestEventInfo(getApplicationContext(), "", "",contentIntent);//ShopNC商城客户端 
//              mNotificationManager.notify(-1, mNotification);// 通知一下才会生效哦  
//              mNotificationManager.cancel(-1);

            }
        });
        //获取node消息
        mSocket.on("get_msg", new Listener() {

            public void call(Object... get_msg) {
                String message = get_msg[0].toString();

                IMConnect = true;//设置链接成功

                if (!message.equals("{}")) {
                    if (isIMNotification()) {
                        mNotification.tickerText = "新消息注意查收";

//                        Intent intent = new Intent(getApplicationContext(), IMFriendsListActivity.class);
                        Intent intent = new Intent(getApplicationContext(), IMNewListActivity.class);

                        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
//                        mNotification.setLatestEventInfo(getApplicationContext(), "消息提示", "有新消息注意查收", contentIntent);//ShopNC商城客户端
//                        mNotificationManager.notify(13, mNotification);// 通知一下才会生效哦
                    } else {
                        Intent intent = new Intent(Constants.IM_UPDATA_UI);
                        intent.putExtra("message", message);
                        sendBroadcast(intent);
                    }
                }

            }
        });

        mSocket.on("get_state", new Listener() {

            public void call(Object... obj) {
                String get_state = obj[0].toString();

                Intent intent = new Intent(Constants.IM_FRIENDS_LIST_UPDATA_UI);
                intent.putExtra("get_state", get_state);
                sendBroadcast(intent);
            }
        });

        /*百度推送*/

        PushUtils.BinForApp(this);
       /*阿里云播放器*/
        String businessId = "video_live";
        final String accessKeyId = "7dy4fM0WxuUKicK4";
        final String accessKeySecret = "ACzQ3TGUuN01cLbubqKSEDZ2CnOPdv";
        AliVcMediaPlayer.init(getApplicationContext(), businessId, new
                AccessKeyCallback() {
                    public AccessKey getAccessToken() {
                        return new AccessKey(accessKeyId, accessKeySecret);
                    }
                });

    }

    private static final int PERMISSION_REQUEST_CODE = 0;//系统授权管理页面时的结果参数

    //请求权限去兼容版本
    private void requestPermissions(String... permission) {
        ActivityCompat.requestPermissions((Activity) MyShopApplication.this.getApplicationContext(), permission, PERMISSION_REQUEST_CODE);
    }

    public static MyShopApplication getInstance() {
        return instance;
    }

    /**
     * node 更新会员状态
     */
    public void UpDateUser() {

        if (!TextUtils.isEmpty(memberID)) {
            try {
                String update_user = "{\"u_id\":\"" + memberID + "\",\"u_name\":\"" + userName + "\",\"avatar\":\"" + memberAvatar + "\"}";
                mSocket.emit("update_user", new JSONObject(update_user));
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 获取个人资料
     *
     * @param key  用户标识
     * @param u_id 用户ID
     *             *
     */
    public void loadingUserInfo(String key, String u_id) {
        String url = Constants.URL_MEMBER_CHAT_GET_USER_INFO;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", key);
        params.put("u_id", u_id);
        params.put("t", "member_id");

        RemoteDataHandler.asyncPostDataString(url, params, new Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                if (data.getCode() == HttpStatus.SC_OK) {
                    String json = data.getJson();
                    try {
                        JSONObject obj2 = new JSONObject(json);
                        String member_info = obj2.getString("member_info");
                        IMMemberInFo memberINFO = IMMemberInFo.newInstanceList(member_info);
                        setMemberID(memberINFO.getMember_id() == null ? "" : memberINFO.getMember_id());
                        setMemberAvatar(memberINFO.getMember_avatar() == null ? "" : memberINFO.getMember_avatar());
                        setUserName(memberINFO.getMember_name() == null ? "" : memberINFO.getMember_name());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
//					Toast.makeText(MyShopApplication.this, R.string.load_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * 百度地图的初始化
     *
     * @return
     */
    private void initBaiduMap() {
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
        mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("gcj02");// 可选，默认gcj02，设置返回的定位结果坐标系
        int span = 5000;
        option.setScanSpan(span);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);// 可选，默认false,设置是否使用gps
        option.setLocationNotify(true);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIgnoreKillProcess(false);// 可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);// 可选，默认false，设置是否收集CRASH信息，默认收集
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(myListener); // 注册监听函数
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // Receive Location
            StringBuilder sb = new StringBuilder(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            lat = location.getLatitude();

            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            lon = location.getLongitude();

            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                // 运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }

            if (mLocationResult != null) {
                mLocationResult.setText(sb.toString());
            }
            LogHelper.i("huting---BaiduLocationApiDem", sb.toString());
        }
    }

    public boolean isShowNum() {
        return showNum;
    }

    public void setShowNum(boolean showNum) {
        this.showNum = showNum;
    }

    public SharedPreferences getSysInitSharedPreferences() {
        return sysInitSharedPreferences;
    }

    public void setSysInitSharedPreferences(
            SharedPreferences sysInitSharedPreferences) {
        this.sysInitSharedPreferences = sysInitSharedPreferences;
    }

    public void setLoginKey(String loginKey) {
        this.loginKey = loginKey;
        sysInitSharedPreferences.edit().putString("loginKey", this.loginKey).commit();
    }

    public String getLoginKey() {
        String loginKey = sysInitSharedPreferences.getString("loginKey", "");
        return loginKey;
    }

    public String getMemberAvatar() {
        String memberAvatar = sysInitSharedPreferences.getString("memberAvatar", "");
        return memberAvatar;
    }

    public void setMemberAvatar(String memberAvatar) {
        this.memberAvatar = memberAvatar;
        sysInitSharedPreferences.edit().putString("memberAvatar", this.memberAvatar).commit();
    }

    public boolean isIsCheckLogin() {
        boolean IsCheckLogin = sysInitSharedPreferences.getBoolean("IsCheckLogin", false);
        return IsCheckLogin;
    }

    public void setIsCheckLogin(boolean isCheckLogin) {
        IsCheckLogin = isCheckLogin;
        sysInitSharedPreferences.edit().putBoolean("IsCheckLogin", this.IsCheckLogin).commit();
    }

    public String getSearchHotValue() {
        return searchHotValue;
    }

    public void setSearchHotValue(String searchHotValue) {
        this.searchHotValue = searchHotValue;
    }

    public String getSearchHotName() {
        return searchHotName;
    }

    public void setSearchHotName(String searchHotName) {
        this.searchHotName = searchHotName;
    }

    public ArrayList<String> getSearchKeyList() {
        return searchKeyList;
    }

    public void setSearchKeyList(ArrayList<String> searchKeyList) {
        this.searchKeyList = searchKeyList;
    }

    public boolean isIMConnect() {
        return IMConnect;
    }

    public void setIMConnect(boolean iMConnect) {
        IMConnect = iMConnect;
    }

    public String getMemberID() {
        String memberID = sysInitSharedPreferences.getString("memberID", "");
        return memberID;
    }

    public String getUserName() {
        String userName = sysInitSharedPreferences.getString("userName", "");
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        sysInitSharedPreferences.edit().putString("userName", this.userName).commit();
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
        sysInitSharedPreferences.edit().putString("memberID", this.memberID).commit();
    }

    public boolean isIMNotification() {
        return IMNotification;
    }

    public Socket getmSocket() {
        return mSocket;
    }

    public void setmSocket(Socket mSocket) {
        this.mSocket = mSocket;
    }

    public void setIMNotification(boolean iMNotification) {
        IMNotification = iMNotification;
    }

    public NotificationManager getmNotificationManager() {
        return mNotificationManager;
    }

    public void setmNotificationManager(NotificationManager mNotificationManager) {
        this.mNotificationManager = mNotificationManager;
    }

    public Notification getmNotification() {
        return mNotification;
    }

    public void setmNotification(Notification mNotification) {
        this.mNotification = mNotification;
    }

    /**
     * 设置ImageLoader初始化参数
     */
    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        File cacheDir = new File(Constants.CACHE_DIR_IMAGE);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .memoryCacheSizePercentage(13) // default
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCache(new UnlimitedDiscCache(cacheDir))
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

    /**
     * 创建SD卡缓存目录
     */
    private void createCacheDir() {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            File f = new File(Constants.CACHE_DIR);
            if (f.exists()) {
                System.out.println("SD卡缓存目录:已存在!");
            } else {
                if (f.mkdirs()) {
                    System.out.println("SD卡缓存目录:" + f.getAbsolutePath()
                            + "已创建!");
                } else {
                    System.out.println("SD卡缓存目录:创建失败!");
                }
            }

            File ff = new File(Constants.CACHE_DIR_IMAGE);
            if (ff.exists()) {
                System.out.println("SD卡照片缓存目录:已存在!");
            } else {
                if (ff.mkdirs()) {
                    System.out.println("SD卡照片缓存目录:" + ff.getAbsolutePath()
                            + "已创建!");
                } else {
                    System.out.println("SD卡照片缓存目录:创建失败!");
                }
            }

            File fff = new File(Constants.CACHE_DIR_UPLOADING_IMG);
            if (fff.exists()) {
                System.out.println("SD卡照片缓存目录:已存在!");
            } else {
                if (fff.mkdirs()) {
                    System.out.println("SD卡照片缓存目录:" + fff.getAbsolutePath()
                            + "已创建!");
                } else {
                    System.out.println("SD卡照片缓存目录:创建失败!");
                }
            }
        }
    }


}
