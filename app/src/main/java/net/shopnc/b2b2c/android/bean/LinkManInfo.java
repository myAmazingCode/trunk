package net.shopnc.b2b2c.android.bean;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/8/8 11:00.
 */
public class LinkManInfo {
    private int link_id;

    private int common_id;

    private String link_man_avatar;

    private int link_man_id;

    private String link_man_name;

    private int link_man_store_id;

    private String link_man_store_name;

    private int link_state;

    private int member_del;

    private int seller_del;

    private String user_avatar;

    private int user_id;

    private String user_name;

    private int user_type;

    public int getLink_id() {
        return link_id;
    }

    public void setLink_id(int link_id) {
        this.link_id = link_id;
    }

    public int getCommon_id() {
        return common_id;
    }

    public void setCommon_id(int common_id) {
        this.common_id = common_id;
    }

    public String getLink_man_avatar() {
        return link_man_avatar;
    }

    public void setLink_man_avatar(String link_man_avatar) {
        this.link_man_avatar = link_man_avatar;
    }

    public int getLink_man_id() {
        return link_man_id;
    }

    public void setLink_man_id(int link_man_id) {
        this.link_man_id = link_man_id;
    }

    public String getLink_man_name() {
        return link_man_name;
    }

    public void setLink_man_name(String link_man_name) {
        this.link_man_name = link_man_name;
    }

    public int getLink_man_store_id() {
        return link_man_store_id;
    }

    public void setLink_man_store_id(int link_man_store_id) {
        this.link_man_store_id = link_man_store_id;
    }

    public String getLink_man_store_name() {
        return link_man_store_name;
    }

    public void setLink_man_store_name(String link_man_store_name) {
        this.link_man_store_name = link_man_store_name;
    }

    public int getLink_state() {
        return link_state;
    }

    public void setLink_state(int link_state) {
        this.link_state = link_state;
    }

    public int getMember_del() {
        return member_del;
    }

    public void setMember_del(int member_del) {
        this.member_del = member_del;
    }

    public int getSeller_del() {
        return seller_del;
    }

    public void setSeller_del(int seller_del) {
        this.seller_del = seller_del;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    @Override
    public String toString() {
        return "LinkManInfo{" +
                "link_id=" + link_id +
                ", common_id=" + common_id +
                ", link_man_avatar='" + link_man_avatar + '\'' +
                ", link_man_id=" + link_man_id +
                ", link_man_name='" + link_man_name + '\'' +
                ", link_man_store_id=" + link_man_store_id +
                ", link_man_store_name='" + link_man_store_name + '\'' +
                ", link_state=" + link_state +
                ", member_del=" + member_del +
                ", seller_del=" + seller_del +
                ", user_avatar='" + user_avatar + '\'' +
                ", user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_type=" + user_type +
                '}';
    }
}
