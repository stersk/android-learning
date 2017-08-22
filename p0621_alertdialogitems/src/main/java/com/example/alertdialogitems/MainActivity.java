package com.example.alertdialogitems;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DB myDB;
    final int DATA_CURSOR = 1;
    final int DATA_ADAPTER = 2;
    final int DATA_LIST = 3;
    Cursor cursor;
    ArrayAdapter<String> dataAdapter;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DB(this);
        myDB.open();

        cursor = myDB.getAllData();
        startManagingCursor(cursor);

        ArrayList<String> dataList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            int textColumnIndex = cursor.getColumnIndex("text");

            do {
                dataList.add(cursor.getString(textColumnIndex));
            } while (cursor.moveToNext());
        }

        data = new String[dataList.size()];
        dataList.toArray(data);

        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
    }

    public void onclick(View view) {
        data[3] = String.valueOf(myDB.increaseCounter());

        switch (view.getId()) {
            case R.id.btnCursor:
                showDialog(DATA_CURSOR);
                break;

            case R.id.btnAdapter:
                showDialog(DATA_ADAPTER);
                break;

            case R.id.btnList:
                showDialog(DATA_LIST);
                break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder dialogBuilder;

        switch (id) {
            case DATA_CURSOR:
                dialogBuilder = new AlertDialog.Builder(this);
                dialogBuilder.setCursor(cursor, null, "text");

                return dialogBuilder.create();

            case DATA_ADAPTER:
                dialogBuilder = new AlertDialog.Builder(this);
                dialogBuilder.setAdapter(dataAdapter, null);

                return dialogBuilder.create();

            case DATA_LIST:
                dialogBuilder = new AlertDialog.Builder(this);
                dialogBuilder.setItems(data, null);

                return dialogBuilder.create();

            default:
                return super.onCreateDialog(id);
        }
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);

        switch (id) {
            case DATA_CURSOR:
                cursor.requery();
                break;
            case DATA_ADAPTER:
                if (dialog instanceof AlertDialog) {
                    AlertDialog alert = (AlertDialog) dialog;
                    ((BaseAdapter)alert.getListView().getAdapter()).notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        myDB.close();
    }
}
