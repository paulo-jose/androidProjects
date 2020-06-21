package br.com.unitins.oquefazer;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class TelaPrincipal extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Spinner spinner;
    String[] capitais = new String[]{"Palmas", "Goiânia", "Cuiabá", "Belém", "Maceio"};
    ArrayAdapter<String> meuAdapter;
    String cidade;



    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_principal);
        spinner = (Spinner) findViewById(R.id.spinner);
        meuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, capitais);
        meuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(meuAdapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        cidade = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void carregar(View view)
    {

        Bundle paramentros = new Bundle();
        paramentros.putString("cidade", cidade);
        Intent intecao = new Intent(this, Lista.class);
        intecao.putExtras(paramentros);
        startActivity(intecao);
    }






}
