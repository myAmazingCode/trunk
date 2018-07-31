package net.shopnc.b2b2c.android.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * 作者：Jie on 2016/6/12 18:39
 */
public class DBHelper {

    private DBManger manger;
    private SQLiteDatabase db;
    private Context context;

    public DBHelper(Context context) {
        this.context = context;
        manger = new DBManger(context);
    }

    public void saveHistory(String str) {
        db = manger.getWritableDatabase();
        ContentValues values = new ContentValues();
        //数据准备
        values.put("word", str);
        db.insert("search_history", null, values);
        db.close();
    }

    public void getHistory(List<String> his) {
        db = manger.getReadableDatabase();
        Cursor cursor = db.query("search_history", new String[]{"word"}, null, null, null, null, "_id asc");
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("word"));
                if (!his.contains(name)) {
                    his.add(name);
                }
            }
            cursor.close();
        }
        db.close();
    }

    /**
     * 清空数据
     */
    public void clearHistory() {
        db = manger.getWritableDatabase();
        db.execSQL("delete from search_history");
        db.close();
    }

    public void saveVouchersId(int id){
        db = manger.getWritableDatabase();
        ContentValues values = new ContentValues();
        //数据准备
        values.put("id", id);
        db.insert("voucher", null, values);
        db.close();
    }

    public void getVouchersId(List<Integer> ids){
        db = manger.getReadableDatabase();
        Cursor cursor = db.query("voucher", new String[]{"id"}, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                if (!ids.contains(id)) {
                    ids.add(id);
                }
            }
            cursor.close();
        }
        db.close();
    }

}
