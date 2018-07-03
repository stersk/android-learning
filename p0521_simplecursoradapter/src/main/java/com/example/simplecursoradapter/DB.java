package com.example.simplecursoradapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {
    private static final String DB_NAME = "mydb_01";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "mytab";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_IMG = "img";
    public static final String COLUMN_TXT = "txt";

    private static final String DB_CREATE =
            "create table " + DB_TABLE + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_IMG + " integer, " +
                    COLUMN_TXT + " text" +
                    ");";

    private final Context mCtx;

    public DB (Context context) {
        mCtx = context;
    }

    private DBOpenHelper dbOpenHelper;
    public SQLiteDatabase db;

    public void open() {
        dbOpenHelper = new DBOpenHelper(mCtx, DB_NAME, DB_VERSION);
        db = dbOpenHelper.getWritableDatabase();
    }

    public void close() {
        if (dbOpenHelper != null) {
            dbOpenHelper.close();
        }
    }

    public void addRecord(String text, int image) {
        ContentValues value = new ContentValues();
        value.put(COLUMN_TXT, text);
        value.put(COLUMN_IMG, image);
        db.insert(DB_TABLE, null, value);
    }

    public void deleteRecord(long idx) {
        db.delete(DB_TABLE, COLUMN_ID + " = ?", new String[]{String.valueOf(idx)});
    }

    public Cursor getAllRecords(){
        return db.query(true, DB_TABLE, null, null, null, null, null, null, null);
    }

    public class DBOpenHelper extends SQLiteOpenHelper{
        public DBOpenHelper(Context context, String name, int version) {
            super(context, "cursorAdapter", null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(DB_CREATE);

            ContentValues cv = new ContentValues();
            for (int i = 1; i < 5; i++) {
                cv.put(COLUMN_TXT, "Какой-то текст " + i);
                cv.put(COLUMN_IMG, R.mipmap.ic_launcher);
                sqLiteDatabase.insert(DB_TABLE, null, cv);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
