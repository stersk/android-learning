package ua.anif.sters.activityresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        text = (TextView) findViewById(R.id.textView);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

                switch (view.getId()) {
                    case R.id.btnPickAlign:
                        intent = new Intent(view.getContext(), AlignPickerActivity.class);
                        startActivityForResult(intent, 1);
                        break;

                    case R.id.btnPickColor:
                        intent = new Intent(view.getContext(), ColorPickerActivity.class);
                        startActivityForResult(intent, 2);
                        break;
                }
            }
        };

        btnColor.setOnClickListener(onClickListener);
        btnAlign.setOnClickListener(onClickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    text.setGravity(data.getIntExtra("align", 0));
                    Log.d("RESULT", "Result: OK, Align" + String.valueOf(data.getIntExtra("align", 0)));
                    break;
                case 2:
                    text.setTextColor(data.getIntExtra("color", 0));
                    Log.d("RESULT", "Result: OK, Color" + String.valueOf(data.getIntExtra("color", 0)));
                    break;
            }
        } else {
            Log.d("RESULT", "Result: " + String.valueOf(resultCode));
        }
    }
}
