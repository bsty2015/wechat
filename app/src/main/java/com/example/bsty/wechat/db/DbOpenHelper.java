package com.example.bsty.wechat.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bsty.wechat.applib.controller.HXSDKHelper;
import com.example.bsty.wechat.fx.others.TopUserDao;

/**
 * Created by bsty on 2/2/16.
 */
public class DbOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static DbOpenHelper instance;
    private static final String TOPUSER_TABLE_CREATE = "CREATE TABLE"
            + TopUserDao.TABLE_NAME + " ("
            + TopUserDao.COLUMN_NAME_TIME + " TEXT, "
            + TopUserDao.COLUMN_NAME_IS_GOUP + " TEXT, "
            + TopUserDao.COLUMN_NAME_ID + " TEXT PRIMARY KEY);";

    private static final String USERNAME_TABLE_CREATE = "CREATE TABLE "
            + UserDao.TABLE_NAME + " ("
            + UserDao.COLUMN_NAME_NICK + " TEXT, "
            + UserDao.COLUMN_NAME_AVATAR + " TEXT, "
            + UserDao.COLUMN_NAME_BEIZHU + " TEXT, "
            + UserDao.COLUMN_NAME_FXID + " TEXT, "
            + UserDao.COLUMN_NAME_REGION + " TEXT, "
            + UserDao.COLUMN_NAME_SEX + " TEXT, "
            + UserDao.COLUMN_NAME_SIGN + " TEXT, "
            + UserDao.COLUMN_NAME_TEL + " TEXT, "
            + UserDao.COLUMN_NAME_ID + " TEXT, "
            + " TEXT PRIMARY KEY);";

    private static final String INIVTE_MESSAGE_TABLE_CREATE = "CREATE TABLE"
            + InviteMessgeDao.TABLE_NAME + " ("
            + InviteMessgeDao.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + InviteMessgeDao.COLUMN_NAME_FROM + " TEXT, "
            + InviteMessgeDao.COLUMN_NAME_GROUP_ID + " TEXT, "
            + InviteMessgeDao.COLUMN_NAME_GROUP_Name + " TEXT, "
            + InviteMessgeDao.COLUMN_NAME_REASON + " TEXT, "
            + InviteMessgeDao.COLUMN_NAME_STATUS + " INTEGER, "
            + InviteMessgeDao.COLUMN_NAME_ISINVITEFROMME + " INTEGER, "
            + InviteMessgeDao.COLUMN_NAME_TIME + " TEXT); ";

    public DbOpenHelper(Context context) {
        super(context, getUserDatabaseName(), null, DATABASE_VERSION);
    }


    private static String getUserDatabaseName() {
        return HXSDKHelper.getInstance().getHXId() + "_glufine.db";
    }

    public static DbOpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DbOpenHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void closeDB() {
        if (instance != null) {
            SQLiteDatabase db = instance.getWritableDatabase();
            db.close();
            instance = null;
        }
    }
}
