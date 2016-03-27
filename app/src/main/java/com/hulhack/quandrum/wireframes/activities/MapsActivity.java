package com.hulhack.quandrum.wireframes.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hulhack.quandrum.wireframes.R;

import java.util.LinkedList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<LatLng> directionPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        directionPoint = new LinkedList<LatLng>();
        directionPoint.add(new LatLng(13.014454, 77.584509));
        directionPoint.add(new LatLng(13.018283, 77.583891));
        directionPoint.add(new LatLng(13.022461, 77.583891));
        directionPoint.add(new LatLng(13.023470, 77.583891));
        directionPoint.add(new LatLng(13.024470, 77.583895));
        directionPoint.add(new LatLng(13.025475, 77.583898));
        directionPoint.add(new LatLng(13.026480, 77.583899));
        directionPoint.add(new LatLng(13.027480, 77.583900));
        directionPoint.add(new LatLng(13.028480, 77.583902));
        directionPoint.add(new LatLng(13.029480, 77.583904));
        directionPoint.add(new LatLng(13.030480, 77.583906));
        directionPoint.add(new LatLng(13.031480, 77.583908));
        directionPoint.add(new LatLng(13.032480, 77.583910));
        directionPoint.add(new LatLng(13.033480, 77.583912));
        directionPoint.add(new LatLng(13.034480, 77.583914));
        directionPoint.add(new LatLng(13.035480, 77.583916));
        directionPoint.add(new LatLng(13.036480, 77.583918));
        directionPoint.add(new LatLng(13.037480, 77.583920));
        directionPoint.add(new LatLng(13.038482, 77.583920));
        directionPoint.add(new LatLng(13.039484, 77.583920));
        directionPoint.add(new LatLng(13.040486, 77.583920));
        directionPoint.add(new LatLng(13.041488, 77.583920));
        directionPoint.add(new LatLng(13.042490, 77.583920));
        directionPoint.add(new LatLng(13.043492, 77.583920));
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(13.014454, 77.584509);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Now leaving wearhouse"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.clear();

        setAnimation(mMap,directionPoint);

    }

    public static void setAnimation(GoogleMap myMap, final List<LatLng> directionPoint) {


        Marker marker = myMap.addMarker(new MarkerOptions()
                .position(directionPoint.get(0))
                .flat(true));

        myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(directionPoint.get(0), 13f));

        animateMarker(myMap, marker, directionPoint, false);
    }


    private static void animateMarker(GoogleMap myMap, final Marker marker, final List<LatLng> directionPoint,
                                      final boolean hideMarker) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = myMap.getProjection();
        final long duration = 30000;

        final Interpolator interpolator = new LinearInterpolator();

        handler.post(new Runnable() {
            int i = 0;

            @Override
            public void run() {
                Log.e("ANIMATION",i+"");
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                if (i < directionPoint.size())
                    marker.setPosition(directionPoint.get(i));

                i++;


                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 1000);
                } else {
                    if (hideMarker) {
                        marker.setVisible(false);
                    } else {
                        marker.setVisible(true);
                    }
                }
            }
        });
    }

}
