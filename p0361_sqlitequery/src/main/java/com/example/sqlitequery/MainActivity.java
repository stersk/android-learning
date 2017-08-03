package com.example.sqlitequery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    Button btnAll, btnFunc, btnPeople, btnGroup, btnHaving, btnSort;
    EditText etFunc, etPeople, etRegionPeople;
    RadioGroup rgSort;

    OpenDBHelper dbOpenHelper;

    final String DB_NAME = "PeopleByRegionStatistics";

    final String LOG_TAG = "SqliteQuery";

    String name[] = { "Китай", "США", "Бразилия", "Россия", "Япония",
            "Германия", "Египет", "Италия", "Франция", "Канада" };
    int people[] = { 1400, 311, 195, 142, 128, 82, 80, 60, 66, 35 };
    String region[] = { "Азия", "Америка", "Америка", "Европа", "Азия",
            "Европа", "Африка", "Европа", "Европа", "Америка" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbOpenHelper = new OpenDBHelper(this);

        btnAll = (Button) findViewById(R.id.btnAll);
        btnFunc = (Button) findViewById(R.id.btnFunc);
        btnPeople = (Button) findViewById(R.id.btnPeople);
        btnGroup = (Button) findViewById(R.id.btnGroup);
        btnHaving = (Button) findViewById(R.id.btnHaving);
        btnSort = (Button) findViewById(R.id.btnSort);

        etFunc = (EditText) findViewById(R.id.etFunc);
        etPeople = (EditText) findViewById(R.id.etPeople);
        etRegionPeople = (EditText) findViewById(R.id.etRegionPeople);

        rgSort = (RadioGroup) findViewById(R.id.rgSort);

        View.OnClickListener btnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
                Cursor cursor;
                String[] columns;
                String peopleCount;

                switch (view.getId()) {
                    case R.id.btnAll:
                        cursor = db.query(true, DB_NAME, null, null, null, null, null, null, null);
                        readDataFromCursor(cursor, "--- Чтение всех данных ---");

                        break;

                    case R.id.btnFunc:
                        String groupFunc = etFunc.getText().toString();
                        cursor = db.query(false, DB_NAME, new String[]{groupFunc}, null, null, null, null, null, null);
                        readDataFromCursor(cursor, "--- Чтение данных по функции \" " + groupFunc + " \"---");

                        break;

                    case R.id.btnPeople:
                        peopleCount = etPeople.getText().toString();

                        cursor = db.query(true, DB_NAME, null, "people > ?", new String[]{peopleCount}, null, null, null, null);
                        readDataFromCursor(cursor, "--- Чтение стран с населением более " + peopleCount + " человек ---");

                        break;

                    case R.id.btnGroup:
                        columns = new String[]{"region", "sum(people) as people"};
                        cursor = db.query(true, DB_NAME, columns, null, null, "region", null, null, null);
                        readDataFromCursor(cursor, "--- Получение данных по регионах ---");

                        break;

                    case R.id.btnHaving:
                        peopleCount = etRegionPeople.getText().toString();
                        columns = new String[]{"region", "sum(people) as peoples"};
                        cursor = db.query(true, DB_NAME, columns, null, null, "region", "sum(people) > " + peopleCount, null, null);
                        readDataFromCursor(cursor, "--- Получение данных по регионах c населением более " + peopleCount + " человек ---");

                        break;

                    case R.id.btnSort:
                        String sortField = "";
                        switch (rgSort.getCheckedRadioButtonId()) {
                            case R.id.rName:
                                sortField = "name";
                                break;
                            case R.id.rPeople:
                                sortField = "people";
                                break;
                            case R.id.rRegion:
                                sortField = "region";
                                break;
                        }

                        cursor = db.query(true, DB_NAME, null, null, null, null, null, sortField, null);
                        readDataFromCursor(cursor, "--- Получение данных c сортировкой по " + sortField + "  ---");

                        break;
                }
            }
        };

        btnAll.setOnClickListener(btnClickListener);
        btnFunc.setOnClickListener(btnClickListener);
        btnPeople.setOnClickListener(btnClickListener);
        btnGroup.setOnClickListener(btnClickListener);
        btnHaving.setOnClickListener(btnClickListener);
        btnSort.setOnClickListener(btnClickListener);

        SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();
        Cursor checkCursor = sqLiteDatabase.query(DB_NAME, null, null,null, null, null, null);
        if (checkCursor.getCount() == 0) {
            Log.d(LOG_TAG,"--- Начальное заполнение базы начато ---");
            ContentValues rowValues;
            for (int i=0; i < 10; i++) {
                rowValues = new ContentValues();
                rowValues.put("name", name[i]);
                rowValues.put("people", people[i]);
                rowValues.put("region", region[i]);

                sqLiteDatabase.insert(DB_NAME, null, rowValues);
            }
            Log.d(LOG_TAG,"--- Начальное заполнение базы завершено ---");

            btnClickListener.onClick(btnAll);
        }

        checkCursor.close();
        sqLiteDatabase.close();
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        dbOpenHelper.close();
    }

    class OpenDBHelper extends SQLiteOpenHelper {

        public OpenDBHelper(Context context) {
            super(context, DB_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(LOG_TAG, "--- Создание базы данных ---");
            // создаем таблицу с полями
            sqLiteDatabase.execSQL("create table " + DB_NAME + " ("
                    + "id integer primary key autoincrement," + "name text,"
                    + "people integer," + "region text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
