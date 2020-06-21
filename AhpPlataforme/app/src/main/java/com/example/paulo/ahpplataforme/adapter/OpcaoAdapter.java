package com.example.paulo.ahpplataforme.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paulo.ahpplataforme.R;

import java.util.List;

/**
 * Created by PAULO on 17/09/2016.
 */
public class OpcaoAdapter extends ArrayAdapter<String> {

    ImageView icon = null;
    TextView rotulo = null;
    Context contexto = null;

    public OpcaoAdapter(Context context, String[] opcoes) {
        super(context, 0, opcoes);
        this.contexto = context;
    }

    public View getView(int indice, View celularReciclada, ViewGroup layouPai)
    {
        String titulo = this.getItem(indice);

        if(celularReciclada == null)
            celularReciclada = LayoutInflater.from(getContext()).inflate(R.layout.layout_item_lista, layouPai, false);

        icon = (ImageView) celularReciclada.findViewById(R.id.imageViewIcon);
        rotulo = (TextView) celularReciclada.findViewById(R.id.textViewRotulo);

        rotulo.setText(titulo);
        icon.setImageBitmap(BitmapFactory.decodeResource(contexto.getResources(), R.drawable.img_plus));

        return  celularReciclada;

    }

}
