package br.com.unitins.cloudcomputing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class Tela_principal extends AppCompatActivity {

    int idImagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_principal);


        ArrayList<Integer> lista = new ArrayList<>();
        lista.add(R.drawable.aws);
        lista.add(R.drawable.azure);
        lista.add(R.drawable.ibm);
        lista.add(R.drawable.google);


        GridView gv = (GridView) findViewById(R.id.gridView);
        gv.setAdapter(new Adaptador(this, lista));


        gv.setOnItemClickListener(new GridView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idImagem = position;
                trocaDeTela();
            }
        });

        }

    private void trocaDeTela() {

        Intent intecao;

        if(idImagem == 0)
        {
            intecao = new Intent(this, Tela_aws.class);
            startActivity(intecao);
        }
        else if(idImagem == 1)
        {
            intecao = new Intent(this, Tela_azure.class);
            startActivity(intecao);
        }
        else if(idImagem == 2)
        {
            intecao = new Intent(this, Tela_google.class);
            startActivity(intecao);
        }
        else if(idImagem == 3)
        {
            intecao = new Intent(this, Tela_ibm.class);
            startActivity(intecao);
        }
    }


}

