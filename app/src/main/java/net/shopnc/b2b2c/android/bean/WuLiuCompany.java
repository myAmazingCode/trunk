package net.shopnc.b2b2c.android.bean;

/**
 * Created by qyf on 2016/1/21.
 */
public class WuLiuCompany {
    private String express_id;
    private String express_name;

    public WuLiuCompany() {
    }

    public WuLiuCompany(String express_id, String express_name) {
        this.express_id = express_id;
        this.express_name = express_name;
    }

    public String getExpress_id() {
        return express_id;
    }

    public void setExpress_id(String express_id) {
        this.express_id = express_id;
    }

    public String getExpress_name() {
        return express_name;
    }

    public void setExpress_name(String express_name) {
        this.express_name = express_name;
    }
}
