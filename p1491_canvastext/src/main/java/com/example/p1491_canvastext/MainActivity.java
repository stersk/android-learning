package com.example.p1491_canvastext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView2(this));
    }

    class DrawView extends View {

        Paint fontPaint;
        Paint redPaint;
        String text = "Test width text";
        int fontSize = 100;
        float[] widths;
        float width;

        public DrawView(Context context) {
            super(context);
            redPaint = new Paint();
            redPaint.setColor(Color.RED);

            fontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            fontPaint.setTextSize(fontSize);
            fontPaint.setStyle(Paint.Style.STROKE);

            // ширина текста
            width = fontPaint.measureText(text);

            // посимвольная ширина
            widths = new float[text.length()];
            fontPaint.getTextWidths(text, widths);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            canvas.translate(50, 250);

            // вывод текста
            canvas.drawText(text, 0, 0, fontPaint);

            // линия шириной в текст
            canvas.drawLine(0, 0, width, 0, fontPaint);

            // посимвольные красные точки
            canvas.drawCircle(0, 0, 3, redPaint);
            for (float w : widths) {
                canvas.translate(w, 0);
                canvas.drawCircle(0, 0, 3, redPaint);
            }
        }
    }

    class DrawView2 extends View {

        Paint p;
        String text = "Test width text";
        int fontSize = 80;
        int maxWidth = 350;
        float realWidth = 0;
        int cnt = 0;
        String info = "";

        public DrawView2(Context context) {
            super(context);
            p = new Paint(Paint.ANTI_ALIAS_FLAG);
            p.setTextSize(fontSize);

            // кол-во символов и их ширина
            float[] measuredWidth = new float[1];
            cnt = p.breakText(text, true, maxWidth, measuredWidth);
            realWidth = measuredWidth[0];

            info = "cnt = " + cnt + ", realWidth = " + realWidth
                    + ", maxWidth = " + maxWidth;

        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            // данные о ширине
            p.setTextSize(24);
            canvas.drawText(info, 50, 50, p);

            // текст
            p.setTextSize(fontSize);
            canvas.drawText(text, 50, 250, p);

            p.setStrokeWidth(10);

            // полоса реальной ширины урезанного текста
            p.setColor(Color.BLUE);
            canvas.drawLine(50, 260, 50 + realWidth, 260, p);

            // полоса лимита
            p.setColor(Color.GREEN);
            canvas.drawLine(50, 270, 50 + maxWidth, 270, p);

        }
    }
}
