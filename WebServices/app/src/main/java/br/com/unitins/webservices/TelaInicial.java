package br.com.unitins.webservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TelaInicial extends AppCompatActivity {


    ArrayList<ModeloDadosEmpresa> vrLista = null;
    ConsomeServicos vrWebService = null;
    MeuAdaptador adaptador = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_inicial);

        vrLista = new ArrayList<>();
        vrWebService = new ConsomeServicos(this);
        vrWebService.execute("http://www.agenciamercadocentral.com.br/app_agencia/clientes.php");


    }

    public void atualizarLista() {
        vrLista = vrWebService.getVrListEmpresa();
        adaptador = new MeuAdaptador(this, vrLista, vrWebService.bitmaps);
        ListView meuList = (ListView)findViewById(R.id.listaPortifolio);
        meuList.setAdapter(adaptador);
    }
}
