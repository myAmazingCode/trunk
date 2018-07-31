package net.shopnc.b2b2c.android.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 商品评价Bean
 *
 * @author dqw
 * @Time 2015/8/4
 */
public class GoodsEvaluateInfo {
    private String gevalScores;
    private String gevalContent;
    private String gevalAddtimeDate;
    private String gevalExplain;
    private String gevalFromMemberName;
    private ArrayList<String> gevalImage240;
    private ArrayList<String> gevalImage1024;
    private String gevalContentAgain;
    private String gevalExplainAgain;
    private String gevalAddtimeAgainDate;
    private String memberAvatar;
    private ArrayList<String> gevalImageAgain240;
    private ArrayList<String> gevalImageAgain1024;

    public GoodsEvaluateInfo(String gevalScores, String gevalContent, String gevalAddtimeDate, String gevalExplain, String gevalFromMemberName, ArrayList<String> gevalImage240, ArrayList<String> gevalImage1024, String gevalContentAgain, String gevalExplainAgain, String gevalAddtimeAgainDate, String memberAvatar, ArrayList<String> gevalImageAgain240, ArrayList<String> gevalImageAgain1024) {
        this.gevalScores = gevalScores;
        this.gevalContent = gevalContent;
        this.gevalAddtimeDate = gevalAddtimeDate;
        this.gevalExplain = gevalExplain;
        this.gevalFromMemberName = gevalFromMemberName;
        this.gevalImage240 = gevalImage240;
        this.gevalImage1024 = gevalImage1024;
        this.gevalContentAgain = gevalContentAgain;
        this.gevalExplainAgain = gevalExplainAgain;
        this.gevalAddtimeAgainDate = gevalAddtimeAgainDate;
        this.memberAvatar = memberAvatar;
        this.gevalImageAgain240 = gevalImageAgain240;
        this.gevalImageAgain1024 = gevalImageAgain1024;
    }

    public static ArrayList<GoodsEvaluateInfo> newInstanceList(String json) {
        ArrayList<GoodsEvaluateInfo> goodsEvaluateList = new ArrayList<GoodsEvaluateInfo>();
        try {
            JSONArray arr = new JSONArray(json);
            int size = null == arr ? 0 : arr.length();
            for (int i = 0; i < size; i++) {
                JSONObject obj = arr.getJSONObject(i);
                String gevalScores = obj.optString("geval_scores");
                String gevalContent = obj.optString("geval_content");
                String gevalAddtimeDate = obj.optString("geval_addtime_date");
                String gevalExplain = obj.optString("geval_explain");
                String gevalFromMemberName = obj.optString("geval_frommembername");
                String gevalContentAgain = obj.optString("geval_content_again");
                String gevalExplainAgain = obj.optString("geval_explain_again");
                String gevalAddtimeAgainDate = obj.optString("geval_addtime_again_date");
                String memberAvatar = obj.optString("member_avatar");
                ArrayList<String> gevalImage240 = getStringList(obj.optString("geval_image_240"));
                ArrayList<String> gevalImage1024 = getStringList(obj.optString("geval_image_1024"));
                ArrayList<String> gevalImageAgain240 = getStringList(obj.optString("geval_image_again_240"));
                ArrayList<String> gevalImageAgain1024 = getStringList(obj.optString("geval_image_again_1024"));
                goodsEvaluateList.add(new GoodsEvaluateInfo(gevalScores, gevalContent, gevalAddtimeDate, gevalExplain, gevalFromMemberName, gevalImage240, gevalImage1024, gevalContentAgain, gevalExplainAgain, gevalAddtimeAgainDate, memberAvatar, gevalImageAgain240, gevalImageAgain1024));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return goodsEvaluateList;
    }

    private static ArrayList<String> getStringList(String json) {
        ArrayList<String> list = null;
        try {
            JSONArray arr = new JSONArray(json);
            int size = null == arr ? 0 : arr.length();
            if (size > 0) {
                list = new ArrayList<String>();
                for (int i = 0; i < size; i++) {
                    list.add(arr.getString(i));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getGevalScores() {
        return gevalScores;
    }

    public void setGevalScores(String gevalScores) {
        this.gevalScores = gevalScores;
    }

    public String getGevalContent() {
        return gevalContent;
    }

    public void setGevalContent(String gevalContent) {
        this.gevalContent = gevalContent;
    }

    public String getGevalAddtimeDate() {
        return gevalAddtimeDate;
    }

    public void setGevalAddtimeDate(String gevalAddtimeDate) {
        this.gevalAddtimeDate = gevalAddtimeDate;
    }

    public String getGevalExplain() {
        return gevalExplain;
    }

    public void setGevalExplain(String gevalExplain) {
        this.gevalExplain = gevalExplain;
    }

    public String getGevalFromMemberName() {
        return gevalFromMemberName;
    }

    public void setGevalFromMemberName(String gevalFromMemberName) {
        this.gevalFromMemberName = gevalFromMemberName;
    }

    public ArrayList<String> getGevalImage240() {
        return gevalImage240;
    }

    public void setGevalImage240(ArrayList<String> gevalImage240) {
        this.gevalImage240 = gevalImage240;
    }

    public ArrayList<String> getGevalImage1024() {
        return gevalImage1024;
    }

    public void setGevalImage1024(ArrayList<String> gevalImage1024) {
        this.gevalImage1024 = gevalImage1024;
    }

    public String getGevalContentAgain() {
        return gevalContentAgain;
    }

    public void setGevalContentAgain(String gevalContentAgain) {
        this.gevalContentAgain = gevalContentAgain;
    }

    public String getGevalExplainAgain() {
        return gevalExplainAgain;
    }

    public void setGevalExplainAgain(String gevalExplainAgain) {
        this.gevalExplainAgain = gevalExplainAgain;
    }

    public String getGevalAddtimeAgainDate() {
        return gevalAddtimeAgainDate;
    }

    public void setGevalAddtimeAgainDate(String gevalAddtimeAgainDate) {
        this.gevalAddtimeAgainDate = gevalAddtimeAgainDate;
    }

    public String getMemberAvatar() {
        return memberAvatar;
    }

    public void setMemberAvatar(String memberAvatar) {
        this.memberAvatar = memberAvatar;
    }

    public ArrayList<String> getGevalImageAgain240() {
        return gevalImageAgain240;
    }

    public void setGevalImageAgain240(ArrayList<String> gevalImageAgain240) {
        this.gevalImageAgain240 = gevalImageAgain240;
    }

    public ArrayList<String> getGevalImageAgain1024() {
        return gevalImageAgain1024;
    }

    public void setGevalImageAgain1024(ArrayList<String> gevalImageAgain1024) {
        this.gevalImageAgain1024 = gevalImageAgain1024;
    }

    @Override
    public String toString() {
        return "GoodsEvaluateInfo{" +
                "gevalScores='" + gevalScores + '\'' +
                ", gevalContent='" + gevalContent + '\'' +
                ", gevalAddtimeDate='" + gevalAddtimeDate + '\'' +
                ", gevalExplain='" + gevalExplain + '\'' +
                ", gevalFromMemberName='" + gevalFromMemberName + '\'' +
                ", gevalImage240=" + gevalImage240 +
                ", gevalImage1024=" + gevalImage1024 +
                ", gevalContentAgain='" + gevalContentAgain + '\'' +
                ", gevalExplainAgain='" + gevalExplainAgain + '\'' +
                ", gevalAddtimeAgainDate='" + gevalAddtimeAgainDate + '\'' +
                ", memberAvatar='" + memberAvatar + '\'' +
                ", gevalImageAgain240=" + gevalImageAgain240 +
                ", gevalImageAgain1024=" + gevalImageAgain1024 +
                '}';
    }
}
