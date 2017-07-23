package ua.anif.sters.intentextras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    TextView teFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        teFullName = (TextView) findViewById(R.id.teFullName);

        Intent intent = getIntent();
        String name = intent.getStringExtra("stName");
        String surName = intent.getStringExtra("stSurName");

        teFullName.setText("Полное имя: " + name + " " + surName + ".");
    }
}
