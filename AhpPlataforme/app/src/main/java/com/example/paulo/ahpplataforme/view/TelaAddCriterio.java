package com.example.paulo.ahpplataforme.view;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulo.ahpplataforme.DAO.DBManager;
import com.example.paulo.ahpplataforme.R;
import com.example.paulo.ahpplataforme.model.Criterio;

public class TelaAddCriterio extends AppCompatActivity {

    private EditText editTextNome;
    private TextView textViewTitulo;

    private Criterio criterio;
    private Intent intent;
    private Boolean alterar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_add_criterio);


        editTextNome = (EditText) findViewById(R.id.editTextNome);
        textViewTitulo = (TextView) findViewById(R.id.textViewTitulo);
        criterio = new Criterio();


        intent = getIntent();

        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if(bundle != null)
            {
                criterio.setId(bundle.getInt("id"));
                criterio.setNome(bundle.getString("nome"));
                editTextNome.setText(criterio.getNome());
                alterar = true;
            }
        }

    }


    public void salvarCriterio(View view) {


        DBManager bd = new DBManager(this);

        criterio.setNome(editTextNome.getText().toString());

        if (alterar) {

            bd.altualizar(criterio);
            Intent intent = new Intent(this, ListViewCriterio.class);
            startActivity(intent);
            Toast.makeText(this, "Criterio alterada com sucesso!", Toast.LENGTH_LONG).show();
            finish();

        } else {


            if(editTextNome != null && !editTextNome.getText().toString().equals("")) {
                Intent intent = new Intent(this, ListViewCriterio.class);
                bd.inserir(criterio);
                startActivity(intent);
                Toast.makeText(this, "Criterio inserida com sucesso!", Toast.LENGTH_LONG).show();
                finish();
            }
            else
            {
                mostraMessagem("Campo Vazio", "Por favor preencha os campo que contém *");
            }
        }
    }


    public void mostraMessagem(String titulo, String messagem)
    {
        AlertDialog.Builder msg = new AlertDialog.Builder(TelaAddCriterio.this);
        msg.setTitle(titulo);
        msg.setMessage(messagem);
        msg.setNegativeButton("OK", null);
        msg.show();

    }

}

