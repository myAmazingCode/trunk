package net.shopnc.b2b2c.android.ui.promotion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import net.shopnc.b2b2c.android.ui.good.SearchGoodsShowActivity;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by xws on 2017/2/8.
 */

public class CategoryHelper {

    public static void jump(Context context, int cat, boolean isBrand) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("promotion", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int promotion = sharedPreferences.getInt("promotion", 0);
        Intent i;
        if (promotion < 0) {
            //清除sp标记
            editor.remove("promotion");
            editor.apply();

            //推广搜索
            i = new Intent(context, PromotionListActivity.class);
        } else {
            i = new Intent(context, SearchGoodsShowActivity.class);
        }


        //品牌
        if (isBrand) {
            i.putExtra("keyword", " ");
            i.putExtra("brandId", cat);

            i.putExtra("brand", cat);//推广
        }else {
            //分类
            i.putExtra("cat", cat);
        }
        context.startActivity(i);
    }
}
