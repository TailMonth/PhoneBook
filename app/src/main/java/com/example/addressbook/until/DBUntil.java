package com.example.addressbook.until;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBUntil extends SQLiteOpenHelper {


    private static final String DB_NAME = "db.addressBook.db";
    private static final int VERSION = 4;
    public static SQLiteDatabase db = null;//操作数据库

    public DBUntil(@Nullable Context context) {
        super(context,DB_NAME,null,VERSION,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表格 需要添加更多!!
        db.execSQL("drop table if exists d_peo");

        db.execSQL("CREATE TABLE d_peo (s_id INTEGER primary key AUTOINCREMENT," +//ID
                "s_name varchar(20),"+                                  //用户名
                "s_phone varchar(20),"+                                 //手机号
                "s_sex varchar(20),"+                                   //性别
                "s_remark varchar(20))" );                              //备注
        db.execSQL("INSERT INTO d_peo(s_name,s_phone,s_sex,s_remark) VALUES('流萤','13418511398','女','萨姆')");
        db.execSQL("INSERT INTO d_peo(s_name,s_phone,s_sex,s_remark) VALUES('卡芙卡','13148515198','女','Please')");
        db.execSQL("INSERT INTO d_peo(s_name,s_phone,s_sex,s_remark) VALUES('卡兹克','13148123198','男','改变就是好事')");
        db.execSQL("INSERT INTO d_peo(s_name,s_phone,s_sex,s_remark) VALUES('张三','12416551298','男','NO')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
