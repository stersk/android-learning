package ua.anif.sters.contextmenuimpl;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView tvColor, tvSize;

    final int MENU_COLOR_RED = 1;
    final int MENU_COLOR_GREEN = 2;
    final int MENU_COLOR_BLUE = 3;

    final int MENU_SIZE_22 = 4;
    final int MENU_SIZE_26 = 5;
    final int MENU_SIZE_30 = 6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvColor = (TextView) findViewById(R.id.tvColor);
        tvSize = (TextView) findViewById(R.id.tvSize);

        registerForContextMenu(tvColor);
        registerForContextMenu(tvSize);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuItem) {

        switch (view.getId()) {
            case R.id.tvSize:
                getMenuInflater().inflate(R.menu.context_menu_2, menu);
                break;
            case R.id.tvColor:
                getMenuInflater().inflate(R.menu.context_menu_1, menu);
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.font_22:
                tvSize.setText("Шрифт 22");
                tvSize.setTextSize(22);
                break;
            case R.id.font_26:
                tvSize.setText("Шрифт 26");
                tvSize.setTextSize(26);
                break;
            case R.id.font_30:
                tvSize.setText("Шрифт 30");
                tvSize.setTextSize(30);
                break;
            case R.id.color_red:
                tvColor.setText("Цвет синий");
                tvColor.setTextColor(Color.BLUE);
                break;
            case R.id.color_green:
                tvColor.setText("Цвет зеленый");
                tvColor.setTextColor(Color.GREEN);
                break;
            case R.id.color_blue:
                tvColor.setText("Цвет красный");
                tvColor.setTextColor(Color.RED);
                break;
        }

        return super.onContextItemSelected(menuItem);
    }

}
