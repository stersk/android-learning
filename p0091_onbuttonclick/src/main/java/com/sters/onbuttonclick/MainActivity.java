package com.sters.onbuttonclick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Button btnOk;
    Button btnCancel;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        result = (TextView) findViewById(R.id.textResult);

        View.OnClickListener onBtnOkClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("Нажато кнопку ОК");
            }
        };

        btnOk.setOnClickListener(onBtnOkClick);

        View.OnClickListener onBtnCancelClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("Нажато кнопку CANCEL");
            }
        };

        btnCancel.setOnClickListener(onBtnCancelClick);
    }
}
