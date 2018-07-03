package com.example.p1661_owndrawable;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;

public class CustomBitmapHexagonDrawable extends HexagonDrawable {

    Bitmap mOriginBitmap;

    public CustomBitmapHexagonDrawable(Bitmap bitmap) {
        mOriginBitmap = bitmap;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);

        Paint paint = getPaint();
        Bitmap bitmap = Bitmap.createScaledBitmap(mOriginBitmap, bounds.width(), bounds.height(), true);
        BitmapShader shader = new BitmapShader(bitmap,
                Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        paint.setShader(shader);
    }
}
