package com.example.preferencessimple2;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Pref extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.pref);
    }
}
