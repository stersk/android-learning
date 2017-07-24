package ua.anif.sters.simpleactivityresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnOk;
    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.etName);
        etName.setText("");
    }

    @Override
    public void onClick(View view) {
        String name = etName.getText().toString();

        if (name.equalsIgnoreCase("")) {
            Toast.makeText(view.getContext(), "Не введено имя", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = getIntent();
            intent.putExtra("name", name);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
