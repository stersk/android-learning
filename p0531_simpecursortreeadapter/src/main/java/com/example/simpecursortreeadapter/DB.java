package com.example.simpecursortreeadapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DB {
    final private Context context;

    final String LOG_TAG;

    final String DB_NAME = "simplecursortreeadapter";
    final int DB_VERSION = 1;

    private DBOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase db;

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
        LOG_TAG = cntx.getString(R.string.LOG_TAG);
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

    public Cursor getCompanies() {
        return db.query(true, COMPANY_TABLE, null, null, null, null, null, null, null);
    }

    public Cursor getCompanyPhonesData(long companyId) {
        return db.query(true, PHONE_TABLE, null, PHONE_COLUMN_COMPANY + " = " + companyId ,null , null, null, null, null);
    }

    public Cursor getAllPhonesData() {
        readDataFromTable(db, PHONE_TABLE);
        return db.query(true, PHONE_TABLE, null, null, null, null, null, null, null);
    }

    public void readDataFromTable(SQLiteDatabase db, String tableName){
        Log.d(LOG_TAG, "--- Чтение данных из таблицы " + tableName + ", V" + String.valueOf(db.getVersion()) + " ---");
        Log.d(LOG_TAG, "------------------------------------------------------------");

        Cursor cursor = db.query(true, tableName, null, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            String[] columns = cursor.getColumnNames();
            String rowData;

            do {
                rowData = "";
                for (String column: columns) {
                    rowData +=  column + " = " + cursor.getString(cursor.getColumnIndex(column)) + "; ";
                }
                Log.d(LOG_TAG, rowData);

            } while (cursor.moveToNext());
        }

        Log.d(LOG_TAG, "------------------------------------------------------------");
        Log.d(LOG_TAG, "--- Всего прочитано " + String.valueOf(cursor.getCount()) + " строк ---");
    }

    private class DBOpenHelper extends SQLiteOpenHelper {
        public DBOpenHelper(Context context, String name, int version) {
            super(context, name, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            ContentValues rowData;
            int i;

            sqLiteDatabase.execSQL(COMPANY_TABLE_CREATE);
            sqLiteDatabase.execSQL(PHONE_TABLE_CREATE);

            try {
                sqLiteDatabase.beginTransaction();

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
                    rowData.put(PHONE_COLUMN_COMPANY, 1);

                    sqLiteDatabase.insert(PHONE_TABLE, null, rowData);
                }

                for (i = 0; i < phonesLG.length; i++){
                    rowData = new ContentValues();
                    rowData.put(PHONE_COLUMN_NAME, phonesLG[i]);
                    rowData.put(PHONE_COLUMN_COMPANY, 2);

                    sqLiteDatabase.insert(PHONE_TABLE, null, rowData);
                }

                rowData = new ContentValues();
                rowData.put(COMPANY_COLUMN_NAME, "HTC");
                rowData.put(COMPANY_COLUMN_ID, 0);
                sqLiteDatabase.insert(COMPANY_TABLE, null, rowData);

                rowData = new ContentValues();
                rowData.put(COMPANY_COLUMN_NAME, "SAMSUNG");
                rowData.put(COMPANY_COLUMN_ID, 1);
                sqLiteDatabase.insert(COMPANY_TABLE, null, rowData);

                rowData = new ContentValues();
                rowData.put(COMPANY_COLUMN_NAME, "LG");
                rowData.put(COMPANY_COLUMN_ID, 2);
                sqLiteDatabase.insert(COMPANY_TABLE, null, rowData);

                sqLiteDatabase.setTransactionSuccessful();
            } catch (Throwable e) {
                Log.d(context.getString(R.string.LOG_TAG), "DB error" + e.getMessage());
            } finally {
                sqLiteDatabase.endTransaction();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

}
