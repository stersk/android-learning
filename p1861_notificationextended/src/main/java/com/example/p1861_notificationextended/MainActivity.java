package com.example.p1861_notificationextended;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBigTextStyleNotificationClick(View view) {

        String longText = "To have a notification appear in an expanded view, " +
                "first create a NotificationCompat.Builder object " +
                "with the normal view options you want. " +
                "Next, call Builder.setStyle() with an " +
                "expanded layout object as its argument.";

        // show notification
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notification")
                        .setContentText("BigTextStyle")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(longText));

        // for Notification autoclose on click on it
        builder.setAutoCancel(true);
        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    public void onBigPictureStyleNotificationClick(View view) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round, options);

        // show notification
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notification")
                        .setContentText("BigPictureStyle")
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));

        // for Notification autoclose on click on it
        builder.setAutoCancel(true);
        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(2, notification);
    }

    public void onInboxStyleNotificationClick(View view) {

        // show notification
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notification")
                        .setContentText("InboxStyle")
                        .setStyle(new NotificationCompat.InboxStyle()
                                .addLine("Line 1")
                                .addLine("Line 2")
                                .addLine("Line 3")
                                .setBigContentTitle("Extended title") // эти два метода есть у всех стилях
                                .setSummaryText("+5 more"));

        // for Notification autoclose on click on it
        builder.setAutoCancel(true);
        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(3, notification);
    }

    public void onMessagingStyleNotificationClick(View view) {

        NotificationCompat.MessagingStyle messagingStyle = new NotificationCompat.MessagingStyle("You");
        messagingStyle.setConversationTitle("Android chat")
                .addMessage("Всем привет!", System.currentTimeMillis(), "Ivan")
                .addMessage("Кто перешел на новую студию, как оно?", System.currentTimeMillis(), "Ivan")
                .addMessage("Я пока не переходил, жду отзывов", System.currentTimeMillis(), "Andrey")
                .addMessage("Я перешел", System.currentTimeMillis(), "")
                .addMessage("Было несколько проблем, но все решаемо", System.currentTimeMillis(), "")
                .addMessage("Ок, спасибо!", System.currentTimeMillis(), "Ivan");

        // show notification
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notification")
                        .setContentText("InboxStyle")
                        .setStyle(messagingStyle);

        // for Notification autoclose on click on it
        builder.setAutoCancel(true);
        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(3, notification);
    }

}
