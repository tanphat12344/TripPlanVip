package com.example.tripplan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;



public class QLLT extends AppCompatActivity implements OnMapReadyCallback {
    //initialize variable
    GoogleMap gMap;
    private MyMapFragment myMapFragment;



    private GoogleMap mMap;

    // below are the latitude and longitude
    // of 4 different locations.
    LatLng sydney = new LatLng(10.849640, 106.368383);
//    LatLng TamWorth = new LatLng(10.811004, 106.682718);
//    LatLng NewCastle = new LatLng(10.814579, 106.683582);
//    LatLng Brisbane = new LatLng(10.820142, 106.687537);

    // creating array list for adding all our locations.
    private ArrayList<LatLng> locationArrayList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_l_l_t);


//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference table_location = database.getReference("Location");


        //hienfamentactivity
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom);
        bottomNavigationView.setSelectedItemId(R.id.actionqllt);
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
                    case R.id.actionqllt:

                        return true;
                    case R.id.actiontk:
                        startActivity(new Intent(getApplicationContext(),QLN.class));
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
//        locationArrayList.add(TamWorth);
//        locationArrayList.add(NewCastle);
//        locationArrayList.add(Brisbane);


    }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            //assign variable
            gMap = googleMap;
            mMap = googleMap;

            Marker myMarker = null;


            // inside on map ready method
            // we will be displaying all our markers.
            // for adding markers we are running for loop and
            // inside that we are drawing marker on our map.
            for (int i = 0; i < locationArrayList.size(); i++) {

                // below line is use to add marker to each location of our array list.
               // mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("Marker"));




                // below lin is use to zoom our camera on map.
                mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));

                // below line is use to move our camera to the specific location.
                mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
                mMap = googleMap;
                LatLng sun = new LatLng(11.347328, 106.100630);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sun,13));
                mMap.addMarker(new MarkerOptions()
                        .title("location")
                        .snippet("tấn phát")
                        .position(sun));
            }


        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //creating marker
                MarkerOptions markerOptions = new MarkerOptions();
                //set Marker position
                markerOptions.position(latLng);
                //set latitude and longitude on market
                markerOptions.title(latLng.latitude+" : "+latLng.longitude);
                // clear the Previously click position
                gMap.clear();
                //zoom the marker
                gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                //add marker on map
                gMap.addMarker(markerOptions);


//

            }
        });

    }
//private final LatLng PERTH = new LatLng(-31.952854, 115.857342);
//    private final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
//    private final LatLng BRISBANE = new LatLng(-27.47093, 153.0235);
//
//    private Marker markerPerth;
//    private Marker markerSydney;
//    private Marker markerBrisbane;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_q_l_l_t);
//
//       // hienfamentactivity
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom);
//        bottomNavigationView.setSelectedItemId(R.id.actionqllt);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()){
//                    case R.id.actiontrangchu:
//                        startActivity(new Intent(getApplicationContext(),Home.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                    case R.id.actiontour:
//                        startActivity(new Intent(getApplicationContext(),Tour.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                    case R.id.actionqllt:
//
//                        return true;
//                    case R.id.actionqln:
//                        startActivity(new Intent(getApplicationContext(),QLN.class));
//                        overridePendingTransition(0,0);
//                        return true;
//
//                }
//                return false;
//            }
//        });
//
//        SupportMapFragment mapFragment =
//                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
//        mapFragment.getMapAsync(this);
//    }
//
//
//
//    /** Called when the map is ready. */
//    @Override
//    public void onMapReady(GoogleMap map) {
//        // Add some markers to the map, and add a data object to each marker.
//        markerPerth = map.addMarker(new MarkerOptions()
//                .position(PERTH)
//                .title("Perth"));
//        markerPerth.setTag(0);
//
//        markerSydney = map.addMarker(new MarkerOptions()
//                .position(SYDNEY)
//                .title("Sydney"));
//        markerSydney.setTag(0);
//
//        markerBrisbane = map.addMarker(new MarkerOptions()
//                .position(BRISBANE)
//                .title("Brisbane"));
//        markerBrisbane.setTag(0);
//
//        // Set a listener for marker click.
//        map.setOnMarkerClickListener(this);
//    }
//
//    /** Called when the user clicks a marker. */
//    @Override
//    public boolean onMarkerClick(final Marker marker) {
//
//        // Retrieve the data from the marker.
//        Integer clickCount = (Integer) marker.getTag();
//
//        // Check if a click count was set, then display the click count.
//        if (clickCount != null) {
//            clickCount = clickCount + 1;
//            marker.setTag(clickCount);
//            Toast.makeText(this,
//                    marker.getTitle() +
//                            " has been clicked " + clickCount + " times.",
//                    Toast.LENGTH_SHORT).show();
//        }
//
//        // Return false to indicate that we have not consumed the event and that we wish
//        // for the default behavior to occur (which is for the camera to move such that the
//        // marker is centered and for the marker's info window to open, if it has one).
//        return false;
//    }


}