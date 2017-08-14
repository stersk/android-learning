package com.example.simpecursortreeadapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorTreeAdapter;

public class MainActivity extends AppCompatActivity {

    ExpandableListView elvMain;
    DB myDb;
    Cursor companies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DB(this);
        myDb.open();

        companies = myDb.getCompanies();
        String[] groupFrom = new String[]{myDb.COMPANY_COLUMN_NAME};
        int[] groupTo = new int[]{android.R.id.text1};

        String[] childFrom = new String[]{myDb.PHONE_COLUMN_NAME};
        int[] childTo = new int[]{android.R.id.text1};

        Cursor test = myDb.getAllPhonesData();

        MySimpleCursorTreeAdaptor myAdaptor = new MySimpleCursorTreeAdaptor(this,
                companies,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo);

        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(myAdaptor);
    }

    private class MySimpleCursorTreeAdaptor extends SimpleCursorTreeAdapter {

        public MySimpleCursorTreeAdaptor(Context context, Cursor cursor, int groupLayout, String[] groupFrom, int[] groupTo, int childLayout, String[] childFrom, int[] childTo) {
            super(context, cursor, groupLayout, groupFrom, groupTo, childLayout, childFrom, childTo);
        }

        @Override
        protected Cursor getChildrenCursor(Cursor cursor) {
            int idColumnIdx = cursor.getColumnIndex("_id");

            return myDb.getCompanyPhonesData(cursor.getInt(idColumnIdx));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        myDb.close();
    }
}
