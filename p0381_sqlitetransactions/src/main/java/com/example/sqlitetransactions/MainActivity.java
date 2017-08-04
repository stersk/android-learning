package com.example.sqlitetransactions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "SQLIteTransaction";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

        makeAction(db);

        Log.d(LOG_TAG, "--- Проверка на соединени в одном dbOpenHelper:");
        SQLiteDatabase db2 = dbOpenHelper.getReadableDatabase();
        Log.d(LOG_TAG, "db = db2 - " + db.equals(db2));
        Log.d(LOG_TAG, "db open - " + db.isOpen() + ", db2 open - " + db2.isOpen());

        dbOpenHelper.close();
        Log.d(LOG_TAG, "-- Закрываем соединение --");
        Log.d(LOG_TAG, "db open - " + db.isOpen() + ", db2 open - " + db2.isOpen());
    }

    private void makeAction(SQLiteDatabase db) {
        clearData(db);
        db.beginTransaction();
        insertData(db, "val1");

        // try to open new tonnection
        try {
            Log.d(LOG_TAG, "--- Попытка поднять второе соединение при транзакции:");
            DBOpenHelper secondDbOpenHelper = new DBOpenHelper(this);
            SQLiteDatabase newConnection = secondDbOpenHelper.getReadableDatabase();
            Log.d(LOG_TAG, "удалось");
        } catch (Throwable t) {
            Log.d(LOG_TAG, "Не удалось: " + t.getMessage());
        }

        db.setTransactionSuccessful();
        insertData(db, "val2");
        db.endTransaction();
        insertData(db, "val3");
        readData(db);
    }

    private void insertData(SQLiteDatabase db, String data) {
        ContentValues rowContainer = new ContentValues();
        rowContainer.put("val", data);

        db.insert("mytable", null, rowContainer);

        Log.d(LOG_TAG, "----------------------------------");
        Log.d(LOG_TAG, "--- Вставлено строку \"" + data + "\" --- ");
    }

    private void clearData(SQLiteDatabase db) {
        int deletedRowsCount = db.delete("mytable", null, null);
        Log.d(LOG_TAG, "----------------------------------");
        Log.d(LOG_TAG, "--- Удалено " + String.valueOf(deletedRowsCount) + " строк --- ");
    }

    private void readData(SQLiteDatabase db) {
        Cursor cursor = db.query(true, "mytable", null, null, null, null, null, null, null);

        String[] columns = cursor.getColumnNames();
        String dataRow;

        Log.d(LOG_TAG, "--- Чтение данных ---");
        Log.d(LOG_TAG, "----------------------------------");

        if (cursor.moveToFirst()) {
            do {
                dataRow = "";

                for (String column : columns) {
                    dataRow += column + " = " + cursor.getString(cursor.getColumnIndex(column)) + "; ";
                }
                Log.d(LOG_TAG, dataRow);
            } while (cursor.moveToNext());
        }

        Log.d(LOG_TAG, "----------------------------------");
        Log.d(LOG_TAG, "--- Всего прочитано " + String.valueOf(cursor.getCount()) + " строк ---");
    }

    class DBOpenHelper extends SQLiteOpenHelper {
        public DBOpenHelper(Context context) {
            super(context, "SQLIteStransaction", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(LOG_TAG, "----------------------------------");
            Log.d(LOG_TAG, "--- Создание базы ---");
            Log.d(LOG_TAG, "----------------------------------");

            sqLiteDatabase.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "val text"
                    + ");");

            Log.d(LOG_TAG, "--- Базу создано ---");
            Log.d(LOG_TAG, "----------------------------------");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
