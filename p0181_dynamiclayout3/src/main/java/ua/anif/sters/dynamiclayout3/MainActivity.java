package ua.anif.sters.dynamiclayout3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    Button btnButton1;
    Button btnButton2;
    SeekBar sbButtonWeight;

    LinearLayout.LayoutParams layoutParams1;
    LinearLayout.LayoutParams layoutParams2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnButton1 = (Button) findViewById(R.id.btn1);
        btnButton2 = (Button) findViewById(R.id.btn2);
        sbButtonWeight = (SeekBar) findViewById(R.id.sbWeight);
        sbButtonWeight.setOnSeekBarChangeListener(this);
        layoutParams1 = (LinearLayout.LayoutParams) btnButton1.getLayoutParams();
        layoutParams2 = (LinearLayout.LayoutParams) btnButton2.getLayoutParams();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        layoutParams1.weight = i;
        layoutParams2.weight = sbButtonWeight.getMax() - i;
        btnButton1.setLayoutParams(layoutParams1);
        btnButton2.setLayoutParams(layoutParams2);

        btnButton1.setText(String.valueOf(i));
        btnButton2.setText(String.valueOf(sbButtonWeight.getMax() - i));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
