package com.example.bogdan.wunder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import io.realm.Realm;
import io.realm.RealmResults;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private String vin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Receiving intent's extra,
        // using vin as identifier of the car,
        // to decide on what car to zoom and put name.
        Intent intent = getIntent();
        vin = intent.getStringExtra("vin");

        Log.v("Received name: ", vin);

        initMap();

    }

    private void initMap(){

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapActivity.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;


        Realm realm = Realm.getDefaultInstance();
        RealmResults<Placemark> results = realm.where(Placemark.class).findAll();

        BitmapDrawable drawable =(BitmapDrawable)getResources().getDrawable(R.drawable.car_icon);
        Bitmap carBitmap = drawable.getBitmap();


        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromBitmap(carBitmap);

        for(int i = 0; i < results.size(); i++){

            LatLng car = new LatLng(results.get(i).getLongitude(), results.get(i).getLatitude());

            Marker opts = map.addMarker(new MarkerOptions()
                    .position(car)
                    .title(results.get(i).getName())
                    .icon(bitmap));

            if(results.get(i).getVin().equals(vin)){
                opts.showInfoWindow();
                CameraUpdate update = CameraUpdateFactory.newLatLngZoom(car, 18);
                map.animateCamera(update);
            }
        }

    }

}
