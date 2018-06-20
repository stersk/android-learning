package com.example.p1641_drawablexml2;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
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
        //setDrawable();
        //setDrawableTransition();
        //setDrawableClip();
        setDrawableAnimation();
    }

    private void setDrawable() {
        imageView.setBackgroundResource(R.drawable.levels);

        Drawable drawable = imageView.getBackground();
        drawable.setLevel(1);
    }

    private void setDrawableTransition() {
        imageView.setBackgroundResource(R.drawable.drawable_transition);

        TransitionDrawable drawable = (TransitionDrawable) imageView.getBackground();
        drawable.startTransition(2500);
        //drawable.reverseTransition(5000);
    }

    private void setDrawableClip() {
        //imageView.setBackgroundResource(R.drawable.drawable_clip);
        //imageView.setBackgroundResource(R.drawable.drawable_clip_vertical);
        //imageView.setBackgroundResource(R.drawable.drawable_horizontal_vertical);
        //imageView.setBackgroundResource(R.drawable.drawable_clip_horizontal);
        //imageView.setBackgroundResource(R.drawable.drawable_scale);
        //imageView.setBackgroundResource(R.drawable.drawable_scale_bottom);
        //imageView.setBackgroundResource(R.drawable.drawable_scale_both);
        imageView.setBackgroundResource(R.drawable.drawable_scale_center);

        Drawable drawable = imageView.getBackground();
        drawable.setLevel(1);
    }

    private void setDrawableAnimation() {
        imageView.setBackgroundResource(R.drawable.drawable_animation);

        AnimationDrawable drawable = (AnimationDrawable) imageView.getBackground();
        //drawable.setOneShot(false);
        drawable.start();
    }
}
