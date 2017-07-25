package ua.anif.sters.activityresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text;
    Button btnColor;
    Button btnAlign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnColor = (Button) findViewById(R.id.btnPickColor);
        btnAlign = (Button) findViewById(R.id.btnPickAlign);
        text = (TextView) findViewById(R.id.btnPickAlign);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

                switch (view.getId()) {
                    case R.id.btnPickAlign:
                        intent = new Intent(view.getContext(), AlignPickerActivity.class);
                        startActivityForResult(intent, 1);
                        break;

                }
            }
        };
    }


}
