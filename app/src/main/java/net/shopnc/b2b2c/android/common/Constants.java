package net.shopnc.b2b2c.android.common;

import android.os.Environment;

import net.shopnc.b2b2c.android.BuildConfig;

/**
 * 常量类
 *
 * @author KingKong-HE
 * @Time 2014-12-31
 * @Email KingKong@QQ.COM
 */
public final class Constants {

    /**
     * 系统初始化配置文件名
     */
    public static final String SYSTEM_INIT_FILE_NAME = "sysini";

    /**
     * 本地缓存目录
     */
    public static final String CACHE_DIR;

    /**
     * 分页显示个数
     */
    public static final int PAGESIZE = 10;
    public static final int PAGESIZE1 = 5;

    /**
     * 表情缓存目录
     */
    public static final String CACHE_DIR_SMILEY;

    /**
     * 图片缓存目录
     */
    public static final String CACHE_DIR_IMAGE;

    /**
     * 待上传图片缓存目录
     */
    public static final String CACHE_DIR_UPLOADING_IMG;

    //微信APPID可测试支付
//    public static final String APP_ID = "wx76c019464a5ebdaa";
    //微信APPID可测试登录
    public static final String APP_ID = BuildConfig.WX_APP_ID;
    public static final String APP_SECRET = BuildConfig.WX_APP_SECRET;

    //新浪key
    public static final String WEIBO_APP_KEY = BuildConfig.WEIBO_APP_KEY;
    public static final String WEIBO_APP_SECRET = BuildConfig.WEIBO_APP_SECRET;
    public static final String WEIBO_REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";
    public static final String WEIBO_SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";

    //QQ APPID
    public static final String QQ_APP_ID = BuildConfig.QQ_APP_ID;
    public static final String QQ_APP_KEY = BuildConfig.QQ_APP_KEY;

    public static String goodId;//商品id


    /**
     * 登录成功广播返回标识
     */
    public static final String LOGIN_SUCCESS_URL = "2";

    /**
     * 商品跳转购物车广播返回标识
     */
    public static final String SHOW_CART_URL = "3";

    /**
     * 店铺页面的排行的商品的数量
     */
    public static final String STORE_RANKING_NUM = "3";

    /**
     * 点击去逛逛跳转首页广播返回标识
     */
    public static final String SHOW_HOME_URL = "9";

    /**
     * 跳转到分类页面广播返回标识
     */
    public static final String SHOW_Classify_URL = "7";

    /**
     * 跳转到“我”页面广播返回标识
     */
    public static final String SHOW_Mine_URL = "1";
    /**
     * 选中发票后返回标识
     */
    public static final int SELECT_INVOICE = 4;

    /**
     * 新增收货地址返回标识
     */
    public static final int ADD_ADDRESS_SUCC = 5;

    /**
     * 选中收货地址返回标识
     */
    public static final int SELECT_ADDRESS = 6;

    /**
     * 绑定手机返回标识
     */
    public static final int RESULT_FLAG_BIND_MOBILE = 90;

    /**
     * 设置支付密码返回标识
     */
    public static final int RESULT_FLAG_SET_PAYPWD = 91;

    /**
     * 支付成功返回标识
     */
    public static final String PAYMENT_SUCCESS = "7";

    /**
     * 虚拟订单支付成功返回标识
     */
    public static final String VPAYMENT_SUCCESS = "8";

    /**
     * IM新消息刷新页面返回标识
     */
    public static final String IM_UPDATA_UI = "10";

    /**
     * IM好友列表状态刷新页面返回标识
     */
    public static final String IM_FRIENDS_LIST_UPDATA_UI = "11";
    public static final String SHOW_CART_NUM = "22";

    static {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            CACHE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ShopNC/";
        } else {
            CACHE_DIR = Environment.getRootDirectory().getAbsolutePath() + "/ShopNC/";
        }

        CACHE_DIR_SMILEY = CACHE_DIR + "/smiley";
        CACHE_DIR_IMAGE = CACHE_DIR + "/pic";
        CACHE_DIR_UPLOADING_IMG = CACHE_DIR + "/uploading_img";
    }

    private Constants() {
    }

    /**
     * 应用包名
     */
    public static final String APPLICATION_ID = BuildConfig.APPLICATION_ID;

    /**
     * 与服务器端连接的协议名
     */
    public static final String PROTOCOL = BuildConfig.PROTOCOL;

    /**
     * 服务器域名
     */
//    public static final String HOST = "192.168.1.4/b2b2c/";
//    public static final String HOST = "www.shopnc2015.com";
//	public static final String HOST = "b2b2c.shopnctest.com/test1";
    //public static final String HOST = "b2b2c.shopnctest.com/test1";
    public static final String HOST = BuildConfig.HOST;
//	public static final String HOST = "b2b2c.shopnctest.com/test1";

    //WAPURL
    public static final String WAP_URL = BuildConfig.WAP_URL;
    public static final String WAP_GOODS_URL = WAP_URL + "tmpl/product_detail.html?goods_id=";

    /**
     * IM服务器地址、端口号
     */
    public static final String IM_HOST = PROTOCOL + BuildConfig.IM_HOST;

    /**
     * 服务器端口号
     */
    public static final String PORT = BuildConfig.PORT;

    /**
     * 应用上下文名
     */
    public static final String APP = BuildConfig.APP;

    /**
     * 应用上下文完整路径
     */
    public static final String URL_CONTEXTPATH = PROTOCOL + HOST + APP + "/index.php?";

    /**
     * 首页请求地址
     */
    public static final String URL_HOME = URL_CONTEXTPATH + "act=index&op=index";

    /**
     * 专题接口(get)
     */
    public static final String URL_SPECIAL = URL_CONTEXTPATH + "act=index&op=special";

    /**
     * 一级分类请求地址
     */
    public static final String URL_GOODSCLASS = URL_CONTEXTPATH + "act=goods_class";

    /**
     * 商品列表请求地址
     */
    public static final String URL_GOODSLIST = URL_CONTEXTPATH + "act=goods&op=goods_list";

    /**
     * 商品详情请求地址
     */
    public static final String URL_GOODSDETAILS = URL_CONTEXTPATH + "act=goods&op=goods_detail";

    /**
     * 登录请求地址
     */
    public static final String URL_LOGIN = URL_CONTEXTPATH + "act=login";

    /**
     * 我的商城请求地址
     */
    public static final String URL_MYSTOIRE = URL_CONTEXTPATH + "act=member_index";

    /**
     * 商品详情WEB页面
     */
    public static final String URL_GOODS_DETAILS_WEB = URL_CONTEXTPATH + "act=goods&op=goods_body";

    /**
     * 添加收藏请求地址
     */
    public static final String URL_ADD_FAVORITES = URL_CONTEXTPATH + "act=member_favorites&op=favorites_add";

    /**
     * 收藏列表请求地址
     */
    public static final String URL_FAVORITES_LIST = URL_CONTEXTPATH + "act=member_favorites&op=favorites_list";

    /**
     * 删除收藏请求地址
     */
    public static final String URL_FAVORITES_DELETE = URL_CONTEXTPATH + "act=member_favorites&op=favorites_del";

    /**
     * 地址列表请求地址
     */
    public static final String URL_ADDRESS_LIST = URL_CONTEXTPATH + "act=member_address&op=address_list";

    /**
     * 订单列表请求地址
     */
    public static final String URL_ORDER_LIST = URL_CONTEXTPATH + "act=member_order&op=order_list";

    /**
     * 订单详情请求地址
     */
    public static final String URL_ORDER_DETAILS = URL_CONTEXTPATH + "act=member_order&op=order_info";

    /**
     * 订单退款请求地址
     */
    public static final String URL_ORDER_REFUND = URL_CONTEXTPATH + "act=member_refund&op=refund_all_form";

    /**
     * 部分商品退款退货信息
     */
    public static final String URL_ORDER_REFUND_SOME = URL_CONTEXTPATH + "act=member_refund&op=refund_form";

    /**
     * 订单物流请求地址
     */
    public static final String URL_ORDER_WULIU_INFO = URL_CONTEXTPATH + "act=member_order&op=search_deliver";


    /**
     * 订单物流请求最新一条信息
     */
    public static final String URL_ORDER_WULIU_NEW_ONE = URL_CONTEXTPATH + "act=member_order&op=get_current_deliver";

    /**
     * 添加购物车请求地址
     */
    public static final String URL_ADD_CART = URL_CONTEXTPATH + "act=member_cart&op=cart_add";

    /**
     * 批量添加购物车请求地址
     */
    public static final String URL_ADD_CART_BATCH = URL_CONTEXTPATH + "act=member_cart&op=cart_batchadd";


    /**
     * 购物车数量请求地址
     */
    public static final String URL_GET_CART_NUM = URL_CONTEXTPATH + "act=member_cart&op=cart_count";

    /**
     * 购物车列表请求地址(老版本)
     */
    public static final String URL_CART_LIST_OLD = URL_CONTEXTPATH + "act=member_cart&op=cart_list_old";

    /**
     * 购物车列表请求地址
     */
    public static final String URL_CART_LIST = URL_CONTEXTPATH + "act=member_cart&op=cart_list";

    /**
     * 购物车删除请求地址
     */
    public static final String URL_CART_DETELE = URL_CONTEXTPATH + "act=member_cart&op=cart_del";

    /**
     * 购物车修改数量
     */
    public static final String URL_CART_EDIT_QUANTITY = URL_CONTEXTPATH + "act=member_cart&op=cart_edit_quantity";

    /**
     * 注销登出请求地址
     */
    public static final String URL_LOGIN_OUT = URL_CONTEXTPATH + "act=logout";

    /**
     * 地址详细信息请求地址
     */
    public static final String URL_ADDRESS_DETAILS = URL_CONTEXTPATH + "act=member_address&op=address_info";

    /**
     * 地区列表请求地址
     */
    public static final String URL_GET_CITY = URL_CONTEXTPATH + "act=area&op=area_list";

    /**
     * 地址编辑请求地址
     */
    public static final String URL_ADDRESS_EDIT = URL_CONTEXTPATH + "act=member_address&op=address_edit";

    /**
     * 地址删除请求地址
     */
    public static final String URL_ADDRESS_DETELE = URL_CONTEXTPATH + "act=member_address&op=address_del";

    /**
     * 地址添加请求地址
     */
    public static final String URL_ADDRESS_ADD = URL_CONTEXTPATH + "act=member_address&op=address_add";

    /**
     * 在线帮助请求地址
     */
    public static final String URL_HELP = PROTOCOL + HOST + APP + "/help.html";

    /**
     * 购买步骤1请求地址
     */
    public static final String URL_BUY_STEP1 = URL_CONTEXTPATH + "act=member_buy&op=buy_step1";

    /**
     * 购买步骤2请求地址
     */
    public static final String URL_BUY_STEP2 = URL_CONTEXTPATH + "act=member_buy&op=buy_step2";

    /**
     * 发票列表请求地址
     */
    public static final String URL_INVOICE_LIST = URL_CONTEXTPATH + "act=member_invoice&op=invoice_list";

    /**
     * 发票内容列表请求地址
     */
    public static final String URL_INVOICE_CONTEXT_LIST = URL_CONTEXTPATH + "act=member_invoice&op=invoice_content_list";

    /**
     * 添加发票请求地址
     */
    public static final String URL_INVOICE_ADD = URL_CONTEXTPATH + "act=member_invoice&op=invoice_add";

    /**
     * 删除发票请求地址
     */
    public static final String URL_INVOICE_DELETE = URL_CONTEXTPATH + "act=member_invoice&op=invoice_del";

    /**
     * 更换收货地址请求地址
     */
    public static final String URL_UPDATE_ADDRESS = URL_CONTEXTPATH + "act=member_buy&op=change_address";

    /**
     * 验证密码请求地址
     */
    public static final String URL_CHECK_PASSWORD = URL_CONTEXTPATH + "act=member_buy&op=check_password";

    /**
     * 订单取消(未付款)请求地址
     */
    public static final String URL_ORDER_CANCEL = URL_CONTEXTPATH + "act=member_order&op=order_cancel";

    /**
     * 订单移除（已取消）请求地址
     */

    public static final String URL_ORDER_DEL = URL_CONTEXTPATH + "act=member_order&op=order_delete";


    /**
     * 退款上传凭证保存
     */
    public static final String URL_ORDER_EXCHANGE_PHOTO = URL_CONTEXTPATH + "act=member_refund&op=upload_pic";

    /**
     * 全部退款保存
     */
    public static final String URL_ORDER_EXCHANGE_REFUND_ALL = URL_CONTEXTPATH + "act=member_refund&op=refund_all_post";

    /**
     * 部分退款信息（退货信息）
     */
    public static final String URL_ORDER_EXCHANGE_SOME = URL_CONTEXTPATH + "act=member_refund&op=refund_post";


    /**
     * 退款记录列表
     */
    public static final String URL_ORDER_EXCHANGE_MONEY_LIST = URL_CONTEXTPATH + "act=member_refund&op=get_refund_list";

    /**
     * 查看退款信息
     */
    public static final String URL_ORDER_EXCHANGE_MONEY_DETAILS = URL_CONTEXTPATH + "act=member_refund&op=get_refund_info";

    /**
     * 退货记录列表
     */
    public static final String URL_ORDER_EXCHANGE_GOODS_LIST = URL_CONTEXTPATH + "act=member_return&op=get_return_list";

    /**
     * 查看退货信息
     */
    public static final String URL_ORDER_EXCHANGE_GOODS_DETAILS = URL_CONTEXTPATH + "act=member_return&op=get_return_info";

    /**
     * 退货发货信息
     */
    public static final String URL_ORDER_EXCHANGE_GOODS_SEND = URL_CONTEXTPATH + "act=member_return&op=ship_form";

    /**
     * 退货发货保存
     */
    public static final String URL_ORDER_EXCHANGE_GOODS_SAVA = URL_CONTEXTPATH + "act=member_return&op=ship_post";

    /**
     * 退货延迟收货保存
     */
    public static final String URL_ORDER_EXCHANGE_GOODS_DELAY = URL_CONTEXTPATH + "act=member_return&op=delay_post";

    /**
     * 订单确认收货请求地址
     */
    public static final String URL_ORDER_RECEIVE = URL_CONTEXTPATH + "act=member_order&op=order_receive";

    /**
     * 订单付款请求地址
     */
    public static final String URL_ORDER_PAYMENT = URL_CONTEXTPATH + "act=member_payment&op=pay";

    /**
     * 收藏添加
     */
    public static final String URL_ADD_FAV = URL_CONTEXTPATH + "act=member_favorites&op=favorites_add";

    /**
     * 收藏删除
     */
    public static final String URL_DELETE_FAV = URL_CONTEXTPATH + "act=member_favorites&op=favorites_del";

    /**
     * 虚拟订单付款请求地址
     */
    public static final String URL_VIRTUAL_ORDER_PAYMENT = URL_CONTEXTPATH + "act=member_payment&op=vr_pay";

    /**
     * 可用支付方式列表
     */
    public static final String URL_ORDER_PAYMENT_LIST = URL_CONTEXTPATH + "act=member_payment&op=payment_list";

    /**
     * 虚拟订单可用码列表
     */
    public static final String URL_MEMBER_VR_ODER = URL_CONTEXTPATH + "act=member_vr_order&op=indate_code_list";

    /**
     * 注册
     */
    public static final String URL_REGISTER = URL_CONTEXTPATH + "act=login&op=register";

    /**
     * 意见反馈
     */
    public static final String URL_FEEDBACK_ADD = URL_CONTEXTPATH + "act=member_feedback&op=feedback_add";

    /**
     * 版本更新
     */
    public static final String URL_VERSION_UPDATE = URL_CONTEXTPATH + "act=index&op=apk_version";

    /**
     * 物流查询
     */
    public static final String URL_QUERY_DELIVER = URL_CONTEXTPATH + "act=member_order&op=search_deliver";

    /**
     * 会员聊天--webivew
     * 请求参数
     * key 当前登录令牌
     */
    public static final String URL_MEMBER_CHAT = URL_CONTEXTPATH + "act=member_chat&op=get_node_info&key=";

    /**
     * 会员聊天--最近联系人列表
     * 调用接口(post) index.php?act=member_chat&op=get_user_list 请求参数
     * key 当前登录令牌 n 查询会员数量
     */
    public static final String URL_MEMBER_CHAT_GET_USER_LIST = URL_CONTEXTPATH + "act=member_chat&op=get_user_list";

    /**
     * 会员聊天--发消息
     * 调用接口(post) index.php?act=member_chat&op=send_msg
     * 请求参数
     * key 当前登录令牌
     * t_id  接收消息会员编号
     * t_name 接收消息会员帐号
     * t_msg 消息
     */
    public static final String URL_MEMBER_CHAT_SEND_MSG = URL_CONTEXTPATH + "act=member_chat&op=send_msg";

    /**
     * 会员聊天--会员信息 调用接口(get)
     * index.php?act=member_chat&op=get_info
     * 请求参数
     * key 当前登录令牌
     * u_id  会员编号
     * t 查询类型('member_id','member_name','store_id','member')
     */
    public static final String URL_MEMBER_CHAT_GET_USER_INFO = URL_CONTEXTPATH + "act=member_chat&op=get_info";

    /**
     * 会员聊天--聊天记录查询
     * 调用接口(post)
     * index.php?act=member_chat&op=get_chat_log
     * 请求参数
     * key 当前登录令牌
     * t_id 接收消息会员编号
     * t 查询天数('7','15','30')
     * page 每页显示数量，可为空，默认为5个
     */
    public static final String URL_MEMBER_CHAT_GET_LOG_INFO = URL_CONTEXTPATH + "act=member_chat&op=get_chat_log";

    /**
     * 我的好友--删除好友
     */
    public static final String URL_IM_FRIENDS_DELETE = URL_CONTEXTPATH + "act=member_snsfriend&op=friend_del";

    /**
     * 我的好友--会员资料
     */
    public static final String URL_IM_MEMBER_INFO = URL_CONTEXTPATH + "act=member_chat&op=get_info";

    /**
     * 虚拟购买第二步
     */
    public static final String URL_MEMBER_VR_BUY = URL_CONTEXTPATH + "act=member_vr_buy&op=buy_step2";

    /**
     * 虚拟购买第三步
     */
    public static final String URL_MEMBER_VR_BUY3 = URL_CONTEXTPATH + "act=member_vr_buy&op=buy_step3";

    /**
     * 虚拟订单列表
     */
    public static final String URL_MEMBER_VR_ORDER = URL_CONTEXTPATH + "act=member_vr_order&op=order_list";

    /**
     * 虚拟订单取消
     */
    public static final String URL_MEMBER_VR_ORDER_CANCEL = URL_CONTEXTPATH + "act=member_vr_order&op=order_cancel";

    /**
     * 获取微信参数
     */
    public static final String URL_MEMBER_WX_PAYMENT = URL_CONTEXTPATH + "act=member_payment&op=wx_app_pay3";
    /**
     * 获取微信参数
     */
    public static final String URL_MEMBER_WX_VPAYMENT = URL_CONTEXTPATH + "act=member_payment&op=wx_app_vr_pay3";
    /**
     * 推荐品牌
     */
    public static final String URL_BRAND = URL_CONTEXTPATH + "act=brand&op=recommend_list";

    /**
     * 2015sp2新增 *
     */
    //获取验证码标识
    public static final String URL_SECCODE_MAKECODEKEY = URL_CONTEXTPATH + "act=seccode&op=makecodekey";
    //获取验证码
    public static final String URL_SECCODE_MAKECODE = URL_CONTEXTPATH + "act=seccode&op=makecode";
    //第三方账号开关
    public static final String URL_CONNECT_STATE = URL_CONTEXTPATH + "act=connect&op=get_state";
    //获取短信动态码
    public static final String URL_CONNECT_GET_SMS_CAPTCHA = URL_CONTEXTPATH + "act=connect&op=get_sms_captcha";
    //验证短信动态码
    public static final String URL_CONNECT_CHECK_SMS_CAPTCHA = URL_CONTEXTPATH + "act=connect&op=check_sms_captcha";
    //手机注册
    public static final String URL_CONNECT_SMS_REGISTER = URL_CONTEXTPATH + "act=connect&op=sms_register";
    //WAP用户注册协议
    public static final String WAP_MEMBER_DOCUMENT = WAP_URL + "tmpl/member/document.html";
    //WAP找回密码
    public static final String WAP_FIND_PASSWORD = WAP_URL + "tmpl/member/find_password.html";
    //WAP品牌图标
    public static final String WAP_BRAND_ICON = WAP_URL + "images/degault.png";
    //商品分类全部子集
    public static final String URL_GOODS_CLASS_CHILD_ALL = URL_CONTEXTPATH + "act=goods_class&op=get_child_all";
    //搜索关键词列表
    public static final String URL_SEARCH_KEY_LIST = URL_CONTEXTPATH + "act=index&op=search_key_list";
    //热门关键词
    public static final String URL_SEARCH_HOT = URL_CONTEXTPATH + "act=index&op=search_hot_info";
    //微信同步登录
    public static final String URL_CONNECT_WX = URL_CONTEXTPATH + "act=connect&op=get_wx_info";
    //微博同步登录
    public static final String URL_CONNECT_WEIBO = URL_CONTEXTPATH + "act=connect&op=get_sina_info";
    //QQ同步登录
    public static final String URL_CONNECT_QQ = URL_CONTEXTPATH + "act=connect&op=get_qq_info";
    //商品列表筛选
    public static final String URL_SEARCH_ADV = URL_CONTEXTPATH + "act=index&op=search_adv";
    //商品配送及运费信息
    public static final String URL_GOODS_CALC = URL_CONTEXTPATH + "act=goods&op=calc";
    //商品描述
    public static final String URL_GOODS_BODY = URL_CONTEXTPATH + "act=goods&op=goods_body";
    //商品评价列表
    public static final String URL_GOODS_EVALUATE = URL_CONTEXTPATH + "act=goods&op=goods_evaluate";
    //商品浏览记录
    public static final String URL_GOODS_BROWSE = URL_CONTEXTPATH + "act=member_goodsbrowse&op=browse_list";
    //清空商品浏览记录
    public static final String URL_GOODS_BROWSE_CLEAR = URL_CONTEXTPATH + "act=member_goodsbrowse&op=browse_clearall";
    //虚拟订单详情
    public static final String URL_MEMBER_VR_ORDER_INFO = URL_CONTEXTPATH + "act=member_vr_order&op=order_info";
    //我的资产
    public static final String URL_MEMBER_MY_ASSET = URL_CONTEXTPATH + "act=member_index&op=my_asset";
    //签到
    public static final String URL_MEMBER_SIGNIN_ADD = URL_CONTEXTPATH + "act=member_signin&op=signin_add";
    //检查是否已签到
    public static final String URL_MEMBER_SIGNIN_CHECK = URL_CONTEXTPATH + "act=member_signin&op=checksignin";
    //签到日志
    public static final String URL_MEMBER_SIGNIN_LIST = URL_CONTEXTPATH + "act=member_signin&op=signin_list";
    //预存款日志记录
    public static final String URL_MEMBER_FUND_PREDEPOSITLOG = URL_CONTEXTPATH + "act=member_fund&op=predepositlog";
    //预存款充值明细
    public static final String URL_MEMBER_FUND_PDRECHARGELIST = URL_CONTEXTPATH + "act=member_fund&op=pdrechargelist";
    //预存款提现记录
    public static final String URL_MEMBER_FUND_PDCASHLIST = URL_CONTEXTPATH + "act=member_fund&op=pdcashlist";
    //充值卡日志记录
    public static final String URL_MEMBER_FUND_RECHARGECARDLOG = URL_CONTEXTPATH + "act=member_fund&op=rcblog";
    //充值卡充值
    public static final String URL_MEMBER_FUND_RECHARGECARDADD = URL_CONTEXTPATH + "act=member_fund&op=rechargecard_add";
    //买家代金券列表
    public static final String URL_MEMBER_VOUCHER_LIST = URL_CONTEXTPATH + "act=member_voucher&op=voucher_list";
    //卡密领取店铺代金券
    public static final String URL_MEMBER_VOUCHER_PASSWORD_ADD = URL_CONTEXTPATH + "act=member_voucher&op=voucher_pwex";
    //免费领取店铺代金券
    public static final String URL_MEMBER_VOUCHER_FREE_ADD = URL_CONTEXTPATH + "act=member_voucher&op=voucher_freeex";
    //买家红包列表
    public static final String URL_MEMBER_REDPACKET_LIST = URL_CONTEXTPATH + "act=member_redpacket&op=redpacket_list";
    //卡密领取红包
    public static final String URL_MEMBER_REDPACKET_ADD = URL_CONTEXTPATH + "act=member_redpacket&op=rp_pwex";
    //积分日志
    public static final String URL_MEMBER_POINT_LOG = URL_CONTEXTPATH + "act=member_points&op=pointslog";
    //得到会员手机绑定状态及手机号
    public static final String URL_MEMBER_ACCOUNT_GET_MOBILE_INFO = URL_CONTEXTPATH + "act=member_account&op=get_mobile_info";
    //绑定手机第一步-发送短信
    public static final String URL_MEMBER_ACCOUNT_BIND_MOBILE_STEP1 = URL_CONTEXTPATH + "act=member_account&op=bind_mobile_step1";
    //绑定手机第二步-验证短信
    public static final String URL_MEMBER_ACCOUNT_BIND_MOBILE_STEP2 = URL_CONTEXTPATH + "act=member_account&op=bind_mobile_step2";
    //更改绑定手机第二步-发送短信(没有第一步)
    public static final String URL_MEMBER_ACCOUNT_UNBIND_MOBILE_STEP2 = URL_CONTEXTPATH + "act=member_account&op=modify_mobile_step2";
    //更改绑定手机第三步-验证短信(没有第一步)
    public static final String URL_MEMBER_ACCOUNT_UNBIND_MOBILE_STEP3 = URL_CONTEXTPATH + "act=member_account&op=modify_mobile_step3";
    //更改密码第二步-发短信(没有第一步)
    public static final String URL_MEMBER_ACCOUNT_MODIFY_PASSWORD_STEP2 = URL_CONTEXTPATH + "act=member_account&op=modify_password_step2";
    //更改密码第三步-验证短信
    public static final String URL_MEMBER_ACCOUNT_MODIFY_PASSWORD_STEP3 = URL_CONTEXTPATH + "act=member_account&op=modify_password_step3";
    //更改密码第五步-保存密码(第五步可以不调用)
    public static final String URL_MEMBER_ACCOUNT_MODIFY_PASSWORD_STEP5 = URL_CONTEXTPATH + "act=member_account&op=modify_password_step5";
    //检测是否设置了支付密码
    public static final String URL_MEMBER_ACCOUNT_GET_PAYPWD_INFO = URL_CONTEXTPATH + "act=member_account&op=get_paypwd_info";
    //更改支付密码第二步-发短信(没有第一步)
    public static final String URL_MEMBER_ACCOUNT_MODIFY_PAYPWD_STEP2 = URL_CONTEXTPATH + "act=member_account&op=modify_paypwd_step2";
    //更改支付密码第三步-验证短信
    public static final String URL_MEMBER_ACCOUNT_MODIFY_PAYPWD_STEP3 = URL_CONTEXTPATH + "act=member_account&op=modify_paypwd_step3";
    //更改支付密码第五步-保存密码(第五步可以不调用)
    public static final String URL_MEMBER_ACCOUNT_MODIFY_PAYPWD_STEP5 = URL_CONTEXTPATH + "act=member_account&op=modify_paypwd_step5";
    //o2o商家分店地址列表
    public static final String URL_GOODS_STORE_O2O_ADDR = URL_CONTEXTPATH + "act=goods&op=store_o2o_addr";


    //支付宝原生支付接口--获取实物订单信息
    public static final String URL_ALIPAY_NATIVE_GOODS = URL_CONTEXTPATH + "act=member_payment&op=alipay_native_pay";
    //支付宝原生支付---获取签名sign
    public static final String URL_GET_SIGN = URL_CONTEXTPATH + "act=member_payment&op=alipay_native_sign";
    //支付宝原生支付接口--获取实物订单信息
    public static final String URL_ALIPAY_NATIVE_Virtual = URL_CONTEXTPATH + "act=member_payment&op=alipay_native_vr_pay";

    //我的收藏--店铺收藏列表
    public static final String URL_STORE_FAV_LIST = URL_CONTEXTPATH + "act=member_favorites_store&op=favorites_list";
    //我的收藏--店铺收藏删除
    public static final String URL_STORE_DELETE = URL_CONTEXTPATH + "act=member_favorites_store&op=favorites_del";
    //我的收藏--店铺新增收藏
    public static final String URL_STORE_ADD_FAVORITES = URL_CONTEXTPATH + "act=member_favorites_store&op=favorites_add";
    //店铺详情
    public static final String URL_STORE_INFO = URL_CONTEXTPATH + "act=store&op=store_info";
    //店铺排行榜
    public static final String URL_STORE_RANKING = URL_CONTEXTPATH + "act=store&op=store_goods_rank";
    //店铺商品上新
    public static final String URL_STORE_GOODS_NEW = URL_CONTEXTPATH + "act=store&op=store_new_goods";
    //店铺促销活动
    public static final String URL_STORE_ACTIVITIES = URL_CONTEXTPATH + "act=store&op=store_promotion";
    //店铺商品分类
    public static final String URL_STORE_GOODS_CLASS = URL_CONTEXTPATH + "act=store&op=store_goods_class";
    //店铺商品列表
    public static final String URL_STORE_GOODS_LIST = URL_CONTEXTPATH + "act=store&op=store_goods";
    //店铺评分
    public static final String URL_STORE_CREDIT = URL_CONTEXTPATH + "act=store&op=store_credit";
    //店铺简介
    public static final String URL_STORE_INTRODUCE = URL_CONTEXTPATH + "act=store&op=store_intro";
    //店铺代金券模板
    public static final String URL_STORE_VOUCHER = URL_CONTEXTPATH + "act=voucher&op=voucher_tpl_list";

    //商品秒杀
    public static final String URL_GOODS_SPIKE = URL_CONTEXTPATH + "act=spike&op=spike_list";
    //商品品牌秒杀
    public static final String URL_BRAND_SPIKE = URL_CONTEXTPATH + "act=spike&op=spike_brands";
    //闪购列表
    public static final String URL_FLASH_LIST = URL_CONTEXTPATH +  "act=flash&op=flash_list";
    //闪购详情
    public static final String URL_FLASH_DETAIL = URL_CONTEXTPATH +  "act=flash&op=flash_detial";
    //详情页门店地址
    public static final String URL_STORE_ADDRESS = URL_CONTEXTPATH + "act=goods&op=store_chain_addr";
    //积分中心首页
    public static final String URL_CREDITS_CENTER = URL_CONTEXTPATH;
    //积分商品详情接口
    public static final String URL_CREDITS_DETAIL= URL_CONTEXTPATH +"act=points&op=pgoods_detial";
    //积分商品兑换记录接口
    public static final String URL_CREDITS_DETAIL_RECORD = URL_CONTEXTPATH + "act=points&op=pgoods_order";
    //积分中心兑换代金券接口
    public static final String URL_CREDITS_EXCHANGE_VOUCHER = URL_CONTEXTPATH + "act=member_points&op=voucherexchange";
    //积分中心兑换红包接口
    public static final String URL_CREDITS_EXCHANGE_REDPACKET = URL_CONTEXTPATH + "act=member_points&op=redpacketexchange";
    //积分明细接口
    public static final String URL_CREDITS_CREDITS_LOG = URL_CONTEXTPATH + "act=member_points&op=pointslog";
    public static final String URL_CREDITS_CREDITS_LOG_HEAD = URL_CONTEXTPATH + "act=member_index&op=my_asset";
    //积分中心购物车接口
    public static final String URL_CREDITS_CART = URL_CONTEXTPATH + "act=member_points&op=prod_cart";


    /**
     * 评论界面
     */
    public static final String URL_ORDER_EVALUATE = URL_CONTEXTPATH + "act=member_evaluate&op=index";
    /**
     * 评价图片上传
     */
    public static final String URL_ORDER_EVALUATE_UPLOAD_IAMGE = URL_CONTEXTPATH + "act=sns_album&op=file_upload";
    /**
     * 评价提交
     */
    public static final String URL_ORDER_EVALUATE_COMMIT = URL_CONTEXTPATH + "act=member_evaluate&op=save";
    /**
     * 追加评价界面
     */
    public static final String URL_ORDER_EVALUATE_ADD = URL_CONTEXTPATH + "act=member_evaluate&op=again";
    /**
     * 追加评论提交
     */
    public static final String URL_ORDER_EVALUATE_ADD_COMMIT = URL_CONTEXTPATH + "act=member_evaluate&op=save_again";

    /**
     * 支付密码修改绑定手机
     */
    public static final String URl_UNDIND_PASSWORD = URL_CONTEXTPATH + "act=member_account&op=check_paypwd";
    /*下单第二步-F码验证*/
    public static final String URL_CHECK_FCODE = URL_CONTEXTPATH + "act=member_buy&op=check_fcode";
    /*列表页模块（get）*/
    public static final String URL_VIDEO_LIST_INDEX = URL_CONTEXTPATH + "act=video_list&op=index";
    /*资讯详情页模块（get）*/
    public static final String URL_ZHIXUN_CONTENT = URL_CONTEXTPATH + "act=news&op=index";
    /*点播详情页面*/
    public static final String URL_DIANBO_CONTENT = URL_CONTEXTPATH + "act=demand&op=index";
    /*获取cate 列表*/
    public static final String URL_CATE_LIST = URL_CONTEXTPATH + "act=video_list&op=cate_list";
    /*点播详情页模块（get）*/
    public static final String URL_RECOMMEND_GOODS_DETAIL = URL_CONTEXTPATH + "act=demand&op=recommend_goods_detail";
    /*直播审核模块（post）*/
    public static final String URL_MEMBER_MOVIE = URL_CONTEXTPATH + "act=member_movie&op=index";
    /*上传图片（身份证正、反面照）(post)*/
    public static final String URL_FILE_UPLOAD = URL_CONTEXTPATH + "act=member_movie&op=file_upload";
    /*申请直播模块（post）*/
    public static final String URL_VERIFY_MOVIE = URL_CONTEXTPATH + "act=member_movie&op=verify_movie";
    /*获取分销商品列表(post) */
    public static final String URL_DISTRI_GOODS = URL_CONTEXTPATH + "act=member_distri&op=distri_goods";
    /*删除分销商品(post)*/
    public static final String URL_DROP_GOODS = URL_CONTEXTPATH + "act=member_distri&op=drop_goods";
    /*获取分销订单列表(post)*/
    public static final String URL_DISTRI_ORDER = URL_CONTEXTPATH + "act=member_distri&op=distri_order";
    /*获取分销结算列表(post)*/
    public static final String URL_DISTRI_BILL = URL_CONTEXTPATH + "act=member_distri&op=distri_bill";
    /*获取分销提现列表(post)*/
    public static final String URL_DISTRI_CASH = URL_CONTEXTPATH + "act=member_distri&op=distri_cash";
    /*提现记录详情(post)*/
    public static final String URL_CASH_INFO = URL_CONTEXTPATH + "act=member_distri&op=cash_info";
    /*获取分销提现列表(post)*/
    public static final String URL_CASH_APPLY = URL_CONTEXTPATH + "act=member_distri&op=cash_apply";
    /*正在直播（post）*/
    public static final String URL_MOVIE_SEND = URL_CONTEXTPATH + "act=member_movie&op=movie_send";
    /*退出直播（get）*/
    public static final String URL_MOVIE_LOGOUT = URL_CONTEXTPATH + "act=member_movie&op=movie_logout";
    /*进入直播观看（post）*/
    public static final String URL_LIVE_INFO = URL_CONTEXTPATH + "act=member_live&op=live_info";
    /*获取直播观看用户列表（post）*/
    public static final String URL_LIVE_MEMBER = URL_CONTEXTPATH + "act=member_live&op=live_member";
    /*获取聊天列表*/
    public static final String URL_GET_CHAT = URL_CONTEXTPATH + "act=member_live&op=get_chat";
    /*发送聊天信息*/
    public static final String URL_SEND_CHAT = URL_CONTEXTPATH + "act=member_live&op=send_chat";
    /*退出直播观看*/
    public static final String URL_LIVE_CLOSE = URL_CONTEXTPATH + "act=member_live&op=live_close";
    /*获取直播商品规格*/
    public static final String URL_GET_SPEC = URL_CONTEXTPATH + "act=member_live&op=get_spec";
    /*获取直播商品信息*/
    public static final String URL_GET_GOODS = URL_CONTEXTPATH + "act=member_live&op=get_goods";
    /*大转盘抽奖*/
    public static final String URL_LOTTERY_DIAL = WAP_URL + "lottery_dial.html";
}
