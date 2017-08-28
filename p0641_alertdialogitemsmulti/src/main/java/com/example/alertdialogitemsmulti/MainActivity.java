package com.example.alertdialogitemsmulti;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DB myDB;
    final int DATA_ITEM_SINGLE = 1;
    Cursor cursor;
    Context context;
    int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        currentPosition = -1;

        myDB = new DB(this);
        myDB.open();

        cursor = myDB.getAllData();
    }

    public void onclick(View view) {
        showDialog(DATA_ITEM_SINGLE);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DATA_ITEM_SINGLE) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setTitle("Выберите один из пунктов");
            dialogBuilder.setMultiChoiceItems(cursor, "checked", "text", onMultiChoiceClickListener);
            dialogBuilder.setPositiveButton("Ок", onItemSelected);
            return dialogBuilder.create();
        }

        return super.onCreateDialog(id);
    }

    DialogInterface.OnClickListener onItemSelected = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            currentPosition = i;
            int fieldIdx = cursor.getColumnIndex("text");

            if (i == AlertDialog.BUTTON_POSITIVE) {
                AlertDialog dialog = (AlertDialog) dialogInterface;
                ListView listView = dialog.getListView();
                SparseBooleanArray newArray = listView.getCheckedItemPositions();

                for (int i1 = 0; i1 < newArray.size(); i1++) {

                    if (newArray.get(i1)) {
                        cursor.moveToPosition(i1);
                        Log.d("MULTISELECT", "Выбран: " + cursor.getString(fieldIdx));
                    }
                }
            }
        }
    };

    DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
            cursor.moveToPosition(i);
            int column = cursor.getColumnIndex("text");
            String text = cursor.getString(column);

            Toast.makeText(context, "Пункт " + text + " " + (b ? "ВЫБРАН" : "НЕ ВЫБРАН"), Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        cursor.close();
        myDB.close();
    }
}
