package com.example.p1661_owndrawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Paint;
import android.graphics.Shader;

public class BitmapHexagonDrawable extends HexagonDrawable {
    private Context contect;

    public BitmapHexagonDrawable(Context context) {
        this.contect = context;

        Paint paint = getPaint();
        paint.setShader(createScaleMatrixShader());
    }

    private Shader createScaleMatrixShader() {
        Bitmap bitmap = BitmapFactory.decodeResource(contect.getResources(),
                R.mipmap.ic_launcher);
        bitmap = Bitmap.createScaledBitmap(bitmap, 480, 680, true);
        BitmapShader shader = new BitmapShader(bitmap,
                Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        return shader;
    }
}
