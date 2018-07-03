package com.example.p1671_savelayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
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

        Paint mShaderPaint;
        Paint mBlackPaint;
        Paint mPaint;
        Bitmap mBitmap;
        Rect mRect = new Rect(0, 40, 750, 370);
        RectF mRectF = new RectF(mRect);

        public DrawView(Context context) {
            super(context);
            setLayerType(LAYER_TYPE_SOFTWARE, null);
            init();
        }

        private void init() {
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mBitmap = BitmapFactory.decodeResource(getResources(),
                    R.drawable.image);
            mBitmap = Bitmap.createScaledBitmap(mBitmap, mRect.width(),
                    mRect.height(), true);

            mShaderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mShaderPaint.setShader(createShader());
            mShaderPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

            mBlackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mBlackPaint.setColor(Color.BLACK);
            mBlackPaint.setAlpha(100);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawBitmap(mBitmap, 0, 0, mPaint);
            canvas.saveLayer(mRectF, mPaint, Canvas.ALL_SAVE_FLAG);
            canvas.drawRect(mRect, mBlackPaint);
            canvas.drawOval(mRectF, mShaderPaint);
            canvas.restore();
        }

        private Shader createShader() {
            final int[] colors = new int[] { 0xff000000, 0xff000000, 0 };
            final float[] anchors = new float[] { 0, 0.5f, 1 };
            Shader shader = new android.graphics.RadialGradient(0, 0, 1,
                    colors, anchors, Shader.TileMode.CLAMP);

            Matrix matrix = new Matrix();
            matrix.postTranslate(mRect.centerX(), mRect.centerY());
            matrix.postScale(mRect.width() / 2, mRect.height() / 2,
                    mRect.centerX(), mRect.centerY());

            shader.setLocalMatrix(matrix);
            return shader;
        }

    }
}
