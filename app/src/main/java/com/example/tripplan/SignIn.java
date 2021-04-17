package com.example.tripplan;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tripplan.Common.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;


public class SignIn extends AppCompatActivity {
    EditText edtPassword, edtPhone;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);
        edtPhone = (MaterialEditText) findViewById(R.id.edtPhone);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(view -> {
            final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
            mDialog.setMessage("Xin vui lòng chờ...");
            mDialog.show();

            table_user.addValueEventListener(new ValueEventListener() {
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
}