package com.example.simpleadapterdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    ListView lvMain;
    SimpleAdapter adapter;
    ArrayList<Map<String,Object>> listData;
    Map<String,Object> rowListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] from = new String[]{ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_IMAGE};
        int[] to = new int[]{R.id.tvText, R.id.lvSimple};
        listData = new ArrayList<>();

        adapter = new SimpleAdapter(this, listData, R.layout.item, from, to);

        lvMain = (ListView) findViewById(R.id.lvSimple);
        lvMain.setAdapter(adapter);

        registerForContextMenu(lvMain);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(1,1,1,"Удалить");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo contextMenuInfoAdapter = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int dataIdx = contextMenuInfoAdapter.position;
        listData.remove(dataIdx);

        adapter.notifyDataSetChanged();

        return super.onContextItemSelected(item);
    }

    public void onButtonClick() {
        int count = listData.size() + 1;

        rowListData = new HashMap<>();
        rowListData.put(ATTRIBUTE_NAME_IMAGE, R.mipmap.ic_launcher);
        rowListData.put(ATTRIBUTE_NAME_TEXT, "Строка " + String.valueOf(count));

        listData.add(rowListData);
        adapter.notifyDataSetChanged();
    }
}