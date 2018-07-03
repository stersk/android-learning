package com.sters.logandmessages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private static final String TAG = "sters";

    Button btnOk;
    Button btnCancel;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Знадем об'єкти view");
        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        result = (TextView) findViewById(R.id.textResult);

        Log.d(TAG, "Присвоїмо обробники подій");
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG,"Обробка події: визначення джерела");
        switch (view.getId()) {
            case (R.id.btnOk):
                Log.d(TAG,"Обробка події кнопка Ok");
                result.setText("Нажато кнопку Ок");
                Toast.makeText(this, "Нажато кнопку Ок", Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnCancel):
                Log.d(TAG,"Обробка події кнопка Cancel");
                result.setText("Нажато кнопку CANCEL");
                Toast.makeText(this, "Нажато кнопку CANCEL", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void onHelloClick(View view){
        result.setText("Hello world");
    }
}