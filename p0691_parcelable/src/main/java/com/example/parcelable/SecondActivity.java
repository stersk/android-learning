package com.example.parcelable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        Log.d(LOG_TAG, "getParcelableExtra");

        MyClass myObj = getIntent().getParcelableExtra(
                MyClass.class.getCanonicalName());

        Log.d(LOG_TAG, "myObj: " + myObj.s + ", " + myObj.integer);
    }

}
