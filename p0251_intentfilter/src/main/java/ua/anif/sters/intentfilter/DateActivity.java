package ua.anif.sters.intentfilter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateActivity extends AppCompatActivity {

    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        tvDate = (TextView) findViewById(R.id.tvDate);

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date currentDate = new Date(System.currentTimeMillis());
        String stringDate = format.format(currentDate);

        tvDate.setText(stringDate);
    }
}
