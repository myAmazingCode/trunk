package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;

/**
 * 作者：Jie on 2016/6/16 09:50
 */
public class SortString implements Serializable {


    private String keyword;

    public SortString(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
