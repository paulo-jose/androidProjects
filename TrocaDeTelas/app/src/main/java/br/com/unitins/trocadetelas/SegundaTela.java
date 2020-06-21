package br.com.unitins.trocadetelas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SegundaTela extends AppCompatActivity implements DialogInterface.OnClickListener {

    private String login;
    private  int senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_segunda_tela);

        login =  getIntent().getExtras().getString("user");
        senha = getIntent().getExtras().getInt("senha");
    }

    public void onClick(DialogInterface vrDiagolo, int codBotao)
    {
        if(codBotao == DialogInterface.BUTTON_POSITIVE)
        {
            Bundle vrDados = new Bundle();
            vrDados.putString("endereco", "406 Norte alameda 7, lote 3");

            Intent intecaoRetorno = new Intent();
            intecaoRetorno.putExtras(vrDados);
            setResult(RESULT_OK, intecaoRetorno);
            finish();
        }
        else
        {
            Bundle vrDados = new Bundle();
            vrDados.putString("endereco", " não encontrado");

            Intent intecaoRetorno = new Intent();
            intecaoRetorno.putExtras(vrDados);
            setResult(RESULT_OK, intecaoRetorno);
            finish();
        }
    }



    public void voltaTela1(View botao)
    {

       // Intent intencao = new Intent(this, TerceiraTela.class);
       // startActivity(intencao);

        AlertDialog.Builder vrAlerta = new AlertDialog.Builder(SegundaTela.this);

        vrAlerta.setTitle("Atenção");
        vrAlerta.setMessage("Voce deseja excluir o contato!");
        vrAlerta.setPositiveButton("Sim", this);
        vrAlerta.setNegativeButton("Nao", this);
        vrAlerta.show();




 /*       Toast vrMensagem = Toast.makeText(this, "Login : " + login, Toast.LENGTH_LONG);
        vrMensagem.show();

        vrMensagem = Toast.makeText(this, "Senha : " + senha, Toast.LENGTH_LONG);
        vrMensagem.show();*/
    }


}
