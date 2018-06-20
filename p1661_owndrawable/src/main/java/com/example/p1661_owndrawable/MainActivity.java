package com.example.p1661_owndrawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.imageView);
        //1.
        //Drawable dr = new HexagonDrawable();

        //2.
//      Drawable dr = new ColorHexagonDrawable(Color.RED);
//      dr.setAlpha(50);

        //3.
//      Drawable dr = new BitmapHexagonDrawable(this);
//      view.setBackgroundDrawable(dr);

        //4.
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.picture);
        Drawable dr = new CustomBitmapHexagonDrawable(bitmap);
        view.setBackgroundDrawable(dr);
    }
}
