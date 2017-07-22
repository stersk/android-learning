package ua.anif.sters.twoactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnOpenSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btnActTwo:
                        Intent newActivity = new Intent(view.getContext(), Main2Activity.class);
                        startActivity(newActivity);
                        break;
                    default:
                        break;

                }
            }
        };

        btnOpenSecond = (Button) findViewById(R.id.btnActTwo);
        btnOpenSecond.setOnClickListener(clickListener);
    }
}
