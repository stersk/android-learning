package com.example.servicelocalbinding;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection serviceConnection;
    private MyService service = null;
    private TextView textView;
    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tvInterval);
        textView.setText("Обработка не запущена");

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                MyService.MyBinder binder = (MyService.MyBinder) iBinder;
                service = binder.getService();
                service.startProcessing();
                textView.setText("Интервал обработки: 1 секунда.");
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
    }

    public void onClickStart(View view){
        if (service != null) {
            Log.d(LOG_TAG, "Service already started and binded");
            return;
        }

        Intent intent = new Intent(this, MyService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    public void onClickUp(View view){
        if (service == null) {
            Log.d(LOG_TAG, "Service not started");
            return;
        }

        int newInterval = service.increaseProcessingInterval();
        textView.setText("Интервал обработки: " + String.valueOf(newInterval) + " секунд.");
    }

    public void onClickDown(View view){
        if (service == null) {
            Log.d(LOG_TAG, "Service not started");
            return;
        }

        int newInterval = service.decreaseProcessingInterval();
        textView.setText("Интервал обработки: " + String.valueOf(newInterval) + " секунд.");
    }

    public void onClickStop(View view){
        if (service == null) {
            Log.d(LOG_TAG, "Service already stopped.");
            return;
        }

        service.stopProcessing();
        unbindService(serviceConnection);
        textView.setText("Обработка остановлена");
        service = null;
    }
}
