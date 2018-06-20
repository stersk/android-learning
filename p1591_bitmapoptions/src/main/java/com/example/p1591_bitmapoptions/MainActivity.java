package com.example.p1591_bitmapoptions;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

        setContentView(new DrawView3(this));
    }

    class DrawView extends View {

        Paint paint;
        Bitmap bitmap;

        public DrawView(Context context) {
            super(context);
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);

            BitmapFactory.Options options = new BitmapFactory.Options();
            //options.inJustDecodeBounds = true;
            options.inSampleSize = 2;
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, options);

            Log.d("log", String.format("bitmap = %s, width = %s, height = %s, mimetype = %s",
                    bitmap, options.outWidth, options.outHeight, options.outMimeType));

        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);
        }

    }

    class DrawView2 extends View {

        Paint paint;
        Bitmap bitmap;

        public DrawView2(Context context) {
            super(context);
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);

            Bitmap tempBitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inBitmap = tempBitmap;
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, options);

            Log.d("log", String.format("bitmap = %s (%s,%s), tempBitmap = %s",
                    bitmap, bitmap.getWidth(), bitmap.getHeight(), tempBitmap));

        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);
        }
    }

    class DrawView3 extends View {

        Paint paint;
        Bitmap bitmap;

        public DrawView3(Context context) {
            super(context);
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setTextSize(40);

            Bitmap bmpIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            bmpIcon = Bitmap.createScaledBitmap(bmpIcon, 500, 500, true);

            bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(bmpIcon, 0,0, paint);
            canvas.drawText("Saved bitmap", 100, 50, paint);

            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "savedBitmap.png");

            try {


                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                } finally {
                    if (fos != null) fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);
            canvas.drawBitmap(bitmap, 100, 100, paint);
        }

    }
}
