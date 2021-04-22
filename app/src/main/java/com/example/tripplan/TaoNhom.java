package com.example.tripplan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripplan.Common.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Semaphore;

public class TaoNhom extends AppCompatActivity implements View.OnClickListener {

    private FirebaseDatabase mDatabase;
    private DatabaseReference userRef;
    private DatabaseReference groupRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taonhom);

        Button btnSubmit = findViewById(R.id.btnaddn);
        btnSubmit.setOnClickListener(this);

        mDatabase = FirebaseDatabase.getInstance();
        userRef = mDatabase.getReference("User");
        groupRef = mDatabase.getReference("group");
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnaddn:
                String groupName = ((TextView)findViewById(R.id.mn)).getText().toString().trim();
                String memberPhoneNumber = ((TextView)findViewById(R.id.txtsdtn)).getText().toString().trim();

                if (groupName.equals("")) {
                    Toast.makeText(this, "Vui lòng nhập tên nhóm !", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (memberPhoneNumber.equals("")) {
                    Toast.makeText(this, "Vui lòng nhập số điện thoại !", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (memberPhoneNumber.equals(Common.currentUser.getId())) {
                    Toast.makeText(this, "Bạn hãy thêm số điện thoại của một người bạn !", Toast.LENGTH_SHORT).show();
                    return;
                }


                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(!snapshot.child(memberPhoneNumber).exists()) {
                            Toast.makeText(getApplicationContext(), "Số điện thoại không chính xác !", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if(snapshot.child(Common.currentUser.getId()).child("groupId").exists()) {
                            Toast.makeText(getApplicationContext(), "Bạn đã có nhóm rồi mà, tạo chi nữa !", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // Tạo nhóm mới trên Firebase
                        String groupId = groupRef.push().getKey();
                        groupRef.child(groupId).child("name").setValue(groupName);
                        groupRef.child(groupId).child("members").push().setValue(Common.currentUser.getId());
                        groupRef.child(groupId).child("members").push().setValue(memberPhoneNumber);

                        // Đặt groupId vào người dùng hiện tại
                        userRef.child(Common.currentUser.getId()).child("groupId").setValue(groupId);
                        userRef.child(memberPhoneNumber).child("groupId").setValue(groupId);


                        Toast.makeText(getApplicationContext(), "Tạo nhóm thành công !", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
        }
    }
}