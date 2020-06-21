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
import com.example.paulo.ahpplataforme.view.TelaAddAlternativa;
import com.example.paulo.ahpplataforme.model.Alternativa;

import java.util.List;

/**
 * Created by PAULO on 18/09/2016.
 */
public class AlternativaAdpter extends BaseAdapter {

    private Context context;
    private List<Alternativa> lista;


    public AlternativaAdpter(Context context, List<Alternativa> listaAlternativa) {
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
        final RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.layout_item_lista_alternativa, null);

        TextView tv = (TextView) layout.findViewById(R.id.textViewNome);


        ImageView editarImg = (ImageView) layout.findViewById(R.id.imageViewEditar);
        ImageView deletarImg = (ImageView) layout.findViewById(R.id.imageViewDeletar);

        lista = (List<Alternativa>) lista;
        tv.setText(lista.get(position).getNome());

            editarImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, TelaAddAlternativa.class);
                    intent.putExtra("nome", lista.get(auxPosition).getNome());
                    intent.putExtra("id", lista.get(auxPosition).getId());
                    context.startActivity(intent);
                }
            });

            deletarImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBManager bd = new DBManager(context);
                    bd.deletar(lista.get(auxPosition));
                    bd.deletarAlternativaCriterio(lista.get(auxPosition));
                    layout.setVisibility(View.GONE);
                }
            });


        return layout;
    }


}
