package net.shopnc.b2b2c.android.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 作者：Jie on 2016/6/12 18:32
 */
public class DBManger extends SQLiteOpenHelper {

    public DBManger(Context context) {
        super(context, "shop_nc", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists search_history ( _id integer primary key autoincrement, " +
                "word text);");
        db.execSQL("create table if not exists voucher ( _id integer primary key autoincrement, " +
                "id integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
