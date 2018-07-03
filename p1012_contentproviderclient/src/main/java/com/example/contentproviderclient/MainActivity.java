package com.example.contentproviderclient;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

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

    private int rowIndex = -1;

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
            SimpleCursorAdapter adapter = (SimpleCursorAdapter) adapterView.getAdapter();
            Cursor cursor = adapter.getCursor();
            cursor.moveToPosition(i);
            int idIndex = cursor.getColumnIndex(CONTACT_ID);
            int nameIndex = cursor.getColumnIndex(CONTACT_NAME);
            int mailIndex = cursor.getColumnIndex(CONTACT_EMAIL);

            rowIndex = cursor.getInt(idIndex);
            teName.setText(cursor.getString(nameIndex));
            teMail.setText(cursor.getString(mailIndex));
        }
    };

    public void onClickInsert(View view) {
        String name, email;
        name = teName.getText().toString();
        email = teMail.getText().toString();

        if (name.isEmpty()) {
            Toast.makeText(this, "Не заполнено имя!", Toast.LENGTH_LONG).show();

            return;
        }

        if (email.isEmpty()) {
            Toast.makeText(this, "Не заполнен адрес почты!", Toast.LENGTH_LONG).show();

            return;
        }

        ContentValues cv = new ContentValues();
        cv.put(CONTACT_NAME, name);
        cv.put(CONTACT_EMAIL, email);

        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.authority(AUTORITY);
        uriBuilder.path(CONTACT_PATH);
        Uri allData = uriBuilder.build();

        try {
            contentClient.insert(allData, cv);
            Toast.makeText(this, "Новую запись создано", Toast.LENGTH_LONG).show();

            rowIndex = -1;
            teName.setText("");
            teMail.setText("");
        } catch (RemoteException e) {
            Log.d(LOG_TAG, "CLIENT: insert error: " + e.getMessage());
        }

    }

    public void onClickUpdate(View view) {
        String name, email;
        name = teName.getText().toString();
        email = teMail.getText().toString();

        if (rowIndex < 0) {
            Toast.makeText(this, "Не выбрана строка для редактирования!", Toast.LENGTH_LONG).show();

            return;
        }

        if (name.isEmpty()) {
            Toast.makeText(this, "Не заполнено имя!", Toast.LENGTH_LONG).show();

            return;
        }

        if (email.isEmpty()) {
            Toast.makeText(this, "Не заполнен адрес почты!", Toast.LENGTH_LONG).show();

            return;
        }

        ContentValues cv = new ContentValues();
        cv.put(CONTACT_NAME, name);
        cv.put(CONTACT_EMAIL, email);

        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.authority(AUTORITY);
        uriBuilder.path(CONTACT_PATH);
        uriBuilder.appendPath(String.valueOf(rowIndex));
        Uri allData = uriBuilder.build();

        try {
            contentClient.update(allData, cv, null, null);
            Toast.makeText(this, "Запись обновлено", Toast.LENGTH_LONG).show();
        } catch (RemoteException e) {
            Log.d(LOG_TAG, "CLIENT: update error: " + e.getMessage());
        }
    }

    public void onClickDelete(View view) {
        if (rowIndex < 0) {
            Toast.makeText(this, "Не выбрана строка для удаления!", Toast.LENGTH_LONG).show();

            return;
        }

        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.authority(AUTORITY);
        uriBuilder.path(CONTACT_PATH);
        uriBuilder.appendPath(String.valueOf(rowIndex));
        Uri allData = uriBuilder.build();

        try {
            contentClient.delete(allData, null, null);

            rowIndex = -1;
            teName.setText("");
            teMail.setText("");

            Toast.makeText(this, "Запись удалено", Toast.LENGTH_LONG).show();
        } catch (RemoteException e) {
            Log.d(LOG_TAG, "CLIENT: delete error: " + e.getMessage());
        }
    }

    public void onClickError(View view) {
        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.authority(AUTORITY);
        uriBuilder.path("contacts/phones");
        uriBuilder.appendPath(String.valueOf(rowIndex));
        Uri allData = uriBuilder.build();

        try {
            Cursor cursor = getContentResolver().query(allData, null, null, null, null);
        } catch (Exception ex) {
            Log.d(LOG_TAG, "Error: " + ex.getClass() + ", " + ex.getMessage());
        }
    }
}
