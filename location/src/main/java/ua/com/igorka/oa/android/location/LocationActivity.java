package ua.com.igorka.oa.android.location;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class LocationActivity extends ActionBarActivity {
    public static final String TAG = LocationActivity.class.getSimpleName();
    private String provider;
    private TextView textViewLatitude;
    private TextView textViewLongitude;
    private Location location;
    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        textViewLatitude = (TextView) findViewById(R.id.text_view_latitude);
        textViewLongitude = (TextView) findViewById(R.id.text_view_longitude);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), true);
        location = locationManager.getLastKnownLocation(provider);
        Location lastKnownLocationGps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location lastKnownLocationPassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        if (lastKnownLocationGps == null) {
            Log.i(TAG, "GPS is disabled");
        }
        if (lastKnownLocationPassive == null) {
            Log.i(TAG, "Passive is disabled");
        }
        else {
            Log.i(TAG, "Passive is enabled");
        }



        if (location != null) {
            Log.i(TAG, "Provider " + provider);
            showCoordinates();
        }
    }

    private void showCoordinates() {
        String latitude;
        String longitude;
        latitude = String.valueOf(location.getLatitude());
        longitude = String.valueOf(location.getLongitude());
        textViewLatitude.setText("Latitude: " + latitude);
        textViewLongitude.setText("Longitude: " + longitude);
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(provider, 1000, 1, locationListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            showCoordinates();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, MapActivity.class)
                    .putExtra(MapActivity.EXTRA_LATITUDE_DOUBLE, location.getLatitude())
                    .putExtra(MapActivity.EXTRA_LONGITUDE_DOUBLE, location.getLongitude()));
        }

        return super.onOptionsItemSelected(item);
    }
}
