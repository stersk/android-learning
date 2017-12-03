package com.example.contentprovider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyContactsProvider extends ContentProvider {
    final String LOG_TAG = "myLogs";

    // // Константы для БД
    // БД
    static final String DB_NAME = "mydb";
    static final int DB_VERSION = 1;

    // Таблица
    static final String CONTACT_TABLE = "contacts";

    // Поля
    static final String CONTACT_ID = "_id";
    static final String CONTACT_NAME = "name";
    static final String CONTACT_EMAIL = "email";

    static final String AUTORITY = "content://com.example.contentprovider";
    static final String CONTACT_PATH = "/contacts/email";

    private static final int ALL_EMAILS = 1;
    private static final int EMAIL_ID = 2;

    // Типы данных
    // набор строк
    static final String CONTACT_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTORITY + "." + CONTACT_PATH;

    // одна строка
    static final String CONTACT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTORITY + "." + CONTACT_PATH;

    SQLiteDatabase db;
    MyDbHelper dbHelper;
    UriMatcher uriMatcher = new UriMatcher(1);
    ContentResolver contentResolver;

    @Override
    public void shutdown() {
        super.shutdown();

        Log.d(LOG_TAG, "PROVIDER: shutdown");

        db.close();
        dbHelper.close();
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MyDbHelper(getContext(), DB_NAME, null, DB_VERSION);
        db = dbHelper.getWritableDatabase();

        uriMatcher.addURI(AUTORITY, CONTACT_PATH, ALL_EMAILS);
        uriMatcher.addURI(AUTORITY, CONTACT_PATH + "/#", EMAIL_ID);

        contentResolver = getContext().getContentResolver();

        Log.d(LOG_TAG, "PROVIDER: create");

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Cursor cursor;

        if (s1 == null || s1.isEmpty()) {
            s1 = CONTACT_NAME + " ASC";
        }

        switch (uriMatcher.match(uri)) {
            case ALL_EMAILS:
                Log.d(LOG_TAG, "PROVIDER: query all");
                cursor = db.query(false,CONTACT_TABLE,strings,s,strings1,"","",s1,"");
                cursor.setNotificationUri(contentResolver, Uri.parse(AUTORITY));

                break;

            case EMAIL_ID:
                String contactId = uri.getLastPathSegment();

                Log.d(LOG_TAG, "PROVIDER: query single");
                cursor = db.query(false,CONTACT_TABLE,strings,CONTACT_ID + "=" + contactId,strings1,"","",s1,"");
                cursor.setNotificationUri(contentResolver, Uri.parse(AUTORITY));

                break;

            default:
                throw new IllegalArgumentException();
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri){
        Log.d(LOG_TAG, "getType, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case ALL_EMAILS:
                return CONTACT_CONTENT_TYPE;
            case EMAIL_ID:
                return CONTACT_CONTENT_ITEM_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        switch (uriMatcher.match(uri)) {
            case ALL_EMAILS:
                Log.d(LOG_TAG, "PROVIDER: insert");

                long newId = db.insert(CONTACT_TABLE,null, contentValues);
                Uri resultUri = uri.withAppendedPath(uri, String.valueOf(newId));

                contentResolver.notifyChange(Uri.parse(AUTORITY), null);

                return resultUri;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {

        int deletedCount;

        switch (uriMatcher.match(uri)) {
            case EMAIL_ID:
                Log.d(LOG_TAG, "PROVIDER: delete");

                s = CONTACT_ID + "=" + uri.getLastPathSegment();
                deletedCount = db.delete(CONTACT_TABLE, s, strings);
                contentResolver.notifyChange(Uri.parse(AUTORITY), null);

                return deletedCount;

            case ALL_EMAILS:

                deletedCount = db.delete(CONTACT_TABLE, s, strings);
                contentResolver.notifyChange(Uri.parse(AUTORITY), null);

                return deletedCount;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        int deletedCount;

        switch (uriMatcher.match(uri)) {
            case EMAIL_ID:
                Log.d(LOG_TAG, "PROVIDER: update");

                s = CONTACT_ID + "=" + uri.getLastPathSegment();

                deletedCount = db.update(CONTACT_TABLE, contentValues, s, strings);
                contentResolver.notifyChange(Uri.parse(AUTORITY), null);

                return deletedCount;
            case ALL_EMAILS:
                deletedCount = db.update(CONTACT_TABLE, contentValues, s, strings);
                contentResolver.notifyChange(uri, null);

                return deletedCount;
            default:
                throw new IllegalArgumentException();
        }
    }

    public class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(LOG_TAG, "PROVIDER: database-create");

            sqLiteDatabase.execSQL("create table " + CONTACT_TABLE + " ("
                    + CONTACT_ID + " integer primary key autoincrement,"
                    + CONTACT_NAME + " text,"
                    + CONTACT_EMAIL + " text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}