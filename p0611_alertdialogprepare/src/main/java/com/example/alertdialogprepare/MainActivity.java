package com.example.alertdialogprepare;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
    final int ALERT = 1;
    final static String LOG_TAG = "myLogs";
    SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreateDialog");
        setContentView(R.layout.activity_main);
    }

    public void onclick(View view){
        showDialog(ALERT);
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        Log.d(LOG_TAG, "onPrepareDialog");
        if (id == ALERT) {
            ((AlertDialog)dialog).setMessage(sdfDate.format(new Date(System.currentTimeMillis())));
        }
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if (id == ALERT) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Текущая дата");

            adb.setMessage(sdfDate.format(new Date(System.currentTimeMillis())));

            return adb.create();
        }

        return super.onCreateDialog(id);
    }
}
