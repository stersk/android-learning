package ua.anif.sters.activityresult;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ColorPickerActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnRed;
    Button btnBlue;
    Button btnGreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        btnRed = (Button) findViewById(R.id.btnColorRed);
        btnBlue = (Button) findViewById(R.id.btnColorBlue);
        btnGreen = (Button) findViewById(R.id.btnColorGreen);

        btnRed.setOnClickListener(this);
        btnBlue.setOnClickListener(this);
        btnGreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = getIntent();
        int color = 0;

        switch (view.getId()) {
            case R.id.btnColorRed:
                color = Color.RED;
                break;
            case R.id.btnColorBlue:
                color = Color.BLUE;
                break;
            case R.id.btnColorGreen:
                color = Color.GREEN;
                break;
        }

        intent.putExtra("color", color);
        setResult(RESULT_OK, intent);
        finish();
    }
}
