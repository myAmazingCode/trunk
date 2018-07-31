package net.shopnc.b2b2c.android.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：Jie on 2016/6/7 10:09
 */

public class GoodsEvaluate implements Serializable {


    /**
     * var avgGoodsEval : NSNumber!
     * var commonId : String!
     * var days : String!
     * var evalCount : NSNumber!
     * var evaluateAgainTime : NSNumber!
     * var evaluateAgainTimeStr : NSString!
     * var evaluateContent1 : String!  //好评
     * var evaluateContent2 : String!  //追加评论
     * var evaluateId : String!
     * var evaluateTime : NSNumber!
     * var evaluateTimeStr : String!
     * var goodsFullSpecs : String!
     * var goodsId : String!
     * var goodsImage : String!
     * var goodsName : String!
     * <p/>
     * var image1FullList : [String]!
     * var image1List : [String]!
     * var image2FullList : [String]!
     * var image2List : [String]!
     * <p/>
     * var images1 : String!
     * var images2 : String!
     * var memberHeadUrl : String!
     * var memberName : String!
     * var scoreTitle : String!
     * var scores : String!
     */

    private String evaluateId;
    private String commonId;
    private String days;
    private long evalCount;
    private long evaluateAgainTime;
    private String evaluateAgainTimeStr;
    private String evaluateContent1;
    private String evaluateContent2;
    private long evaluateTime;
    private String evaluateTimeStr;
    private String goodsFullSpecs;
    private String goodsId;
    private String goodsImage;
    private String goodsName;
    private List<String> image1FullList;
    private List<String> image1List;
    private List<String> image2FullList;
    private List<String> image2List;
    private String images1;
    private String images2;
    private String memberHeadUrl;
    private String memberName;
    private String scoreTitle;
    private String scores;

    public String getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(String evaluateId) {
        this.evaluateId = evaluateId;
    }

    public String getCommonId() {
        return commonId;
    }

    public void setCommonId(String commonId) {
        this.commonId = commonId;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public long getEvalCount() {
        return evalCount;
    }

    public void setEvalCount(int evalCount) {
        this.evalCount = evalCount;
    }

    public long getEvaluateAgainTime() {
        return evaluateAgainTime;
    }

    public void setEvaluateAgainTime(int evaluateAgainTime) {
        this.evaluateAgainTime = evaluateAgainTime;
    }

    public String getEvaluateAgainTimeStr() {
        return evaluateAgainTimeStr.split(" ")[0];
    }

    public void setEvaluateAgainTimeStr(String evaluateAgainTimeStr) {
        this.evaluateAgainTimeStr = evaluateAgainTimeStr;
    }

    public String getEvaluateContent1() {
        return evaluateContent1;
    }

    public void setEvaluateContent1(String evaluateContent1) {
        this.evaluateContent1 = evaluateContent1;
    }

    public String getEvaluateContent2() {
        return evaluateContent2;
    }

    public void setEvaluateContent2(String evaluateContent2) {
        this.evaluateContent2 = evaluateContent2;
    }

    public long getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(int evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    public String getEvaluateTimeStr() {
        return evaluateTimeStr.split(" ")[0];
    }

    public void setEvaluateTimeStr(String evaluateTimeStr) {
        this.evaluateTimeStr = evaluateTimeStr;
    }

    public String getGoodsFullSpecs() {
        return goodsFullSpecs;
    }

    public void setGoodsFullSpecs(String goodsFullSpecs) {
        this.goodsFullSpecs = goodsFullSpecs;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public List<String> getImage1FullList() {
        return image1FullList;
    }

    public void setImage1FullList(List<String> image1FullList) {
        this.image1FullList = image1FullList;
    }

    public List<String> getImage1List() {
        return image1List;
    }

    public void setImage1List(List<String> image1List) {
        this.image1List = image1List;
    }

    public List<String> getImage2FullList() {
        return image2FullList;
    }

    public void setImage2FullList(List<String> image2FullList) {
        this.image2FullList = image2FullList;
    }

    public List<String> getImage2List() {
        return image2List;
    }

    public void setImage2List(List<String> image2List) {
        this.image2List = image2List;
    }

    public String getImages1() {
        return images1;
    }

    public void setImages1(String images1) {
        this.images1 = images1;
    }

    public String getImages2() {
        return images2;
    }

    public void setImages2(String images2) {
        this.images2 = images2;
    }

    public String getMemberHeadUrl() {
        return memberHeadUrl;
    }

    public void setMemberHeadUrl(String memberHeadUrl) {
        this.memberHeadUrl = memberHeadUrl;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getScoreTitle() {
        return scoreTitle;
    }

    public void setScoreTitle(String scoreTitle) {
        this.scoreTitle = scoreTitle;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }
}
