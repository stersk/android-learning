package com.sters.listsimplechoice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "ListChoice";
    ListView listView;
    Button btnChoice;
    String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = getResources().getStringArray(R.array.names);

        listView = (ListView) findViewById(R.id.lvMain);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        ArrayAdapter<CharSequence> adaptor = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_list_item_multiple_choice);
        listView.setAdapter(adaptor);

        btnChoice = (Button) findViewById(R.id.btnChecked);
        final View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
                String logString = "Выбрано: ";
                int iterator;
                int itemsSize = checkedItems.size();
                for (iterator = 0; iterator < itemsSize; iterator++) {
                    if (checkedItems.valueAt(iterator)) {
                        logString += names[checkedItems.keyAt(iterator)] + "; ";
                    }
                }
                Log.d(LOG_TAG, logString);
            }
        };
        btnChoice.setOnClickListener(listener);
    }
}
