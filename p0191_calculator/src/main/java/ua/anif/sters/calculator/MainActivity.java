package ua.anif.sters.calculator;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAdd;
    Button btnSub;
    Button btnMultify;
    Button btnDiv;
    Button btnEval;
    Button btnClear;
    Button btnExit;

    TextView tvResult;

    EditText teInputValue;

    float firstArg;
    float secondArg;
    char operation;
    float result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMultify = (Button) findViewById(R.id.btnMultify);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnEval = (Button) findViewById(R.id.btnEval);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnExit = (Button) findViewById(R.id.btnExit);

        tvResult = (TextView) findViewById(R.id.tvResult);

        teInputValue = (EditText) findViewById(R.id.teInputValue);

        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMultify.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnEval.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnExit.setOnClickListener(this);

        firstArg = 0;
        secondArg = 0;
        result = 0;
        operation = ' ';
    }

    @Override
    public void onClick(View view) {
        float value;
        String textRepresentation;

        if (result == 0 && teInputValue.getText().toString() != "") {
            value = Float.parseFloat(teInputValue.getText().toString());
        } else {
            value = result;
            result = 0;
            operation = ' ';
        }

        switch (view.getId()) {
            case R.id.btnClear:
                firstArg = 0;
                secondArg = 0;
                operation = ' ';
                result = 0;

                teInputValue.setText("");
                tvResult.setText("0");
                break;
            case R.id.btnExit:
                finish();
                break;
            case R.id.btnAdd:

                if (value == 0) {
                    Toast.makeText(this, "Не введено значение первого аргумента", Toast.LENGTH_LONG).show();
                    break;
                };

                operation = '+';
                firstArg = value;
                secondArg = 0;

                teInputValue.setText("");

                break;

            case R.id.btnSub:
                if (value == 0) {
                    Toast.makeText(this, "Не введено значение первого аргумента", Toast.LENGTH_LONG).show();
                    break;
                };

                operation = '-';
                firstArg = value;
                secondArg = 0;

                teInputValue.setText("");

                break;

            case R.id.btnMultify:
                if (value == 0) {
                    Toast.makeText(this, "Не введено значение первого аргумента", Toast.LENGTH_LONG).show();
                    break;
                };

                operation = '*';
                secondArg = 0;
                firstArg = value;

                teInputValue.setText("");

                break;

            case R.id.btnDiv:
                if (value == 0) {
                    Toast.makeText(this, "Не введено значение первого аргумента", Toast.LENGTH_LONG).show();
                    break;
                };

                operation = '/';
                firstArg = value;
                secondArg = 0;

                teInputValue.setText("");

                break;

            case R.id.btnEval:
                if (operation == ' ') {
                    Toast.makeText(this, "Не выбрана арифметическая операция", Toast.LENGTH_LONG).show();
                    break;
                }

                if (value == 0) {
                    Toast.makeText(this, "Не введено значение второго аргумента", Toast.LENGTH_LONG).show();
                    break;
                };

                secondArg = value;

                switch (operation) {
                    case '+':
                        result = firstArg + secondArg;
                        break;
                    case '-':
                        result = firstArg - secondArg;
                        break;
                    case '*':
                        result = firstArg * secondArg;
                        break;
                    case '/':
                        result = firstArg / secondArg;
                        break;
                };

                break;
        };

        textRepresentation = "";

        if (firstArg == 0) {
            textRepresentation = "0";
            setResultRepresentation(textRepresentation);
            return;
        } else {
            textRepresentation = textRepresentation + String.valueOf(firstArg);
        }

        if (operation == ' ') {
            setResultRepresentation(textRepresentation);
            return;
        } else {
            textRepresentation = textRepresentation + ' ' + operation;
        }

        if (secondArg == 0) {
            setResultRepresentation(textRepresentation);
            return;
        } else {
            textRepresentation = textRepresentation + ' ' + String.valueOf(secondArg);
        }

        if (result == 0) {
            setResultRepresentation(textRepresentation);
            return;
        } else {
            textRepresentation = textRepresentation + " = " + String.valueOf(result);
            setResultRepresentation(textRepresentation);

            teInputValue.setText(String.valueOf(result));
        }

    }

    private void setResultRepresentation(String text) {
        tvResult.setText(text);
    }
}