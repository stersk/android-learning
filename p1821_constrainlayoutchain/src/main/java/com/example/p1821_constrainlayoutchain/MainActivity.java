package com.example.p1821_constrainlayoutchain;

import android.os.Bundle;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Group group = (Group) findViewById(R.id.group);
        group.setVisibility(View.GONE);

    }
}
