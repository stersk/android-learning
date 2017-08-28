package com.example.alertdialogcustom;

import android.app.AlertDialog;
import android.app.Dialog;
import java.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    final int ALERT_DIALOG = 1;
    ArrayList<View> arrayList;
    int btnClickId;
    LinearLayout bodyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();
    }

    public void onclick(View view) {
        btnClickId = view.getId();

        showDialog(ALERT_DIALOG);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case (ALERT_DIALOG):
                AlertDialog.Builder alertDialogBulder = new AlertDialog.Builder(this);
                alertDialogBulder.setTitle("Такое себе меню");

                LayoutInflater inflater = getLayoutInflater();

                bodyView = (LinearLayout) inflater.inflate(R.layout.dialog, null);

                alertDialogBulder.setView(bodyView);

                return alertDialogBulder.create();

            default:
                return super.onCreateDialog(id);
        }
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);

        if (id == ALERT_DIALOG) {
            if (btnClickId == R.id.btnAdd) {
                TextView newTextView = new TextView(dialog.getContext());
                arrayList.add(newTextView);

                newTextView.setText("Пункт " + String.valueOf(arrayList.size()));
                bodyView.addView(newTextView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            } else {
                if (arrayList.size() > 0) {
                    View lastView = arrayList.get(arrayList.size() - 1);
                    arrayList.remove(lastView);
                    bodyView.removeView(lastView);
                }
            }

            ((TextView) bodyView.findViewById(R.id.tvCount)).setText("Количество элементов: " + String.valueOf(arrayList.size()));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.US);
            String date = sdf.format(new Date(System.currentTimeMillis()));
            ((TextView) bodyView.findViewById(R.id.tvTime)).setText("Текущая дата: " + date);
        }
    }
}
