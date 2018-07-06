package com.example.p1901_notificationchannels;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "channel_1";
    private static final String GROUP_KEY = "group";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onChannelsNotificationClick(View view) {

        NotificationCompat.Builder mBuilder;
        Notification notification;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My channel",
                NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("My channel description");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(false);
            notificationManager.createNotificationChannel(channel);
        }


        //Create messages
        mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Sender " + 1)
                .setContentText("Subject text " + 1)
                .setGroup(GROUP_KEY);

        notification = mBuilder.build();

        notificationManager.notify(1, notification);

        mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Sender " + 2)
                .setContentText("Subject text " + 2)
                .setGroup(GROUP_KEY);

        notification = mBuilder.build();

        notificationManager.notify(2, notification);

        mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Sender " + 3)
                .setContentText("Subject text " + 3)
                .setGroup(GROUP_KEY);

        notification = mBuilder.build();

        notificationManager.notify(3, notification);

        mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Sender " + 4)
                .setContentText("Subject text " + 4)
                .setGroup(GROUP_KEY);

        notification = mBuilder.build();

        notificationManager.notify(4, notification);

        mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Sender " + 5)
                .setContentText("Subject text " + 5)
                .setGroup(GROUP_KEY);

        notification = mBuilder.build();

        notificationManager.notify(5, notification);

        //Greate group
        mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSubText("user_mail.com")
                .setGroup(GROUP_KEY)
                .setGroupSummary(true);

        notification = mBuilder.build();

        notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(-100, notification);
    }

    public void onCheckChannelSettingsClick(View view) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            NotificationChannel channel = notificationManager.getNotificationChannel(CHANNEL_ID);
            if (channel == null) {
                Toast.makeText(this, "Channel not found", Toast.LENGTH_LONG).show();
                return;
            }

            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                Toast.makeText(this, "Channel is switched off!!!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                startActivity(intent);
            } else {
                Toast.makeText(this, "It`s all Ok", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "Notification channels not supported", Toast.LENGTH_LONG).show();
        }
    }

    public void onDeleteChannelClick(View view) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            notificationManager.deleteNotificationChannel(CHANNEL_ID);
                Toast.makeText(this, "Channel deleted!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Notification channels not supported", Toast.LENGTH_LONG).show();
        }
    }
}