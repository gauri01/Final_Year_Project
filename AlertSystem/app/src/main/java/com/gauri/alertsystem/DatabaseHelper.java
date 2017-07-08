package com.gauri.alertsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper {



    public static final String TABLE_LOGIN = "LOGIN";


    static final String DB_NAME = "REGISTRATION.DB";


    public static final String USER_ID = "_id";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";
    public static final String USER_QUESTION = "question";
    public static final String USER_ANSWER = "answer";
    public static final String USER_EMAIL = "email";


    static final int DB_VERSION = 1;


    private static final String CREATE_TABLE_LOGIN = "create table "
            + TABLE_LOGIN
            + "(" + USER_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_USERNAME + " TEXT NOT NULL, "
            + USER_PASSWORD + " TEXT ,"
            + USER_EMAIL + " TEXT, "
            + USER_QUESTION + " TEXT, "
            + USER_ANSWER + " TEXT );";

    public DatabaseHelper(Context context)
    {
        super(context, DB_NAME,null,  DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_LOGIN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        onCreate(db);
    }
}
