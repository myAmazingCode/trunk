package net.shopnc.b2b2c.android.common;

import java.util.ArrayList;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.SmiliesList;

/**
 * 记录表情图片数据
 * @author KingKong-HE
 * @Time 2015-2-5
 * @Email KingKong@QQ.COM
 */
public final class SmiliesData {
	
	public static final ArrayList<SmiliesList> smiliesLists = new ArrayList<SmiliesList>();

	static {
		smiliesLists.add(new SmiliesList("微笑",":smile:",R.drawable.smile));
		smiliesLists.add(new SmiliesList("难过",":sad:",R.drawable.sad));
		smiliesLists.add(new SmiliesList("呲牙",":biggrin:",R.drawable.biggrin));
		smiliesLists.add(new SmiliesList("大哭",":cry:",R.drawable.cry));
		smiliesLists.add(new SmiliesList("发怒",":huffy:",R.drawable.huffy));
		smiliesLists.add(new SmiliesList("惊讶",":shocked:",R.drawable.shocked));
		smiliesLists.add(new SmiliesList("调皮",":tongue:",R.drawable.tongue));
		smiliesLists.add(new SmiliesList("害羞",":shy:",R.drawable.shy));
		smiliesLists.add(new SmiliesList("偷笑",":titter:",R.drawable.titter));
		smiliesLists.add(new SmiliesList("流汗",":sweat:",R.drawable.sweat));
		smiliesLists.add(new SmiliesList("抓狂",":mad:",R.drawable.mad));
		smiliesLists.add(new SmiliesList("阴险",":lol:",R.drawable.lol));
		smiliesLists.add(new SmiliesList("可爱",":loveliness:",R.drawable.loveliness));
		smiliesLists.add(new SmiliesList("惊恐",":funk:",R.drawable.funk));
		smiliesLists.add(new SmiliesList("咒骂",":curse:",R.drawable.curse));
		smiliesLists.add(new SmiliesList("晕",":dizzy:",R.drawable.dizzy));
		smiliesLists.add(new SmiliesList("闭嘴",":shutup:",R.drawable.shutup));
		smiliesLists.add(new SmiliesList("睡",":sleepy:",R.drawable.sleepy));
		smiliesLists.add(new SmiliesList("拥抱",":hug:",R.drawable.hug));
		smiliesLists.add(new SmiliesList("胜利",":victory:",R.drawable.victory));
		smiliesLists.add(new SmiliesList("太阳",":sun:",R.drawable.sun));
		smiliesLists.add(new SmiliesList("月亮",":moon:",R.drawable.moon));
		smiliesLists.add(new SmiliesList("示爱",":kiss:",R.drawable.kiss));
		smiliesLists.add(new SmiliesList("握手",":handshake:",R.drawable.handshake));
	}

}
