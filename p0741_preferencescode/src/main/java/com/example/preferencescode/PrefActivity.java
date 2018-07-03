package com.example.preferencescode;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;

public class PrefActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceScreen mainScreen = getPreferenceManager().createPreferenceScreen(this);
        setPreferenceScreen(mainScreen);

        CheckBoxPreference cbp = new CheckBoxPreference(this);
        cbp.setKey("chb1");
        cbp.setSummaryOn("Description of checkbox 1 on");
        cbp.setSummaryOff("Description of checkbox 1 off");
        cbp.setTitle("CheckBox 1");
        mainScreen.addPreference(cbp);

        ListPreference listPreference = new ListPreference(this);
        listPreference.setKey("list");
        listPreference.setEntries(R.array.entries);
        listPreference.setEntryValues(R.array.entries_values);
        listPreference.setSummary("Description of list");
        listPreference.setTitle("List");
        mainScreen.addPreference(listPreference);
        listPreference.setDependency("chb1");

        CheckBoxPreference cbp1 = new CheckBoxPreference(this);
        cbp1.setKey("chb2");
        cbp1.setSummary("Description of checkbox 2");
        cbp1.setTitle("CheckBox 2");
        mainScreen.addPreference(cbp1);

        PreferenceScreen psFirst = mainScreen.getPreferenceManager().createPreferenceScreen(this);
        psFirst.setKey("screen");
        psFirst.setSummary("Description of screen");
        psFirst.setTitle("Screen");
        mainScreen.addPreference(psFirst);
        psFirst.setDependency("chb2");

        CheckBoxPreference cbp2 = new CheckBoxPreference(this);
        cbp2.setKey("chb3");
        cbp2.setSummary("Description of checkbox 3");
        cbp2.setTitle("CheckBox 3");
        psFirst.addPreference(cbp2);

        PreferenceCategory pc1 = new PreferenceCategory(this);
        pc1.setKey("categ1");
        pc1.setSummary("Description of category 1");
        pc1.setTitle("Category 1");
        psFirst.addPreference(pc1);

        final CheckBoxPreference cbp3 = new CheckBoxPreference(this);
        cbp3.setKey("chb4");
        cbp3.setSummary("Description of checkbox 4");
        cbp3.setTitle("CheckBox 4");
        pc1.addPreference(cbp3);

        final PreferenceCategory pc2 = new PreferenceCategory(this);
        pc2.setKey("categ2");
        pc2.setSummary("Description of category 2");
        pc2.setTitle("Category 1");
        psFirst.addPreference(pc2);

        CheckBoxPreference cbp4 = new CheckBoxPreference(this);
        cbp4.setKey("chb5");
        cbp4.setSummary("Description of checkbox 5");
        cbp4.setTitle("CheckBox 5");
        pc2.addPreference(cbp4);

        CheckBoxPreference cbp5 = new CheckBoxPreference(this);
        cbp5.setKey("chb6");
        cbp5.setSummary("Description of checkbox 6");
        cbp5.setTitle("CheckBox 6");
        pc2.addPreference(cbp5);

        pc2.setEnabled(cbp3.isChecked());
        cbp3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                pc2.setEnabled(cbp3.isChecked());
                return false;
            }
        });
    }
}
