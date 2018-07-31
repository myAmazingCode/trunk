package net.shopnc.b2b2c.android.bean;

/**
 * @author lulei
 *         Created 2017/5/18 18:28
 * @copyright Copyright (c) 2007-2017 ShopNC Inc. All rights reserved.
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * 投诉对话bean
 */
public class TalkBean {

    /**
     * talkId : 8
     * complainId : 10
     * userId : 4
     * talkUser : shopnc_lhf
     * talkType : 1
     * talkContent : 那这个应给怎么解决呢？
     * talkTime : 2017-05-18 17:29:25
     * disableState : 0
     * talkRole : 买家
     * talkDate : 2017/05/18
     */

    private int talkId;
    private int complainId;
    private int userId;
    private String talkUser;
    private int talkType;
    private String talkContent;
    private String talkTime;
    private int disableState;
    private String talkRole;
    private String talkDate;

    public int getTalkId() {
        return talkId;
    }

    public void setTalkId(int talkId) {
        this.talkId = talkId;
    }

    public int getComplainId() {
        return complainId;
    }

    public void setComplainId(int complainId) {
        this.complainId = complainId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTalkUser() {
        return talkUser;
    }

    public void setTalkUser(String talkUser) {
        this.talkUser = talkUser;
    }

    public int getTalkType() {
        return talkType;
    }

    public void setTalkType(int talkType) {
        this.talkType = talkType;
    }

    public String getTalkContent() {
        return talkContent;
    }

    public void setTalkContent(String talkContent) {
        this.talkContent = talkContent;
    }

    public String getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(String talkTime) {
        this.talkTime = talkTime;
    }

    public int getDisableState() {
        return disableState;
    }

    public void setDisableState(int disableState) {
        this.disableState = disableState;
    }

    public String getTalkRole() {
        return talkRole;
    }

    public void setTalkRole(String talkRole) {
        this.talkRole = talkRole;
    }

    public String getTalkDate() {
        return talkDate;
    }

    public void setTalkDate(String talkDate) {
        this.talkDate = talkDate;
    }
}
