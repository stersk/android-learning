package com.example.p1881_notificationscustom;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCustomNotificationClick(View view) {
        Intent intent = getIntent();
        PendingIntent rootPendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
        remoteViews.setTextViewText(R.id.textView, "Custom notification text");
        remoteViews.setOnClickPendingIntent(R.id.root, rootPendingIntent);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContent(remoteViews);

        // for Notification autoclose on click on it
        builder.setAutoCancel(true);
        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);

    }

    public void onNewCustomNotificationClick(View view) {
        Intent intent = getIntent();
        PendingIntent rootPendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
        remoteViews.setTextViewText(R.id.textView, "Custom notification text");
        remoteViews.setOnClickPendingIntent(R.id.root, rootPendingIntent);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setCustomContentView(remoteViews)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle());

        // for Notification autoclose on click on it
        builder.setAutoCancel(true);
        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(2, notification);

    }

    public void onNewBigCustomNotificationClick(View view) {
        Intent intent = getIntent();
        PendingIntent rootPendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
        remoteViews.setTextViewText(R.id.textView, "Custom notification text");
        remoteViews.setOnClickPendingIntent(R.id.root, rootPendingIntent);

        RemoteViews remoteViewsExtended = new RemoteViews(getPackageName(), R.layout.extended_notification);
        remoteViewsExtended.setTextViewText(R.id.textView, "Extended custom notification text");
        remoteViewsExtended.setOnClickPendingIntent(R.id.root, rootPendingIntent);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setCustomContentView(remoteViews)
                .setCustomBigContentView(remoteViewsExtended)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle());

        // for Notification autoclose on click on it
        builder.setAutoCancel(true);
        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(3, notification);

    }
}
