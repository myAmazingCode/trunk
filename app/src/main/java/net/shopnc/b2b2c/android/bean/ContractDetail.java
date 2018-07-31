package net.shopnc.b2b2c.android.bean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 消保详情Bean
 *
 * @author dqw
 * @Time 2015/8/3
 */
public class ContractDetail {

    private String ctiId;
    private String ctiName;
    private String ctiIcon;

    public ContractDetail() {
    }

    public ContractDetail(String ctiId, String ctiName, String ctiIcon) {
        this.ctiId = ctiId;
        this.ctiName = ctiName;
        this.ctiIcon = ctiIcon;
    }

    public static ArrayList<ContractDetail> newInstanceList(String json) {
        ArrayList<ContractDetail> contractList = new ArrayList<ContractDetail>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> iter = jsonObject.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                JSONObject obj = jsonObject.getJSONObject(key);
                String ctiId = obj.optString("cti_id");
                String ctiName = obj.optString("cti_name");
                String ctiIcon = obj.optString("cti_icon_url_60");
                contractList.add(new ContractDetail(ctiId, ctiName, ctiIcon));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return contractList;
    }

    public String getCtiId() {
        return ctiId;
    }

    public void setCtiId(String ctiId) {
        this.ctiId = ctiId;
    }

    public String getCtiName() {
        return ctiName;
    }

    public void setCtiName(String ctiName) {
        this.ctiName = ctiName;
    }

    public String getCtiIcon() {
        return ctiIcon;
    }

    public void setCtiIcon(String ctiIcon) {
        this.ctiIcon = ctiIcon;
    }

    @Override
    public String toString() {
        return "ContractDetail{" +
                "ctiId='" + ctiId + '\'' +
                ", ctiName='" + ctiName + '\'' +
                ", ctiIcon='" + ctiIcon + '\'' +
                '}';
    }
}
