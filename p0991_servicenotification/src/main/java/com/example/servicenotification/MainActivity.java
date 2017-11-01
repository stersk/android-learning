package com.example.servicenotification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final static String FILE_NAME = "filename";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String text = intent.getStringExtra(FILE_NAME);
        if (!TextUtils.isEmpty(text)) {
            TextView tv = findViewById(R.id.tv);
            tv.setText(text);
        }
    }

    public void onClickStart(View view){
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public void onClickStop(View view){
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }
}
