package com.example.touch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setOnTouchListener(this);
        setContentView(tv);

        count = 0;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        String eventType;
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                eventType = "Нажатие";
                break;
            case MotionEvent.ACTION_UP:
                eventType = "Отпускание";
                break;
            case MotionEvent.ACTION_MOVE:
                eventType = "Движение";
                break;
            default:
                eventType = "Другое";
        }

        float x,y;
        x = motionEvent.getX();
        y = motionEvent.getY();

        count++;

        String row = String.valueOf(count) + ":" + eventType + "(" + String.valueOf(x) + "|";
        row = row + String.valueOf(y) + ")\n";

        TextView tv = (TextView) view;
        tv.append(row);

        return true;
    }
}
