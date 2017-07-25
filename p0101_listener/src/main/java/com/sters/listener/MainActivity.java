package com.sters.listener;

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

        View.OnClickListener onBtnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (view.getId() == R.id.btnOk) {
                    result.setText("Нажато кнопку ОК");
                } else {
                    result.setText("Нажато кнопку CANCEL");
                }*/
                switch (view.getId()) {
                    case (R.id.btnOk) :
                        result.setText("нажато кнопку ок");
                        break;
                    case (R.id.btnCancel):
                        result.setText("Нажато кнопку CANCEL");
                        break;
                }
            }
        };

        btnOk.setOnClickListener(onBtnClick);
        btnCancel.setOnClickListener(onBtnClick);
    }
}
