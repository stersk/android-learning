package com.example.dialogfragment;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    DialogFragment dlg1;
    DialogFragment dlg2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dlg1 = new Dialog1();
        dlg2 = new Dialog2();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDlg1:
                dlg1.show(getSupportFragmentManager(), "dlg1");
                break;
            case R.id.btnDlg2:
                dlg2.show(getSupportFragmentManager(), "dlg2");
                break;
            default:
                break;
        }

    }
}
