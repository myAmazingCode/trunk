package net.shopnc.b2b2c.android.ui.buy;

/**
 * @Description 订单页面eventBus实体类
 * @Author qyf
 *
 * Created 2016/5/5 11:01.
 */
public class BuyStepBus {
    public static String ADDRESSID = "addressId";   //地址id
    public static String FLAG_STOREPRICE = "flag_storeprice";  //店铺价格标志
    public static String ALL = "all";    //  ->BuyStepPrice对象
    public static String STOREID = "storeId";
    public static String FLAG_STOREMSG = "flag_storemsg";     //店铺留言标志
    public static String STOREIDMSG = "storemsg";   //map  id，msg
    public static String INVOICE = "invoice";
    public static String PAYID = "payid";

    private String msg;
    private Object obj;

    public BuyStepBus(String msg) {
        this.msg = msg;
    }


    public BuyStepBus(String msg, Object obj) {
        this.msg = msg;
        this.obj = obj;
    }


    public String getMsg() {
        return msg;
    }

    public Object getObj() {
        return obj;
    }


    @Override
    public String toString() {
        return "BuyStepBus{" +
                "msg='" + msg + '\'' +
                ", obj=" + obj +
                '}';
    }
}
