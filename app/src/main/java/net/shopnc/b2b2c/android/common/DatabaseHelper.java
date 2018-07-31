package net.shopnc.b2b2c.android.common;

import java.sql.SQLException;

import net.shopnc.b2b2c.android.bean.Search;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper
{
    private static final String TAG = "DatabaseHelper";
    // 数据库名称
    private static final String DATABASE_NAME = "shopnc_2015.db";
    // 数据库version
    private static final int DATABASE_VERSION = 2;

    private Dao<Search, Integer> searchDao = null;
    private RuntimeExceptionDao<Search, Integer> searchRuntimeDao = null;

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
       // 可以用配置文件来生成 数据表，有点繁琐，不喜欢用
       // super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    /**
     * @param context
     * @param databaseName
     * @param factory
     * @param databaseVersion
     */
    public DatabaseHelper(Context context, String databaseName, CursorFactory factory, int databaseVersion)
    {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource)
    {
        try
        {
          //建立User表
            TableUtils.createTable(connectionSource, Search.class);
            
            //初始化DAO
            searchDao = getSearchDao();
            searchRuntimeDao = getSearchDataDao();
        }
        catch (SQLException e)
        {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        try
        {
            TableUtils.dropTable(connectionSource, Search.class, true);
        }
        catch (SQLException e)
        {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
    }

    
    /**
     * @return
     * @throws SQLException
     */
    private Dao<Search, Integer> getSearchDao() throws SQLException
    {
        if (searchDao == null)
        	searchDao = getDao(Search.class);
        return searchDao;
    }

    public RuntimeExceptionDao<Search, Integer> getSearchDataDao()
    {
        if (searchRuntimeDao == null)
        {
        	searchRuntimeDao = getRuntimeExceptionDao(Search.class);
        }
        return searchRuntimeDao;
    }
    
    /**
     * 释放 DAO
     */
    @Override
    public void close() {
        super.close();
        searchRuntimeDao = null;
    }

}
