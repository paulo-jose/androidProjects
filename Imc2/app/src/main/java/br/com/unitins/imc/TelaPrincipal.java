package br.com.unitins.imc;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TelaPrincipal extends AppCompatActivity {

    EditText labelPeso = null;
    EditText labelAltura = null;
    TextView labelResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        labelPeso = (EditText) findViewById(R.id.editPeso);
        labelAltura = (EditText) findViewById(R.id.editAltura);
        labelResult = (TextView) findViewById(R.id.textViewResult);
    }


    public void calcular(View botao) {

        Double peso = null;
        Double altura = null;
        Double result = null;

        peso = Double.parseDouble(labelPeso.getText().toString());
        altura = Double.parseDouble(labelAltura.getText().toString());

        result = peso / Math.pow(altura, 2);

        Bundle vrParametros = new Bundle();
        vrParametros.putDouble("resultado", result);


        Intent intencao = new Intent(this, TelaGordo.class);
        intencao.putExtras(vrParametros);
        startActivity(intencao);


    }
}
