package br.com.unitins.oquefazer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Paulo on 26/05/2016.
 */
public class Adaptador extends ArrayAdapter<Lugar> {


    ImageView icon = null;
    TextView titulo = null;
    Context vrContext;

    public Adaptador(Context context, List<Lugar> vetorDados) {
        super(context, 0, vetorDados);
        this.vrContext = context;
    }


    public View getView(int indice, View celularReciclada, ViewGroup layouPai){

        Lugar lugar = this.getItem(indice);


        if(celularReciclada == null)
        {
            Log.i("info", "celula criada");
            celularReciclada = LayoutInflater.from(getContext()).inflate(R.layout.layout_celula, layouPai, false);

        }
        else
        {
            Log.i("info", "celular reciclada");
        }

        icon = (ImageView) celularReciclada.findViewById(R.id.imageViewIcon);
        titulo = (TextView) celularReciclada.findViewById(R.id.textViewTitulo);


        titulo.setText(lugar.getTitulo());
        icon.setImageBitmap(retornaImagem(lugar.getIcon()));

        return celularReciclada;

    }

    public Bitmap retornaImagem(String icon) {

        if(icon.equals("Bares"))
        {
            return BitmapFactory.decodeResource(vrContext.getResources(), R.drawable.bares);
        }
        else if(icon.equals("Restaurantes"))
        {
            return BitmapFactory.decodeResource(vrContext.getResources(), R.drawable.restaurante);
        }
        else if(icon.equals("Festas"))
        {
            return BitmapFactory.decodeResource(vrContext.getResources(), R.drawable.festas);
        }

            return BitmapFactory.decodeResource(vrContext.getResources(), R.drawable.pontos);


    }

}
