package com.example.handlersimplemessage;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ProgressBar pbConnect;
    Button btnConnect;
    TextView tvStatus;
    Handler handler;

    final int STATUS_NONE = 0, STATUS_CONNECTING = 1, STATUS_CONNECTED = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pbConnect = (ProgressBar) findViewById(R.id.pbConnect);
        btnConnect = (Button) findViewById(R.id.btnConnect);
        tvStatus = (TextView) findViewById(R.id.tvStatus);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                switch (msg.what) {
                    case STATUS_NONE:
                        tvStatus.setText("Нет подключения");
                        pbConnect.setVisibility(View.GONE);
                        btnConnect.setEnabled(true);

                        break;
                    case STATUS_CONNECTING:
                        tvStatus.setText("Подключение ...");
                        pbConnect.setVisibility(View.VISIBLE);
                        btnConnect.setEnabled(false);

                        break;
                    case STATUS_CONNECTED:
                        tvStatus.setText("Подключено.");
                        pbConnect.setVisibility(View.GONE);

                        break;
                }
            }
        };
        handler.sendEmptyMessage(STATUS_NONE);
    }

    public void onclick(View view) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(STATUS_CONNECTING);

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.sendEmptyMessage(STATUS_CONNECTED);

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.sendEmptyMessage(STATUS_NONE);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
