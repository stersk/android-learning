package com.example.p1871_notificationbuttons;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static String ACTION_REPLY = "com.example.p1871_notificationbuttons.action.reply";
    public static String EXTRA_ITEM_TEXT = "text_reply";
    public static String EXTRA_ITEM_ID = "id_reply";
    private String EXTRA_TEXT_REPLY = "Начальный текст";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onDeleteNotificationClick(View view) {

        int itemId = 1;

        Intent deleteIntent = new Intent(this, MainActivity.class);
        deleteIntent.setAction("ru.startandroid.notifications.action_delete");
        PendingIntent deletePendingIntent = PendingIntent.getActivity(getApplicationContext(),
                itemId, deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // show notification
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Title")
                        .setContentText("Notification text")
                        .addAction(android.R.drawable.ic_delete, "Delete", deletePendingIntent);


        // for Notification autoclose on click on it
        builder.setAutoCancel(true);
        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    public void onReplyNotificationClick(View view) {
        // id
        int itemId = 22;

        // Intent
        //Intent intent = new Intent(this, MyIntentService.class);
        Intent intent = new Intent(this, MyService.class);
        intent.setAction(ACTION_REPLY);
        intent.putExtra(EXTRA_ITEM_ID, itemId);

        // PendingIntent
        PendingIntent replyPendingIntent =
                PendingIntent.getService(getApplicationContext(),
                        itemId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // RemoteInput
        RemoteInput remoteInput = new RemoteInput.Builder(EXTRA_ITEM_TEXT)
                .setLabel(EXTRA_TEXT_REPLY)
                .build();

        // Action
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(android.R.drawable.ic_menu_send,
                        "Reply", replyPendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();
        // show notification
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Title")
                        .setContentText("Notification text")
                        .addAction(action);

        builder.setContentIntent(replyPendingIntent);

        // for Notification autoclose on click on it
        builder.setAutoCancel(true);
        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(itemId, notification);
    }
}
