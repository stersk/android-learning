package com.example.tabcontentfactory;

import android.app.TabActivity;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity {

    final String TAB1 = "tab1";
    final String TAB2 = "tab2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec(TAB1);
        tabSpec1.setIndicator("Вкладка 1");
        tabSpec1.setContent(tabContentFactory);

        tabHost.addTab(tabSpec1);

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec(TAB2);
        tabSpec2.setIndicator("Вкладка 2");
        tabSpec2.setContent(tabContentFactory);

        tabHost.addTab(tabSpec2);
    }

    TabHost.TabContentFactory tabContentFactory = new TabHost.TabContentFactory() {
        @Override
        public View createTabContent(String s) {
            View view = null;

            if (s.equals(TAB1)) {
               LayoutInflater inflater = getLayoutInflater();
               view = inflater.inflate(R.layout.tab, null);
            } else if (s.equals(TAB2)) {
                Context context = MainActivity.this;
                ViewGroup.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                LinearLayout ll = new LinearLayout(getBaseContext());
                ll.setLayoutParams(linearParams);

                TextView tvSecond = new TextView(context);
                tvSecond.setLayoutParams(linearParams);
                tvSecond.setText("Это создано с помощью кодинга");

                ll.addView(tvSecond,new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                view = ll;
            }

            return view;
        }
    };
}
