package com.sters.simplemysqllite;

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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    final private String LOG_TAG = "SimpleDB";
    final private String DB_NAME ="db";

    Button btnAdd, btnClear, btnRead, btnSelect, btnRemove, btnUpdate;
    EditText etName, etMail, etId;

    DBOpenHelper openDbHelper;
    SQLiteDatabase writebleDb;

    String index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openDbHelper = new DBOpenHelper(this);

        etName = (EditText) findViewById(R.id.etName);
        etMail = (EditText) findViewById(R.id.etMail);
        etId = (EditText) findViewById(R.id.etId);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnRemove = (Button) findViewById(R.id.btnRemove);

        btnAdd.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnSelect.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnRemove.setOnClickListener(this);
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
                int clearedRowsCount = writebleDb.delete(DB_NAME, null, null);
                writebleDb.close();

                Log.d(LOG_TAG, "--- Очистка данных произведена (очищено " + String.valueOf(clearedRowsCount) + " строк) ---");

                break;

            case R.id.btnRead:
                Log.d(LOG_TAG, "--- Чтение данных ---");

                writebleDb = openDbHelper.getReadableDatabase();
                Cursor dataCursor = writebleDb.query(DB_NAME, null, null, null ,null ,null, null);
                if (dataCursor.moveToFirst()) {
                    int indexId = dataCursor.getColumnIndex("id");
                    int indexName = dataCursor.getColumnIndex("name");
                    int indexMail = dataCursor.getColumnIndex("email");

                    do {
                        Log.d(LOG_TAG, "-------------------------------------------------------------");
                        Log.d(LOG_TAG, " id - " + String.valueOf(dataCursor.getInt(indexId)) + " ----");
                        Log.d(LOG_TAG, " name - " + String.valueOf(dataCursor.getString(indexName)) + " ----");
                        Log.d(LOG_TAG, " e-mail - " + String.valueOf(dataCursor.getString(indexMail)) + " ----");
                    } while (
                        dataCursor.moveToNext()
                    );

                    Log.d(LOG_TAG, "-------------------------------------------------------------");
                    Log.d(LOG_TAG, "--- Прочитано данных: " + String.valueOf(dataCursor.getCount()) + " строк ---");
                } else {
                    Log.d(LOG_TAG, "--- Прочитано данных: 0 строк ---");
                }
                dataCursor.close();

                break;

            case R.id.btnSelect:

                index = etId.getText().toString();
                if (index.equalsIgnoreCase("")) {
                    Toast.makeText(this, "Введено некорректный идентфикатор", Toast.LENGTH_LONG).show();
                } else {
                    writebleDb = openDbHelper.getReadableDatabase();
                    Cursor result = writebleDb.query(true, DB_NAME, null, "id = ?", new String[]{index}, null, null, null, null, null);
                    if (result.moveToFirst()) {
                        int indexId = result.getColumnIndex("id");
                        int indexName = result.getColumnIndex("name");
                        int indexMail = result.getColumnIndex("email");

                        Log.d(LOG_TAG, "-------------------------------------------------------------");
                        Log.d(LOG_TAG, " id - " + String.valueOf(result.getInt(indexId)) + " ----");
                        Log.d(LOG_TAG, " name - " + String.valueOf(result.getString(indexName)) + " ----");
                        Log.d(LOG_TAG, " e-mail - " + String.valueOf(result.getString(indexMail)) + " ----");
                        Log.d(LOG_TAG, "-------------------------------------------------------------");

                        etName.setText(result.getString(indexName));
                        etMail.setText(result.getString(indexMail));
                    } else {
                        Log.d(LOG_TAG, "-------------------------------------------------------------");
                        Log.d(LOG_TAG, " Строки с id " + String.valueOf(index) + " не найдено ----");
                        Log.d(LOG_TAG, "-------------------------------------------------------------");

                        etName.setText("");
                        etMail.setText("");
                    }

                    result.close();
                }
                break;

            case R.id.btnUpdate:
                index = etId.getText().toString();
                if (index.equalsIgnoreCase("")) {
                    Toast.makeText(this, "Введено некорректный идентфикатор", Toast.LENGTH_LONG).show();
                } else {
                    writebleDb = openDbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("id", index);
                    values.put("name", etName.getText().toString());
                    values.put("email", etMail.getText().toString());

                    writebleDb.update(DB_NAME, values, "id = ?", new String[]{index});
                    writebleDb.close();
                    Log.d(LOG_TAG, " Данные строки с id " + String.valueOf(index) + " перезаписаны ----");
                }
                break;

            case R.id.btnRemove:
                index = etId.getText().toString();
                if (index.equalsIgnoreCase("")) {
                    Toast.makeText(this, "Введено некорректный идентфикатор", Toast.LENGTH_LONG).show();
                } else {
                    writebleDb = openDbHelper.getWritableDatabase();

                    int rowsDeleted = writebleDb.delete(DB_NAME, "id = ?", new String[]{index});
                    writebleDb.close();
                    Log.d(LOG_TAG, " Удалено " + String.valueOf(rowsDeleted) + " строку(строк) ----");
                }
                break;
        }
        openDbHelper.close();
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
