package com.example.paulo.ahpplataforme.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulo.ahpplataforme.DAO.DBManager;
import com.example.paulo.ahpplataforme.R;
import com.example.paulo.ahpplataforme.adapter.CriterioAlternativaAdpter;
import com.example.paulo.ahpplataforme.model.AlternativaCriterio;

/**
 * Created by PAULO on 21/09/2016.
 */
public class TelaAddPeso extends AppCompatActivity {

    private EditText editTextNome;
    private TextView textViewTitulo;
    private int id_alternativa = 0;
    private int id_criterio = 0;
    private Intent intent;
    private AlternativaCriterio alternativaCriterio = null;
    private Boolean alterar = false;
    private int index = 0;
    DBManager bd = null;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_add_criterio);

        editTextNome = (EditText) findViewById(R.id.editTextNome);
        textViewTitulo = (TextView) findViewById(R.id.textViewTitulo);


        editTextNome.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editTextNome.setHint("* Peso");

        alternativaCriterio = CriterioAlternativaAdpter.alternativaCriterio;

        intent = getIntent();

        bd = new DBManager(this);

        if (alternativaCriterio.getPeso() != 0.0) {

            textViewTitulo.setText("Novo Peso");
            editTextNome.setText(alternativaCriterio.getPeso() + "");
            alterar = true;

        } else {
            textViewTitulo.setText("Altera Peso");
        }


    }


    public void salvarCriterio(View view) {

        alternativaCriterio.setPeso(Double.valueOf(editTextNome.getText().toString()));

        if (alterar) {

            bd.altualizar(alternativaCriterio);
            Intent intent = new Intent(this, ListViewCriterioAlternativa.class);
            Toast.makeText(this, "Peso alterada com sucesso!", Toast.LENGTH_LONG).show();

            finish();

        } else {

            if (editTextNome != null && !editTextNome.getText().toString().equals("")) {

                if (bd.inserir(alternativaCriterio) != -1) {

                    Log.i("Info", "id_criterio Tela :" + alternativaCriterio.getCriterio().getId());
                    Log.i("Info", "Peso Tela peso :" + alternativaCriterio.getPeso());
                    CriterioAlternativaAdpter.alternativaCriterio = alternativaCriterio;
                    alternativaCriterio.getCriterio().setPeso(alternativaCriterio.getPeso());
                    bd.altualizar(alternativaCriterio.getCriterio());
                    Toast.makeText(this, "Peso inserido com sucesso!", Toast.LENGTH_LONG).show();
                    finish();

                } else {

                    Toast.makeText(this, "Erro em adicionar o peso!", Toast.LENGTH_LONG).show();
                    finish();
                }

            } else {
                mostraMessagem("Campo Vazio", "Por favor preencha os campo que contém *");
            }

        }
    }


    public void mostraMessagem(String titulo, String messagem)
    {
        AlertDialog.Builder msg = new AlertDialog.Builder(TelaAddPeso.this);
        msg.setTitle(titulo);
        msg.setMessage(messagem);
        msg.setNegativeButton("OK", null);
        msg.show();

    }



}

