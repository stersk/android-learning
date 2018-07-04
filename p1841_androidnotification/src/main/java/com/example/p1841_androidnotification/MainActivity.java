package com.example.p1841_androidnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private PendingIntent resultPendingIntent;
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent resultIntent = new Intent(this, MainActivity.class);
        resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void onMessageClick(View view) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Title")
                        .setContentText("Notification text")
                        .setContentIntent(resultPendingIntent);

        // for Notification autoclose on click on it
        builder.setAutoCancel(true);

        builder.setContentInfo("Content info");

        builder.setNumber(4);

        // notification cannot be closed
        //builder.setOngoing(true);

        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    public void onUpdateMessageClick(View view) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.ic_dialog_email)
                        .setContentTitle("Title change")
                        .setContentText("Notification text change");

        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    public void onSecondMessageClick(View view) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.ic_dialog_email)
                        .setContentTitle("Second title")
                        .setContentText("Second notification text");

        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(2, notification);
    }

    public void onFirstMessageDeleteClick(View view) {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(1);
    }

    public void onAllMessageDeleteClick(View view) {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    public void onMessageWithProgressClick(View view) {
        final int max = 100;

        // show notification with indeterminate progressbar
        builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Some operation")
                .setContentText("Preparing")
                .setProgress(max,0, true);

        notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());


        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int progress = 0;

                while (progress < max) {

                    try {
                        TimeUnit.MILLISECONDS.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    progress += 10;

                    // show notification with current progress
                    builder.setProgress(max, progress, false)
                            .setContentText(progress + " of " + max);
                    notificationManager.notify(1, builder.build());

                }

                // show notification without progressbar
                builder.setProgress(0, 10, false)
                        .setContentText("Completed");
                notificationManager.notify(1, builder.build());
            }
        }).start();
    }
}
