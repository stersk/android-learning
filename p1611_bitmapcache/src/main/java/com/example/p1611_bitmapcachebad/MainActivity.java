package com.example.p1611_bitmapcachebad;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    ListView mLvImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkGranted(this);

        mLvImages = (ListView) findViewById(R.id.lvImages);


        File dir = new File(Environment.getExternalStorageDirectory(), "Download/L0161");
        File[] filesArray = dir.listFiles();

        if (filesArray != null) {
            //ImageAdapter adapter = new ImageAdapter(this, filesArray);
            //BufferedImageAdapter adapter = new BufferedImageAdapter(this, filesArray);
            BufferedAsyncImageAdapter adapter = new BufferedAsyncImageAdapter(this, filesArray);
            mLvImages.setAdapter(adapter);
        }
    }

    static class ImageAdapter extends ArrayAdapter<File> {

        LayoutInflater mInflater;
        int mSize;

        public ImageAdapter(Context context, File[] objects) {
            super(context, R.layout.item, objects);
            mInflater = LayoutInflater.from(context);
            mSize = context.getResources().getDimensionPixelSize(R.dimen.image_size);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = mInflater.inflate(R.layout.item, parent, false);
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            Bitmap bitmap = getBitmap(position);
            imageView.setImageBitmap(bitmap);
            return view;
        }

        private Bitmap getBitmap(int position) {
            String filePath = getItem(position).getAbsolutePath();
            return Utils.decodeSampledBitmapFromResource(filePath, mSize, mSize);

        }
    }

    static class BufferedImageAdapter extends ArrayAdapter<File> {

        LayoutInflater mInflater;
        int mSize;
        LruCache<String, Bitmap> mMemoryCache;

        public BufferedImageAdapter(Context context, File[] objects) {
            super(context, R.layout.item, objects);
            mInflater = LayoutInflater.from(context);
            mSize = context.getResources().getDimensionPixelSize(R.dimen.image_size);

            final int maxMemory = (int) (Runtime.getRuntime().maxMemory());
            final int cacheSize = maxMemory / 8;

            mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getByteCount();
                }
            };
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = mInflater.inflate(R.layout.item, parent, false);
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            Bitmap bitmap = getBitmap(position);
            imageView.setImageBitmap(bitmap);
            return view;
        }

        private Bitmap getBitmap(int position) {
            String filePath = getItem(position).getAbsolutePath();
            Bitmap bitmap = getBitmapFromMemCache(filePath);
            if (bitmap == null) {
                bitmap = Utils.decodeSampledBitmapFromResource(filePath, mSize, mSize);
                addBitmapToMemoryCache(filePath, bitmap);
            }
            return bitmap;

        }

        public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
            if (getBitmapFromMemCache(key) == null) {
                mMemoryCache.put(key, bitmap);
            }
        }

        public Bitmap getBitmapFromMemCache(String key) {
            return mMemoryCache.get(key);
        }
    }

    static class BufferedAsyncImageAdapter extends ArrayAdapter<File> {

        LayoutInflater mInflater;
        Picasso mPicasso;

        public BufferedAsyncImageAdapter(Context context, File[] objects) {
            super(context, R.layout.item, objects);
            mInflater = LayoutInflater.from(context);
            mPicasso = Picasso.get();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = mInflater.inflate(R.layout.item, parent, false);
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            mPicasso.load(getItem(position)).resizeDimen(R.dimen.image_size, R.dimen.image_size). centerInside().into(imageView);
            return view;
        }
    }

    private void checkGranted(AppCompatActivity context){
        int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

    }
}
