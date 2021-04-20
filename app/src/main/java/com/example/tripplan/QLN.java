package com.example.tripplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QLN extends AppCompatActivity {
    Button btntn,btnvtctvn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_l_n);

        btntn = (Button)findViewById(R.id.btntn);
        btnvtctvn = (Button)findViewById(R.id.btnvtctv);

        btntn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent signUp = new Intent(QLN.this, TaoNhom.class);
                startActivity(signUp);

            }
        });
        btnvtctvn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent signUp = new Intent(QLN.this, ViTriNhom.class);
                startActivity(signUp);

            }
        });

//
//        //hienfamentactivity
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom);
//        bottomNavigationView.setSelectedItemId(R.id.actionql);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()){
//                    case R.id.actiontrangchu:
//                        startActivity(new Intent(getApplicationContext(),Home.class));
//                        overridePendingTransition(0,0);
//                        return true;
////                    case R.id.actiontour:
////                        startActivity(new Intent(getApplicationContext(),Tour.class));
////                        overridePendingTransition(0,0);
////                        return true;
//                    case R.id.actionql:
//                        return true;
//                    case R.id.actiontk:
//                        startActivity(new Intent(getApplicationContext(),Timkiem.class));
//                        overridePendingTransition(0,0);
//
//
//                        return true;
//
//                }
//                return false;
//            }
//        });


    }
}