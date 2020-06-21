package com.example.paulo.ahpplataforme.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.paulo.ahpplataforme.view.ListViewCriterioAlternativa;
import com.example.paulo.ahpplataforme.R;
import com.example.paulo.ahpplataforme.model.Alternativa;

import java.util.List;

/**
 * Created by PAULO on 18/09/2016.
 */
public class AlternativaCriterioAdpter extends BaseAdapter {

    private Context context;
    private List<Alternativa> lista;


    public AlternativaCriterioAdpter(Context context, List<Alternativa> listaAlternativa) {
        this.context = context;
        this.lista = listaAlternativa;

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
        return lista.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int auxPosition = position;

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.layout_item_lista_alternativa_criterio, null);

        TextView tv = (TextView) layout.findViewById(R.id.textViewNome);
        ImageView imgView = (ImageView) layout.findViewById(R.id.imageView);

        tv.setText(lista.get(position).getNome());

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, ListViewCriterioAlternativa.class);
                    intent.putExtra("id_alternativa", lista.get(auxPosition).getId());
                    context.startActivity(intent);
                }
            });


        return layout;
    }


}
