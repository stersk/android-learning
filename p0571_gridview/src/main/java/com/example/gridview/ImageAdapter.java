package com.example.gridview;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private Integer[] pictures;

    public ImageAdapter(Context mContext) {
        super();

        generatePictures();
        context = mContext;
    }

    @Override
    public int getCount() {
        return pictures.length;
    }

    @Override
    public Object getItem(int i) {
        return pictures[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ImageView result;
        if (view == null) {
            result = new ImageView(context);
            result.setLayoutParams(new GridView.LayoutParams(85, 85));
            result.setScaleType(ImageView.ScaleType.CENTER_CROP);
            result.setPadding(8, 8, 8, 8);
            result.setBackgroundColor(Color.RED);
        } else {
            result = (ImageView) view;
        }

        result.setImageResource(pictures[i]);

        return result;
    }

    private void generatePictures() {
        pictures = new Integer[]{
                R.drawable.bl1,
                R.drawable.bl2,
                R.drawable.bl3,
                R.drawable.bl4,
                R.drawable.bl5,
                R.drawable.bl1,
                R.drawable.bl2,
                R.drawable.bl3,
                R.drawable.bl4,
                R.drawable.bl5,
                R.drawable.bl1,
                R.drawable.bl2,
                R.drawable.bl3,
                R.drawable.bl4,
                R.drawable.bl5,
                R.drawable.bl1,
                R.drawable.bl2,
                R.drawable.bl3,
                R.drawable.bl4,
                R.drawable.bl5,
                R.drawable.bl1,
                R.drawable.bl2,
                R.drawable.bl3,
                R.drawable.bl4,
                R.drawable.bl5,
                R.drawable.bl1,
                R.drawable.bl2,
                R.drawable.bl3,
                R.drawable.bl4,
                R.drawable.bl5,
                R.drawable.bl1,
                R.drawable.bl2,
                R.drawable.bl3,
                R.drawable.bl4,
                R.drawable.bl5
        };
    }
}
