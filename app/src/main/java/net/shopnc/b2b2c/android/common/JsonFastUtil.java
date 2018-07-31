package net.shopnc.b2b2c.android.common;

import com.alibaba.fastjson.JSON;

/**
 * Created by wj on 2016/1/5.
 *
 */
public class JsonFastUtil {
    public static <T> T fromJsonFast(String jsonStr, Class<T> type){
        return  JSON.parseObject(jsonStr, type);
    }


}
