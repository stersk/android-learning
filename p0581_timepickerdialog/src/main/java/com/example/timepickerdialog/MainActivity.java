package com.example.timepickerdialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    final int TIMEPICKER = 1;
    TextView tvTime;
    int myHour = 14;
    int myMinute = 35;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTime = (TextView) findViewById(R.id.tvTime);
    }

    public void onclick(View view){
        showDialog(TIMEPICKER);
    }

    protected Dialog onCreateDialog(int dialogType) {
        Dialog dialog = null;
        if (dialogType == TIMEPICKER) {
            dialog = new TimePickerDialog(this, onTimeSetListener, myHour, myMinute, true);
        }
        return dialog;
    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            tvTime.setText("Выбранное время: " + String.valueOf(i) + ":" + String.valueOf(i1));
        }
    };
}
