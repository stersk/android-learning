package com.example.asynctaskresult;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "MyLog";
    MyClass classOfMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnGet).setEnabled(false);
        findViewById(R.id.btnPostGet).setEnabled(false);
    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                findViewById(R.id.btnStart).setEnabled(false);

                findViewById(R.id.btnGet).setEnabled(true);
                findViewById(R.id.btnPostGet).setEnabled(true);

                classOfMine = new MyClass();
                classOfMine.execute("21", "22", "23", "24", "25", "26");

                break;
            case R.id.btnGet:
                findViewById(R.id.btnStart).setEnabled(false);
                findViewById(R.id.btnGet).setEnabled(false);
                findViewById(R.id.btnPostGet).setEnabled(false);

                try {
                    String result = classOfMine.get();

                    Log.d(LOG_TAG, "The sipmple get result:" + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.btnPostGet:
                findViewById(R.id.btnStart).setEnabled(false);
                findViewById(R.id.btnGet).setEnabled(false);
                findViewById(R.id.btnPostGet).setEnabled(false);

                try {
                    String result = classOfMine.get(5,TimeUnit.SECONDS);

                    Log.d(LOG_TAG, "The sipmple get result:" + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    Log.d(LOG_TAG, "Retrieve result timeout");

                    e.printStackTrace();
                }
                break;
        }

    }

    class MyClass extends AsyncTask<String, Integer, String> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings) {
            int counter = 0;

            for (String value : strings) {
                counter++;
                publishProgress(counter);
                Log.d(LOG_TAG, "Загружается файл \"" + value + "\"");
                donwloadFile();
            }

            return "Result: That's ok";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            findViewById(R.id.btnStart).setEnabled(true);
            findViewById(R.id.btnGet).setEnabled(false);
            findViewById(R.id.btnPostGet).setEnabled(false);

            Log.d(LOG_TAG, "from onPostExecute: " + s);
        }
    };

    protected void donwloadFile() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };
}
