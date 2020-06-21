package com.example.paulo.ahpplataforme.view;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.paulo.ahpplataforme.DAO.DBManager;
import com.example.paulo.ahpplataforme.R;
import com.example.paulo.ahpplataforme.adapter.OpcaoAdapter;
import com.example.paulo.ahpplataforme.model.Alternativa;
import com.example.paulo.ahpplataforme.model.AlternativaCriterio;
import com.example.paulo.ahpplataforme.model.Criterio;

import java.util.ArrayList;
import java.util.List;

public class TelaInicial extends AppCompatActivity {

    private ListView listView = null;
    private OpcaoAdapter adapter = null;
    Context context = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_inicial);
        context = this;
        //DBManager db = new DBManager(this);
        //db.deletarTodos();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.listView);

        String[] opcoes = new String[]{"ALTERNATIVA", "CRITÉRIO", "PESO"};

        adapter = new OpcaoAdapter(this, opcoes);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    Intent intecao = new Intent(context, ListViewAlternativas.class);
                    startActivity(intecao);

                }
                else if (position == 1)
                {
                    Intent intecao = new Intent(context, ListViewCriterio.class);
                    startActivity(intecao);

                } else if (position == 2)
                {

                    Intent intecao = new Intent(context, ListViewAlternativaCriterio.class);
                    startActivity(intecao);
                }
            }
        });
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void chamarTela(View view)
    {
        if(!verificarLista())
        {
            mostraMessagem("Alerta!!!!", "Peso não inserido para cada criterio, por favor insira o peso que está faltando");
        }
        else {
            Intent intecao = new Intent(context, TelaLista.class);
            startActivity(intecao);
        }
    }

    public boolean verificarLista() {
        DBManager db = new DBManager(this);
        List<AlternativaCriterio> listaAlternativaCriterio = new ArrayList<>();
        List<Alternativa> listAlternativa = new ArrayList<>();
        List<Criterio> listCriterio = new ArrayList<>();

        listAlternativa = db.buscar();
        listaAlternativaCriterio = db.buscarAlternativaCriterio();
        listCriterio = db.buscarCriterios();

        if((listAlternativa.size()*listCriterio.size()) == listaAlternativaCriterio.size())
            return true;

        return false;

    }

    public void listaAjuda(View view)
    {
        mostraMessagem(" MAPEAMENTO DAS PREFERENCIAS", "A atribuição do gral de importância é realizada da seguinte forma: \n \n" +
                " Gral 1 - Mesma importancia \n" +
                " Gral 3 - Importância pequena \n" +
                " Gral 5 - Importância grande \n" +
                " Gral 7 - Importância muito grande \n" +
                " Gral 9 - Importância absoluta \n"
                );

    }
    public void mostraMessagem(String titulo, String mensagem)
    {
        AlertDialog.Builder msg = new AlertDialog.Builder(TelaInicial.this);
        msg.setTitle(titulo);
        msg.setMessage(mensagem);
        msg.setNegativeButton("OK", null);
        msg.show();

    }
}
