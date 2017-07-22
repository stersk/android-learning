package ua.anif.sters.twoactivitystate;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnActivityToggle;
    final String TAG = "States";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnActivityToggle = (Button) findViewById(R.id.btnActTwo);

        View.OnClickListener onToggleBtnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Main2Activity.class);
                startActivity(intent);
            }
        };
        btnActivityToggle.setOnClickListener(onToggleBtnClick);

        Log.d(TAG, "Обработчик onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "Обработчик onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "Обработчик onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "Обработчик onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "Обработчик onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "Обработчик onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "Обработчик onDestroy");
    }
}
