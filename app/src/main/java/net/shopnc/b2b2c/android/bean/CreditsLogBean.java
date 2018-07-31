package net.shopnc.b2b2c.android.bean;

import java.util.List;

/**
 * Created by yuanshuo on 2017/10/19.
 */

public class CreditsLogBean {

    private List<LogListBean> log_list;

    public List<LogListBean> getLog_list() {
        return log_list;
    }

    public void setLog_list(List<LogListBean> log_list) {
        this.log_list = log_list;
    }

    public static class LogListBean {
        /**
         * addtimetext : 2017-10-19
         * pl_addtime : 1508394268
         * pl_desc : 392330561738268220
         * pl_id : 450
         * pl_memberid : 220
         * pl_membername : qiangudamotou
         * pl_points : -100
         * pl_stage : app
         * stagetext : 积分兑换
         */

        private String addtimetext;
        private String pl_addtime;
        private String pl_desc;
        private String pl_id;
        private String pl_memberid;
        private String pl_membername;
        private String pl_points;
        private String pl_stage;
        private String stagetext;

        public String getAddtimetext() {
            return addtimetext;
        }

        public void setAddtimetext(String addtimetext) {
            this.addtimetext = addtimetext;
        }

        public String getPl_addtime() {
            return pl_addtime;
        }

        public void setPl_addtime(String pl_addtime) {
            this.pl_addtime = pl_addtime;
        }

        public String getPl_desc() {
            return pl_desc;
        }

        public void setPl_desc(String pl_desc) {
            this.pl_desc = pl_desc;
        }

        public String getPl_id() {
            return pl_id;
        }

        public void setPl_id(String pl_id) {
            this.pl_id = pl_id;
        }

        public String getPl_memberid() {
            return pl_memberid;
        }

        public void setPl_memberid(String pl_memberid) {
            this.pl_memberid = pl_memberid;
        }

        public String getPl_membername() {
            return pl_membername;
        }

        public void setPl_membername(String pl_membername) {
            this.pl_membername = pl_membername;
        }

        public String getPl_points() {
            return pl_points;
        }

        public void setPl_points(String pl_points) {
            this.pl_points = pl_points;
        }

        public String getPl_stage() {
            return pl_stage;
        }

        public void setPl_stage(String pl_stage) {
            this.pl_stage = pl_stage;
        }

        public String getStagetext() {
            return stagetext;
        }

        public void setStagetext(String stagetext) {
            this.stagetext = stagetext;
        }
    }
}
