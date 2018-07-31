package net.shopnc.b2b2c.android.bean;

/**
 * Created by xws on 2017/3/13.
 */

public class LoginState {


    /**
     * code : 200
     * datas : {"pc_qq":"0","pc_sn":"0","connect_qq":"0","connect_sn":"0","connect_wx":"1","connect_wap_wx":"0","connect_sms_reg":"1","connect_sms_lgn":"1","connect_sms_psd":"1"}
     */

    private int code;
    /**
     * pc_qq : 0
     * pc_sn : 0
     * connect_qq : 0
     * connect_sn : 0
     * connect_wx : 1
     * connect_wap_wx : 0
     * connect_sms_reg : 1
     * connect_sms_lgn : 1
     * connect_sms_psd : 1
     */

    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        private String pc_qq;
        private String pc_sn;
        private String connect_qq;
        private String connect_sn;
        private String connect_wx;
        private String connect_wap_wx;
        private String connect_sms_reg;
        private String connect_sms_lgn;
        private String connect_sms_psd;

        public String getPc_qq() {
            return pc_qq;
        }

        public void setPc_qq(String pc_qq) {
            this.pc_qq = pc_qq;
        }

        public String getPc_sn() {
            return pc_sn;
        }

        public void setPc_sn(String pc_sn) {
            this.pc_sn = pc_sn;
        }

        public String getConnect_qq() {
            return connect_qq;
        }

        public void setConnect_qq(String connect_qq) {
            this.connect_qq = connect_qq;
        }

        public String getConnect_sn() {
            return connect_sn;
        }

        public void setConnect_sn(String connect_sn) {
            this.connect_sn = connect_sn;
        }

        public String getConnect_wx() {
            return connect_wx;
        }

        public void setConnect_wx(String connect_wx) {
            this.connect_wx = connect_wx;
        }

        public String getConnect_wap_wx() {
            return connect_wap_wx;
        }

        public void setConnect_wap_wx(String connect_wap_wx) {
            this.connect_wap_wx = connect_wap_wx;
        }

        public String getConnect_sms_reg() {
            return connect_sms_reg;
        }

        public void setConnect_sms_reg(String connect_sms_reg) {
            this.connect_sms_reg = connect_sms_reg;
        }

        public String getConnect_sms_lgn() {
            return connect_sms_lgn;
        }

        public void setConnect_sms_lgn(String connect_sms_lgn) {
            this.connect_sms_lgn = connect_sms_lgn;
        }

        public String getConnect_sms_psd() {
            return connect_sms_psd;
        }

        public void setConnect_sms_psd(String connect_sms_psd) {
            this.connect_sms_psd = connect_sms_psd;
        }
    }
}
