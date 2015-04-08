package ua.com.igorka.oa.android.appwidget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Igor Kuzmenko on 03.04.2015.
 * Update Widget Service
 */
public class WidgetUpdateService extends IntentService {

    public static final String TAG = WidgetUpdateService.class.getSimpleName();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public WidgetUpdateService() {
        super(WidgetUpdateService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        RemoteViews views = new RemoteViews(getApplicationContext().getPackageName(), R.layout.appwidget);
        views.setTextViewText(R.id.text_widget_text, new SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(new Date()));
        AppWidgetManager manager = AppWidgetManager.getInstance(getApplicationContext());
        manager.updateAppWidget(intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID), views);
    }
}
