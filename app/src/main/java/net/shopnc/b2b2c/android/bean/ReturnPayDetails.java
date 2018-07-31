package net.shopnc.b2b2c.android.bean;

/**
 * Created by qyf on 2016/1/21.
 */
public class ReturnPayDetails {

    private String refund_code;
    private String pay_amount ;
    private String pd_amount ;
    private String rcb_amount ;

    public ReturnPayDetails() {
    }

    public ReturnPayDetails(String refund_code, String pay_amount, String pd_amount, String rcb_amount) {
        this.refund_code = refund_code;
        this.pay_amount = pay_amount;
        this.pd_amount = pd_amount;
        this.rcb_amount = rcb_amount;
    }

    public String getRefund_code() {
        return refund_code;
    }

    public void setRefund_code(String refund_code) {
        this.refund_code = refund_code;
    }

    public String getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(String pay_amount) {
        this.pay_amount = pay_amount;
    }

    public String getPd_amount() {
        return pd_amount;
    }

    public void setPd_amount(String pd_amount) {
        this.pd_amount = pd_amount;
    }

    public String getRcb_amount() {
        return rcb_amount;
    }

    public void setRcb_amount(String rcb_amount) {
        this.rcb_amount = rcb_amount;
    }
}
