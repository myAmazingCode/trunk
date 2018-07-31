package net.shopnc.b2b2c.android.common;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * gson的日期时间对象与时间毫秒值之间的转换器
 * @author qiujy
 */
public class DateConvert implements JsonSerializer<Date>, JsonDeserializer<Date> {
	//把Date类型数据转换成JSON中的数据
	@Override
	public JsonElement serialize(Date date, Type arg1, JsonSerializationContext arg2) {
		return new JsonPrimitive(date.getTime());
	}
	
	//把JSON中的数据转换成Date类型
	@Override
	public Date deserialize(JsonElement ele, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		return new Date(ele.getAsLong());
	}
}
