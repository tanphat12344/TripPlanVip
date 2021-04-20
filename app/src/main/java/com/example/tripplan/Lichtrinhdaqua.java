package com.example.tripplan;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import com.example.tripplan.Common.Common;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Lichtrinhdaqua extends AppCompatActivity implements OnMapReadyCallback  {

    //initialize variable
    private GoogleMap gMap;
    private MyMapFragment myMapFragment;
    private Marker marker;
    private GoogleMap mMap;

    Firebase firebaseAuth;
    DatabaseReference reference;



    // below are the latitude and longitude
    // of 4 different locations.
    LatLng sydney = new LatLng(10.849640, 106.368383);
    LatLng TamWorth = new LatLng(10.811004, 106.682718);
//    LatLng NewCastle = new LatLng(10.814579, 106.683582);
//    LatLng Brisbane = new LatLng(10.820142, 106.687537);

    // creating array list for adding all our locations.
    private ArrayList<LatLng> locationArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichtrinhdaqua);


        //hienfamentactivity
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom);
        bottomNavigationView.setSelectedItemId(R.id.actionql);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.actiontrangchu:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;
//                    case R.id.actiontour:
//                        startActivity(new Intent(getApplicationContext(),Tour.class));
//                        overridePendingTransition(0,0);
//                        return true;
                    case R.id.actionql:

                        return true;
                    case R.id.actiontk:
                        startActivity(new Intent(getApplicationContext(),Timkiem.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        //obtain the SupportMapFragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.google_map);
        supportMapFragment.getMapAsync(this);



        // in below line we are initializing our array list.
        locationArrayList = new ArrayList<>();

        // on below line we are adding our
        // locations in our array list.
        locationArrayList.add(sydney);
        locationArrayList.add(TamWorth);
//        locationArrayList.add(NewCastle);
//        locationArrayList.add(Brisbane);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //assign variable
        gMap = googleMap;
        mMap = googleMap;

        Marker myMarker = null;




//
//
///// lấy tất cả các tọa độ từ firebase lên map
        gMap = googleMap;
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        gMap.getUiSettings().setZoomControlsEnabled(true);
        gMap.getUiSettings().setCompassEnabled(true);
        //Disable Map Toolbar:
        //map.getUiSettings().setMapToolbarEnabled(false);

//
//        //mới
//        String usern;
//        usern = firebaseAuth.getInstance().getCurrentUser().getUid();
//// mới

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersRef = rootRef
                .child("location");



        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    Log.d("CurrentUserId:", Common.currentUser.getId());
                    Log.d("FirebaseUserId:", ds.child("id").getValue().toString());


                    // Chỉ hiện những địa điểm của User đã đăng nhập
                    if (ds.child("id").getValue().toString().equals(Common.currentUser.getId())) {
                        Log.d("CHECKOK:", "OK");

//                    //mới thêm
//
//                    reference=FirebaseDatabase.getInstance()
//                            .getReference().child("location")
//                            .child("id" + usern).child("User");
//                    //mới


                        //Toast.makeText(L_Location_Activity.this,"for",Toast.LENGTH_LONG).show();


                        String latitude_Display = ds
                                .child("lat")
                                .getValue().toString();

                        String longitude_Display = ds
                                .child("lng")
                                .getValue().toString();

                        String place_Display = ds
                                .child("place")
                                .getValue().toString();

                        String content_Display = ds
                                .child("content")
                                .getValue().toString();

//                    ImageView image_Display = ds
//                            .child("image")
//                            .getValue().toString()


                        String latLng = latitude_Display;
                        String latLng1 = longitude_Display;
                        String place1 = place_Display;
                        String content1 = content_Display;
                        //  ImageView image1 = image_Display;


                        double latitude = Double.parseDouble(latLng);
                        double longitude = Double.parseDouble(latLng1);

                        // map.clear();
                        LatLng currentLocation = new LatLng(latitude, longitude);
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(currentLocation);
                        //markerOptions.title("i'm here");
                        //map.addMarker( markerOptions );
                        gMap.addMarker(new MarkerOptions()
                                .position(new LatLng(latitude, longitude))
                                .title(place1))

                                //.setIcon(BitmapDescriptorFactory.fromResource(image1))
                                .setSnippet(content1);


                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        usersRef.addListenerForSingleValueEvent(eventListener);
//
//        // lấy tất cả các tọa độ từ firebase lên map



//        for (int i = 0; i < locationArrayList.size(); i++) {
//
//            // below line is use to add marker to each location of our array list.
//            // mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("Marker"));
//
//
//            // below lin is use to zoom our camera on map.
//            mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));
//
//            // below line is use to move our camera to the specific location.
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
//            mMap = googleMap;
//            LatLng sun = new LatLng(11.347328, 106.100630);
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sun, 10));
//            mMap.addMarker(new MarkerOptions()
//                    .title("Tây Ninh")
//                    .snippet("Đi Núi Bà Đen")
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.lgintro))
//                    .position(sun));
//
//            LatLng mon = new LatLng(11.692125, 108.175675);
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sun, 10));
//            mMap.addMarker(new MarkerOptions()
//                    .title("Lâm Đồng")
//                    .snippet("Đi chơi Đà Lạt")
//                    .position(mon));
//
//            LatLng tus = new LatLng(16.060461, 107.986232);
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sun, 10));
//            mMap.addMarker(new MarkerOptions()
//                    .title("Đà Nẵng")
//                    .snippet("Tắm biển")
//                    .position(tus));
//        }
//
//        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
//                //creating marker
//                MarkerOptions markerOptions = new MarkerOptions();
//                //set Marker position
//                markerOptions.position(latLng);
//                //set latitude and longitude on market
//                markerOptions.title(latLng.latitude+" : "+latLng.longitude);
//                // clear the Previously click position
//                gMap.clear();
//                //zoom the marker
//                gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
//                //add marker on map
//                gMap.addMarker(markerOptions);
//
//
////
//
//            }
//        });

    }
}