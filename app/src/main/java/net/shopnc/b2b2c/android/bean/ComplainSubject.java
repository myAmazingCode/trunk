package net.shopnc.b2b2c.android.bean;

/**
 * @author lulei
 *         Created 2017/5/18 14:41
 * @copyright Copyright (c) 2007-2017 ShopNC Inc. All rights reserved.
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * <p>
 * 投诉主题bean
 */
public class ComplainSubject {

    private int subjectId;//主题Id
    private String title;//投诉主题

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
