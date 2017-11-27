package com.example.simplecursoradapter;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class
MainActivity extends AppCompatActivity {

    private DB db;
    private SimpleCursorAdapter scAdapter;
    private ListView lvData;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DB(this);
        db.open();

        cursor = db.getAllRecords();
        startManagingCursor(cursor);

        // формируем столбцы сопоставления
        String[] from = new String[] { DB.COLUMN_IMG, DB.COLUMN_TXT };
        int[] to = new int[] { R.id.ivImg, R.id.tvText };

        // создааем адаптер и настраиваем список
        scAdapter = new SimpleCursorAdapter(this, R.layout.item, cursor, from, to);
        lvData = (ListView) findViewById(R.id.lvData);
        lvData.setAdapter(scAdapter);

        registerForContextMenu(lvData);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(1, 1, 1, "Удалить");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            // получаем из пункта контекстного меню данные по пункту списка
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            // извлекаем id записи и удаляем соответствующую запись в БД
            db.deleteRecord(acmi.id);
            // обновляем курсор
            cursor.requery();
            return true;
        }

        return super.onContextItemSelected(item);
    }

    public void onButtonClick(View view){
        db.addRecord("Какой-то текст " + String.valueOf(cursor.getCount() + 1), R.mipmap.ic_launcher);
        cursor.requery();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        db.close();
    }
}
