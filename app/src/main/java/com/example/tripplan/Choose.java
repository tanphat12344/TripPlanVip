package com.example.tripplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choose extends AppCompatActivity {
    Button btnTlt,btnLtdq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        btnTlt = (Button)findViewById(R.id.btnTlt);
        btnLtdq = (Button)findViewById(R.id.btnLtdq);

        btnTlt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent signUp = new Intent(Choose.this,QLLT.class);
                startActivity(signUp);

            }
        });
        btnLtdq.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent signUp = new Intent(Choose.this,Lichtrinhdaqua.class);
                startActivity(signUp);

            }
        });


    }
}