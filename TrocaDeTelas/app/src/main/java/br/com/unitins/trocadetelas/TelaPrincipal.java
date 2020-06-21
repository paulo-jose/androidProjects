package br.com.unitins.trocadetelas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class TelaPrincipal extends AppCompatActivity {

    final int ID_TELA_2 = 2;
    final int ID_TELA_3 = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_principal);
    }

    public void chamaTela2(View botao)
    {
        Bundle vrParametros = new Bundle();
        vrParametros.putString("user", "Paulo");
        vrParametros.putInt("senha", 1234);

        Intent intencao = new Intent(this, SegundaTela.class);
        intencao.putExtras(vrParametros);
        startActivityForResult(intencao, ID_TELA_2);
    }

    public void onActivityResult(int codTEla, int tipoResultado, Intent intencao)
    {
        if(tipoResultado == RESULT_OK)
        {
            if(codTEla == ID_TELA_2)
            {
                Bundle vrDados = intencao.getExtras();
                String endereco = vrDados.getString("endereco");
                Toast vrMensagem = Toast.makeText(this, "endereco " + endereco, Toast.LENGTH_LONG );
                vrMensagem.show();
            }

        }
        else
        {
            Toast vrMensagem = Toast.makeText(this, "Operação cancelada...", Toast.LENGTH_LONG );
            vrMensagem.show();
        }
    }
}
