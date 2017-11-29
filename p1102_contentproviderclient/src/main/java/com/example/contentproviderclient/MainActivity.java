package com.example.contentproviderclient;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    private ContentResolver contentResolver;
    private ContentProviderClient contentClient;

    private ListView listView;

    static final String AUTORITY = "content://com.example.contentprovider";
    static final String CONTACT_PATH = "/contacts/email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lvContact);

        contentResolver = getContentResolver();
        contentClient = contentResolver.acquireContentProviderClient(AUTORITY);

        Uri allEmailsUri = Uri.parse(AUTORITY + CONTACT_PATH);
        try {
            Cursor cursor = contentClient.query(allEmailsUri, null, null, null,null);
            //SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, getResources().getLayout(android.R.layout.simple_list_item_1), cursor, )
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onClickInsert(View view) {

    }

    public void onClickUpdate(View view) {

    }

    public void onClickDelete(View view) {

    }

    public void onClickError(View view) {

    }
}
