package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 消保Bean
 *
 * @author dqw
 * @Time 2015/7/21
 */
public class ContractInfo {

    private String id;
    private String name;

    public ContractInfo() {
    }

    public ContractInfo(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public static ArrayList<ContractInfo> newInstanceList(String json) {
        ArrayList<ContractInfo> contractList = new ArrayList<ContractInfo>();

        try {
            JSONArray arr = new JSONArray(json);
            int size = null == arr ? 0 : arr.length();
            for (int i = 0; i < size; i++) {
                JSONObject obj = arr.getJSONObject(i);
                String id = obj.optString("id");
                String name = obj.optString("name");
                contractList.add(new ContractInfo(id, name));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return contractList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ContractInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
