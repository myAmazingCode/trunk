package net.shopnc.b2b2c.android.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description 订单Vo
 * @Author qyf
 * <p>
 * Created 2016/5/11 18:53.
 */
public class OrdersVo {
    private List<OrdersGoodsVo> ordersGoodsVoList;

    private List<GoodGift> ordersGiftVoList;
    private List<String> invoiceList;

    public List<String> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<String> invoiceList) {
        this.invoiceList = invoiceList;
    }

    /**
     * 订单主键ID
     */
    private int ordersId;
    /**
     * 订单编号
     */
    private long ordersSn;
    /**
     * 订单状态
     */
    private int ordersState;
    /**
     * 订单状态
     */
    private String ordersStateName;
    /**
     * 订单来源
     */
    private String ordersFrom;
    /**
     * 支付单ID
     */
    private int payId;
    /**
     * 支付单号
     */
    private long paySn;
    /**
     * 店铺ID
     */
    private int storeId;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 店铺电话
     */
    private String storePhone;
    /**
     * 店铺地址
     */
    private String storeAddress;
    /**
     * QQ
     */
    private String storeQq;
    /**
     * 旺旺
     */
    private String storeWw;
    /**
     * 卖家账号
     */
    private String sellerName;
    /**
     * 会员ID
     */
    private int memberId;
    /**
     * 会员名
     */
    private String memberName;
    /**
     * 一级地区ID
     */
    private int receiverAreaId1;
    /**
     * 二级地区ID
     */
    private int receiverAreaId2;
    /**
     * 三级地区Id
     */
    private int receiverAreaId3;
    /**
     * 四级地区
     */
    private int receiverAreaId4;
    /**
     * 省市县(区)内容
     */
    private String receiverAreaInfo;
    /**
     * 收货人地址
     */
    private String receiverAddress;
    /**
     * 收货人电话
     */
    private String receiverPhone;
    /**
     * 收货人姓名
     */
    private String receiverName;
    /**
     * 买家留言
     */
    private String receiverMessage;
    /**
     * 下单时间
     */
    private String createTime;
    /**
     * 线上线下支付代码online/offline
     */
    private String paymentTypeCode;
    /**
     * 线上线下支付中文名
     */
    private String paymentTypeName;
    /**
     * 支付方式代码
     */
    private String paymentCode;
    /**
     * 外部交易号
     */
    private String outOrdersSn;
    /**
     * 支付方式名称
     */
    private String paymentName;
    /**
     * 支付时间
     */
    private String paymentTime;
    /**
     * 发货时间
     */
    private String sendTime;
    /**
     * 订单完成时间
     */
    private String finishTime;
    /**
     * 订单金额
     */
    private BigDecimal ordersAmount;
    /**
     * 定金
     */
    private List<BookOrderBean> ordersBookVoList;
    /**
     * 预存款支付金额
     */
    private BigDecimal predepositAmount;
    /**
     * 运费
     */
    private BigDecimal freightAmount;
    /**
     * 评价状态
     */
    private int evaluationState;
    /**
     * 追加评价状态
     */
    private int evaluationAppendState;
    /**
     * 评价时间
     */
    private String evaluationTime;
    /**
     * 发货单号
     */
    private String shipSn;
    /**
     * 快递公司
     */
    private String shipName;
    /**
     * 快递公司编码
     */
    private String shipCode;
    /**
     * 快递公司网址
     */
    private String shipUrl;
    /**
     * 发货备注
     */
    private String shipNote;
    /**
     * 自动收货时间
     */
    private String autoReceiveTime;
    /**
     * 发票内容
     */
    private String invoiceInfo;
    /**
     * 订单类型
     */
    private int ordersType;
    /**
     * 订单取消原因
     */
    private Integer cancelReason;
    /**
     * 订单取消原因
     */
    private String cancelReasonContent;
    /**
     * 关闭时间
     */
    private String cancelTime;
    /**
     * 自动关闭时间
     */
    private String autoCancelTime;
    /**
     * 自动取消周期
     */
    private int autoCancelCycle;
    /**
     * 订单关闭操作主体
     */
    private String cancelRole;
    /**
     * 订单收取佣金
     */
    private BigDecimal commissionAmount = new BigDecimal(0);
    /**
     * 订单列表是否显示操作
     */
    private int showMemberCancel = 0;
    private int showMemberPay = 0;
    private int showShipSearch = 0;
    private int showMemberReceive = 0;
    private int showStoreCancel = 0;
    private int showStoreModifyFreight = 0;
    private int showStoreSend = 0;
    private int showStoreSendModify = 0;
    private int showEvaluation = 0;
    private int showEvaluationAppend = 0;
    private int showStoreModifyReceiver = 0;
    private int showAdminCancel = 0;
    private int showAdminPay = 0;
    private int showMemberDelayReceive = 0;
    private int showMemberComplain = 0;
    // bycj [ 订单退款，全退 ]
    private int showMemberRefundAll = 0;

    /**
     * bycj [ 退款金额 ]
     */
    private BigDecimal refundAmount;

    /**
     * bycj [ 退款状态:0是无退款,1是部分退款,2是全部退款 ]
     */
    private int refundState;

    // bycj [ 是否显示退款退货中 ]
    private int showRefundWaiting = 0;

    // bycj [ 退款信息 ]
    private RefundItemVo refundItemVo;
    /**
     * 是否进行过延迟收货操作，买家只能点击一次 0-否/1-是
     */
    private int delayReceiveState;
    /**
     * 是否是管理员点击的收款
     */
    private int adminReceivePayState;
    /**
     * 红包金额
     */
    private BigDecimal redPackageAmount = new BigDecimal(0);
    /**
     * 红包Id
     */
    private Integer redPackageId = 0;
    /**
     * 优惠券面额
     */
    private BigDecimal voucherPrice;
    /**
     * 优惠券编码
     */
    private String voucherCode = "";
    /**
     * (满送减免)金额限制
     */
    private BigDecimal limitAmount = new BigDecimal(0);
    /**
     * (满送减免)减免金额
     */
    private BigDecimal conformPrice = new BigDecimal(0);
    /**
     * (满送减免)是否包邮
     */
    private int isFreeFreight = 0;
    /**
     * (满送减免)优惠券模板编号
     */
    private int templateId = 0;
    /**
     * 拼团ID
     */
    private int groupId = 0;
    /**
     * 开团ID
     */
    private int goId = 0;

    public List<OrdersGoodsVo> getOrdersGoodsVoList() {
        return ordersGoodsVoList;
    }

    public void setOrdersGoodsVoList(List<OrdersGoodsVo> ordersGoodsVoList) {
        this.ordersGoodsVoList = ordersGoodsVoList;
    }

    public List<GoodGift> getOrdersGiftVoList() {
        return ordersGiftVoList;
    }

    public void setOrdersGiftVoList(List<GoodGift> ordersGiftVoList) {
        this.ordersGiftVoList = ordersGiftVoList;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public long getOrdersSn() {
        return ordersSn;
    }

    public void setOrdersSn(long ordersSn) {
        this.ordersSn = ordersSn;
    }

    public int getOrdersState() {
        return ordersState;
    }

    public void setOrdersState(int ordersState) {
        this.ordersState = ordersState;
    }

    public String getOrdersStateName() {
        return ordersStateName;
    }

    public void setOrdersStateName(String ordersStateName) {
        this.ordersStateName = ordersStateName;
    }

    public String getOrdersFrom() {
        return ordersFrom;
    }

    public void setOrdersFrom(String ordersFrom) {
        this.ordersFrom = ordersFrom;
    }

    public int getPayId() {
        return payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

    public long getPaySn() {
        return paySn;
    }

    public void setPaySn(long paySn) {
        this.paySn = paySn;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreQq() {
        return storeQq;
    }

    public void setStoreQq(String storeQq) {
        this.storeQq = storeQq;
    }

    public String getStoreWw() {
        return storeWw;
    }

    public void setStoreWw(String storeWw) {
        this.storeWw = storeWw;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getReceiverAreaId1() {
        return receiverAreaId1;
    }

    public void setReceiverAreaId1(int receiverAreaId1) {
        this.receiverAreaId1 = receiverAreaId1;
    }

    public int getReceiverAreaId2() {
        return receiverAreaId2;
    }

    public void setReceiverAreaId2(int receiverAreaId2) {
        this.receiverAreaId2 = receiverAreaId2;
    }

    public int getReceiverAreaId3() {
        return receiverAreaId3;
    }

    public void setReceiverAreaId3(int receiverAreaId3) {
        this.receiverAreaId3 = receiverAreaId3;
    }

    public int getReceiverAreaId4() {
        return receiverAreaId4;
    }

    public void setReceiverAreaId4(int receiverAreaId4) {
        this.receiverAreaId4 = receiverAreaId4;
    }

    public String getReceiverAreaInfo() {
        return receiverAreaInfo;
    }

    public void setReceiverAreaInfo(String receiverAreaInfo) {
        this.receiverAreaInfo = receiverAreaInfo;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverMessage() {
        return receiverMessage;
    }

    public void setReceiverMessage(String receiverMessage) {
        this.receiverMessage = receiverMessage;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPaymentTypeCode() {
        return paymentTypeCode;
    }

    public void setPaymentTypeCode(String paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getOutOrdersSn() {
        return outOrdersSn;
    }

    public void setOutOrdersSn(String outOrdersSn) {
        this.outOrdersSn = outOrdersSn;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public BigDecimal getOrdersAmount() {
        return ordersAmount;
    }

    public void setOrdersAmount(BigDecimal ordersAmount) {
        this.ordersAmount = ordersAmount;
    }

    public BigDecimal getPredepositAmount() {
        return predepositAmount;
    }

    public void setPredepositAmount(BigDecimal predepositAmount) {
        this.predepositAmount = predepositAmount;
    }

    public BigDecimal getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(BigDecimal freightAmount) {
        this.freightAmount = freightAmount;
    }

    public int getEvaluationState() {
        return evaluationState;
    }

    public void setEvaluationState(int evaluationState) {
        this.evaluationState = evaluationState;
    }

    public int getEvaluationAppendState() {
        return evaluationAppendState;
    }

    public void setEvaluationAppendState(int evaluationAppendState) {
        this.evaluationAppendState = evaluationAppendState;
    }

    public String getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(String evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    public String getShipSn() {
        return shipSn;
    }

    public void setShipSn(String shipSn) {
        this.shipSn = shipSn;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipCode() {
        return shipCode;
    }

    public void setShipCode(String shipCode) {
        this.shipCode = shipCode;
    }

    public String getShipUrl() {
        return shipUrl;
    }

    public void setShipUrl(String shipUrl) {
        this.shipUrl = shipUrl;
    }

    public String getShipNote() {
        return shipNote;
    }

    public void setShipNote(String shipNote) {
        this.shipNote = shipNote;
    }

    public String getAutoReceiveTime() {
        return autoReceiveTime;
    }

    public void setAutoReceiveTime(String autoReceiveTime) {
        this.autoReceiveTime = autoReceiveTime;
    }

    public String getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setInvoiceInfo(String invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    public int getOrdersType() {
        return ordersType;
    }

    public void setOrdersType(int ordersType) {
        this.ordersType = ordersType;
    }

    public Integer getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(Integer cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getCancelReasonContent() {
        return cancelReasonContent;
    }

    public void setCancelReasonContent(String cancelReasonContent) {
        this.cancelReasonContent = cancelReasonContent;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getAutoCancelTime() {
        return autoCancelTime;
    }

    public void setAutoCancelTime(String autoCancelTime) {
        this.autoCancelTime = autoCancelTime;
    }

    public int getAutoCancelCycle() {
        return autoCancelCycle;
    }

    public void setAutoCancelCycle(int autoCancelCycle) {
        this.autoCancelCycle = autoCancelCycle;
    }

    public String getCancelRole() {
        return cancelRole;
    }

    public void setCancelRole(String cancelRole) {
        this.cancelRole = cancelRole;
    }

    public BigDecimal getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(BigDecimal commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public int getShowMemberCancel() {
        return showMemberCancel;
    }

    public void setShowMemberCancel(int showMemberCancel) {
        this.showMemberCancel = showMemberCancel;
    }

    public int getShowMemberPay() {
        return showMemberPay;
    }

    public void setShowMemberPay(int showMemberPay) {
        this.showMemberPay = showMemberPay;
    }

    public int getShowShipSearch() {
        return showShipSearch;
    }

    public void setShowShipSearch(int showShipSearch) {
        this.showShipSearch = showShipSearch;
    }

    public int getShowMemberReceive() {
        return showMemberReceive;
    }

    public void setShowMemberReceive(int showMemberReceive) {
        this.showMemberReceive = showMemberReceive;
    }

    public int getShowStoreCancel() {
        return showStoreCancel;
    }

    public void setShowStoreCancel(int showStoreCancel) {
        this.showStoreCancel = showStoreCancel;
    }

    public int getShowStoreModifyFreight() {
        return showStoreModifyFreight;
    }

    public void setShowStoreModifyFreight(int showStoreModifyFreight) {
        this.showStoreModifyFreight = showStoreModifyFreight;
    }

    public int getShowStoreSend() {
        return showStoreSend;
    }

    public void setShowStoreSend(int showStoreSend) {
        this.showStoreSend = showStoreSend;
    }

    public int getShowStoreSendModify() {
        return showStoreSendModify;
    }

    public void setShowStoreSendModify(int showStoreSendModify) {
        this.showStoreSendModify = showStoreSendModify;
    }

    public int getShowEvaluation() {
        return showEvaluation;
    }

    public void setShowEvaluation(int showEvaluation) {
        this.showEvaluation = showEvaluation;
    }

    public int getShowEvaluationAppend() {
        return showEvaluationAppend;
    }

    public void setShowEvaluationAppend(int showEvaluationAppend) {
        this.showEvaluationAppend = showEvaluationAppend;
    }

    public int getShowStoreModifyReceiver() {
        return showStoreModifyReceiver;
    }

    public void setShowStoreModifyReceiver(int showStoreModifyReceiver) {
        this.showStoreModifyReceiver = showStoreModifyReceiver;
    }

    public int getShowAdminCancel() {
        return showAdminCancel;
    }

    public void setShowAdminCancel(int showAdminCancel) {
        this.showAdminCancel = showAdminCancel;
    }

    public int getShowAdminPay() {
        return showAdminPay;
    }

    public void setShowAdminPay(int showAdminPay) {
        this.showAdminPay = showAdminPay;
    }

    public int getShowMemberDelayReceive() {
        return showMemberDelayReceive;
    }

    public void setShowMemberDelayReceive(int showMemberDelayReceive) {
        this.showMemberDelayReceive = showMemberDelayReceive;
    }

    public int getShowMemberComplain() {
        return showMemberComplain;
    }

    public void setShowMemberComplain(int showMemberComplain) {
        this.showMemberComplain = showMemberComplain;
    }

    public int getShowMemberRefundAll() {
        return showMemberRefundAll;
    }

    public void setShowMemberRefundAll(int showMemberRefundAll) {
        this.showMemberRefundAll = showMemberRefundAll;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public int getRefundState() {
        return refundState;
    }

    public void setRefundState(int refundState) {
        this.refundState = refundState;
    }

    public int getShowRefundWaiting() {
        return showRefundWaiting;
    }

    public void setShowRefundWaiting(int showRefundWaiting) {
        this.showRefundWaiting = showRefundWaiting;
    }

    public RefundItemVo getRefundItemVo() {
        return refundItemVo;
    }

    public void setRefundItemVo(RefundItemVo refundItemVo) {
        this.refundItemVo = refundItemVo;
    }

    public int getDelayReceiveState() {
        return delayReceiveState;
    }

    public void setDelayReceiveState(int delayReceiveState) {
        this.delayReceiveState = delayReceiveState;
    }

    public int getAdminReceivePayState() {
        return adminReceivePayState;
    }

    public void setAdminReceivePayState(int adminReceivePayState) {
        this.adminReceivePayState = adminReceivePayState;
    }

    public BigDecimal getRedPackageAmount() {
        return redPackageAmount;
    }

    public void setRedPackageAmount(BigDecimal redPackageAmount) {
        this.redPackageAmount = redPackageAmount;
    }

    public Integer getRedPackageId() {
        return redPackageId;
    }

    public void setRedPackageId(Integer redPackageId) {
        this.redPackageId = redPackageId;
    }

    public BigDecimal getVoucherPrice() {
        return voucherPrice;
    }

    public void setVoucherPrice(BigDecimal voucherPrice) {
        this.voucherPrice = voucherPrice;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }

    public BigDecimal getConformPrice() {
        return conformPrice;
    }

    public void setConformPrice(BigDecimal conformPrice) {
        this.conformPrice = conformPrice;
    }

    public int getIsFreeFreight() {
        return isFreeFreight;
    }

    public void setIsFreeFreight(int isFreeFreight) {
        this.isFreeFreight = isFreeFreight;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public List<BookOrderBean> getOrdersBookVoList() {
        return ordersBookVoList;
    }

    public void setOrdersBookVoList(List<BookOrderBean> ordersBookVoList) {
        this.ordersBookVoList = ordersBookVoList;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getGoId() {
        return goId;
    }

    public void setGoId(int goId) {
        this.goId = goId;
    }
}
