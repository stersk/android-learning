package com.example.dinamicactionbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    private boolean firstFragmentShown;
    private Fragment1 fragment1;
    private Fragment2 fragment2;

    private final int MENU_ID = 1;

    private CheckBox chbAddDell;
    private CheckBox chbVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();

        chbAddDell = findViewById(R.id.chbVisible);
        chbVisible = findViewById(R.id.chbAddDel);

        firstFragmentShown = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.setGroupVisible(R.id.groupVsbl, chbVisible.isChecked());
        if (chbAddDell.isChecked()) {
            menu.add(0, MENU_ID, 0, R.string.menu_item1)
                    .setIcon(android.R.drawable.ic_delete)
                    .setShowAsAction(
                            MenuItem.SHOW_AS_ACTION_ALWAYS
                                    | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        } else {
            menu.removeItem(MENU_ID);
        }
        return true;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chbAddDel:
            case R.id.chbVisible:
                invalidateOptionsMenu();

                break;

            case R.id.btnFrag:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.cont, firstFragmentShown ? fragment2:fragment1)
                        .commit();
                firstFragmentShown = !firstFragmentShown;
                break;
        }
    }
}
