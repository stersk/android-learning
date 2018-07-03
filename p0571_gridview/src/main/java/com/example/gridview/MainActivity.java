package com.example.gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageAdapter adapter;
    GridView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ImageAdapter(this);

        mainView = (GridView) findViewById(R.id.gridview);
        mainView.setAdapter(adapter);
        mainView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(view.getContext(), "Выбрано картинку с идентфикатором " + adapter.getItem(i), Toast.LENGTH_LONG).show();
            }
        });

        adjustGridView();
    }

    private void adjustGridView(){
        mainView.setNumColumns(GridView.AUTO_FIT);
        mainView.setColumnWidth(100);
        mainView.setVerticalSpacing(15);
        mainView.setHorizontalSpacing(10);
        mainView.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);
    }
}
