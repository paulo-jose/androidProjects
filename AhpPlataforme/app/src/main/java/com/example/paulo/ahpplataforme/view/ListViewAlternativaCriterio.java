package com.example.paulo.ahpplataforme.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.paulo.ahpplataforme.DAO.DBManager;
import com.example.paulo.ahpplataforme.R;
import com.example.paulo.ahpplataforme.adapter.AlternativaCriterioAdpter;
import com.example.paulo.ahpplataforme.model.Alternativa;

import java.util.List;

public class ListViewAlternativaCriterio extends AppCompatActivity {

    private List<Alternativa> listaAlternativa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_view_alternativa_criterio);

        ListView listView = (ListView) findViewById(R.id.listView);

        DBManager bd = new DBManager(this);
        listaAlternativa = bd.buscar();
        AlternativaCriterioAdpter adpter = new AlternativaCriterioAdpter(this, listaAlternativa);
        listView.setAdapter(adpter);
    }
}
