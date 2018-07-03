package ua.anif.sters.intentfilter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateAltActivity extends AppCompatActivity {

    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_alt);

        tvDate = (TextView) findViewById(R.id.tvDateAlt);

        SimpleDateFormat format = new SimpleDateFormat("EEE, MMM d, yyyy");
        Date currentDate = new Date(System.currentTimeMillis());
        String stringDate = format.format(currentDate);

        tvDate.setText(stringDate);
    }
}
