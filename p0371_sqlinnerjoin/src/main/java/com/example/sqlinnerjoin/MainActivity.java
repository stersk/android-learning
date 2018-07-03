package com.example.sqlinnerjoin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    final String DB_NAME = "TwoTablesForLeftJoin";
    final String LOG_TAG = "TABLE_JOIN";

    // данные для таблицы должностей
    int[] position_id = { 1, 2, 3, 4 };
    String[] position_name = { "Директор", "Программер", "Бухгалтер", "Охранник" };
    int[] position_salary = { 15000, 13000, 10000, 8000 };

    // данные для таблицы людей
    String[] people_name = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь" };
    int[] people_posid = { 2, 3, 2, 2, 3, 1, 2, 4 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor currentCursor;

        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

        currentCursor = db.query(true, "position", null, null, null, null, null, null, null);
        readDataFromCursor(currentCursor, "Выведем таблицу должностей:");

        currentCursor = db.query(true, "people", null, null, null, null, null, null, null);
        readDataFromCursor(currentCursor, "Выведем таблицу должностей:");

        currentCursor = db.rawQuery("Select people.name as name, position.name as profession, position.salary from people left join position on people.posid = position.id", null);
        readDataFromCursor(currentCursor, "Суммарные данные:");

        String table = "people left join position on people.posid = position.id";
        String[] fields = new String[]{"people.name as name", "position.name as profession", "position.salary"};

        currentCursor = db.query(true, table, fields, "position.salary > ?", new String[]{"10000"}, null, null, null, null);

        readDataFromCursor(currentCursor, "Суммарные данные (объектная модель, больше 10000:");

        db.close();
        dbOpenHelper.close();
    }

    private void readDataFromCursor(Cursor cursor, String logHead) {
        String rowInfo;

        Log.d(LOG_TAG, logHead);
        Log.d(LOG_TAG, "---------------------------------------------");

        if (cursor.moveToFirst()) {
            do {
                rowInfo = "";
                for (String columnName : cursor.getColumnNames()) {
                    rowInfo = rowInfo.concat(columnName + " = "
                            + cursor.getString(cursor.getColumnIndex(columnName)) + "; ");
                }
                Log.d(LOG_TAG, rowInfo);

            } while (cursor.moveToNext());
        }

        Log.d(LOG_TAG, "---------------------------------------------");
        Log.d(LOG_TAG, "Всего прочитано " + String.valueOf(cursor.getCount()) + " строк.");
        Log.d(LOG_TAG, "---------------------------------------------");
        cursor.close();
    }

    class DBOpenHelper extends SQLiteOpenHelper {

        public DBOpenHelper(Context context) {
            super(context, DB_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(LOG_TAG, "Создание таблицы должностей...");

            int iterator;
            ContentValues rowValue;
            sqLiteDatabase.execSQL("create table position ("
                    + "id integer primary key,"
                    + "name text," + "salary integer"
                    + ");");

            for (iterator = 0; iterator < position_id.length; iterator++) {
                rowValue = new ContentValues();
                rowValue.put("id", position_id[iterator]);
                rowValue.put("name", position_name[iterator]);
                rowValue.put("salary", position_salary[iterator]);

                sqLiteDatabase.insert("position", null, rowValue);
            }
            Log.d(LOG_TAG, "Таблицу должностей создано.");
            Log.d(LOG_TAG, "Создание таблицы работников...");

            sqLiteDatabase.execSQL("create table people ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "posid integer"
                    + ");");

            for (iterator = 0; iterator < people_name.length; iterator++) {
                rowValue = new ContentValues();
                rowValue.put("name", people_name[iterator]);
                rowValue.put("posid", people_posid[iterator]);

                sqLiteDatabase.insert("people", null, rowValue);
            }
            Log.d(LOG_TAG, "Таблицу должностей создано.");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}


