//package com.example.tripplan;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MenuItem;
//
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//
//public class Tour extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tour);
//
//        //hienfamentactivity
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom);
//        bottomNavigationView.setSelectedItemId(R.id.actiontour);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()){
//                    case R.id.actiontrangchu:
//                        startActivity(new Intent(getApplicationContext(),Home.class));
//                        overridePendingTransition(0,0);
//                        return true;
////                    case R.id.actiontour:
////
////                        return true;
//                    case R.id.actionqllt:
//                        startActivity(new Intent(getApplicationContext(),QLLT.class));
//                        overridePendingTransition(0,0);
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
//    }
//}