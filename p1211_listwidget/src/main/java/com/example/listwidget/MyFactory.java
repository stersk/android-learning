package com.example.listwidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyFactory implements RemoteViewsService.RemoteViewsFactory {
    ArrayList<String> data;
    Context context;
    SimpleDateFormat sdf;
    int widgetID;

    public MyFactory(Context context, Intent intent) {
        super();

        this.context = context;
        widgetID = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        sdf = new SimpleDateFormat("HH:mm:ss");
    }

    @Override
    public void onCreate() {
        data = new ArrayList<>();
    }

    @Override
    public void onDataSetChanged() {
        data.clear();
        data.add(sdf.format(new Date(System.currentTimeMillis())));
        data.add(String.valueOf(hashCode()));
        data.add(String.valueOf(widgetID));
        for (int i = 3; i < 15; i++) {
            data.add("Item " + i);
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.item);
        remoteView.setTextViewText(R.id.tvItemText, data.get(i));

        Intent clickIntent = new Intent();
        clickIntent.putExtra(MyWidget.ITEM_POSITION, i);
        remoteView.setOnClickFillInIntent(R.id.tvItemText, clickIntent);

        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
