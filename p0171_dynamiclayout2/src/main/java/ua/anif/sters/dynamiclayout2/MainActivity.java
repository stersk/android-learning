package ua.anif.sters.dynamiclayout2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout llLayout;
    Button btnAdd;
    Button btnClear;
    TextView tvResult;
    RadioGroup rgAlign;

    int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llLayout = (LinearLayout) findViewById(R.id.llMain);
        btnAdd = (Button) findViewById(R.id.btnCreate);
        btnClear = (Button) findViewById(R.id.btnClear);
        tvResult = (TextView) findViewById(R.id.etName);
        rgAlign = (RadioGroup) findViewById(R.id.rgGravity);

        btnAdd.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int btnGravity = Gravity.LEFT;
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(wrapContent, wrapContent);

        switch (rgAlign.getCheckedRadioButtonId()) {
            case R.id.rbLeft:
                btnGravity = Gravity.LEFT;
                break;
            case R.id.rbCenter:
                btnGravity = Gravity.CENTER;
                break;
            case R.id.rbRight:
                btnGravity = Gravity.RIGHT;
                break;
        }

        llParams.gravity = btnGravity;

        switch (view.getId()) {
            case R.id.btnCreate:
                Button newButton = new Button(this);
                String buttonLabel = tvResult.getText().toString();

                newButton.setText(buttonLabel);

                llLayout.addView(newButton, llParams);

                break;
            case R.id.btnClear:
                llLayout.removeAllViews();
                Toast.makeText(this, "Удалено", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
