package com.example.tripplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Quanly extends AppCompatActivity {

    Button btncqllt, btncqln;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly);


        btncqllt = (Button)findViewById(R.id.btncqllt);
        btncqln = (Button)findViewById(R.id.btncqln);

        btncqllt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent signUp = new Intent(Quanly.this,Choose.class);
                startActivity(signUp);

            }
        });
        btncqln.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent signUp = new Intent(Quanly.this,QLN.class);
                startActivity(signUp);

            }
        });

    }
}