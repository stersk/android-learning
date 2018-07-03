package com.example.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyBoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Product> objects;
    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            boolean elementChecked = compoundButton.isChecked();
            int elementPosition = (int) compoundButton.getTag();

            Product productObj = (Product) getItem(elementPosition);
            productObj.checkbox = elementChecked;
        }
    };

    MyBoxAdapter(Context context, ArrayList<Product> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View returnView = view;
        if (returnView == null) {
            returnView = lInflater.inflate(R.layout.item, viewGroup, false);
        }

        Product dataElement = (Product) getItem(i);

        ImageView ivProduct = returnView.findViewById(R.id.ivImage);
        ivProduct.setImageResource(dataElement.picture);

        TextView tvPrice = returnView.findViewById(R.id.tvPrice);
        tvPrice.setText(String.valueOf(dataElement.price));

        TextView tvDescription = returnView.findViewById(R.id.tvDescr);
        tvDescription.setText(String.valueOf(dataElement.name));

        CheckBox checkBox = returnView.findViewById(R.id.cbBox);
        checkBox.setTag(i);
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBox.setChecked(dataElement.checkbox);

        return returnView;
    }

    public ArrayList<Product> getProductsInBox() {
        ArrayList<Product> alBox = new ArrayList<>();

        for (Product productItem : objects) {
            if (productItem.checkbox) {
                alBox.add(productItem);
            }
        }

        return alBox;
    }
}
