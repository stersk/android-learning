package com.example.alertdialogitems;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {
    private final Context context;
    private final int dbVersion = 1;
    private final String DB_NAME = "Dialog";
    private final String MAIT_TABLE = "main";

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
        if (dbOpenHelper == null) {
            dbOpenHelper.close();
        }
    }

    public Cursor getAllData() {
        return db.query(true, MAIT_TABLE, null, null, null, null, null, null, null);
    }

    public void increaseCounter() {
        Cursor currentValueCursor = db.query(true, MAIT_TABLE, null, "_id = ?", new String[]{"4"}, null, null, null, null);
        int textColumnIdx = currentValueCursor.getColumnIndex("text");

        if (currentValueCursor.moveToFirst()) {
            int currentValue = Integer.valueOf(currentValueCursor.getString(textColumnIdx));
            ContentValues cv = new ContentValues();
            cv.put("text", String.valueOf(currentValue + 1));

            db.update(MAIT_TABLE,cv, "_id", new String[]{"4"});
        }
    }

    private class MySqliteDBOpenHelper extends SQLiteOpenHelper {

        public MySqliteDBOpenHelper(Context context, String name, int version) {
            super(context, name, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table " + MAIT_TABLE + " (" +
                    "_id int auroincrement," +
                    "text text)");

            ContentValues cv = new ContentValues();
            cv.put("text", "Первая строка");
            sqLiteDatabase.insert(MAIT_TABLE, null, cv);
            cv.clear();

            cv.put("text", "Вторая строка");
            sqLiteDatabase.insert(MAIT_TABLE, null, cv);
            cv.clear();

            cv.put("text", "Третья строка");
            sqLiteDatabase.insert(MAIT_TABLE, null, cv);
            cv.clear();

            cv.put("text", "0");
            sqLiteDatabase.insert(MAIT_TABLE, null, cv);
            cv.clear();
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
