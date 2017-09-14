package com.example.handlermessagemanage;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler.Callback hc = new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                Log.d(LOG_TAG, "Handler #" + String.valueOf(message.what));

                return true;
            }
        };

        Handler handler = new Handler(hc);

        handler.sendEmptyMessageDelayed(1, 1000);
        handler.sendEmptyMessageDelayed(2, 2000);
        handler.sendEmptyMessageDelayed(3, 4000);

        handler.removeMessages(2);
    }
}
