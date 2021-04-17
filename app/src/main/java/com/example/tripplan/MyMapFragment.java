package com.example.tripplan;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyMapFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap googleMap;


    public MyMapFragment(){
        getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap gmap) {
        this.googleMap = gmap;


        // Set default position
        // Add a marker in Sydney and move the camera
        LatLng vietnam = new LatLng(14.0583, 108.2772); // 14.0583° N, 108.2772° E
        this.googleMap.addMarker(new MarkerOptions().position(vietnam).title("Marker in Vietnam"));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(vietnam));

        this.googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
//                MarkerOptions markerOptions = new MarkerOptions();
//                markerOptions.position(latLng);
//                markerOptions.title(latLng.latitude + " : "+ latLng.longitude);
//                // Clear previously click position.
//                googleMap.clear();
//                // Zoom the Marker
//                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
//                // Add Marker on Map
//                googleMap.addMarker(markerOptions);


//                // First check if myMarker is null
//                Marker myMarker = null;
//                if (myMarker == null) {
//
//                    // Marker was not set yet. Add marker:
//                    myMarker = googleMap.addMarker(new MarkerOptions()
//                            .position(latLng)
//                            .title("Your marker title")
//                            .snippet("Your marker snippet"));
//                    LatLng myLatLng = new LatLng(myMarker.getPosition().latitude,myMarker.getPosition().longitude);
//                } else {
//
//                    // Marker already exists, just update it's position
//                    myMarker.setPosition(latLng);
//                    LatLng myLatLng = new LatLng(myMarker.getPosition().latitude,myMarker.getPosition().longitude);
//
//                }


            }
        });

    }
}
