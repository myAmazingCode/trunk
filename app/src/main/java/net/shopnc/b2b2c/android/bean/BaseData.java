package net.shopnc.b2b2c.android.bean;

/**
 * Created by qyf on 2016/3/11.
 */
public class BaseData {
    private int code;
    private String datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "BaseData{" +
                "code=" + code +
                ", datas='" + datas + '\'' +
                '}';
    }
}
