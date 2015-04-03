package ua.com.igorka.oa.android.appwidget;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Igor Kuzmenko on 03.04.2015.
 * Configure Activity for App Widget
 */
public class AppWidgetConfigure extends Activity {

    private static final int DEFAULT_UPDATE_INTERVAL = 3600000;

    private int updateInterval = DEFAULT_UPDATE_INTERVAL;
    private int mAppWidgetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_widget_configure);
        Log.i(AppWidgetConfigure.class.getSimpleName(), "ON_CREATE");
        Log.i(AppWidget.class.getSimpleName(), getApplicationContext().toString());
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinner_update_time);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this
                , R.array.widget_updates_intervals_items
                , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] intervals = getResources().getStringArray(R.array.widget_updates_intervals_values);
                updateInterval = Integer.parseInt(intervals[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        findViewById(R.id.button_set_update_interval).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(getApplicationContext(), WidgetUpdateService.class);
                PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 0, intent, 0);
                alarmManager.setRepeating(AlarmManager.RTC,
                        System.currentTimeMillis() + 1000,
                        updateInterval,
                        pendingIntent);
                Toast.makeText(getApplicationContext(), String.valueOf(updateInterval), Toast.LENGTH_LONG).show();

                Intent resultValue = new Intent();
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
                setResult(RESULT_OK, resultValue);
                finish();
            }
        });


    }
}
