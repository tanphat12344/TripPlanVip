package com.example.tripplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Quanly extends AppCompatActivity {

    Button btncqllt, btncqln, btnlogout;
    //logout
    private FirebaseAuth mFirebaseAuth;
    private TextView edtPhone;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly);

        //logout
        edtPhone = findViewById(R.id.edtPhone);

        mFirebaseAuth = FirebaseAuth.getInstance();
//

        btncqllt = (Button) findViewById(R.id.btncqllt);
        btncqln = (Button) findViewById(R.id.btncqln);
        btnlogout = (Button) findViewById(R.id.btnlogout);

        btncqllt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(Quanly.this, Choose.class);
                startActivity(signUp);
            }
        });
        btncqln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(Quanly.this, QLN.class);
                startActivity(signUp);
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    //there is some user logged in
                    edtPhone.setText(mFirebaseUser.getPhoneNumber());
                } else {
                    //no one logged in
                    startActivity(new Intent(Quanly.this, DNDK.class));
                    finish();
                }
            }
        });


    }
//    @Override
//    protected void onStart(){
//        super.onStart();
//        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
//        if(mFirebaseUser!=null){
//            //there is some user logged in
//            edtPhone.setText(mFirebaseUser.getPhoneNumber());
//        }else {
//            //no one logged in
//            startActivity(new Intent(this,DNDK.class));
//            finish();
//        }
//
//    }

    public void logout(View view) {
        mFirebaseAuth.signOut();
    }
}