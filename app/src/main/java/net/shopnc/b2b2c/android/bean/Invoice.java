package net.shopnc.b2b2c.android.bean;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/7/21 17:27.
 */
public class Invoice {
    private int invoiceId;

    private int memberId;

    private int invoiceType;

    private String title;

    private String content;

    private String company;

    private String codeSn;

    private String registerAddress;

    private String registerPhone;

    private String bankName;

    private String bankAccount;

    private String receiverName;

    private String receiverPhone;

    private String receiverArea;

    private String receiverAddress;

    private String invoiceContent;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(int invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCodeSn() {
        return codeSn;
    }

    public void setCodeSn(String codeSn) {
        this.codeSn = codeSn;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getRegisterPhone() {
        return registerPhone;
    }

    public void setRegisterPhone(String registerPhone) {
        this.registerPhone = registerPhone;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverArea() {
        return receiverArea;
    }

    public void setReceiverArea(String receiverArea) {
        this.receiverArea = receiverArea;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", memberId=" + memberId +
                ", invoiceType=" + invoiceType +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", company='" + company + '\'' +
                ", codeSn='" + codeSn + '\'' +
                ", registerAddress='" + registerAddress + '\'' +
                ", registerPhone='" + registerPhone + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverArea='" + receiverArea + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", invoiceContent='" + invoiceContent + '\'' +
                '}';
    }
}
