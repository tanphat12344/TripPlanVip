package com.example.tripplan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import android.view.View;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripplan.Common.Common;
import com.example.tripplan.Interface.ItemClickListener;
import com.example.tripplan.ViewHolder.MenuViewHolder;
import com.example.tripplan.ui.Place;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    FirebaseDatabase database;
    DatabaseReference place;

   // TextView txtFullName;

    RecyclerView recyler_menu;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Place, MenuViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       // Toolbar toolbar = findViewById(R.id.toolbar);
       // toolbar.setTitle("Menu");
       // setSupportActionBar(toolbar);

        //Init Firebase
        database = FirebaseDatabase.getInstance();
        place = database.getReference("Place");


        RelativeLayout drawer = findViewById(R.id.drawer_layout);

        //load menu
        recyler_menu = (RecyclerView)findViewById(R.id.recycler_menu);
        recyler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyler_menu.setLayoutManager(layoutManager);

        loadMenu();


        //hienfamentactivity
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom);
        bottomNavigationView.setSelectedItemId(R.id.actiontrangchu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.actiontrangchu:

                        return true;
//                    case R.id.actiontour:
//                        startActivity(new Intent(getApplicationContext(),Tour.class));
//                        overridePendingTransition(0,0);
//                        return true;
                    case R.id.actionql:
                        startActivity(new Intent(getApplicationContext(),DNDK.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.actiontk:
                        startActivity(new Intent(getApplicationContext(),Timkiem.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });


    }

    private void loadMenu() {
        adapter = new FirebaseRecyclerAdapter<Place, MenuViewHolder>(Place.class,R.layout.menu_item,MenuViewHolder.class,place) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Place model, int position) {
                viewHolder.txtMenuName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageView);
                Place clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //get planceId and send to new activity
                        Intent tourList = new Intent(Home.this, TourList.class);
                        //because planceId is ey, so we just get key of this item
                        tourList.putExtra("PlaceId",adapter.getRef(position).getKey());
                        startActivity(tourList);
                    }
                });

            }
        };
        recyler_menu.setAdapter(adapter);
    }
}