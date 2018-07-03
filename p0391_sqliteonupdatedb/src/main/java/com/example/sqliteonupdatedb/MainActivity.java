package com.example.sqliteonupdatedb;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnV1, btnV2;
    DBOpenHelper dbOpenHelper;

    // данные для таблицы людей
    final String[] people_name = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь" };
    final String[] people_position = {"Программер", "Бухгалтер", "Программер","Программер","Бухгалтер","Директор","Программер","Охранник"};
    final String LOG_TAG = "UPDATEDB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnV1 = (Button) findViewById(R.id.btnV1);
        btnV2 = (Button) findViewById(R.id.btnV2);
        btnV1.setOnClickListener(this);
        btnV2.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        SQLiteDatabase db;
        ContentValues rowValues;

        switch (view.getId()) {
            case R.id.btnV1:
                dbOpenHelper = new DBOpenHelper(this, 1);
                db = dbOpenHelper.getWritableDatabase();
                Cursor alldata = db.query("people", null, null, null, null, null, null);
                if (alldata.getCount() == 0) {
                    Log.d(LOG_TAG, "Заполнение таблицы...");

                    for (int i = 0; i < people_name.length; i++ ){
                        rowValues = new ContentValues();
                        rowValues.put("name", people_name[i]);
                        rowValues.put("position", people_position[i]);

                        db.insert("people", null, rowValues);
                    }
                    Log.d(LOG_TAG, "Таблицу создано.");
                }

                readDataFromTable(db, "people");

                break;
            case R.id.btnV2:
                dbOpenHelper = new DBOpenHelper(this, 2);
                db = dbOpenHelper.getWritableDatabase();

                readDataFromTable(db, "position");
                readDataFromTable(db, "people");

                break;
        }

        dbOpenHelper.close();
    }

    class DBOpenHelper extends SQLiteOpenHelper {
        public DBOpenHelper(Context context, int version) {
            super(context, "SQLUpdateDb", null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(LOG_TAG, "Создание базы v.1");
            sqLiteDatabase.execSQL("create table people ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "position text"
                    + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            if ((i == 1) && (i1 == 2)) {
                Log.d(LOG_TAG, "Обновление базы таблицы к v.2 ...");
                sqLiteDatabase.beginTransaction();

                try {

                    ContentValues rowValue;

                    sqLiteDatabase.execSQL("create table position ("
                            + "id integer primary key autoincrement,"
                            + "name text"
                            + ");");

                    sqLiteDatabase.execSQL("alter table people add posid integer");

                    // Заполняем таблицу "position"
                    Cursor peopleCursor = sqLiteDatabase.query(true, "people", new String[] {"position"}, null, null, null, null, null,null);
                    int positionColumnIdx = peopleCursor.getColumnIndex("position");
                    if (peopleCursor.moveToFirst()) {
                        do {
                            rowValue = new ContentValues();
                            rowValue.put("name", peopleCursor.getString(positionColumnIdx));
                            sqLiteDatabase.insert("position", null, rowValue);
                        } while (peopleCursor.moveToNext());
                    }

                    // Заполняем колонку PosId
                    Cursor posCursor = sqLiteDatabase.query(true, "position", null, null, null, null, null, null, null);
                    int idColumnIdx = posCursor.getColumnIndex("id");
                    int positionNameColumnIdx = posCursor.getColumnIndex("name");
                    if (posCursor.moveToFirst()) {
                        do {
                            rowValue = new ContentValues();
                            rowValue.put("posid", posCursor.getInt(idColumnIdx));
                            sqLiteDatabase.update("people", rowValue, "position = ?", new String[]{posCursor.getString(positionNameColumnIdx)});
                        } while (posCursor.moveToNext());
                    }

                    // Удаляем колонку position
                    sqLiteDatabase.execSQL("create temporary table people_tmp ("
                            + "id integer, name text, position text, posid integer);");

                    sqLiteDatabase.execSQL("insert into people_tmp select id, name, position, posid from people;");
                    sqLiteDatabase.execSQL("drop table people;");

                    sqLiteDatabase.execSQL("create table people ("
                            + "id integer primary key autoincrement,"
                            + "name text, posid integer);");

                    sqLiteDatabase.execSQL("insert into people select id, name, posid from people_tmp;");
                    sqLiteDatabase.execSQL("drop table people_tmp;");

                    sqLiteDatabase.setTransactionSuccessful();
                    Log.d(LOG_TAG, "Обновление базы завершено.");
                } catch (Throwable t) {
                    Log.d(LOG_TAG, "Ошибка обновления базы: " + t.getMessage());
                }

                finally {
                    sqLiteDatabase.endTransaction();
                }
            }
        }
    }
}
