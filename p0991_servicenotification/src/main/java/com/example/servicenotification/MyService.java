package com.example.servicenotification;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;



public class MyService extends Service {
    NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
