package com.example.game_eatcircles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class CanvasView extends View implements ICanvasView {
    private GameManager gameManager;
    private Canvas canvas;
    private Paint paint;
    private static int width;
    private static int height;
    private Toast toast;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWidthAndHeightOfMainWindow(context);
        initPaint();
        gameManager = new GameManager(this, width, height);
    }

    private void initWidthAndHeightOfMainWindow(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            gameManager.onTouchEvent(x,y);
            invalidate();
        }

        return true;
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        gameManager.onDraw();

    }

    @Override
    public void drawCircle(SimpleCircle circle) {
        paint.setColor(circle.getColor());

        canvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), paint);
    }

    @Override
    public void showMessage(String s) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(getContext(), s, Toast.LENGTH_LONG);
        toast.show();
    }
}
