package com.example.simpleadaptercustom2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_PB = "pb";
    final String ATTRIBUTE_NAME_LL = "ll";

    LinearLayout llRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int load[] = { 41, 48, 22, 35, 30, 67, 51, 88 };

        List<Map<String, Object>> rowsData = new ArrayList<>();
        Map<String,Object> rowData;
        for (int i = 0; i < load.length; i++) {
            rowData = new HashMap<>();
            rowData.put(ATTRIBUTE_NAME_TEXT, "День " + String.valueOf(i));
            rowData.put(ATTRIBUTE_NAME_PB, load[i]);
            rowData.put(ATTRIBUTE_NAME_LL, load[i]);

            rowsData.add(rowData);
        }

        String[] from = new String[]{ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_PB, ATTRIBUTE_NAME_PB};
        int[] to = new int[]{R.id.tvLoad, R.id.pbLoad, R.id.llLoad};

        SimpleAdapter myAdapter = new SimpleAdapter(this, rowsData, R.layout.item, from, to);

        MyViewBinder binder = new MyViewBinder();
        myAdapter.setViewBinder(binder);

        ListView lvMain = (ListView) findViewById(R.id.lvSimple);
        lvMain.setAdapter(myAdapter);
    }

    public class MyViewBinder implements SimpleAdapter.ViewBinder {

        @Override
        public boolean setViewValue(View view, Object o, String s) {
            switch (view.getId()) {
                case R.id.pbLoad :
                    ProgressBar progress = (ProgressBar) view;
                    progress.setProgress((Integer) o);
                    return true;

                case R.id.llLoad:
                    int value = (Integer) o;
                    if (value <= 30) {
                        view.setBackgroundColor(getResources().getColor(R.color.Green));
                    } else if (value <= 70) {
                        view.setBackgroundColor(getResources().getColor(R.color.Orange));
                    } else {
                        view.setBackgroundColor(getResources().getColor(R.color.Red));
                    }

                    return true;
                default:
                    return false;
            }
        }
    }
}
