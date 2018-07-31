package net.shopnc.b2b2c.android.ui.im;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.IMSmile;

import java.util.ArrayList;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/8/9 15:36.
 */
public final class SmilesData {
    public static final ArrayList<IMSmile> smiliesLists = new ArrayList<IMSmile>();

    static {
        smiliesLists.add(new IMSmile("微笑",":smile:", R.drawable.smile));
        smiliesLists.add(new IMSmile("难过",":sad:",R.drawable.sad));
        smiliesLists.add(new IMSmile("呲牙",":biggrin:",R.drawable.biggrin));
        smiliesLists.add(new IMSmile("大哭",":cry:",R.drawable.cry));
        smiliesLists.add(new IMSmile("傲慢",":arrogant:",R.drawable.arrogant));
        smiliesLists.add(new IMSmile("尴尬",":awkward:",R.drawable.awkward));
        smiliesLists.add(new IMSmile("眨眼",":blink:",R.drawable.blink));
        smiliesLists.add(new IMSmile("害羞",":shy:",R.drawable.shy));
        smiliesLists.add(new IMSmile("偷笑",":titter:",R.drawable.titter));
        smiliesLists.add(new IMSmile("囧",":embarrassed:",R.drawable.embarrassed));
        smiliesLists.add(new IMSmile("抓狂",":mad:",R.drawable.mad));
        smiliesLists.add(new IMSmile("阴险",":lol:",R.drawable.lol));
        smiliesLists.add(new IMSmile("可爱",":loveliness:",R.drawable.loveliness));
        smiliesLists.add(new IMSmile("沮丧",":funk:",R.drawable.depressed));
        smiliesLists.add(new IMSmile("诅咒",":curse:",R.drawable.curse));
        smiliesLists.add(new IMSmile("晕",":dizzy:",R.drawable.dizzy));
        smiliesLists.add(new IMSmile("闭嘴",":shutup:",R.drawable.shutup));
        smiliesLists.add(new IMSmile("睡",":sleep:",R.drawable.sleep));
        smiliesLists.add(new IMSmile("鄙视",":despise:",R.drawable.despise));
        smiliesLists.add(new IMSmile("爱",":love:",R.drawable.love));
        smiliesLists.add(new IMSmile("撇嘴",":mouth:",R.drawable.mouth));
        smiliesLists.add(new IMSmile("凝视",":stare:",R.drawable.stare));
        smiliesLists.add(new IMSmile("示爱",":kiss:",R.drawable.kiss));
        smiliesLists.add(new IMSmile("咒骂",":swear:",R.drawable.swear));
    }
}
