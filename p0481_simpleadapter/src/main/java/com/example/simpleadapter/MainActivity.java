package com.example.simpleadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // имена атрибутов для Map
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_CHECKED = "checked";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    ListView lvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] texts = { "sometext 1", "sometext 2", "sometext 3",
                "sometext 4", "sometext 5" };
        boolean[] checked = { true, false, false, true, false };
        int img = R.mipmap.ic_launcher;

        String[] from = new String[] {ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_CHECKED, ATTRIBUTE_NAME_IMAGE, ATTRIBUTE_NAME_TEXT};
        int[] to = new int[]{R.id.tvText, R.id.cbChecked, R.id.ivImg, R.id.cbChecked};

        ArrayList<Map<String,Object>> rowsData = new ArrayList<>();
        Map<String,Object> rowData;

        int rowsCount = texts.length;
        for (int i = 0; i < rowsCount; i++) {
            rowData = new HashMap<>();
            rowData.put(ATTRIBUTE_NAME_TEXT, texts[i]);
            rowData.put(ATTRIBUTE_NAME_CHECKED, checked[i]);
            rowData.put(ATTRIBUTE_NAME_IMAGE, img);

            rowsData.add(rowData);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, rowsData, R.layout.item, from, to);
        lvMain = (ListView) findViewById(R.id.lvSimple);
        lvMain.setAdapter(simpleAdapter);
    }
}
