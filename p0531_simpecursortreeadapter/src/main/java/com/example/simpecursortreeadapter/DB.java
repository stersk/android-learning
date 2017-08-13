package com.example.simpecursortreeadapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DB {
    final private Context context;

    final String DB_NAME = "simplecursortreeadapter";
    final int DB_VERSION = 1;

    DBOpenHelper sqLiteOpenHelper;
    SQLiteDatabase db;

    // имя таблицы компаний, поля и запрос создания
    private static final String COMPANY_TABLE = "company";
    public static final String COMPANY_COLUMN_ID = "_id";
    public static final String COMPANY_COLUMN_NAME = "name";
    private static final String COMPANY_TABLE_CREATE = "create table "
            + COMPANY_TABLE + "(" + COMPANY_COLUMN_ID
            + " integer primary key, " + COMPANY_COLUMN_NAME + " text" + ");";

    // имя таблицы телефонов, поля и запрос создания
    private static final String PHONE_TABLE = "phone";
    public static final String PHONE_COLUMN_ID = "_id";
    public static final String PHONE_COLUMN_NAME = "name";
    public static final String PHONE_COLUMN_COMPANY = "company";
    private static final String PHONE_TABLE_CREATE = "create table "
            + PHONE_TABLE + "(" + PHONE_COLUMN_ID
            + " integer primary key autoincrement, " + PHONE_COLUMN_NAME
            + " text, " + PHONE_COLUMN_COMPANY + " integer" + ");";

    public DB(Context cntx) {
       context = cntx;
    }

    public void open() {
        sqLiteOpenHelper = new DBOpenHelper(context, DB_NAME, DB_VERSION);
        db = sqLiteOpenHelper.getWritableDatabase();
    }

    public void close() {
        if (sqLiteOpenHelper != null) {
            sqLiteOpenHelper.close();
        }
    }

    private class DBOpenHelper extends SQLiteOpenHelper {
        public DBOpenHelper(Context context, String name, int version) {
            super(context, name, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            ContentValues rowData;
            int i;

            try {
                db.beginTransaction();
                db.execSQL(PHONE_TABLE_CREATE);

                String[] phonesHTC = new String[] { "Sensation", "Desire",
                        "Wildfire", "Hero" };
                String[] phonesSams = new String[] { "Galaxy S II", "Galaxy Nexus",
                        "Wave" };
                String[] phonesLG = new String[] { "Optimus", "Optimus Link",
                        "Optimus Black", "Optimus One" };

                for (i = 0; i < phonesHTC.length; i++){
                    rowData = new ContentValues();
                    rowData.put(PHONE_COLUMN_NAME, phonesHTC[i]);
                    rowData.put(PHONE_COLUMN_COMPANY, 0);

                    sqLiteDatabase.insert(PHONE_TABLE, null, rowData);
                }

                for (i = 0; i < phonesSams.length; i++){
                    rowData = new ContentValues();
                    rowData.put(PHONE_COLUMN_NAME, phonesSams[i]);
                    rowData.put(PHONE_COLUMN_COMPANY, 0);

                    sqLiteDatabase.insert(PHONE_TABLE, null, rowData);
                }

                for (i = 0; i < phonesLG.length; i++){
                    rowData = new ContentValues();
                    rowData.put(PHONE_COLUMN_NAME, phonesLG[i]);
                    rowData.put(PHONE_COLUMN_COMPANY, 0);

                    sqLiteDatabase.insert(PHONE_TABLE, null, rowData);
                }

                db.execSQL(COMPANY_TABLE_CREATE);

                rowData = new ContentValues();
                rowData.put("COMPANY_COLUMN_NAME", 0);
                db.insert(COMPANY_TABLE, null, rowData);

                rowData = new ContentValues();
                rowData.put("COMPANY_COLUMN_NAME", 1);
                db.insert(COMPANY_TABLE, null, rowData);

                rowData = new ContentValues();
                rowData.put("COMPANY_COLUMN_NAME", 2);
                db.insert(COMPANY_TABLE, null, rowData);

                db.setTransactionSuccessful();
            } catch (Throwable e) {
                Log.d(context.getString(R.string.LOG_TAG), "DB error" + e.getMessage());
            } finally {
                db.endTransaction();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

}
