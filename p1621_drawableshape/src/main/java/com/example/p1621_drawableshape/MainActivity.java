package com.example.p1621_drawableshape;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
// TODO: Цей урок чомусь недороблений - доробити

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        setDrawable();
    }

    private void setDrawable() {
        //imageView.setImageResource(R.drawable.shape);

        //textView.setBackgroundResource(R.drawable.shape_padding);

    }
}
