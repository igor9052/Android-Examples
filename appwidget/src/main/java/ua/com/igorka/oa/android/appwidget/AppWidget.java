package ua.com.igorka.oa.android.appwidget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Igor Kuzmenko on 02.04.2015.
 * AppWidget class
 */
public class AppWidget extends AppWidgetProvider {

    public static final String TAG = AppWidget.class.getSimpleName();

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.i(AppWidget.class.getSimpleName(), "ON_UPDATE " + this);
    }

    @Override
    public void onEnabled(Context context) {
        Log.i(TAG, "ON_ENABLED " + context.toString());
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        for (int widgetId : appWidgetIds) {
            Intent intent = new Intent(context, WidgetUpdateService.class);
            PendingIntent pendingIntent = PendingIntent.getService(context, widgetId, intent, 0);
            alarmManager.cancel(pendingIntent);
            Log.i(TAG, "Widget deleted: " + widgetId);
        }
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onDisabled(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, WidgetUpdateService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
        alarmManager.cancel(pendingIntent);
        super.onDisabled(context);
    }
}
