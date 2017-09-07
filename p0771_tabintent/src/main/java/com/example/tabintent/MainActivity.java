package com.example.tabintent;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = getTabHost();

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("Tab 1");
        tabSpec1.setIndicator("Вкладка 1");
        tabSpec1.setContent(new Intent(this, one.class));

        tabHost.addTab(tabSpec1);

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("Tab 2");
        tabSpec2.setIndicator("Вкладка 2");
        tabSpec2.setContent(new Intent(this, two.class));

        tabHost.addTab(tabSpec2);
    }
}
