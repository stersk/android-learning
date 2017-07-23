package ua.anif.sters.intentfilter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class TimeActivity extends AppCompatActivity {

    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        tvTime = (TextView) findViewById(R.id.tvTime);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date currentDate = new Date(System.currentTimeMillis());
        String stringDate = format.format(currentDate);

        tvTime.setText(stringDate);
    }
}
