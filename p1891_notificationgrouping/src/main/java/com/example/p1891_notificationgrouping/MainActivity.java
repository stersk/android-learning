package com.example.p1891_notificationgrouping;

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

    private String GROUP_KEY = "group_1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onGroupNotificationClick(View view) {

        NotificationCompat.Builder mBuilder;
        Notification notification;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        //Create messages

        mBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Sender " + 1)
                        .setContentText("Subject text " + 1)
                        .setGroup(GROUP_KEY);

        notification = mBuilder.build();

        notificationManager.notify(1, notification);

        mBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Sender " + 2)
                        .setContentText("Subject text " + 2)
                        .setGroup(GROUP_KEY);

        notification = mBuilder.build();

        notificationManager.notify(2, notification);

        mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Sender " + 3)
                .setContentText("Subject text " + 3)
                .setGroup(GROUP_KEY);

        notification = mBuilder.build();

        notificationManager.notify(3, notification);

        mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Sender " + 4)
                .setContentText("Subject text " + 4)
                .setGroup(GROUP_KEY);

        notification = mBuilder.build();

        notificationManager.notify(4, notification);

        mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Sender " + 5)
                .setContentText("Subject text " + 5)
                .setGroup(GROUP_KEY);

        notification = mBuilder.build();

        notificationManager.notify(5, notification);

        //Greate group
        mBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setSubText("user_mail.com")
                        .setGroup(GROUP_KEY)
                        .setGroupSummary(true);

        notification = mBuilder.build();

        notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(-100, notification);
    }
}
