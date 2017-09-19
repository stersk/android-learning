package com.example.asynctaskparams;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    String LOG_TAG = "MyLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
    }

    public void onclick(View view) {
        MyClass classOfMine = new MyClass();
        classOfMine.execute("21", "22", "23");
    }

    class MyClass extends AsyncTask<String, Integer, Void> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            tvInfo.setText("Загружен файл номер " + String.valueOf(values[0]));
        }

        @Override
        protected Void doInBackground(String... strings) {
            int counter = 0;

            for (String value : strings) {
                counter++;
                publishProgress(counter);
                Log.d(LOG_TAG, "Загружается файл \"" + value + "\"");
                donwloadFile();
            }

            return null;
        }
    };

    protected void donwloadFile() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };
}
