package com.example.simpleadaptercustom1;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_VALUE = "value";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    final int positive = android.R.drawable.arrow_up_float;
    final int negative = android.R.drawable.arrow_down_float;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int[] dinamics = { 8, 4, -3, 2, -5, 0, 3, -6, 1, -1 };

        String[] from = new String[]{ATTRIBUTE_NAME_IMAGE, ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_VALUE};
        int[] to = new int[]{R.id.ivImg, R.id.tvText, R.id.tvValue};

        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String,Object> dayData;

        for (int i = 0; dinamics.length > i; i++) {
            dayData = new HashMap<>();
            dayData.put(ATTRIBUTE_NAME_TEXT, "День " + String.valueOf(i));
            dayData.put(ATTRIBUTE_NAME_IMAGE, (dinamics[i] < 0 )? negative: positive);
            dayData.put(ATTRIBUTE_NAME_VALUE, dinamics[i]);

            dataList.add(dayData);
        }

        MySimpleAdaptor myAdaptor = new MySimpleAdaptor(this,
                                                        dataList,
                                                        R.layout.item,
                                                        from,
                                                        to);

        ListView lvMain = (ListView) findViewById(R.id.lvSimple);
        lvMain.setAdapter(myAdaptor);
    }

    private class MySimpleAdaptor extends SimpleAdapter {
        public MySimpleAdaptor(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        int greenColor = Color.GREEN;
        int redColor = Color.RED;

        @Override
        public void setViewImage(ImageView v, int value) {
            super.setViewImage(v, value);
            switch (value) {
                case positive:
                    v.setBackgroundColor(greenColor);
                    break;
                case negative:
                    v.setBackgroundColor(redColor);
                    break;
            }
        }

        @Override
        public void setViewText(TextView v, String text) {
            super.setViewText(v, text);

            if (v.getId() == R.id.tvValue) {
                int value = Integer.parseInt(text);

                if (value > 0) {
                    v.setTextColor(greenColor);
                }

                if (value < 0) {
                    v.setTextColor(redColor);
                }
            }
        }
    }
}
