package br.com.unitins.trocadetelas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TerceiraTela extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_terceira_tela);
    }

    public void home(View botao)
    {
       finish();
    }


}
