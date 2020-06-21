package br.com.unitins.cloudcomputing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TelaInicial extends AppCompatActivity {

    ImageView iconDoucmento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_inicial);

        iconDoucmento = (ImageView) findViewById(R.id.imageViewDocumentacao);
        iconDoucmento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chamarTelaDocumento();
            }
        });
    }

    public void chamarTelaDocumento()
    {
        Intent intencao = new Intent(this, Tela_principal.class);
        startActivity(intencao);
    }
}
