package ua.anif.sters.twoactivitystate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {

    final String TAG = "States";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.d(TAG, "Обработчик 2 onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "Обработчик 2 onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "Обработчик 2 onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "Обработчик 2 onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "Обработчик 2 onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "Обработчик 2 onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "Обработчик 2 onDestroy");
    }
}
