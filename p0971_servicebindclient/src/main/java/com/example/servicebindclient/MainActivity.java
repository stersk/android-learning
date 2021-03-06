package com.example.servicebindclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent myIntent;
    ServiceConnection serviceConnection;
    boolean bound = false;

    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "Application started.");

        myIntent = new Intent("com.example.servicebindserver.SERVICE");
        myIntent.setPackage("com.example.servicebindserver");

        serviceConnection = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                Log.d(LOG_TAG, "MainActivity onServiceConnected");
                bound = true;
            }

            public void onServiceDisconnected(ComponentName name) {
                Log.d(LOG_TAG, "MainActivity onServiceDisconnected");
                bound = false;
            }
        };
    }

    public void onClickStart(View view) {
        startService(myIntent);
    }

    public void onClickStop(View view) {
        stopService(myIntent);
    }

    public void onClickBind(View view) {
        bindService(myIntent, serviceConnection, BIND_AUTO_CREATE);
    }

    public void onClickUnBind(View view) {
        if (!bound) return;
        unbindService(serviceConnection);
        bound = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onClickUnBind(null);
    }
}
