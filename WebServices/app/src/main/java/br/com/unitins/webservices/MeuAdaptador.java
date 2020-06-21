package br.com.unitins.webservices;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulo on 18/05/2016.
 */
public class MeuAdaptador extends ArrayAdapter<ModeloDadosEmpresa>
{

    Context vrContext;
    ArrayList<Bitmap> bitmaps;
    ImageView icon = null;
    TextView titulo = null;
    TextView descricao = null;
    TextView clienteDesde = null;

    public MeuAdaptador(Context context, List<ModeloDadosEmpresa> vetorDados, ArrayList<Bitmap> bitmaps) {
        super(context,0,vetorDados );
        vrContext = context;
        this.bitmaps = bitmaps;
    }



    public View getView(int indice, View celularReciclada, ViewGroup layouPai){

        ModeloDadosEmpresa usuario = this.getItem(indice);


        if(celularReciclada == null)
        {
            Log.i("info", "celula criada");
            celularReciclada = LayoutInflater.from(getContext()).inflate(R.layout.layout_celula, layouPai, false);

        }
        else
        {
            Log.i("info", "celular reciclada");
        }

        icon = (ImageView) celularReciclada.findViewById(R.id.imageView);
        titulo = (TextView) celularReciclada.findViewById(R.id.textViewTitulo);
        descricao = (TextView) celularReciclada.findViewById(R.id.textViewDescricao);
        clienteDesde = (TextView) celularReciclada.findViewById(R.id.textViewClienteDesde);


        titulo.setText(usuario.getNome());
        descricao.setText(usuario.getDescricao());
        clienteDesde.setText(usuario.getClienteDesde());
        icon.setImageBitmap(bitmaps.get(indice));

        return celularReciclada;

    }



}


