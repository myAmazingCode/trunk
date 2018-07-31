package net.shopnc.b2b2c.android.bean;

/**
 * Created by xws on 2017/4/26.
 */

public class GroupBean {


    /**
     * groupId : 139079
     * groupName : 412 开团后取消商品团购
     * groupTitle : 动标题是商
     * groupExplain : 活动描述： 412 开团后取消商品团购
     * startTime : 2017-04-12 16:27:27
     * endTime : 2017-04-29 16:27:29
     * groupRequireNum : 3
     * buyLimitNum : 1
     * storeId : 10583
     * groupJoinedNum : 0
     * groupOpenVirtual : 1
     * groupVirtualTime : 2017-04-20 19:46:04
     * groupGameRule : 每个商品都有单独购买价格和拼团价格，选择拼团购买进行商品下单，开团支付成功后获取转发链接，邀请好友参团，参团成员也可以将该团分享出去邀约更多的团员参团，在规定时间内邀请到相应人数支付购买则拼团成功，等待收货;未达到人数则团购失败，系统会自动退款到付款账户。
     * storeName :
     * groupState : 1
     * groupStateText : 进行中
     */

    private int groupId;
    private String groupName;
    private String groupTitle;
    private String groupExplain;
    private String startTime;
    private String endTime;
    private int groupRequireNum;
    private int buyLimitNum;
    private int storeId;
    private int groupJoinedNum;
    private int groupOpenVirtual;
    private String groupVirtualTime;
    private String groupGameRule;
    private String storeName;
    private int groupState;
    private String groupStateText;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public String getGroupExplain() {
        return groupExplain;
    }

    public void setGroupExplain(String groupExplain) {
        this.groupExplain = groupExplain;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getGroupRequireNum() {
        return groupRequireNum;
    }

    public void setGroupRequireNum(int groupRequireNum) {
        this.groupRequireNum = groupRequireNum;
    }

    public int getBuyLimitNum() {
        return buyLimitNum;
    }

    public void setBuyLimitNum(int buyLimitNum) {
        this.buyLimitNum = buyLimitNum;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getGroupJoinedNum() {
        return groupJoinedNum;
    }

    public void setGroupJoinedNum(int groupJoinedNum) {
        this.groupJoinedNum = groupJoinedNum;
    }

    public int getGroupOpenVirtual() {
        return groupOpenVirtual;
    }

    public void setGroupOpenVirtual(int groupOpenVirtual) {
        this.groupOpenVirtual = groupOpenVirtual;
    }

    public String getGroupVirtualTime() {
        return groupVirtualTime;
    }

    public void setGroupVirtualTime(String groupVirtualTime) {
        this.groupVirtualTime = groupVirtualTime;
    }

    public String getGroupGameRule() {
        return groupGameRule;
    }

    public void setGroupGameRule(String groupGameRule) {
        this.groupGameRule = groupGameRule;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getGroupState() {
        return groupState;
    }

    public void setGroupState(int groupState) {
        this.groupState = groupState;
    }

    public String getGroupStateText() {
        return groupStateText;
    }

    public void setGroupStateText(String groupStateText) {
        this.groupStateText = groupStateText;
    }
}
