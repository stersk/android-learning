package com.example.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnSave, btnLoad;
    EditText etData;
    SharedPreferences dataContainer;
    final String containerName = "SETTINGS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etData = (EditText) findViewById(R.id.etData);

        btnLoad = (Button) findViewById(R.id.btnLoad);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnLoad.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        dataContainer = getPreferences(MODE_PRIVATE);

        loadData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoad:
                loadData();
                Toast.makeText(view.getContext(), "Данные загружены", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnSave:
                saveData();
                Toast.makeText(view.getContext(), "Данные сохранены", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void saveData(){
        String strValue = etData.getText().toString();
        SharedPreferences.Editor editor = dataContainer.edit();
        editor.putString(containerName, strValue);
        editor.commit();
    }

    private void loadData(){
        String strValue = dataContainer.getString(containerName, "");
        etData.setText(strValue);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        saveData();
    }
}
