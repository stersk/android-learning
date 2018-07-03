package com.example.servicelocalbinding;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    private final String LOG_TAG = "myLogs";
    private boolean processGoing = false;
    private int interval;
    private Timer timer;
    private MyTimerTask processingTask;

    public void startProcessing() {
        if (processGoing) {
            Log.d(LOG_TAG, "Processing already started");

            return;
        }

        timer = new Timer();
        processingTask = new MyTimerTask();

        interval = 1;
        timer.schedule(processingTask, 1000, interval * 1000);
        Log.d(LOG_TAG, "Processing started");
        processGoing = true;
    }

    public void stopProcessing() {
        if (!processGoing) {
            Log.d(LOG_TAG, "Processing not started");
        }

        timer.cancel();
        Log.d(LOG_TAG, "Processing stopped");
        processGoing = false;
    }

    public int increaseProcessingInterval() {
        if (!processGoing) {
            Log.d(LOG_TAG, "Processing not started");

            return interval;
        }

        interval++;
        timer.cancel();
        timer = new Timer();
        processingTask = new MyTimerTask();
        timer.schedule(processingTask, 1000, interval * 1000);

        return interval;
    }

    public int decreaseProcessingInterval() {
        if (!processGoing) {
            Log.d(LOG_TAG, "Processing not started");

            return interval;
        }

        if (interval == 1) {
            Log.d(LOG_TAG, "Can't decrease: It's a minimum interval.");
            return interval;
        }

        interval--;
        timer.cancel();
        timer = new Timer();
        processingTask = new MyTimerTask();
        timer.schedule(processingTask, 1000, interval * 1000);

        return interval;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        if (processGoing) {
            stopProcessing();
        }

        Log.d(LOG_TAG, "Service onUnbind");

        return super.onUnbind(intent);
    }



    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "Service onBind");

        return new MyBinder();
    }

    class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            Date currentTime = Calendar.getInstance().getTime();
            Log.d(LOG_TAG, "Timer tick: " + currentTime.toString());
        }
    }
}
