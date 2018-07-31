package net.shopnc.b2b2c.android.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by qyf on 2016/1/14.
 */
public class ReasonBean {
    private String reason_id;

    private String reason_info;

    public void setReason_id(String reason_id){
        this.reason_id = reason_id;
    }
    public String getReason_id(){
        return this.reason_id;
    }
    public void setReason_info(String reason_info){
        this.reason_info = reason_info;
    }
    public String getReason_info(){
        return this.reason_info;
    }


    public static ReasonBean getInstance(String json){
        ReasonBean bean = null;
        try {
            JSONObject obj = new JSONObject(json);
            if(obj.length()> 0){
                bean = new ReasonBean();
                String reason_id = obj.optString("reason_id");
                String reason_info = obj.optString("reason_info");
                bean.setReason_info(reason_info);
                bean.setReason_id(reason_id);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bean;
    }


}
