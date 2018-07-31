/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.model
 * FileNmae:Spec.java
 */
package net.shopnc.b2b2c.android.bean;

/**
 * 规格属性Bean
 *
 * @author KingKong·HE
 * @Time 2014年1月23日 下午4:34:44
 * @E-mail hjgang@bizpoer.com
 */
public class Spec {

    private String SpecID;
    private String SpecName;

    public Spec() {
    }

    public Spec(String specID, String specName) {
        super();
        SpecID = specID;
        SpecName = specName;
    }

    public String getSpecID() {
        return SpecID;
    }

    public void setSpecID(String specID) {
        SpecID = specID;
    }

    public String getSpecName() {
        return SpecName;
    }

    public void setSpecName(String specName) {
        SpecName = specName;
    }

    @Override
    public String toString() {
        return "Spec [SpecID=" + SpecID + ", SpecName=" + SpecName + "]";
    }
}
