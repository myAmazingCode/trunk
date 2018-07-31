package net.shopnc.b2b2c.android.bean;

/**
 * @Description 保障服务
 * @Author qyf
 *
 * Created 2016/4/25 16:29.
 */
public class ContractVo {

    private int ctId;
    private int ctStoreid;
    private String ctStorename;
    private int ctItemid;
    private int ctAuditstate;
    private String ctAuditstateStr;
    private int ctJoinstate;
    private String ctJoinstateStr;
    private String ctCost;//保证金余额
    private int ctClosestate;
    private String ctClosestateStr;
    private int ctQuitstate;
    private String ctQuitstateStr;
    private String ctState;
    private String ctItemname;
    private String cause;

    private String desc;
    private String icon;
    private String ctiCost;//所需保证金
    private String iconUrl;

    private String ctDescurl;

    public String getCtDescurl() {
        return ctDescurl;
    }
    public void setCtDescurl(String ctDescurl) {
        this.ctDescurl = ctDescurl;
    }
    public String getIconUrl() {
        return iconUrl;
    }
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
    public String getCtiCost() {
        return ctiCost;
    }
    public void setCtiCost(String ctiCost) {
        this.ctiCost = ctiCost;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getCause() {
        return cause;
    }
    public void setCause(String cause) {
        this.cause = cause;
    }
    public String getCtAuditstateStr() {
        return ctAuditstateStr;
    }
    public void setCtAuditstateStr(String ctAuditstateStr) {
        this.ctAuditstateStr = ctAuditstateStr;
    }
    public String getCtJoinstateStr() {
        return ctJoinstateStr;
    }
    public void setCtJoinstateStr(String ctJoinstateStr) {
        this.ctJoinstateStr = ctJoinstateStr;
    }
    public String getCtClosestateStr() {
        return ctClosestateStr;
    }
    public void setCtClosestateStr(String ctClosestateStr) {
        this.ctClosestateStr = ctClosestateStr;
    }
    public String getCtQuitstateStr() {
        return ctQuitstateStr;
    }
    public void setCtQuitstateStr(String ctQuitstateStr) {
        this.ctQuitstateStr = ctQuitstateStr;
    }
    public String getCtItemname() {
        return ctItemname;
    }
    public void setCtItemname(String ctItemname) {
        this.ctItemname = ctItemname;
    }
    public int getCtId() {
        return ctId;
    }
    public void setCtId(int ctId) {
        this.ctId = ctId;
    }
    public int getCtStoreid() {
        return ctStoreid;
    }
    public void setCtStoreid(int ctStoreid) {
        this.ctStoreid = ctStoreid;
    }
    public String getCtStorename() {
        return ctStorename;
    }
    public void setCtStorename(String ctStorename) {
        this.ctStorename = ctStorename;
    }
    public int getCtItemid() {
        return ctItemid;
    }
    public void setCtItemid(int ctItemid) {
        this.ctItemid = ctItemid;
    }
    public int getCtAuditstate() {
        return ctAuditstate;
    }
    public void setCtAuditstate(int ctAuditstate) {
        this.ctAuditstate = ctAuditstate;
    }
    public int getCtJoinstate() {
        return ctJoinstate;
    }
    public void setCtJoinstate(int ctJoinstate) {
        this.ctJoinstate = ctJoinstate;
    }
    public String getCtCost() {
        return ctCost;
    }
    public void setCtCost(String ctCost) {
        this.ctCost = ctCost;
    }
    public int getCtClosestate() {
        return ctClosestate;
    }
    public void setCtClosestate(int ctClosestate) {
        this.ctClosestate = ctClosestate;
    }
    public int getCtQuitstate() {
        return ctQuitstate;
    }
    public void setCtQuitstate(int ctQuitstate) {
        this.ctQuitstate = ctQuitstate;
    }
    public String getCtState() {
        return ctState;
    }
    public void setCtState(String ctState) {
        this.ctState = ctState;
    }
}

