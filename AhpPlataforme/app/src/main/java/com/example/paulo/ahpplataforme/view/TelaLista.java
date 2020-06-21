package com.example.paulo.ahpplataforme.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.paulo.ahpplataforme.DAO.DBManager;
import com.example.paulo.ahpplataforme.CalcularMatriz;
import com.example.paulo.ahpplataforme.ParCriterios;
import com.example.paulo.ahpplataforme.R;
import com.example.paulo.ahpplataforme.adapter.Adapter;
import com.example.paulo.ahpplataforme.model.Criterio;

import java.util.ArrayList;
import java.util.List;

public class TelaLista extends AppCompatActivity {

    ListView listCriterios = null;
    Adapter adapter = null;
    List<ParCriterios> list = null;
   // String[] criterios = {"PREÇO", "SGBD", "SUPORTE", "RUN-TIME"};
    List<Criterio> criterios = null;
    CalcularMatriz calcularMatriz =null;
    public List<Double> vetorResultado = null;
    DBManager db = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lista);

        listCriterios = (ListView)findViewById(R.id.listView);

        db = new DBManager(this);
        criterios = db.buscarCriterios();
        iniciar();
        adapter = new Adapter(this, list);
        listCriterios.setAdapter(adapter);

       }




    public void calcular(View v)
    {

        ArrayList<Double> pesos = new ArrayList<Double>();

        for(int i = 0; i  < adapter.getCount(); i ++)
        {

            if(adapter.getItem(i).getPesoCriterio1() == 0.0)
            {
               pesos.add(1 / adapter.getItem(i).getPesoCriterio2());
            }
            else
            {
                pesos.add(adapter.getItem(i).getPesoCriterio1());
            }


        }

        calcularMatriz = new CalcularMatriz(this, criterios.size());
        calcularMatriz.execute(pesos);

        //Toast msg = Toast.makeText(this, adapter.getItem(1).getPesoCriterio1() + "", Toast.LENGTH_SHORT);
        //msg.show();
    }




    public void iniciar() {

        list = new ArrayList<>();

        for(int i = 0; i < criterios.size(); i ++)
        {
            for(int j = i; j < criterios.size() -1; j++)
            {
                ParCriterios aux = new ParCriterios();
                aux.setNomeCriterio1(criterios.get(i).getNome());
                aux.setNomeCriterio2(criterios.get(j +1).getNome());

                list.add(aux);
            }
        }
    }

    public void chamarTelaGrafico()
    {
        Intent intencao = new Intent(this, TelaGrafico.class);
        TelaGrafico.resultados = vetorResultado;
        startActivity(intencao);

    }
}
