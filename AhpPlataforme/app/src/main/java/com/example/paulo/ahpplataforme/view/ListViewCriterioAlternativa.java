package com.example.paulo.ahpplataforme.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.paulo.ahpplataforme.DAO.DBManager;
import com.example.paulo.ahpplataforme.R;
import com.example.paulo.ahpplataforme.adapter.CriterioAlternativaAdpter;
import com.example.paulo.ahpplataforme.model.AlternativaCriterio;
import com.example.paulo.ahpplataforme.model.Criterio;

import java.util.List;

public class ListViewCriterioAlternativa extends AppCompatActivity {

    private List<Criterio> listCriteiro;
    private int id_alternativa = -1;
    private AlternativaCriterio alternativaCriterio = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_view_criterio_alternativa);

        ListView listView = (ListView) findViewById(R.id.listView);

        DBManager bd = new DBManager(this);
        listCriteiro = bd.buscarCriterios();

       if(alternativaCriterio == null)
            id_alternativa = getIntent().getExtras().getInt("id_alternativa");


        if(id_alternativa != -1)
        {
            alternativaCriterio = new AlternativaCriterio();
            alternativaCriterio.setAlternativa(bd.buscaAlternativaPorId(id_alternativa));
        }

        CriterioAlternativaAdpter adpter = new CriterioAlternativaAdpter(this, listCriteiro, alternativaCriterio);
        listView.setAdapter(adpter);
    }
}
