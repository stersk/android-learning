package ua.anif.sters.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final int MENU_ALPHA = 1;
    final int MENU_SCALE = 2;
    final int MENU_TRANS = 3;
    final int MENU_ROTATE = 4;
    final int MENU_COMBO = 5;

    TextView tvElement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvElement = (TextView) findViewById(R.id.tv);

        registerForContextMenu(tvElement);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, MENU_ALPHA, 0, "Alpha");
        menu.add(0, MENU_SCALE, 0, "Scale");
        menu.add(0, MENU_TRANS, 0, "Transform");
        menu.add(0, MENU_ROTATE, 0, "Rotate");
        menu.add(0, MENU_COMBO, 0, "Combo");
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem){
        Animation animation = null;

        switch (menuItem.getItemId()) {
            case MENU_ALPHA:
                animation = AnimationUtils.loadAnimation(this, R.anim.myalpha);
                break;
            case MENU_SCALE:
                animation = AnimationUtils.loadAnimation(this, R.anim.myscale);
                break;
            case MENU_TRANS:
                animation = AnimationUtils.loadAnimation(this, R.anim.mytrans);
                break;
            case MENU_ROTATE:
                animation = AnimationUtils.loadAnimation(this, R.anim.myrotate);
                break;
            case MENU_COMBO:
                animation = AnimationUtils.loadAnimation(this, R.anim.mycombo);
                break;

        }

        tvElement.startAnimation(animation);

        return super.onContextItemSelected(menuItem);
    }
}
