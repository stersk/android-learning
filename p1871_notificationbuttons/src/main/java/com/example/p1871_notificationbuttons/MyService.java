package com.example.p1871_notificationbuttons;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (MainActivity.ACTION_REPLY.equals(intent.getAction())) {
            // Get reply text
            CharSequence replyText = null;
            Bundle results = RemoteInput.getResultsFromIntent(intent);
            if (results != null) {
                replyText = results.getCharSequence(MainActivity.EXTRA_ITEM_TEXT);
            }

            // Get itemId
            int itemId = intent.getIntExtra(MainActivity.EXTRA_ITEM_ID, 0);

            // Perform operations with replyText and itemId
            Log.d("Log", "Reply text:" + replyText);
            //       ...

            // Create new notification
            Notification repliedNotification =
                    new NotificationCompat.Builder(getBaseContext())
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentText("Replied")
                            .build();

            // Update notification
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(itemId, repliedNotification);
        }

        return super.onStartCommand(intent, flags, startId);
    }
}
