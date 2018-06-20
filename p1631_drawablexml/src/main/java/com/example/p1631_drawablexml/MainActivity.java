package com.example.p1631_drawablexml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);

        setDrawable();
    }

    private void setDrawable() {
        //imageView.setBackgroundResource(R.drawable.drawable_bitmap_repeat);
        //imageView.setBackgroundResource(R.drawable.drawable_tilemode_repeat);
        //imageView.setBackgroundResource(R.drawable.drawable_tilemode_mirror);
        //imageView.setBackgroundResource(R.drawable.drawable_tilemode_clump);
        imageView.setBackgroundResource(R.drawable.drawable_layer_list);
    }
}
