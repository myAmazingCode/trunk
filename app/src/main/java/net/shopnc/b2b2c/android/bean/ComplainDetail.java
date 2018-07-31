package net.shopnc.b2b2c.android.bean;


import java.util.List;

/**
 * @author lulei
 *         Created 2017/5/17 17:44
 * @copyright Copyright (c) 2007-2017 ShopNC Inc. All rights reserved.
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * <p>
 * 投诉详情bean
 */
public class ComplainDetail {

    /**
     * complainId : 1
     * ordersId : 73
     * ordersGoodsId : 264
     * goodsId : 502
     * commonId : 134
     * goodsName : BOLON暴龙太阳镜男警察墨镜开车驾驶司机镜偏光太阳眼镜潮BL2282
     * goodsImage : image/5c/51/5c51e3e4ce6a443c5bf14fc1c6cb5d91.jpg
     * goodsFullSpecs : 颜色：A09砂黑镜框 墨绿色镜片
     * accuserId : 4
     * accuserName : shopnc_lhf
     * accusedSellerId : 0
     * accusedId : 15
     * accusedName : feng的服装配饰
     * subjectId : 2
     * subjectTitle : 售后保障服务
     * accuserContent : 价格不对啊。
     * accuserImages : image/03/78/0378e84892468c2ad5e0ae715afc6e5f.jpg,image/ee/39/ee39ce5e2e2a8d0883f5a10ddfb3e94d.jpg
     * accuserTime : 2016-09-18 14:33:48
     * adminCheckTime : null
     * adminCheckName : null
     * adminCheckContent : null
     * accusedTime : null
     * accusedContent : null
     * accusedImages : null
     * adminConfirmTime : 2016-09-18 14:38:02
     * adminConfirmContent : 退款
     * adminConfirmName : admin
     * complainState : 50
     * complainStateName : 已完成
     * accuserImagesList : ["http://192.168.1.234/upload/image/03/78/0378e84892468c2ad5e0ae715afc6e5f.jpg","http://192.168.1.234/upload/image/ee/39/ee39ce5e2e2a8d0883f5a10ddfb3e94d.jpg"]
     * accusedImagesList : []
     * showMemberClose : 0
     * showAccuserUploadImages : 0
     * showAdminHandle : 0
     * showStoreHandle : 0
     * isComplainStateCancel : 0
     * isComplainStateNew : 0
     * isComplainStateAccess : 0
     * isComplainStateTalk : 0
     * isComplainStateStopTalk : 0
     * isComplainStateFinish : 1
     * showTalkAdd : 0
     * showTalkList : 1
     * imageSrc : http://192.168.1.234/upload/image/5c/51/5c51e3e4ce6a443c5bf14fc1c6cb5d91.jpg
     */

    private int complainId;
    private int ordersId;
    private int ordersGoodsId;
    private int goodsId;
    private int commonId;
    private String goodsName;
    private String goodsImage;
    private String goodsFullSpecs;
    private int accuserId;
    private String accuserName;
    private int accusedSellerId;
    private int accusedId;
    private String accusedName;
    private int subjectId;
    private String subjectTitle;
    private String accuserContent;
    private String accuserImages;
    private String accuserTime;
    private String adminCheckTime;
    private String adminCheckName;
    private String adminCheckContent;
    private String accusedTime;
    private String accusedContent;
    private String accusedImages;
    private String adminConfirmTime;
    private String adminConfirmContent;
    private String adminConfirmName;
    private int complainState;
    private String complainStateName;
    private int showMemberClose;
    private int showAccuserUploadImages;
    private int showAdminHandle;
    private int showStoreHandle;
    private int isComplainStateCancel;
    private int isComplainStateNew;
    private int isComplainStateAccess;
    private int isComplainStateTalk;
    private int isComplainStateStopTalk;
    private int isComplainStateFinish;
    private int showTalkAdd;
    private int showTalkList;
    private String imageSrc;
    private List<String> accuserImagesList;
    private List<String> accusedImagesList;

    public int getComplainId() {
        return complainId;
    }

    public void setComplainId(int complainId) {
        this.complainId = complainId;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public int getOrdersGoodsId() {
        return ordersGoodsId;
    }

    public void setOrdersGoodsId(int ordersGoodsId) {
        this.ordersGoodsId = ordersGoodsId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getCommonId() {
        return commonId;
    }

    public void setCommonId(int commonId) {
        this.commonId = commonId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsFullSpecs() {
        return goodsFullSpecs;
    }

    public void setGoodsFullSpecs(String goodsFullSpecs) {
        this.goodsFullSpecs = goodsFullSpecs;
    }

    public int getAccuserId() {
        return accuserId;
    }

    public void setAccuserId(int accuserId) {
        this.accuserId = accuserId;
    }

    public String getAccuserName() {
        return accuserName;
    }

    public void setAccuserName(String accuserName) {
        this.accuserName = accuserName;
    }

    public int getAccusedSellerId() {
        return accusedSellerId;
    }

    public void setAccusedSellerId(int accusedSellerId) {
        this.accusedSellerId = accusedSellerId;
    }

    public int getAccusedId() {
        return accusedId;
    }

    public void setAccusedId(int accusedId) {
        this.accusedId = accusedId;
    }

    public String getAccusedName() {
        return accusedName;
    }

    public void setAccusedName(String accusedName) {
        this.accusedName = accusedName;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getAccuserContent() {
        return accuserContent;
    }

    public void setAccuserContent(String accuserContent) {
        this.accuserContent = accuserContent;
    }

    public String getAccuserImages() {
        return accuserImages;
    }

    public void setAccuserImages(String accuserImages) {
        this.accuserImages = accuserImages;
    }

    public String getAccuserTime() {
        return accuserTime;
    }

    public void setAccuserTime(String accuserTime) {
        this.accuserTime = accuserTime;
    }

    public String getAccusedContent() {
        return accusedContent;
    }

    public void setAccusedContent(String accusedContent) {
        this.accusedContent = accusedContent;
    }


    public String getAdminConfirmTime() {
        return adminConfirmTime;
    }

    public void setAdminConfirmTime(String adminConfirmTime) {
        this.adminConfirmTime = adminConfirmTime;
    }

    public String getAdminConfirmContent() {
        return adminConfirmContent;
    }

    public void setAdminConfirmContent(String adminConfirmContent) {
        this.adminConfirmContent = adminConfirmContent;
    }

    public String getAdminConfirmName() {
        return adminConfirmName;
    }

    public void setAdminConfirmName(String adminConfirmName) {
        this.adminConfirmName = adminConfirmName;
    }

    public int getComplainState() {
        return complainState;
    }

    public void setComplainState(int complainState) {
        this.complainState = complainState;
    }

    public String getComplainStateName() {
        return complainStateName;
    }

    public void setComplainStateName(String complainStateName) {
        this.complainStateName = complainStateName;
    }

    public int getShowMemberClose() {
        return showMemberClose;
    }

    public void setShowMemberClose(int showMemberClose) {
        this.showMemberClose = showMemberClose;
    }

    public int getShowAccuserUploadImages() {
        return showAccuserUploadImages;
    }

    public void setShowAccuserUploadImages(int showAccuserUploadImages) {
        this.showAccuserUploadImages = showAccuserUploadImages;
    }

    public int getShowAdminHandle() {
        return showAdminHandle;
    }

    public void setShowAdminHandle(int showAdminHandle) {
        this.showAdminHandle = showAdminHandle;
    }

    public int getShowStoreHandle() {
        return showStoreHandle;
    }

    public void setShowStoreHandle(int showStoreHandle) {
        this.showStoreHandle = showStoreHandle;
    }

    public int getIsComplainStateCancel() {
        return isComplainStateCancel;
    }

    public void setIsComplainStateCancel(int isComplainStateCancel) {
        this.isComplainStateCancel = isComplainStateCancel;
    }

    public int getIsComplainStateNew() {
        return isComplainStateNew;
    }

    public void setIsComplainStateNew(int isComplainStateNew) {
        this.isComplainStateNew = isComplainStateNew;
    }

    public int getIsComplainStateAccess() {
        return isComplainStateAccess;
    }

    public void setIsComplainStateAccess(int isComplainStateAccess) {
        this.isComplainStateAccess = isComplainStateAccess;
    }

    public int getIsComplainStateTalk() {
        return isComplainStateTalk;
    }

    public void setIsComplainStateTalk(int isComplainStateTalk) {
        this.isComplainStateTalk = isComplainStateTalk;
    }

    public int getIsComplainStateStopTalk() {
        return isComplainStateStopTalk;
    }

    public void setIsComplainStateStopTalk(int isComplainStateStopTalk) {
        this.isComplainStateStopTalk = isComplainStateStopTalk;
    }

    public int getIsComplainStateFinish() {
        return isComplainStateFinish;
    }

    public void setIsComplainStateFinish(int isComplainStateFinish) {
        this.isComplainStateFinish = isComplainStateFinish;
    }

    public int getShowTalkAdd() {
        return showTalkAdd;
    }

    public void setShowTalkAdd(int showTalkAdd) {
        this.showTalkAdd = showTalkAdd;
    }

    public int getShowTalkList() {
        return showTalkList;
    }

    public void setShowTalkList(int showTalkList) {
        this.showTalkList = showTalkList;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public List<String> getAccuserImagesList() {
        return accuserImagesList;
    }

    public void setAccuserImagesList(List<String> accuserImagesList) {
        this.accuserImagesList = accuserImagesList;
    }

    public String getAdminCheckTime() {
        return adminCheckTime;
    }

    public void setAdminCheckTime(String adminCheckTime) {
        this.adminCheckTime = adminCheckTime;
    }

    public String getAdminCheckName() {
        return adminCheckName;
    }

    public void setAdminCheckName(String adminCheckName) {
        this.adminCheckName = adminCheckName;
    }

    public String getAdminCheckContent() {
        return adminCheckContent;
    }

    public void setAdminCheckContent(String adminCheckContent) {
        this.adminCheckContent = adminCheckContent;
    }

    public String getAccusedTime() {
        return accusedTime;
    }

    public void setAccusedTime(String accusedTime) {
        this.accusedTime = accusedTime;
    }

    public String getAccusedImages() {
        return accusedImages;
    }

    public void setAccusedImages(String accusedImages) {
        this.accusedImages = accusedImages;
    }

    public List<String> getAccusedImagesList() {
        return accusedImagesList;
    }

    public void setAccusedImagesList(List<String> accusedImagesList) {
        this.accusedImagesList = accusedImagesList;
    }
}
