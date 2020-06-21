package com.example.paulo.ahpplataforme.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.paulo.ahpplataforme.DAO.DBManager;
import com.example.paulo.ahpplataforme.R;
import com.example.paulo.ahpplataforme.adapter.CriterioAdpter;
import com.example.paulo.ahpplataforme.model.Criterio;

import java.util.List;

public class ListViewCriterio extends AppCompatActivity {


    private List<Criterio> listaCriterios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_view_criterio);

        ListView listView = (ListView) findViewById(R.id.listView);

        DBManager bd = new DBManager(this);
        listaCriterios = bd.buscarCriterios();
        CriterioAdpter adpter = new CriterioAdpter(this, listaCriterios);
        listView.setAdapter(adpter);
    }

    public void adicionar(View view) {

        Intent intecao = new Intent(this, TelaAddCriterio.class);
        startActivity(intecao);
        finish();
    }
}
