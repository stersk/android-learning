package com.sters.simplemysqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.MessageQueue;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    final private String LOG_TAG = "SimpleDB";
    final private String DB_NAME ="db";

    Button btnAdd, btnClear, btnRead;
    EditText etName, etMail;

    DBOpenHelper openDbHelper;
    SQLiteDatabase writebleDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openDbHelper = new DBOpenHelper(this);


        etName = (EditText) findViewById(R.id.etName);
        etMail = (EditText) findViewById(R.id.etMail);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnClear = (Button) findViewById(R.id.btnClear);

        btnAdd.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAdd:
                Log.d(LOG_TAG, "--- Добавление данных ---");

                writebleDb = openDbHelper.getWritableDatabase();
                ContentValues value = new ContentValues();
                value.put("name", etName.getText().toString());
                value.put("email", etMail.getText().toString());
                Log.d(LOG_TAG, "Имя: " + etName.getText().toString());
                Log.d(LOG_TAG, "Адрес: " + etMail.getText().toString());
                writebleDb.insert(DB_NAME, null, value);
                writebleDb.close();

                Log.d(LOG_TAG, "--- Добавление произведено ---");

                break;

            case R.id.btnClear:
                Log.d(LOG_TAG, "--- Очистка данных ---");

                writebleDb = openDbHelper.getWritableDatabase();
                writebleDb.delete(DB_NAME, null, null);
                writebleDb.close();

                Log.d(LOG_TAG, "--- Очистка данных произведена ---");

                break;

            case R.id.btnRead:
                Log.d(LOG_TAG, "--- Чтение данных ---");

                writebleDb = openDbHelper.getReadableDatabase();
                Cursor dataCursor = writebleDb.query(DB_NAME, null, null, null ,null ,null, null);
        }
    }

    class DBOpenHelper extends SQLiteOpenHelper {

        public DBOpenHelper(Context context) {
            super(context, DB_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(LOG_TAG, "--- Создание базы данных ---");
            // создаем таблицу с полями

            sqLiteDatabase.execSQL("create table " + DB_NAME + " ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "email text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
