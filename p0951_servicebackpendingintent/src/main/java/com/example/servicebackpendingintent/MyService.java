package com.example.servicebackpendingintent;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    final String LOG_TAG = "myLogs";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        PendingIntent pIntent = intent.getParcelableExtra("pendingIntent");
        MyRun myRun = new MyRun(startId, 10, pIntent);
        myRun.run();

        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MyService onDestroy");
    }

    void readFlags(int flags) {
        if ((flags&START_FLAG_REDELIVERY) == START_FLAG_REDELIVERY)
            Log.d(LOG_TAG, "START_FLAG_REDELIVERY");
        if ((flags&START_FLAG_RETRY) == START_FLAG_RETRY)
            Log.d(LOG_TAG, "START_FLAG_RETRY");
    }

    class MyRun implements Runnable {

        int startId;
        int delay;
        PendingIntent pIntent;

        public MyRun(int startId, int delay, PendingIntent pIntent) {
            this.startId = startId;
            this.delay = delay;
            this.pIntent = pIntent;

            Log.d(LOG_TAG, "MyRun#" + startId + " create");
        }

        public void run() {
            Log.d(LOG_TAG, "MyRun#" + startId + " start");
            try {
                TimeUnit.SECONDS.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            stop();
        }

        void stop() {
            try {
                pIntent.send(startId);
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }

            Log.d(LOG_TAG, "MyRun#" + startId + " end, stopSelfResult("
                    + startId + ") = " + stopSelfResult(startId));
        }
    }
}
