package com.example.tripplan.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tripplan.Interface.ItemClickListener;
import com.example.tripplan.R;


public class TourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tour_name;
    public ImageView tour_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public TourViewHolder(View itemView){
        super(itemView);

        tour_name= (TextView)itemView.findViewById(R.id.tour_name);
        tour_image = (ImageView)itemView.findViewById(R.id.tour_image);

        itemView.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
}
