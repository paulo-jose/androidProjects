package br.com.unitins.maps;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;


public class FragmentMap extends Fragment implements OnMapReadyCallback {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_map, container, false);

        SupportMapFragment mapFragment = ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.fragmento2));

        mapFragment.getMapAsync(this);

        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
