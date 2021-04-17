package com.example.tripplan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripplan.Interface.ItemClickListener;
import com.example.tripplan.ViewHolder.TourViewHolder;
import com.example.tripplan.ui.Tour;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import kotlin.Triple;

public class custom_info extends AppCompatActivity {
//
//    public TextView content, place;
//    public ImageView image;
//
//    private StorageReference mStorage;
//    private static final int CAMERA_REQUEST_CODE = 1;
//    //CollapsingToolbarLayout collapsingToolbarLayout;
//    //private ItemClickListener itemClickListener;
//
//    // DatabaseReference all = FirebaseDatabase.getInstance().getReference();
//    //Query allPost = FirebaseDatabase.getInstance().getReference().child("location");
//    FirebaseDatabase mdatabase;
//    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_info);

//        place = (TextView) findViewById(R.id.name);
//        content = (TextView) findViewById(R.id.desc);
//        image = (ImageView) findViewById(R.id.image1);
//
//        //Get instance
//        mStorage = FirebaseStorage.getInstance().getReference();


    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
//
//            Uri uri = data.getData();
//
//            StorageReference filePath = mStorage.child("location").child(uri.getLastPathSegment());
//            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                    //Show image
//                    Uri downloadUri = taskSnapshot.getUploadSessionUri();
//                    Picasso.with(custom_info.this).load(downloadUri).into(image);
//
//                    Toast.makeText(custom_info.this, "Upload Done", Toast.LENGTH_SHORT).show();
//                    Log.v("Test", "image load");
//
//                }
//            });


//        mdatabase = FirebaseDatabase.getInstance();
//        ref = mdatabase.getReference().child("location");
//
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                String na = dataSnapshot.child("place").getValue(String.class);
//                String de = dataSnapshot.child("content").getValue(String.class);
//                ImageView im = dataSnapshot.child("image").getValue(ImageView.class);
//
//                name.setText(na);
//                dest.setText(de);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


//        }
//
//    }
}