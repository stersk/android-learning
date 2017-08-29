package com.example.alertdialogoprations;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "DIALOGEVENTS";
    final int DIALOG = 1;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "------------------------------");
        Log.d(LOG_TAG, "--------------СТАРТ-----------");
        Log.d(LOG_TAG, "------------------------------");
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG:
                Log.d(LOG_TAG, "Создание диалога");
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                dialogBuilder.setTitle("Диалог для тестирования");
                dialogBuilder.setPositiveButton("Ok", null);
                dialogBuilder.setOnCancelListener(onCancelListener);

                dialog = dialogBuilder.create();
                dialog.setOnShowListener(onShowListener);
                dialog.setOnDismissListener(onDismissListener);

                return dialog;
            default:
                return super.onCreateDialog(id);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(LOG_TAG, "------------------------------");
        Log.d(LOG_TAG, "--------------ФИНИШ-----------");
        Log.d(LOG_TAG, "------------------------------");
    }

    DialogInterface.OnCancelListener onCancelListener = new DialogInterface.OnCancelListener() {
        @Override
        public void onCancel(DialogInterface dialogInterface) {
            Log.d(LOG_TAG, "Срабавывание onCancel");
        }
    };

    DialogInterface.OnDismissListener onDismissListener = new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialogInterface) {
            Log.d(LOG_TAG, "Срабавывание OnDismiss");
        }
    };

    DialogInterface.OnShowListener onShowListener = new DialogInterface.OnShowListener() {
        @Override
        public void onShow(DialogInterface dialogInterface) {
            Log.d(LOG_TAG, "Срабавывание onShow");

            /*
            Этот кусок кода останавливает поток, поэтому onShow сработает, но
            диалог прорисуется тоько после срабатывания закомментированного кода,
            так как он приостанавливает выполнение программы
            try {
                Thread.sleep(2000);
                methodOne();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(2000);
                methodTwo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    methodOne();
                }
            }, 2000);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    methodTwo();
                }
            }, 4000);
        }
    };

    public void onclick(View view) {
        showDialog(DIALOG);
    }

    private void methodOne(){
        Log.d(LOG_TAG, "Выполнение methodOne");
        dialog.hide();
    }

    private void methodTwo(){
        Log.d(LOG_TAG, "Выполнение methodTwo");
        dialog.cancel();
    }
}
