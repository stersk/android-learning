package com.example.sqlitequery;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
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

    final String DB_NAME = "PeopleStatistics";

    final String LOG_TAG = "Sqlite Query";

    String name[] = { "Китай", "США", "Бразилия", "Россия", "Япония",
            "Германия", "Египет", "Италия", "Франция", "Канада" };
    int people[] = { 1400, 311, 195, 142, 128, 82, 80, 60, 66, 35 };
    String region[] = {  };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener btnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db;

                switch (view.getId()) {
                    case R.id.btnAll:

                        break;
                    case R.id.btnFunc:
                        break;
                    case R.id.btnPeople:
                        break;
                    case R.id.btnGroup:
                        break;
                    case R.id.btnHaving:
                        break;
                    case R.id.btnSort:
                        break;
                }

            }
        };

        Button btnAll = (Button) findViewById(R.id.btnAll);
        Button btnFunc = (Button) findViewById(R.id.btnFunc);
        Button btnPeople = (Button) findViewById(R.id.btnPeople);
        Button btnGroup = (Button) findViewById(R.id.btnGroup);
        Button btnHaving = (Button) findViewById(R.id.btnHaving);
        Button btnSort = (Button) findViewById(R.id.btnSort);

        btnAll.setOnClickListener(btnClickListener);
        btnFunc.setOnClickListener(btnClickListener);
        btnPeople.setOnClickListener(btnClickListener);
        btnGroup.setOnClickListener(btnClickListener);
        btnHaving.setOnClickListener(btnClickListener);
        btnSort.setOnClickListener(btnClickListener);

        EditText etFunc = (EditText) findViewById(R.id.etFunc);
        EditText etPeople = (EditText) findViewById(R.id.etPeople);
        EditText etRegionPeople = (EditText) findViewById(R.id.etRegionPeople);

        RadioGroup rgSort = (RadioGroup) findViewById(R.id.rgSort);

        dbOpenHelper = new OpenDBHelper(this);
        SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();
        Cursor checkCursor = sqLiteDatabase.query(DB_NAME, null, null,null, null, null, null);
        if (checkCursor.getCount() == 0) {
            ContentValues rowValues;
            for (int i=0; i < 10; i++) {
                rowValues = new ContentValues();
                rowValues.put("name", name[i]);
                rowValues.put("people", people[i]);
                rowValues.put("region", region[i]);

                sqLiteDatabase.insert(DB_NAME, null, rowValues);
            }
        }

        checkCursor.close();
        sqLiteDatabase.close();
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
            Log.d(LOG_TAG, "--- onCreate database ---");
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
