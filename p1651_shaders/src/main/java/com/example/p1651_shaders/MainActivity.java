package com.example.p1651_shaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new DrawView(this));
    }

    class DrawView extends View {

        Paint paint,paint2;
        Bitmap bitmap;
        Bitmap scaledBitmap;

        public DrawView(Context context) {
            super(context);
            //Shader shader = createShader();
            //Shader shader = createMatrixShader();
            //Shader shader = createScaleMatrixShader();
            //Shader shader = createGradientShader();
            //Shader shader = createGradientShader2();
            Shader shader = createGradientPositionShader();

            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setShader(shader);

            Shader shader2 = createGradientPositionShaderWider();

            paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint2.setShader(shader2);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);
            canvas.drawRect(100, 100, 400, 300, paint);
            canvas.drawCircle(300, 400, 100, paint);
            canvas.drawRect(100, 500, 400, 700, paint2);
            canvas.drawCircle(300, 800, 100, paint2);
        }

        private Shader createShader() {
            bitmap = BitmapFactory.decodeResource(getResources(),
                    R.mipmap.ic_launcher);
            BitmapShader shader = new BitmapShader(bitmap,
                    Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            return shader;
        }

        private Shader createMatrixShader() {
            bitmap = BitmapFactory.decodeResource(getResources(),
                    R.mipmap.ic_launcher);
            BitmapShader shader = new BitmapShader(bitmap,
                    Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            Matrix matrix = new Matrix();
            matrix.postScale(2, 1.5f);
            matrix.postRotate(45);
            shader.setLocalMatrix(matrix);
            return shader;
        }

        private Shader createScaleMatrixShader() {
            bitmap = BitmapFactory.decodeResource(getResources(),
                    R.mipmap.ic_launcher);
            bitmap = Bitmap.createScaledBitmap(bitmap, 480, 680, true);
            BitmapShader shader = new BitmapShader(bitmap,
                    Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            return shader;
        }

        private Shader createGradientShader() {
            LinearGradient shader = new LinearGradient(0, 0, 100, 20,
                    Color.RED, Color.GREEN, Shader.TileMode.MIRROR);
            return shader;
        }

        private Shader createGradientShader2() {
            LinearGradient shader = new LinearGradient(0, 0, 100, 20,
                    new int[] { Color.RED, Color.BLUE, Color.GREEN }, null,
                    Shader.TileMode.MIRROR);
            return shader;
        }

        private Shader createGradientPositionShader() {
            LinearGradient shader = new LinearGradient(120, 0, 380, 0,
                    new int[] { Color.RED, Color.BLUE, Color.GREEN },
                    new float[] { 0f, 0.5f, 1f }, Shader.TileMode.REPEAT);
            return shader;
        }

        private Shader createGradientPositionShaderWider() {
            LinearGradient shader = new LinearGradient(120, 0, 380, 0,
                    new int[] { Color.RED, Color.BLUE, Color.GREEN },
                    new float[] { 0f, 0.7f, 1f }, Shader.TileMode.REPEAT);
            return shader;
        }
    }
}
