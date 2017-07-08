package com.gauri.alertsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public SQLiteDatabase getDatabaseInstance() {
        return database;
    }

    public void close() {
        dbHelper.close();
    }

    //------------Login----------------------------
    public void insertLogin(String username, String password, String email, String question, String answer) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.USER_USERNAME, username);
        contentValue.put(DatabaseHelper.USER_PASSWORD, password);
        contentValue.put(DatabaseHelper.USER_EMAIL, email);
        contentValue.put(DatabaseHelper.USER_QUESTION, question);
        contentValue.put(DatabaseHelper.USER_ANSWER, answer);
        database.insert(DatabaseHelper.TABLE_LOGIN, null, contentValue);
    }
    //------get all rows---------------------
    public Cursor fetchLogin() {
        String[] columns = new String[] { DatabaseHelper.USER_ID, DatabaseHelper.USER_USERNAME, DatabaseHelper.USER_PASSWORD,
                                        DatabaseHelper.USER_EMAIL, DatabaseHelper.USER_QUESTION, DatabaseHelper.USER_ANSWER  };
        Cursor cursor = database.query(DatabaseHelper.TABLE_LOGIN, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //----------get particular row data-------------

    public String getSinlgeEntry(String userName) {
        String uname=DatabaseHelper.USER_USERNAME+"=?";
        Cursor cursor = database.query(DatabaseHelper.TABLE_LOGIN, null, uname,
                new String[] { userName }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_PASSWORD));
        cursor.close();
        return password;
    }

    //---------------end of Login----------------------
}



