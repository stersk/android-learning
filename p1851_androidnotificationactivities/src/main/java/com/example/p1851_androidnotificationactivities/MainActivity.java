package com.example.p1851_androidnotificationactivities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final static String EXTRA_ITEM_ID = "Parameter name";
    private int itemId = 1; // Константа для тестирования, так нужно передавать id открываемого пункта
    private int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onUpdateNotificationClick(View view) {
        Intent thatsNewIntent = new Intent(this, ThatsNewActivity.class);
        thatsNewIntent.putExtra(EXTRA_ITEM_ID, 2);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, thatsNewIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // show notification
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Update")
                        .setContentText("Update you application")
                        .setContentIntent(pendingIntent);

        // for Notification autoclose on click on it
        builder.setAutoCancel(true);
        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(2, notification);

    }

    public void onNewMessageNotificationClick(View view) {
        // Create PendingIntent
        Intent resultIntent = new Intent(this, DetailsActivity.class);
        resultIntent.putExtra(EXTRA_ITEM_ID, itemId);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(DetailsActivity.class);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // show notification
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Title")
                        .setContentText("Notification text")
                        .setContentIntent(resultPendingIntent);

        // for Notification autoclose on click on it
        builder.setContentIntent(resultPendingIntent);
        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notification);
    }

    public void onTextViewClick(View view) {
        Intent detailsIntent = new Intent(this, DetailsActivity.class);
        detailsIntent.putExtra(EXTRA_ITEM_ID, itemId);

        startActivity(detailsIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        notificationManager.cancelAll();
    }
}
