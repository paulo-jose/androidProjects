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

import com.example.paulo.ahpplataforme.DAO.DBManager;
import com.example.paulo.ahpplataforme.R;
import com.example.paulo.ahpplataforme.view.TelaAddPeso;
import com.example.paulo.ahpplataforme.model.AlternativaCriterio;
import com.example.paulo.ahpplataforme.model.Criterio;

import java.util.List;

/**
 * Created by PAULO on 18/09/2016.
 */
public class CriterioAlternativaAdpter extends BaseAdapter {


    private Context context;
    private List<Criterio> lista;
    public static AlternativaCriterio alternativaCriterio;
    int id_alternativa;


    public CriterioAlternativaAdpter(Context context, List<Criterio> listaCriterios, AlternativaCriterio alternativaCriterio) {
        this.context = context;
        this.lista = listaCriterios;
        this.alternativaCriterio = alternativaCriterio;
        id_alternativa = alternativaCriterio.getAlternativa().getId();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int auxPosition = position;

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.layout_item_lista_alternativa_criterio, null);

        TextView tv = (TextView) layout.findViewById(R.id.textViewNome);
        ImageView imgView = (ImageView) layout.findViewById(R.id.imageView);

        final DBManager bd = new DBManager(context);

        tv.setText(lista.get(position).getNome());



            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    alternativaCriterio.setCriterio(bd.bsucarCriterioPorId(lista.get(auxPosition).getId()));
                    alternativaCriterio = bd.buscarAlternativaCriterioPorId(id_alternativa, alternativaCriterio.getCriterio().getId());


                    if(alternativaCriterio != null)
                    {
                      //  alternativaCriterio = bd.buscarAlternativaCriterioPorId(alternativaCriterio.getAlternativa().getId(), alternativaCriterio.getCriterio().getId());

                        Intent intent = new Intent(context, TelaAddPeso.class);
                        intent.putExtra("id", position);
                        context.startActivity(intent);
                    }
                    else
                    {
                        alternativaCriterio = new AlternativaCriterio();
                        alternativaCriterio.getAlternativa().setId(id_alternativa);
                        alternativaCriterio.setCriterio(bd.bsucarCriterioPorId(lista.get(auxPosition).getId()));
                        Intent intent = new Intent(context, TelaAddPeso.class);
                        context.startActivity(intent);
                    }

                }
            });


        return layout;
    }


}
