package com.example.alertdialogitems;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    DB myDB;
    final int DATA_CURSOR = 1;
    final int DATA_ADAPTER = 2;
    final int DATA_LIST = 3;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DB(this);
        myDB.open();
        cursor = myDB.getAllData();
        startManagingCursor(cursor);
    }

    public void onclick(View view) {
        myDB.increaseCounter();

        switch (view.getId()) {
            case R.id.btnCursor:
                showDialog(DATA_CURSOR);
                break;

            case R.id.btnAdapter:
                break;

            case R.id.btnList:
                break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DATA_CURSOR) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setCursor(cursor, null, "text");

            return dialogBuilder.create();
        }

        return super.onCreateDialog(id);
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);

        if (id == DATA_CURSOR) {
            cursor.requery();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        myDB.close();
    }
}
