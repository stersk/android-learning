package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
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
        Paint paintSrc;
        Paint paintDst;
        Paint paintBorder;

        Path pathSrc;
        Path pathDst;

        Bitmap bitmapSrc;
        Bitmap bitmapDst;

        // PorterDuff режим
        //colorDst/colorSrc
        //PorterDuff.Mode mode = PorterDuff.Mode.SRC;
        //PorterDuff.Mode mode = PorterDuff.Mode.DST;
        //PorterDuff.Mode mode = PorterDuff.Mode.DST_ATOP;
        //PorterDuff.Mode mode = PorterDuff.Mode.DST_IN;
        //PorterDuff.Mode mode = PorterDuff.Mode.DST_OUT;
        //PorterDuff.Mode mode = PorterDuff.Mode.DST_OVER;
        //PorterDuff.Mode mode = PorterDuff.Mode.SRC_ATOP;
        //PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        //PorterDuff.Mode mode = PorterDuff.Mode.SRC_OUT;
        //PorterDuff.Mode mode = PorterDuff.Mode.XOR;

        //colorDst1/colorSrc1
        //PorterDuff.Mode mode = PorterDuff.Mode.ADD;

        //colorDst2/colorSrc2
        PorterDuff.Mode mode = PorterDuff.Mode.DST_OUT;

        int colorDst = Color.BLUE;
        int colorSrc = Color.YELLOW;

        int colorDst1 = Color.RED;
        int colorSrc1 = Color.GREEN;

        int colorDst2 = Color.argb(170, 0, 0, 255);
        int colorSrc2 = Color.argb(85, 255, 255, 0);

        public DrawView(Context context) {
            super(context);

            // необходимо для корректной работы
            if (android.os.Build.VERSION.SDK_INT >= 11) {
                setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }

            // DST фигура
            pathDst = new Path();
            pathDst.moveTo(0, 0);
            pathDst.lineTo(500, 0);
            pathDst.lineTo(500, 500);
            pathDst.close();

            // создание DST bitmap
            bitmapDst = createBitmap(pathDst, colorDst2);

            // кисть для вывода DST bitmap
            paintDst = new Paint();

            // SRC фигура
            pathSrc = new Path();
            pathSrc.moveTo(0, 0);
            pathSrc.lineTo(500, 0);
            pathSrc.lineTo(0, 500);
            pathSrc.close();

            // создание SRC bitmap
            bitmapSrc = createBitmap(pathSrc, colorSrc2);

            // кисть для вывода SRC bitmap
            paintSrc = new Paint();
            paintSrc.setXfermode(new PorterDuffXfermode(mode));

            // кисть для рамки
            paintBorder = new Paint();
            paintBorder.setStyle(Paint.Style.STROKE);
            paintBorder.setStrokeWidth(3);
            paintBorder.setColor(Color.BLACK);
        }

        private Bitmap createBitmap(Path path, int color) {
            // создание bitmap и канвы для него
            Bitmap bitmap = Bitmap.createBitmap(500, 500,
                    Bitmap.Config.ARGB_8888);
            Canvas bitmapCanvas = new Canvas(bitmap);

            // создание кисти нужного цвета
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setColor(color);

            // рисование фигуры на канве bitmap
            bitmapCanvas.drawPath(path, paint);

            return bitmap;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.translate(390, 80);

            // DST bitmap
            canvas.drawBitmap(bitmapDst, 0, 0, paintDst);

            // SRC bitmap
            canvas.drawBitmap(bitmapSrc, 0, 0, paintSrc);

            // рамка
            canvas.drawRect(0, 0, 500, 500, paintBorder);

        }
    }
}
