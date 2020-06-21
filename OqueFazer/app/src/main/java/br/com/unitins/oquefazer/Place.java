package br.com.unitins.oquefazer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.location.Address;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.unitins.oquefazer.googlePlace.Example;
import br.com.unitins.oquefazer.googlePlace.Location;
import br.com.unitins.oquefazer.googlePlace.Result;

public class Place extends AppCompatActivity {

    SupportMapFragment suportMap = null;
    static Example locais;
    ListView lista = null;

    static double latitude = -10.185;
    static double longitude = -48.3343;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_place);

        suportMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        suportMap.getMap().setMyLocationEnabled(true);
        suportMap.getMap().setTrafficEnabled(true);
        suportMap.getMap().setMapType( GoogleMap.MAP_TYPE_NORMAL );

        lista = (ListView)findViewById(R.id.listView);


        carregarLista();


        LatLng cidadeAtual = new LatLng(latitude, longitude);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(cidadeAtual, 15);
        suportMap.getMap().animateCamera(cameraUpdate);


        for(Result res : locais.getResults())
        {

            LatLng posicao = new LatLng( res.getGeometry().getLocation().getLat(), res.getGeometry().getLocation().getLng() );
            suportMap.getMap().addMarker( new MarkerOptions()
                    .title(res.getName())
                    .position(posicao)
            );
        }




    }

    private void carregarLista() {

        ArrayList<String> nomeLugares = new ArrayList<>(  );

        for(Result res : locais.getResults())
        {
            nomeLugares.add( res.getName() );
        }

        ArrayAdapter<String> listaAdapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_activated_1, nomeLugares );

        if(nomeLugares.size() == 0)
            listaAdapter.add( "NENHUM LOCAL ENCONTRADO" );

        lista.setAdapter( listaAdapter );
    }


}