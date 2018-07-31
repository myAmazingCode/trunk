package net.shopnc.b2b2c.android.ncinterface;

/**
 * Created by dqw on 2015/6/30.
 */
public interface INCOnAddressDialogConfirm {
    /**
     *
     * @param cityId       一级 id
     * @param areaId       二级 id
     * @param ThreeareaId      三级 id区
     * @param areaInfo     内容
     */
    void onAddressDialogConfirm(String cityId, String areaId,String ThreeareaId, String areaInfo);
}
