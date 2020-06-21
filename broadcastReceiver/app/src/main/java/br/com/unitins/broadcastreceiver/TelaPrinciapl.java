package br.com.unitins.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


//PARA A CAPTURA DE BROACAST EXTERNOS E PRECISO PEDIR PERMISSAO NO ARQUIVO MANIFEST
public class TelaPrinciapl extends AppCompatActivity {


    ReceiverViaCodigo vrReceiverCodigo = null;
    public static ImageView vrImagem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_princiapl);

        vrReceiverCodigo = new ReceiverViaCodigo();
        registerReceiver(vrReceiverCodigo, new IntentFilter("MUDA_PARA_VERMELHO"));

        vrImagem = (ImageView) findViewById(R.id.imageView);
    }

    public void dispararVerde(View botao)
    {
        this.sendBroadcast(new Intent("MUDA_PARA_VERDE"));
    }

    public void dispararVeremlho(View botao)
    {
        this.sendBroadcast(new Intent("MUDA_PARA_VERMELHO"));
    }
}
