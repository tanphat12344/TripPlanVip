package com.example.tripplan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripplan.Interface.ItemClickListener;
import com.example.tripplan.ViewHolder.TourViewHolder;
import com.example.tripplan.ui.Tour;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class TourList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference tourList;

    String placeId= "";
    FirebaseRecyclerAdapter<Tour, TourViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_list);

        //firebase
        database = FirebaseDatabase.getInstance();
        tourList = database.getReference("Tour");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_tour);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //get intent here
        if(getIntent()!= null)
            placeId = getIntent().getStringExtra("PlaceId");
        if(!placeId.isEmpty() && placeId != null)
        {
            loadListTour(placeId);
        }


    }

    private void loadListTour(String placeId) {
        adapter= new FirebaseRecyclerAdapter<Tour, TourViewHolder>(Tour.class,
                R.layout.tour_item,
                TourViewHolder.class,
                tourList.orderByChild("TourId").equalTo(placeId) //like : select * from Tour where TourId =
            ) {
            @Override
            protected void populateViewHolder(TourViewHolder viewHolder, Tour model, int position) {
                viewHolder.tour_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                .into(viewHolder.tour_image);

                final Tour local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        // start new activity
                        Intent tourDetail = new Intent(TourList.this, TourDetail.class);
                        tourDetail.putExtra("TourId",adapter.getRef(position).getKey()); //sent tour id to new activity
                        startActivity(tourDetail);
                    }
                });
            }
        };

        //set adapter
        Log.d("TAG",""+adapter.getItemCount());
        recyclerView.setAdapter(adapter);

    }
}