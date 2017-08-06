package com.sters.layoutinflater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "INFLATER";
    ViewGroup linLayout, relLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linLayout = (ViewGroup) findViewById(R.id.linLayout);
        relLayout = (ViewGroup) findViewById(R.id.relLayout);

        LayoutInflater layoutInflater = getLayoutInflater();
        View view1 = layoutInflater.inflate(R.layout.text, linLayout, true);
        View view2 = layoutInflater.inflate(R.layout.text, relLayout, true);

        ViewGroup.LayoutParams lp = view1.getLayoutParams();
        Log.d(LOG_TAG, "Class of view: " + view1.getClass().toString());
        Log.d(LOG_TAG, "LayoutParams of view: " + lp.getClass().toString());
       //Log.d(LOG_TAG, "Text of view: " + ((TextView) view1).getText());

        lp = view2.getLayoutParams();
        Log.d(LOG_TAG, "Class of view: " + view2.getClass().toString());
        Log.d(LOG_TAG, "LayoutParams of view: " + lp.getClass().toString());
        //Log.d(LOG_TAG, "Text of view: " + ((TextView) view2).getText());
    }
}
