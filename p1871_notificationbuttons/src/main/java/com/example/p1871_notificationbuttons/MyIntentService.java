package com.example.p1871_notificationbuttons;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 */
public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
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
    }
}
