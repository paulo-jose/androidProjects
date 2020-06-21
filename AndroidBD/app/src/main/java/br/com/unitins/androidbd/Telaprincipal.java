package br.com.unitins.androidbd;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class Telaprincipal extends AppCompatActivity
{
    GerenciadorBanco vrBancoDados = null;
    EditText vrCampoNome = null;
    EditText vrCampoAno = null;
    EditText vrCampoPlaca = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_telaprincipal);

        vrBancoDados = new GerenciadorBanco(this, "BDCarros", 1);

        vrCampoNome = (EditText)findViewById(R.id.editTextNome);
        vrCampoAno = (EditText)findViewById(R.id.editTextAno);
        vrCampoPlaca = (EditText)findViewById(R.id.editTextPlaca);
    }


    public void onDestroy()
    {
        super.onDestroy();
        vrBancoDados.close();
    }

    public void trataEventoBotao(View botao)
    {
        ContentValues dados = new ContentValues();

        dados.put("nome", vrCampoNome.getText().toString());
        dados.put("placa", vrCampoPlaca.getText().toString());
        dados.put("ano", vrCampoAno.getText().toString());

        vrBancoDados.insereDados(dados);

        ArrayList<String> carros = vrBancoDados.listaCarros();
    }

    public void deletarRegistro(View botao)
    {
        String placa  = "MDLL22";

        vrBancoDados.removerCarro(placa);

    }

}
