package com.example.tripplan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tripplan.Common.Common;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViTriNhom extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap gMap;
    private DatabaseReference rootRef;
    private DatabaseReference usersRef;
    private DatabaseReference groupsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitrinhom);

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.google_map);
        supportMapFragment.getMapAsync(this);

        this.rootRef = FirebaseDatabase.getInstance().getReference();
        this.usersRef = rootRef.child("User");
        this.groupsRef = rootRef.child("group");
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.gMap = googleMap;
        this.gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        this.gMap.getUiSettings().setZoomControlsEnabled(true);
        this.gMap.getUiSettings().setCompassEnabled(true);

        // Set default position
        LatLng vietnam = new LatLng(14.0583, 108.2772); // 14.0583° N, 108.2772° E
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(vietnam));

        groupsRef.child(Common.currentUser.getGroupId()).child("members").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    String memberId = postSnapshot.getValue().toString();

                    System.out.println("Members Id: " + memberId);

                    DatabaseReference userRef = usersRef.child(memberId);
                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child("lastLat").exists() && snapshot.child("lastLng").exists()) {
                                double lat = Double.parseDouble(snapshot.child("lastLat").getValue().toString());
                                double lng = Double.parseDouble(snapshot.child("lastLng").getValue().toString());
                                LatLng userPosition = new LatLng(lat, lng);
                                gMap.addMarker(new MarkerOptions().position(userPosition).title(snapshot.child("name").getValue().toString()));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}