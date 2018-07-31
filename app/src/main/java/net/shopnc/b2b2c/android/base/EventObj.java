package net.shopnc.b2b2c.android.base;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/7/27 14:33.
 */
public class EventObj {
    public static String REDPACKAGEGETSUCCESS="get";
    public static String ORDERREFUNDSENDDELAY="orderDelay";
    public static String IMMESSAGESENDSUCCESS="imMesageSendSuccess";
    public static String IMMESSAGEGETSUCCESS="imMessageGetSuccess";

    public static String CARTDATA="cartData";
    public static String CARTREFRESH="cartRefresh";
    public static String TRYINDEX="tryIndex";

    private String flag;
    private Object obj;

    public EventObj(String flag) {
        this.flag = flag;
    }

    public EventObj(String flag, Object obj) {
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
