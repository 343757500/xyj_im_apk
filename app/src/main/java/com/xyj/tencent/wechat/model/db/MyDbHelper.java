package com.xyj.tencent.wechat.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {
    public static final String DBNAME="im.db";
    public static final int VERSION=1;
    public static final String IM_SMS="im_message";
    public static final String IM_CHAT="tb_chat";



    public MyDbHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }
    private void createTable(SQLiteDatabase db) {
        db.execSQL("create table "+IM_SMS+"("+"id integer primary key autoincrement,"+
                    "wxno text," +
                    "fromAccount text,"+
                    "toAccount text,"+
                    "content text,"+
                    "conversationTime text,"+
                    "type text,"+
                    "wxid text,"+
                    "headUrl text,"+
                    "nickName text,"+
                     "isSend text,"+
                     "imwechatId text,"+
                    "remarkName text"+")"
                        );



        db.execSQL("create table "+IM_CHAT+"("+"id integer primary key autoincrement,"+
                "wxno text," +
                "fromAccount text," +
                "toAccount text," +
                "content text," +
                "conversationTime text," +
                "type text," +
                "wxid text," +
                "headUrl text," +
                "nickName text," +
                "remarkName text," +
                "receiverState text," +
                "fid text," +
                "msgState text"+")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
