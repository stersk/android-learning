package com.example.servicebackpendingintent;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView1, textView2, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.tvTask1);
        textView2 = (TextView) findViewById(R.id.tvTask2);
        textView3 = (TextView) findViewById(R.id.tvTask3);
    }

    public void onClickStart(View view) {
        textView1.setText("");
        textView2.setText("");
        textView3.setText("");

        Intent serviceIntent = new Intent(this, MyService.class);

        PendingIntent pendingIntent = createPendingResult(1, serviceIntent, 0);

        serviceIntent.putExtra("requestNumber", 1);
        serviceIntent.putExtra("pendingIntent", pendingIntent);
        startService(serviceIntent);

        serviceIntent.putExtra("requestNumber", 2);
        startService(serviceIntent);

        serviceIntent.putExtra("requestNumber", 3);
        startService(serviceIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1:
                textView1.setText("Первый процесс завершен");
                break;

            case 2:
                textView2.setText("Второй процесс завершен");
                break;

            case 3:
                textView3.setText("Третий процесс завершен");
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
