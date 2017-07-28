package ua.anif.sters.activityresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlignPickerActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLeft;
    Button btnCenter;
    Button btnRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_align_picker);

        btnLeft = (Button) findViewById(R.id.btnAlignOnLeft);
        btnCenter = (Button) findViewById(R.id.btnAlignOnCenter);
        btnRight = (Button) findViewById(R.id.btnAlignOnRight);

        btnLeft.setOnClickListener(this);
        btnCenter.setOnClickListener(this);
        btnRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = getIntent();
        int alligment = 0;
        switch (view.getId()) {
            case R.id.btnAlignOnLeft:
                alligment = Gravity.LEFT;
                break;
            case R.id.btnAlignOnCenter:
                alligment = Gravity.CENTER;
                break;
            case R.id.btnAlignOnRight:
                alligment = Gravity.RIGHT;
                break;
        }

        intent.putExtra("align", alligment);
        setResult(RESULT_OK, intent);
        finish();
    }
}
