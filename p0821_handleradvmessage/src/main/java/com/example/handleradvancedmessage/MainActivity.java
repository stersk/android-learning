package com.example.handleradvancedmessage;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ProgressBar pbConnect, pbDownload;
    Button btnConnect;
    TextView tvStatus;
    Handler handler;
    int filesCount;

    final int STATUS_NONE = 0, STATUS_CONNECTING = 1, STATUS_CONNECTED = 2, STATUS_DOWNLOADING = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pbConnect = (ProgressBar) findViewById(R.id.pbConnect);
        pbDownload = (ProgressBar) findViewById(R.id.pbDownload);
        btnConnect = (Button) findViewById(R.id.btnConnect);
        tvStatus = (TextView) findViewById(R.id.tvStatus);
        filesCount = 5;

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                switch (msg.what) {
                    case STATUS_NONE:
                        if (tvStatus.getText().equals("")) {
                            tvStatus.setText("Нет подключения");
                        } else {
                            tvStatus.setText("Нет подключения (" + tvStatus.getText() + ")");
                            pbDownload.setVisibility(View.GONE);
                        }

                        pbConnect.setVisibility(View.GONE);
                        btnConnect.setEnabled(true);

                        break;
                    case STATUS_CONNECTING:
                        tvStatus.setText("Подключение ...");
                        pbConnect.setVisibility(View.VISIBLE);
                        btnConnect.setEnabled(false);

                        pbDownload.setVisibility(View.GONE);

                        break;
                    case STATUS_CONNECTED:
                        if (msg.arg1 == 0) {
                            tvStatus.setText("Нет файлов к загрузке");
                        } else {
                            tvStatus.setText("Подключено.");
                        }
                        pbConnect.setVisibility(View.GONE);

                        pbDownload.setMax(filesCount + 1);
                        pbDownload.setProgress(0);
                        pbDownload.setVisibility(View.VISIBLE);

                        break;

                    case STATUS_DOWNLOADING:
                        String filesCountText = String.valueOf(msg.arg1) + " из " + String.valueOf(msg.arg2) ;

                        tvStatus.setText("Загрузка ( " + filesCountText + " ) ...");
                        pbConnect.setVisibility(View.GONE);
                        pbDownload.setVisibility(View.VISIBLE);

                        pbDownload.setProgress(msg.arg1);
                        pbDownload.setContentDescription(filesCountText + " файлов");
                }
            }
        };
        handler.sendEmptyMessage(STATUS_NONE);
    }

    public void onclick(View view) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            Random rand = new Random();
            filesCount = rand.nextInt(5);

            try {
                handler.sendEmptyMessage(STATUS_CONNECTING);

                TimeUnit.SECONDS.sleep(2);

                Message messageConnected = handler.obtainMessage(STATUS_CONNECTED, filesCount, 0);
                handler.sendMessage(messageConnected);

                if (filesCount != 0) {
                    for (int i = 1; i <= filesCount; i++) {
                        byte[] fileData = null;
                        try {
                            fileData = downloadFile();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // Здесь мы можем сохраниьб данные
                        saveFile(fileData);

                        // или отдать их главному потоку
                        Message messageProgress = handler.obtainMessage(STATUS_DOWNLOADING, i, filesCount, fileData);
                        handler.sendMessage(messageProgress);
                    }
                }

                handler.sendEmptyMessage(STATUS_NONE);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    byte[] downloadFile() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return new byte[1024];
    }

    void saveFile(byte[] file) {

    }
}
