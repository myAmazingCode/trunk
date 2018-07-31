package net.shopnc.b2b2c.android.ui.good;

/**
 * @Description 商品详情页面跳转
 * @Author qyf
 * <p>
 * Created 2016/4/25 10:36.
 */
public class GoodBusBean {
    public static final String REFRESH_CART_COUNT = "refresh_cart_count";//更新购物车商品数量
    public static final String EVALUATES_COUNT = "evaluates_count";//评价数量
    public static String GoodDelete = "goodDelete";  //购物清单列表页删除
    public static String SelectedGoods = "selectedGoods";//商品规格的轮播图
    public static String GoodEvaluate = "goodEvaluate";// 切换到评价页
    public static String GoodDetails = "goodDetails";
    public static String GoodBrowse = "goodBrowse";
    public static String GoodBrowseTurn = "goodBrowseTurn";
    public static String GoodNum = "goodNum";
    public static String GoodPreHashMap = "goodPreHashMap";

    private String flag;
    private Object obj;

    public GoodBusBean(String flag) {
        this.flag = flag;
    }

    public GoodBusBean(String flag, Object obj) {
        this.flag = flag;
        this.obj = obj;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
