package com.example.alertdialogsimple;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final int ALERT = 1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onclick(View view){
        showDialog(ALERT);
    }

    @Override
    public void onBackPressed() {
        showDialog(ALERT);
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if (id == ALERT) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Заголовок диалога");
            adb.setMessage("Это вся разшифровка всего диалога");
            adb.setNeutralButton("Отмена", myClickListener);
            adb.setNegativeButton("Нет", myClickListener);
            adb.setPositiveButton("Ok", myClickListener);
            adb.setCancelable(false);

            return adb.create();
        }

        return super.onCreateDialog(id);
    }

    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                // положительная кнопка
                case Dialog.BUTTON_POSITIVE:
                    saveData();
                    finish();
                    break;
                // негативная кнопка
                case Dialog.BUTTON_NEGATIVE:
                    finish();
                    break;
                // нейтральная кнопка
                case Dialog.BUTTON_NEUTRAL:
                    break;
            }
        }
    };

    private void saveData() {
        Toast.makeText(this, "Ок", Toast.LENGTH_LONG).show();
    }

}
