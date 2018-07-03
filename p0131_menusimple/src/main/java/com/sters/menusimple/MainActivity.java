package com.sters.menusimple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Пункт меню 1");
        menu.add("Пункт меню 2");
        menu.add("Пункт меню 3");
        menu.add("Пункт меню 4");

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem option){
        Toast.makeText(this, option.getTitle(), Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(option);
    }
}
