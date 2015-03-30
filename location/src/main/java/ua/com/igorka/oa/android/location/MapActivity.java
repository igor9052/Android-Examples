package ua.com.igorka.oa.android.location;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapActivity extends Activity {

    public static final String EXTRA_LONGITUDE_DOUBLE = "LONGITUDE_DOUBLE";
    public static final String EXTRA_LATITUDE_DOUBLE = "LATITUDE_DOUBLE";

    private double latitude = 0.0;
    private double longitude = 0.0;

    private OnMapReadyCallback mapReadyCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng currentPosition = new LatLng(latitude, longitude);

            CameraPosition cameraPosition = new CameraPosition(currentPosition, 17, 0, 0);
            googleMap.addMarker(new MarkerOptions()
                    .position(currentPosition).title("Marker"));
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_LATITUDE_DOUBLE)) {
            latitude = intent.getDoubleExtra(EXTRA_LATITUDE_DOUBLE, 0);
        }
        if (intent.hasExtra(EXTRA_LONGITUDE_DOUBLE)) {
            longitude = intent.getDoubleExtra(EXTRA_LONGITUDE_DOUBLE, 0);
        }

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(mapReadyCallback);
    }
}
