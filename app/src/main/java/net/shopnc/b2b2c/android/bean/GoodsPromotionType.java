package net.shopnc.b2b2c.android.bean;

/**
 * @Description
 * @Author qyf
 *
 * Created 2016/4/20 18:56.
 */
public class GoodsPromotionType {
    public static final int GENERAL = 0;
    public static final String GENERAL_TEXT = "";
    public static final int DISCOUNT = 1;
    public static final String DISCOUNT_TEXT = "限时折扣";
    public static final String getText(int type) {
        switch(type) {
            case DISCOUNT:
                return DISCOUNT_TEXT;
            default:
                return GENERAL_TEXT;
        }
    }
}
