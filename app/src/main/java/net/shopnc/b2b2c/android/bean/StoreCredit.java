package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 店铺信用
 *
 * @author  huting
 * @date 2016-01-07
 */
public class StoreCredit implements Serializable{
    private String text;//信用类型文字
    private String credit;//信用值
    private String percent; //同行水平百分比
    private String percent_class; //同行水平返回值有high、equal、low
    private String percent_text; //同行水平文字


    public StoreCredit(){}
    public StoreCredit(String text, String credit, String percent, String percent_class, String percent_text) {
        this.text = text;
        this.credit = credit;
        this.percent = percent;
        this.percent_class = percent_class;
        this.percent_text = percent_text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getPercent_class() {
        return percent_class;
    }

    public void setPercent_class(String percent_class) {
        this.percent_class = percent_class;
    }

    public String getPercent_text() {
        return percent_text;
    }

    public void setPercent_text(String percent_text) {
        this.percent_text = percent_text;
    }

    @Override
    public String toString() {
        return "StoreCredit{" +
                "text='" + text + '\'' +
                ", credit='" + credit + '\'' +
                ", percent='" + percent + '\'' +
                ", percent_class='" + percent_class + '\'' +
                ", percent_text='" + percent_text + '\'' +
                '}';
    }
}
