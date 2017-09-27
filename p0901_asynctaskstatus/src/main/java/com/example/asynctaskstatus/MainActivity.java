package com.example.asynctaskstatus;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public MyClass classOfMine;
    final public String LOG_TAG = "MyLog";
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnStart).setEnabled(false);
        findViewById(R.id.btnCancel).setEnabled(false);

        tvStatus = (TextView) findViewById(R.id.tvStatus);
    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btnCreate:
                classOfMine = new MyClass();

                findViewById(R.id.btnStart).setEnabled(true);
                findViewById(R.id.btnCancel).setEnabled(true);

                break;
            case R.id.btnStart:
                findViewById(R.id.btnCreate).setEnabled(false);
                findViewById(R.id.btnStart).setEnabled(false);
                findViewById(R.id.btnCancel).setEnabled(true);

                classOfMine.execute("21", "22", "23", "24", "25", "26");

                break;
            case R.id.btnCancel:
                Log.d(LOG_TAG, "Прерывание загрузки...");
                classOfMine.cancel(true);
                Log.d(LOG_TAG, "Загрузку превано...");

                findViewById(R.id.btnStart).setEnabled(true);
                findViewById(R.id.btnCancel).setEnabled(false);

                break;

            case R.id.btnStatus:
                AsyncTask.Status status = classOfMine.getStatus();
                switch (status) {
                    case FINISHED:
                        if (classOfMine.isCancelled()) {
                            tvStatus.setText("Status: canceled");
                        } else {
                            tvStatus.setText("Status: finished");
                        }
                        break;
                    case RUNNING:
                        tvStatus.setText("Status: running");
                        break;
                    case PENDING:
                        tvStatus.setText("Status: pending");
                        break;
                    default:
                        tvStatus.setText("Status: no status");
                }
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
                if (isCancelled()) {
                    Log.d(LOG_TAG, "Поток загрузки прервано");
                    return "";
                }

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

            findViewById(R.id.btnCreate).setEnabled(true);
            findViewById(R.id.btnStart).setEnabled(false);
            findViewById(R.id.btnCancel).setEnabled(false);

            Log.d(LOG_TAG, "from onPostExecute: " + s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.d(LOG_TAG, "from onCanceled: Задачу прервано");

            findViewById(R.id.btnCreate).setEnabled(true);
            findViewById(R.id.btnStart).setEnabled(false);
            findViewById(R.id.btnCancel).setEnabled(false);
        }
    }

    protected void donwloadFile() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
