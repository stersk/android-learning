package com.example.actionmode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView lvActionMode;
    final String LOG_TAG = "myLogs";

    String[] data = { "one", "two", "three", "four", "five" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, data);
        lvActionMode = findViewById(R.id.lvActionMode);
        lvActionMode.setAdapter(adapter);
        lvActionMode.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lvActionMode.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public boolean onCreateActionMode(android.view.ActionMode actionMode, Menu menu) {
                actionMode.getMenuInflater().inflate(R.menu.context, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(android.view.ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(android.view.ActionMode actionMode, MenuItem menuItem) {
                actionMode.finish();
                return false;
            }

            @Override
            public void onDestroyActionMode(android.view.ActionMode actionMode) {

            }

            @Override
            public void onItemCheckedStateChanged(android.view.ActionMode actionMode, int position, long l, boolean checked) {
                Log.d(LOG_TAG, "position = " + position + ", checked = "
                        + checked);
            }
        });
    }
}
