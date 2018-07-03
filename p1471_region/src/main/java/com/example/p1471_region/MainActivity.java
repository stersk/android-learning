package com.example.p1471_region;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
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

        Paint p;
        Rect rect1;
        Rect rect2;
        Region region;
        RegionIterator iterator;
        Path path;
        //Region.Op op = Region.Op.UNION;
        //Region.Op op = Region.Op.XOR;
        //Region.Op op = Region.Op.DIFFERENCE;
        //Region.Op op = Region.Op.REVERSE_DIFFERENCE;
        //Region.Op op = Region.Op.INTERSECT;
        Region.Op op = Region.Op.REPLACE;


        public DrawView(Context context) {
            super(context);
            p = new Paint();
            p.setStrokeWidth(3);

            // прямоугольники
            rect1 = new Rect(200,200,400,400);
            rect2 = new Rect(300,300,500,500);

            // создание региона
            region = new Region();
            region.set(rect1);
            region.op(rect2, op);

            // создание path из региона
            path = region.getBoundaryPath();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            // контуры прямоугольников
            p.setStyle(Paint.Style.STROKE);
            p.setColor(Color.BLACK);
            canvas.drawRect(rect1, p);
            canvas.drawRect(rect2, p);

            // path
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.BLUE);
            canvas.drawPath(path, p);

        }
    }

    class DrawView2 extends View {

        Paint p;
        Region region;
        Region clipRegion;
        Path path;
        Path pathDst;
        Rect rect;

        public DrawView2(Context context) {
            super(context);
            p = new Paint();
            p.setStrokeWidth(3);
            p.setStyle(Paint.Style.STROKE);

            // path, треугольник
            path = new Path();
            path.moveTo(100, 100);
            path.lineTo(150, 150);
            path.lineTo(100, 200);
            path.close();

            // регион из прямоугольника обрезки
            rect = new Rect(100, 100, 150, 150);
            clipRegion = new Region(rect);

            // итоговый регион
            region = new Region();
            // отсекаем от path область clipRegion
            region.setPath(path, clipRegion);
            // получаем path из региона
            pathDst = region.getBoundaryPath();

        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            // треугольник
            p.setColor(Color.GREEN);
            canvas.drawPath(path, p);

            canvas.translate(200, 0);

            // верхняя часть треугольника
            p.setColor(Color.BLUE);
            canvas.drawPath(pathDst, p);

        }
    }
}
