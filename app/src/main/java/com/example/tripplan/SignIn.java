package com.example.tripplan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tripplan.Common.Common;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;


public class SignIn extends AppCompatActivity {
    EditText edtPassword, edtPhone;
    Button btnSignIn;

    private DatabaseReference rootRef;
    private DatabaseReference usersRef;

    private Location currentLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);
        edtPhone = (MaterialEditText) findViewById(R.id.edtPhone);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        // Set default username and password
        edtPhone.setText("01");
        edtPassword.setText("321");

        this.rootRef = FirebaseDatabase.getInstance().getReference();
        this.usersRef = rootRef.child("User");

        btnSignIn.setOnClickListener(view -> {
            final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
            mDialog.setMessage("Xin vui lòng chờ...");
            mDialog.show();

            usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // check if user not exist in data
                    if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                        //lấy thông tin người dùng
                        mDialog.dismiss();

                        String username = edtPhone.getText().toString().trim();
                        String password = edtPassword.getText().toString().trim();

                        User user = dataSnapshot.child(username).getValue(User.class);

                        if (user.getPassword().equals(password)) {
                            Intent homeIntent = new Intent(SignIn.this, Quanly.class);
                            user.setId(username);
                            Common.currentUser = user;
                            Log.d("UserLogin", user.toString());

                            // Get current location and update to firebase
                            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(SignIn.this);
                            fetchLastLocation();

                            startActivity(homeIntent);
                            finish();
                        } else {
                            Toast.makeText(SignIn.this, "Sai mật khẩu!!!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mDialog.dismiss();
                        Toast.makeText(SignIn.this, "Người dùng không tồn tại!!!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        });


    }

    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null) {
                String lng = String.valueOf(location.getLongitude());
                String lat = String.valueOf(location.getLatitude());

                usersRef.child(Common.currentUser.getId()).child("lastLat").setValue(lat);
                usersRef.child(Common.currentUser.getId()).child("lastLng").setValue(lng);
            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLastLocation();
                }
                break;
        }
    }
}