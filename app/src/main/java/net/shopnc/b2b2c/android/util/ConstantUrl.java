package net.shopnc.b2b2c.android.util;

import net.shopnc.b2b2c.BuildConfig;

/**
 * @Description 网络请求地址
 * @Author qyf
 * <p>
 * Created 2016/4/6 15:55.
 */
public class ConstantUrl {

    public static final String URL_API = BuildConfig.API;
    public static final String URL_IM_API = BuildConfig.IM_API;
    public static final String URL_WAP = BuildConfig.WAP;
    public static final String URL_WEB = BuildConfig.WEB;

    /**
     * 用户登录
     */
    public static final String URL_LOGIN = URL_API + "/login";
    //注销登陆
    public static final String URL_LOGOUT = URL_API + "/logout";

    public static final String URL_GETTOTALNUM = URL_API + "/member/index";
    /**
     * 用户注册
     */
    public static final String URL_REGISTER = URL_API + "/register/general";
    /**
     * 查看用户注册协议
     */
    public static final String URL_REGISTER_AGREEMENT = URL_API + "/register/agreement";
    /**
     * QQ登录
     */
    public static final String URL_LOGIN_QQ = URL_API + "/loginconnect/umeng/qq";
    /**
     * 微信登录
     */
    public static final String URL_LOGIN_WX = URL_API + "/loginconnect/umeng/weixin";
    /**
     * 微博登陆
     */
    public static final String URL_LOGIN_WEIBO = URL_API + "/login/weibo";
    /**
     * 第三方登录开关
     */
    public static final String URL_LOGIN_STATE = URL_API + "/loginconnect/state";

    public static final String URL_FAV_GOODS = URL_API + "/member/goods/favorite/add";
    public static final String URL_FAV_GOODS_DEL = URL_API + "/member/goods/favorite/delete";

    /**
     * 商品分类
     */
    public static final String URL_GOODS_CATEGORY = URL_API + "/category/list";
    /**
     * 品牌推荐
     */
    public static final String URL_BRAND_RECOMMEND = URL_API + "/brand/recommend";

    public static final String URL_WHETHER_FAV_GOODS = URL_API + "/goods/favorite/member/exist";
    /**
     * 收获地址列表
     */
    public static final String URL_ADDRESS_LIST = URL_API + "/member/address/list";
    /**
     * 添加收货地址
     */
    public static final String URL_ADDRESS_ADD = URL_API + "/member/address/add";
    /**
     * 删除收获地址
     */
    public static final String URL_ADDRESS_DELETE = URL_API + "/member/address/delete";
    /**
     * 收货地址详细信息
     */
    public static final String URL_ADDRESS_INFO = URL_API + "/member/address/info";
    /**
     * 编辑收货地址
     */
    public static final String URL_ADDRESS_EDIT = URL_API + "/member/address/edit";
    /**
     * 地区列表
     */
    public static final String URL_AREA_LIST = URL_API + "/area/list";
    /**
     * 店铺详情
     */
    public static final String URL_STORE_INFO = URL_API + "/store/index";

    /**
     * 店铺商品分类
     */
    public static final String URL_STORE_CATEGORY = URL_API + "/store/category";
    /**
     * 店铺内商品搜索
     */
    public static final String URL_STORE_SEARCH_GOODS = URL_API + "/store/search/goods";
    /**
     * 店铺收藏
     */
    public static final String URL_STORE_FAVORITE_ADD = URL_API + "/member/store/favorite/add";
    /**
     * 取消收藏店铺
     */
    public static final String URL_STORE_FAVORITE_DEL = URL_API + "/member/store/favorite/delete";

    //会员收藏店铺
    public static final String URL_MEMBER_STORE_FAVORITE_LIST = URL_API + "/member/store/favorite/list";

    //会员收藏商品
    public static final String URL_MEMBER_GOODS_FAVORITE_KIST = URL_API + "/member/goods/favorite/list";

    /**
     * WAP品牌图标
     */
    public static final String WAP_BRAND_ICON = URL_API + "images/degault.png";

    //搜索商品
    public static final String URL_SEARCH_GOOD = URL_API + "/search";
    //获取商品详情
    public static final String URL_GOOD_DETAILS = URL_API + "/goods/";

    /**
     * 添加购物车
     */
    public static final String URL_ADD_CART = URL_API + "/cart/add";
    /**
     * 购物车列表
     */
    public static final String URL_CART_LIST = URL_API + "/cart/list";
    /**
     *
     */
    public static final String URL_CART_EDIT = URL_API + "/cart/edit";
    /**
     * 删除购物车中spu
     */
    public static final String URL_CART_DEL_SPU = URL_API + "/member/cart/delete/spu";
    /**
     * 删除购物车中的sku
     */
    public static final String URL_CART_DEL_SKU = URL_API + "/member/cart/delete/sku";
    /**
     * 批量删除购物车中的sku
     */
    public static final String URL_CART_BATCH_SEK = URL_API + "/cart/del/batch/sku";
    /**
     * 购买第一步
     */
    public static final String URL_BUY_STEP1 = URL_API + "/member/buy/step1";
    /**
     * 购买第一步
     */
    public static final String URL_BUY_BOOK_STEP1 = URL_API + "/member/buy/book/step1";
    /**
     * 获取发票信息
     */
    public static final String URL_BUY_INVOICE_LIST = URL_API + "/member/buy/invoice/list";

    /**
     * 首页
     */
    public static final String URL_INDEX = URL_API + "/";
    /**
     * 专题
     */
    public static final String URL_INDEX_SPECIAL = URL_API + "/special";

    //更改地址后，重新获取运费等信息
    public static final String URL_ADDRESS_FREIGHT = URL_API + "/member/buy/calc/freight";

    //定金：更改地址后，重新获取运费等信息
    public static final String URL_BOOK_ADDRESS_FREIGHT = URL_API + "/member/buy/book/calc/freight";

    //选择优惠之后  获取可用红包列表
    public static final String URL_REDPACKAGE_LIST = URL_API + "/member/buy/red/package/list";
    /**
     * 店铺代金券
     */
    public static final String URL_VOUCHER_LIST = URL_API + "/store/voucher";

    public static final String URL_USER_GOT_VOUCHER = URL_API + "/member/voucher/template/free";
    /**
     * 代金券领取
     */
    public static final String URL_VOUCHER_GET = URL_API + "/member/voucher/receive/free";

    //确认订单页提交订单
    public static final String URL_SAVE_ORDER = URL_API + "/member/buy/step2";

    //定金：确认订单页提交订单
    public static final String URL_BOOK_SAVE_ORDER = URL_API + "/member/buy/book/save";

    //获取订单列表
    public static final String URL_ORDER_LIST = URL_API + "/member/orders/list";

    //取消订单
    public static final String URL_ORDER_CANCEL = URL_API + "/member/orders/cancel";

    //确认订单
    public static final String URL_ORDER_SURE = URL_API + "/member/orders/receive";

    //订单详情
    public static final String URL_ORDER_DETAILS = URL_API + "/member/orders/info";

    //订单物流信息
    public static final String URL_ORDER_SHIP = URL_API + "/member/orders/ship/search";

    //获取可用支付方式列表
    public static final String URL_PAY_LIST = URL_API + "/member/buy/show/payment";

    //订单支付-支付宝支付
    public static final String URL_PAY_ALIBABA = URL_API + "/member/buy/pay/app/alipay";

    //订单支付-微信支付
    public static final String URL_PAY_WXPAY = URL_API + "/member/buy/pay/app/wxpay";

    //商品详情-选择地址-获得运费
    public static final String URL_GOODS_FREIGHT = URL_API + "/goods/calc/freight";

    //每日推荐搜索
    public static final String URL_SEARCH_HOT = URL_API + "/search/hot/keyword";

    public static final String URL_DELETE_FAV_GOODS = URL_API + "/member/goods/favorite/delete";

    //每日搜索
    public static final String URL_SEARCH_DEFAULT = URL_API + "/search/default/keyword";

    //会员预存款充值列表
    public static final String URL_RECHARGE_GET = URL_API + "/member/predeposit/recharge/list";

    //会员预存款提现列表
    public static final String URL_WITHDRAW_DEPOSIT = URL_API + "/member/predeposit/cash/list";

    //会员预存款提现详情
    public static final String URL_WITHDRAW_DEPOSIT_CASH_INFO = URL_API + "/member/predeposit/cash/info";

    //会员预存款提现申请
    public static final String URL_WITHDRAW_DEPOSIT_CASH_SAVE = URL_API + "/member/predeposit/cash/save";

    //会员预存款接口
    public static final String URL_DEPOSIT_GET = URL_API + "/member/predeposit/log";

    //会员优惠券
    public static final String URL_GET_VOUCHER = URL_API + "/member/voucher/list";

    //会员财产
    public static final String URL_DEPOSIT_REMAIN = URL_API + "/member/asset";

    //会员红包
    public static final String URL_REDPACKAGE_GETLIST = URL_API + "/member/redpackage/list";

    //会员卡密领取红包
    public static final String URL_RED_PACKAGE_GET = URL_API + "/member/redpackage/receive/pwd";

    //会员积分
    public static final String URL_MEMBER_POINTS = URL_API + "/member/points/log";

    //会员经验
    public static final String URL_MEMBER_EXPERINCE = URL_API + "/member/exppoints/log";

    //添加我的足迹
    public static final String URL_MINE_FOOTPRINT = URL_API + "/goodsbrowse/add";

    //清空我的足迹
    public static final String URL_CLEAR_FOOTPRINT = URL_API + "/member/goodsbrowse/clearall";

    //商品评价
    public static final String URL_EVALUATE = URL_API + "/goods/evaluate/queryGoodsEvaluate";

    //我的足迹列表
    public static final String URL_MEMBER_HISTORY = URL_API + "/member/goodsbrowse/list";

    //图片验证码标示
    public static final String URL_DEPOSIT_CAPTCHAKEY = URL_API + "/captcha/makecaptchakey";

    //图片验证码图片
    public static final String URL_PHNOE_CAPTCHER_IMAGE = URL_API + "/captcha/makecaptcha";

    //短信动态码+图片验证码
    public static final String URL_PHONE_AND_KEY = URL_API + "/loginconnect/smscode/send";

    //短信动态码
    public static final String URL_PHONE_KEY = URL_API + "/loginconnect/smscode/send/simple";

    //手机注册
    public static final String URL_MOBILE_REGISTER = URL_API + "/loginconnect/mobile/register";

    //手机登录
    public static final String URL_MOBILE_LOGIN = URL_API + "/loginconnect/mobile/login";

    //卡密优惠券
    public static final String URL_VOUCHER_PWD = URL_API + "/member/voucher/receive/pwd";
    //
    public static final String URL_STORE_INTRODUCE = URL_API + "/store/introduce";

    public static final String WAP_GOODS_URL = URL_WAP + "/tmpl/product_detail.html?commonId=";

    public static final String URL_INVOICE_LIST = URL_API + "/member/invoice/list";

    public static final String URL_INVOICE_DEL = URL_API + "/member/invoice/del";

    public static final String URL_INVOICE_CONTENT_LIST = URL_API + "/member/invoice/content/list";

    public static final String URL_INVOICE_ADD = URL_API + "/member/invoice/add";
    /*订单评价接口*/
    /**
     * 评价商品|店铺保存 调用接口(post)
     */
    public static final String URL_EVALUATE_ADD = URL_API + "/member/evaluate/add";
    //  图片上传（退款退货凭证、评价追评）
    public static final String URL_IMAGE_UPLOAD = URL_API + "/member/image/upload";

    //评价店铺|商品
    public static final String URL_EVALUATE_INFO = URL_API + "/member/evaluate/addPage";

    //追加评价请求数据
    public static final String URL_EVALUATE_ADD_INFO = URL_API + "/member/evaluate/addAgainPage";

    public static final String URL_EVALUATE_ADD_SAVE = URL_API + "/member/evaluate/addAgain";

    //单个商品退款申请
    public static final String URL_SINGLE_GOOD_REFUND = URL_API + "/member/refund/goods";

    //单个商品退款申请保存
    public static final String URL_SINGLE_GOOD_FEFUND_SAVE = URL_API + "/member/refund/goods/save";

    //单个商品退货申请
    public static final String URL_SINGLE_GOOD_RETURN = URL_API + "/member/return/add";

    //单个商品退货申请保存
    public static final String URL_SINGLE_GOOD_RETURN_SAVE = URL_API + "/member/return/save";

    //全部退款申请
    public static final String URL_ORDER_FEFUND = URL_API + "/member/refund/all";

    //全部退款申请保存
    public static final String URL_ORDER_FEFUND_SAVE = URL_API + "/member/refund/all/save";

    //退款记录列表
    public static final String URL_ORDER_REFUND_MONEY_LIST = URL_API + "/member/refund/list";

    //退货记录列表
    public static final String URL_ORDER_RETURN_GOOD_LIST = URL_API + "/member/return/list";

    //退款详情
    public static final String URL_ORDER_REFUND_INFO = URL_API + "/member/refund/info";

    //退货详情
    public static final String URL_ORDER_RETURN_INFO = URL_API + "/member/return/info";

    //退货发货请求
    public static final String URL_ORDER_RETURN_GOOD_SEND = URL_API + "/member/return/ship";

    //退货发货保存
    public static final String URL_ORDER_RETURN_GOOD_SEND_SAVE = URL_API + "/member/return/ship/save";

    //退货延迟收货保存
    public static final String URL_ORDER_RETURN_GOOD_SEND_DELAY = URL_API + "/member/return/ship/delay";

    //获取与某个联系人的聊天记录
    public static final String URL_IM_HISTORY_MESSAGE = URL_IM_API + "/his_msg";

    //添加商品咨询id
    public static final String URL_IM_ADD_GOOD = URL_IM_API + "/link_man_add";

    public static final String URL_IM_GET_LINK_INFO = URL_IM_API + "/get_link_info";

    //获得用户的联系人信息
    public static final String URL_IM_SELLER_LIST = URL_IM_API + "/get_link_list";

    //商家默认头像
    public static final String URL_CATEGORY_ICON_DEFAULT = URL_WAP + "/images/degault.png";

    //商家默认头像
    public static final String URL_SELLER_IMG_DEFAULT = URL_WAP + "/images/avatar_seller.gif";

    //删除联系人
    public static final String URL_IM_SELLER_DELETE = URL_IM_API + "/del_link_man";

    //版本更新
    public static final String URL_UPAPP_VERSION = URL_API + "/app/android";

    //web端商品链接
    public static final String URL_WEB_GOODS = URL_WEB + "/goods/";

    //店铺搜索
    public static final String URL_SEARCH_STORE = URL_API + "/search/store";

    //到货通知
    public static final String URL_ARRIVAL_NOTICE = URL_API + "/member/arrival/notice";

    //搜索框提示联想词
    public static final String URL_SUGGEST_TERM = URL_API + "/search/suggest.json";

    //IM未读消息数量
    public static final String URL_IM_MSG_UNRED = URL_IM_API + "/get_unread_msg_count";

    //我申请的试用列表
    public static final String URL_MY_TRY_LIST = URL_API + "/member/trys/list";

    //试用商品列表
    public static final String URL_TRY_GOOD_LIST = URL_API + "/trys/list";

    //试用商品分类列表
    public static final String URL_TRY_GOOD_SORT_LIST = URL_API + "/trys/category/list";

    //试用商品详情
    public static final String URL_TRY_GOOD_DETAILS = URL_API + "/trys/info";

    //申请试用
    public static final String URL_TRY_REQUEST = URL_API + "/trys/apply";

    //试用报告列表
    public static final String URL_TRY_REPORT_LIST = URL_API + "/trys/report/list";

    //试用报告详情
    public static final String URL_TRY_REPORT_INFO = URL_API + "/trys/report/info";

    //试用优惠券列表
    public static final String URL_TRY_VOUCHER_LIST = URL_API + "/member/trys/voucher/list";

    //提交本地购物车数据
    public static final String UEL_ADD_CART_LOCAL = URL_API + "/cart/add/from/app";

    //推广商品添加记录
    public static final String URL_DISTRIBUTION_GOODS_ADD = URL_API + "/distribution/goods/add";

    //批量提交推广记录
    public static final String URL_DISTRIBUTION_MERGE_GOODS = URL_API + "/distributor/merge/goods";

    //购物车商品数量
    public static final String URL_CART_AMOUNT = ConstantUrl.URL_API + "/cart/count";

    //推广信息
    public static final String DISTRIBUTOR_JOIN_INFO = ConstantUrl.URL_API + "/member/distributor/join/info";

    //查询会员未读站内消息数量
    public static final String URL_UNREAD_MESSAGE_COUNT = ConstantUrl.URL_API + "/get/unread/message/count";

    //投诉列表
    public static final String URL_MEMBER_COMPLAIN_LIST = ConstantUrl.URL_API + "/member/complain/list";

    //投诉详情
    public static final String URL_MEMBER_COMPLAIN_INFO = ConstantUrl.URL_API + "/member/complain/info";

    //投诉对话列表
    public static final String URL_MEMBER_COMPLAIN_TALK_LIST = ConstantUrl.URL_API + "/member/complain/talk/list";

    //投诉发起页面
    public static final String URL_MEMBER_COMPLAIN_ADD = ConstantUrl.URL_API + "/member/complain/add";

    //投诉保存
    public static final String URL_MEMBER_COMPLAIN_ADD_SAVE = ConstantUrl.URL_API + "/member/complain/add/save";

    //撤销投诉
    public static final String URL_MEMBER_COMPLAIN_CLOSE = ConstantUrl.URL_API + "/member/complain/close";

    //补充投诉凭证
    public static final String URL_MEMBER_COMPLAIN_UPDATE_IMAGE = ConstantUrl.URL_API + "/member/complain/update/image";

    //发送对话
    public static final String URL_MEMBER_COMPLAIN_TALK_SAVE = ConstantUrl.URL_API + "/member/complain/talk/save";

    //提交仲裁
    public static final String URL_MEMBER_COMPLAIN_STOP_TALK = ConstantUrl.URL_API + "/member/complain/stop/talk";

}
