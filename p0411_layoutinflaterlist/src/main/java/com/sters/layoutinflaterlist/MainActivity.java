package com.sters.layoutinflaterlist;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String[] name = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
            "Костя", "Игорь", "Марья", "Петр", "Антон", "Даша", };
    final String[] position = { "Программер", "Бухгалтер", "Программер",
            "Программер", "Бухгалтер", "Директор", "Программер", "Охранник", "Бухгалтер", "Директор", "Программер", "Охранник" };
    final int[] salary = { 13000, 10000, 13000, 13000, 10000, 15000, 13000, 8000, 10000, 15000, 13000, 8000 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] colors = new int[] {Color.parseColor("#559966CC"),Color.parseColor("#55336699")};

        LinearLayout llLinear = (LinearLayout) findViewById(R.id.linLayout);
        LayoutInflater inflater = getLayoutInflater();
        TextView tvName, tvPosition, tvSalary;
        View view;

        for (int i = 0; i < name.length; i++) {
            view = inflater.inflate(R.layout.list_item, llLinear, false);
            tvName = view.findViewById(R.id.tvName);
            tvName.setText(name[i]);
            tvPosition = view.findViewById(R.id.tvPosition);
            tvPosition.setText(position[i]);
            tvSalary = view.findViewById(R.id.tvName);
            tvSalary.setText(String.valueOf(salary[i]));
            view.setBackgroundColor(colors[i % 2]);

            llLinear.addView(view);
        }
    }
}
