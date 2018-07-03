package com.example.servicekillclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickStart(View v) {
        Intent serviceIntent = new Intent("com.example.servicekillserver.MyService");
        serviceIntent.setPackage("com.example.servicekillserver");
        serviceIntent.putExtra("name", "value");
        startService(serviceIntent);
    }
}
