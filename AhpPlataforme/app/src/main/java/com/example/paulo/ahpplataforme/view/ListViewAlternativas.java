package com.example.paulo.ahpplataforme.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.paulo.ahpplataforme.DAO.DBManager;
import com.example.paulo.ahpplataforme.R;
import com.example.paulo.ahpplataforme.adapter.AlternativaAdpter;
import com.example.paulo.ahpplataforme.model.Alternativa;

import java.util.List;

public class ListViewAlternativas extends AppCompatActivity {

    private List<Alternativa> listaAlternativa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_view_alternativas);

        ListView listView = (ListView) findViewById(R.id.listView);

        DBManager bd = new DBManager(this);
        listaAlternativa = bd.buscar();
        AlternativaAdpter adpter = new AlternativaAdpter(this, listaAlternativa);
        listView.setAdapter(adpter);

    }


    public void adicionar(View view) {

        Intent intecao = new Intent(this, TelaAddAlternativa.class);
        startActivity(intecao);
        finish();
    }

}
