package com.example.alertdialogitemsingle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {
    private final Context context;
    private final int dbVersion = 1;
    private final String DB_NAME = "DialogItemSingle";
    private final String MAIN_TABLE = "main";

    private MySqliteDBOpenHelper dbOpenHelper;
    private SQLiteDatabase db;

    public DB(Context mContesxt) {
        context = mContesxt;
    }

    public void open() {
        dbOpenHelper = new MySqliteDBOpenHelper(context, DB_NAME, dbVersion);
        db = dbOpenHelper.getWritableDatabase();
    }

    public void close() {
        if (dbOpenHelper != null) {
            dbOpenHelper.close();
        }
    }

    public Cursor getAllData() {
        return db.query(true, MAIN_TABLE, null, null, null, null, null, null, null);
    }

    private class MySqliteDBOpenHelper extends SQLiteOpenHelper {

        public MySqliteDBOpenHelper(Context context, String name, int version) {
            super(context, name, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table " + MAIN_TABLE + " (" +
                    "_id integer primary key autoincrement," +
                    "text text)");

            ContentValues cv = new ContentValues();
            cv.put("text", "Первая строка");
            sqLiteDatabase.insert(MAIN_TABLE, null, cv);
            cv.clear();

            cv.put("text", "Вторая строка");
            sqLiteDatabase.insert(MAIN_TABLE, null, cv);
            cv.clear();

            cv.put("text", "Третья строка");
            sqLiteDatabase.insert(MAIN_TABLE, null, cv);
            cv.clear();

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
