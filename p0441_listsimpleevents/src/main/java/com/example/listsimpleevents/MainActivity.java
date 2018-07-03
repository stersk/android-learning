package com.example.listsimpleevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "ListChoiceEvents";
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
        View.OnClickListener listener = new View.OnClickListener() {
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

        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(LOG_TAG, "Клик по пункту: " + names[i]);
            }
        };

        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(LOG_TAG, "Выбран пункт: " + names[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d(LOG_TAG, "Сняты пометки со всех пунктов");
            }
        };

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                String mode;
                switch (i) {
                    case (AbsListView.OnScrollListener.SCROLL_STATE_FLING):
                        mode = "SCROLL_STATE_FLING (Скролл пошел \"по инерции\")";
                        break;

                    case (AbsListView.OnScrollListener.SCROLL_STATE_IDLE):
                        mode = "SCROLL_STATE_IDLE (Скролл окончен)";
                        break;

                    case (AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL):
                        mode = "SCROLL_STATE_TOUCH_SCROLL (Начало скролла)";
                        break;

                    default:
                        mode = "Непонятно что. Код:" + String.valueOf(i);
                        break;
                }

                Log.d(LOG_TAG, "Скролл: смена режима на: " + mode + ".");
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                Log.d(LOG_TAG, "Скролл: Первый видимый: " + names[i] + ", видимо элементов: "
                    + String.valueOf(i1) + ", всего элементов: " + String.valueOf(i2) + ";");
            }
        });

        btnChoice.setOnClickListener(listener);
        listView.setOnItemClickListener(onItemClickListener);
        listView.setOnItemSelectedListener(onItemSelectedListener);
    }
}
