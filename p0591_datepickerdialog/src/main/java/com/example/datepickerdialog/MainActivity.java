package com.example.datepickerdialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final int DATEPICKER = 1;
    TextView tvTime;
    int myDay = 14;
    int myMonth = 1;
    int myYear = 2017;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTime = (TextView) findViewById(R.id.tvTime);
    }

    public void onclick(View view){
        showDialog(DATEPICKER);
    }

    protected Dialog onCreateDialog(int dialogType) {
        Dialog dialog = null;
        if (dialogType == DATEPICKER) {
            dialog = new DatePickerDialog(this, onDateSetListener, myYear, myMonth - 1, myDay);
        }
        return dialog;
    }

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            myYear = i;
            myMonth = i1 + 1;
            myDay = i2;
            tvTime.setText("Выбранная дата: " + String.valueOf(i) + "." + String.valueOf(i1) + "." + String.valueOf(i2));
        }
    };
}
