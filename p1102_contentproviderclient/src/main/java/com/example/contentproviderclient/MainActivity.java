package com.example.contentproviderclient;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";

    private ContentResolver contentResolver;
    private ContentProviderClient contentClient;

    private ListView listView;
    private EditText teName, teMail;

    static final String CONTACT_ID = "_id";
    static final String CONTACT_NAME = "name";
    static final String CONTACT_EMAIL = "email";

    static final String AUTORITY = "content://com.example.contentprovider";
    static final String CONTACT_PATH = "/contacts/email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lvContact);
        teName = (EditText) findViewById(R.id.teName);
        teMail = (EditText) findViewById(R.id.teMail);

        contentResolver = getContentResolver();
        contentClient = contentResolver.acquireContentProviderClient(AUTORITY);

        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.authority(AUTORITY);
        uriBuilder.path(CONTACT_PATH);
        Uri allData = uriBuilder.build();

        try {
            Cursor cursor = contentClient.query(allData, null, null, null,null);
            String[] cursorFields = new String[] {CONTACT_NAME, CONTACT_EMAIL};
            SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, cursorFields, new int[]{android.R.id.text1,android.R.id.text2});

            listView.setAdapter(simpleCursorAdapter);

            listView.setOnItemClickListener(onListItemClickListener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    AdapterView.OnItemClickListener onListItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            SimpleCursorAdapter viewCursor = (SimpleCursorAdapter) adapterView.getAdapter();
        }
    };

    public void onClickInsert(View view) {

    }

    public void onClickUpdate(View view) {

    }

    public void onClickDelete(View view) {

    }

    public void onClickError(View view) {

    }
}
