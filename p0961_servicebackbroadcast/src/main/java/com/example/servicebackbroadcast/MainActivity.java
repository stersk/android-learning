package com.example.servicebackbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView1, textView2, textView3;
    MyReceiver myReceiver;

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(myReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.tvTask1);
        textView2 = (TextView) findViewById(R.id.tvTask2);
        textView3 = (TextView) findViewById(R.id.tvTask3);

        myReceiver = new MyReceiver();
        registerReceiver(myReceiver, new IntentFilter("com.example.servicebackbroadcast.MAIN"));
    }

    public void onClickStart(View view) {
        textView1.setText("");
        textView2.setText("");
        textView3.setText("");

        Intent serviceIntent = new Intent(this, MyService.class);

        serviceIntent.putExtra("requestNumber", 1);
        serviceIntent.putExtra("delay", 3);
        startService(serviceIntent);

        serviceIntent.putExtra("requestNumber", 2);
        serviceIntent.putExtra("delay", 6);
        startService(serviceIntent);

        serviceIntent.putExtra("requestNumber", 3);
        serviceIntent.putExtra("delay", 4);
        startService(serviceIntent);
    }

    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getIntExtra("startId", 0)) {
                case 1:
                    textView1.setText("Первый процесс завершен");
                    break;

                case 2:
                    textView2.setText("Второй процесс завершен");
                    break;

                case 3:
                    textView3.setText("Третий процесс завершен");
                    break;
            }
        }
    }
}
