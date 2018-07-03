package com.example.parcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class MyClass implements Parcelable {
    final static String LOG_TAG = "myLogs";

    public String s;
    public int integer;

    public MyClass(String _s, int _i) {
        s = _s;
        integer = _i;
    }

    protected MyClass(Parcel in) {
        Log.d(LOG_TAG, "MyObject(Parcel parcel)");
        s = in.readString();
        integer = in.readInt();
    }

    public static final Creator<MyClass> CREATOR = new Creator<MyClass>() {
        @Override
        public MyClass createFromParcel(Parcel in) {
            Log.d(LOG_TAG, "createFromParcel");
            return new MyClass(in);
        }

        @Override
        public MyClass[] newArray(int size) {
            return new MyClass[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Log.d(LOG_TAG, "writeToParcel");
        parcel.writeString(s);
        parcel.writeInt(integer);
    }
}
