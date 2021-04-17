package com.example.tripplan;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tripplan.ui.Tour;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class TourDetail extends AppCompatActivity {

    TextView tour_name, tour_price, tour_description;
    ImageView tour_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
  //  FloatingActionButton btnCart;

    String tourId="";

    FirebaseDatabase database;
    DatabaseReference tour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);

        //firebase
        database = FirebaseDatabase.getInstance();
        tour = database.getReference("Tour");

        //init view
        //btnCart = (FloatingActionButton)findViewById(R.id.btnCart);

        tour_description = (TextView)findViewById(R.id.tour_description);
        tour_name = (TextView)findViewById(R.id.tour_name);
       // tour_price = (TextView)findViewById(R.id.tour_price);
        tour_image = (ImageView) findViewById(R.id.img_tour);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
       // collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandeAppbar);
        //collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CollapsedAppbar);

        //get tour id from Intent
        if(getIntent() != null)
            tourId = getIntent().getStringExtra("TourId");
        if(!tourId.isEmpty())
        {
            getDetailTour(tourId);
        }
    }

    private void getDetailTour(String tourId) {
        tour.child(tourId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Tour tour = dataSnapshot.getValue(Tour.class);

                //set Image
                Picasso.with(getBaseContext()).load(tour.getImage())
                        .into(tour_image);
                collapsingToolbarLayout.setTitle(tour.getName());

              //  tour_price.setText(tour.getPrice());

                tour_name.setText(tour.getName());

                tour_description.setText(tour.getDescription());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}