package com.example.p1401_googlemapsdraw;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    SupportMapFragment mapFragment;
    GoogleMap map;
    Marker marker, marker2;
    int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.INTERNET,
                                android.Manifest.permission.ACCESS_FINE_LOCATION,
                                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                                android.Manifest.permission.INTERNET,
                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_NETWORK_STATE
                        },
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {

        }

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                if (map == null) {
                    finish();
                    return;
                }

                init();
            }
        });

    }

    private void init() {
        marker2 = map.addMarker(new MarkerOptions()
                .position(new LatLng(30, 30))
                .title("Hello world")
                .snippet("Additional text"));

        map.addMarker(new MarkerOptions().position(new LatLng(0, 20))
                .title("Hello world1").snippet("Additional text1"));

        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker marker) {
                if (marker.getId().equals(MainActivity.this.marker2.getId())) {
                    TextView tv = new TextView(MainActivity.this);
                    tv.setText("Test getInfoWindow");
                    tv.setTextColor(Color.RED);
                    return tv;
                } else
                    return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                TextView tv = new TextView(MainActivity.this);
                tv.setText("Test getInfoContents");
                return tv;
            }
        });

        PolylineOptions polylineOptions = new PolylineOptions()
                .add(new LatLng(-5, -30)).add(new LatLng(-5, -20))
                .add(new LatLng(5, -20)).add(new LatLng(5, -30))
                .color(Color.MAGENTA).width(1);

        map.addPolyline(polylineOptions);

        PolygonOptions polygoneOptions = new PolygonOptions()
                .add(new LatLng(-5, -10)).add(new LatLng(-5, 0))
                .add(new LatLng(5, 0)).add(new LatLng(5, -10))
                .strokeColor(Color.CYAN).strokeWidth(10).fillColor(Color.GREEN);

        map.addPolygon(polygoneOptions);

        CircleOptions circleOptions = new CircleOptions()
                .center(new LatLng(0, 15)).radius(500000)
                .fillColor(Color.YELLOW).strokeColor(Color.DKGRAY)
                .strokeWidth(5);

        map.addCircle(circleOptions);

        List<LatLng> list = new ArrayList<LatLng>();
        list.add(new LatLng(26, -5));
        list.add(new LatLng(30, -1));
        list.add(new LatLng(34, -5));
        list.add(new LatLng(30, -9));

        polygoneOptions = new PolygonOptions()
                .add(new LatLng(25, -10))
                .add(new LatLng(25, 0))
                .add(new LatLng(35, 0))
                .add(new LatLng(35, -10))
                .addHole(list)
                .strokeColor(Color.CYAN).
                        strokeWidth(1).
                        fillColor(Color.GREEN);

        map.addPolygon(polygoneOptions);

        GroundOverlayOptions newarkMap = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.ic_vol_type_speaker_light))
                .position(new LatLng(-20, -20), 500000f, 500000f);
        map.addGroundOverlay(newarkMap);
    }

//    public void onClickTest(View view) {
//        map.addMarker(new MarkerOptions()
//            .position(new LatLng(0, 0))
//            .title("Hello world"));
//    }

    public void onClickTest(View view) {
        map.addMarker(new MarkerOptions().position(new LatLng(-10, -10)).icon(
                BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).icon(
                BitmapDescriptorFactory.defaultMarker()));

        map.addMarker(new MarkerOptions().position(new LatLng(10, 10)).icon(
                BitmapDescriptorFactory.fromResource(R.drawable.ic_vol_type_speaker_light)));

        marker2.showInfoWindow();
    }
}
