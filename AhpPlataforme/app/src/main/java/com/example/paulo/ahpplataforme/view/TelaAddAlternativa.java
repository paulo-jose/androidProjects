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
import com.example.paulo.ahpplataforme.model.Alternativa;
import com.example.paulo.ahpplataforme.model.Criterio;

public class TelaAddAlternativa extends AppCompatActivity {

    private EditText editTextNome;
    private TextView textViewTitulo;
    private Alternativa alternativa;
    private Criterio criterio;
    private Intent intent;
    private Boolean alterar = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_add_alternativa);

        editTextNome = (EditText) findViewById(R.id.editTextNome);
        textViewTitulo = (TextView) findViewById(R.id.textViewTitulo);
        alternativa = new Alternativa();


        intent = getIntent();

            if (intent != null) {
                Bundle bundle = intent.getExtras();
                if(bundle != null)
                {
                    alternativa.setId(bundle.getInt("id"));
                    alternativa.setNome(bundle.getString("nome"));
                    editTextNome.setText(alternativa.getNome());
                    alterar = true;
                }

            }

    }


    public void salvarAlternativa(View view) {


        DBManager bd = new DBManager(this);

        alternativa.setNome(editTextNome.getText().toString());

        if (alterar) {

            bd.altualizar(alternativa);
            Intent intent = new Intent(this, ListViewAlternativas.class);
            startActivity(intent);
            Toast.makeText(this, "Alternativa alterada com sucesso!", Toast.LENGTH_LONG).show();
            finish();

            //           Intent intent = new Intent(this, ListViewAlternativas.class);
            //          startActivity(intent);
        } else {

            if(editTextNome != null && !editTextNome.getText().toString().equals("")) {
                Intent intent = new Intent(this, ListViewAlternativas.class);
                bd.inserir(alternativa);
                startActivity(intent);
                Toast.makeText(this, "Alternativa inserida com sucesso!", Toast.LENGTH_LONG).show();
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
        AlertDialog.Builder msg = new AlertDialog.Builder(TelaAddAlternativa.this);
        msg.setTitle(titulo);
        msg.setMessage(messagem);
        msg.setNegativeButton("OK", null);
        msg.show();

    }



}
