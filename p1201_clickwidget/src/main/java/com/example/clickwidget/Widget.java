package com.example.clickwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Widget extends AppWidgetProvider {

    final static String ACTION_CHANGE = "com.example.clickwidget.change_count";
    final static String ACTION_CHANGE_TIME = "com.example.clickwidget.change_time";
    final static String ACTION_CHANGE_SEARCH = "com.example.clickwidget.open_google";

    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        // обновляем все экземпляры
        for (int i : appWidgetIds) {
            updateWidget(context, appWidgetManager, i);
        }
    }

    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        // Удаляем Preferences
        SharedPreferences.Editor editor = context.getSharedPreferences(
                Config.WIDGET_PREF, Context.MODE_PRIVATE).edit();
        for (int widgetID : appWidgetIds) {
            editor.remove(Config.WIDGET_TIME_FORMAT + widgetID);
            editor.remove(Config.WIDGET_COUNT + widgetID);
            editor.remove(Config.WIDGET_TIME + widgetID);
        }
        editor.commit();
    }

    static void updateWidget(Context ctx, AppWidgetManager appWidgetManager,
                             int widgetID) {
        SharedPreferences sp = ctx.getSharedPreferences(
                Config.WIDGET_PREF, Context.MODE_PRIVATE);

        // Читаем формат времени и определяем текущее время
        String timeFormat = sp.getString(Config.WIDGET_TIME_FORMAT
                + widgetID, null);
        if (timeFormat == null) return;
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        String currentTime = sp.getString(Config.WIDGET_TIME + widgetID, "");

        // Читаем счетчик
        String count = String.valueOf(sp.getInt(Config.WIDGET_COUNT
                + widgetID, 0));

        // Помещаем данные в текстовые поля
        RemoteViews widgetView = new RemoteViews(ctx.getPackageName(),
                R.layout.widget);

        widgetView.setTextViewText(R.id.tvTime, currentTime);
        widgetView.setTextViewText(R.id.tvCount, count);

        // Конфигурационный экран (первая зона)
        Intent configIntent = new Intent(ctx, Config.class);
        configIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_CONFIGURE);
        configIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent = PendingIntent.getActivity(ctx, widgetID,
                configIntent, 0);
        widgetView.setOnClickPendingIntent(R.id.tvPressConfig, pIntent);

        // Обновление виджета (вторая зона)
        Intent updateIntent = new Intent(ctx, Widget.class);
        updateIntent.setAction(ACTION_CHANGE_TIME);
        updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        pIntent = PendingIntent.getBroadcast(ctx, widgetID, updateIntent, 0);
        widgetView.setOnClickPendingIntent(R.id.tvPressUpdate, pIntent);

        // Счетчик нажатий (третья зона)
        Intent countIntent = new Intent(ctx, Widget.class);
        countIntent.setAction(ACTION_CHANGE);
        countIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        pIntent = PendingIntent.getBroadcast(ctx, widgetID, countIntent, 0);
        widgetView.setOnClickPendingIntent(R.id.tvPressCount, pIntent);

        // Google (четвертая зона)
        Intent searchIntent = new Intent(ctx, Widget.class);
        searchIntent.setAction(ACTION_CHANGE_SEARCH);
        pIntent = PendingIntent.getBroadcast(ctx, widgetID, searchIntent, 0);
        widgetView.setOnClickPendingIntent(R.id.tvGoToGoogle, pIntent);

        // Обновляем виджет
        appWidgetManager.updateAppWidget(widgetID, widgetView);
    }

    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        // Проверяем, что это intent от нажатия на третью зону
        if (intent.getAction().equalsIgnoreCase(ACTION_CHANGE)) {

            // извлекаем ID экземпляра
            int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
            Bundle extras = intent.getExtras();
            if (extras != null) {
                mAppWidgetId = extras.getInt(
                        AppWidgetManager.EXTRA_APPWIDGET_ID,
                        AppWidgetManager.INVALID_APPWIDGET_ID);

            }

            SharedPreferences sp = context.getSharedPreferences(
                    Config.WIDGET_PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();

            if (mAppWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                // Читаем значение счетчика, увеличиваем на 1 и записываем

                int cnt = sp.getInt(Config.WIDGET_COUNT + mAppWidgetId,  0);

                editor.putInt(Config.WIDGET_COUNT + mAppWidgetId, ++cnt);
            }

            editor.commit();
            // Обновляем виджет
            updateWidget(context, AppWidgetManager.getInstance(context),
                    mAppWidgetId);
        }

        // Проверяем, что это intent от нажатия на вторую зону
        if (intent.getAction().equalsIgnoreCase(ACTION_CHANGE_TIME)) {

            // извлекаем ID экземпляра
            int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
            Bundle extras = intent.getExtras();
            if (extras != null) {
                mAppWidgetId = extras.getInt(
                        AppWidgetManager.EXTRA_APPWIDGET_ID,
                        AppWidgetManager.INVALID_APPWIDGET_ID);

            }

            SharedPreferences sp = context.getSharedPreferences(
                    Config.WIDGET_PREF, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sp.edit();

            if (mAppWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                String timeFormat = sp.getString(Config.WIDGET_TIME_FORMAT
                        + mAppWidgetId, null);
                if (timeFormat == null) return;

                SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);

                String currentTime = sdf.format(new Date(System.currentTimeMillis()));
                editor.putString(Config.WIDGET_TIME + mAppWidgetId, currentTime);
            }

            editor.commit();
            // Обновляем виджет
            updateWidget(context, AppWidgetManager.getInstance(context),
                    mAppWidgetId);
        }

        if (intent.getAction().equalsIgnoreCase(ACTION_CHANGE_SEARCH)) {
            Intent googleIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
            context.startActivity(googleIntent);
        }

    }
}

