package ua.anif.sters.intentextras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {

    EditText teName;
    EditText teSurName;
    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        teName = (EditText) findViewById(R.id.tiName);
        teSurName = (EditText) findViewById(R.id.tiSurname);
        btnShow = (Button) findViewById(R.id.btnDisplay);

        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = teName.getText().toString();
                String surName = teSurName.getText().toString();

                if (name.equalsIgnoreCase("") || surName.equalsIgnoreCase("")) {
                    Toast.makeText(view.getContext(), "Не заполнено имя или фамилия", Toast.LENGTH_LONG).show();
                } else {
                    Intent toggleIntent = new Intent("ua.anif.sters.intentextras.action.display");

                    toggleIntent.putExtra("stName", name);
                    toggleIntent.putExtra("stSurName", surName);
                    startActivity(toggleIntent);
                }

            }
        };
        btnShow.setOnClickListener(btnListener);
    }
}
