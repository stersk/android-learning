package com.example.pendingintent;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    NotificationManager nm;
    AlarmManager am;
    Intent intent1;
    Intent intent2;
    PendingIntent pIntent1;
    PendingIntent pIntent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        am = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

   /* public void onClick1(View view) {
        intent1 = createIntent("action", "extra 1");
        pIntent1 = PendingIntent.getBroadcast(this, 0, intent1, 0);

        intent2 = createIntent("action", "extra 2");
        //pIntent2 = PendingIntent.getBroadcast(this, 0, intent2, 0);
        //pIntent2 = PendingIntent.getBroadcast(this, 0, intent2, PendingIntent.FLAG_CANCEL_CURRENT);
        pIntent2 = PendingIntent.getBroadcast(this, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        compare();

        sendNotif(1, pIntent1);
        sendNotif(2, pIntent2);
    }

    public void onClick2(View view) {
        intent1 = createIntent("action", "extra 1");
        pIntent1 = PendingIntent.getBroadcast(this, 0, intent1, PendingIntent.FLAG_ONE_SHOT);

        sendNotif(1, pIntent1);
        sendNotif(2, pIntent1);
    }*/

    /*public void onClick1(View view) {
        intent1 = createIntent("action", "extra 1");
        pIntent1 = PendingIntent.getBroadcast(this, 0, intent1, 0);
        Log.d(LOG_TAG, "pIntent1 created");
    }

    public void onClick2(View view){
        intent2 = createIntent("action", "extra 2");
        pIntent2 = PendingIntent.getBroadcast(this, 0, intent2, PendingIntent.FLAG_NO_CREATE);
        if (pIntent2 == null) Log.d(LOG_TAG, "pIntent2 is null");
        else Log.d(LOG_TAG, "pIntent2 created");
    }
    */

    /*public void onClick1(View view) {
        intent1 = createIntent("action", "extra 1");
        pIntent1 = PendingIntent.getBroadcast(this, 0, intent1, 0);
        sendNotif(1, pIntent1);
    }

    public void onClick2(View view) {
        pIntent1.cancel();
        Log.d(LOG_TAG, "cancel pIntent1");
    }*/

    /*public void onClick1(View view) {
        intent1 = createIntent("action", "extra 1");
        Uri data1 = Uri.parse(intent1.toUri(Intent.URI_INTENT_SCHEME));
        intent1.setData(data1);
        pIntent1 = PendingIntent.getBroadcast(this, 0, intent1, 0);

        intent2 = createIntent("action", "extra 2");
        Uri data2 = Uri.parse(intent2.toUri(Intent.URI_INTENT_SCHEME));
        intent2.setData(data2);
        pIntent2 = PendingIntent.getBroadcast(this, 0, intent2, 0);

        compare();

        sendNotif(1, pIntent1);
        sendNotif(2, pIntent2);
    }*/

    public void onClick1(View view) {
        intent1 = createIntent("action 1", "extra 1");
        pIntent1 = PendingIntent.getBroadcast(this, 0, intent1, 0);

        intent2 = createIntent("action 2", "extra 2");
        pIntent2 = PendingIntent.getBroadcast(this, 0, intent2, 0);

        Log.d(LOG_TAG, "start");
        am.set(AlarmManager.RTC, System.currentTimeMillis() + 4000, pIntent1);
        am.setRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 3000, 5000, pIntent2);
    }

    public void onClick2(View view) {
        am.cancel(pIntent2);
    }


    Intent createIntent(String action, String extra) {
        Intent intent = new Intent(this, MyReceiver.class);
        intent.setAction(action);
        intent.putExtra("extra", extra);
        return intent;
    }

    void compare() {
        Log.d(LOG_TAG, "intent1 = intent2: " + intent1.filterEquals(intent2));
        Log.d(LOG_TAG, "pIntent1 = pIntent2: " + pIntent1.equals(pIntent2));
    }

    void sendNotif(int id, PendingIntent pIntent) {
        Notification notif = new Notification.Builder(this)
                .setContentTitle("Title " + id)
                .setContentText("Content " + id)
                .setContentIntent(pIntent)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .getNotification();

        notif.flags |= Notification.FLAG_AUTO_CANCEL;
        nm.notify(id, notif);
    }
}
