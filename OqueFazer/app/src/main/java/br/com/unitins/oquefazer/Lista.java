package br.com.unitins.oquefazer;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;


import org.json.JSONArray;

import java.util.ArrayList;

import br.com.unitins.oquefazer.googlePlace.Example;
import br.com.unitins.oquefazer.googlePlace.Location;
import br.com.unitins.oquefazer.googlePlace.Result;

public class Lista extends AppCompatActivity  {

    ListView listLugares = null;
    TextView cidadeTextView = null;
    Adaptador adaptador = null;
    ArrayList<Lugar> lugares = null;
    String cidade = null;
    SupportMapFragment suportMap = null;
  //  Location location = null;
   // LocationManager locationManager = null;
    ArrayList<Place> places = null;
    ConsomeServicos webService = null;
    Example locais = null;

    double latitude;
    double longitude;
    String cahveAPI = "AIzaSyCphiHZa0sD-U6GmrLy49d_yoV8ddQ8Jcw";
    LatLng cidadeAtual = null;
    Context vrContexto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lista);

        lugares = new ArrayList<>();
        listLugares = (ListView)findViewById(R.id.listView);
        cidadeTextView = (TextView)findViewById(R.id.textViewCidade);
        vrContexto = this;

        suportMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView));
        //suportMap.getMap().setMyLocationEnabled(true);
        suportMap.getMap().setTrafficEnabled(true);


        cidade = getIntent().getExtras().getString("cidade");
        cidadeTextView.setText(cidade);

        carregarLugares();
        setLocalização();


        cidadeAtual = new LatLng(latitude, longitude);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(cidadeAtual, 16);

        suportMap.getMap().animateCamera(cameraUpdate);

        adaptador = new Adaptador(this, lugares);
        listLugares.setAdapter(adaptador);

        listLugares.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    mudarTela( 0 );
                    //webService.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+"&radius=500&types=bar&key=AIzaSyCphiHZa0sD-U6GmrLy49d_yoV8ddQ8Jcw");
                } else if (position == 1) {
                    mudarTela( 1 );
                    //webService.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+"&radius=500&types=casino&key=AIzaSyCphiHZa0sD-U6GmrLy49d_yoV8ddQ8Jcw");
                } else if (position == 2) {
                    mudarTela( 2 );
                    //webService.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+"&radius=500&types=restaurant&key=AIzaSyCphiHZa0sD-U6GmrLy49d_yoV8ddQ8Jcw");
                } else {
                    mudarTela( 3 );
                    //webService.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+"&radius=500&types=park&key=AIzaSyCphiHZa0sD-U6GmrLy49d_yoV8ddQ8Jcw");
                }


            }});}




    private void mudarTela(int position) {

        webService = new ConsomeServicos(this);

        if(position == 0)
        {

           webService.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+"&radius=500&types=bar&key=AIzaSyCphiHZa0sD-U6GmrLy49d_yoV8ddQ8Jcw");
        }
        else if (position == 1)
        {

            webService.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+"&radius=500&types=night_club&key=AIzaSyCphiHZa0sD-U6GmrLy49d_yoV8ddQ8Jcw");
        }
        else if (position == 2)
        {

            webService.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+"&radius=500&types=restaurant&key=AIzaSyCphiHZa0sD-U6GmrLy49d_yoV8ddQ8Jcw");
        }

        else if(position == 3)
            webService.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+"&radius=500&types=park&key=AIzaSyCphiHZa0sD-U6GmrLy49d_yoV8ddQ8Jcw");

    }


    private void setLocalização() {

        if(cidade.equals( "Palmas"))
        {
            latitude = -10.185;
            longitude = -48.3343;
        }
        else if (cidade.equals( "Goiânia"))
        {
            latitude = -16.675663;
            longitude = -49.252180;
        }
        else if (cidade.equals( "Cuiabá"))
        {
            latitude = -15.597863;
            longitude = -56.091592;
        }
        else if (cidade.equals( "Belém"))
        {
            latitude = -1.438033;
            longitude = -48.469549;
        }
        else
        {
            latitude = -9.627265;
            longitude = -35.727204;
        }

    }

    public void chamarTelaMapa() {

        Bundle paramentros = new Bundle();

        paramentros.putString("locais", String.valueOf(locais));

        locais = webService.getLugares();

        Intent intecao = new Intent(this, Place.class);
        intecao.putExtras(paramentros);
        startActivity(intecao);
        Place.locais = webService.getLugares();
        Place.latitude = latitude;
        Place.longitude = longitude;
        webService.cancel( true );
    }

    public void carregarLugares() {

        String[] opcoes = new String[]{"Bares", "Festas", "Restaurantes", "Pontos Turisticos"};

        for(int i =0; i < opcoes.length; i++)
        {
            lugares.add(new Lugar(opcoes[i], opcoes[i], new ArrayList<String>()));
        }
    }


}



