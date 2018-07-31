package net.shopnc.b2b2c.android.bean;

import net.shopnc.b2b2c.android.common.ShopHelper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/9/5 16:42.
 */
public class Conform implements Serializable{

    /**
     * 下单页免优惠的显示规则
     */
    private String shortRule;

    public String getShortRule() {
        return shortRule;
    }

    public void setShortRule(String shortRule) {
        this.shortRule = shortRule;
    }

    private String contentCartRule;
    /**
     * 满优惠编
     */
    private int conformId;
    /**
     * 满优惠名称
     */
    private String conformName;
    /**
     * 满优惠标签
     */
    private String conformTitle;
    /**
     * 满优惠标签最终
     */
    private String conformTileFinal;
    /**
     * 活动开始时间
     */
    private String startTime;
    /**
     * 活动结束时间
     */
    private String endTime;
    /**
     * 活动状态
     */
    private int conformState;
    /**
     * 金额限制
     */
    private BigDecimal limitAmount;
    /**
     * 减免金额
     */
    private BigDecimal conformPrice;
    /**
     * 是否包邮
     */
    private int isFreeFreight;
    /**
     * 排除地区
     */
    private String ruleOutAreaIds = "";
    /**
     * 排除地区
     */
    private String ruleOutAreaNames = "";
    /**
     * 优惠券模板编号
     */
    private int templateId;
    /**
     * 面额
     */
    private BigDecimal templatePrice = new BigDecimal(0);
    /**
     * 送赠品  1-是  0-否
     */
    private int isGift;
    /**
     * 满优惠赠品数组
     */
    private List<GoodGift> giftVoList;
    /**
     * 店铺编号
     */
    private int storeId;
    /**
     * 活动状态文字
     */
    private String conformStateText;
    /**
     * 活动完整规则
     */
    private String contentRule;

    public String getContentCartRule() {
        return contentCartRule;
    }

    public void setContentCartRule(String contentCartRule) {
        this.contentCartRule = contentCartRule;
    }

    public int getConformId() {
        return conformId;
    }

    public void setConformId(int conformId) {
        this.conformId = conformId;
    }

    public String getConformName() {
        return conformName;
    }

    public void setConformName(String conformName) {
        this.conformName = conformName;
    }

    public String getConformTitle() {
        return conformTitle;
    }

    public void setConformTitle(String conformTitle) {
        this.conformTitle = conformTitle;
    }

    public String getConformTileFinal() {
        return conformTileFinal;
    }

    public void setConformTileFinal(String conformTileFinal) {
        this.conformTileFinal = conformTileFinal;
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

    public int getConformState() {
        return conformState;
    }

    public void setConformState(int conformState) {
        this.conformState = conformState;
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

    public String getRuleOutAreaIds() {
        return ruleOutAreaIds;
    }

    public void setRuleOutAreaIds(String ruleOutAreaIds) {
        this.ruleOutAreaIds = ruleOutAreaIds;
    }

    public String getRuleOutAreaNames() {
        return ruleOutAreaNames;
    }

    public void setRuleOutAreaNames(String ruleOutAreaNames) {
        this.ruleOutAreaNames = ruleOutAreaNames;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public BigDecimal getTemplatePrice() {
        return templatePrice;
    }

    public void setTemplatePrice(BigDecimal templatePrice) {
        this.templatePrice = templatePrice;
    }

    public int getIsGift() {
        return isGift;
    }

    public void setIsGift(int isGift) {
        this.isGift = isGift;
    }

    public List<GoodGift> getGiftVoList() {
        return giftVoList;
    }

    public void setGiftVoList(List<GoodGift> giftVoList) {
        this.giftVoList = giftVoList;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getConformStateText() {
        return conformStateText;
    }

    public void setConformStateText(String conformStateText) {
        this.conformStateText = conformStateText;
    }

    public void setContentRule(String contentRule) {
        this.contentRule = contentRule;
    }

    public String getContentRule() {
        return contentRule;
    }

    public String getShowText() {
        String showText = "单笔订单满" + ShopHelper.getPriceString(limitAmount) + "元";
        if (conformPrice.compareTo(new BigDecimal(0)) == 1) {
            showText = showText + ",立减" + ShopHelper.getPriceString(conformPrice) + "元";
        }
        if (isFreeFreight == 1) {
            showText = showText + ",免邮费";
        }
        if (templateId > 0) {
            showText = showText + ",送面额" + ShopHelper.getPriceString(templatePrice) + "元优惠券";
        }
        if (isGift==1){
            showText = showText + ",送赠品";
        }

        return showText;
    }
}
