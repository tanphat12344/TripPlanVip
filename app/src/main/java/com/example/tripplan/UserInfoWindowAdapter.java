package com.example.tripplan;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class UserInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    LayoutInflater inflater = null;

    UserInfoWindowAdapter(LayoutInflater inflater){
        this.inflater= inflater;

    }
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {

        View inforWindows= inflater.inflate(R.layout.activity_custom_info,null);

        TextView title = (TextView)inforWindows.findViewById(R.id.title);
        TextView description = (TextView)inforWindows.findViewById(R.id.snippet);

        title.setText(marker.getTitle());
        description.setText(marker.getSnippet());

        return(inforWindows);
    }
}
