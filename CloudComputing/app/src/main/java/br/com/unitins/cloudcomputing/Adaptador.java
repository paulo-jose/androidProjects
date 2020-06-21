package br.com.unitins.cloudcomputing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Paulo on 30/03/2016.
 */
public class Adaptador extends BaseAdapter {
    private Context contexto;
    private ArrayList<Integer> lista;


    public Adaptador(Context contexto, ArrayList<Integer> lista) {
        this.contexto = contexto;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imagem = new ImageView(contexto);
        imagem.setImageBitmap(retornaImagem(position));
        //System.out.println(position);
        imagem.setAdjustViewBounds(true);
        return imagem;
    }

    Bitmap retornaImagem(int codigo)
    {
        if(codigo == 0)
        {
            return BitmapFactory.decodeResource(contexto.getResources(), R.drawable.aws);
        }
        else if(codigo == 1)
        {
            return BitmapFactory.decodeResource(contexto.getResources(), R.drawable.azure);
        }
        else if(codigo == 2)
        {
            return BitmapFactory.decodeResource(contexto.getResources(), R.drawable.google);
        }
        else if(codigo == 3)
        {
            return BitmapFactory.decodeResource(contexto.getResources(), R.drawable.ibm);
        }

        return null;
    }
}
