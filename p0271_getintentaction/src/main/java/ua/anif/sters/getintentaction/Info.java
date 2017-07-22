package ua.anif.sters.getintentaction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Info extends AppCompatActivity {

    TextView tvInfo;
    String formatterString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        Intent intent = getIntent();
        String currentAction = intent.getAction();

        switch (currentAction) {
            case "ua.anif.sters.intent2.action.showtime":
                formatterString = "HH:mm:ss";
                break;
            case "ua.anif.sters.intent2.action.showdate":
                formatterString = "dd.MM.yyyy";
                break;
        }

        Date currentTime = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormatter = new SimpleDateFormat(formatterString);

        tvInfo.setText(dateFormatter.format(currentTime));
    }
}
